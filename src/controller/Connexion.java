package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.UserBean;
import dao.DAOFactory;
import dao.UserDao;

public class Connexion extends HttpServlet {

	public static final String CONF_DAO_FACTORY = "daofactory";
    public static final String ATT_USER         = "user";
    public static final String VUE              = "/WEB-INF/jsf/login.jsp";

    private UserDao userDao;

    public void init() throws ServletException {
        /* Récupération d'une instance de notre DAO Utilisateur */
        this.userDao = ((DAOFactory)getServletContext().getAttribute(CONF_DAO_FACTORY)).getUtilisateurDao();
    }

    public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
        /* Affichage de la page d'inscription */
        this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );
    }

    public void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {

    	UserBean user = userDao.trouver("louis.paret@gmail.com");
    	if(user != null) {
    		this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );
    	} else {
    		this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );
    	}
    }
}
