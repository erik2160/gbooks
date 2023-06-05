package br.com.ticotech.gbooks.java.controllers;

import br.com.ticotech.gbooks.java.entities.Book;
import br.com.ticotech.gbooks.java.repository.StockRepository;
import br.com.ticotech.gbooks.java.view.stock.StockTableModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class StockController {
    private StockRepository stockRepository;
    private final StockTableModel stockTableModel;
    List<Book> stockList;

    public StockTableModel getStockTableModel() {
        return stockTableModel;
    }

    public StockController(StockRepository stockRepository){
        this.stockRepository = stockRepository;
        stockList = stockRepository.getStock();
        //stockList = new ArrayList<>();
        stockTableModel = new StockTableModel(stockList);
        //TEST
        stockRepository.addToStock(new Book("123", "Book1", "AUTHOR",1, "PUBLISHER", 10, 25.17, 26.20));

    }

    public void doSearch(String search){
        List<Book> newList = new ArrayList<>();
        if(!Objects.equals(search,"")) {
            for (Book book : stockRepository.getStock()) {
                if (book.getCode().contains(search) && !newList.contains(book)) {
                    newList.add(book);
                }if (book.getTitle().toLowerCase().contains(search) && !newList.contains(book)) {
                    newList.add(book);
                }if (book.getAuthor().toLowerCase().contains(search) && !newList.contains(book)) {
                    newList.add(book);
                }if (book.getPublisher().toLowerCase().contains(search) && !newList.contains(book)) {
                    newList.add(book);
                }
            }
        }
        else{
            newList = stockRepository.getStock();
        }
        stockTableModel.setStockBookList(newList);
    }

}
