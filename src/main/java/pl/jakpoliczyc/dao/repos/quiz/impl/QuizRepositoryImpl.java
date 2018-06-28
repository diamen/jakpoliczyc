package pl.jakpoliczyc.dao.repos.quiz.impl;


import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.jakpoliczyc.dao.entities.quiz.Quiz;
import pl.jakpoliczyc.dao.repos.quiz.QuizRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Optional;

@Repository
@Transactional
public class QuizRepositoryImpl implements QuizRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional(readOnly = true)
    @Override
    public Optional<Quiz> findQuiz(long id) {
        return Optional.ofNullable(entityManager.find(Quiz.class, id));
    }
}
