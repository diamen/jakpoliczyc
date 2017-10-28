package pl.jakpoliczyc.dao.entities;

import pl.jakpoliczyc.dao.converters.UrlToStringConverter;
import pl.jakpoliczyc.dao.repos.utils.RepositoryUtils;

import javax.persistence.*;
import java.net.URL;
import java.util.Collection;
import java.util.Date;

@PersistenceUnit(name = RepositoryUtils.PERSISTENCE_UNIT_NAME)
@Entity(name = "STORAGES")
public class Storage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Embedded
    private Story story;
    @Temporal(TemporalType.DATE)
    @Column(name = "ADDED_DATE")
    private Date addedDate;
    @ManyToMany
    @JoinTable(name = "STO_STA",
            joinColumns = @JoinColumn(name = "STO_ID", referencedColumnName = "ID"),
            inverseJoinColumns = @JoinColumn(name = "STA_ID", referencedColumnName = "ID"))
    private Collection<Stag> stags;
    @Convert(converter = UrlToStringConverter.class)
    private URL url;
    @ManyToOne
    @JoinColumn(name = "KAHOOT_ID")
    private Kahoot kahoot;

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

    public Collection<Stag> getStags() {
        return stags;
    }

    public void setStags(Collection<Stag> stags) {
        this.stags = stags;
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
}
