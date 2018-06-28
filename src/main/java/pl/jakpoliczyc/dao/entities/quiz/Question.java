package pl.jakpoliczyc.dao.entities.quiz;

import pl.jakpoliczyc.dao.repos.utils.RepositoryUtils;

import javax.persistence.*;
import java.util.Collection;

@Entity
@PersistenceUnit(name = RepositoryUtils.PERSISTENCE_UNIT_NAME)
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false)
    private String title;
    @JoinColumn(nullable = false)
    @OneToMany(mappedBy = "question")
    private Collection<Answer> answers;
    @ManyToOne(optional = false)
    private Quiz quiz;

    private String exclamation;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Collection<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(Collection<Answer> answers) {
        this.answers = answers;
    }

    public Quiz getQuiz() {
        return quiz;
    }

    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
    }

    public String getExclamation() {
        return exclamation;
    }

    public void setExclamation(String exclamation) {
        this.exclamation = exclamation;
    }
}
