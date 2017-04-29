package pl.jakpoliczyc.web.dto;

import org.hibernate.validator.constraints.NotEmpty;

import java.io.Serializable;

public class EmailContentDto implements Serializable {

    @NotEmpty
    private String title;
    @NotEmpty
    private String content;

    public EmailContentDto() { super(); }

    public EmailContentDto(String title, String content) {
        this.title = title;
        this.content = content;
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
        final StringBuilder sb = new StringBuilder("EmailContentDto{");
        sb.append("title='").append(title).append('\'');
        sb.append(", content='").append(content).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
