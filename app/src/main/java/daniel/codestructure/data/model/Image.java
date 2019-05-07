package daniel.codestructure.data.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class Image implements Serializable {
    private String href;
    private String mainColor;
    private int width;
    private int height;

    @JsonProperty("href")
    public String getHref() {
        return href;
    }

    @JsonProperty("href")
    public void setHref(String href) {
        this.href = href;
    }

    @JsonProperty("main_color")
    public String getMainColor() {
        return mainColor;
    }

    @JsonProperty("main_color")
    public void setMainColor(String mainColor) {
        this.mainColor = mainColor;
    }

    @JsonProperty("width")
    public int getWidth() {
        return width;
    }

    @JsonProperty("width")
    public void setWidth(int width) {
        this.width = width;
    }

    @JsonProperty("height")
    public int getHeight() {
        return height;
    }

    @JsonProperty("height")
    public void setHeight(int height) {
        this.height = height;
    }

    @Override
    public String toString() {
        return "Avatar{" +
            "href='" + href + '\'' +
            ", mainColor='" + mainColor + '\'' +
            ", width=" + width +
            ", height=" + height +
            '}';
    }
}
