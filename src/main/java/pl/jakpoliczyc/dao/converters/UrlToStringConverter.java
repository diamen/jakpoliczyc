package pl.jakpoliczyc.dao.converters;

import org.h2.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.net.MalformedURLException;
import java.net.URL;

@Converter
public class UrlToStringConverter implements AttributeConverter<URL, String> {

    private Logger log = LoggerFactory.getLogger(UrlToStringConverter.class);

    @Override
    public String convertToDatabaseColumn(URL attribute) {
        if (attribute == null) {
            return null;
        }
        return attribute.toExternalForm();
    }

    @Override
    public URL convertToEntityAttribute(String dbData) {
        if (StringUtils.isNullOrEmpty(dbData)) {
            return null;
        }

        try {
            return new URL(dbData);
        } catch (MalformedURLException e) {
            log.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }

}
