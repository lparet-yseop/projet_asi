package controller;

import java.io.IOException;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

/**
 * Utilities for controllers
 * 
 * @author Lucas Grégoire
 */
public class ControllerUtils {

    /**
     * Create a notification message
     * 
     * @param severity The severity of the message
     * @param text The text
     */
    public static void addMessage( Severity severity, String text ) {
        FacesMessage message = new FacesMessage(severity, text, null);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    /**
     * method to redirect to other page
     * 
     * @param page The page to redirect to
     */
    public static void redirectTo( String page ) {
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        context.getFlash().setKeepMessages(true);

        try {
            context.redirect(context.getRequestContextPath() + "/jsf/" + page + ".jsf");
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
