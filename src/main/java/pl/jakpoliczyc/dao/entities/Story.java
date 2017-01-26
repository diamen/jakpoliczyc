package pl.jakpoliczyc.dao.entities;

import javax.persistence.*;

@Embeddable @Access(AccessType.FIELD)
public class Story {
    private String title;
    @Basic(fetch = FetchType.LAZY)
    @Column(columnDefinition = "text")
    @Lob
    private String intro;
    @Basic(fetch = FetchType.LAZY)
    @Lob
    private String content;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
