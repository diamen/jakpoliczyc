package pl.jakpoliczyc.web.dto;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import java.io.Serializable;

public class EmailDto implements Serializable {

    @Email
    private String address;
    @NotEmpty
    private String title;
    @NotEmpty
    private String content;

    public EmailDto() {}

    public EmailDto(String address, String title, String content) {
        this.address = address;
        this.title = title;
        this.content = content;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("EmailDto{");
        sb.append("address='").append(address).append('\'');
        sb.append(", title='").append(title).append('\'');
        sb.append(", content='").append(content).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
