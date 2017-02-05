package pl.jakpoliczyc.dao.entities;

import javax.persistence.*;
import java.util.Collection;

@PersistenceUnit(name = "JakPoliczyc")
@Entity
public class Menu {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(nullable = false)
    private String name;
    @ManyToOne(cascade = CascadeType.PERSIST)
    private Menu parent;
    @OneToMany(mappedBy = "parent")
    private Collection<Menu> submenus;
    @OneToMany(mappedBy = "menu")
    private Collection<Article> articles;

    public long getId() { return id; }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public Menu getParent() {
        return parent;
    }

    public void setParent(Menu parent) {
        this.parent = parent;
    }

    public Collection<Menu> getSubmenus() {
        return submenus;
    }

    public void setSubmenus(Collection<Menu> submenus) {
        this.submenus = submenus;
    }

    public Collection<Article> getArticles() {
        return articles;
    }

    public void setArticles(Collection<Article> articles) {
        this.articles = articles;
    }
}
