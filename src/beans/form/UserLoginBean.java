package beans.form;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import beans.UserBean;

/**
 * User bean for login form
 * 
 * @author Lucas Grégoire
 */
@ManagedBean( name = "userLoginBean" )
@RequestScoped
public class UserLoginBean implements Serializable {

    /** Serializable id */
    private static final long serialVersionUID = -2200856748515946645L;

    private String login;
    private String password;

    /**
     * @return the login
     */
    public String getLogin() {
        return login;
    }

    /**
     * @param login the login to set
     */
    public void setLogin( String login ) {
        this.login = login;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword( String password ) {
        this.password = password;
    }

    /**
     * @return The user bean relative to this UserLoginBean
     */
    public UserBean getUserBean() {
        UserBean bean = new UserBean();

        bean.setLogin(login);
        bean.setPassword(password);

        return bean;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "UserLoginBean [getUserBean()=" + getUserBean() + "]";
    }

}