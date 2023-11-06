package tn.esprit.devops_project.controllers;


import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.devops_project.dto.StockDTO;
import tn.esprit.devops_project.entities.Stock;
import tn.esprit.devops_project.services.iservices.IStockService;

import java.util.List;

//@CrossOrigin(origins = "*")
@RestController
@AllArgsConstructor
public class StockController {

    IStockService stockService;

    @PostMapping("/stocks")
    public Stock createStock(@RequestBody StockDTO stockDTO) {
        Stock stock = new Stock();
        stock.setTitle(stockDTO.getTitle());
        return stockService.addStock(stock);
    }

    @GetMapping("/stockk/{id}")
    Stock retrieveStock(@PathVariable Long id){
        return stockService.retrieveStock(id);
    }

    @GetMapping("/stock")
    List<Stock> retrieveAllStock(){
        return stockService.retrieveAllStock();
    }


}
