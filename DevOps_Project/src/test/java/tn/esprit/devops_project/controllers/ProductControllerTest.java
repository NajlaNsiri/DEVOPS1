package tn.esprit.devops_project.controllers;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

import org.mockito.Mock;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;
import tn.esprit.devops_project.dto.ProductDTO;
import tn.esprit.devops_project.entities.Product;
import tn.esprit.devops_project.entities.ProductCategory;
import tn.esprit.devops_project.entities.Stock;
import tn.esprit.devops_project.services.iservices.IProductService;
import tn.esprit.devops_project.services.iservices.IStockService;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class ProductControllerTest {

    @InjectMocks
    private ProductController productController;

    @Mock
    private IProductService productService;
    @Mock
    private IStockService stockService;

    @Test
     void testCreateProduct() {
        // Create a ProductDTO for testing
        ProductDTO productDTO = new ProductDTO();
        productDTO.setIdStock(1L);
        productDTO.setTitle("Test Product");
        productDTO.setPrice(10.0f);
        productDTO.setQuantity(100);
        productDTO.setCategory(ProductCategory.ELECTRONICS);

        Stock mockStock = new Stock();
        when(stockService.retrieveStock(1L)).thenReturn(mockStock);

        // Mock the behavior of productService to return a product
        Product mockProduct = new Product();
        when(productService.addProduct(any(Product.class), eq(1L))).thenReturn(mockProduct);

        // Call the method to be tested
        Product createdProduct = productController.createProduct(productDTO);

        // Assert the result
        assertNotNull(createdProduct);
    }

    @Test
     void testRetrieveProduct() {
        Long productId = 1L;
        Product mockProduct = new Product();
        when(productService.retrieveProduct(productId)).thenReturn(mockProduct);

        Product retrievedProduct = productController.retrieveProduct(productId);

        assertNotNull(retrievedProduct);
        // Add more assertions as needed to validate the behavior
    }

    @Test
   void testRetrieveAllProduct() {
        List<Product> mockProductList = new ArrayList<>();
        when(productService.retreiveAllProduct()).thenReturn(mockProductList);

        List<Product> allProducts = productController.retreiveAllProduct();

        assertNotNull(allProducts);
        // Add more assertions as needed to validate the behavior
    }

    @Test
     void testRetrieveProductStock() {
        Long stockId = 1L;
        List<Product> mockProductList = new ArrayList<>();
        when(productService.retreiveProductStock(stockId)).thenReturn(mockProductList);

        List<Product> productsInStock = productController.retreiveProductStock(stockId);

        assertNotNull(productsInStock);
        // Add more assertions as needed to validate the behavior
    }

    @Test
     void testRetrieveProductByCategory() {
        ProductCategory category = ProductCategory.ELECTRONICS;
        List<Product> mockProductList = new ArrayList<>();
        when(productService.retrieveProductByCategory(category)).thenReturn(mockProductList);

        List<Product> productsByCategory = productController.retrieveProductByCategory(category);

        assertNotNull(productsByCategory);
        // Add more assertions as needed to validate the behavior
    }

    @Test
    void testDeleteProduct() {
        Long productId = 1L;

        // Mock the productService's deleteProduct method
        doNothing().when(productService).deleteProduct(productId);

        // Call the method to be tested
        productController.deleteProduct(productId);

        // Assert that the productService's deleteProduct method was called with the correct productId
        verify(productService, times(1)).deleteProduct(productId);
    }
}