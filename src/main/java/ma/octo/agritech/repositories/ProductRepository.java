package ma.octo.agritech.repositories;

import ma.octo.agritech.domains.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface ProductRepository extends CrudRepository<Product, Long> {

    Product findOneByRef(String ref);
}
