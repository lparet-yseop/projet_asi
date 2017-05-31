package dao.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation for database bean columns
 * 
 * @author Lucas Grégoire
 */
@Target( ElementType.FIELD )
@Retention( RetentionPolicy.RUNTIME )
public @interface DBColumn {

    /**
     * Represents the name of the column in the database table
     * 
     * @return The name of the column
     */
    public String value();

    /**
     * Tells if the column is a primary key or not
     * 
     * @return <b>true</b> if the column is a key, <b>false</b> else
     */
    public boolean primaryKey() default false;
}
