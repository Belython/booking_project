package by.kanarski.booking.dao.interfaces;

import by.kanarski.booking.constants.DaoMessage;
import by.kanarski.booking.entities.User;
import by.kanarski.booking.exceptions.DaoException;
import by.kanarski.booking.utils.ClosingUtil;
import by.kanarski.booking.utils.ConnectionUtil;
import by.kanarski.booking.utils.EntityParser;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * User dao interface
 * @author Dzmitry Kanarski
 * @version 1.0
 * @see IDao
 */

public interface IUserDao extends IDao<User> {

    /**
     * Adds a list of <b>{@link User}</b> in the database
     * @param userList a list of users
     * @throws DaoException
     */

    public void addList(List<User> userList) throws DaoException;

    /**
     * Recives <b>{@link User}</b> from the databse by login
     * @param login the user login
     * @return an user with the corresponding login
     * @throws DaoException
     */

    public User getByLogin(String login) throws DaoException;

    /**
     * Recives <b>{@link User}</b> from the databse by email
     * @param email the user email
     * @return an user with the corresponding email
     * @throws DaoException
     */

    public User getByEmail(String email) throws DaoException;

    /**
     * Checks user with such login and password in the database
     * @param login user login
     * @param password user password
     * @return true, if user found, false if not found
     * @throws DaoException
     */

    public boolean isAuthorized(String login, String password) throws DaoException;

    /**
     * Checks user with such login in the database
     * @param login user login
     * @return false, if user found, true if not found
     * @throws DaoException
     */

    public boolean isNewUser(String login) throws DaoException;

}
