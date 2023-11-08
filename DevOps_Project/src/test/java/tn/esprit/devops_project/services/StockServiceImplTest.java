package tn.esprit.devops_project.services;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.devops_project.entities.Stock;
import tn.esprit.devops_project.repositories.StockRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@SpringBootTest
//@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ExtendWith(MockitoExtension.class)
class StockServiceImplTest {
    @Mock
    private StockRepository stockRepository;

    @Test
    public void testAddStock() {
        Stock stockToAdd = new Stock(/* create a Stock instance for testing */);
        stockToAdd.setTitle("Example Stock");
        Stock savedStock =stockToAdd; /* create a Stock instance with an ID after saving */;

        // Mock the behavior of the stockRepository
        Mockito.when(stockRepository.save(stockToAdd)).thenReturn(savedStock);

        StockServiceImpl stockService = new StockServiceImpl(stockRepository);

        Stock result = stockService.addStock(stockToAdd);

        // Assert that the result matches the savedStock
        assertEquals(savedStock, result);
    }

    @Test
    public void RetrieveStock() {
        Long stockId = 20L;
        Stock stockToRetrieve = new Stock(stockId,"title") /* create a Stock instance for testing */;

        // Mock the behavior of the stockRepository
        Mockito.when(stockRepository.findById(stockId)).thenReturn(Optional.of(stockToRetrieve));

        StockServiceImpl stockService = new StockServiceImpl(stockRepository);

        Stock result = stockService.retrieveStock(stockId);

        // Assert that the result matches the stockToRetrieve
        assertEquals(stockToRetrieve, result);
    }

    @Test
    public void testRetrieveAllStock() {
        List<Stock> stockList = new ArrayList<>();
        // Add test data to the stockList

        // Mock the behavior of the stockRepository
        Mockito.when(stockRepository.findAll()).thenReturn(stockList);

        StockServiceImpl stockService = new StockServiceImpl(stockRepository);

        List<Stock> result = stockService.retrieveAllStock();

        // Assert that the result matches the stockList
        assertEquals(stockList, result);
    }
}
