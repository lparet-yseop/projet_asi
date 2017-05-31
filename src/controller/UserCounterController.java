package controller;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import beans.utils.UserCounter;

@ManagedBean( name = "userCounterController" )
@ViewScoped
public class UserCounterController implements Serializable {

	private static final long serialVersionUID = 1L;
	private int nbUserConnected;
	 
	public UserCounterController() {
		this.nbUserConnected = UserCounter.getInstance().getUserConnected();
	}

	public int getUsersConnected() {
		return nbUserConnected;
	}
}
