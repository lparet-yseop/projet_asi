package dao.generic;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import beans.utils.DatabaseBean;
import dao.annotation.DBAnnotationsManager;
import dao.annotation.DBJoin;

/**
 * Utility which map DatabaseBean from Results from the database
 * 
 * @author Lucas Grégoire
 * @param <T> The type parameter
 */
public class DatabaseObjectMapper<T extends DatabaseBean> {

    /**
     * Map all the result set to objects
     * 
     * @param resultSet The result set to parse
     * @param destinationClass The destination bean class
     * @return The list of objects mapped
     * @throws Exception throwed for illegal access, sql exception, instanciations errors
     */
    public List<T> mapAll( ResultSet resultSet, Class<T> destinationClass ) throws Exception {
        List<T> beans = new ArrayList<T>();

        while (resultSet.next()) {
            beans.add(mapDirectRecursive(resultSet, destinationClass));
        }

        return beans;
    }

    /**
     * Map one result set to one object bean
     * 
     * @param resultSet The result set to parse
     * @param destinationClass The destination bean class
     * @return The object mapped
     * @throws Exception throwed for illegal access, sql exception, instanciations errors
     */
    public T mapOne( ResultSet resultSet, Class<T> destinationClass ) throws Exception {
        try {
            List<T> beans = mapAll(resultSet, destinationClass);
            return beans.get(0);
        }
        catch (IndexOutOfBoundsException e) {
            return null;
        }
    }

    @SuppressWarnings( "unchecked" )
    private T mapDirectRecursive( ResultSet resultSet, Class<? extends DatabaseBean> destinationClass ) throws Exception {
        T generatedBean = (T) destinationClass.newInstance();

        // Map fields
        for (Entry<Field, String> field : DBAnnotationsManager.getAllKeysMap(destinationClass).entrySet()) {
            field.getKey().setAccessible(true);
            field.getKey().set(generatedBean, resultSet.getObject(field.getValue()));
        }

        // Map sub-beans
        for (Entry<Field, DBJoin> subField : DBAnnotationsManager.getDBJoins(destinationClass).entrySet()) {
            subField.getKey().setAccessible(true);
            subField.getKey().set(generatedBean, mapDirectRecursive(resultSet, subField.getValue().joinClass()));
        }

        return generatedBean;
    }
}
