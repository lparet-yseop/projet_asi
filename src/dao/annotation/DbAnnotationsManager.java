package dao.annotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import dao.utils.UnknownTableNameException;

/**
 * Database beans annotations engine
 * 
 * @author Lucas Grégoire
 */
public class DbAnnotationsManager {

    /**
     * Gets the table name of a bean class
     * 
     * @param entityClass The entity bean class
     * @return The table name relative to the bean
     * @throws UnknownTableNameException If the table name isn't set
     */
    public static String getTableName( Class<?> entityClass ) throws UnknownTableNameException {
        return getTableNameGeneric(entityClass, DBTable.class);
    }

    private static String getTableNameGeneric( Class<?> entityClass, Class<? extends Annotation> annotationClass ) throws UnknownTableNameException {
        if (entityClass.isAnnotationPresent(annotationClass)) {
            return ((DBTable) entityClass.getAnnotation(annotationClass)).value();
        }
        throw new UnknownTableNameException();
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

    private static Map<Field, String> getKeysFromPrimaryKeyStatus( Class<?> entityClass, Class<? extends Annotation> annotationClass,
            boolean statusPrimaryKey ) {
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
}
