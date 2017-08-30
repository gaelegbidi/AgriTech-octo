package ma.octo.agritech.domains;

import javax.persistence.*;

@Entity
@Table(name = "PRODUCTION")
public class Production {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne()
    @JoinColumn(name="compaign_id")
    private Compaign compaign;

    @ManyToOne()
    @JoinColumn(name="product_id")
    private Product product;

    @Transient
    private String productRef;

    private String quantity;
    private enum submitState {valid, submit};

    @ManyToOne()
    @JoinColumn(name="exploitation_id")
    private Exploitation exploitation;

    @Transient
    private String exploitationRef;
    @Transient
    private String compaignRef;

    @ManyToOne()
    @JoinColumn(name = "farmer_id")
    private Farmer farmer;

    public Production() {
    }


    public Production(String compaignRef, String productRef, String quantity, String exploitationRef) {
        this.compaignRef = compaignRef;
        this.productRef = productRef;
        this.quantity = quantity;
        this.exploitation = null;
        this.exploitationRef=exploitationRef;
        this.farmer = null;
        this.product = null;
        this.compaign = null;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Compaign getCompaign() {
        return compaign;
    }

    public void setCompaign(Compaign compaign) {
        this.compaign = compaign;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String getProductRef() {
        return productRef;
    }

    public void setProductRef(String productRef) {
        this.productRef = productRef;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public Exploitation getExploitation() {
        return exploitation;
    }

    public void setExploitation(Exploitation exploitation) {
        this.exploitation = exploitation;
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

    public Farmer getFarmer() {
        return farmer;
    }

    public void setFarmer(Farmer farmer) {
        this.farmer = farmer;
    }
}
