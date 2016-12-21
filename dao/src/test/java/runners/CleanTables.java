package runners;

import by.kanarski.booking.exceptions.DaoException;
import by.kanarski.booking.testingUtils.pre.jsonHandler.TestDatabaseFiller;
import by.kanarski.booking.utils.SystemLogger;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author Dzmitry Kanarski
 * @version 1.0
 */

public class CleanTables {

    public static void main(String[] args) {
        try {
            TestDatabaseFiller testDatabaseFiller = new ClassPathXmlApplicationContext("dao-test-config.xml").getBean(TestDatabaseFiller.class);
            testDatabaseFiller.cleanTables();
        } catch (DaoException e) {
            SystemLogger.getInstance().logError(CleanTables.class, e.getMessage(), e);
        }
    }


}
