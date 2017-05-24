package controller;

import javax.servlet.http.HttpServletRequest;

import beans.UserBean;
import dao.UserDao;

public class InscriptionForm {

	private UserDao userDao;
	private static final String CHAMP_EMAIL      = "email-input";
    private static final String CHAMP_PASS       = "password-input";
    private static final String CHAMP_CONF       = "password-repeat-input";
    private static final String CHAMP_NOM        = "nom";
    private static final String CHAMP_LOGIN        = "login-input";
    

	public InscriptionForm( UserDao userDao ) {
	    this.userDao = userDao;
	}
	
	public UserBean inscrireUtilisateur(HttpServletRequest request) {
		String email = getValeurChamp( request, CHAMP_EMAIL );
        String motDePasse = getValeurChamp( request, CHAMP_PASS );
        String confirmation = getValeurChamp( request, CHAMP_CONF );
        String nom = getValeurChamp( request, CHAMP_NOM );
	    UserBean utilisateur = new UserBean();
    	userDao.creer(utilisateur);
	    return utilisateur;
	}
	
	/*
     * Méthode utilitaire qui retourne null si un champ est vide, et son contenu
     * sinon.
     */
    private static String getValeurChamp( HttpServletRequest request, String nomChamp ) {
        String valeur = request.getParameter( nomChamp );
        if ( valeur == null || valeur.trim().length() == 0 ) {
            return null;
        } else {
            return valeur;
        }
    }
}
