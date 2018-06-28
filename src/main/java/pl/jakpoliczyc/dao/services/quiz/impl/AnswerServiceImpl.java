package pl.jakpoliczyc.dao.services.quiz.impl;

import org.apache.commons.collections4.CollectionUtils;
import pl.jakpoliczyc.dao.entities.quiz.Answer;
import pl.jakpoliczyc.dao.entities.quiz.Question;
import pl.jakpoliczyc.dao.services.quiz.AnswerService;

import java.util.Collection;
import java.util.stream.Collectors;

public class AnswerServiceImpl implements AnswerService {

    public Collection<Answer> getCorrectAnswers(final Question question) {
        return CollectionUtils.emptyIfNull(question.getAnswers())
                .stream()
                .filter(Answer::isCorrect)
                .collect(Collectors.toList());
    }

}
