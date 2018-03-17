package pl.jakpoliczyc.dao.services

import pl.jakpoliczyc.dao.entities.Menu
import pl.jakpoliczyc.dao.services.impl.MenuTreeTraverserImpl
import spock.lang.Ignore
import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Subject

@Subject(MenuTreeTraverser.class)
class MenuTreeTraverserSpec extends Specification {

    @Shared
    MenuTreeTraverser menuTreeTraverser

    def setupSpec() {
        menuTreeTraverser = new MenuTreeTraverserImpl()
    }

    @Ignore
    def "Menu"() {
        /*
            m1 is root
            m1 has 2 children
            m1 has 3 descendants
            m1_1 has 1 sibling
         */
        def m1 = new Menu()
        def m1_1 = new Menu()
        m1_1.setName("Z")
        def m1_1_1 = new Menu()
        def m1_2 = new Menu()
        m1_2.setName("A")
        m1_1_1.setSubmenus([])
        m1_2.setSubmenus([])
        m1_1.setSubmenus([m1_1_1])
        m1.setSubmenus([m1_1, m1_2])
        return m1
    }

    def "Should correct number of descendants be returned"() {
        given: "A menu with 3 descendants"
        def menu = Menu()

        when: "Descendants are returned"
        def result = menuTreeTraverser.findDescendants(menu)

        then: "The number of descendants equals 3 + root == 4"
        result.size() == 4
    }

    def "Should correct number of children callbacks be called"() {
        given: "A menu with 2 children"
        def menu = Menu()

        and: "A callback with counter"
        def counter = 0
        def callback = { m -> counter++ }

        when: "childCallback method is called"
        menuTreeTraverser.childCallback(menu, callback)

        then: "Callback should be called 2 times"
        counter == 2
    }

    def "Should correct number of sibling callbacks be called"() {
        given: "A menu with 1 sibling"
        def menu = Menu().getSubmenus()[0]

        and: "A callback with counter"
        def counter = 0
        def callback = { m -> counter++ }

        when: "siblingCallback method is called"
        menuTreeTraverser.childCallback(menu, callback)

        then: "Callback should be called 1 time"
        counter == 1
    }

    def "Should submenus be sorted correctly"() {
        given: "Unsorted menu"
        def menu = Menu()

        and: "A comparator"
        def comparator = { m1, m2 -> m1.name <=> m2.name }

        when: "Collection of Menu is passed to #sortEachLevel"
        menuTreeTraverser.sortEachLevel([menu], comparator)

        then: "Submenus should be sorted alphabetically"
        menu.getSubmenus()[0].name == 'A'
        menu.getSubmenus()[1].name == 'Z'
    }

}