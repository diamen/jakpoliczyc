package pl.jakpoliczyc.dao.repos.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort;
import org.springframework.util.StringUtils;
import pl.jakpoliczyc.infrastructure.email.service.EmailService;

import javax.persistence.Embedded;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class RepositoryUtils {

    private static final Logger log = LoggerFactory.getLogger(EmailService.class);

    public static final String PERSISTENCE_UNIT_NAME = "JakPoliczyc";

    private RepositoryUtils() {
    }

    public static String sortToStringNativeQuery(final Sort sort) {
        if (sort == null) {
            return "";
        }

        final String attributes = StringUtils.collectionToCommaDelimitedString(
                StreamSupport.stream(sort.spliterator(), false)
                    .map(e -> e.getProperty() + " " + e.getDirection())
                .collect(Collectors.toList())
        );

        return String.format("ORDER BY %s", attributes);
    }

    public static String sortToStringQuery(final Sort sort, final Class entity) {
        if (sort == null) {
            return "";
        }

        final String attributes = StringUtils.collectionToCommaDelimitedString(
                StreamSupport.stream(sort.spliterator(), false)
                        .map(e -> {
                            try {
                                return "e." + getProperty(e.getProperty(), entity) + " " + e.getDirection();
                            } catch (UnknownPropertyForEntityClassException exception) {
                                return "";
                            }
                        })
                        .collect(Collectors.toList())
        );

        return attributes.length() == 0 ? "" : String.format("ORDER BY %s", attributes);
    }

    private static String getProperty(final String property, final Class entity) throws UnknownPropertyForEntityClassException {
        final Field[] fields = entity.getDeclaredFields();
        Optional<Field> optionalField = Arrays.stream(fields)
                .filter(e -> e.getName().equals(property)).findAny();

        if (optionalField.isPresent()) {
            return optionalField.get().getName();
        }

        optionalField = Arrays.stream(fields)
                .filter(e -> e.getAnnotation(Embedded.class) != null).findAny();

        if (optionalField.isPresent()) {
            Field[] embeddedFields;
            try {
                embeddedFields = Class.forName(optionalField.get().getType().getName()).getDeclaredFields();
            } catch (ClassNotFoundException e) {
                log.error(e.getMessage());
                throw new UnknownPropertyForEntityClassException();
            }

            final Optional<Field> embeddedField = Arrays.stream(embeddedFields)
                    .filter(e -> e.getName().equals(property)).findAny();

            if (embeddedField.isPresent()) {
                return optionalField.get().getName() + "." + embeddedField.get().getName();
            }
        }

        throw new UnknownPropertyForEntityClassException();
    }

}
