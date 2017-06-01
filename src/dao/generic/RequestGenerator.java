package dao.generic;

import java.lang.reflect.Field;
import java.util.Map.Entry;

import beans.utils.DatabaseBean;
import dao.annotation.DBAnnotationsManager2;
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

        sb.append(DBAnnotationsManager2.getTableName(entityClass));
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

    private static String getJoinClause( Class<? extends DatabaseBean> entityClass ) {
        StringBuilder sb = new StringBuilder(ESP);

        for (DBJoin join : DBAnnotationsManager2.getDBJoins(entityClass).values()) {
            sb.append("JOIN");
            sb.append(ESP);
            sb.append(DBAnnotationsManager2.getTableName(join.joinClass()));
            sb.append(ESP);
            sb.append("ON");
            sb.append(ESP);
            sb.append(DBAnnotationsManager2.getTableName(entityClass));
            sb.append(".");
            sb.append(join.srcColumn());
            sb.append(ESP + "=" + ESP);
            sb.append(DBAnnotationsManager2.getTableName(join.joinClass()));
            sb.append(".");
            sb.append(join.destColumn());
            sb.append(ESP);
        }

        return sb.toString();
    }

    private static String getWhereClause( Class<? extends DatabaseBean> entityClass ) {
        StringBuilder sb = new StringBuilder("WHERE" + ESP);
        boolean first = true;

        for (Entry<Field, String> entry : DBAnnotationsManager2.getPrimaryKeysMap(entityClass).entrySet()) {
            if (!first)
                sb.append("," + ESP);
            sb.append(entry.getValue() + ESP + "= ?");
            first = false;
        }

        return sb.toString();
    }

}
