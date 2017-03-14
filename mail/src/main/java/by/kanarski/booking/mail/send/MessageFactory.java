package by.kanarski.booking.mail.send;

import org.springframework.core.io.InputStreamSource;

import javax.mail.internet.MimeMessage;

/**
 * @author Dzmitry Kanarski
 * @version 1.0
 */

public interface MessageFactory {

    MessageFactory setFrom(String from);

    MessageFactory setTo(String to);

    MessageFactory setSubject(String subject);

    MessageFactory setText(String text);

    MessageFactory addImage(String imageName, InputStreamSource image);

    MimeMessage create();

}
