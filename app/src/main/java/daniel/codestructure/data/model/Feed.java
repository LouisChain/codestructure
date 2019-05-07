package daniel.codestructure.data.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

public class Feed implements Serializable {
    private String documentId;
    private String title;
    private String description;
    private String contentType;
    private String publishedDate;
    private Publisher publisher;
    private String originUrl;
    private Image avatar;
    private List<Image> images;
    private String templateType;
    private List<Section> sections;

    @JsonProperty("document_id")
    public String getDocumentId() {
        return documentId;
    }

    @JsonProperty("document_id")
    public void setDocumentId(String documentId) {
        this.documentId = documentId;
    }

    @JsonProperty("title")
    public String getTitle() {
        return title;
    }

    @JsonProperty("title")
    public void setTitle(String title) {
        this.title = title;
    }

    @JsonProperty("description")
    public String getDescription() {
        return description;
    }

    @JsonProperty("description")
    public void setDescription(String description) {
        this.description = description;
    }

    @JsonProperty("content_type")
    public String getContentType() {
        return contentType;
    }

    @JsonProperty("content_type")
    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    @JsonProperty("published_date")
    public String getPublishedDate() {
        return publishedDate;
    }

    @JsonProperty("published_date")
    public void setPublishedDate(String publishedDate) {
        this.publishedDate = publishedDate;
    }

    @JsonProperty("publisher")
    public Publisher getPublisher() {
        return publisher;
    }

    @JsonProperty("publisher")
    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }

    @JsonProperty("origin_url")
    public String getOriginUrl() {
        return originUrl;
    }

    @JsonProperty("origin_url")
    public void setOriginUrl(String originUrl) {
        this.originUrl = originUrl;
    }

    @JsonProperty("avatar")
    public Image getAvatar() {
        return avatar;
    }

    @JsonProperty("avatar")
    public void setAvatar(Image avatar) {
        this.avatar = avatar;
    }

    @JsonProperty("images")
    public List<Image> getImages() {
        return images;
    }

    @JsonProperty("images")
    public void setImages(List<Image> images) {
        this.images = images;
    }

    @JsonProperty("template_type")
    public String getTemplateType() {
        return templateType;
    }

    @JsonProperty("template_type")
    public void setTemplateType(String templateType) {
        this.templateType = templateType;
    }

    @JsonProperty("sections")
    public List<Section> getSections() {
        return sections;
    }

    @JsonProperty("sections")
    public void setSections(List<Section> sections) {
        this.sections = sections;
    }

    @Override
    public String toString() {
        return "Feed{" +
            "documentId='" + documentId + '\'' +
            ", title='" + title + '\'' +
            ", description='" + description + '\'' +
            ", contentType='" + contentType + '\'' +
            ", publishedDate='" + publishedDate + '\'' +
            ", publisher=" + publisher +
            ", originUrl='" + originUrl + '\'' +
            ", avatar=" + avatar +
            ", images=" + images +
            ", templateType='" + templateType + '\'' +
            ", sections=" + sections +
            '}';
    }
}
