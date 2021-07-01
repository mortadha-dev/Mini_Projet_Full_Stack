package oga.stage.product_management.services;

import oga.stage.product_management.entities.Category;
import oga.stage.product_management.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository ;

    @Autowired
    CategoryService(CategoryRepository categoryRepository){
        this.categoryRepository=categoryRepository ;
    }

    public void addCategory(Category category){
        var timestamp = new Timestamp(System.currentTimeMillis());
        category.setDateCreation(timestamp);
        category.setDateModif(null);
        categoryRepository.save(category);
    }

    public void updateCategory(Category category){
        var timestamp = new Timestamp(System.currentTimeMillis());
        category.setDateModif(timestamp);
        categoryRepository.save(category);
    }











}
