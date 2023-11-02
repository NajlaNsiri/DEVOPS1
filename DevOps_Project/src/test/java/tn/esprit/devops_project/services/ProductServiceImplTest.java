package tn.esprit.devops_project.services;

import org.junit.jupiter.api.MethodOrderer;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.devops_project.entities.Product;

import tn.esprit.devops_project.entities.Stock;
import tn.esprit.devops_project.repositories.ProductRepository;
import tn.esprit.devops_project.repositories.StockRepository;


import static org.assertj.core.api.Assertions.assertThat;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ProductServiceImplTest {

   @InjectMocks
    private  ProductServiceImpl service;
   @Autowired
   private ProductServiceImpl servicep;
   @Mock
    private  ProductRepository productRepository;
   @Autowired
   private  ProductRepository productRepositoryp;

   @Mock
   private  StockRepository stockRepositorys;
    @Autowired
    private StockRepository stockRepository;


    @Test
    void addProduct() {
    Long idStock =1L;
    Stock stock =new Stock();

    when(stockRepository.findById(idStock)).thenReturn(Optional.of(stock));
    Product product =new Product();


    Product productReselt = service.addProduct(product,idStock);

    assertThat(productReselt).isNotNull();

     assertThat(productReselt.getTitle()).isEqualTo("stock not found");
    verify(stockRepository,times(1)).findById(idStock);

    Product productFromDatabase =productRepository.findById(productReselt.getIdProduct()).orElse(null);
    assertThat(productFromDatabase).isNotNull();
    assertThat(productFromDatabase.getTitle()).isEqualTo("stock not found");

    }

   @Test
    void retrieveProduct() {
        Long productId = 1L;
        Product product = new Product();
       when(productRepository.findById(productId)).thenReturn(Optional.of(product));
       Product retrivedProduct = service.retrieveProduct(productId);

       assertThat(retrivedProduct).isNotNull();
      assertEquals(product, retrivedProduct);

   }



   @Test
    void retreiveAllProduct() {
       List<Product> products = Arrays.asList(new Product(), new Product());
       when(productRepository.findAll()).thenReturn(products);

       // Act
       List<Product> retrievedProducts = service.retreiveAllProduct();

       // Assert
      assertThat(retrievedProducts);
       assertEquals(products.size(), retrievedProducts.size());
       verify(productRepository, times(1)).findAll();
   }





    @Test
    void deleteProduct() {

        Long productId = 1L;

       service.deleteProduct(productId);
        verify(productRepository, times(1)).deleteById(productId);
    }

    @Test
    void retreiveProductStock() {
        Long stockId = 1L;
        Product product = new Product();

        when(productRepository.findByStockIdStock(stockId)).thenReturn(Arrays.asList(product));

        List<Product> products = service.retreiveProductStock(stockId);

        assertThat(products).isNotNull();
        assertEquals(1, products.size());
        verify(productRepository, times(1)).findByStockIdStock(stockId);
    }

    }
