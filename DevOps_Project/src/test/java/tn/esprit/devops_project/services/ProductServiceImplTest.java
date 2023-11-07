package tn.esprit.devops_project.services;

import org.junit.jupiter.api.MethodOrderer;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
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
    private  StockServiceImpl services ;
    @InjectMocks
    private  ProductServiceImpl service;
    @Autowired
    private ProductServiceImpl servicep;
    @Mock
    private  ProductRepository productRepository;
    @Autowired
    private  ProductRepository productRepositoryp;


    @Autowired
    private  StockRepository stockRepositorys;
    @Mock
    private StockRepository stockRepository;


    @Test
    void addProduct() {

        Stock stock = new Stock();
        stock.setIdStock(1L);

        // Créez un objet Product à ajouter
        Product productToAdd = new Product();
        productToAdd.setTitle("Sample Product");

        // Définissez le comportement simulé pour le stockRepository
        Mockito.when(stockRepository.findById(Mockito.anyLong())).thenReturn(java.util.Optional.of(stock));

        // Définissez le comportement simulé pour le productRepository
        Mockito.when(productRepository.save(Mockito.any(Product.class))).thenReturn(productToAdd);

        // Appelez la méthode addProduct
        Product savedProduct = service.addProduct(productToAdd, 1L);

        // Vérifiez si le stock a été correctement défini sur le produit ajouté
        assertEquals(stock, savedProduct.getStock());

        // Vérifiez si le produit ajouté correspond à celui retourné par le productRepository
        assertEquals("Sample Product", savedProduct.getTitle());

        /* Long idStock =1L;
    Stock stock =new Stock();

    when(stockRepository.findById(idStock)).thenReturn(Optional.of(stock));
    Product product =new Product();


    Product productReselt = service.addProduct(product,idStock);

    assertThat(productReselt).isNotNull();

     assertThat(productReselt.getTitle()).isEqualTo("stock not found");
    verify(stockRepository,times(1)).findById(idStock);

    Product productFromDatabase =productRepository.findById(productReselt.getIdProduct()).orElse(null);
    assertThat(productFromDatabase).isNotNull();
    assertThat(productFromDatabase.getTitle()).isEqualTo("stock not found"); */
        // Créez un objet Stock pour simuler la recherche en base de données
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
        // Créez une liste de produits simulée
        Product product1 = new Product();
        product1.setIdProduct(1L);
        product1.setTitle("Product 1");

        Product product2 = new Product();
        product2.setIdProduct(2L);
        product2.setTitle("Product 2");

        List<Product> productList = Arrays.asList(product1, product2);

        // Définissez le comportement simulé pour le productRepository
        Mockito.when(productRepository.findAll()).thenReturn(productList);

        // Appelez la méthode retrieveAllProduct
        List<Product> retrievedProducts = service.retreiveAllProduct();

        // Vérifiez si la liste de produits retournée correspond à celle simulée
        assertEquals(2, retrievedProducts.size());
        assertEquals("Product 1", retrievedProducts.get(0).getTitle());
        assertEquals("Product 2", retrievedProducts.get(1).getTitle());
    /* List<Product> products = Arrays.asList(new Product(), new Product());
       when(productRepository.findAll()).thenReturn(products);
       // Act
       List<Product> retrievedProducts = service.retreiveAllProduct();
       // Assert
      assertThat(retrievedProducts);
       assertEquals(products.size(), retrievedProducts.size());
       verify(productRepository, times(1)).findAll(); */

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
