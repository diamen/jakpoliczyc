package pl.jakpoliczyc.dao.entities;

import org.hibernate.validator.constraints.Email;
import pl.jakpoliczyc.dao.repos.utils.RepositoryUtils;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.PersistenceUnit;

@PersistenceUnit(name = RepositoryUtils.PERSISTENCE_UNIT_NAME)
@Entity(name = "SUBSCRIBERS")
public class Subscriber {

    @Email
    @Id
    private String email;

    public Subscriber() {}

    public Subscriber(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
