package pl.jakpoliczyc.dao.converters;

import pl.jakpoliczyc.dao.common.KahootDifficulties;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.stream.Stream;

@Converter
public class KahootEnumToIntConverter implements AttributeConverter<KahootDifficulties, Integer> {

    @Override
    public Integer convertToDatabaseColumn(KahootDifficulties attribute) {
        return attribute.getDbCounterpart();
    }

    @Override
    public KahootDifficulties convertToEntityAttribute(Integer dbData) {
        return Stream.of(KahootDifficulties.values())
                .filter(kahoot -> kahoot.getDbCounterpart() == dbData)
                .findFirst().orElse(null);
    }
}
