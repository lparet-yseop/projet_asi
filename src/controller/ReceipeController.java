package controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import beans.CookTypeBean;
import beans.ReceipeBean;
import dao.ReceipeDao;

@ManagedBean
@SessionScoped
public class ReceipeController {

//	public boolean addReceipt(ReceipeBean receipe) {
//		ReceipeDao receipeDao = ReceipeDao.getInstance();
//
//		boolean rs = receipeDao.add(receipe.getName(), receipe.getDetails(),
//				receipe.getResume(), receipe.getNbPersons(),
//				receipe.getComplexity(), receipe.getType(), receipe.getImage());
//
//		return rs;
//	}
//
//	public boolean editReceipe(ReceipeBean receipe) {
//		ReceipeDao receipeDao = ReceipeDao.getInstance();
//
//		boolean rs = receipeDao.edit(receipe.getId(), receipe.getName(),
//				receipe.getDetails(), receipe.getResume(),
//				receipe.getNbPersons(), receipe.getComplexity(),
//				receipe.getType(), receipe.getImage());
//
//		return rs;
//	}
	
	public int cookType;
	public int duration;
	public int nbPeople;
	public List<ReceipeBean> receipes  = new ArrayList<ReceipeBean>();
	public ReceipeBean selectedReceipe; 
	


	public ReceipeController() {
		super();
		//receipes = new ArrayList<ReceipeBean>();
	}
	


	public List<ReceipeBean> getAllReceipes() {
		ReceipeDao receipeDao = ReceipeDao.getInstance();

		List<ReceipeBean> list = receipeDao.getAll();

		return list;
	}

	
	public String goToDetail(ReceipeBean receipeBean){
		this.selectedReceipe = receipeBean;
		System.out.println("toto");
		return "detailReceip";
	}
	
	
	
	public String goToDetail(){
		this.selectedReceipe = this.receipes.get(0);
		System.out.println("toto");
		return "detailReceip";
	}

	public ReceipeBean getReceipe(int receipeId) {
		ReceipeDao receipeDao = ReceipeDao.getInstance();

		ReceipeBean receipe = receipeDao.getReceipe(receipeId);

		return receipe;
	}


	public String getReceipesByCriteria(){
		ReceipeDao receipeDao = ReceipeDao.getInstance();

		ReceipeBean receipeBean = new ReceipeBean();
		if(duration > 0) receipeBean.setDuration(duration);
		CookTypeBean cookTypeBean = new CookTypeBean();
		cookTypeBean.setId(cookType);
		if(cookType > 0 ) receipeBean.setCookTypeBean(cookTypeBean);
		if(nbPeople > 0 ) receipeBean.setNbPeople(nbPeople);
		List<ReceipeBean> list = receipeDao.findByCriteria(receipeBean);
		receipes = list;
		selectedReceipe = list.get(1);
		//return list;
		return "resultsRecipes";
	}

	public int getCookType() {
		return cookType;
	}

	public void setCookType(int cookType) {
		this.cookType = cookType;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public int getNbPeople() {
		return nbPeople;
	}

	public void setNbPeople(int nbPeople) {
		this.nbPeople = nbPeople;
	}

	public List<ReceipeBean> getReceipes() {
		return receipes;
	}

	public void setReceipes(List<ReceipeBean> receipes) {
		this.receipes = receipes;
	}

	public ReceipeBean getSelectedReceipe() {
		return selectedReceipe;
	}

	public void setSelectedReceipe(ReceipeBean selectedReceipe) {
		this.selectedReceipe = selectedReceipe;
	}
	
	/**
     * Delete User
     */
    public void deleteReceipe(ReceipeBean receipeBean) {
    	addMessage(FacesMessage.SEVERITY_INFO, "Non implémenté");
    }
    
    /**
     * Create a notification message
     * 
     * @param severity The severity of the message
     * @param text The text
     */
    public void addMessage( Severity severity, String text ) {
        FacesMessage message = new FacesMessage(severity, text, null);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
}
