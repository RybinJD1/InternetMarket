package projectMarket.dao;

import projectMarket.daoImpl.OrdersDao;
import projectMarket.entities.Orders;

import java.util.Collection;

/**
 * Created by AlexFisher on 16.01.2017.
 */
public class OrdersDatabaseDao implements OrdersDao{

    private static final String SQL_FIND_BY_ID_ORDERS_QUERY = "SELECT * FROM orders WHERE id = ?";
    private static final String SQL_ADD_ORDERS_QUERY = "INSERT INTO orders(quantityOfProducts, productsInBooking, email, password, phone, address)" +
            "VALUES(?, ?, ?, ?, ?, ?)";
    private static final String SQL_UPDATE_ORDERS_QUERY = "UPDATE orders SET name = ?, surname = ?, email = ?, password = ?," +
            "phone = ?, address = ? WHERE id = ?";
    private static final String SQL_DELETE_ORDERS_QUERY = "DELETE FROM orders WHERE surname = ?";
    private static final String SQL_DELETE_ALL_ORDERS_QUERY = "TRUNCATE TABLE orders";
    private static final String SQL_SELECT_ALL_ORDERS_QUERY = "SELECT * FROM orders";


    @Override
    public void add(Orders entity) {

    }

    @Override
    public void update(Orders entity) {

    }

    @Override
    public Orders getById(long id) {
        return null;
    }

    @Override
    public void delete(Orders entity) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public Collection getAll() {
        return null;
    }
}
