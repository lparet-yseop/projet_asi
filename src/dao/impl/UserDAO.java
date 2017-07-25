package dao.impl;

import java.util.HashMap;
import java.util.Map;

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

    /**
     * Gets the user by his login & password
     * 
     * @param login The login of the user
     * @param password The password of the user
     * @return The user found or null
     */
    public UserBean findByLoginPassword( String login, String password ) {
        Map<Integer, Object> map = new HashMap<Integer, Object>();
        map.put(1, login);
        map.put(2, password);

        return manager.findOneByCustomWhereClause("usr_login = ? AND usr_password = ? AND usr_admin = 1", map);
    }

    /**
     * Gets the user by his email
     * 
     * @param email The email of the user
     * @return The user found or null
     */
    public UserBean findByMail( String email ) {
        Map<Integer, Object> map = new HashMap<Integer, Object>();
        map.put(1, email);

        return manager.findOneByCustomWhereClause("usr_email = ?", map);
    }

}
