package pl.jakpoliczyc.dao.entities;

import java.io.Serializable;

public class AuthorityId implements Serializable {

    private String username;
    private String authority;

    public AuthorityId(String username, String authority) {
        this.username = username;
        this.authority = authority;
    }

    public String getUsername() {
        return username;
    }

    public String getAuthority() {
        return authority;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AuthorityId that = (AuthorityId) o;

        if (username != null ? !username.equals(that.username) : that.username != null) return false;
        return authority != null ? authority.equals(that.authority) : that.authority == null;
    }

    @Override
    public int hashCode() {
        int result = username != null ? username.hashCode() : 0;
        result = 31 * result + (authority != null ? authority.hashCode() : 0);
        return result;
    }
}
