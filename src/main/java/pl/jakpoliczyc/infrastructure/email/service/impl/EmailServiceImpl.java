package pl.jakpoliczyc.infrastructure.email.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import pl.jakpoliczyc.infrastructure.email.service.EmailService;

import javax.mail.*;
import javax.mail.internet.*;
import java.io.IOException;
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
    public void sendFromToEmail(final String from, final String subject, String content) throws MessagingException {
        final Session session = Session.getDefaultInstance(initAndCreateProperties(), null);

        final MimeMessage mailMessage = createFromToMessage(session, from, subject, content);

        sendMessage(session, mailMessage);
    }

    @Override
    public void sendMultiEmail(List<String> emailAddressesTo, String subject, String content) throws MessagingException, IOException {
        final Session session = Session.getDefaultInstance(initAndCreateProperties(), null);

        final MimeMessage mailMessage = createMultiMessage(session, emailAddressesTo, subject, content);

        sendMessage(session, mailMessage);
    }

    protected Properties initAndCreateProperties() {
        Properties props = System.getProperties();
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", smtpHost);
        props.put("mail.smtp.user", smtpUsername);
        props.put("mail.smtp.password", smtpPassword);
        props.put("mail.smtp.port", smtpPort);
        props.put("mail.smtp.auth", "true");
        props.put("mail.debug", "true");
        return props;
    }

    protected MimeMessage createFromToMessage(final Session session, final String from, final String subject, String content) throws MessagingException {
        MimeMessage mailMessage = new MimeMessage(session);
        mailMessage.setFrom(new InternetAddress(smtpUsername));
        mailMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(smtpUsername));

        mailMessage.setSubject("[Klient JakPoliczyć] " + subject);
        content = String.format("[Wiadomość wysłana przez %s]\n\n%s", from, content);

        mailMessage.setText(content);
        mailMessage.setHeader("Content-Type", "text/plain; charset=UTF-8");

        return mailMessage;
    }

    protected MimeMessage createMultiMessage(final Session session, final List<String> emailAddressesTo,
                                             final String subject, String content) throws MessagingException, IOException {
        MimeMessage mailMessage = new MimeMessage(session);
        mailMessage.setFrom(new InternetAddress(smtpUsername));
        mailMessage.setRecipients(Message.RecipientType.TO,
                emailAddressesTo.stream().map(to -> {
                    try {
                        return new InternetAddress(to);
                    } catch (AddressException e) {
                        log.error(e.getMessage());
                    }
                    return null;
                }).toArray(Address[]::new));
        mailMessage.setSubject("[JakPoliczyć] " + subject);

        MimeMultipart mimeMultipart = new MimeMultipart();
        final MimeBodyPart htmlPart = new MimeBodyPart();
        final String cidImage = "image";
        content =
                "<html>" +
                        content +
                        "<br/>" +
                        "<div>" +
                        "<img src=\"cid:" + cidImage + "\"/>" +
                        "</div>" +
                        "<div style=\"width: 354px; display: inline-block; text-align: center;\">" +
                        "Zespół <strong>JakPoliczyć</strong>" +
                        "<br><a href=\"http://www.JakPoliczyc.pl\">http://www.JakPoliczyc.pl</a>" +
                        "</div>" +
                        "</html>";
        htmlPart.setText(content, "UTF-8", "html");

        final MimeBodyPart imagePart = new MimeBodyPart();
        imagePart.attachFile(getClass().getResource("/logo320w.png").getFile());

        imagePart.setContentID("<" + cidImage + ">");
        imagePart.setDisposition(MimeBodyPart.INLINE);
        mimeMultipart.addBodyPart(htmlPart);
        mimeMultipart.addBodyPart(imagePart);

        mailMessage.setContent(mimeMultipart);

        return mailMessage;
    }

    protected void sendMessage(final Session session, final MimeMessage mailMessage) throws MessagingException {
        Transport transport = session.getTransport(protocol);
        transport.connect(smtpHost, smtpUsername, smtpPassword);
        transport.sendMessage(mailMessage, mailMessage.getAllRecipients());
        transport.close();
    }

}
