package ma.octo.agritech.services;

import ma.octo.agritech.domains.Product;
import ma.octo.agritech.domains.Role;
import ma.octo.agritech.domains.User;
import ma.octo.agritech.repositories.ProductRepository;
import ma.octo.agritech.requests.StoreProductRequest;
import ma.octo.agritech.requests.StoreProductionRequest;
import ma.octo.agritech.requests.UpdateProductRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private UserService userService;

    public List<Product> getAll() {
        List<Product> products = new ArrayList<>();
        this.productRepository.findAll().forEach(products::add);
        return products;
    }

    public Product save(Product product) {
        this.productRepository.save(product);
        return product;
    }

    public Product SaveByStoreRequest(StoreProductRequest storeProductRequest){
        Product product = new Product(storeProductRequest);
        this.productRepository.save(product);
        return product;


    }

    public Product getById(Long id) {
        return this.productRepository.findOne(id);
    }

    public Product saveByUpdateRequest(Long id, UpdateProductRequest updateProductRequest) {
        Product product = this.productRepository.findOne(id);
        product.setImage(updateProductRequest.getImage());
        product.setDescription(updateProductRequest.getDescription());
        return this.productRepository.save(product);
    }

    public void deleteById(Long id) {
        User user = this.userService.getAuth();
        if (user != null && user.hasRole("ROLE_ADMIN")) {
            this.productRepository.delete(id);
        }
    }
}
