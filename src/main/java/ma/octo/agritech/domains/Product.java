package ma.octo.agritech.domains;


import ma.octo.agritech.requests.StoreProductRequest;

import javax.persistence.*;

@Entity
@Table(name = "PRODUCTS")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true)
    private String ref;

    @Column(unique = true)
    private String name;

    private String description;
    private String image;


    public Product() {
    }

    public Product(StoreProductRequest storeProductRequest) {
    }

    public Product(String name, String ref, String description) {
        this.name = name;
        this.ref = ref;
        this.description = description;
    }

    public Product(String name, String ref, String description, String image) {
        this.name = name;
        this.ref = ref;
        this.description = description;
        this.image = image;
    }

    public  Product(Product product){
        this.name=product.name;
        this.ref=product.ref;
        this.description=product.description;
        this.image=product.image;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
