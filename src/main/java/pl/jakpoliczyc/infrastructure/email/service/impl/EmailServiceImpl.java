package pl.jakpoliczyc.infrastructure.email.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import pl.jakpoliczyc.infrastructure.email.service.EmailService;

import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.List;
import java.util.Properties;

@Component
public class EmailServiceImpl implements EmailService {

    @Value("${smtp.host}")
    private String smtpHost;
    @Value("${smtp.port}")
    private String smtpPort;
    @Value("${smtp.username}")
    private String smtpUsername;
    @Value("${smtp.password}")
    private String smtpPassword;

    private static final String protocol = "smtp";

    @Override
    public void sendEmail(List<String> emailAddressesTo, String subject, String content) throws MessagingException {

        Properties props = System.getProperties();
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", smtpHost);
        props.put("mail.smtp.user", smtpUsername);
        props.put("mail.smtp.password", smtpPassword);
        props.put("mail.smtp.port", smtpPort);
        props.put("mail.smtp.auth", "true");
        props.put("mail.debug", "true");

        Session session = Session.getDefaultInstance(props, null);
        session.setDebug(true); // TODO usunąć

        MimeMessage mailMessage = new MimeMessage(session);
        mailMessage.setFrom(new InternetAddress(smtpUsername));
        mailMessage.setRecipients(Message.RecipientType.TO,
                emailAddressesTo.stream().map(e -> { try {
                        return new InternetAddress(e);
                    } catch (AddressException e1) {
                        // TODO logger
                    }
                    return null;
                }).toArray(Address[]::new));
        mailMessage.setSubject(subject);
        mailMessage.setText(content);
        mailMessage.setHeader("Content-Type", "text/html");
        Transport transport = session.getTransport(protocol);
        transport.connect(smtpHost, smtpUsername, smtpPassword);
        transport.sendMessage(mailMessage, mailMessage.getAllRecipients());
        transport.close();
    }

}
