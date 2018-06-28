package pl.jakpoliczyc.web.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.validator.constraints.URL;
import pl.jakpoliczyc.dao.entities.Kahoot;
import pl.jakpoliczyc.dao.entities.Story;

import java.io.Serializable;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class StorageDto implements Serializable {

    private Story story;
    private List<String> stags;
    @URL
    private String url;
    @URL
    private String pdf;
    private Kahoot kahoot;

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

    public Kahoot getKahoot() {
        return kahoot;
    }

    public void setKahoot(Kahoot kahoot) {
        this.kahoot = kahoot;
    }

    public String getPdf() {
        return pdf;
    }

    public void setPdf(String pdf) {
        this.pdf = pdf;
    }
}
