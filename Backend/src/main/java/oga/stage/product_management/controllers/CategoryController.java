package oga.stage.product_management.controllers;

import oga.stage.product_management.entities.Category;
import oga.stage.product_management.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;


@CrossOrigin
@RestController
@RequestMapping("/categories")
public class CategoryController {


    private final CategoryService categoryService ;

    @Autowired
    public CategoryController(CategoryService categoryService){
        this.categoryService=categoryService ;
    }

    @PostMapping("/AddCategory")
    public void addCategory(@RequestBody Category category){
        categoryService.addCategory(category);
    }

    @GetMapping("/showcategories")
    public List<Category> showCategories(){
        return categoryService.showCategories();
    }

    @GetMapping("/findcategory/{id}")
    public Optional<Category> showCategory(@PathVariable("id") Long id){
        var oui= categoryService.findCategory(id).isPresent() ;
        if (oui)
        {
          return categoryService.findCategory(id);
        }
      return Optional.empty();
    }

    @DeleteMapping("/deletecategory/{id}")
    public void deleteCategory(@PathVariable("id") Long id){
        categoryService.deleteCategory(id);
    }

    @PutMapping("/updatecategory/{id}")
    public void updateCategory(@RequestBody Category category, @PathVariable("id")long id){
        var oui= categoryService.findCategory(id).isPresent() ;
        if (oui)
        {
            var category1 =new Category();
            category1.setDateCreation(category1.getDateCreation());
            category1.setQuantity(category.getQuantity());
            category1.setNom(category.getNom());
            categoryService.updateCategory(category1);
        }


    }

}