package dao.impl;

import java.util.Arrays;

import beans.UserBean;
import dao.annotation.DbAnnotationsManager;
import dao.generic.AbstractDAO;
import dao.utils.UnknownTableNameException;

/**
 * DAO for UserBean
 * 
 * @author Lucas Grégoire
 */
public class UserDAO extends AbstractDAO<UserBean> {

    private static UserDAO instance;

    /**
     * @return The instance of UserDAO
     */
    public static UserDAO getInstance() {
        if (instance == null)
            instance = new UserDAO();
        return instance;
    }

    /**
     * Constructor
     */
    public UserDAO() {
        super(UserBean.class);
    }

    /**
     * Gets the user by his login
     * 
     * @param userBean The userBean
     * @return The user found or null
     */
    public UserBean getUserAdminByLogin( UserBean userBean ) {
        try {
            StringBuilder request = new StringBuilder("SELECT * FROM " + DbAnnotationsManager.getTableName(userBean.getClass()));
            request.append(" WHERE usr_login = ? AND usr_password = ? AND usr_admin = 1");
            return manager.executeSelect(UserBean.class, request.toString(), Arrays.asList(userBean.getLogin(), userBean.getPassword()));
        }
        catch (UnknownTableNameException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public UserBean getByMail( UserBean userBean ) {
        try {
            StringBuilder request = new StringBuilder("SELECT * FROM " + DbAnnotationsManager.getTableName(userBean.getClass()));
            request.append(" WHERE usr_email = ?");
            return manager.executeSelect(UserBean.class, request.toString(), Arrays.asList(userBean.getEmail()));
        }
        catch (UnknownTableNameException e) {
            e.printStackTrace();
            return null;
        }
    }

}
