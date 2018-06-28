package pl.jakpoliczyc.web.controllers

import com.google.common.collect.Lists
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import pl.jakpoliczyc.dao.entities.quiz.Quiz
import pl.jakpoliczyc.dao.services.quiz.QuizService
import pl.jakpoliczyc.web.WebTestUtils
import pl.jakpoliczyc.web.dto.quiz.QuestionWithUserAnswer
import pl.jakpoliczyc.web.dto.quiz.QuizResult
import spock.lang.Specification
import spock.lang.Subject
import spock.lang.Title

import static javax.servlet.http.HttpServletResponse.SC_NOT_FOUND
import static javax.servlet.http.HttpServletResponse.SC_OK
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get

@Title("""
Tests for QuizController class
which allows to test all endpoint methods
related to quizzes logic
""")
@Subject(KahootController.class)
class QuizControllerSpec extends Specification {

    QuizService quizService = Mock(QuizService)

    QuizController controller = new QuizController()

    MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller).build()

    def setup() {
        controller.quizService = quizService
    }

    def "Should controller return 404 status when quiz was not found"() {
        given: "Service should not return a value"
        quizService.getQuiz(1) >> Optional.empty()

        when: "A request is performed"
        def response = mockMvc.perform(get("/quiz/1")).andReturn().response

        then: "Returned response equals 404"
        response.status == SC_NOT_FOUND
    }

    def "Should controller return 200 status when quiz is found"() {
        given: "Service should return a value"
        quizService.getQuiz(1) >> Optional.of(Mock(Quiz))

        when: "A request is performed"
        def response = mockMvc.perform(get("/quiz/1")).andReturn().response

        then: "Returned response equals 200"
        response.status == SC_OK
    }

    def "Should controller return 404 status when quizResult was not found"() {
        given: "Service should not return a value"
        quizService.getResult(_) >> Optional.empty()
        and: "quizResult should be sent to the request"
        def quizResult = new QuizResult()
        quizResult.setId(1)
        quizResult.setQuestions(Lists.newArrayList(Mock(QuestionWithUserAnswer)))

        when: "A request is performed"
        def response = mockMvc.perform(get("/quizresult")
                .contentType(MediaType.APPLICATION_JSON)
                .content(WebTestUtils.generateRequest(quizResult)))
                .andReturn().response

        then: "Returned response equals 404"
        response.status == SC_NOT_FOUND
    }

    def "Should controller return 200 status when quizResult is found"() {
        given: "Service should return a value"
        quizService.getResult(_) >> Optional.of(Mock(Quiz))

        and: "quizResult should be sent to the request"
        def quizResult = new QuizResult()
        quizResult.setId(1)
        quizResult.setQuestions(Lists.newArrayList(Mock(QuestionWithUserAnswer)))

        when: "A request is performed"
        def response = mockMvc.perform(get("/quizresult")
                .contentType(MediaType.APPLICATION_JSON)
                .content(WebTestUtils.generateRequest(quizResult)))
                .andReturn().response

        then: "Returned response equals 200"
        response.status == SC_OK
    }

}