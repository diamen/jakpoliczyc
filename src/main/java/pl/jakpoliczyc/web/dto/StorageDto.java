package pl.jakpoliczyc.web.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.validator.constraints.URL;
import pl.jakpoliczyc.dao.entities.Story;

import java.io.Serializable;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class StorageDto implements Serializable {

    private Story story;
    private List<String> stags;
    @URL
    private String url;

    public Story getStory() {
        return story;
    }

    public void setStory(Story story) {
        this.story = story;
    }

    public List<String> getStags() {
        return stags;
    }

    public void setStags(List<String> stags) {
        this.stags = stags;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("StorageDto{");
        sb.append("story=").append(story);
        sb.append(", stags=").append(stags);
        sb.append(", url='").append(url).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
