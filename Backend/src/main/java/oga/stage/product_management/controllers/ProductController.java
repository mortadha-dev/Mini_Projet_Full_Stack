package oga.stage.product_management.controllers;

import oga.stage.product_management.entities.Category;
import oga.stage.product_management.entities.Product;
import oga.stage.product_management.exceptions.ResourceNotFoundException;
import oga.stage.product_management.services.CategoryService;
import oga.stage.product_management.services.ExportProductService;
import oga.stage.product_management.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.annotation.AccessType;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayInputStream;
import org.springframework.http.HttpHeaders.* ;

import java.io.IOException;
import java.security.AccessControlContext;
import java.util.Date;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/products")
public class ProductController {


    private ProductService productserv;

    @Autowired
    ProductController(ProductService productserv) {
        this.productserv = productserv;
    }

    @Autowired
    private CategoryService categorieserv;

    @Autowired
    private ExportProductService exp;

    @CrossOrigin(origins = "http://localhost:8085")
    @PostMapping("/AddProduct/{catid}")
    public void AddProduct(@RequestBody Product product, @PathVariable("catid") long catitd) {
        Category cat = categorieserv.FindCategory(catitd).orElseThrow(() -> new ResourceNotFoundException("category not found with id :" + catitd));
        product.setCategory(cat);
        if(!product.isDisponible()){
            product.setQuantity(0);
            productserv.AddProduct(product);
        }
        else{
            productserv.AddProduct(product);
        }

    }

    @GetMapping("/showproducts")
    public List<Product> ShowProducts() {
        return productserv.ShowProducts();
    }

    @GetMapping("/findbycatid/{catid}")
    public List<Product> ShowProductsInCat(@PathVariable("catid") long catid) {
        Category existingCategory = this.categorieserv.FindCategory(catid)
                .orElseThrow(() -> new ResourceNotFoundException("category not found with id :" + catid));
        return productserv.ShowProductsInCat(catid);
    }

    @GetMapping("/findproduct/{id}")
    public Product ShowProduct(@PathVariable("id") Long id) {
        return productserv.FindProduct(id).orElseThrow(() -> new ResourceNotFoundException("Product not found with id :" + id));
    }

    @DeleteMapping("/deleteproduct/{id}")
    public void DeleteProduct(@PathVariable("id") Long id) {
        Product existingproduct = this.productserv.FindProduct(id)
                .orElseThrow(() -> new ResourceNotFoundException("product not found with id :" + id));
        productserv.DeleteProduct(id);
    }


    @PutMapping("/updateproduct/{id}")
    public void UpdateProduct(@RequestBody Product product, @PathVariable("id") long id) {
        Product prod = productserv.FindProduct(id).orElseThrow(() -> new ResourceNotFoundException("product not found with id :" + id));
        Date a = prod.getDateCreation();
        prod.setDateCreation(prod.getDateCreation());
        prod.setDisponible(product.isDisponible());
        prod.setQuantity(product.getQuantity());
        prod.setNom(product.getNom());
        productserv.UpdateProduct(prod);

    }

    @GetMapping("/exportpdf/{catid}")
    public ResponseEntity<InputStreamResource> exportTermsPdf(@PathVariable("catid") long catid) {
        List<Product> products = (List<Product>) productserv.ShowProductsInCat(catid);
        ByteArrayInputStream bais = ExportProductService.productsPDFExport(products);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=products.pdf");
        return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF).body(new InputStreamResource(bais));
    }

    @GetMapping("/exportexcel/{catid}")
    public ResponseEntity<InputStreamResource> exportTermsExcel(@PathVariable("catid") long catid) throws IOException {
        List<Product> products = (List<Product>) productserv.ShowProductsInCat(catid);
        ByteArrayInputStream bais = ExportProductService.productsExcelExport(products);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=products.xlsx");
        return ResponseEntity.ok().headers(headers).body(new InputStreamResource(bais));
    }

}














