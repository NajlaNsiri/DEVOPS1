package tn.esprit.devops_project.controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.devops_project.dto.ProductDTO;
import tn.esprit.devops_project.entities.Product;
import tn.esprit.devops_project.entities.ProductCategory;
import tn.esprit.devops_project.entities.Stock;
import tn.esprit.devops_project.services.StockServiceImpl;
import tn.esprit.devops_project.services.iservices.IProductService;

import java.util.List;

@RestController
@AllArgsConstructor
//@CrossOrigin(origins = "*")
public class ProductController {

    private final IProductService productService;
    private StockServiceImpl stockService;

    @PostMapping("/products")
    public Product createProduct(@RequestBody ProductDTO productDTO) {
        Stock stock = stockService.retrieveStock(productDTO.getIdStock());

        Product product = new Product();
        product.setTitle(productDTO.getTitle());
        product.setPrice(productDTO.getPrice());
        product.setQuantity(productDTO.getQuantity());
        product.setCategory(productDTO.getCategory());
        product.setStock(stock);
        return productService.addProduct(product, productDTO.getIdStock());
    }

    @GetMapping("/{id}")
    public Product retrieveProduct(@PathVariable Long id){
        return productService.retrieveProduct(id);
    }

    @GetMapping("/Allproducts")
    public List<Product> retrieveAllProduct(){
        return productService.retreiveAllProduct();
    }

    @GetMapping("/stock/{id}")
    public List<Product> retrieveProductStock(@PathVariable Long id){
        return productService.retreiveProductStock(id);
    }

    @GetMapping("/category/{category}")
    public List<Product> retrieveProductByCategory(@PathVariable ProductCategory category){
        return productService.retrieveProductByCategory(category);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id){
        productService.deleteProduct(id);
    }
}
