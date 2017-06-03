package controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import beans.UserBean;
import beans.form.UserAdministrationBean;
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
            ControllerUtils.redirectTo("activities");
        }
        else {
            SessionUtil.clearSession();
            ControllerUtils.addMessage(FacesMessage.SEVERITY_WARN, "Connexion échouée");
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
        ControllerUtils.addMessage(FacesMessage.SEVERITY_INFO, "Inscription réalisée");
        ControllerUtils.redirectTo("login");
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
     * 
     * @return The list of Users
     */
    public List<UserAdministrationBean> getAllUsers() {
        UserDAO userDao = UserDAO.getInstance();
        List<UserBean> users = userDao.findAll();
        List<UserAdministrationBean> adminUsers = new ArrayList<UserAdministrationBean>();

        for (UserBean user : users) {
            adminUsers.add(user.getUserAdministrationBean());
        }

        return adminUsers;
    }

    /**
     * Delete User
     * 
     * @param userAdministrationBean The user to delete
     */
    public void deleteUser( UserAdministrationBean userAdministrationBean ) {
        UserDAO userDao = UserDAO.getInstance();
        userDao.delete(userAdministrationBean.getUserBean());
        ControllerUtils.addMessage(FacesMessage.SEVERITY_INFO, "Utilisateur supprimé");
    }

    /**
     * Edit an User
     * 
     * @param userAdministrationBean The user to edit
     */
    public void setUser( UserAdministrationBean userAdministrationBean ) {
        UserDAO userDao = UserDAO.getInstance();

        if (userDao.update(userAdministrationBean.getUserBean()) != null) {
            ControllerUtils.addMessage(FacesMessage.SEVERITY_INFO, "Utilisateur modifié");
        }
        else {
            ControllerUtils.addMessage(FacesMessage.SEVERITY_INFO, "Echec modification");
        }
    }
}
