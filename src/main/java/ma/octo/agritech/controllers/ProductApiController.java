package ma.octo.agritech.controllers;

import ma.octo.agritech.domains.Product;
import ma.octo.agritech.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/products")
public class ProductApiController {
    @Autowired
    private ProductService productService;

    @GetMapping(produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public List<Product> getAll() {
        return this.productService.getAll();
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping(produces = {MediaType.APPLICATION_JSON_UTF8_VALUE}, consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public Product store(@RequestBody Product product) {
        return this.productService.save(product);
    }
//
//    @PostMapping(produces = {MediaType.APPLICATION_JSON_UTF8_VALUE}, consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE})
//    public Product store(@RequestBody StoreProductRequest storeProductRequest) {
//        return this.productService.saveByStoreRequest(storeProductRequest);
//    }


}
