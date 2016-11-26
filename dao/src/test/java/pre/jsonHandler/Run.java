package pre.jsonHandler;

import by.kanarski.booking.exceptions.DaoException;
import by.kanarski.booking.utils.BookingSystemLogger;

import java.io.IOException;

/**
 * @author Dzmitry Kanarski
 * @version 1.0
 */

public class Run {

    public static void main(String[] args) {
        try {
            TestDatabaseFiller.getInstance().execute();
        } catch (DaoException | IOException e) {
            BookingSystemLogger.getInstance().logError(Run.class, e.getMessage(), e);
        }
    }


}
