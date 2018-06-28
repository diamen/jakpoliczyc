package pl.jakpoliczyc.dao.converters

import pl.jakpoliczyc.dao.common.Difficulty
import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Subject
import spock.lang.Title

@Title("""
Tests for DifficultyToIntConverter class, 
whose purpose is to convert Difficulty enum into 
int values and the other way around
""")
@Subject(DifficultyToIntConverter.class)
class DifficultyToIntConverterSpec extends Specification {

    @Shared
    DifficultyToIntConverter converter

    def setupSpec() {
        converter = new DifficultyToIntConverter()
    }

    def "DifficultyToIntConverter should convert given Enum to appropriate int value"() {
        given: "An Enum"
        Difficulty kahootDifficulty = Difficulty.MIDDLE
        and: "expected int"
        int expectedOutput = kahootDifficulty.MIDDLE.getDbCounterpart()

        when: "Enum is converted to int"
        int returnedOutput = converter.convertToDatabaseColumn(kahootDifficulty)

        then: "Returned value equals expected"
        returnedOutput == expectedOutput
    }

    def "DifficultyToIntConverter should convert given int to appropriate Enum value"() {
        given: "An int"
        int input = Difficulty.MIDDLE.getDbCounterpart()
        and: "expected Enum"
        Difficulty expectedOutput = Difficulty.MIDDLE

        when: "int is converted to Enum"
        Difficulty returnedOutput = converter.convertToEntityAttribute(input)

        then: "Returned value equals expected"
        returnedOutput == expectedOutput
    }

}