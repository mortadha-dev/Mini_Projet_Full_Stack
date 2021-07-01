package oga.stage.product_management.controllers;

import oga.stage.product_management.entities.Category;
import oga.stage.product_management.repositories.CategoryRepository;
import oga.stage.product_management.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    private final CategoryService categoryService ;

    private final CategoryRepository categoryRepository ;


    @Autowired
    public CategoryController(CategoryService categoryService,CategoryRepository categoryRepository){
        this.categoryService=categoryService ;
        this.categoryRepository=categoryRepository ;
    }

    @PostMapping("/AddCategory")
    public void addCategory(@RequestBody Category category){
        categoryService.addCategory(category);
    }

    @GetMapping("/showcategories")
    public List<Category> showCategories(){
        return categoryRepository.findAll();
    }

    @GetMapping("/findcategory/{id}")
    public Optional<Category> showCategory(@PathVariable("id") Long id){
        var oui= categoryRepository.findById(id).isPresent() ;
        if (oui)
        {
          return categoryRepository.findById(id);
        }
      return Optional.empty();
    }

    @DeleteMapping("/deletecategory/{id}")
    public void deleteCategory(@PathVariable("id") Long id){
        categoryRepository.deleteById(id);
    }

    @PutMapping("/updatecategory/{id}")
    public void updateCategory(@RequestBody Category category, @PathVariable("id")long id){
        var oui= categoryRepository.findById(id).isPresent() ;
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