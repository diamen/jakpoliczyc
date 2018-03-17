package pl.jakpoliczyc.dao.converters

import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Subject
import spock.lang.Title

@Title("""
Tests for UrlToStringCovnerter class, 
whose purpose is to convert raw Strings into URL Java objects
and the other way around
""")
@Subject(UrlToStringConverter.class)
class UroToStringConverterSpec extends Specification {

    @Shared
    UrlToStringConverter converter
    String urlAsString = "http://www.google.pl"

    def setupSpec() {
        converter = new UrlToStringConverter()
    }

    def "UrlToStringConverter should convert given String to an URL object"() {
        expect: "that converted value is the same as given String"
        urlAsString == converter.convertToEntityAttribute(urlAsString).toExternalForm()
    }

    def "UrlToStringConverter should convert given URL to a String"() {
        given: "An URL object"
        URL url = new URL(urlAsString)

        when: "URL object is converted to a database column"
        String returnedUrl = converter.convertToDatabaseColumn(url)

        then: "Converted URL is a valid String"
        urlAsString == returnedUrl
    }

    def "Should an exception be thrown when given String is malformed"() {
        given: "A malformed URL"
        String incorrectString = "wwwjpg.pl"

        when: "String is converted to an URL object"
        converter.convertToEntityAttribute(incorrectString)

        then: "an exception will be thrown"
        thrown(RuntimeException)
    }

}
