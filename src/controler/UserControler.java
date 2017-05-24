package controler;

import java.util.Calendar;
import java.util.List;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import beans.UserBean;
import dao.UserDao;

@ManagedBean
@ApplicationScoped
public class UserControler {

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

	public String getUserByLogin(String login, String password) {
		UserDao userDao = UserDao.getInstance();

		UserBean user = userDao.getUserByLogin(login, password);

		if(user != null) {
			return "activities.jsp";
		} else {
			
			return "login.jsp";
		}
	}

	/**
	 * To test
	 * 
	 */
//	public static void main(String[] args) {
//		System.out.println("tototo");
//		UserControler uc = new UserControler();
//		UserBean user = new UserBean("louis", "louis", "louis", "paret", new java.sql.Date(Calendar.getInstance().getTime().getTime()), "louis.paret@gmail.com");
//		boolean addUser = uc.addUser(user);
//		System.out.println("User add: " + addUser);
//
//		List<UserBean> allUsers = uc.getAllUsers();
//		for(UserBean u : allUsers) {
//			System.out.println(u.getFirstname());
//		}
//		
//	}
}
