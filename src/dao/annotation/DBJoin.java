package dao.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import beans.utils.DatabaseBean;

/**
 * Annotation for database join bean
 * 
 * @author Lucas Grégoire
 */
@Target( ElementType.FIELD )
@Retention( RetentionPolicy.RUNTIME )
public @interface DBJoin {

    /**
     * Represents the class of the joined bean
     * 
     * @return The class of the bean
     */
    public Class<? extends DatabaseBean> joinClass();

    /**
     * Represents the column of the source table used for the join
     * 
     * @return The name of the column
     */
    public String srcColumn();

    /**
     * Represents the column of the joined table used for the join
     * 
     * @return The name of the column
     */
    public String destColumn();

}
