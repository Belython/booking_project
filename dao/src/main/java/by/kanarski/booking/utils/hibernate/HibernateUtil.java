package by.kanarski.booking.utils.hibernate;

import by.kanarski.booking.utils.BookingSystemLogger;
import by.kanarski.booking.utils.threadLocal.ThreadLocalUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class HibernateUtil {

    private static BookingSystemLogger logger = BookingSystemLogger.getInstance().setSender(HibernateUtil.class);
    private static SessionFactory sessionFactory = null;

    public static Session getSession() {
        Session session = (Session) ThreadLocalUtil.HIBERNATE_SESSION.get();
        if (session == null) {
            if (sessionFactory == null) {
                buildSessionFactory();
            }
            session = sessionFactory.openSession();
            ThreadLocalUtil.HIBERNATE_SESSION.set(session);
        }
        return session;
    }

    public static void closeSession() {
        Session session = getSession();
        if (session != null && session.isOpen()) {
            session.clear();
            session.close();
            ThreadLocalUtil.HIBERNATE_SESSION.remove();
        }
    }

    private static void buildSessionFactory() {
        try {
            Configuration configuration = new Configuration()
                    .configure()
                    .setNamingStrategy(new CustomNamingStrategy());
            StandardServiceRegistryBuilder registryBuilder = new StandardServiceRegistryBuilder().
                    applySettings(configuration.getProperties());
            ServiceRegistry serviceRegistry = registryBuilder.build();
            sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        } catch (Throwable e) {
            // TODO: 18.11.2016 исправить
            logger.logError(HibernateUtil.class, "ОЙ, ОШИБОЧКА", e);
        }
    }
}
