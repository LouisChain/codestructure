package daniel.codestructure.data.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class Markup implements Serializable {
    private int markupType;
    private int start;
    private int end;
    private String href;

    @JsonProperty("markup_type")
    public int getMarkupType() {
        return markupType;
    }

    @JsonProperty("markup_type")
    public void setMarkupType(int markupType) {
        this.markupType = markupType;
    }

    @JsonProperty("start")
    public int getStart() {
        return start;
    }

    @JsonProperty("start")
    public void setStart(int start) {
        this.start = start;
    }

    @JsonProperty("end")
    public int getEnd() {
        return end;
    }

    @JsonProperty("end")
    public void setEnd(int end) {
        this.end = end;
    }

    @JsonProperty("href")
    public String getHref() {
        return href;
    }

    @JsonProperty("href")
    public void setHref(String href) {
        this.href = href;
    }

    @Override
    public String toString() {
        return "Markup{" +
            "markupType=" + markupType +
            ", start=" + start +
            ", end=" + end +
            ", href='" + href + '\'' +
            '}';
    }
}
