package ma.octo.agritech.domains;

import org.springframework.web.multipart.MultipartFile;

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