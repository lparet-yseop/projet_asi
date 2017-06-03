package dao.annotation;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.faces.bean.ApplicationScoped;

import beans.utils.DatabaseBean;

/**
 * Database beans annotations util
 * 
 * @author Lucas Grégoire
 */
@ApplicationScoped
public class DBAnnotationsBeanValues {

    /**
     * Get the primary keys values of a bean
     * 
     * @param bean The bean to manage
     * @param instanceClass The instance class of the bean
     * @return The list of primary key values as object
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     */
    public static List<Object> getPrimaryKeysValues( DatabaseBean bean, Class<?> instanceClass ) throws IllegalArgumentException, IllegalAccessException {
        List<Object> values = new ArrayList<Object>();

        for (Field field : DBAnnotationsManager.getPrimaryKeysMap(instanceClass).keySet()) {
            field.setAccessible(true);
            values.add(field.get(bean));
        }

        return values;
    }

    /**
     * Get the non primary keys values of a bean
     * 
     * @param bean The bean to manage
     * @param instanceClass The instance class of the bean
     * @return The list of primary non key values as object
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     */
    public static List<Object> getNonPrimaryKeysValues( DatabaseBean bean, Class<?> instanceClass ) throws IllegalArgumentException, IllegalAccessException {
        List<Object> values = new ArrayList<Object>();

        for (Field field : DBAnnotationsManager.getNonPrimaryKeysMap(instanceClass).keySet()) {
            field.setAccessible(true);
            values.add(field.get(bean));
        }

        return values;
    }

    /**
     * Get all values of a bean
     * 
     * @param bean The bean to manage
     * @param instanceClass The instance class of the bean
     * @return The list of all values as object
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     */
    public static List<Object> getAllKeysValues( DatabaseBean bean, Class<?> instanceClass ) throws IllegalArgumentException, IllegalAccessException {
        List<Object> values = new ArrayList<Object>();

        for (Field field : DBAnnotationsManager.getAllKeysMap(instanceClass).keySet()) {
            field.setAccessible(true);
            values.add(field.get(bean));
        }

        return values;
    }

    /**
     * Get the values of the field in the database bean in parameters
     * 
     * @param bean The bean to manage
     * @param fields The list of fields
     * @return The list of all values as object
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     */
    public static List<Object> getFieldValuesOfBean( DatabaseBean bean, Collection<Field> fields ) throws IllegalArgumentException, IllegalAccessException {
        List<Object> values = new ArrayList<Object>();

        for (Field field : fields) {
            field.setAccessible(true);
            values.add(field.get(bean));
        }

        return values;
    }
}
