package pl.jakpoliczyc.web.dto;

import pl.jakpoliczyc.dao.entities.Tag;

import java.util.Collection;
import java.util.Date;

public class ArticleCompressedDto {

    private Long id;
    private String title;
    private String intro;
    private Date addedDate;

    private Collection<Tag> tags;
    private MenuDto menu;

    public ArticleCompressedDto(Long id, String title, String intro, Date addedDate, Collection<Tag> tags, MenuDto menu) {
        this.id = id;
        this.title = title;
        this.intro = intro;
        this.addedDate = addedDate;
        this.tags = tags;
        this.menu = menu;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public Date getAddedDate() {
        return addedDate;
    }

    public void setAddedDate(Date addedDate) {
        this.addedDate = addedDate;
    }

    public Collection<Tag> getTags() {
        return tags;
    }

    public void setTags(Collection<Tag> tags) {
        this.tags = tags;
    }

    public MenuDto getMenu() {
        return menu;
    }

    public void setMenu(MenuDto menu) {
        this.menu = menu;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ArticleCompressedDto{");
        sb.append("id=").append(id);
        sb.append(", title='").append(title).append('\'');
        sb.append(", intro='").append(intro).append('\'');
        sb.append(", addedDate=").append(addedDate);
        sb.append(", tags=").append(tags);
        sb.append(", menu=").append(menu);
        sb.append('}');
        return sb.toString();
    }
}
