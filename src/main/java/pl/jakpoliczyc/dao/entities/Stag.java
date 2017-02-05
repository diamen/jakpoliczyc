package pl.jakpoliczyc.dao.entities;

import javax.persistence.*;
import java.util.Collection;

@PersistenceUnit(name = "JakPoliczyc")
@Entity(name = "STAGS")
public class Stag {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;
    @ManyToMany
    private Collection<Storage> storages;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public Collection<Storage> getStorages() {
        return storages;
    }

    public void setStorages(Collection<Storage> storages) {
        this.storages = storages;
    }
}
