package pl.jakpoliczyc.dao.services.quiz.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.jakpoliczyc.dao.entities.quiz.Answer;
import pl.jakpoliczyc.dao.entities.quiz.Question;
import pl.jakpoliczyc.dao.entities.quiz.Quiz;
import pl.jakpoliczyc.dao.repos.quiz.QuizRepository;
import pl.jakpoliczyc.dao.services.quiz.QuizService;
import pl.jakpoliczyc.web.dto.quiz.QuestionWithUserAnswer;
import pl.jakpoliczyc.web.dto.quiz.QuizResult;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Transactional
@Service
public class QuizServiceImpl implements QuizService {

    @Autowired
    private QuizRepository quizRepository;

    @Transactional(readOnly = true)
    @Override
    public Optional<Quiz> getQuiz(final long id) {
        final Optional<Quiz> optionalQuiz = quizRepository.findQuiz(id);
        if (!optionalQuiz.isPresent()) {
            return Optional.empty();
        }

        final Quiz quiz = optionalQuiz.get();

        final Map<Long, List<Answer>> answers = quiz.getQuestions()
                .stream()
                .map(Question::getAnswers)
                .flatMap(Collection::stream)
                .map(this::copyAnswerWithoutCorrectness)
                .collect(Collectors.groupingBy(ans -> ans.getQuestion().getId()));

        final Collection<Question> questions = quiz.getQuestions().stream().map(this::copyQuestion)
                .map(question -> {
                    final Collection<Answer> ans = answers.get(question.getId())
                            .stream()
                            .peek(a -> a.setQuestion(question))
                            .collect(Collectors.toList());

                    question.setAnswers(ans);
                    return question;
                })
                .collect(Collectors.toList());

        final Quiz copy = copyQuiz(quiz);
        copy.setQuestions(questions);
        return Optional.of(copy);
    }

    private Answer copyAnswerWithoutCorrectness(final Answer answer) {
        final Answer copy = new Answer();
        BeanUtils.copyProperties(answer, copy, "correct");
        return copy;
    }

    private Question copyQuestion(final Question question) {
        final Question copy = new Question();
        BeanUtils.copyProperties(question, copy, "answers", "quiz", "exclamation");
        return copy;
    }

    private Quiz copyQuiz(final Quiz quiz) {
        final Quiz copy = new Quiz();
        BeanUtils.copyProperties(quiz, copy, "questions");
        return copy;
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<QuizResult> getResult(QuizResult currentResult) {
        final Optional<Quiz> optionalQuiz = quizRepository.findQuiz(currentResult.getId());
        if (!optionalQuiz.isPresent()) {
            return Optional.empty();
        }

        final Quiz quiz = optionalQuiz.get();

        final QuizResult quizResult = new QuizResult();

        final Collection<QuestionWithUserAnswer> questionWithUserAnswers = currentResult.getQuestions().stream().map(questionWithUserAnswer -> {
            final Question question = quiz.getQuestions().stream()
                    .filter(q -> q.getId() == questionWithUserAnswer.getId())
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException(String.format("Quiz with id %d is corrupted", quizResult.getId())));

            questionWithUserAnswer.setExclamation(question.getExclamation());
            questionWithUserAnswer.setQuizAnswers(question.getAnswers());

            return questionWithUserAnswer;
        }).collect(Collectors.toList());

        quizResult.setId(currentResult.getId());
        quizResult.setQuestions(questionWithUserAnswers);

        return Optional.of(quizResult);
    }


}
