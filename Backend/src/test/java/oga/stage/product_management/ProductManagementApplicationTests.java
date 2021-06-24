package oga.stage.product_management;

import oga.stage.product_management.entities.Category;
import oga.stage.product_management.entities.Product;
import oga.stage.product_management.services.CategoryService;
import oga.stage.product_management.services.ProductService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ProductManagementApplicationTests {
	@Autowired
	public CategoryService categoryService ;

	@Autowired
	public ProductService productService ;

	@Test
	public void addCategory( ){
		Category c = new Category();
		c.setNom("testunit");
		c.setQuantity(20);
		categoryService.addCategory(c);
	}
	@Test
	public void showCategories(){
		 categoryService.showCategories();
	}

	@Test
	public void showCategory(){
		long id= 2L ;
		if(categoryService.findCategory(id).isPresent())
		 categoryService.findCategory(id).get();
	}
//	@Test
	public void deleteCategory(){
		long id = 15L ;
		categoryService.deleteCategory(id);
	}
	@Test
	public void updateCategory(){
		long id = 16L ;
		if(categoryService.findCategory(id).isPresent()){
			Category cat = categoryService.findCategory(id).get() ;
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
		if(categoryService.findCategory(catid).isPresent()){
			Category cat =categoryService.findCategory(catid).get() ;
			product.setCategory(cat);
			//product.isDisponible();
			product.setNom("moqejaqmin");
			product.setQuantity(8960);
			productService.addProduct(product);
		}

	}
    @Test
	public void showProducts() {

		 productService.showProducts();
	}
	@Test
	public void showProductsInCat() {
		long catid = 2L;
		 productService.ShowProductsInCat(catid);
	}
    @Test
	public void showProduct() {
		long id = 28L ;
		productService.findProduct(id);
	}
	@Test
	public void deleteProduct() {
		long id = 27L ;
		productService.deleteProduct(id);
	}
    @Test
	public void updateProduct() {
	long id = 28L ;
	if(productService.findProduct(id).isPresent()){
		Product prod = productService.findProduct(id).get();
		prod.setDateCreation(prod.getDateCreation());
		prod.setDisponible(true);
		prod.setQuantity(69);
		prod.setNom("ya tayssirrrrr");
		productService.updateProduct(prod);

	}


	}





}
