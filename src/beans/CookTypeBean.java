package beans;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;

import beans.utils.DatabaseBean;
import dao.annotation.DBColumn;
import dao.annotation.DBTable;

/**
 * Cook type bean
 * 
 * @author Louis Paret, Lucas Grégoire
 */
@ManagedBean( name = "cookTypeBean" )
@DBTable( "T_E_COOK_TYPE_COT" )
public class CookTypeBean extends DatabaseBean implements Serializable {

    /** Serializable id */
    private static final long serialVersionUID = -5388685879696441316L;

    @DBColumn( value = "cot_id", primaryKey = true )
    private int id;
    @DBColumn( "cot_text" )
    private String text;

    /**
     * Constructs a new CookTypeBean
     */
    public CookTypeBean() {
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId( int id ) {
        this.id = id;
    }

    /**
     * @return the text
     */
    public String getText() {
        return text;
    }

    /**
     * @param text the text to set
     */
    public void setText( String text ) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "CookType [id=" + id + ", text=" + text + "]";
    }

}
