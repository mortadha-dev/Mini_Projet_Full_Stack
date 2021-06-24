package oga.stage.product_management.repositories;

import oga.stage.product_management.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query(value = "SELECT * from product Where category_id=:idb ", nativeQuery = true)
     List<Product> showProductsInCat(@Param("idb") long idb);


}
