package dao.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Database connector access
 * 
 * @author Lucas Grégoire
 */
public class DBAccess {

    private static final String URL = "jdbc:mysql://163.172.91.2:3306/projetJEE";
    private static final String USER = "user";
    private static final String PASS = "";
    private static Connection connection;

    /**
     * Gets the current connection
     * 
     * @return The current database connection
     */
    public static Connection getConnection() {
        if (connection == null)
            connection = initConnection();
        return connection;
    }

    private static Connection initConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            return DriverManager.getConnection(URL, USER, PASS);
        }
        catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

}
