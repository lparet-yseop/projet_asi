package beans.utils;

public class UserCounter {

	/** L'instance statique */
    private static UserCounter instance;
    
    /** variable statique des utilisateurs connectés */
    private static int userConnected = 0;
    
    public UserCounter() {
    }
    
	public static UserCounter getInstance() {
        if (null == instance) {
            instance = new UserCounter();
        }
        return instance;
    }
	
	public void addUserConnected() {
		userConnected++;
	}
	
	public void deleteUserConnected() {
		userConnected--;
	}
	
	public int getUserConnected() {
		return userConnected;
	}
}
