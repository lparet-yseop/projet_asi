package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import beans.NoteBean;

public class NoteDao extends Dao {

    private static NoteDao noteDao;

    private NoteDao() {

    }

    public static NoteDao getInstance() {
        if (null == noteDao) {
            noteDao = new NoteDao();
        }
        return noteDao;
    }

    // public ReceipeBean getReceipe(int id) {
    // ReceipeBean receipe = null;
    // try {
    // Class.forName("com.mysql.jdbc.Driver");
    //
    // Connection conn;
    // conn = DriverManager.getConnection(url, user, passwd);
    //
    // PreparedStatement querySt = conn
    // .prepareStatement(Request.SELECT_USER.getQuery());
    // querySt.setInt(1, id);
    //
    // ResultSet rs = querySt.executeQuery();
    // // Only one result
    // if (rs.next()) {
    // receipe = new ReceipeBean();
    // receipe.setId(rs.getInt("id"));
    // receipe.setDetails(rs.getString("details"));
    // receipe.setName(rs.getString("name"));
    // receipe.setComplexity(rs.getInt("complexity"));
    // receipe.setNbPersons(rs.getInt("nbPersons"));
    // receipe.setResume(rs.getString("resume"));
    // receipe.setType(rs.getString("type"));
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
    // return receipe;
    // }

    public boolean add( int idReceipe, int idUser, String note, String title ) {
        boolean result = false;
        try {
            Class.forName("com.mysql.jdbc.Driver");

            Connection conn;
            conn = DriverManager.getConnection(url, user, passwd);

            Statement query = conn.createStatement();
            PreparedStatement querySt = conn.prepareStatement(Request.INSERT_NOTE.getQuery());

            // Définition de la valeur des paramètres
            querySt.setInt(1, idReceipe);
            querySt.setString(2, title);
            querySt.setString(3, note);
            querySt.setInt(4, idUser);

            // Exécution
            int rs = querySt.executeUpdate();
            if (rs == 1) {
                result = true;
            }
            query.close();
            conn.close();

        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return result;
    }

    public boolean edit( int id, int idReceipe, int idUser, String note, String title ) {
        boolean result = false;
        try {
            Class.forName("com.mysql.jdbc.Driver");

            Connection conn;
            conn = DriverManager.getConnection(url, user, passwd);

            Statement query = conn.createStatement();

            PreparedStatement querySt = conn.prepareStatement(Request.UPDATE_NOTE.getQuery());
            // Definition de la valeur des parametres
            querySt.setInt(1, idReceipe);
            querySt.setString(2, title);
            querySt.setString(3, note);
            querySt.setInt(4, idUser);
            // Where clause
            querySt.setInt(5, id);

            // Execution
            int rs = querySt.executeUpdate();
            if (rs == 0) {
                result = true;
            }
            query.close();
            conn.close();

        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return result;
    }

    public List<NoteBean> getAllForReceipe( int idReceipe ) {
        List<NoteBean> noteList = new ArrayList<NoteBean>();
        try {
            Class.forName("com.mysql.jdbc.Driver");

            Connection conn;
            conn = DriverManager.getConnection(url, user, passwd);

            Statement query = conn.createStatement();
            // Executer puis parcourir les résultats
            PreparedStatement querySt = conn.prepareStatement(Request.SELECT_ALL_NOTES_FOR_RECEIPE.getQuery());
            // Definition de la valeur des parametres
            querySt.setInt(1, idReceipe);

            ResultSet rs = querySt.executeQuery();

            while (rs.next()) {
                NoteBean note = new NoteBean();
                note.setId(rs.getInt("id"));
                note.setIdReceipe(rs.getInt("idReceipe"));
                note.setIdUser(rs.getInt("idUser"));
                note.setNote(rs.getString("note"));
                note.setTitle(rs.getString("title"));

                noteList.add(note);
            }

            rs.close();
            query.close();
            conn.close();

        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return noteList;
    }

    public List<NoteBean> getNotesForUsers( int idUser ) {
        List<NoteBean> noteList = new ArrayList<NoteBean>();
        try {
            Class.forName("com.mysql.jdbc.Driver");

            Connection conn;
            conn = DriverManager.getConnection(url, user, passwd);

            Statement query = conn.createStatement();
            // Executer puis parcourir les résultats
            PreparedStatement querySt = conn.prepareStatement(Request.SELECT_ALL_NOTES_FOR_USER.getQuery());
            // Definition de la valeur des parametres
            querySt.setInt(1, idUser);

            ResultSet rs = querySt.executeQuery();

            while (rs.next()) {
                NoteBean note = new NoteBean();
                note.setId(rs.getInt("id"));
                note.setIdReceipe(rs.getInt("idReceipe"));
                note.setIdUser(rs.getInt("idUser"));
                note.setNote(rs.getString("note"));
                note.setTitle(rs.getString("title"));

                noteList.add(note);
            }

            rs.close();
            query.close();
            conn.close();

        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return noteList;
    }
}
