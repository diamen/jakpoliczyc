package pl.jakpoliczyc.web.dto;

import org.hibernate.validator.constraints.NotEmpty;

public class ConfigurationDto {

    @NotEmpty
    private String keyy;
    @NotEmpty
    private String value;

    public ConfigurationDto() {
    }

    public ConfigurationDto(String keyy, String value) {
        this.keyy = keyy;
        this.value = value;
    }

    public String getKeyy() {
        return keyy;
    }

    public void setKeyy(String keyy) {
        this.keyy = keyy;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ConfigurationDto{");
        sb.append("keyy='").append(keyy).append('\'');
        sb.append(", value='").append(value).append('\'');
        sb.append('}');
        return sb.toString();
    }

}
