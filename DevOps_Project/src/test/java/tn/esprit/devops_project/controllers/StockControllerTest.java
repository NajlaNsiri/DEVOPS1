package tn.esprit.devops_project.controllers;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.devops_project.dto.StockDTO;
import tn.esprit.devops_project.entities.Stock;
import tn.esprit.devops_project.services.StockServiceImpl;
import java.util.ArrayList;
import java.util.List;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class StockControllerTest {
    @InjectMocks
    private StockController stockController;

    @Mock
    private StockServiceImpl stockService;



    @Test
    void testCreateStock() {
        StockDTO stockDTO = new StockDTO();
        stockDTO.setTitle("Test Stock");

        Stock stock = new Stock();
        stock.setTitle("Test Stock");

        when(stockService.addStock(any(Stock.class))).thenReturn(stock);

        Stock createdStock = stockController.createStock(stockDTO);

        assertNotNull(createdStock);
        assertEquals("Test Stock", createdStock.getTitle());
    }

    @Test
    void testRetrieveStock() {
        Long stockId = 1L;
        Stock stock = new Stock();
        stock.setIdStock(stockId);

        when(stockService.retrieveStock(stockId)).thenReturn(stock);

        Stock retrievedStock = stockController.retrieveStock(stockId);

        assertNotNull(retrievedStock);
        assertEquals(stockId, retrievedStock.getIdStock());
    }

    @Test
    void testRetrieveAllStock() {
        List<Stock> stockList = new ArrayList<>();
        stockList.add(new Stock());
        stockList.add(new Stock());

        when(stockService.retrieveAllStock()).thenReturn(stockList);

        List<Stock> retrievedStocks = stockController.retrieveAllStock();

        assertNotNull(retrievedStocks);
        assertEquals(2, retrievedStocks.size());
    }
}