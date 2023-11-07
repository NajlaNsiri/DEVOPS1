package tn.esprit.devops_project.controllers;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import tn.esprit.devops_project.dto.StockDTO;
import tn.esprit.devops_project.entities.Stock;
import tn.esprit.devops_project.services.StockServiceImpl;
import java.util.ArrayList;
import java.util.List;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

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

        assertNotNull(createdStock); // Check if the createdStock is not null
        assertEquals("Test Stock", createdStock.getTitle()); // Check if the title matches the expected value
    }

    @Test
     void testRetrieveStock() {
        Long stockId = 1L;
        Stock stock = new Stock();
        stock.setIdStock(stockId);

        when(stockService.retrieveStock(stockId)).thenReturn(stock);

        Stock retrievedStock = stockController.retrieveStock(stockId);

        assertNotNull(retrievedStock); // Check if the retrievedStock is not null
        assertEquals(stockId, retrievedStock.getIdStock()); // Check if the ID matches the expected value
    }

    @Test
     void testRetrieveAllStock() {
        List<Stock> stockList = new ArrayList<>();
        stockList.add(new Stock());
        stockList.add(new Stock());

        when(stockService.retrieveAllStock()).thenReturn(stockList);

        List<Stock> retrievedStocks = stockController.retrieveAllStock();

        assertNotNull(retrievedStocks); // Check if the retrievedStocks list is not null
        assertEquals(2, retrievedStocks.size()); // Check if the list contains the expected number of elements
    }
}