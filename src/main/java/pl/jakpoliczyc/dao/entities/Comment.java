package pl.jakpoliczyc.dao.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import pl.jakpoliczyc.dao.repos.utils.RepositoryUtils;

import javax.persistence.*;
import java.util.Date;

@PersistenceUnit(name = RepositoryUtils.PERSISTENCE_UNIT_NAME)
@Entity(name = "COMMENTS")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String author;
    @Basic(fetch = FetchType.LAZY)
    @Lob
    private String content;
    @Temporal(TemporalType.DATE)
    @Column(name = "ADDED_DATE")
    private Date addedDate;
    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "ART_ID")
    private Article article;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getAddedDate() {
        return addedDate;
    }

    public void setAddedDate(Date addedDate) {
        this.addedDate = addedDate;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }
}
