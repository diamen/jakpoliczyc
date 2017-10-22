package pl.jakpoliczyc.dao.converters

import pl.jakpoliczyc.dao.common.KahootDifficulties
import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Subject
import spock.lang.Title

@Title("""
Tests for KahootEnumToIntConverter class, 
whose purpose is to convert KahootDifficulties enum into 
int values and the other way around
""")
@Subject(KahootEnumToIntConverter.class)
class KahootEnumToIntConverterSpec extends Specification {

    @Shared
    KahootEnumToIntConverter converter

    def setupSpec() {
        converter = new KahootEnumToIntConverter()
    }

    def "KahootEnumToIntConverter should convert given Enum to appropriate int value"() {
        given: "An Enum"
        KahootDifficulties kahootDifficulty = KahootDifficulties.MIDDLE
        and: "expected int"
        int expectedOutput = kahootDifficulty.MIDDLE.getDbCounterpart()

        when: "Enum is converted to int"
        int returnedOutput = converter.convertToDatabaseColumn(kahootDifficulty)

        then: "Returned value equals expected"
        returnedOutput == expectedOutput
    }

    def "KahootEnumToInt should convert given int to appropriate Enum value"() {
        given: "An int"
        int input = KahootDifficulties.MIDDLE.getDbCounterpart()
        and: "expected Enum"
        KahootDifficulties expectedOutput = KahootDifficulties.MIDDLE

        when: "int is converted to Enum"
        KahootDifficulties returnedOutput = converter.convertToEntityAttribute(input)

        then: "Returned value equals expected"
        returnedOutput == expectedOutput
    }

}