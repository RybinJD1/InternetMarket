package projectMarket.daoImpl;

import projectMarket.entities.Buyer;
import projectMarket.entities.Entity;

import java.util.Collection;


public interface GenericDao<T extends Entity> {

    void add(T entity);

    void update(T entity);

    T getById(long id);

    void delete(T entity);

    void deleteAll();

    Collection getAll();

}
