package pl.jakpoliczyc.web.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import pl.jakpoliczyc.dao.entities.Story;

import javax.validation.constraints.NotNull;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class StoryMenuTagDto {

    @NotNull
    private Story story;
    private List<String> tags;
    @NotNull
    private List<MenuDto> menus;

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

    @Override
    public String toString() {
        return "StoryMenuTag{" +
                "story=" + story +
                ", tags=" + tags +
                ", menus=" + menus +
                '}';
    }
}
