package pl.jakpoliczyc.web.dto;

import pl.jakpoliczyc.dao.entities.Stag;

import java.util.Collection;
import java.util.Date;

public class StorageCompressedDto {

    private Long id;
    private String title;
    private Date addedDate;

    private Collection<Stag> stags;

    public StorageCompressedDto(Long id, String title, Date addedDate, Collection<Stag> stags) {
        this.id = id;
        this.title = title;
        this.addedDate = addedDate;
        this.stags = stags;
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

    public Date getAddedDate() {
        return addedDate;
    }

    public void setAddedDate(Date addedDate) {
        this.addedDate = addedDate;
    }

    public Collection<Stag> getStags() {
        return stags;
    }

    public void setStags(Collection<Stag> stags) {
        this.stags = stags;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("StorageCompressedDto{");
        sb.append("id=").append(id);
        sb.append(", title='").append(title).append('\'');
        sb.append(", addedDate=").append(addedDate);
        sb.append(", stags=").append(stags);
        sb.append('}');
        return sb.toString();
    }
}
