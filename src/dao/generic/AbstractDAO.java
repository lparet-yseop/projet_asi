package dao.generic;

import java.util.List;

import javax.faces.bean.ApplicationScoped;

/**
 * The dao implementation of basic CRUD methods
 * 
 * @author Lucas Grégoire
 * @param <T> The type of entities
 */
@ApplicationScoped
public class AbstractDAO<T> implements DAO<T> {

    protected TableManager<T> manager;
    protected Class<T> instanceClass;

    protected AbstractDAO(Class<T> instanceClass) {
        this.manager = new TableManager<T>();
        this.instanceClass = instanceClass;
    }

    @Override
    public List<T> findAll() {
        return manager.selectAll(instanceClass);
    }

    @Override
    public T findOneById( Integer id ) {
        return manager.selectById(id, instanceClass);
    }

    @Override
    public T insert( T entity ) {
        manager.insert(entity);
        return manager.select(entity);
    }

    @Override
    public T update( T entity ) {
        manager.update(entity);
        return manager.select(entity);
    }

    @Override
    public T save( T entity ) {
        if (manager.select(entity) == null)
            manager.insert(entity);
        else
            manager.update(entity);
        return manager.select(entity);
    }

    @Override
    public boolean delete( T entity ) {
        return manager.delete(entity);
    }

}