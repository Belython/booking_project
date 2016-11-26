package by.kanarski.booking.utils.transaction;

/**
 * @author Dzmitry Kanarski
 * @version 1.0
 */

public class TransactionManager {

    public static TransactoinWrapper getTransaction() {
//        if (ThreadLocalUtil.hasVariable(ThreadLocalUtil.CONNECTION.name())) {
//            return new SimpleTransaction();
//        } else {
//            return new HibernateTransaction();
//        }
        return new HibernateTransaction();
    }

}
