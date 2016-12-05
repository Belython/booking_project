package by.kanarski.booking.dao.impl;

import by.kanarski.booking.dao.interfaces.IUserDao;
import by.kanarski.booking.entities.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @author Dzmitry Kanarski
 * @version 1.0
 * @see IUserDao
 */

@Repository
public class UserDao extends ExtendedBaseDao<User> implements IUserDao {

    @Autowired
    public UserDao(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

}
