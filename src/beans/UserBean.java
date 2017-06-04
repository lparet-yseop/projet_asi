package beans;

import java.io.Serializable;
import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import beans.form.UserAdministrationBean;
import beans.utils.DatabaseBean;
import dao.annotation.DBColumn;
import dao.annotation.DBTable;

/**
 * User bean
 * 
 * @author Louis Paret, Lucas Grégoire
 */
@ManagedBean( name = "userBean" )
@SessionScoped
@DBTable( "T_E_USER_USR" )
public class UserBean extends DatabaseBean implements Serializable {

    /** Serializable id */
    private static final long serialVersionUID = -2920686139621697269L;

    @DBColumn( value = "usr_id", primaryKey = true )
    private int id;
    @DBColumn( "usr_login" )
    private String login;
    @DBColumn( "usr_password" )
    private String password;
    @DBColumn( "usr_firstname" )
    private String firstname;
    @DBColumn( "usr_lastname" )
    private String lastname;
    @DBColumn( "usr_date_birth" )
    private Date datebirthday;
    @DBColumn( "usr_email" )
	private String email;
    @DBColumn( "usr_admin" )
    private Boolean admin;
    @DBColumn("usr_path_photo")
    private String pathPhoto;
    
    /**
     * Constructs a new UserBean
     */
    public UserBean() {
    }

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
     * @return the datebirthday
     */
    public Date getDatebirthday() {
        return datebirthday;
    }

    /**
     * @param datebirthday the datebirthday to set
     */
    public void setDatebirthday( Date datebirthday ) {
        this.datebirthday = datebirthday;
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

    
    
    /**
     * 
     * @return pathphto
     */
    public String getPathPhoto() {
		return pathPhoto;
	}

	public void setPathPhoto(String pathPhoto) {
		this.pathPhoto = pathPhoto;
	}

	@Override
	public String toString() {
		return "UserBean [id=" + id + ", login=" + login + ", password=" + password + ", firstname=" + firstname
				+ ", lastname=" + lastname + ", datebirthday=" + datebirthday + ", email=" + email + ", admin=" + admin
				+ ", pathPhoto=" + pathPhoto + "]";
	}

    /**
     * @return The UserAdministrationBean relative to this UserBean
     */
    public UserAdministrationBean getUserAdministrationBean() {
        UserAdministrationBean adminUser = new UserAdministrationBean();

        adminUser.setId(id);
        adminUser.setFirstname(firstname);
        adminUser.setLastname(lastname);
        adminUser.setLogin(login);
        adminUser.setPassword(password);
        adminUser.setAdmin(admin);

        return adminUser;
    }

}
