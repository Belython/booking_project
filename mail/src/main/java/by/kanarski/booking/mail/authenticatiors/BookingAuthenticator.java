package by.kanarski.booking.mail.authenticatiors;

import by.kanarski.booking.managers.AuthenticationManager;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

public class BookingAuthenticator extends Authenticator {

    @Override
    protected PasswordAuthentication getPasswordAuthentication() {
        String userName = AuthenticationManager.USER_NAME.get();
        String password = AuthenticationManager.PASSWORD.get();
        PasswordAuthentication passwordAuthentication = new PasswordAuthentication(userName, password);
        return passwordAuthentication;
    }
}
