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
import beans.form.UserLoginBean;
import beans.form.UserRegisterBean;
import beans.utils.SessionUtil;
import beans.utils.UserCounter;
import dao.impl.UserDAO;

/**
 * Controller for users management
 * 
 * @author Louis Paret, Lucas Grégoire
 */
@ManagedBean( name = "userController" )
@ViewScoped
public class UserController implements Serializable {

    /** Serializable id */
    private static final long serialVersionUID = -4707280395188249270L;
    private UserCounter userCounter = new UserCounter();

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

    /**
     * method to redirect to other page
     * 
     * @param page The page to redirect to
     */
    public void redirectTo( String page ) {
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        context.getFlash().setKeepMessages(true);

        try {
            context.redirect(context.getRequestContextPath() + "/jsf/" + page + ".jsf");
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Get user by login, used for the connection
     * 
     * @param loginBean The UserLoginBean to login
     */
    public void login( UserLoginBean loginBean ) {
        UserDAO userDao = UserDAO.getInstance();
        UserBean bean = userDao.findByLoginPassword(loginBean.getLogin(), loginBean.getPassword());
System.out.println(bean);
        if (bean != null) {
            SessionUtil.setUserBean(bean);
            userCounter.addUserConnected();
            redirectTo("activities");
        }
        else {
        	SessionUtil.clearSession();
            addMessage(FacesMessage.SEVERITY_WARN, "Connexion échouée");
        }
    }

    /**
     * Register an user
     * 
     * @param registerBean The UserRegisterBean to register
     */
    public void register( UserRegisterBean registerBean ) {
        UserDAO userDao = UserDAO.getInstance();
        userDao.insert(registerBean.getUserBean());
        addMessage(FacesMessage.SEVERITY_INFO, "Inscription réalisée");
        redirectTo("login");      
    }

    /**
     * Logout the user
     */
    public void logout() {
        SessionUtil.clearSession();
        userCounter.deleteUserConnected();
    }
    
    /**
     * Get All Users
     */
    public List<UserBean> getAllUsers() {
    	UserDAO userDao = UserDAO.getInstance();
    	return userDao.findAll();
    }
    
    /**
     * Delete User
     */
    public void deleteUser(UserBean userBean) {
    	UserDAO userDao = UserDAO.getInstance();
    	userDao.delete(userBean);
    	addMessage(FacesMessage.SEVERITY_INFO, "Utilisateur supprimé");
    }
    
    /**
     * Delete User
     */
    public void setUser(UserRegisterBean userRegisterBean) {
    	UserDAO userDao = UserDAO.getInstance();
    	UserBean userBean = userDao.getByMail(userRegisterBean.getUserBean());
    	userBean.setLogin(userRegisterBean.getLogin());
    	userBean.setFirstname(userRegisterBean.getFirstname());
    	userBean.setLastname(userRegisterBean.getLastname());
    	userBean.setPassword(userRegisterBean.getPassword());
    	UserBean save = userDao.save(userBean);
    	if (save != null) {
    		addMessage(FacesMessage.SEVERITY_INFO, "Utilisateur modifié");
    	} else {
    		addMessage(FacesMessage.SEVERITY_INFO, "Echec modification");
    	}
    }    
}
