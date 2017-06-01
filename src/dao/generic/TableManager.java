package dao.generic;

import java.lang.reflect.Field;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import dao.annotation.DBAnnotationsManager;
import dao.utils.DBAccess;

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
     * Execute a select with custom request and parameters
     * 
     * @param instanceClass The type class to return
     * @param request The request
     * @param parameters The list of parameters
     * @return The bean returned or null
     */
    public T executeSelect( Class<T> instanceClass, String request, List<Object> parameters ) {
        try {
            // Preparing statement
            PreparedStatement stmt = DBAccess.getConnection().prepareStatement(request);
            int i = 0;

            // Setting parameters
            for (Object obj : parameters)
                stmt.setObject(++i, obj);

            ResultSet rs = stmt.executeQuery();
            return fromResultSet(instanceClass, rs).get(0);
        }
        catch (IndexOutOfBoundsException | InstantiationException | IllegalAccessException | IllegalArgumentException | SQLException e) {
            return null;
        }
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
        catch (IllegalArgumentException | IllegalAccessException | InstantiationException | SQLException | IndexOutOfBoundsException e) {
            e.printStackTrace();
            return null;
        }
    }

    @SuppressWarnings( "unchecked" )
    private T execSelect( T entity )
            throws SQLException, IllegalArgumentException, IllegalAccessException, InstantiationException, IndexOutOfBoundsException {
        String tableName = DBAnnotationsManager.getTableName(entity.getClass());

        Map<Field, String> pkeys = DBAnnotationsManager.getPrimaryKeysMap(entity.getClass());
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

        return fromResultSet((Class<T>) entity.getClass(), rs).get(0);
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
        catch (IllegalArgumentException | IllegalAccessException | InstantiationException | SQLException | IndexOutOfBoundsException e) {
            e.printStackTrace();
            return null;
        }
    }

    private T execSelectById( Integer id, Class<T> instanceClass )
            throws SQLException, IllegalArgumentException, IllegalAccessException, InstantiationException, IndexOutOfBoundsException {
        String tableName = DBAnnotationsManager.getTableName(instanceClass);

        Map<Field, String> pkeys = DBAnnotationsManager.getPrimaryKeysMap(instanceClass);
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
        catch (IllegalArgumentException | IllegalAccessException | InstantiationException | SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    private List<T> execSelectAll( Class<T> instanceClass )
            throws SQLException, IllegalArgumentException, IllegalAccessException, InstantiationException {
        String tableName = DBAnnotationsManager.getTableName(instanceClass);
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
        catch (IllegalArgumentException | IllegalAccessException | SQLException e) {
            e.printStackTrace();
        }
    }

    private void execInsert( T entity ) throws IllegalArgumentException, IllegalAccessException, SQLException {
        String tableName = DBAnnotationsManager.getTableName(entity.getClass());

        Map<Field, String> allKeys = DBAnnotationsManager.getAllKeysMap(entity.getClass());
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
        catch (IllegalArgumentException | IllegalAccessException | SQLException e) {
            e.printStackTrace();
        }
    }

    private void execUpdate( T entity ) throws IllegalArgumentException, IllegalAccessException, SQLException {
        String tableName = DBAnnotationsManager.getTableName(entity.getClass());

        Map<Field, String> keys = DBAnnotationsManager.getPrimaryKeysMap(entity.getClass());
        Map<Field, String> noKeys = DBAnnotationsManager.getNonPrimaryKeysMap(entity.getClass());
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

    /**
     * Delete the entity in the table
     * 
     * @param entity The entity object
     * @return The status of the deletion
     */
    public boolean delete( T entity ) {
        try {
            return execDelete(entity);
        }
        catch (IllegalArgumentException | IllegalAccessException | SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    private boolean execDelete( T entity ) throws IllegalArgumentException, IllegalAccessException, SQLException {
        String tableName = DBAnnotationsManager.getTableName(entity.getClass());

        Map<Field, String> keys = DBAnnotationsManager.getPrimaryKeysMap(entity.getClass());
        StringBuilder clause = new StringBuilder("DELETE FROM " + tableName + " WHERE ");
        boolean first = true;

        // Generate where clause
        first = true;
        for (Entry<Field, String> field : keys.entrySet()) {
            if (!first) {
                clause.append(", ");
            }
            clause.append(field.getValue() + " = ?");
            first = false;
        }

        // Preparing statement
        PreparedStatement stmt = DBAccess.getConnection().prepareStatement(clause.toString());
        int i = 0;

        // Primary
        for (Entry<Field, String> field : keys.entrySet()) {
            field.getKey().setAccessible(true);
            stmt.setObject(++i, field.getKey().get(entity));
        }

        return stmt.executeUpdate() > 0;
    }

    private List<T> fromResultSet( Class<T> entityClass, ResultSet resultSet )
            throws InstantiationException, IllegalAccessException, IllegalArgumentException, SQLException {
        List<T> results = new ArrayList<T>();
        T currentInstance;

        if (resultSet.next()) {
            do {
                currentInstance = (T) entityClass.newInstance();

                for (Entry<Field, String> field : DBAnnotationsManager.getAllKeysMap(entityClass).entrySet()) {
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
        // bean.setFirstname("testInsertFirstName");
        // bean.setLastname("lastazeazeName");
        // bean.setPassword("testtest");
        // bean.setDatebirthday(new Date());
        // bean.setEmail("lucas@lsge.fr");
        // bean.setLogin("lucas");
        //
        // TableManager<UserBean> mng = new TableManager<UserBean>();
        //
        // System.out.println(mng.selectAll(UserBean.class));
        //
        // mng.insert(bean);
        //
        // System.out.println(mng.selectAll(UserBean.class));
    }

}
