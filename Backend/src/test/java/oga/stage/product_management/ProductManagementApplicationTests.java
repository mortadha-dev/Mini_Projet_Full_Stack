package oga.stage.product_management;

import oga.stage.product_management.entities.Category;
import oga.stage.product_management.entities.Product;
import oga.stage.product_management.repositories.CategoryRepository;
import oga.stage.product_management.repositories.ProductRepository;
import oga.stage.product_management.services.CategoryService;
import oga.stage.product_management.services.ProductService;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@SpringBootTest
@RunWith(SpringRunner.class)
public class ProductManagementApplicationTests {

	public CategoryService categoryService ;
	public ProductService productService ;
	public CategoryRepository categoryRepository ;
	public ProductRepository productRepository ;
	@Autowired
	public ProductManagementApplicationTests (CategoryService categoryService , ProductService productService , CategoryRepository categoryRepository, ProductRepository productRepository){
		this.categoryService=categoryService ;
		this.productService=productService ;
		this.categoryRepository=categoryRepository ;
		this.productRepository=productRepository ;
	}

	@Test
	public void addCategory( ){
		Category c = new Category();
		c.setNom("testunit");
		c.setQuantity(20);
		Assertions.assertThat(c.getNom()).isEqualTo("testunit");
		categoryService.addCategory(c);
	}
	@Test
	public void showCategories(){

		categoryRepository.findAll();
	}

	@Test
	public void showCategory(){
		long id= 2L ;
		if(categoryRepository.findById(id).isPresent())
		 categoryRepository.findById(id).get();
	}
//	@Test
	public void deleteCategory(){
		long id = 15L ;
		categoryRepository.deleteById(id);
	}
	@Test
	public void updateCategory(){
		long id = 16L ;
		if(categoryRepository.findById(id).isPresent()){
			Category cat = categoryRepository.findById(id).get() ;
			cat.setDateCreation(cat.getDateCreation());
			cat.setQuantity(300);
			cat.setNom("going up ");
			categoryService.updateCategory(cat);

		}

	}
	@Test
	public void addProduct() {
		Product product = new Product();
		long catid = 17L ;
		if(categoryRepository.findById(catid).isPresent()){
			Category cat =categoryRepository.findById(catid).get() ;
			product.setCategory(cat);
			//product.isDisponible();
			product.setNom("moqejaqmin");
			product.setQuantity(8960);
			productService.addProduct(product);
		}

	}
    @Test
	public void showProducts() {

		 productRepository.findAll();
	}
	@Test
	public void showProductsInCat() {
		long catid = 2L;
		 productRepository.showProductsInCat(catid);
	}
    @Test
	public void showProduct() {
		long id = 28L ;
		productRepository.findById(id);
	}
	@Test
	public void deleteProduct() {
		long id = 27L ;
		productRepository.deleteById(id);
	}
    @Test
	public void updateProduct() {
	long id = 28L ;
	if(productRepository.findById(id).isPresent()){
		Product prod = productRepository.findById(id).get();
		prod.setDateCreation(prod.getDateCreation());
		prod.setDisponible(true);
		prod.setQuantity(69);
		prod.setNom("ya tayssirrrrr");
		productService.updateProduct(prod);

	}


	}





}
