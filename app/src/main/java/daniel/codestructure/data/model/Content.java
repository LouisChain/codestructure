package daniel.codestructure.data.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

public class Content implements Serializable {
    private String href;
    private String caption;
    private int duration;
    private Image previewImage;
    private String text;
    private List<Markup> markups;
    private int originalWidth;
    private int originalHeight;

    @JsonProperty("href")
    public String getHref() {
        return href;
    }

    @JsonProperty("href")
    public void setHref(String href) {
        this.href = href;
    }

    @JsonProperty("caption")
    public String getCaption() {
        return caption;
    }

    @JsonProperty("caption")
    public void setCaption(String caption) {
        this.caption = caption;
    }

    @JsonProperty("duration")
    public int getDuration() {
        return duration;
    }

    @JsonProperty("duration")
    public void setDuration(int duration) {
        this.duration = duration;
    }

    @JsonProperty("preview_image")
    public Image getPreviewImage() {
        return previewImage;
    }

    @JsonProperty("preview_image")
    public void setPreviewImage(Image previewImage) {
        this.previewImage = previewImage;
    }

    @JsonProperty("text")
    public String getText() {
        return text;
    }

    @JsonProperty("text")
    public void setText(String text) {
        this.text = text;
    }

    @JsonProperty("markups")
    public List<Markup> getMarkups() {
        return markups;
    }

    @JsonProperty("markups")
    public void setMarkups(List<Markup> markups) {
        this.markups = markups;
    }

    @JsonProperty("original_width")
    public int getOriginalWidth() {
        return originalWidth;
    }

    @JsonProperty("original_width")
    public void setOriginalWidth(int originalWidth) {
        this.originalWidth = originalWidth;
    }

    @JsonProperty("original_height")
    public int getOriginalHeight() {
        return originalHeight;
    }

    @JsonProperty("original_height")
    public void setOriginalHeight(int originalheight) {
        this.originalHeight = originalheight;
    }

    @Override
    public String toString() {
        return "Content{" +
            "href='" + href + '\'' +
            ", caption='" + caption + '\'' +
            ", duration=" + duration +
            ", previewImage=" + previewImage +
            ", text='" + text + '\'' +
            ", markups=" + markups +
            '}';
    }
}
