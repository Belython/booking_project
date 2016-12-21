package by.kanarski.booking.utils.properties;

import java.util.Properties;

public class PropertiesManager {

    private static PropertiesManager instance;

    private static final String OUTGOING_MAIL_PROPERTIES = "outgoingMail.xml";
    
    private PropertiesManager(){
        
    }

    public static synchronized PropertiesManager getInstance() {
        if (instance == null) {
            instance = new PropertiesManager();
        }
        return instance;
    }

    public Properties getOutgoingMailProperties() {
        Properties properties = new Properties();
//        try {
//            InputStream inputStream = new FileInputStream(OUTGOING_MAIL_PROPERTIES);
//            properties.loadFromXML(inputStream);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        properties.setProperty("mail.smtp.host", "smtp.gmail.com");
        properties.setProperty("mail.smtp.socketFactory.port", "465");
        properties.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        properties.setProperty("mail.smtp.auth", "true");
        properties.setProperty("mail.smtp.port", "465");
        return properties;
    }
    
}
