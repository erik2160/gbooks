package br.com.ticotech.gbooks.java.controllers;

import br.com.ticotech.gbooks.java.entities.Book;
import br.com.ticotech.gbooks.java.repository.StockRepository;
import br.com.ticotech.gbooks.java.view.stock.StockTableModel;

import java.util.ArrayList;

public class StockController {
    private StockRepository stockRepository;
    private final StockTableModel stockTableModel;

    public StockTableModel getStockTableModel() {
        return stockTableModel;
    }

    public StockController(StockRepository stockRepository){
        this.stockRepository = stockRepository;
        stockTableModel = new StockTableModel(new ArrayList<>());
        stockTableModel.getStockBookList().add(new Book("999","TITLE","AUTHOR",1, "PUBLISHER",1,10.0,10.0));

    }

}
