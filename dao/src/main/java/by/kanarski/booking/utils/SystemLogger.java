package by.kanarski.booking.utils;

import org.apache.log4j.Logger;

public class SystemLogger {

    private static SystemLogger instance;
    private Logger logger;
    private Class sender;

    private SystemLogger() {
    }

    public static synchronized SystemLogger getInstance() {
        if (instance == null) {
            instance = new SystemLogger();
        }
        return instance;
    }

    public SystemLogger setSender(Class sender) {
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

    public void logError(Class sender, Throwable error) {
        logger = Logger.getLogger(sender);
        logger.error(error);
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