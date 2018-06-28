package pl.jakpoliczyc.dao.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import pl.jakpoliczyc.dao.converters.UrlToStringConverter;
import pl.jakpoliczyc.dao.repos.utils.RepositoryUtils;

import javax.persistence.*;
import java.net.URL;
import java.util.Collection;
import java.util.Date;

@PersistenceUnit(name = RepositoryUtils.PERSISTENCE_UNIT_NAME)
@Entity(name = "ARTICLES")
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Embedded
    private Story story;
    @Temporal(TemporalType.DATE)
    @Column(name = "ADDED_DATE")
    private Date addedDate;
    @JsonBackReference
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "MENU_ID", nullable = false)
    private Menu menu;
    @JsonManagedReference
    @OneToMany(mappedBy = "article", fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private Collection<Comment> comments;
    @ManyToMany
    @JoinTable(name = "ART_TAG",
            joinColumns = @JoinColumn(name = "ART_ID", referencedColumnName = "ID"),
            inverseJoinColumns = @JoinColumn(name = "TAG_ID", referencedColumnName = "ID"))
    private Collection<Tag> tags;
    @Convert(converter = UrlToStringConverter.class)
    private URL url;
    @ManyToOne
    @JoinColumn(name = "KAHOOT_ID")
    private Kahoot kahoot;
    @Convert(converter = UrlToStringConverter.class)
    private URL pdf;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Story getStory() {
        return story;
    }

    public void setStory(Story story) {
        this.story = story;
    }

    public Date getAddedDate() {
        return addedDate;
    }

    public void setAddedDate(Date addedDate) {
        this.addedDate = addedDate;
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public Collection<Comment> getComments() {
        return comments;
    }

    public void setComments(Collection<Comment> comments) {
        this.comments = comments;
    }

    public Collection<Tag> getTags() {
        return tags;
    }

    public void setTags(Collection<Tag> tags) {
        this.tags = tags;
    }

    public URL getUrl() {
        return url;
    }

    public void setUrl(URL url) {
        this.url = url;
    }

    public Kahoot getKahoot() {
        return kahoot;
    }

    public void setKahoot(Kahoot kahoot) {
        this.kahoot = kahoot;
    }

    public URL getPdf() {
        return pdf;
    }

    public void setPdf(URL pdf) {
        this.pdf = pdf;
    }
}
