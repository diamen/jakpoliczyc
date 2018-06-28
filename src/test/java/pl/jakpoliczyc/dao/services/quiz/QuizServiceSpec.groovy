package pl.jakpoliczyc.dao.services.quiz

import com.github.springtestdbunit.annotation.DbUnitConfiguration
import com.github.springtestdbunit.dataset.AbstractDataSetLoader
import org.dbunit.dataset.IDataSet
import org.dbunit.dataset.builder.DataSetBuilder
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.io.Resource
import pl.jakpoliczyc.dao.JakPoliczycDbSpec
import pl.jakpoliczyc.dao.entities.quiz.Quiz
import pl.jakpoliczyc.web.dto.quiz.QuestionWithUserAnswer
import pl.jakpoliczyc.web.dto.quiz.QuizResult
import pl.jakpoliczyc.web.dto.quiz.UserAnswer
import spock.lang.Subject
import spock.lang.Title

@Title("""
Tests for QuizService class
which allows to test database
operations related with Quiz, Question
and Answer entities
""")
@Subject(QuizService.class)

@DbUnitConfiguration(dataSetLoader = Loader.class)
class QuizServiceSpec extends JakPoliczycDbSpec {

    @Autowired
    QuizService quizService

    /**
     * Database test data
     */
    static class Loader extends AbstractDataSetLoader {
        @Override
        protected IDataSet createDataSet(Resource resource) throws Exception {
            return new DataSetBuilder()

                    .newRow("quiz").with("id", 1).with("difficulty", 1).with("title", "any").add()

                    .newRow("question").with("id", 1).with("title", "any").with("exclamation", "any").with("quiz_id", 1).add()

                    .newRow("question").with("id", 2).with("title", "any").with("exclamation", "any").with("quiz_id", 1).add()

                    .newRow("answer").with("id", 1).with("correct", false)
                    .with("content", "any").with("question_id", 1).add()

                    .newRow("answer").with("id", 2).with("correct", true)
                    .with("content", "any").with("question_id", 1).add()

                    .newRow("answer").with("id", 3).with("correct", false)
                    .with("content", "any").with("question_id", 2).add()

                    .newRow("answer").with("id", 4).with("correct", true)
                    .with("content", "any").with("question_id", 2).add()

                    .build()
        }
    }

    def "Should service hide information about correctness of the answer"() {
        given: "Quiz found by service"
        Quiz quiz = quizService.getQuiz(1).get()

        when: "Collection of the answers are retrieved"
        def allAnswers = quiz.questions.collect { it.answers }.flatten()
        def answersWithoutCorrectness = allAnswers.findAll { it.correct == null }

        then: "All the answers have not information about their correctness"
        answersWithoutCorrectness.size() == allAnswers.size()
        answersWithoutCorrectness.size() == 4
    }

    def "Should quiz result has information about correctness"() {
        given: "Quiz result sent by client which contains his answers"
        QuizResult clientQuizResult = prepareQuizResult()

        when: "Quiz result extended by information about correctness is returned"
        QuizResult serverQuizResult = quizService.getResult(clientQuizResult).get()
        def serverAnswers = serverQuizResult.getQuestions().collect { it.quizAnswers }.flatten().findAll {
            it.correct != null
        }

        then: "All the answers has information about correctness"
        serverAnswers.size() == 4
    }

    def "Should service hide exclamation of the question"() {
        given: "Quiz found by service"
        Quiz quiz = quizService.getQuiz(1).get()

        when: "Collection of the questions are retrieved"
        def allQuestions = quiz.questions;
        def questionsWithoutExclamation = allQuestions.findAll { it.exclamation == null }

        then: "All the answers have not information about their correctness"
        questionsWithoutExclamation.size() == allQuestions.size()
        questionsWithoutExclamation.size() == 2
    }

    def "Should quiz result has information about questions' exclamations"() {
        given: "Quiz result sent by client which contains his answers"
        QuizResult clientQuizResult = prepareQuizResult()


        when: "Quiz result extended by information about exlamation is returned"
        QuizResult serverQuizResult = quizService.getResult(clientQuizResult).get()
        def serverQuestions = serverQuizResult.getQuestions().findAll {
            it.exclamation != null
        }

        then: "All the questions has information about exclamation"
        serverQuestions.size() == 2
    }

    def prepareQuizResult() {
        Quiz quiz = quizService.getQuiz(1).get()

        /**
         * First question
         */
        final UserAnswer userAnswerForFirstQuestion = new UserAnswer()
        userAnswerForFirstQuestion.setAnswer("A")

        final QuestionWithUserAnswer questionWithUserAnswer1 = new QuestionWithUserAnswer()
        def answersForFirstQuestion = quiz.getQuestions().findAll { it.id == 1 }.collect { it.answers }.flatten()
        questionWithUserAnswer1.setId(1)
        questionWithUserAnswer1.setQuizAnswers(answersForFirstQuestion)
        questionWithUserAnswer1.setUserAnswers([userAnswerForFirstQuestion])

        /**
         * Second question
         */
        final UserAnswer userAnswerForSecondQuestion = new UserAnswer()
        userAnswerForSecondQuestion.setAnswer("C")

        final QuestionWithUserAnswer questionWithUserAnswer2 = new QuestionWithUserAnswer()
        def answersForSecondQuestion = quiz.getQuestions().findAll { it.id == 2 }.collect { it.answers }.flatten()
        questionWithUserAnswer2.setId(2)
        questionWithUserAnswer2.setQuizAnswers(answersForSecondQuestion)
        questionWithUserAnswer2.setUserAnswers([userAnswerForSecondQuestion])

        QuizResult clientQuizResult = new QuizResult()
        clientQuizResult.setId(1)
        clientQuizResult.setQuestions([questionWithUserAnswer1, questionWithUserAnswer2])

        return clientQuizResult;
    }

}