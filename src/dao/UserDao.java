package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import beans.UserBean;

public class UserDao extends Dao {

	private static UserDao userDao;

	private UserDao() {

	}

	public static UserDao getInstance() {
		if (null == userDao) {
			userDao = new UserDao();
		}
		return userDao;
	}

	public boolean add(String firstName, String lastName, Date datebirthday,
			String login, String email, String password) {
		boolean result = false;
		try {
			Class.forName("com.mysql.jdbc.Driver");

			Connection conn;
			conn = DriverManager.getConnection(url, user, passwd);

			Statement query = conn.createStatement();

			PreparedStatement querySt = conn
					.prepareStatement(Request.INSERT_USER.getQuery());
			// Définition de la valeur des paramètres
			querySt.setString(1, login);
			querySt.setString(2, password);
			querySt.setString(3, firstName);
			querySt.setString(4, lastName);
			querySt.setString(6, email);
			querySt.setDate(5, datebirthday);

			// Exécution
			int rs = querySt.executeUpdate();
			if (rs == 1) {
				result = true;
			}
			query.close();
			conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return result;
	}

	public List<UserBean> getAll() {
		List<UserBean> userList = new ArrayList<UserBean>();
		try {
			Class.forName("com.mysql.jdbc.Driver");

			Connection conn;
			conn = DriverManager.getConnection(url, user, passwd);

			Statement query = conn.createStatement();
			// Executer puis parcourir les résultats
			ResultSet rs = query.executeQuery(Request.SELECT_ALL_USERS
					.getQuery());

			while (rs.next()) {
				UserBean user = new UserBean();
				user.setId(rs.getInt("USR_ID"));
				user.setFirstname(rs.getString("USR_FIRSTNAME"));
				user.setLastname(rs.getString("USR_LASTNAME"));
				user.setDatebirthday(rs.getDate("USR_DATE_BIRTH"));
				user.setEmail(rs.getString("USR_EMAIL"));
				user.setAdmin(rs.getBoolean("USR_ADMIN"));
				user.setLogin(rs.getString("USR_LOGIN"));
				user.setPassword(rs.getString("USR_PASSWORD"));
				userList.add(user);
			}

			rs.close();
			query.close();
			conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return userList;
	}

	public UserBean getUser(int userId) {
		UserBean userB = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");

			Connection conn;
			conn = DriverManager.getConnection(url, user, passwd);

			PreparedStatement querySt = conn
					.prepareStatement(Request.SELECT_USER.getQuery());
			// Définition de la valeur des paramètres
			querySt.setInt(1, userId);

			ResultSet rs = querySt.executeQuery();
			// Only one result
			if (rs.next()) {
				userB = new UserBean();
				userB.setId(rs.getInt("USR_ID"));
				userB.setFirstname(rs.getString("USR_FIRSTNAME"));
				userB.setLastname(rs.getString("USR_LASTNAME"));
				userB.setDatebirthday(rs.getDate("USR_DATE_BIRTH"));
				userB.setEmail(rs.getString("USR_EMAIL"));
				userB.setAdmin(rs.getBoolean("USR_ADMIN"));
				userB.setLogin(rs.getString("USR_LOGIN"));
				userB.setPassword(rs.getString("USR_PASSWORD"));
			}

			rs.close();
			querySt.close();
			conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return userB;
	}
	
	public UserBean getUserByLogin(String login, String password) {
		UserBean userB = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");

			Connection conn;
			conn = DriverManager.getConnection(url, user, passwd);

			PreparedStatement querySt = conn
					.prepareStatement(Request.SELECT_USER_BY_LOGIN.getQuery());
			// Définition de la valeur des paramètres
			querySt.setString(1, login);
			querySt.setString(2, password);

			ResultSet rs = querySt.executeQuery();
			// Only one result
			if (rs.next()) {
				userB = new UserBean();
				userB.setId(rs.getInt("USR_ID"));
				userB.setFirstname(rs.getString("USR_FIRSTNAME"));
				userB.setLastname(rs.getString("USR_LASTNAME"));
				userB.setDatebirthday(rs.getDate("USR_DATE_BIRTH"));
				userB.setEmail(rs.getString("USR_EMAIL"));
				userB.setAdmin(rs.getBoolean("USR_ADMIN"));
				userB.setLogin(rs.getString("USR_LOGIN"));
				userB.setPassword(rs.getString("USR_PASSWORD"));
			}

			rs.close();
			querySt.close();
			conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return userB;
	}

}
