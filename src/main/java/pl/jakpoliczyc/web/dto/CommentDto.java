package pl.jakpoliczyc.web.dto;

import org.hibernate.validator.constraints.NotEmpty;

import java.io.Serializable;

public class CommentDto implements Serializable {

    @NotEmpty
    private String author;
    @NotEmpty
    private String content;

    public CommentDto() { super(); }

    public CommentDto(String author, String content) {
        this.author = author;
        this.content = content;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("CommentDto{");
        sb.append("author='").append(author).append('\'');
        sb.append(", content='").append(content).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
