package ma.octo.agritech.domains;

public class UploadModel {

    private String extraField;

//    private MultipartFile[] files;

    public UploadModel(String extraField) {
        this.extraField = extraField;
    }

    public UploadModel() {
    }

    public String getExtraField() {
        return extraField;
    }

    public void setExtraField(String extraField) {
        this.extraField = extraField;
    }
}