package by.kanarski.booking.mail.authenticatiors;

import by.kanarski.booking.managers.DatabaseManager;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

public class BookingAuthenticator extends Authenticator {

    @Override
    protected PasswordAuthentication getPasswordAuthentication() {
        String userName = DatabaseManager.USER_NAME.get();
        String password = DatabaseManager.PASSWORD.get();
        PasswordAuthentication passwordAuthentication = new PasswordAuthentication(userName, password);
        return passwordAuthentication;
    }
}
