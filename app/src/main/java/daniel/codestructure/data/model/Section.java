package daniel.codestructure.data.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class Section implements Serializable {
    private int sectionType;
    private Content content;

    @JsonProperty("section_type")
    public int getSectionType() {
        return sectionType;
    }

    @JsonProperty("section_type")
    public void setSectionType(int sectionType) {
        this.sectionType = sectionType;
    }

    @JsonProperty("content")
    public Content getContent() {
        return content;
    }

    @JsonProperty("content")
    public void setContent(Content content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Section{" +
            "sectionType=" + sectionType +
            ", content=" + content +
            '}';
    }
}
