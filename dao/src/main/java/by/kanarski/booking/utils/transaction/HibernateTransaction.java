package by.kanarski.booking.utils.transaction;

import by.kanarski.booking.utils.threadLocal.ThreadLocalUtil;
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
        this.hibernateSession = (Session) ThreadLocalUtil.HIBERNATE_SESSION.get();
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
