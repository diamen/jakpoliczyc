package pl.jakpoliczyc.web.dto;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

public class MenuDto {

    @NotNull
    public long id;
    @NotEmpty
    public String name;

    public MenuDto() {
        super();
    };

    public MenuDto(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Menu{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
