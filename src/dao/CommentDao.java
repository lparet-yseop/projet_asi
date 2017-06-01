package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import beans.CommentBean;
import beans.CookTypeBean;
import beans.ReceipeBean;
import beans.UserBean;

public class CommentDao extends Dao {

	private static CommentDao commentDao;
	
	private CommentDao(){
		
	}
	
	public static CommentDao getInstance(){
		if(commentDao == null){
			commentDao = new CommentDao();
		}
		return commentDao;
	}
	
	public boolean add(String text, Date date, int mark, ReceipeBean receipeBean, UserBean userBean) {
		boolean result = false;
		try {
			Class.forName("com.mysql.jdbc.Driver");

			Connection conn;
			conn = DriverManager.getConnection(url, user, passwd);

			Statement query = conn.createStatement();
			PreparedStatement querySt = conn.prepareStatement(Request.INSERT_COMMENT.getQuery());
			// Définition de la valeur des paramètres
			querySt.setString(1, text);
			querySt.setDate(2, new java.sql.Date(date.getTime()));
			querySt.setInt(3, mark);
			querySt.setInt(4, receipeBean.getId());
			querySt.setInt(5, userBean.getId());
			
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
	
	
	public List<CommentBean> findByReceipe(ReceipeBean receipeBean) {
		List<CommentBean> commentList = new ArrayList<CommentBean>();
		try {
			Class.forName("com.mysql.jdbc.Driver");

			Connection conn;
			conn = DriverManager.getConnection(url, user, passwd);

			Statement query = conn.createStatement();
						
			// Executer puis parcourir les résultats
			String queryString = Request.SELECT_COMMENT_FOR_RECEIPE.getQuery() + Request.JOIN_COMMENT_USER.getQuery()  + Request.WHERE_RECEIPE.getQuery();
			PreparedStatement querySt = conn.prepareStatement( queryString);
			// Definition de la valeur des parametres
			querySt.setInt(1, receipeBean.getId());

			ResultSet rs = querySt.executeQuery();
			
			

			while (rs.next()) {
				CommentBean comment = new CommentBean();
				comment.setId(rs.getInt("COM_ID"));
				comment.setText(rs.getString("COM_TEXT"));
				comment.setDate(rs.getDate("COM_DATE"));
				comment.setMark(rs.getInt("COM_MARK"));
				ReceipeBean receipeBean2 = new ReceipeBean();
				receipeBean.setId(rs.getInt("REC_ID"));
				comment.setReceipeBean(receipeBean2);
				UserBean userBean = new UserBean();
				userBean.setFirstname(rs.getString("USR_FIRSTNAME"));
				userBean.setPathPhoto(rs.getString("USR_PATH_PHOTO"));
				comment.setUserBean(userBean);
				commentList.add(comment);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return commentList;

	}
	
	
	
}
