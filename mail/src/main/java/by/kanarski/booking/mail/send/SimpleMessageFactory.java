package by.kanarski.booking.mail.send;

import org.hibernate.pretty.MessageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamSource;
import org.springframework.mail.MailSender;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.HashMap;

/**
 * @author Dzmitry Kanarski
 * @version 1.0
 */

@Component
public class SimpleMessageFactory implements MessageFactory {

    @Autowired
    private JavaMailSender mailSender;

    private MimeMessage mimeMessage;
    private MimeMessageHelper messageHelper;
    private String from;
    private String to;
    private String suject;
    private String text;
    private HashMap<String, InputStreamSource> images;

    public SimpleMessageFactory() throws MessagingException {
        mimeMessage = mailSender.createMimeMessage();
        messageHelper = new MimeMessageHelper(mimeMessage, true);
    }

    @Override
    public MessageFactory setFrom(String from) {

        return this;
    }

    @Override
    public MessageFactory setTo(String to) {
        messageHelper.setTo(to);
        return this;
    }

    @Override
    public MessageFactory setSubject(String subject) {
        messageHelper.setSubject(subject);
        return this;
    }

    @Override
    public MessageFactory setText(String text) {
        messageHelper.setText(text, true);
        return this;
    }

    @Override
    public MessageFactory addImage(String imageName, InputStreamSource image) {
        messageHelper.addAttachment(imageName, image);
        return this;
    }

    @Override
    public MimeMessage create() {
        return null;
    }
}
