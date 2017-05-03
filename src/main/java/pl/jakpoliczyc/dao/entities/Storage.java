package pl.jakpoliczyc.dao.entities;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonView;
import pl.jakpoliczyc.dao.converters.UrlToStringConverter;
import pl.jakpoliczyc.web.common.View;

import javax.persistence.*;
import java.net.URL;
import java.util.Collection;
import java.util.Date;

@JsonInclude(JsonInclude.Include.NON_NULL)
@PersistenceUnit(name = "JakPoliczyc")
@Entity(name = "STORAGES")
public class Storage {

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
    @ManyToMany
    @JoinTable(name = "STO_STA",
            joinColumns = @JoinColumn(name = "STO_ID", referencedColumnName = "ID"),
            inverseJoinColumns = @JoinColumn(name = "STA_ID", referencedColumnName = "ID"))
    private Collection<Stag> stags;
    @Convert(converter = UrlToStringConverter.class)
    private URL url;

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
}
