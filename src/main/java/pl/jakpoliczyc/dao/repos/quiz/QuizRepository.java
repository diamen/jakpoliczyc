package pl.jakpoliczyc.dao.repos.quiz;

import pl.jakpoliczyc.dao.entities.quiz.Quiz;

import java.util.Optional;

public interface QuizRepository {

    Optional<Quiz> findQuiz(long id);

}
