package controller;

import java.io.IOException;
import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import beans.UserBean;
import beans.UserCounter;
import beans.UserLoginBean;
import beans.UserRegisterBean;
import beans.Util;
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
        UserBean bean = userDao.getUserAdminByLogin(loginBean.getUserBean());

        if (bean != null) {
            HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
            session.setAttribute("userBean", bean);
            userCounter.addUserConnected();

            redirectTo("activities");
        }
        else {
            logout();
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

        if (userDao.insert(registerBean.getUserBean()) != null) {
            addMessage(FacesMessage.SEVERITY_INFO, "Inscription réalisée");
            redirectTo("login");
        }
        else {
            addMessage(FacesMessage.SEVERITY_WARN, "Echec de l'inscription");
        }
    }

    /**
     * Logout the user
     */
    public void logout() {
        Util.getSession().invalidate();
        userCounter.deleteUserConnected();
    }
}
