package pl.jakpoliczyc.dao.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import pl.jakpoliczyc.web.common.View;

import javax.persistence.*;
import java.util.Collection;

@PersistenceUnit(name = "JakPoliczyc")
@Entity(name = "TAGS")
public class Tag {

    @JsonView(View.Compress.class)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @JsonView(View.Compress.class)
    @Column(unique = true)
    private String name;
    @JsonIgnore
    @ManyToMany(mappedBy = "tags", fetch = FetchType.LAZY)
    private Collection<Article> articles;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Collection<Article> getArticles() {
        return articles;
    }

    public void setArticles(Collection<Article> articles) {
        this.articles = articles;
    }
}
