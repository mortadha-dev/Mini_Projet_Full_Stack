package oga.stage.product_management.services;


import oga.stage.product_management.entities.Category;
import oga.stage.product_management.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository ;

    @Autowired
    CategoryService(CategoryRepository categoryRepository){
        this.categoryRepository=categoryRepository ;
    }

    public void addCategory(Category category){
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        System.out.println(timestamp);
        category.setDateCreation(timestamp);
        category.setDateModif(null);
        categoryRepository.save(category);
    }

    public List<Category>  showCategories(){
        return categoryRepository.findAll() ;
    }

    public void deleteCategory (long id ){
        categoryRepository.deleteById(id);
    }
    public Optional<Category> findCategory(long id){
        return categoryRepository.findById(id) ;
    }

    public void updateCategory(Category category){
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        category.setDateModif(timestamp);
        categoryRepository.save(category);
    }











}
