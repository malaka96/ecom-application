package edu.malaka96.ecom_application.controller;

import edu.malaka96.ecom_application.model.dto.ProductRequest;
import edu.malaka96.ecom_application.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping("/api/allproducts")
    public ResponseEntity<?> getAllProducts(){
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @GetMapping("/api/products")
    public ResponseEntity<?> searchProduct(@RequestParam(value = "keyword") String keyword){
        return ResponseEntity.ok(productService.searchProducts(keyword));
    }

    @PostMapping("/api/addproduct")
    public ResponseEntity<?> addProduct(@RequestBody ProductRequest productRequest){
        productService.addProduct(productRequest);
        return ResponseEntity.ok("Product added successfully");
    }

    @PutMapping("/api/updateproduct/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable Long id, @RequestBody ProductRequest productRequest){
        boolean isUpdated = productService.updateProduct(id, productRequest);
        if(isUpdated)
            return ResponseEntity.ok("Product updated successfully");
        else
            return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/api/deleteproduct/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id){
        boolean isDeleted = productService.deleteProduct(id);
        if(isDeleted)
            return ResponseEntity.ok("Product deleted successfully");
        else
            return ResponseEntity.notFound().build();
    }

}
