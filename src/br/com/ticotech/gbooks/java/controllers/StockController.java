package br.com.ticotech.gbooks.java.controllers;

import br.com.ticotech.gbooks.java.entities.Book;
import br.com.ticotech.gbooks.java.model.StockRepository;
import br.com.ticotech.gbooks.java.model.StockTableModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class StockController {
    private final StockRepository stockRepository;
    private final StockTableModel stockTableModel;
    public StockTableModel getStockTableModel() {
        return stockTableModel;
    }

    public StockController(StockRepository stockRepository){
        this.stockRepository = stockRepository;
        List<Book> stockList = stockRepository.getStock();
        stockTableModel = new StockTableModel(stockList);
    }

    public void doSearch(String search){
        List<Book> newList = new ArrayList<>();
        if(!Objects.equals(search,"") && !Objects.equals(search,"search" )) {
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

    public Book getBook(String barcode){
        return stockRepository.getBook(barcode);
    }

    public void addBook(Book book){
        stockRepository.addBook(book);
    }
    public void deleteBook(String barcode){
        stockRepository.deleteBook(barcode);
    }

}
