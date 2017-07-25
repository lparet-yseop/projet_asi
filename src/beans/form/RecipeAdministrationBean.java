package beans.form;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import beans.RecipeBean;

/**
 * Recipe bean for recipe administration forms
 * 
 * @author Lucas Grégoire
 */
@ManagedBean( name = "recipeAdministrationBean" )
@RequestScoped
public class RecipeAdministrationBean implements Serializable {

    /** Serializable id */
    private static final long serialVersionUID = 6700278922392828844L;

    private int id;
    private int duration;
    private int difficulty;
    private String title;
    private String description;

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
     * @return the difficulty
     */
    public int getDifficulty() {
        return difficulty;
    }

    /**
     * @param difficulty the difficulty to set
     */
    public void setDifficulty( int difficulty ) {
        this.difficulty = difficulty;
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle( String title ) {
        this.title = title;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription( String description ) {
        this.description = description;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "RecipeBean [id=" + id + ", duration=" + duration + ", difficulty=" + difficulty + ", title=" + title + ", description=" + description + "]";
    }

    /**
     * @return The recipe bean relative to this RecipeAdministrationBean
     */
    public RecipeBean getRecipeBean() {
        RecipeBean bean = new RecipeBean();

        bean.setId(id);
        bean.setDuration(duration);
        bean.setDifficulty(difficulty);
        bean.setTitle(title);
        bean.setDescription(description);

        return bean;
    }

}
