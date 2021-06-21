package oga.stage.product_management.services;


import oga.stage.product_management.entities.Category;
import oga.stage.product_management.entities.Product;
import oga.stage.product_management.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    private CategoryRepository catrepos ;

    @Autowired
    CategoryService(CategoryRepository catrepos){
        this.catrepos=catrepos ;
    }

    public void AddCategory(Category category){
        Date date = new Date();
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        System.out.println(timestamp);
        category.setDateCreation(timestamp);
        category.setDateModif(null);
        catrepos.save(category);
    }

    public List<Category>  ShowCategories(){
        return catrepos.findAll() ;
    }

    public void DeleteCategory (long id ){
        catrepos.deleteById(id);
    }
    public Optional<Category> FindCategory(long id){
        return catrepos.findById(id) ;
    }

    public void UpdateCategory(Category category){
        Date date = new Date();
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        category.setDateModif(timestamp);
        catrepos.save(category);
    }











}
