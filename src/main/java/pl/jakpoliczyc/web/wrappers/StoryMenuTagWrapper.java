package pl.jakpoliczyc.web.wrappers;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import pl.jakpoliczyc.dao.entities.Story;

import javax.validation.constraints.NotNull;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class StoryMenuTagWrapper {

    @NotNull
    private Story story;
    private List<String> tags;
    @NotNull
    private List<MenuWrapper> menus;

    public StoryMenuTagWrapper() {
        super();
    };

    public StoryMenuTagWrapper(Story story, List<String> tags, List<MenuWrapper> menus) {
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

    public List<MenuWrapper> getMenus() {
        return menus;
    }

    public void setMenus(List<MenuWrapper> menus) {
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
