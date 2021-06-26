package oga.stage.product_management.controllers;
import oga.stage.product_management.entities.Category;
import oga.stage.product_management.entities.Product;
import oga.stage.product_management.exceptions.ResourceNotFoundException;
import oga.stage.product_management.services.CategoryService;
import oga.stage.product_management.services.ExportProductService;
import oga.stage.product_management.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/products")
public class ProductController {


    private final  ProductService productService;
    private final CategoryService categoryService;

    @Autowired
    ProductController(ProductService productService,CategoryService categoryService)
    {
        this.productService = productService;
        this.categoryService=categoryService ;
    }



    @CrossOrigin(origins = "http://localhost:8085")
    @PostMapping("/AddProduct/{catid}")
    public void addProduct(@RequestBody Product product, @PathVariable("catid") long catitd) {
        Category cat = categoryService.findCategory(catitd).orElseThrow(() -> new ResourceNotFoundException("category not found with id :" + catitd));
        product.setCategory(cat);
        if(!product.isDisponible()){
            product.setQuantity(0);
        }
        productService.addProduct(product);

    }

    @GetMapping("/showproducts")
    public List<Product> showProducts() {
        return productService.showProducts();
    }

    @GetMapping("/findbycatid/{catid}")
    public List<Product> showProductsInCat(@PathVariable("catid") long catid) {
        return productService.ShowProductsInCat(catid);
    }

    @GetMapping("/findproduct/{id}")
    public Product showProduct(@PathVariable("id") Long id) {
        return productService.findProduct(id).orElseThrow(() -> new ResourceNotFoundException("Product not found with id :" + id));
    }

    @DeleteMapping("/deleteproduct/{id}")
    public void deleteProduct(@PathVariable("id") Long id) {
        productService.deleteProduct(id);
    }


    @PutMapping("/updateproduct/{id}")
    public void updateProduct(@RequestBody Product product, @PathVariable("id") long id) {
        Product prod = productService.findProduct(id).orElseThrow(() -> new ResourceNotFoundException("product not found with id :" + id));
        prod.setDateCreation(prod.getDateCreation());
        prod.setDisponible(product.isDisponible());
        prod.setQuantity(product.getQuantity());
        prod.setNom(product.getNom());
        productService.updateProduct(prod);

    }

    @GetMapping("/exportpdf/{catid}")
    public ResponseEntity<InputStreamResource> exportTermsPdf(@PathVariable("catid") long catid) {
        List<Product> products = productService.ShowProductsInCat(catid);
        ByteArrayInputStream bais = ExportProductService.productsPDFExport(products);
        var headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=products.pdf");
        return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF).body(new InputStreamResource(bais));
    }

    @GetMapping("/exportexcel/{catid}")
    public ResponseEntity<InputStreamResource> exportTermsExcel(@PathVariable("catid") long catid) throws IOException {
        List<Product> products = productService.ShowProductsInCat(catid);
        ByteArrayInputStream bais = ExportProductService.productsExcelExport(products);
        var headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=products.xlsx");
        return ResponseEntity.ok().headers(headers).body(new InputStreamResource(bais));
    }

}














