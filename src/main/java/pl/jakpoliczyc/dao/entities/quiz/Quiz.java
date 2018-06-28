package pl.jakpoliczyc.dao.entities.quiz;

import pl.jakpoliczyc.dao.common.Difficulty;
import pl.jakpoliczyc.dao.repos.utils.RepositoryUtils;

import javax.persistence.*;
import java.util.Collection;

@Entity
@PersistenceUnit(name = RepositoryUtils.PERSISTENCE_UNIT_NAME)
public class Quiz {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private Difficulty difficulty;
    @JoinColumn(nullable = false)
    @OneToMany(mappedBy = "quiz")
    private Collection<Question> questions;

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

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    public Collection<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(Collection<Question> questions) {
        this.questions = questions;
    }
}
