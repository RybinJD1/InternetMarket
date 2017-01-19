package testDao;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import projectMarket.connectionDB.DatabaseConnection;
import projectMarket.dao.ProductDatabaseDao;
import sun.rmi.transport.Connection;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by AlexFisher on 18.01.2017.
 */
public class TestJDdbsProductDao {

    private static final String CREATE_PRODUCT_TABLE_QUERY = "CREATE TABLE product(id int auto_increment, name varchar(60)" +
            " not null, description varchar(60) not null, cost int, remainder int, primary key(id))";
    private ProductDatabaseDao productDatabaseDao = new ProductDatabaseDao();

    private Connection connection;

    @Before
    public void initDb() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        connection = DatabaseConnection.getConnection();
        Assert.assertNotNull(connection);
    }

    @Test
    public void testBuyersTableCreated() throws SQLException {
        createBuyersTable();
        DatabaseMetaData metaData = connection.getMetaData();
        ResultSet resultSet = metaData.getTables(null, null, "buyer", null);
        Assert.assertTrue(resultSet.next());
    }





    @After
    public void closeConnection() {
        try {
            //dropBuyerTable();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
