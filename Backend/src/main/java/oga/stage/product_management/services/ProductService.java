package oga.stage.product_management.services;

import oga.stage.product_management.entities.Product;
import oga.stage.product_management.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
//testing git

    private final ProductRepository productRepository ;

    @Autowired
    ProductService(ProductRepository productRepository){
        this.productRepository=productRepository ;
    }

    public void addProduct(Product product){
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        System.out.println(timestamp);
        product.setDateCreation(timestamp);
        product.setDateModif(null);
        productRepository.save(product);
    }
    public void updateProduct(Product product){

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        product.setDateModif(timestamp);
        productRepository.save(product);
    }

    public List<Product> showProducts(){
        return productRepository.findAll() ;
    }
    public List<Product> ShowProductsInCat(long id ){

        return productRepository.showProductsInCat(id) ;
    }

    public void deleteProduct (long id ){
        productRepository.deleteById(id);
    }

    public Optional<Product> findProduct(long id){
        return productRepository.findById(id) ;
    }

}
