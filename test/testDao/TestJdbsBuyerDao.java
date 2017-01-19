package testDao;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import projectMarket.dao.BuyerDatabaseDao;
import projectMarket.connectionDB.DatabaseConnection;
import projectMarket.entities.Buyer;

import java.sql.*;
import java.util.Collection;


public class TestJdbsBuyerDao {

    private static final String CREATE_BUYERS_TABLE_QUERY = "CREATE TABLE buyer(id int auto_increment, name varchar(60)" +
            " not null, surname varchar(60) not null, email varchar(60) unique not null, password varchar(60) not null," +
            " phone varchar(60) not null, address varchar(60) not null, primary key(id))";
    private BuyerDatabaseDao buyerDatabaseDao = new BuyerDatabaseDao();
    // DELETE FROM `project_market`.`buyers` WHERE `id`='9';


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

    @Test
    public void testAddedBuyer() throws SQLException {
        buyerDatabaseDao.add(new Buyer("Andrei", "Bacenko", "a.bacenko@gmail.com", "12345", "2563152", "Minsk, Kirova 18, 1"));
        buyerDatabaseDao.add(new Buyer("Maksim", "Tkach", "m.tkach@gmail.com", "12345", "2561523", "Minsk, Kulman 8, 21"));
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT COUNT(*) FROM buyer");
        resultSet.next();
        Assert.assertEquals(2, resultSet.getInt(1));
    }

    @Test
    public void testUpdateBuyer() {
        buyerDatabaseDao.update(new Buyer(8, "Ivan", "Doroshko", "i.doroshko@gmail.com", "12345", "2561524", "Minsk, Kulman 8, 22"));

    }

    @Test
    public void testGetBuyerByID() throws SQLException {
        Buyer buyer = buyerDatabaseDao.getById(8L);
        Assert.assertEquals("Tkach", buyer.getSurname());
    }

    @Test
    public void testDeleteBySurname() {
        buyerDatabaseDao.delete(new Buyer("Bacenko"));
    }

    @Test
    public void testDeleteAll() {
        buyerDatabaseDao.deleteAll();
    }

    @Test
    public void testGetAll() {
        buyerDatabaseDao.getAll();

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

    private void createBuyersTable() throws SQLException {
        Statement statement = connection.createStatement();
        statement.executeUpdate(CREATE_BUYERS_TABLE_QUERY);
        statement.close();
    }

    private void dropBuyerTable() throws SQLException {
        connection.createStatement().executeUpdate("DROP TABLE IF EXISTS buyer");
    }

    /*
        addBuyer("Андрей", "Баценко", "a.bazenko@gmail.com", "12345", "2563152", "Minsk, Kirova 18, 1");
        addBuyer("Максим", "Ткач", "m.tkach@gmail.com", "12345", "2561523", "Minsk, Kulman 8, 21");
    */


}
