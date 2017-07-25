package dao.generic;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.faces.bean.ApplicationScoped;

import beans.utils.DatabaseBean;
import dao.annotation.DBAnnotationsManager;
import dao.impl.UserDAO;

/**
 * The dao implementation of basic CRUD methods
 * 
 * @author Lucas Grégoire
 * @param <T> The type of entities
 */
@ApplicationScoped
public class AbstractDAO<T extends DatabaseBean> implements DAO<T> {

    protected DatabaseManager<T> manager;

    protected AbstractDAO(Class<T> instanceClass) {
        this.manager = new DatabaseManager<T>(instanceClass);
    }

    @Override
    public List<T> findAll() {
        return manager.findAll();
    }

    @Override
    public T findOneById( Integer id ) {
        return manager.findOneById(id);
    }

    @Override
    public T insert( T entity ) {
        manager.insert(entity);
        return manager.findOneByPrimaryKey(entity);
    }

    @Override
    public T update( T entity ) {
        return execUpdate(entity, manager.findOneByPrimaryKey(entity));
    }

    @Override
    public T save( T entity ) {
        T prevEntity = manager.findOneByPrimaryKey(entity);
        if (prevEntity == null)
            manager.insert(entity);
        else
            return execUpdate(entity, prevEntity);
        return manager.findOneByPrimaryKey(entity);
    }

    private T execUpdate( T entity, T prevEntity ) {
        Map<Field, String> fieldsModified = new HashMap<Field, String>();

        try {
            for (Entry<Field, String> entry : DBAnnotationsManager.getNonPrimaryKeysMap(prevEntity.getClass()).entrySet()) {
                entry.getKey().setAccessible(true);

                if (entry.getKey().get(entity) != null && !entry.getKey().get(entity).equals(entry.getKey().get(prevEntity)))
                    fieldsModified.put(entry.getKey(), entry.getValue());
            }

            if (!fieldsModified.isEmpty())
                manager.update(entity, fieldsModified);
        }
        catch (IllegalArgumentException | IllegalAccessException e) {
            e.printStackTrace();
        }

        return manager.findOneByPrimaryKey(entity);
    }

    @Override
    public boolean delete( T entity ) {
        return manager.delete(entity);
    }

}
