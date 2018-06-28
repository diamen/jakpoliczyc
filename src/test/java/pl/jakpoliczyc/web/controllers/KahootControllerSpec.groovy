package pl.jakpoliczyc.web.controllers

import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import pl.jakpoliczyc.dao.common.Difficulty
import pl.jakpoliczyc.dao.entities.Kahoot
import pl.jakpoliczyc.dao.services.KahootService
import pl.jakpoliczyc.web.WebTestUtils
import spock.lang.Specification
import spock.lang.Subject
import spock.lang.Title

import static javax.servlet.http.HttpServletResponse.*
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete

@Title("""
Tests for KahootController class
which allows to test all endpoint methods
""")
@Subject(KahootController.class)
class KahootControllerSpec extends Specification {

    KahootService kahootService = Mock(KahootService)

    KahootController controller = new KahootController()

    MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller).build()

    def setup() {
        controller.kahootService = kahootService
    }

    def "Should controller return 404 status when no kahoot were found"() {
        given: "Service should not return a value"
        kahootService.findAll() >> []

        when: "A request is performed"
        def response = mockMvc.perform(get("/kahoot")).andReturn().response

        then: "Returned response equals 404"
        response.status == SC_NOT_FOUND
    }

    def "Should controller return 200 status when kahoot is found"() {
        given: "Service should return a value"
        kahootService.findAll() >> [Mock(Kahoot), Mock(Kahoot)]

        when: "A request is performed"
        def response = mockMvc.perform(get("/kahoot")).andReturn().response

        then: "Returned response equals 200"
        response.status == SC_OK
    }

    def "Should controller return 201 status when kahoot is inserted"() {
        given: "Data to replace"
        Kahoot kahoot = new Kahoot()
        kahoot.setTitle("title")
        kahoot.setDifficulty(Difficulty.VERY_DIFFICULT)
        kahoot.setUrl(new URL("https://www.google.pl"))
        String request = WebTestUtils.generateRequest(kahoot)

        when: "A request is performed"
        def response = mockMvc.perform(post("/kahoot")
                .contentType(MediaType.APPLICATION_JSON)
                .content(request))
                .andReturn().response

        then: "Returned response equals 201"
        response.status == SC_CREATED
    }

    def "Should controller return 200 status when kahoot is deleted"() {
        expect: "Returned response equals 200"
        mockMvc.perform(delete("/kahoot/1")).andReturn().response.status == SC_OK
    }

}