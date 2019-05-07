package daniel.codestructure.data.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class Publisher implements Serializable {
    private String id;
    private String name;
    private String iconLink;

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("icon")
    public String getIconLink() {
        return iconLink;
    }

    @JsonProperty("icon")
    public void setIconLink(String iconLink) {
        this.iconLink = iconLink;
    }

    @Override
    public String toString() {
        return "Publisher{" +
            "id='" + id + '\'' +
            ", name='" + name + '\'' +
            ", iconLink='" + iconLink + '\'' +
            '}';
    }
}
