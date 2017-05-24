package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import beans.ReceipeBean;

public class ReceipeDao extends Dao {

	private static ReceipeDao userDao;

	private ReceipeDao() {

	}

	public static ReceipeDao getInstance() {
		if (null == userDao) {
			userDao = new ReceipeDao();
		}
		return userDao;
	}

	public boolean add(String name, String details, String resume, int nbPersons, int complexity, String type, String image) {
		boolean result = false;
		try {
			Class.forName("com.mysql.jdbc.Driver");

			Connection conn;
			conn = DriverManager.getConnection(url, user, passwd);

			Statement query = conn.createStatement();
			PreparedStatement querySt = conn.prepareStatement(Request.INSERT_RECEIPE.getQuery());
			// Définition de la valeur des paramètres
			querySt.setString(1, name);
			querySt.setString(2, resume);
			querySt.setString(3, details);
			querySt.setInt(4, nbPersons);
			querySt.setInt(5, complexity);
			querySt.setString(6, type);
			querySt.setString(7, image);

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

	public boolean edit(int id, String name, String details, String resume, int nbPersons, int complexity,
			String type, String image) {
		boolean result = false;
		try {
			Class.forName("com.mysql.jdbc.Driver");

			Connection conn;
			conn = DriverManager.getConnection(url, user, passwd);

			Statement query = conn.createStatement();

			PreparedStatement querySt = conn.prepareStatement(Request.UPDATE_RECEIPE.getQuery());
			// Definition de la valeur des parametres
			querySt.setString(1, name);
			querySt.setString(2, resume);
			querySt.setString(3, details);
			querySt.setInt(4, nbPersons);
			querySt.setInt(5, complexity);
			querySt.setString(6, type);
			querySt.setString(7, image);
			// Where clause
			querySt.setInt(8, id);

			// Execution
			int rs = querySt.executeUpdate();
			if (rs == 0) {
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

	public List<ReceipeBean> getAll() {
		List<ReceipeBean> receiptList = new ArrayList<ReceipeBean>();
		try {
			Class.forName("com.mysql.jdbc.Driver");

			Connection conn;
			conn = DriverManager.getConnection(url, user, passwd);

			Statement query = conn.createStatement();
			// Executer puis parcourir les résultats
			ResultSet rs = query.executeQuery(Request.SELECT_ALL_RECEIPT.getQuery());

			while (rs.next()) {
				ReceipeBean receipe = new ReceipeBean();
				receipe.setId(rs.getInt("id"));
				receipe.setDetails(rs.getString("details"));
				receipe.setName(rs.getString("name"));
				receipe.setComplexity(rs.getInt("complexity"));
				receipe.setNbPersons(rs.getInt("nbPersons"));
				receipe.setResume(rs.getString("resume"));
				receipe.setType(rs.getString("type"));

				receiptList.add(receipe);
			}

			rs.close();
			query.close();
			conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return receiptList;
	}
	
	public ReceipeBean getReceipe(int id) {
		ReceipeBean receipe = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");

			Connection conn;
			conn = DriverManager.getConnection(url, user, passwd);

			PreparedStatement querySt = conn
					.prepareStatement(Request.SELECT_RECEIPT.getQuery());
			querySt.setInt(1, id);

			ResultSet rs = querySt.executeQuery();
			// Only one result
			if (rs.next()) {
				receipe = new ReceipeBean();
				receipe.setId(rs.getInt("id"));
				receipe.setDetails(rs.getString("details"));
				receipe.setName(rs.getString("name"));
				receipe.setComplexity(rs.getInt("complexity"));
				receipe.setNbPersons(rs.getInt("nbPersons"));
				receipe.setResume(rs.getString("resume"));
				receipe.setType(rs.getString("type"));
			}

			rs.close();
			querySt.close();
			conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return receipe;
	}
}
