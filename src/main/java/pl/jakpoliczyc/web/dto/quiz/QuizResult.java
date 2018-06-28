package pl.jakpoliczyc.web.dto.quiz;

import com.fasterxml.jackson.annotation.JsonFilter;
import org.hibernate.validator.constraints.NotEmpty;
import pl.jakpoliczyc.web.serialization.Filters;

import javax.validation.constraints.NotNull;
import java.util.Collection;

public class QuizResult {

    @NotNull
    private long id;
    @NotEmpty
    private Collection<QuestionWithUserAnswer> questions;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Collection<QuestionWithUserAnswer> getQuestions() {
        return questions;
    }

    public void setQuestions(Collection<QuestionWithUserAnswer> questions) {
        this.questions = questions;
    }

}
