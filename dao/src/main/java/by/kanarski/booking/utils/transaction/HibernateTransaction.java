package by.kanarski.booking.utils.transaction;

import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * @author Dzmitry Kanarski
 * @version 1.0
 */

public class HibernateTransaction implements TransactoinWrapper {

    private Session hibernateSession;
    private Transaction transaction;

    public HibernateTransaction(Session hibernateSession) {
        this.hibernateSession = hibernateSession;
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

    }
}
