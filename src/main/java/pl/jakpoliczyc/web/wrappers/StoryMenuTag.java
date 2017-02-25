package pl.jakpoliczyc.web.wrappers;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import pl.jakpoliczyc.dao.entities.Story;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class StoryMenuTag {

    private Story story;
    private List<String> tags;
    private List<Menu> menus;

    public StoryMenuTag() {
        super();
    };

    public StoryMenuTag(Story story, List<String> tags, List<Menu> menus) {
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

    public List<Menu> getMenus() {
        return menus;
    }

    public void setMenus(List<Menu> menus) {
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
