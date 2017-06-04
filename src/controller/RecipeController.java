package controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import beans.CommentBean;
import beans.CookTypeBean;
import beans.RecipeBean;
import beans.UserBean;
import beans.form.RecipeAdministrationBean;
import beans.form.RecipeSearchBean;
import dao.CommentDao;
import dao.impl.RecipeDAO;

/**
 * Controller for recipes management
 * 
 * @author Hugo Blanc, Lucas Grégoire
 */
@ManagedBean( name = "recipeController" )
@SessionScoped
public class RecipeController implements Serializable {

    /** Serializable id */
    private static final long serialVersionUID = 5049629637751803488L;

    private List<RecipeBean> results;
    private RecipeBean selectedRecipe;
	public List<CommentBean> commentBeans; 

    /**
     * Constructor
     */
    public RecipeController() {
        super();
        results = new ArrayList<RecipeBean>();
    }

    /**
     * Goes to detail page
     * 
     * @param recipeBean The selected recipe
     * @return The page of details
     */
    public String goToDetail( RecipeBean recipeBean ) {
        this.selectedRecipe = recipeBean;
        commentBeans = getCommentByRecipe(); 
        // ControllerUtils.addMessage(FacesMessage.SEVERITY_INFO, "Welcome to Primefaces!!");

        return "detailRecipe";
    }
    
    /**
     * Back to results page
     * 
     * @return The results page
     */
    public String goToResults() {
        this.selectedRecipe = null;

        return "resultsRecipes";
    }

//    public RecipeBean getReceipe( int receipeId ) {
//        RecipeDAO receipeDao = RecipeDAO.getInstance();
//
//        RecipeBean receipe = receipeDao.findOneById(receipeId);
//
//        return receipe;
//    }

    /**
     * Launch the search using the RecipeSearchBean
     * 
     * @param search The search criteria bean
     * @return The result page
     */
    public String getRecipesByCriteria( RecipeSearchBean search ) {
        RecipeDAO receipeDao = RecipeDAO.getInstance();
        RecipeBean receipeBean = new RecipeBean();

        if (search.getDuration() > 0)
            receipeBean.setDuration(search.getDuration());

        CookTypeBean cookTypeBean = new CookTypeBean();
        cookTypeBean.setId(search.getCookType());
        if (search.getCookType() > 0)
            receipeBean.setCookTypeBean(cookTypeBean);
        if (search.getNbPeople() > 0)
            receipeBean.setNbPeople(search.getNbPeople());

        results = receipeDao.findByCriteria(receipeBean);

        return "resultsRecipes";
    }

    
    /**
     * Get All Recipes
     * 
     * @return The list of Recipes
     */
    public List<RecipeAdministrationBean> getAllRecipes() {
        RecipeDAO recipeDAO = RecipeDAO.getInstance();
        List<RecipeBean> recipes = recipeDAO.findAll();
        List<RecipeAdministrationBean> adminRecipes = new ArrayList<RecipeAdministrationBean>();

        for (RecipeBean recipe : recipes) {
            adminRecipes.add(recipe.getRecipeAdministrationBean());
        }

        return adminRecipes;
    }

    /**
     * Delete Recipe
     * 
     * @param recipeAdministrationBean The recipe to delete
     */
    public void deleteRecipe( RecipeAdministrationBean recipeAdministrationBean ) {
        RecipeDAO recipeDAO = RecipeDAO.getInstance();
        recipeDAO.delete(recipeAdministrationBean.getRecipeBean());
        // addMessage(FacesMessage.SEVERITY_INFO, "Utilisateur supprimé");
    }
    
    
    
	public List<CommentBean> getCommentByRecipe(){
		CommentDao commentDao = CommentDao.getInstance();
		return commentDao.findByRecipe(this.selectedRecipe);
	}
    
    public String postComment(String text, int id){
    	CommentDao commentDao =  CommentDao.getInstance();
    	UserBean userBean = new UserBean();
    	userBean.setId(id);
    	commentDao.add(text, new Date(), 0, selectedRecipe, userBean);
    	return goToResults();
    }

    /**
     * Edit an Recipe
     * 
     * @param recipeAdministrationBean The recipe to edit
     */
    public void setRecipe( RecipeAdministrationBean recipeAdministrationBean ) {
        RecipeDAO recipeDao = RecipeDAO.getInstance();

        if (recipeDao.update(recipeAdministrationBean.getRecipeBean()) != null) {
            // addMessage(FacesMessage.SEVERITY_INFO, "Utilisateur modifié");
        }
        else {
            // addMessage(FacesMessage.SEVERITY_INFO, "Echec modification");
        }
    }

    /**
     * @return the results
     */
    public List<RecipeBean> getResults() {
        return results;
    }

    /**
     * @param results the results to set
     */
    public void setResults( List<RecipeBean> results ) {
        this.results = results;
    }

    /**
     * @return the selectedRecipe
     */
    public RecipeBean getSelectedRecipe() {
        return selectedRecipe;
    }

    /**
     * @param selectedRecipe the selectedRecipe to set
     */
    public void setSelectedRecipe( RecipeBean selectedRecipe ) {
        this.selectedRecipe = selectedRecipe;
    }

    
	public List<CommentBean> getCommentBeans() {
		return commentBeans;
	}

	public void setCommentBeans(List<CommentBean> commentBeans) {
		this.commentBeans = commentBeans;
	}

    
    
    
    
}
