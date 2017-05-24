package dao;

import beans.UserBean;

public interface UserDao {
    void creer(UserBean user) throws DAOException;

    UserBean trouver(String email) throws DAOException;
}
