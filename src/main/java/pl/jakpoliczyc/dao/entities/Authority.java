package pl.jakpoliczyc.dao.entities;

import javax.persistence.*;

@Entity(name = "AUTHORITIES")
@IdClass(AuthorityId.class)
public class Authority {

    @Id
    private String username;
    @Id
    private String authority;
    @ManyToOne
    @JoinColumn(name = "USERNAME", insertable = false, updatable = false)
    private User user;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
