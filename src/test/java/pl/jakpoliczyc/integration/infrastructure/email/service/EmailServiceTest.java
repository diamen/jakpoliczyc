package pl.jakpoliczyc.integration.infrastructure.email.service;

import com.icegreen.greenmail.util.GreenMail;
import com.icegreen.greenmail.util.ServerSetup;
import org.assertj.core.api.SoftAssertions;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import pl.jakpoliczyc.infrastructure.email.service.EmailService;
import pl.jakpoliczyc.integration.web.WebTestConfig;

import javax.mail.Message;
import javax.mail.MessagingException;
import java.util.Arrays;
import java.util.List;

@TestPropertySource(properties = {
        "smtp.host=localhost",
        "smtp.port=25",
        "smtp.username=any",
        "smtp.password=any"
})
@ContextConfiguration(classes = { EmailServiceTest.Config.class})
public class EmailServiceTest extends WebTestConfig {

    @Autowired
    private EmailService emailService;

    private GreenMail testSmtp;

    @Configuration
    static class Config {
        @Bean
        public static PropertySourcesPlaceholderConfigurer propertiesResolver() {
            return new PropertySourcesPlaceholderConfigurer();
        }
    }

    @Before
    public void init() {
        testSmtp = new GreenMail(ServerSetup.SMTP);
        testSmtp.start();
    }

    @Test
    public void shouldEmailBeSentCorrectly() throws MessagingException {
        // given
        List<String> receivers = Arrays.asList("mstopa23@gmail.com", "stopa23@interia.eu");
        final String title = "TEST TITLE";
        final String content = "TEST CONTENT";

        // when
        emailService.sendEmail(null, receivers, title, content);

        // then
        Message[] messages = testSmtp.getReceivedMessages();
        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(messages[0].getAllRecipients().length).isEqualTo(receivers.size());
        softly.assertThat(messages[0].getSubject()).isEqualTo(title);
        softly.assertAll();
    }

    @After
    public void destroy() {
        testSmtp.stop();
    }

}
