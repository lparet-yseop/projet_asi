package beans;

import java.io.Serializable;
import java.sql.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class UserBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private int id;
	private String login;
	private String password;
	private String firstname;
	private String lastname;
	private Date datebirthday;
	private String email;
	private Boolean admin;

	public UserBean() {
	}

	public UserBean(String login, String password, String firstname, String lastname, Date datebirthday, String email) {
		super();
		this.login = login;
		this.password = password;
		this.firstname = firstname;
		this.lastname = lastname;
		this.datebirthday = datebirthday;
		this.email = email;
	}

	public UserBean(int id, String login, String password, String firstname, String lastname, Date datebirthday, String email,
			Boolean admin) {
		super();
		this.id = id;
		this.login = login;
		this.password = password;
		this.firstname = firstname;
		this.lastname = lastname;
		this.datebirthday = datebirthday;
		this.email = email;
		this.admin = admin;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public Date getDatebirthday() {
		return datebirthday;
	}

	public void setDatebirthday(Date datebirthday) {
		this.datebirthday = datebirthday;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Boolean getAdmin() {
		return admin;
	}

	public void setAdmin(Boolean admin) {
		this.admin = admin;
	}

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "UserBean [id=" + id + ", login=" + login + ", password=" + password + ", firstname=" + firstname + ", lastname=" + lastname
                + ", datebirthday=" + datebirthday + ", email=" + email + ", admin=" + admin + "]";
    }
	
	
}
