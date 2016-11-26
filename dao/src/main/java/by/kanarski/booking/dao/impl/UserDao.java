package by.kanarski.booking.dao.impl;

import by.kanarski.booking.entities.User;
import by.kanarski.booking.dao.interfaces.IUserDao;

/**
 * @author Dzmitry Kanarski
 * @version 1.0
 * @see IUserDao
 */
public class UserDao extends ExtendedBaseDao<User> implements IUserDao {

    private static UserDao instance = null;

    private UserDao() {
    }

    public static UserDao getInstance() {
        if (instance == null) {
            instance = new UserDao();
        }
        return instance;
    }

//    @Override
//    public User getByLogin(String login) throws DaoException {
//        SearchFilter searchFilter = SearchFilter.createBasicEqFilter(ColumnName.USER_LOGIN, login);
//        return getUniqueByFilter(searchFilter);
//    }
//
//    @Override
//    public User getByEmail(String email) throws DaoException {
//
//    }

//    @Override
//    public boolean isAuthorized(String login, String password) throws DaoException {
//        SearchFilter searchFilter = new SearchFilter();
//        searchFilter.setEqFilter(ColumnName.USER_LOGIN, login);
//        searchFilter.setEqFilter(ColumnName.USER_PASSWORD, password);
//        User user = getUniqueByFilter(searchFilter);
//        return user != null;
//    }

//    @Override
//    public boolean isNewUser(String login) throws DaoException {
//        Connection connection = ConnectionUtil.getConnection();
//        ResultSet resultSet = null;
//        boolean isNew = true;
//        try (PreparedStatement stm = connection.prepareStatement(CHECK_LOGIN_QUERY)) {
//            stm.setString(1, login);
//            resultSet = stm.executeQuery();
//            isNew = !resultSet.next();
//        } catch (SQLException e) {
//            throw new DaoException(ExceptionMessageManager.GET_EXCEPTION.get(), e);
//        } finally {
//            ClosingUtil.close(resultSet);
//        }
//        return isNew;
//    }
//
//    public void updateList(List<User> userList) throws DaoException {
//        Connection connection = ConnectionUtil.getConnection();
//        try (PreparedStatement stm = connection.prepareStatement(UPDATE_QUERY)) {
//            for (User user : userList) {
//                stm.setString(1, user.getFirstName());
//                stm.setString(2, user.getLastName());
//                stm.setString(3, user.getEmail());
//                stm.setString(4, user.getLogin());
//                stm.setString(5, user.getPassword());
//                stm.setString(6, user.getRole());
//                stm.setString(7, user.getUserStatus());
//                stm.setLong(8, user.getUserId());
//                stm.addBatch();
//            }
//            stm.executeBatch();
//        } catch (SQLException e) {
//            throw new DaoException(ExceptionMessageManager.UPDATE_EXCEPTION.get(), e);
//        }
//    }


}
