package dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import beans.RecipeBean;
import dao.annotation.DBAnnotationsManager2;
import dao.generic.AbstractDAO;

/**
 * DAO for RecipeBean
 * 
 * @author Lucas Grégoire
 */
public class RecipeDAO extends AbstractDAO<RecipeBean> {

    private static RecipeDAO instance;

    /**
     * @return The instance of RecipeDAO
     */
    public static RecipeDAO getInstance() {
        if (instance == null)
            instance = new RecipeDAO();
        return instance;
    }

    /**
     * Constructor
     */
    public RecipeDAO() {
        super(RecipeBean.class);
    }

    /**
     * Gets all RecipeBean from crtierias
     * 
     * @param bean The bean container of criteria
     * @return The list of beans found
     */
    public List<RecipeBean> findByCriteria( RecipeBean bean ) {
        Map<Integer, Object> map = new HashMap<Integer, Object>();
        StringBuilder sb = new StringBuilder();
        int i = 0;

        // Construct the where clause
        if (bean != null) {
            if (bean.getNbPeople() > 0) {
                sb.append("rec_nb_people = ?");
                map.put(++i, bean.getNbPeople());
            }

            if (bean.getCookTypeBean() != null) {
                if (i > 0)
                    sb.append(" AND ");

                sb.append(DBAnnotationsManager2.getTableName(RecipeBean.class) + ".cot_id = ");
                map.put(++i, bean.getCookTypeBean().getId());
            }

            if (bean.getDuration() > 0) {
                if (i > 0)
                    sb.append(" AND ");

                sb.append("rec_duration = ");
                map.put(++i, bean.getDuration());
            }

            if (bean.getDifficulty() > 0) {
                if (i > 0)
                    sb.append(" AND ");

                sb.append("rec_difficulty = ?");
                map.put(++i, bean.getDifficulty());
            }
        }

        return manager2.findAllByCustomWhereClause(sb.toString(), map);
    }

}
