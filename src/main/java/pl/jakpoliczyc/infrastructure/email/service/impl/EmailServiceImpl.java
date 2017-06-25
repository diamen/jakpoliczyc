package pl.jakpoliczyc.infrastructure.email.service.impl;

import org.h2.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private Logger log = LoggerFactory.getLogger(EmailService.class);

    private static final String protocol = "smtp";

    @Override
    public void sendEmail(String from, List<String> emailAddressesTo, String subject, String content) throws MessagingException {
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
                    log.error(e1.getMessage());
                }
                    return null;
                }).toArray(Address[]::new));
        mailMessage.setSubject("[JakPoliczyć] " + subject);
        if (!StringUtils.isNullOrEmpty(from)) {
            content = "[Wiadomość wysłana przez " + from + "]\n\n" + content;
        }
        mailMessage.setText(content);
        if (StringUtils.isNullOrEmpty(from)) {
            mailMessage.setHeader("Content-Type", "text/html");
        }
        Transport transport = session.getTransport(protocol);
        transport.connect(smtpHost, smtpUsername, smtpPassword);
        transport.sendMessage(mailMessage, mailMessage.getAllRecipients());
        transport.close();
    }

}
