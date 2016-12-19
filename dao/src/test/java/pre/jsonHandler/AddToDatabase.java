package pre.jsonHandler;

import by.kanarski.booking.exceptions.DaoException;
import by.kanarski.booking.utils.SystemLogger;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author Dzmitry Kanarski
 * @version 1.0
 */

public class AddToDatabase {

    public static void main(String[] args) {
        try {
            TestDatabaseFiller testDatabaseFiller = new ClassPathXmlApplicationContext("dao-test-config.xml").getBean(TestDatabaseFiller.class);
            testDatabaseFiller.addToDatabase();
        } catch (DaoException e) {
            SystemLogger.getInstance().logError(AddToDatabase.class, e.getMessage(), e);
        }
    }


}
