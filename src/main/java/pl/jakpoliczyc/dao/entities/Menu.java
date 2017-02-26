package pl.jakpoliczyc.dao.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonView;
import pl.jakpoliczyc.web.common.View;

import javax.persistence.*;
import java.util.Collection;

@PersistenceUnit(name = "JakPoliczyc")
@Entity
public class Menu {

    @JsonView(View.Compress.class)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false)
    private String name;
    @JsonBackReference
    @JoinColumn(nullable = true)
    @ManyToOne
    private Menu parent;
    @JsonManagedReference
    @OneToMany(mappedBy = "parent", cascade = CascadeType.PERSIST)
    private Collection<Menu> submenus;
    @JsonIgnore
    @OneToMany(mappedBy = "menu", fetch = FetchType.LAZY)
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
