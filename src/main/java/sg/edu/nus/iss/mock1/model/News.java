package sg.edu.nus.iss.mock1.model;

public class News {
    private String id;
    private Integer published;
    private String title;
    private String url;
    private String imageUrl;
    private String body;
    private String tags;
    private String categories;

    public News() {

    }

    public News(String id, Integer published, String title, String url, String imageUrl, String body, String tags,
            String categories) {
        this.id = id;
        this.published = published;
        this.title = title;
        this.url = url;
        this.imageUrl = imageUrl;
        this.body = body;
        this.tags = tags;
        this.categories = categories;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getPublished() {
        return published;
    }

    public void setPublished(Integer published) {
        this.published = published;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getCategories() {
        return categories;
    }

    public void setCategories(String categories) {
        this.categories = categories;
    }

    @Override
    public String toString() {
        return id + "~~~" + published + "~~~" + title + "~~~" + url + "~~~"
                + imageUrl + "~~~" + body + "~~~" + tags + "~~~" + categories;
    }

    
    
    
}
