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
import java.sql.Timestamp;
import java.util.Date;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ProductManagementApplicationTests {
	@Autowired
	public CategoryService catserv ;

	@Autowired
	public ProductService prodserv ;

	@Test
	public void addCategory( ){
		Category c = new Category();
		c.setNom("testunit");
		c.setQuantity(20);
		catserv.AddCategory(c);
	}
	@Test
	public void ShowCategories(){
		 catserv.ShowCategories();
	}

	@Test
	public void ShowCategory(){
		long id= 2L ;
		 catserv.FindCategory(id).get();
	}
//	@Test
	public void DeleteCategory(){
		long id = 15L ;
		catserv.DeleteCategory(id);
	}
	@Test
	public void UpdateCategory(){
		long id = 16L ;
		Category cat = catserv.FindCategory(id).get() ;
		Date a = cat.getDateCreation();
		cat.setDateCreation((Timestamp) cat.getDateCreation());
		cat.setQuantity(300);
		cat.setNom("going up ");
		catserv.UpdateCategory(cat);
	}
	@Test
	public void AddProduct() {
		Product product = new Product();
		long catid = 17L ;
		Category cat = catserv.FindCategory(catid).get();
		product.setCategory(cat);
		product.isDisponible();
		product.setNom("moqejaqmin");
		product.setQuantity(8960);
		prodserv.AddProduct(product);
	}
    @Test
	public void ShowProducts() {

		 prodserv.ShowProducts();
	}
	@Test
	public void ShowProductsInCat() {
		long catid = 2L;
		 prodserv.ShowProductsInCat(catid);
	}
    @Test
	public void ShowProduct() {
		long id = 28L ;
		prodserv.FindProduct(id).get();
	}
	@Test
	public void DeleteProduct() {
		long id = 27L ;
		prodserv.DeleteProduct(id);
	}
    @Test
	public void UpdateProduct() {
	long id = 28L ;
	Product prod = prodserv.FindProduct(id).get();
		Date a = prod.getDateCreation();
		prod.setDateCreation(prod.getDateCreation());
		prod.setDisponible(true);
		prod.setQuantity(69);
		prod.setNom("ya tayssirrrrr");
		prodserv.UpdateProduct(prod);

	}





}
