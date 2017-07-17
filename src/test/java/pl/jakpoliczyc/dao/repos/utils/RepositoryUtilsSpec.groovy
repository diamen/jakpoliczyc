package pl.jakpoliczyc.dao.repos.utils

import org.springframework.data.domain.Sort
import org.springframework.data.domain.Sort.Direction
import org.springframework.data.domain.Sort.Order
import pl.jakpoliczyc.dao.entities.Article
import pl.jakpoliczyc.dao.entities.Storage
import pl.jakpoliczyc.dao.entities.Story
import spock.lang.Specification
import spock.lang.Subject
import spock.lang.Title

@Title("""
Tests for RepositoryUtils class,
which is a helper util class for repository
layer classes. It can for example prepare
valid query Strings
""")
@Subject(RepositoryUtils)
class RepositoryUtilsSpec extends Specification {

    def "Should property be found in embedded class"() {
        given: "Property we are looking for and Sort instance"
        def embeddedClassName = Story.class.getSimpleName().toLowerCase()
        def property = "title"
        def direction = Direction.ASC
        final Sort sort = new Sort(new Order(direction, property))

        when: "Sort is converted to String query"
        def orderQuery = RepositoryUtils.sortToStringQuery(sort, Article.class)

        then: "String query is a valid JPA query"
        "ORDER BY e.$embeddedClassName.$property $direction".toString() == orderQuery
    }

    def "Should property be found directly in Entity class"() {
        given: "Property we are looking for and Sort instance"
        def property = "addedDate"
        def direction = Direction.DESC
        final Sort sort = new Sort(new Order(direction, property))

        when: "Sort is converted to String query"
        def orderQuery = RepositoryUtils.sortToStringQuery(sort, Storage.class)

        then: "String query is a valid JPA query"
        "ORDER BY e.$property $direction".toString() == orderQuery
    }

    def "Should order query be empty without sort"() {
        expect: "empty query"
        "" == RepositoryUtils.sortToStringQuery(null, Article.class)
    }

    def "Should order query be empty when property is not found"() {
        expect: "empty query"
        "" == RepositoryUtils.sortToStringQuery(new Sort(new Order(Direction.ASC, "unknown")), Article.class)
    }

    def "Should name of column be parsed correctly for native query"() {
        when: "Property is parsed to a column name"
        def columnName = RepositoryUtils.getPropertyForNative(input, Article.class)

        then: "Returned value is a proper column name"
        output == columnName

        where: "sample data"
        input       ||  output
        "addedDate" ||  "ADDED_DATE"
        "title"     ||  "TITLE"
    }

}
