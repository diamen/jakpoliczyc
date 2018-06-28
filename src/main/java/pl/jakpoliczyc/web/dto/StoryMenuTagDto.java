package pl.jakpoliczyc.web.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.validator.constraints.URL;
import pl.jakpoliczyc.dao.entities.Kahoot;
import pl.jakpoliczyc.dao.entities.Story;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class StoryMenuTagDto implements Serializable {

    @NotNull
    private Story story;
    private List<String> tags;
    private List<MenuDto> menus;
    @URL
    private String youtube;
    @URL
    private String pdf;
    private Kahoot kahoot;

    public StoryMenuTagDto() {
        super();
    }

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

    public String getYoutube() {
        return youtube;
    }

    public void setYoutube(String youtube) {
        this.youtube = youtube;
    }

    public String getPdf() {
        return pdf;
    }

    public void setPdf(String pdf) {
        this.pdf = pdf;
    }

    public Kahoot getKahoot() {
        return kahoot;
    }

    public void setKahoot(Kahoot kahoot) {
        this.kahoot = kahoot;
    }
}
