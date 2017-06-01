package dao.generic;

import java.sql.ResultSet;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import beans.utils.DatabaseBean;

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
            ResultSet set = connector.executeRequest(query, null);

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
            ResultSet set = connector.executeRequest(query, map);

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
            ResultSet set = connector.executeRequest(query, map);

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
            ResultSet set = connector.executeRequest(query, map);

            return mapper.mapOne(set, instanceClass);
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
