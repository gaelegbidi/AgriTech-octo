package ma.octo.agritech.domains;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "PRODUCTIONS")
public class Production {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne()
    @JoinColumn(name = "compaign_id")
    private Compaign compaign = new Compaign();

    @ManyToOne()
    @JoinColumn(name = "product_id")
    private Product product = new Product();

    @ManyToOne()
    @JoinColumn(name = "exploitation_id")
    private Exploitation exploitation = new Exploitation();

    @ManyToOne()
    @JoinColumn(name = "user_id")
    private User user = new User();

    @OneToMany(mappedBy = "production")
    @JsonIgnore
    private List<Negociation> negociations = new ArrayList<>();


    private Double quantity;


    public Production() {
    }


    public Production(Double quantity) {

        this.quantity = quantity;
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

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public List<Negociation> getNegociations() {
        return negociations;
    }

    public void setNegociations(List<Negociation> negociations) {
        this.negociations = negociations;
    }
}
