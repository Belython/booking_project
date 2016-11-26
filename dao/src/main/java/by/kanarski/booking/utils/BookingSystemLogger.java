package by.kanarski.booking.utils;

import org.apache.log4j.Logger;

public class BookingSystemLogger {

    private static BookingSystemLogger instance;
    private Logger logger;
    private Class sender;

    private BookingSystemLogger() {
    }

    public static synchronized BookingSystemLogger getInstance() {
        if (instance == null) {
            instance = new BookingSystemLogger();
        }
        return instance;
    }

    public BookingSystemLogger setSender(Class sender) {
        this.sender = sender;
        return instance;
    }

    public void logError(Class sender, String message, Throwable error) {
        logger = Logger.getLogger(sender);
        logger.error(message, error);
    }

    public void logError(Class sender, String message) {
        logger = Logger.getLogger(sender);
        logger.error(message);
    }

    public void logError(String message, Throwable error) {
        logger = Logger.getLogger(sender);
        logger.error(message, error);
    }

    public void logError(String message) {
        logger = Logger.getLogger(sender);
        logger.error(message);
    }

    public void logInfo(Class sender, String message) {
        logger = Logger.getLogger(sender);
        logger.info(message);
    }

    public void logInfo(String message) {
        logger = Logger.getLogger(sender);
        logger.info(message);
    }

}