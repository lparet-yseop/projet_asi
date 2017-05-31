package dao.impl;

import beans.CookTypeBean;
import dao.generic.AbstractDAO;

/**
 * DAO for CookTypeBean
 * 
 * @author Lucas Grégoire
 */
public class CookTypeDAO extends AbstractDAO<CookTypeBean> {

    private static CookTypeDAO instance;

    /**
     * @return The instance of UserDAO
     */
    public static CookTypeDAO getInstance() {
        if (instance == null)
            instance = new CookTypeDAO();
        return instance;
    }

    /**
     * Constructor
     */
    public CookTypeDAO() {
        super(CookTypeBean.class);
    }
}
