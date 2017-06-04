package dao.annotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import javax.faces.bean.ApplicationScoped;

import beans.utils.DatabaseBean;

/**
 * Database beans annotations engine
 * 
 * @author Lucas Grégoire
 */
@ApplicationScoped
public class DBAnnotationsManager {

    /**
     * Gets the table name of a bean class
     * 
     * @param entityClass The entity bean class
     * @return The table name relative to the bean
     */
    public static String getTableName( Class<?> entityClass ) {
        return getTableNameGeneric(entityClass, DBTable.class);
    }

    private static String getTableNameGeneric( Class<?> entityClass, Class<? extends Annotation> annotationClass ) {
        if (entityClass.isAnnotationPresent(annotationClass)) {
            return ((DBTable) entityClass.getAnnotation(annotationClass)).value();
        }
        return null;
    }

    /**
     * Gets the primary key map of fields relative to a bean class
     * 
     * @param entityClass The entity class
     * @return The map of bean field, table column
     */
    public static Map<Field, String> getPrimaryKeysMap( Class<?> entityClass ) {
        return getKeysFromPrimaryKeyStatus(entityClass, DBColumn.class, true);
    }

    /**
     * Gets the non primary key map of fields relative to a bean class
     * 
     * @param entityClass The entity class
     * @return The map of bean field, table column
     */
    public static Map<Field, String> getNonPrimaryKeysMap( Class<?> entityClass ) {
        return getKeysFromPrimaryKeyStatus(entityClass, DBColumn.class, false);
    }

    private static Map<Field, String> getKeysFromPrimaryKeyStatus( Class<?> entityClass, Class<? extends Annotation> annotationClass, boolean statusPrimaryKey ) {
        Map<Field, String> keys = new HashMap<Field, String>();

        for (Field field : entityClass.getDeclaredFields()) {
            if (field.isAnnotationPresent(annotationClass)) {
                DBColumn col = (DBColumn) field.getAnnotation(annotationClass);

                if (col.primaryKey() == statusPrimaryKey)
                    keys.put(field, col.value());
            }
        }
        return keys;
    }

    /**
     * Gets all fields and columns of entity bean
     * 
     * @param entityClass The class of the bean
     * @return The map of bean field, table column
     */
    public static Map<Field, String> getAllKeysMap( Class<?> entityClass ) {
        return getAllKeysMapGeneric(entityClass, DBColumn.class);
    }

    private static Map<Field, String> getAllKeysMapGeneric( Class<?> entityClass, Class<? extends Annotation> annotationClass ) {
        Map<Field, String> keys = new HashMap<Field, String>();

        for (Field field : entityClass.getDeclaredFields()) {
            if (field.isAnnotationPresent(annotationClass)) {
                DBColumn col = (DBColumn) field.getAnnotation(annotationClass);

                keys.put(field, col.value());
            }
        }
        return keys;
    }

    /**
     * Gets the field class map of the subbeans
     * 
     * @param entityClass The class to parse te DBJoin
     * @return The map of bean field, sub bean class
     */
    public static Map<Field, DBJoin> getDBJoins( Class<? extends DatabaseBean> entityClass ) {
        Map<Field, DBJoin> map = new HashMap<Field, DBJoin>();

        for (Field field : entityClass.getDeclaredFields()) {
            if (field.isAnnotationPresent(DBJoin.class)) {
                map.put(field, (DBJoin) field.getAnnotation(DBJoin.class));
            }
        }

        return map;
    }
}
