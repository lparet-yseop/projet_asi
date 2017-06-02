package dao.generic;

import java.util.List;

import javax.faces.bean.ApplicationScoped;

import beans.utils.DatabaseBean;
import dao.impl.UserDAO;

/**
 * The dao implementation of basic CRUD methods
 * 
 * @author Lucas Grégoire
 * @param <T> The type of entities
 */
@ApplicationScoped
public class AbstractDAO<T extends DatabaseBean> implements DAO<T> {

    protected TableManager<T> manager;
    protected DatabaseManager<T> manager2;

    protected AbstractDAO(Class<T> instanceClass) {
        this.manager = new TableManager<T>();
        this.manager2 = new DatabaseManager<T>(instanceClass);
    }

    @Override
    public List<T> findAll() {
        return manager2.findAll();
    }

    @Override
    public T findOneById( Integer id ) {
        return manager2.findOneById(id);
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

    /**
     * @param args
     */
    public static void main( String[] args ) {
        try {
            UserDAO userdao = new UserDAO();

             System.out.println(userdao.findAll());

            //System.out.println(userdao.findOneById(2));

            // // System.out.println(DatabaseObjectMapper.mapAll(co.executeRequest(RequestGenerator.getSelectAll(RecipeBean.class), null), RecipeBean.class));
            //
            // Map<Integer, Object> map = new HashMap<Integer, Object>();
            // map.put(1, 2);
            // System.out.println(
            // DatabaseObjectMapper.mapAll(co.executeRequest(RequestGenerator.getSelectByPrimaryKey(RecipeBean.class), map), RecipeBean.class));
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

}
