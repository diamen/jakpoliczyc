package pl.jakpoliczyc.dao.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import pl.jakpoliczyc.dao.common.KahootDifficulties;
import pl.jakpoliczyc.dao.converters.KahootEnumToIntConverter;
import pl.jakpoliczyc.dao.converters.UrlToStringConverter;
import pl.jakpoliczyc.dao.repos.utils.RepositoryUtils;

import javax.persistence.*;
import java.net.URL;
import java.util.List;

@PersistenceUnit(name = RepositoryUtils.PERSISTENCE_UNIT_NAME)
@Entity(name = "KAHOOT")
public class Kahoot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String title;
    @Convert(converter = UrlToStringConverter.class)
    private URL url;
    @Convert(converter = KahootEnumToIntConverter.class)
    private KahootDifficulties kahootDifficulties;
    @JsonIgnore
    @OneToMany(mappedBy = "kahoot", fetch = FetchType.LAZY)
    private List<Article> articles;
    @JsonIgnore
    @OneToMany(mappedBy = "kahoot", fetch = FetchType.LAZY)
    private List<Storage> storage;

    @PreRemove
    private void clearing() {
        articles.forEach(article -> article.setKahoot(null));
        storage.forEach(single -> single.setKahoot(null));
    }

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

    public URL getUrl() {
        return url;
    }

    public void setUrl(URL url) {
        this.url = url;
    }

    public KahootDifficulties getKahootDifficulties() {
        return kahootDifficulties;
    }

    public void setKahootDifficulties(KahootDifficulties kahootDifficulties) {
        this.kahootDifficulties = kahootDifficulties;
    }

    public List<Article> getArticles() {
        return articles;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }

    public List<Storage> getStorage() {
        return storage;
    }

    public void setStorage(List<Storage> storage) {
        this.storage = storage;
    }
}
