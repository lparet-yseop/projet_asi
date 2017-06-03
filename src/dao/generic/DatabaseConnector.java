package dao.generic;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.Map.Entry;

import dao.utils.DBAccess;

/**
 * Database abstract connector
 * 
 * @author Lucas Grégoire
 */
public class DatabaseConnector {

    private static DatabaseConnector instance;
    private final static boolean showRequest = true;

    /**
     * Constructor of DatabaseConnector
     */
    private DatabaseConnector() {
    }

    /**
     * Executes a select query and returns the ResultSet
     * 
     * @param query The query string
     * @param parameters The map of parameters
     * @return The ResultSet
     * @throws SQLException If an error occured
     */
    public ResultSet executeQuery( String query, Map<Integer, Object> parameters ) throws SQLException {
        // Preparing statement
        PreparedStatement stmt = DBAccess.getConnection().prepareStatement(query);

        if (showRequest)
            System.out.println(query);

        // Setting parameters
        if (parameters != null)
            for (Entry<Integer, Object> entry : parameters.entrySet())
                stmt.setObject(entry.getKey(), entry.getValue());

        return stmt.executeQuery();
    }

    /**
     * Executes a update (insert, update, delete) query and returns the status as boolean
     * 
     * @param query The query string
     * @param parameters The map of parameters
     * @return true if the query succeed
     * @throws SQLException If an error occured
     */
    public boolean executeUpdate( String query, Map<Integer, Object> parameters ) throws SQLException {
        // Preparing statement
        PreparedStatement stmt = DBAccess.getConnection().prepareStatement(query);

        if (showRequest)
            System.out.println(query);
        
        // Setting parameters
        if (parameters != null)
            for (Entry<Integer, Object> entry : parameters.entrySet())
                stmt.setObject(entry.getKey(), entry.getValue());

        return stmt.executeUpdate() > 0;
    }

    /**
     * Gets the singleton
     * 
     * @return The instance of DatabaseConnector
     */
    public static DatabaseConnector getInstance() {
        if (instance == null)
            instance = new DatabaseConnector();
        return instance;
    }

}
