package dao.generic;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.Map.Entry;

import beans.utils.DatabaseBean;
import dao.annotation.DBAnnotationsManager;
import dao.annotation.DBJoin;

/**
 * Request generator used by abstract DAO
 * 
 * @author Lucas Grégoire
 */
public class RequestGenerator {

    private final static String ESP = " ";

    /**
     * Generates the select all query for the class bean
     * 
     * @param entityClass The class of the bean
     * @return The query generated
     */
    public static String getSelectAll( Class<? extends DatabaseBean> entityClass ) {
        StringBuilder sb = new StringBuilder("SELECT * FROM ");

        sb.append(DBAnnotationsManager.getTableName(entityClass));
        sb.append(getJoinClause(entityClass));

        return sb.toString();
    }

    /**
     * Generates the select one query for the class bean
     * 
     * @param entityClass The class of the bean
     * @return The query generated
     */
    public static String getSelectByPrimaryKey( Class<? extends DatabaseBean> entityClass ) {
        StringBuilder sb = new StringBuilder(getSelectAll(entityClass));
        sb.append(getWhereClause(entityClass));

        return sb.toString();
    }

    /**
     * Generates the delete one query for the class bean
     * 
     * @param entityClass The class of the bean
     * @return The query generated
     */
    public static String getDeleteOne( Class<? extends DatabaseBean> entityClass ) {
        StringBuilder sb = new StringBuilder("DELETE FROM ");

        sb.append(DBAnnotationsManager.getTableName(entityClass));
        sb.append(ESP);
        sb.append(getWhereClause(entityClass));

        return sb.toString();
    }

    /**
     * Generates the update one query for the class bean
     * 
     * @param entityClass The class of the bean
     * @param fieldsModified The map of modified fields
     * @return The query generated
     */
    public static String getUpdateOne( Class<? extends DatabaseBean> entityClass, Map<Field, String> fieldsModified ) {
        StringBuilder sb = new StringBuilder("UPDATE ");

        sb.append(DBAnnotationsManager.getTableName(entityClass));
        sb.append(ESP);
        sb.append(getSetClause(entityClass, fieldsModified));
        sb.append(ESP);
        sb.append(getWhereClause(entityClass));

        return sb.toString();
    }

    /**
     * Generates the insert one query for the class bean
     * 
     * @param entityClass The class of the bean
     * @return The query generated
     */
    public static String getInsertOne( Class<? extends DatabaseBean> entityClass ) {
        StringBuilder sb = new StringBuilder("INSERT INTO ");

        sb.append(DBAnnotationsManager.getTableName(entityClass));
        sb.append(ESP);
        sb.append(getInsertColumns(entityClass));
        sb.append(ESP);
        sb.append(getInsertValues(entityClass));

        return sb.toString();
    }

    private static String getJoinClause( Class<? extends DatabaseBean> entityClass ) {
        StringBuilder sb = new StringBuilder(ESP);

        for (DBJoin join : DBAnnotationsManager.getDBJoins(entityClass).values()) {
            sb.append("JOIN");
            sb.append(ESP);
            sb.append(DBAnnotationsManager.getTableName(join.joinClass()));
            sb.append(ESP);
            sb.append("ON");
            sb.append(ESP);
            sb.append(DBAnnotationsManager.getTableName(entityClass));
            sb.append(".");
            sb.append(join.srcColumn());
            sb.append(ESP + "=" + ESP);
            sb.append(DBAnnotationsManager.getTableName(join.joinClass()));
            sb.append(".");
            sb.append(join.destColumn());
            sb.append(ESP);
        }

        return sb.toString();
    }

    private static String getWhereClause( Class<? extends DatabaseBean> entityClass ) {
        StringBuilder sb = new StringBuilder("WHERE" + ESP);
        boolean first = true;

        for (Entry<Field, String> entry : DBAnnotationsManager.getPrimaryKeysMap(entityClass).entrySet()) {
            if (!first)
                sb.append("," + ESP);
            sb.append(entry.getValue() + ESP + "= ?");
            first = false;
        }

        return sb.toString();
    }

    private static String getSetClause( Class<? extends DatabaseBean> entityClass, Map<Field, String> fieldsModified ) {
        StringBuilder sb = new StringBuilder("SET" + ESP);
        boolean first = true;

        for (Entry<Field, String> entry : fieldsModified.entrySet()) {
            if (!first)
                sb.append("," + ESP);
            sb.append(entry.getValue() + ESP + "= ?");
            first = false;
        }

        return sb.toString();
    }

    private static String getInsertColumns( Class<? extends DatabaseBean> entityClass ) {
        StringBuilder sb = new StringBuilder("(");
        boolean first = true;

        for (Entry<Field, String> entry : DBAnnotationsManager.getAllKeysMap(entityClass).entrySet()) {
            if (!first)
                sb.append("," + ESP);
            sb.append(entry.getValue() + ESP);
            first = false;
        }
        sb.append(" )");
        
        return sb.toString();
    }

    private static String getInsertValues( Class<? extends DatabaseBean> entityClass ) {
        StringBuilder sb = new StringBuilder("VALUES (" + ESP);

        for (int i = 0; i < DBAnnotationsManager.getAllKeysMap(entityClass).size(); i++) {
            if (i > 0)
                sb.append("," + ESP);
            sb.append("?");
        }

        sb.append(")");
        return sb.toString();
    }

}
