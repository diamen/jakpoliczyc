package pl.jakpoliczyc.web.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.validator.constraints.URL;
import pl.jakpoliczyc.dao.entities.Story;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class StoryMenuTagDto implements Serializable {

    @NotNull
    private Story story;
    private List<String> tags;
    @NotNull
    private List<MenuDto> menus;
    @URL
    private String url;

    public StoryMenuTagDto() {
        super();
    };

    public StoryMenuTagDto(Story story, List<String> tags, List<MenuDto> menus) {
        this.story = story;
        this.tags = tags;
        this.menus = menus;
    }

    public Story getStory() {
        return story;
    }

    public void setStory(Story story) {
        this.story = story;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public List<MenuDto> getMenus() {
        return menus;
    }

    public void setMenus(List<MenuDto> menus) {
        this.menus = menus;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("StoryMenuTagDto{");
        sb.append("story=").append(story);
        sb.append(", tags=").append(tags);
        sb.append(", menus=").append(menus);
        sb.append(", url='").append(url).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
