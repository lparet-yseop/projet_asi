package controller;

import java.io.IOException;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import bean.UserRegisterBean;

/**
 * Controller for users management
 * 
 * @author Lucas Grégoire
 */
@ManagedBean( name = "userController" )
@ApplicationScoped
public class UserController {

    /**
     * Register the User and redirect to home
     * 
     * @param bean The UserRegisterBean
     * @return The index.jsp page
     * @throws IOException
     */
    public String register( UserRegisterBean bean ) throws IOException {
        System.out.println(bean.getUserBean());

        // TODO plug to DAO

        return "login.jsf";
    }

}
