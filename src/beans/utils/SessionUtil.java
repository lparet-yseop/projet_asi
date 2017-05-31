package beans.utils;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import beans.UserBean;

/**
 * Session utilities
 * 
 * @author Louis Paret, Lucas Grégoire
 */
public class SessionUtil {

    /**
     * @return The current Session
     */
    public static HttpSession getSession() {
        return (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
    }

    /**
     * Clear the session => unlog the user
     */
    public static void clearSession() {
        getSession().invalidate();
    }

    /**
     * @return The current user bean
     */
    public static UserBean getUserBean() {
        return (UserBean) getSession().getAttribute("userBean");
    }

    /**
     * Sets the session user bean
     * 
     * @param bean The bean to set
     */
    public static void setUserBean( UserBean bean ) {
        getSession().setAttribute("userBean", bean);
    }

    /**
     * @return The current request
     */
    public static HttpServletRequest getRequest() {
        return (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
    }
}
