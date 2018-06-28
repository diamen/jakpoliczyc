package pl.jakpoliczyc.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.jakpoliczyc.dao.entities.quiz.Quiz;
import pl.jakpoliczyc.dao.services.quiz.QuizService;
import pl.jakpoliczyc.web.dto.quiz.QuizResult;

import java.util.Optional;

@RestController
public class QuizController {

    @Autowired
    private QuizService quizService;

    @GetMapping(value = "/quiz/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Quiz> getQuiz(@PathVariable long id) {
        return mapOptionalToResponseEntity(quizService.getQuiz(id), "None quiz found");
    }

    @GetMapping(value = "/quizresult", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<QuizResult> getResult(@RequestBody QuizResult quizResult) {
        return mapOptionalToResponseEntity(quizService.getResult(quizResult), "None quiz result found");
    }

    private <T> ResponseEntity<T> mapOptionalToResponseEntity(final Optional<T> optional, final String exceptionMsg) {
        return optional
                .map(result -> new ResponseEntity<>(result, HttpStatus.OK))
                .orElseThrow(() -> new ResourceNotFoundException(exceptionMsg));
    }

}
