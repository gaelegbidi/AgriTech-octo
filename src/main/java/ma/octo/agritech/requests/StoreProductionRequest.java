package ma.octo.agritech.requests;


public class StoreProductionRequest {

    private String productRef;
    private String exploitationRef;
    private String compaignRef;
    private Double quantity;

    public StoreProductionRequest() {
    }

    public StoreProductionRequest(String productRef, String exploitationRef, String compaignRef, Double quantity) {
        this.productRef = productRef;
        this.exploitationRef = exploitationRef;
        this.compaignRef = compaignRef;
        this.quantity = quantity;
    }

    public String getProductRef() {
        return productRef;
    }

    public void setProductRef(String productRef) {
        this.productRef = productRef;
    }

    public String getExploitationRef() {
        return exploitationRef;
    }

    public void setExploitationRef(String exploitationRef) {
        this.exploitationRef = exploitationRef;
    }

    public String getCompaignRef() {
        return compaignRef;
    }

    public void setCompaignRef(String compaignRef) {
        this.compaignRef = compaignRef;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }
}
