package pl.jakpoliczyc.dao.entities;

import org.hibernate.validator.constraints.Email;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.PersistenceUnit;

@PersistenceUnit(name = "JakPoliczyc")
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
