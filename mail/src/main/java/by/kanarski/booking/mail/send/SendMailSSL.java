package by.kanarski.booking.mail.send;

import by.kanarski.booking.mail.authenticatiors.BookingAuthenticator;
import by.kanarski.booking.managers.AuthenticationManager;
import by.kanarski.booking.managers.MailMessageManager;
import by.kanarski.booking.utils.properties.PropertiesManager;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Locale;
import java.util.Properties;

public class SendMailSSL {

    private static SendMailSSL instance;

//    private ResourceBundle bookingAccount = ResourceManager.AUTHENTIFICATION.get();

    private SendMailSSL() {

    }

    public static synchronized SendMailSSL getInstance() {
        if (instance == null) {
            instance = new SendMailSSL();
        }
        return instance;
    }

    private void send(String to, String subject, String messageText) {
        Session session = defineSession();
        try {
            String from = AuthenticationManager.USER_NAME.get();
            InternetAddress fromAdress = new InternetAddress(from);
            InternetAddress[] toAdress = InternetAddress.parse(to);
            Message message = new MimeMessage(session);
            message.setFrom(fromAdress);
            message.setRecipients(Message.RecipientType.TO, toAdress);
            message.setSubject(subject);
            message.setText(messageText);
            Transport.send(message);
        } catch (MessagingException e) {
            // TODO: 01.10.2016 Обработать
            throw new RuntimeException(e);
        }
    }

    private Session defineSession() {
        Properties properties = PropertiesManager.getInstance().getOutgoingMailProperties();
        Authenticator authenticator = new BookingAuthenticator();
        Session session = Session.getDefaultInstance(properties, authenticator);
        return session;
    }

    public void sendPassword(String to, String password, Locale locale) {
        String subject = MailMessageManager.SUBJECT_RECOVERY_PASSWORD.get();
        subject = "[booking.by] " + subject;
        String message = MailMessageManager.MESSAGE_YOUR_PASSWORD.get();
        message += " " + password;
        send(to, subject, message);
    }

}