package pl.jakpoliczyc.web.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import pl.jakpoliczyc.dao.entities.Story;

import java.io.Serializable;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class StorageDto implements Serializable {

    private Story story;
    private List<String> stags;

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

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("StorageDto{");
        sb.append("story=").append(story);
        sb.append(", stags=").append(stags);
        sb.append('}');
        return sb.toString();
    }
}
