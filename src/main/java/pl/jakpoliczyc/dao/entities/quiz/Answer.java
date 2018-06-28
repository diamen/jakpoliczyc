package pl.jakpoliczyc.dao.entities.quiz;

import pl.jakpoliczyc.dao.repos.utils.RepositoryUtils;

import javax.persistence.*;

@Entity
@PersistenceUnit(name = RepositoryUtils.PERSISTENCE_UNIT_NAME)
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false)
    private Boolean correct;
    @Column(nullable = false)
    private String content;
    @ManyToOne(optional = false)
    private Question question;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Boolean isCorrect() {
        return correct;
    }

    public void setCorrect(Boolean correct) {
        this.correct = correct;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }
}
