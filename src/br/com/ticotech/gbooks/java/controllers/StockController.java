package br.com.ticotech.gbooks.java.controllers;

import br.com.ticotech.gbooks.java.entities.Book;
import br.com.ticotech.gbooks.java.repository.StockRepository;
import br.com.ticotech.gbooks.java.view.stock.StockTableModel;

import java.util.List;

public class StockController {
    private StockRepository stockRepository;
    private final StockTableModel stockTableModel;

    public StockTableModel getStockTableModel() {
        return stockTableModel;
    }

    public StockController(StockRepository stockRepository){
        this.stockRepository = stockRepository;
        List<Book> stockList = stockRepository.getStock();
        stockTableModel = new StockTableModel(stockList);
        //TEST
        stockRepository.addToStock(new Book("123", "Book1", "AUTHOR",1, "PUBLISHER", 10, 25.17, 26.20));

    }

}
