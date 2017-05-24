package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import beans.UserBean;

public class UserDaoImpl implements UserDao {
	private DAOFactory daoFactory;
    private static final String SQL_SELECT_PAR_EMAIL = "SELECT id, email, nom, mot_de_passe, date_inscription FROM Utilisateur WHERE email = ?";
    private static final String SQL_INSERT = "INSERT INTO Utilisateur (email, mot_de_passe, nom, date_inscription) VALUES (?, ?, ?, NOW())";
	
	UserDaoImpl(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }
    
    /* Implémentation de la méthode trouver() définie dans l'interface UtilisateurDao */
    @Override
    public UserBean trouver(String email) throws DAOException {
    	return trouver(SQL_SELECT_PAR_EMAIL, email);
    }

    /* Implémentation de la méthode creer() définie dans l'interface UtilisateurDao */
    @Override
    public void creer(UserBean user) throws IllegalArgumentException, DAOException {
    	Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet valeursAutoGenerees = null;

        try {
            /* Récupération d'une connexion depuis la Factory */
            connexion = daoFactory.getConnection();
            preparedStatement = DAOUtilitaire.initialisationRequetePreparee( connexion, SQL_INSERT, true, user.getEmail(), user.getPassword(), user.getName() );
            int statut = preparedStatement.executeUpdate();
            /* Analyse du statut retourné par la requête d'insertion */
            if ( statut == 0 ) {
                throw new DAOException( "Échec de la création de l'utilisateur, aucune ligne ajoutée dans la table." );
            }
            /* Récupération de l'id auto-généré par la requête d'insertion */
            valeursAutoGenerees = preparedStatement.getGeneratedKeys();
            if ( valeursAutoGenerees.next() ) {
                /* Puis initialisation de la propriété id du bean Utilisateur avec sa valeur */
                user.setId( valeursAutoGenerees.getInt(1));
            } else {
                throw new DAOException( "Échec de la création de l'utilisateur en base, aucun ID auto-généré retourné." );
            }
        } catch ( SQLException e ) {
            throw new DAOException( e );
        } finally {
            DAOUtilitaire.fermeturesSilencieuses( valeursAutoGenerees, preparedStatement, connexion );
        }
    }
    
    private UserBean trouver(String sql, Object... objets) throws DAOException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        UserBean user = null;

        try {
            /* RÃ©cupÃ©ration d'une connexion depuis la Factory */
            connexion = daoFactory.getConnection();
            /*
             * PrÃ©paration de la requÃªte avec les objets passÃ©s en arguments
             * (ici, uniquement une adresse email) et exÃ©cution.
             */
            preparedStatement = DAOUtilitaire.initialisationRequetePreparee( connexion, sql, false, objets );
            resultSet = preparedStatement.executeQuery();
            /* Parcours de la ligne de donnÃ©es retournÃ©e dans le ResultSet */
            if ( resultSet.next() ) {
                user = map( resultSet );
            }
        } catch ( SQLException e ) {
            throw new DAOException( e );
        } finally {
            DAOUtilitaire.fermeturesSilencieuses( resultSet, preparedStatement, connexion );
        }

        return user;
    }
    
    /*
     * Simple méthode utilitaire permettant de faire la correspondance (le
     * mapping) entre une ligne issue de la table des utilisateurs (un
     * ResultSet) et un bean Utilisateur.
     */
    private static UserBean map(ResultSet resultSet) throws SQLException {
        UserBean user = new UserBean();
        user.setId( resultSet.getInt("id"));
        user.setEmail( resultSet.getString("email"));
        user.setPassword( resultSet.getString("password"));
        user.setName(resultSet.getString("name"));
        user.setAge(resultSet.getInt("age"));
        user.setLastname(resultSet.getString("lastname"));
        user.setLogin(resultSet.getString("login"));
        return user;
    }
}
