package ma.octo.agritech.controllers;

import ma.octo.agritech.domains.Product;
import ma.octo.agritech.requests.StoreProductRequest;
import ma.octo.agritech.requests.UpdateProductRequest;
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
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping(value = "/{id}",produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public Product show(@PathVariable("id") Long id) {

        return this.productService.getById(id);
    }
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping(value = "/{id}",produces = {MediaType.APPLICATION_JSON_UTF8_VALUE}, consumes ={ MediaType.APPLICATION_JSON_UTF8_VALUE})
    public Product update(@PathVariable("id") Long id, @RequestBody UpdateProductRequest updateProductRequest ) {

        return this.productService.saveByUpdateRequest(id, updateProductRequest);
    }
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public void destroy(@PathVariable("id")  Long id) {
        this.productService.deleteById(id);
    }





}
