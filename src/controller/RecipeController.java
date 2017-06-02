package controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;

import beans.CookTypeBean;
import beans.RecipeBean;
import dao.impl.RecipeDAO;

@ManagedBean
// @ViewScoped
public class RecipeController {

    // public boolean addReceipt(ReceipeBean receipe) {
    // ReceipeDao receipeDao = ReceipeDao.getInstance();
    //
    // boolean rs = receipeDao.add(receipe.getName(), receipe.getDetails(),
    // receipe.getResume(), receipe.getNbPersons(),
    // receipe.getComplexity(), receipe.getType(), receipe.getImage());
    //
    // return rs;
    // }
    //
    // public boolean editReceipe(ReceipeBean receipe) {
    // ReceipeDao receipeDao = ReceipeDao.getInstance();
    //
    // boolean rs = receipeDao.edit(receipe.getId(), receipe.getName(),
    // receipe.getDetails(), receipe.getResume(),
    // receipe.getNbPersons(), receipe.getComplexity(),
    // receipe.getType(), receipe.getImage());
    //
    // return rs;
    // }

    public int cookType;
    public int duration;
    public int nbPeople;
    public List<RecipeBean> recipes;
    public RecipeBean selectedRecipe;

    public RecipeController() {
        super();
        recipes = new ArrayList<RecipeBean>();
    }

    public List<RecipeBean> getAllReceipes() {
        RecipeDAO receipeDao = RecipeDAO.getInstance();

        List<RecipeBean> list = receipeDao.findAll();

        return list;
    }

    public String goToDetail( RecipeBean receipeBean ) {
        this.selectedRecipe = receipeBean;
        return "detailReceip";
    }

    public RecipeBean getReceipe( int receipeId ) {
        RecipeDAO receipeDao = RecipeDAO.getInstance();

        RecipeBean receipe = receipeDao.findOneById(receipeId);

        return receipe;
    }

    public String getReceipesByCriteria() {
        RecipeDAO receipeDao = RecipeDAO.getInstance();

        RecipeBean receipeBean = new RecipeBean();
        if (duration > 0)
            receipeBean.setDuration(duration);
        CookTypeBean cookTypeBean = new CookTypeBean();
        cookTypeBean.setId(cookType);
        if (cookType > 0)
            receipeBean.setCookTypeBean(cookTypeBean);
        if (nbPeople > 0)
            receipeBean.setNbPeople(nbPeople);
        List<RecipeBean> list = receipeDao.findByCriteria(receipeBean);
        recipes = list;
        // return list;
        return "resultsRecipes";
    }

    public int getCookType() {
        return cookType;
    }

    public void setCookType( int cookType ) {
        this.cookType = cookType;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration( int duration ) {
        this.duration = duration;
    }

    public int getNbPeople() {
        return nbPeople;
    }

    public void setNbPeople( int nbPeople ) {
        this.nbPeople = nbPeople;
    }

    public List<RecipeBean> getReceipes() {
        return recipes;
    }

    public void setReceipes( List<RecipeBean> receipes ) {
        this.recipes = receipes;
    }

    public RecipeBean getSelectedReceipe() {
        return selectedRecipe;
    }

    public void setSelectedReceipe( RecipeBean selectedReceipe ) {
        this.selectedRecipe = selectedReceipe;
    }

    // public static void main(String[] args) {
    //
    // ReceipeControler rc = new ReceipeControler();
    // System.out.println(rc.getAllReceipes());
    //
    // ReceipeBean bean = new ReceipeBean("Crepes",
    // "Farine + oeuf + lait + biere", "Delicieuse crepes", 4, 1,
    // "Dessert", "crepes.png");
    // boolean addReceipt = rc.addReceipt(bean);
    // System.out.println("Add receipe: " + addReceipt);
    //
    // bean.setNbPersons(1);
    // bean.setId(1);
    // boolean editUser = rc.editReceipe(bean);
    // System.out.println("User edit: " + editUser);
    //
    // bean = rc.getReceipe(1);
    // System.out.println("User edited:" + bean);
    // System.out.println(rc.getAllReceipes());
    //
    // }
}
