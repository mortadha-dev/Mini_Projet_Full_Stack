package oga.stage.product_management.services;

import oga.stage.product_management.entities.Product;
import oga.stage.product_management.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.sql.Timestamp;
@Service
public class ProductService {

    private final ProductRepository productRepository ;

    @Autowired
    ProductService(ProductRepository productRepository){
        this.productRepository=productRepository ;
    }

    public void addProduct(Product product){
        var timestamp = new Timestamp(System.currentTimeMillis());
        product.setDateCreation(timestamp);
        product.setDateModif(null);
        productRepository.save(product);
    }
    public void updateProduct(Product product){

        var timestamp = new Timestamp(System.currentTimeMillis());
        product.setDateModif(timestamp);
        productRepository.save(product);
    }
}
