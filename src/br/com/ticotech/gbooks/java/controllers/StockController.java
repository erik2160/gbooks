package br.com.ticotech.gbooks.java.controllers;

import br.com.ticotech.gbooks.java.repository.StockRepository;

public class StockController {
    private StockRepository stockRepository;
    public StockController(StockRepository stockRepository){
        this.stockRepository = stockRepository;
    }

}
