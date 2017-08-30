package ma.octo.agritech.controllers;

import ma.octo.agritech.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="api")
public class ProductApiController {
    private final ProductRepository productRepository ;

    @Autowired
    public ProductApiController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
}
