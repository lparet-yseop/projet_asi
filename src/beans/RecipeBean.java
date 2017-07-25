package beans;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;

import beans.form.RecipeAdministrationBean;
import beans.utils.DatabaseBean;
import dao.annotation.DBColumn;
import dao.annotation.DBJoin;
import dao.annotation.DBTable;

/**
 * Cook type bean
 * 
 * @author Hugo Blanc, Lucas Grégoire
 */
@ManagedBean( name = "recipeBean" )
@DBTable( "T_E_RECIPE_REC" )
public class RecipeBean extends DatabaseBean implements Serializable {

    /** Serializable id */
    private static final long serialVersionUID = -1002438860544059170L;

    @DBColumn( value = "rec_id", primaryKey = true )
    private int id;
    @DBColumn( "rec_duration" )
    private int duration;
    @DBColumn( "rec_difficulty" )
    private int difficulty;
    @DBColumn( "rec_nb_people" )
    private int nbPeople;
    @DBColumn( "rec_title" )
    private String title;
    @DBColumn( "rec_description" )
    private String description;
    @DBColumn( "rec_path_photo" )
    private String pathPhoto;
    @DBJoin( joinClass = CookTypeBean.class, srcColumn = "cot_id", destColumn = "cot_id" )
    private CookTypeBean cookTypeBean;

    /**
     * Constructs a new RecipeBean
     */
    public RecipeBean() {
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

    /**
     * @return the pathPhoto
     */
    public String getPathPhoto() {
        return pathPhoto;
    }

    /**
     * @param pathPhoto the pathPhoto to set
     */
    public void setPathPhoto( String pathPhoto ) {
        this.pathPhoto = pathPhoto;
    }

    /**
     * @return the cookTypeBean
     */
    public CookTypeBean getCookTypeBean() {
        return cookTypeBean;
    }

    /**
     * @param cookTypeBean the cookTypeBean to set
     */
    public void setCookTypeBean( CookTypeBean cookTypeBean ) {
        this.cookTypeBean = cookTypeBean;
    }

    /**
     * @return The UserAdministrationBean relative to this UserBean
     */
    public RecipeAdministrationBean getRecipeAdministrationBean() {
        RecipeAdministrationBean adminRecipe = new RecipeAdministrationBean();

        adminRecipe.setId(id);
        adminRecipe.setDuration(duration);
        adminRecipe.setDifficulty(difficulty);
        adminRecipe.setTitle(title);
        adminRecipe.setDescription(description);

        return adminRecipe;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "RecipeBean [id=" + id + ", duration=" + duration + ", difficulty=" + difficulty + ", nbPeople=" + nbPeople + ", title=" + title + ", description=" + description
                + ", pathPhoto=" + pathPhoto + ", cookTypeBean=" + cookTypeBean + "]";
    }

}
