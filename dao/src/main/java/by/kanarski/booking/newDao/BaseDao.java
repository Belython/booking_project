package by.kanarski.booking.newDao;

import by.kanarski.booking.exceptions.DaoException;
import by.kanarski.booking.newDao.interfaces.IDao;
import by.kanarski.booking.utils.BookingSystemLogger;
import by.kanarski.booking.utils.hibernate.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * @author Dzmitry Kanarski
 * @version 1.0
 */

public class BaseDao implements IDao {

    private static BookingSystemLogger logger = BookingSystemLogger.getInstance().setSender(HibernateUtil.class);
    private Transaction transaction = null;

    @Override
    public void add(Object o) throws DaoException {
        try {
            Session session = HibernateUtil.getSession();
            transaction = session.beginTransaction();
            session.save(o);
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            throw new DaoException(ErrorMessage.SAVE_OR_UPDATE, e);
        }
    }

    @Override
    public Object getById(long id) throws DaoException {
        return null;
    }

    @Override
    public void update(Object o) throws DaoException {

    }

    @Override
    public void delete(Object o) throws DaoException {

    }
}
