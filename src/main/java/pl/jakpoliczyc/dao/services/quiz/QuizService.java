package pl.jakpoliczyc.dao.services.quiz;


import pl.jakpoliczyc.dao.entities.quiz.Quiz;
import pl.jakpoliczyc.web.dto.quiz.QuizResult;

import java.util.Optional;

public interface QuizService {

    Optional<Quiz> getQuiz(long id);

    Optional<QuizResult> getResult(QuizResult currentResult);

}
