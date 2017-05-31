package controller;

import java.util.List;

import javax.faces.bean.ManagedBean;

import beans.CookTypeBean;
import dao.impl.CookTypeDAO;

@ManagedBean
public class CookTypeController {

    List<CookTypeBean> cookTypeBeansList;

    public CookTypeController() {
        this.cookTypeBeansList = getAllReceipes();
    }

    public List<CookTypeBean> getAllReceipes() {        
        return CookTypeDAO.getInstance().findAll();
    }

    public List<CookTypeBean> getCookTypeBeansList() {
        return cookTypeBeansList;
    }

    public void setCookTypeBeansList( List<CookTypeBean> cookTypeBeansList ) {
        this.cookTypeBeansList = cookTypeBeansList;
    }

}
