package controller;

import javax.servlet.http.HttpServletRequest;

import beans.UserBean;
import dao.UserDao;

public class InscriptionForm {

	private UserDao      userDao;

	public InscriptionForm( UserDao userDao ) {
	    this.userDao = userDao;
	}
	
	public UserBean inscrireUtilisateur(HttpServletRequest request) {
	    UserBean utilisateur = new UserBean();
    	userDao.creer(utilisateur);
	    return utilisateur;
	}
}
