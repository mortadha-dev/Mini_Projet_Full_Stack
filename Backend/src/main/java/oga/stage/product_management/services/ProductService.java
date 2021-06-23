package oga.stage.product_management.services;

import oga.stage.product_management.entities.Product;
import oga.stage.product_management.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
//testing git

    private ProductRepository prodrepos ;

    @Autowired
    ProductService(ProductRepository prodrepos){
        this.prodrepos=prodrepos ;
    }

    public void AddProduct(Product product){
        Date date = new Date();
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        System.out.println(timestamp);
        product.setDateCreation(timestamp);
        product.setDateModif(null);
        prodrepos.save(product);
    }
    public void UpdateProduct(Product product){
        Date date = new Date();
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        product.setDateModif(timestamp);
        prodrepos.save(product);
    }

    public List<Product> ShowProducts(){
        return prodrepos.findAll() ;
    }
    public List<Product> ShowProductsInCat(long id ){

        return prodrepos.ShowProductsInCat(id) ;
    }

    public void DeleteProduct (long id ){
        prodrepos.deleteById(id);
    }

    public Optional<Product> FindProduct(long id){
        return prodrepos.findById(id) ;
    }

}
