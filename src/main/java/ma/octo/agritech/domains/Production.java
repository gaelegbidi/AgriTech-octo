package ma.octo.agritech.domains;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

@Entity
@Table(name = "PRODUCTIONS")
public class Production {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne()
    @JoinColumn(name = "compaign_id")
    @JsonBackReference
    private Compaign compaign;

    @ManyToOne()
    @JoinColumn(name = "product_id")
    @JsonBackReference
    private Product product;

    @ManyToOne()
    @JoinColumn(name = "exploitation_id")
    @JsonBackReference
    private Exploitation exploitation;

    @ManyToOne()
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private User user;

    @Transient
    private String productRef;
    @Transient
    private String exploitationRef;
    @Transient
    private String compaignRef;

    private String quantity;


    public Production() {
    }


    public Production(String compaignRef, String productRef, String quantity, String exploitationRef) {
        this.compaignRef = compaignRef;
        this.productRef = productRef;
        this.exploitationRef = exploitationRef;
        this.quantity = quantity;
        this.exploitation = null;
        this.user = null;
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

    public Exploitation getExploitation() {
        return exploitation;
    }

    public void setExploitation(Exploitation exploitation) {
        this.exploitation = exploitation;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }
}
