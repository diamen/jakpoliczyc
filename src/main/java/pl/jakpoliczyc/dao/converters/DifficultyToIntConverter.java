package pl.jakpoliczyc.dao.converters;

import pl.jakpoliczyc.dao.common.Difficulty;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.stream.Stream;

@Converter
public class DifficultyToIntConverter implements AttributeConverter<Difficulty, Integer> {

    @Override
    public Integer convertToDatabaseColumn(Difficulty attribute) {
        return attribute.getDbCounterpart();
    }

    @Override
    public Difficulty convertToEntityAttribute(Integer dbData) {
        return Stream.of(Difficulty.values())
                .filter(difficulty -> difficulty.getDbCounterpart() == dbData)
                .findFirst().orElse(null);
    }
}
