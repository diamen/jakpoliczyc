package pl.jakpoliczyc.dao.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonView;
import pl.jakpoliczyc.web.common.View;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;

@PersistenceUnit(name = "JakPoliczyc")
@Entity(name = "ARTICLES")
public class Article {

    @JsonView(View.Compress.class)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @JsonView(View.Compress.class)
    @Embedded
    private Story story;
    @JsonView(View.Compress.class)
    @Temporal(TemporalType.DATE)
    @Column(name = "ADDED_DATE")
    private Date addedDate;
    @JsonView(View.Compress.class)
    @JsonIgnoreProperties({ "name", "parent", "submenus", "articles" })
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "MENU_ID", nullable = false)
    private Menu menu;
    @JsonManagedReference
    @OneToMany(mappedBy = "article", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private Collection<Comment> comments;
    @JsonView(View.Compress.class)
    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name = "ART_TAG",
        joinColumns = @JoinColumn(name = "ART_ID", referencedColumnName = "ID"),
        inverseJoinColumns = @JoinColumn(name = "TAG_ID", referencedColumnName = "ID"))
    private Collection<Tag> tags;

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
}
