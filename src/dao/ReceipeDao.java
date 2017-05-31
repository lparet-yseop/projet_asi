package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import beans.CookTypeBean;
import beans.ReceipeBean;

public class ReceipeDao extends Dao {

	private static ReceipeDao receipeDao;

	private ReceipeDao() {

	}

	public static ReceipeDao getInstance() {
		if (null == receipeDao) {
			receipeDao = new ReceipeDao();
		}
		return receipeDao;
	}

	public boolean add(String name, String details, String resume, int nbPersons, int complexity, String type,
			String image) {
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

	public boolean edit(int id, String name, String details, String resume, int nbPersons, int complexity, String type,
			String image) {
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
			ResultSet rs = query.executeQuery(Request.SELECT_WITHOUT_CRITERIA.getQuery() + Request.INNER_JOIN_COOK_TYPE.getQuery());

			while (rs.next()) {
				ReceipeBean receipe = new ReceipeBean();
				receipe.setId(rs.getInt("REC_ID"));
				receipe.setDescription(rs.getString("REC_DESCRIPTION"));
				receipe.setTitle(rs.getString("REC_TITLE"));
				receipe.setDifficulty(rs.getInt("REC_DIFFICULTY"));
				receipe.setNbPeople(rs.getInt("REC_NB_PEOPLE"));
				receipe.setDuration(rs.getInt("REC_DURATION"));
				CookTypeBean cookTypeBean = new CookTypeBean();
				cookTypeBean.setId(rs.getInt("COT_ID"));
				cookTypeBean.setText(rs.getString("COT_TEXT"));
				receipe.setCookTypeBean(cookTypeBean);
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

			PreparedStatement querySt = conn.prepareStatement(Request.SELECT_RECEIPT.getQuery() + Request.INNER_JOIN_COOK_TYPE.getQuery());
			querySt.setInt(1, id);

			ResultSet rs = querySt.executeQuery();
			// Only one result
			if (rs.next()) {
				receipe = new ReceipeBean();
				receipe.setId(rs.getInt("REC_ID"));
				receipe.setDescription(rs.getString("REC_DESCRIPTION"));
				receipe.setTitle(rs.getString("REC_TITLE"));
				receipe.setDifficulty(rs.getInt("REC_DIFFICULTY"));
				receipe.setNbPeople(rs.getInt("REC_NB_PEOPLE"));
				receipe.setDuration(rs.getInt("REC_DURATION"));
				CookTypeBean cookTypeBean = new CookTypeBean();
				cookTypeBean.setId(rs.getInt("COT_ID"));
				cookTypeBean.setText(rs.getString("COT_TEXT"));
				receipe.setCookTypeBean(cookTypeBean);
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

	public List<ReceipeBean> findByCriteria(ReceipeBean receipeBean) {
		List<ReceipeBean> receiptList = new ArrayList<ReceipeBean>();
		String WHERE_CLAUSE = " ";
		try {
			Class.forName("com.mysql.jdbc.Driver");

			Connection conn;
			conn = DriverManager.getConnection(url, user, passwd);

			Statement query = conn.createStatement();
			
			if(receipeBean != null){
				//Construct the where clause
				
				if(receipeBean.getNbPeople() > 0){
					WHERE_CLAUSE += "REC_NB_PEOPLE = "+ receipeBean.getNbPeople();
				}
				
				if(receipeBean.getCookTypeBean() != null){
					if(WHERE_CLAUSE.length() > 1 ) WHERE_CLAUSE +=" , AND ";
					WHERE_CLAUSE += "";
				}
				
				if(receipeBean.getDuration() > 0){
					if(WHERE_CLAUSE.length() > 1 ) WHERE_CLAUSE +=", AND ";
					WHERE_CLAUSE += "REC_DURATION = "+ receipeBean.getDuration();
				}
				
				if(receipeBean.getDifficulty() > 0){
					//Dernier pas de AND
					//if(WHERE_CLAUSE.length() > 1 ) WHERE_CLAUSE +="";
					WHERE_CLAUSE += "REC_DIFFICULTY = " + receipeBean.getDifficulty();
				}
						
			}
			
			
			// Executer puis parcourir les résultats
			ResultSet rs = query.executeQuery(Request.SELECT_WITHOUT_CRITERIA.getQuery() + Request.INNER_JOIN_COOK_TYPE.getQuery() + WHERE_CLAUSE );

			while (rs.next()) {
				ReceipeBean receipe = new ReceipeBean();
				receipe.setId(rs.getInt("REC_ID"));
				receipe.setDescription(rs.getString("REC_DESCRIPTION"));
				receipe.setTitle(rs.getString("REC_TITLE"));
				receipe.setDifficulty(rs.getInt("REC_DIFFICULTY"));
				receipe.setNbPeople(rs.getInt("REC_NB_PEOPLE"));
				receipe.setDuration(rs.getInt("REC_DURATION"));
				CookTypeBean cookTypeBean = new CookTypeBean();
				cookTypeBean.setId(rs.getInt("COT_ID"));
				cookTypeBean.setText(rs.getString("COT_TEXT"));
				receipe.setCookTypeBean(cookTypeBean);
				receiptList.add(receipe);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return receiptList;

	}

}
