package pre.jsonHandler;

import by.kanarski.booking.exceptions.DaoException;
import by.kanarski.booking.utils.SystemLogger;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

/**
 * @author Dzmitry Kanarski
 * @version 1.0
 */

public class Run {

    public static void main(String[] args) {
        try {
            TestDatabaseFiller testDatabaseFiller = new ClassPathXmlApplicationContext("dao-test-config.xml").getBean(TestDatabaseFiller.class);
            testDatabaseFiller.execute();
        } catch (DaoException | IOException e) {
            SystemLogger.getInstance().logError(Run.class, e.getMessage(), e);
        }
    }


}
