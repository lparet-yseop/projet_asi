package dao.generic;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import beans.utils.DatabaseBean;
import dao.annotation.DBAnnotationsBeanValues;

/**
 * Database abstract manager
 * 
 * @author Lucas Grégoire
 * @param <T> The type of entity
 */
public class DatabaseManager<T extends DatabaseBean> {

    protected DatabaseConnector connector;
    protected DatabaseObjectMapper<T> mapper;
    protected Class<T> instanceClass;

    /**
     * Constructs the DatabaseManager for an instanceClass
     * 
     * @param instanceClass
     */
    public DatabaseManager(Class<T> instanceClass) {
        this.connector = DatabaseConnector.getInstance();
        this.mapper = new DatabaseObjectMapper<T>();
        this.instanceClass = instanceClass;
    }

    /**
     * Gets all the entities
     * 
     * @return The list of entities objects
     */
    public List<T> findAll() {
        try {
            String query = RequestGenerator.getSelectAll(instanceClass);
            ResultSet set = connector.executeQuery(query, null);

            return mapper.mapAll(set, instanceClass);
        }
        catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    /**
     * Gets one entity by its id
     * 
     * @param id The integer id
     * @return The entity object
     */
    public T findOneById( Integer id ) {
        try {
            String query = RequestGenerator.getSelectByPrimaryKey(instanceClass);

            Map<Integer, Object> map = new HashMap<Integer, Object>();
            map.put(1, id);
            ResultSet set = connector.executeQuery(query, map);

            return mapper.mapOne(set, instanceClass);
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Gets one entity by its primary keys
     * 
     * @param bean The bean to delete. Should contain all primary keys
     * @return The entity object
     */
    public T findOneByPrimaryKey( T bean ) {
        try {
            String query = RequestGenerator.getSelectByPrimaryKey(instanceClass);
            List<Object> keyValues = DBAnnotationsBeanValues.getPrimaryKeysValues(bean, instanceClass);

            Map<Integer, Object> map = new HashMap<Integer, Object>();
            int i = 0;
            for (Object keyVal : keyValues) {
                map.put(++i, keyVal);
            }
            ResultSet set = connector.executeQuery(query, map);

            return mapper.mapOne(set, instanceClass);
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Gets all entities using a where clause
     * 
     * @param whereClause The where clause
     * @param map The map of parameters
     * @return The list of entity object
     */
    public List<T> findAllByCustomWhereClause( String whereClause, Map<Integer, Object> map ) {
        try {
            String query = RequestGenerator.getSelectAll(instanceClass);
            query += "WHERE " + whereClause;
            ResultSet set = connector.executeQuery(query, map);

            return mapper.mapAll(set, instanceClass);
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Gets one entity using a where clause
     * 
     * @param whereClause The where clause
     * @param map The map of parameters
     * @return The entity object
     */
    public T findOneByCustomWhereClause( String whereClause, Map<Integer, Object> map ) {
        try {
            String query = RequestGenerator.getSelectAll(instanceClass);
            query += "WHERE " + whereClause;
            ResultSet set = connector.executeQuery(query, map);

            return mapper.mapOne(set, instanceClass);
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Delete one entity
     * 
     * @param bean The bean to delete. It should have all the primary keys values
     * @return The status of the deletion
     */
    public boolean delete( T bean ) {
        try {
            String query = RequestGenerator.getDeleteOne(instanceClass);
            List<Object> keyValues = DBAnnotationsBeanValues.getPrimaryKeysValues(bean, instanceClass);

            Map<Integer, Object> map = new HashMap<Integer, Object>();
            int i = 0;
            for (Object keyVal : keyValues) {
                map.put(++i, keyVal);
            }

            return connector.executeUpdate(query, map);
        }
        catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Update one entity
     * 
     * @param bean The bean to update. It should have all the primary keys values and other values
     * @param fieldsModified The map of fields modified
     * @return The status of the update
     */
    public boolean update( T bean, Map<Field, String> fieldsModified ) {
        try {
            String query = RequestGenerator.getUpdateOne(instanceClass, fieldsModified);
            List<Object> keyValues = DBAnnotationsBeanValues.getPrimaryKeysValues(bean, instanceClass);
            List<Object> modifiedValues = DBAnnotationsBeanValues.getFieldValuesOfBean(bean, fieldsModified.keySet());

            Map<Integer, Object> map = new HashMap<Integer, Object>();
            int i = 0;

            for (Object modifVal : modifiedValues) {
                map.put(++i, modifVal);
            }
            for (Object keyVal : keyValues) {
                map.put(++i, keyVal);
            }

            return connector.executeUpdate(query, map);
        }
        catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Insert one entity
     * 
     * @param bean The bean to insert. It should have all the primary keys values and other values
     * @return The status of the update
     */
    public boolean insert( T bean ) {
        try {
            String query = RequestGenerator.getInsertOne(instanceClass);
            List<Object> allValues = DBAnnotationsBeanValues.getAllKeysValues(bean, instanceClass);

            Map<Integer, Object> map = new HashMap<Integer, Object>();
            int i = 0;

            for (Object val : allValues) {
                map.put(++i, val);
            }

            return connector.executeUpdate(query, map);
        }
        catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}
