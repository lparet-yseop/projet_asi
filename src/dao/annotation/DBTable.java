package dao.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation for database bean tables
 * 
 * @author Lucas Grégoire
 */
@Target( ElementType.TYPE )
@Retention( RetentionPolicy.RUNTIME )
public @interface DBTable {

    /**
     * Represents the name of the table in the database
     * 
     * @return The name of the table
     */
    public String value();
}
