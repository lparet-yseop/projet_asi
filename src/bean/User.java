package bean;

import java.io.Serializable;
import java.util.Random;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class User implements Serializable {
	private static final long serialVersionUID = 1L;
	private String name;
	private String firstName;
	private String lastName;
	private String pwd;
	private int age;
	private int pictureNumber;
	private String email;

	public User() {
		java.util.Random rand = new java.util.Random();
		pictureNumber = rand.nextInt(12);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String goToWelcome() {
		return "welcomeUser/welcome";
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getFirstName() {
		return firstName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public void setPictureNumber(int pictureNumber) {
		this.pictureNumber = pictureNumber;
	}
	
	public int getPictureNumber() {
		return pictureNumber;
	}
	
}
