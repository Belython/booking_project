package by.kanarski.booking.utils;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author Dzmitry Kanarski
 * @version 1.0
 */

public class ContextHolder {

    public static ApplicationContext getServiceContext() {
        ApplicationContext serviceContext = new ClassPathXmlApplicationContext("service-config.xml");
        return serviceContext;
    }

    public static ApplicationContext getDaoContext() {
        ApplicationContext daoContext = new ClassPathXmlApplicationContext("dao-config.xml");
        return daoContext;
    }

}
