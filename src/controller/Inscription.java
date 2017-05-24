package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.UserBean;
import dao.DAOFactory;
import dao.UserDao;

public class Inscription extends HttpServlet {

	public static final String CONF_DAO_FACTORY = "daofactory";
    public static final String ATT_USER         = "user";
    public static final String ATT_FORM         = "form";
    public static final String VUE              = "/WEB-INF/jsf/register.jsp";

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

    	/* Préparation de l'objet formulaire */
        InscriptionForm form = new InscriptionForm(userDao );

        /* Traitement de la requête et récupération du bean en résultant */
        UserBean user = form.inscrireUtilisateur( request );

        /* Stockage du formulaire et du bean dans l'objet request */
        request.setAttribute( ATT_FORM, form );
        request.setAttribute( ATT_USER, user );
        
        this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );
    }
}
