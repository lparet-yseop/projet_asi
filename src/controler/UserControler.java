package controler;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import beans.UserBean;
import beans.Util;
import dao.UserDao;

@ManagedBean
@ViewScoped
public class UserControler implements Serializable {

	private static final long serialVersionUID = 1L;

	public boolean addUser(UserBean user) {
		UserDao userDao = UserDao.getInstance();

		boolean rs = userDao.add(user.getFirstname(), user.getLastname(), user.getDatebirthday(), user.getLogin(),
				user.getEmail(), user.getPassword());

		return rs;
	}

	public List<UserBean> getAllUsers() {
		UserDao userDao = UserDao.getInstance();

		List<UserBean> list = userDao.getAll();

		return list;
	}

	public UserBean getUser(int userId) {
		UserDao userDao = UserDao.getInstance();

		UserBean user = userDao.getUser(userId);

		return user;
	}

	public void getUserByLogin(String login, String password) {
		UserDao userDao = UserDao.getInstance();

		UserBean user = userDao.getUserByLogin(login, password);

		if(user != null) {
			redirectTo("activities");
		} else {
			logout();
			addMessage(FacesMessage.SEVERITY_WARN, "Connexion échouée");
		}
	}
     
    public void addMessage(Severity severity, String texte) {
        FacesMessage message = new FacesMessage(severity, texte,  null);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public void redirectTo(String page) {
    	ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        try {
			context.redirect(context.getRequestContextPath() + "/jsf/" + page + ".jsf");
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    public void logout() {
        Util.getSession().invalidate();
     }
}
