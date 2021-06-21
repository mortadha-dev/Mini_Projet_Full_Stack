package oga.stage.product_management.repositories;

import oga.stage.product_management.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query(value = "SELECT * from product Where category_id=:idb ", nativeQuery = true)
    public List<Product> ShowProductsInCat(@Param("idb") long idb);

    @Modifying
    @Transactional
    @Query("UPDATE Product p SET p.Quantity = ?1 WHERE p.id= ?2")
    public void updateprod( int quantity, long idd);

}
