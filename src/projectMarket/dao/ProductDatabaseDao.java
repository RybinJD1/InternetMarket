package projectMarket.dao;


import projectMarket.connectionDB.DatabaseConnection;
import projectMarket.daoImpl.ProductDao;

import projectMarket.entities.Buyer;
import projectMarket.entities.Product;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


public class ProductDatabaseDao implements ProductDao {

    private static final String SQL_ADD_PRODUCT_QUERY = "INSERT INTO product (name, description, cost, remainder)" +
            "VALUES(?, ?, ?, ?)";
    private static final String SQL_FIND_BY_ID = "SSLECT * FROM product WHERE id = ?";
    private static final String SQL_UPDATE_PRODUCT_QUERY = "UPDATE product SET name = ?, description=?, cost=?, remainder=? WHERE id = ?";
    private static final String SQL_DELETE_PRODUCT_QUERY = "DELETE FROM product WHERE name = ?";
    private static final String SQL_DELETE_ALL_PRODUCTS_QUERY = "TRUNCATE TABLE product";
    private static final String SQL_SELECT_ALL_PRODUCT_QUERY = "SELECT * FROM product";


    public ProductDatabaseDao() {
    }

    @Override
    public void add(Product product) {
        Connection connection = DatabaseConnection.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_ADD_PRODUCT_QUERY);
            preparedStatement.setString(1, product.getName());
            preparedStatement.setString(2, product.getDescription());
            preparedStatement.setInt(3, product.getCost());
            preparedStatement.setInt(4, product.getRemainder());
            preparedStatement.execute();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection(connection);
        }

    }

    @Override
    public void update(Product product) {
        Connection connection = DatabaseConnection.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE_PRODUCT_QUERY);
            preparedStatement.setLong(5, product.getId());
            preparedStatement.setString(1, product.getName());
            preparedStatement.setString(2, product.getDescription());
            preparedStatement.setInt(3, product.getCost());
            preparedStatement.setInt(4, product.getRemainder());
            preparedStatement.execute();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection(connection);
        }
    }

    @Override
    public Product getById(long id) {
        Product result = null;
        Connection connection = DatabaseConnection.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_BY_ID);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet != null && resultSet.next()) {
                result = new Product(resultSet.getLong(1), resultSet.getString(2), resultSet.getString(3),
                        resultSet.getInt(4), resultSet.getInt(5));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection(connection);
        }
        return result;
    }

    @Override
    public void delete(Product product) {
        Connection connection = DatabaseConnection.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE_PRODUCT_QUERY);
            preparedStatement.setString(1, product.getName());
            preparedStatement.execute();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection(connection);
        }
    }

    @Override
    public void deleteAll() {
        Connection connection = DatabaseConnection.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE_ALL_PRODUCTS_QUERY);
            preparedStatement.execute();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection(connection);
        }
    }

    @Override
    public Collection getAll() {
        List<Product> result = new ArrayList<>();
        Connection connection = DatabaseConnection.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_ALL_PRODUCT_QUERY);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet != null && resultSet.next()) {
                Product product = new Product(resultSet.getLong(1), resultSet.getString(2), resultSet.getString(3),
                        resultSet.getInt(4), resultSet.getInt(5));
                result.add(product);
            }
            preparedStatement.execute();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection(connection);
        }
        return result;
    }
}
