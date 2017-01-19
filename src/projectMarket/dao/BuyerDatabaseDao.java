package projectMarket.dao;

import projectMarket.connectionDB.DatabaseConnection;
import projectMarket.daoImpl.BuyerDao;

import projectMarket.entities.Buyer;


import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class BuyerDatabaseDao implements BuyerDao {

    private static final String SQL_FIND_BY_ID_BUYER_QUERY = "SELECT * FROM buyer WHERE id = ?";
    private static final String SQL_ADD_BUYER_QUERY = "INSERT INTO buyer(name, surname, email, password, phone, address)" +
            "VALUES(?, ?, ?, ?, ?, ?)";
    private static final String SQL_UPDATE_BUYER_QUERY = "UPDATE buyer SET name = ?, surname = ?, email = ?, password = ?," +
            "phone = ?, address = ? WHERE id = ?";
    private static final String SQL_DELETE_BUYER_QUERY = "DELETE FROM buyer WHERE surname = ?";
    private static final String SQL_DELETE_ALL_BUYERS_QUERY = "TRUNCATE TABLE buyer";
    private static final String SQL_SELECT_ALL_BUYERS_QUERY = "SELECT * FROM buyer";

    public BuyerDatabaseDao() {
    }

    @Override
    public void add(Buyer buyer) {
        Connection connection = DatabaseConnection.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_ADD_BUYER_QUERY);
            preparedStatement.setString(1, buyer.getName());
            preparedStatement.setString(2, buyer.getSurname());
            preparedStatement.setString(3, buyer.getEmail());
            preparedStatement.setString(4, buyer.getPassword());
            preparedStatement.setString(5, buyer.getPhone());
            preparedStatement.setString(6, buyer.getAddress());
            preparedStatement.execute();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection(connection);
        }
    }

    @Override
    public void update(Buyer buyer) {
        Connection connection = DatabaseConnection.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE_BUYER_QUERY);
            preparedStatement.setLong(7, buyer.getId());
            preparedStatement.setString(1, buyer.getName());
            preparedStatement.setString(2, buyer.getSurname());
            preparedStatement.setString(3, buyer.getEmail());
            preparedStatement.setString(4, buyer.getPassword());
            preparedStatement.setString(5, buyer.getPhone());
            preparedStatement.setString(6, buyer.getAddress());
            preparedStatement.execute();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection(connection);
        }
    }

    @Override
    public Buyer getById(long id) {
        Buyer result = null;
        Connection connection = DatabaseConnection.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_BY_ID_BUYER_QUERY);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet != null && resultSet.next()) {
                result = new Buyer(resultSet.getLong(1), resultSet.getString(2), resultSet.getString(3),
                        resultSet.getString(4), resultSet.getString(5), resultSet.getString(6), resultSet.getString(7));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection(connection);
        }
        return result;
    }

    @Override
    public void delete(Buyer buyer) {
        Connection connection = DatabaseConnection.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE_BUYER_QUERY);
            preparedStatement.setString(1, buyer.getSurname());
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
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE_ALL_BUYERS_QUERY);
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
        List<Buyer> result = new ArrayList<>();
        Connection connection = DatabaseConnection.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_ALL_BUYERS_QUERY);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet != null && resultSet.next()) {
                Buyer buyer = new Buyer(resultSet.getLong(1), resultSet.getString(2), resultSet.getString(3),
                        resultSet.getString(4), resultSet.getString(5), resultSet.getString(6), resultSet.getString(7));
                result.add(buyer);
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
