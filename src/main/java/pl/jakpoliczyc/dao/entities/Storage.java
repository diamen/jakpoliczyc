package pl.jakpoliczyc.dao.entities;

import javax.persistence.*;
import java.util.Collection;

@PersistenceUnit(name = "JakPoliczyc")
@Entity(name = "STORAGES")
public class Storage {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Embedded
    private Story story;
    @ManyToMany
    @JoinTable(name = "STO_STA",
            joinColumns = @JoinColumn(name = "STO_ID", referencedColumnName = "ID"),
            inverseJoinColumns = @JoinColumn(name = "STA_ID", referencedColumnName = "ID"))
    private Collection<Stag> stags;

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

    public Collection<Stag> getStags() {
        return stags;
    }

    public void setStags(Collection<Stag> stags) {
        this.stags = stags;
    }
}
