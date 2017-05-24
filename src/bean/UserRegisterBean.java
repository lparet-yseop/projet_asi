package bean;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * User bean for register form
 * 
 * @author Lucas Grégoire
 */
@ManagedBean( name = "userRegisterBean" )
@SessionScoped
public class UserRegisterBean implements Serializable {

    /** Serializable */
    private static final long serialVersionUID = -460913437505442449L;
    
    @Size( min = 2, max = 50, message = "Min 2 and max 50 characters" )
    private String lastname;
    @Size( min = 2, max = 50, message = "Min 2 and max 50 characters" )
    private String firstname;
    @Pattern( regexp = "^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$", message = "This is not a valid email" )
    private String email;
    private String login;
    private String password;
    private String repeatpassword;

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
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail( String email ) {
        this.email = email;
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
     * @return the repeatpassword
     */
    public String getRepeatpassword() {
        return repeatpassword;
    }

    /**
     * @param repeatpassword the repeatpassword to set
     */
    public void setRepeatpassword( String repeatpassword ) {
        this.repeatpassword = repeatpassword;
    }

}