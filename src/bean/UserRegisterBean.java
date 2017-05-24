package bean;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * User bean for register form
 * 
 * @author Lucas Grégoire
 */
@ManagedBean( name = "userRegisterBean" )
@RequestScoped
public class UserRegisterBean implements Serializable {

    /** Serializable */
    private static final long serialVersionUID = -460913437505442449L;

    @Size( min = 2, max = 50, message = "Min 2 and max 50 characters" )
    @Pattern( regexp = "^[_A-Za-z0-9-@]+", message = "This is not a valid first name" )
    @NotNull( message = "The name cannot be empty" )
    private String firstname;

    @Size( min = 2, max = 50, message = "Min 2 and max 50 characters" )
    @Pattern( regexp = "^[_A-Za-z0-9-@]+", message = "This is not a valid last name" )
    @NotNull( message = "The name cannot be empty" )
    private String lastname;

    @Digits( integer = 4, fraction = 0, message = "The value is not valid" )
    @Min( value = 1990, message = "The value is too small" )
    @NotNull( message = "The birth year cannot be empty" )
    private Integer birthyear;

    @Pattern( regexp = "^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$", message = "This is not a valid email" )
    @NotNull( message = "The name cannot be empty" )
    private String email;

    @Size( min = 2, max = 50, message = "Min 2 and max 50 characters" )
    @Pattern( regexp = "^[_A-Za-z0-9-@]+", message = "This is not a valid login" )
    @NotNull( message = "The login cannot be empty" )
    private String login;

    @Size( min = 6, max = 50, message = "Min 6 and max 50 characters" )
    @Pattern( regexp = "^[_A-Za-z0-9-@]+", message = "This is not a password" )
    private String password;

    private String repeatpassword;

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
     * @return the birthyear
     */
    public Integer getBirthyear() {
        return birthyear;
    }

    /**
     * @param birthyear the birthyear to set
     */
    public void setBirthyear( Integer birthyear ) {
        this.birthyear = birthyear;
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