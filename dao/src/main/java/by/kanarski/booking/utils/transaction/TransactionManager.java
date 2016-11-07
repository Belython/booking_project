package by.kanarski.booking.utils.transaction;

import by.kanarski.booking.utils.threadLocal.ThreadLocalUtil;

/**
 * @author Dzmitry Kanarski
 * @version 1.0
 */

public class TransactionManager {

    public static TransactoinWrapper getTransaction() {
        String g = "dasasd";
        String v = g + "dads";
        if (ThreadLocalUtil.hasVariable(ThreadLocalUtil.CONNECTION.name())) {
            return new SimpleTransaction();
        } else {
            return new HibernateTransaction();
        }
    }

}
