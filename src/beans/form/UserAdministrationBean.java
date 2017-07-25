package beans.form;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import beans.UserBean;

/**
 * User bean for user administration forms
 * 
 * @author Lucas Grégoire
 */
@ManagedBean( name = "userAdministrationBean" )
@RequestScoped
public class UserAdministrationBean implements Serializable {

    /** Serializable id */
    private static final long serialVersionUID = 1542930181973365336L;

    private int id;
    private String firstname;
    private String lastname;
    private String login;
    private String password;
    private Boolean admin;

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId( int id ) {
        this.id = id;
    }

    /**
     * @return the firstname
     */
    public String getFirstname() {
        return firstname;
    }

    /**
     * @param firstname the firstname to set
     */
    public void setFirstname( String firstname ) {
        this.firstname = firstname;
    }

    /**
     * @return the lastname
     */
    public String getLastname() {
        return lastname;
    }

    /**
     * @param lastname the lastname to set
     */
    public void setLastname( String lastname ) {
        this.lastname = lastname;
    }

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
     * @return the admin
     */
    public Boolean getAdmin() {
        return admin;
    }

    /**
     * @param admin the admin to set
     */
    public void setAdmin( Boolean admin ) {
        this.admin = admin;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "UserAdministrationBean [id=" + id + ", firstname=" + firstname + ", lastname=" + lastname + ", login=" + login + ", password=" + password + ", admin=" + admin
                + "]";
    }

    /**
     * @return The user bean relative to this UserAdministrationBean
     */
    public UserBean getUserBean() {
        UserBean bean = new UserBean();

        bean.setId(id);
        bean.setFirstname(firstname);
        bean.setLastname(lastname);
        bean.setLogin(login);
        bean.setPassword(password);
        bean.setAdmin(admin);

        return bean;
    }

}
