package beans.form;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 * Recipe bean for recipe search forms
 * 
 * @author Hugo Blanc, Lucas Grégoire
 */
@ManagedBean( name = "recipeSearchBean" )
@RequestScoped
public class RecipeSearchBean implements Serializable {

    /** Serializable id */
    private static final long serialVersionUID = 7706300331129612288L;

    private int cookType;
    private int duration;
    private int nbPeople;

    /**
     * @return the cookType
     */
    public int getCookType() {
        return cookType;
    }

    /**
     * @param cookType the cookType to set
     */
    public void setCookType( int cookType ) {
        this.cookType = cookType;
    }

    /**
     * @return the duration
     */
    public int getDuration() {
        return duration;
    }

    /**
     * @param duration the duration to set
     */
    public void setDuration( int duration ) {
        this.duration = duration;
    }

    /**
     * @return the nbPeople
     */
    public int getNbPeople() {
        return nbPeople;
    }

    /**
     * @param nbPeople the nbPeople to set
     */
    public void setNbPeople( int nbPeople ) {
        this.nbPeople = nbPeople;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "RecipeSearchBean [cookType=" + cookType + ", duration=" + duration + ", nbPeople=" + nbPeople + "]";
    }

}
