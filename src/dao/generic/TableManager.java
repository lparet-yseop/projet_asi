package dao.generic;

import java.lang.reflect.Field;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import dao.annotation.DbAnnotationsManager;
import dao.utils.DBAccess;
import dao.utils.UnknownTableNameException;

/**
 * Database abstract manager
 * 
 * @author Lucas Grégoire
 * @param <T> The type of entity
 */
public class TableManager<T> {

    /**
     * Constructor of Table manager
     */
    public TableManager() {
    }

    /**
     * Select the entity using his primary keys
     * 
     * @param entity The entity object
     * @return The object if in the database or null
     */
    public T select( T entity ) {
        try {
            return execSelect(entity);
        }
        catch (IllegalArgumentException | IllegalAccessException | InstantiationException | UnknownTableNameException | SQLException
                | IndexOutOfBoundsException e) {
            e.printStackTrace();
            return null;
        }
    }

    private T execSelect( T entity ) throws UnknownTableNameException, SQLException, IllegalArgumentException, IllegalAccessException,
            InstantiationException, IndexOutOfBoundsException {
        String tableName = DbAnnotationsManager.getTableName(entity.getClass());

        Map<Field, String> pkeys = DbAnnotationsManager.getPrimaryKeysMap(entity.getClass());
        StringBuilder whereClause = new StringBuilder("SELECT * FROM " + tableName + " WHERE ");
        boolean first = true;

        // Generate where clause
        for (Entry<Field, String> field : pkeys.entrySet()) {
            if (!first)
                whereClause.append(",");
            whereClause.append(field.getValue() + " = ? ");
            first = false;
        }

        // Preparing statement
        PreparedStatement stmt = DBAccess.getConnection().prepareStatement(whereClause.toString());
        int i = 0;

        for (Entry<Field, String> field : pkeys.entrySet()) {
            field.getKey().setAccessible(true);
            stmt.setObject(++i, field.getKey().get(entity));
        }

        ResultSet rs = stmt.executeQuery();

        return fromResultSet(entity.getClass(), rs).get(0);
    }

    /**
     * Select the entity using his id
     * 
     * @param id The id of the object
     * @param instanceClass The instance of the class
     * @return The object if in the database or null
     */
    public T selectById( Integer id, Class<T> instanceClass ) {
        try {
            return execSelectById(id, instanceClass);
        }
        catch (IllegalArgumentException | IllegalAccessException | InstantiationException | UnknownTableNameException | SQLException
                | IndexOutOfBoundsException e) {
            e.printStackTrace();
            return null;
        }
    }

    private T execSelectById( Integer id, Class<T> instanceClass ) throws UnknownTableNameException, SQLException, IllegalArgumentException,
            IllegalAccessException, InstantiationException, IndexOutOfBoundsException {
        String tableName = DbAnnotationsManager.getTableName(instanceClass);

        Map<Field, String> pkeys = DbAnnotationsManager.getPrimaryKeysMap(instanceClass);
        StringBuilder whereClause = new StringBuilder("SELECT * FROM " + tableName + " WHERE ");
        boolean first = true;

        // Generate where clause
        for (Entry<Field, String> field : pkeys.entrySet()) {
            if (!first)
                whereClause.append(",");
            whereClause.append(field.getValue() + " = ? ");
            first = false;
        }

        // Preparing statement
        PreparedStatement stmt = DBAccess.getConnection().prepareStatement(whereClause.toString());
        int i = 0;

        for (Entry<Field, String> field : pkeys.entrySet()) {
            field.getKey().setAccessible(true);
            stmt.setObject(++i, id);
        }

        ResultSet rs = stmt.executeQuery();

        return fromResultSet(instanceClass, rs).get(0);
    }

    /**
     * Select all the entities in the database
     * 
     * @param instanceClass The instance of the class
     * @return The object if in the database or null
     */
    public List<T> selectAll( Class<T> instanceClass ) {
        try {
            return execSelectAll(instanceClass);
        }
        catch (IllegalArgumentException | IllegalAccessException | InstantiationException | UnknownTableNameException | SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    private List<T> execSelectAll( Class<T> instanceClass )
            throws UnknownTableNameException, SQLException, IllegalArgumentException, IllegalAccessException, InstantiationException {
        String tableName = DbAnnotationsManager.getTableName(instanceClass);
        StringBuilder clause = new StringBuilder("SELECT * FROM " + tableName);

        // Preparing statement
        PreparedStatement stmt = DBAccess.getConnection().prepareStatement(clause.toString());
        ResultSet rs = stmt.executeQuery();

        return fromResultSet(instanceClass, rs);
    }

    /**
     * Insert the entity in the table
     * 
     * @param entity The entity object
     */
    public void insert( T entity ) {
        try {
            execInsert(entity);
        }
        catch (IllegalArgumentException | IllegalAccessException | UnknownTableNameException | SQLException e) {
            e.printStackTrace();
        }
    }

    private void execInsert( T entity ) throws IllegalArgumentException, IllegalAccessException, UnknownTableNameException, SQLException {
        String tableName = DbAnnotationsManager.getTableName(entity.getClass());

        Map<Field, String> allKeys = DbAnnotationsManager.getAllKeysMap(entity.getClass());
        StringBuilder insertClause = new StringBuilder("INSERT INTO " + tableName + " (");
        StringBuilder valuesClause = new StringBuilder();
        boolean first = true;

        // Generate insert clause
        for (Entry<Field, String> field : allKeys.entrySet()) {
            field.getKey().setAccessible(true);
            if (field.getKey().get(entity) == null)
                continue;

            if (!first) {
                insertClause.append(",");
                valuesClause.append(",");
            }
            insertClause.append(field.getValue());
            valuesClause.append("?");
            first = false;
        }
        insertClause.append(") VALUES (");
        insertClause.append(valuesClause.toString());
        insertClause.append(")");

        // Preparing statement
        PreparedStatement stmt = DBAccess.getConnection().prepareStatement(insertClause.toString());
        int i = 0;

        for (Entry<Field, String> field : allKeys.entrySet()) {
            field.getKey().setAccessible(true);
            if (field.getKey().get(entity) == null)
                continue;

            stmt.setObject(++i, field.getKey().get(entity));
        }

        stmt.executeUpdate();
    }

    /**
     * Update the entity field in the table if they are not null
     * 
     * @param entity The entity object
     */
    public void update( T entity ) {
        try {
            execUpdate(entity);
        }
        catch (IllegalArgumentException | IllegalAccessException | UnknownTableNameException | SQLException e) {
            e.printStackTrace();
        }
    }

    private void execUpdate( T entity ) throws UnknownTableNameException, IllegalArgumentException, IllegalAccessException, SQLException {
        String tableName = DbAnnotationsManager.getTableName(entity.getClass());

        Map<Field, String> keys = DbAnnotationsManager.getPrimaryKeysMap(entity.getClass());
        Map<Field, String> noKeys = DbAnnotationsManager.getNonPrimaryKeysMap(entity.getClass());
        StringBuilder updateClause = new StringBuilder("UPDATE " + tableName + " SET ");
        StringBuilder whereClause = new StringBuilder(" WHERE ");
        boolean first = true;

        // Generate update clause
        for (Entry<Field, String> field : noKeys.entrySet()) {
            field.getKey().setAccessible(true);
            if (field.getKey().get(entity) == null)
                continue;

            if (!first) {
                updateClause.append(", ");
            }
            updateClause.append(field.getValue() + " = ?");
            first = false;
        }

        // Generate where clause
        first = true;
        for (Entry<Field, String> field : keys.entrySet()) {
            if (!first) {
                whereClause.append(", ");
            }
            whereClause.append(field.getValue() + " = ?");
            first = false;
        }
        updateClause.append(whereClause.toString());

        System.out.println(updateClause.toString());

        // Preparing statement
        PreparedStatement stmt = DBAccess.getConnection().prepareStatement(updateClause.toString());
        int i = 0;

        // Non primary
        for (Entry<Field, String> field : noKeys.entrySet()) {
            field.getKey().setAccessible(true);
            if (field.getKey().get(entity) == null)
                continue;

            stmt.setObject(++i, field.getKey().get(entity));
        }

        // Primary
        for (Entry<Field, String> field : keys.entrySet()) {
            field.getKey().setAccessible(true);
            stmt.setObject(++i, field.getKey().get(entity));
        }

        stmt.executeUpdate();
    }

    @SuppressWarnings( "unchecked" )
    private List<T> fromResultSet( Class<?> entityClass, ResultSet resultSet )
            throws InstantiationException, IllegalAccessException, IllegalArgumentException, SQLException {
        List<T> results = new ArrayList<T>();
        T currentInstance;

        if (resultSet.next()) {
            do {
                currentInstance = (T) entityClass.newInstance();

                for (Entry<Field, String> field : DbAnnotationsManager.getAllKeysMap(entityClass).entrySet()) {
                    field.getKey().setAccessible(true);
                    field.getKey().set(currentInstance, resultSet.getObject(field.getValue()));
                }

                results.add(currentInstance);
            } while (resultSet.next());
        }
        else {
            return new ArrayList<T>();
        }

        return results;
    }

    /**
     * @param args
     */
    public static void main( String[] args ) {
        // UserBean bean = new UserBean();
        // bean.setId(17);
        // // bean.setFirstname("testInsertFirstName");
        // // bean.setLastname("lastazeazeName");
        // // bean.setPassword("hello");
        // // bean.setDatebirthday(new Date());
        // // bean.setEmail("lucas@lsge.fr");
        //
        // TableManager<UserBean> mng = new TableManager<UserBean>();
        //
        // System.out.println(mng.select(bean));
    }

}
