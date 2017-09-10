package ma.octo.agritech.requests;

public class StoreNegociationRequest {

    private Double price;
    private Long productionId;

    public StoreNegociationRequest(Double price, Long productionId) {
        this.price = price;
        this.productionId = productionId;
    }

    public StoreNegociationRequest() {
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Long getProductionId() {
        return productionId;
    }

    public void setProductionId(Long productionId) {
        this.productionId = productionId;
    }
}
