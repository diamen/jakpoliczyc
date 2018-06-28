package pl.jakpoliczyc.web.dto.quiz;

import org.hibernate.validator.constraints.NotEmpty;
import pl.jakpoliczyc.dao.entities.quiz.Answer;

import javax.validation.constraints.NotNull;
import java.util.Collection;

public class QuestionWithUserAnswer {

    @NotNull
    private long id;
    @NotEmpty
    private Collection<UserAnswer> userAnswers;
    @NotEmpty
    private Collection<Answer> quizAnswers;
    private String exclamation;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Collection<Answer> getQuizAnswers() {
        return quizAnswers;
    }

    public void setQuizAnswers(Collection<Answer> quizAnswers) {
        this.quizAnswers = quizAnswers;
    }

    public Collection<UserAnswer> getUserAnswers() {
        return userAnswers;
    }

    public void setUserAnswers(Collection<UserAnswer> userAnswers) {
        this.userAnswers = userAnswers;
    }

    public String getExclamation() {
        return exclamation;
    }

    public void setExclamation(String exclamation) {
        this.exclamation = exclamation;
    }
}