package oga.stage.product_management.controllers;

import oga.stage.product_management.entities.Category;
import oga.stage.product_management.entities.Product;
import oga.stage.product_management.services.CategoryService;
import oga.stage.product_management.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;


@CrossOrigin
@RestController
@RequestMapping("/categories")
public class CategoryController {
@Autowired
    private ProductService productserv;

    private CategoryService categoryserv ;

    public CategoryController(CategoryService categoryserv){
        this.categoryserv=categoryserv ;
    }

    @PostMapping("/AddCategory")
    public void AddCategory(@RequestBody Category cat){
        categoryserv.AddCategory(cat);
    }

    @GetMapping("/showcategories")
    public List<Category> ShowCategories(){
        return categoryserv.ShowCategories();
    }

    @GetMapping("/findcategory/{id}")
    public Category ShowCategory(@PathVariable("id") Long id){
        return categoryserv.FindCategory(id).get();
    }

    @DeleteMapping("/deletecategory/{id}")
    public void DeleteCategory(@PathVariable("id") Long id){
        categoryserv.DeleteCategory(id);
    }

    @PutMapping("/updatecategory/{id}")
    public void UpdateCategory(@RequestBody Category category, @PathVariable("id")long id){
        Category cat = categoryserv.FindCategory(id).get() ;
        Date a = cat.getDateCreation();
        cat.setDateCreation((Timestamp) cat.getDateCreation());
        cat.setQuantity(category.getQuantity());
        cat.setNom(category.getNom());
        categoryserv.UpdateCategory(cat);
    }

}