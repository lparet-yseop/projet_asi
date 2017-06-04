package controller;

import java.io.Serializable;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import beans.utils.UserCounter;

/**
 * User connected coutner
 * 
 * @author Louis Paret
 */
@ManagedBean( name = "userCounterController" )
@ApplicationScoped
public class UserCounterController implements Serializable {

    private static final long serialVersionUID = 1L;
    private int nbUserConnected;

    /**
     * Constructor
     */
    public UserCounterController() {
        this.nbUserConnected = UserCounter.getInstance().getUserConnected();
    }

    /**
     * Return the number of users connected
     * 
     * @return The number of connected users
     */
    public int getUsersConnected() {
        return nbUserConnected;
    }
}
