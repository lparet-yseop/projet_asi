package controller;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import beans.UserBean;
import beans.UserRegisterBean;
import beans.Util;
import dao.UserDao;

/**
 * Controller for users management
 * 
 * @author Lucas Grégoire
 */
@ManagedBean( name = "userController" )
@ViewScoped
public class UserController implements Serializable {

	private static final long serialVersionUID = 1L;

	// Get all Users on Database
	public List<UserBean> getAllUsers() {
		UserDao userDao = UserDao.getInstance();

		List<UserBean> list = userDao.getAll();

		return list;
	}

	// Get User by id
	public UserBean getUser(int userId) {
		UserDao userDao = UserDao.getInstance();

		UserBean user = userDao.getUser(userId);

		return user;
	}

	// Get user by login, used for the connexion
	public void getUserByLogin(String login, String password) {
		UserDao userDao = UserDao.getInstance();

		UserBean user = userDao.getUserByLogin(login, password);

		if(user != null) {
			redirectTo("activities");
		} else {
			logout();
			addMessage(FacesMessage.SEVERITY_WARN, "Connexion échouée");
		}
	}
    
	// Create a notification message
    public void addMessage(Severity severity, String texte) {
        FacesMessage message = new FacesMessage(severity, texte,  null);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    // method to redirect to other page
    public void redirectTo(String page) {
    	ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
    	context.getFlash().setKeepMessages(true);
        try {
			context.redirect(context.getRequestContextPath() + "/jsf/" + page + ".jsf");
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    // Logout
    public void logout() {
        Util.getSession().invalidate();
    }
    
    // Register : add user
	public void register(UserRegisterBean user) {
		UserDao userDao = UserDao.getInstance();
		Boolean res = userDao.add(user.getFirstname(), user.getLastname(), user.getBirthdate(), user.getLogin(),
				user.getEmail(), user.getPassword());
		if(res) {
			addMessage(FacesMessage.SEVERITY_INFO, "Inscription réalisé");
			redirectTo("login");
		} else {
			addMessage(FacesMessage.SEVERITY_WARN, "Echec de l'inscription");
		}
	}
}
