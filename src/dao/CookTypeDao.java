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
import beans.UserBean;

public class CookTypeDao extends Dao {

	private static CookTypeDao cookTypeDao;

	private CookTypeDao() {

	}

	public static CookTypeDao getInstance() {
		if (null == cookTypeDao) {
			cookTypeDao = new CookTypeDao();
		}
		return cookTypeDao;
	}

	public List<CookTypeBean> getAll() {
		List<CookTypeBean> cookTypeBeansList = new ArrayList<CookTypeBean>();
		try {
			Class.forName("com.mysql.jdbc.Driver");

			Connection conn;
			conn = DriverManager.getConnection(url, user, passwd);

			Statement query = conn.createStatement();
			// Executer puis parcourir les résultats
			ResultSet rs = query.executeQuery(Request.SELECT_COOK_TYPE.getQuery());

			while (rs.next()) {
				CookTypeBean cookTypeBean = new CookTypeBean();
				cookTypeBean.setId(rs.getInt("COT_ID"));
				cookTypeBean.setText(rs.getString("COT_TEXT"));
				cookTypeBeansList.add(cookTypeBean);
			}

			rs.close();
			query.close();
			conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return cookTypeBeansList;

	}

	// public CookTypeBean getCookType(int cookTypeId){
	// CookTypeBean cookTypeBean = null;
	// try {
	// Class.forName("com.mysql.jdbc.Driver");
	//
	// Connection conn;
	// conn = DriverManager.getConnection(url, user, passwd);
	//
	// PreparedStatement querySt = conn
	// .prepareStatement(Request.SELECT_USER.getQuery());
	// // Définition de la valeur des paramètres
	// querySt.setInt(1, cookTypeId);
	//
	// ResultSet rs = querySt.executeQuery();
	// // Only one result
	// if (rs.next()) {
	// cookTypeBean = new CookTypeBean();
	// cookTypeBean.setId(rs.getInt("COT_ID"));
	// cookTypeBean.setText(rs.getString("COT_TEXT"));
	// }
	//
	// rs.close();
	// querySt.close();
	// conn.close();
	//
	// } catch (SQLException e) {
	// e.printStackTrace();
	// } catch (ClassNotFoundException e) {
	// e.printStackTrace();
	// }
	// return cookTypeBean;
	// }

}
