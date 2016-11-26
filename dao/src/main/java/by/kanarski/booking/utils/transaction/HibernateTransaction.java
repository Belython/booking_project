package by.kanarski.booking.utils.transaction;

import by.kanarski.booking.utils.hibernate.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * @author Dzmitry Kanarski
 * @version 1.0
 */

public class HibernateTransaction implements TransactoinWrapper {

    private Session hibernateSession;
    private Transaction transaction;

    public HibernateTransaction() {
        this.hibernateSession = HibernateUtil.getSession();
    }

    @Override
    public void begin() {
        transaction = hibernateSession.beginTransaction();
    }

    @Override
    public void commit() {
        transaction.commit();
    }

    @Override
    public void rollback() {
        transaction.rollback();
    }

    @Override
    public void clean() {
        hibernateSession.clear();
    }


}
