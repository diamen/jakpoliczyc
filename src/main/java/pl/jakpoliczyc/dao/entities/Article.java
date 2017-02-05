package pl.jakpoliczyc.dao.entities;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;

@PersistenceUnit(name = "JakPoliczyc")
@Entity(name = "ARTICLES")
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Embedded
    private Story story;
    @Temporal(TemporalType.DATE)
    @Column(name = "ADDED_DATE")
    private Date addedDate;
    @ManyToOne
    @JoinColumn(name = "MENU_ID")
    private Menu menu;
    @OneToMany(mappedBy = "article", fetch = FetchType.LAZY)
    private Collection<Comment> comments;
    @ManyToMany
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
