package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DAOFactory;
import dao.UserDao;

public class Inscription extends HttpServlet {

	public static final String CONF_DAO_FACTORY = "daofactory";
    public static final String ATT_USER         = "user";
    public static final String ATT_FORM         = "form";
    public static final String VUE              = "/WEB-INF/inscription.jsp";

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

        request.setAttribute(ATT_USER, userDao);

        this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );
    }
}
