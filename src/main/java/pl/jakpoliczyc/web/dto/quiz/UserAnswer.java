package pl.jakpoliczyc.web.dto.quiz;

import org.hibernate.validator.constraints.NotEmpty;

public class UserAnswer {

    @NotEmpty
    private String answer;

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}