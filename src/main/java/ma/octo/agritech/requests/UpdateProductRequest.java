package ma.octo.agritech.requests;

public class UpdateProductRequest {

    private String image;
    private String description;

    public UpdateProductRequest() {
    }

    public UpdateProductRequest(String image, String description) {
        this.image = image;
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
