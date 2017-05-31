package dao.impl;

import beans.UserBean;
import dao.generic.AbstractDAO;

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

}
