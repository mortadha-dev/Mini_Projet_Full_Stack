package oga.stage.product_management.controllers;
import oga.stage.product_management.entities.Category;
import oga.stage.product_management.entities.Product;
import oga.stage.product_management.exceptions.ResourceNotFoundException;
import oga.stage.product_management.repositories.CategoryRepository;
import oga.stage.product_management.repositories.ProductRepository;
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

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductRepository productRepository;
    private final  ProductService productService;
    private final CategoryRepository categoryRepository;


    @Autowired
    ProductController(ProductService productService, CategoryRepository categoryRepository, ProductRepository productRepository)
    {
        this.productService = productService;
        this.categoryRepository=categoryRepository ;
        this.productRepository = productRepository ;
    }



    @CrossOrigin(origins = "http://localhost:8085")
    @PostMapping("/AddProduct/{catid}")
    public void addProduct(@RequestBody Product product, @PathVariable("catid") long catitd) {
        Category cat = categoryRepository.findById(catitd).orElseThrow(() -> new ResourceNotFoundException("category not found with id :" + catitd));

        product.setCategory(cat);
        if(!product.isDisponible()){
            product.setQuantity(0);
        }
        productService.addProduct(product);

    }

    @GetMapping("/showproducts")
    public List<Product> showProducts() {
        return productRepository.findAll();
    }

    @GetMapping("/findbycatid/{catid}")
    public List<Product> showProductsInCat(@PathVariable("catid") long catid) {
        return productRepository.showProductsInCat(catid);
    }

    @GetMapping("/findproduct/{id}")
    public Product showProduct(@PathVariable("id") Long id) {
        return productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product not found with id :" + id));
    }

    @DeleteMapping("/deleteproduct/{id}")
    public void deleteProduct(@PathVariable("id") Long id) {
        productRepository.deleteById(id);
    }


    @PutMapping("/updateproduct/{id}")
    public void updateProduct(@RequestBody Product product, @PathVariable("id") long id) {
        Product prod = productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("product not found with id :" + id));
        prod.setDateCreation(prod.getDateCreation());
        prod.setDisponible(product.isDisponible());
        prod.setQuantity(product.getQuantity());
        prod.setNom(product.getNom());
        productService.updateProduct(prod);

    }

    @GetMapping("/exportpdf/{catid}")
    public ResponseEntity<InputStreamResource> exportTermsPdf(@PathVariable("catid") long catid) {
        List<Product> products = productRepository.showProductsInCat(catid);
        ByteArrayInputStream bais = ExportProductService.productsPDFExport(products);
        var headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=products.pdf");
        return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF).body(new InputStreamResource(bais));
    }

    @GetMapping("/exportexcel/{catid}")
    public ResponseEntity<InputStreamResource> exportTermsExcel(@PathVariable("catid") long catid) throws IOException {
        List<Product> products = productRepository.showProductsInCat(catid);
        ByteArrayInputStream bais = ExportProductService.productsExcelExport(products);
        var headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=products.xlsx");
        return ResponseEntity.ok().headers(headers).body(new InputStreamResource(bais));
    }

}














