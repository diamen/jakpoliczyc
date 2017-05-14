package pl.jakpoliczyc.dao.converters;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import pl.jakpoliczyc.dao.converters.UrlToStringConverter;

import java.net.MalformedURLException;
import java.net.URL;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@RunWith(JUnit4.class)
public class UrlToStringCovnerterTestUnit {

    private UrlToStringConverter converter = new UrlToStringConverter();
    private final String urlAsString = "http://www.google.pl";

    @Test
    public void shouldConvertFromStringToUrl() {
        // given
        // urlAsString

        // when
        URL url = converter.convertToEntityAttribute(urlAsString);

        // then
        assertThat(urlAsString).isEqualTo(url.toExternalForm());
    }

    @Test
    public void shouldConvertFromUrlToString() throws MalformedURLException {
        // given
        URL url = new URL(urlAsString);

        // when
        String returnedUrl = converter.convertToDatabaseColumn(url);

        // then
        assertThat(urlAsString).isEqualTo(returnedUrl);
    }

    @Test
    public void shouldThrowExceptionWhenUrlIsNotCorrect() {
        // given
        String uncorrectString = "wwwjpg.pl";

        // expect
        assertThatThrownBy(() -> converter.convertToEntityAttribute(uncorrectString)).isInstanceOf(RuntimeException.class);
    }

}
