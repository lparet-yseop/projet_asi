package beans.utils;

/**
 * Compteur d'utilisateurs
 * 
 * @author Louis Paret
 */
public class UserCounter {

    /** L'instance statique */
    private static UserCounter instance;

    /** variable statique des utilisateurs connectés */
    private static int userConnected = 0;

    /**
     * Constructor
     */
    public UserCounter() {
    }

    /**
     * Singleton
     * 
     * @return The UserCounter instance
     */
    public static UserCounter getInstance() {
        if (null == instance) {
            instance = new UserCounter();
        }
        return instance;
    }

    /**
     * Add user connected
     */
    public void addUserConnected() {
        userConnected++;
    }

    /**
     * Remove user connected : logout
     */
    public void deleteUserConnected() {
        userConnected--;
    }

    /**
     * Gets the number of connected users
     * 
     * @return The number of users
     */
    public int getUserConnected() {
        return userConnected;
    }
}
