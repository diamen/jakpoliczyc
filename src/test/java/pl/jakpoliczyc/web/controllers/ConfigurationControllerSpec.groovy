package pl.jakpoliczyc.web.controllers

import groovy.json.JsonSlurper
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import pl.jakpoliczyc.dao.entities.Configuration
import pl.jakpoliczyc.dao.services.ConfigurationService
import pl.jakpoliczyc.web.WebTestUtils
import pl.jakpoliczyc.web.dto.ConfigurationDto
import spock.lang.Specification
import spock.lang.Subject
import spock.lang.Title

import static javax.servlet.http.HttpServletResponse.*
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post

@Title("""
Tests for ConfigurationController class
which allows to test all endpoint methods
""")
@Subject(ConfigurationController.class)
class ConfigurationControllerSpec extends Specification {

    ConfigurationService configurationService = Mock(ConfigurationService)

    ConfigurationController controller = new ConfigurationController()

    MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller).build()

    def setup() {
        controller.configurationService = configurationService
    }

    def "Should controller return 404 status when no configuration were found"() {
        given: "Service should not return a value"
        configurationService.findAll() >> []

        when: "A request is performed"
        def response = mockMvc.perform(get("/configuration")).andReturn().response

        then: "Returned response equals 404"
        response.status == SC_NOT_FOUND
    }

    def "Should controller return 200 status when configuration found"() {
        given: "Service should return values"
        configurationService.findAll() >> [Mock(Configuration), Mock(Configuration)]

        when: "A request is performed"
        def response = mockMvc.perform(get("/configuration")).andReturn().response
        def content = new JsonSlurper().parseText(response.contentAsString)

        then: "Returned response equals 200"
        response.status == SC_OK
        ((List<Configuration>) content).size() == 2
    }

    def "Should controller return 201 status when configuration is inserted"() {
        given: "Data to insert"
        ConfigurationDto configurationDto = new ConfigurationDto()
        configurationDto.setKeyy("keyy")
        configurationDto.setValue("value")
        String requestJson = WebTestUtils.generateRequest([configurationDto])

        when: "A request is performed"
        def response = mockMvc.perform(post("/configuration")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJson))
                .andReturn().response

        then: "Returned response equals 201"
        response.status == SC_CREATED
    }

}