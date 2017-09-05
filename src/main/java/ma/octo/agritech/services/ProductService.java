package ma.octo.agritech.services;

import ma.octo.agritech.domains.Product;
import ma.octo.agritech.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAll() {
        List<Product> products = new ArrayList<>();
        this.productRepository.findAll().forEach(products::add);
        return products;
    }

    public Product save(Product product) {
        this.productRepository.save(product);
        return product;
    }
}
