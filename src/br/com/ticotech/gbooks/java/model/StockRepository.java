package br.com.ticotech.gbooks.java.model;

import br.com.ticotech.gbooks.java.entities.Book;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class StockRepository {
    private final List<Book> bookList = new ArrayList<>();

    public StockRepository(){
        addBook(new Book("111", "Book1", "AUTHOR","1", "PUBLISHER", 10, 25.17, 26.20));
        addBook(new Book("222", "Book2", "AUTHOR","1", "PUBLISHER", 10, 25.17, 27.13));
        addBook(new Book("333", "Book3", "AUTHOR","1", "PUBLISHER", 10, 20.17, 22.16));
    }

    public List<Book> getStock(){
        return bookList;
    }

    public Book getBook(String barcode){
        for(Book book : bookList){
            if (Objects.equals(book.getCode(), barcode)){
                return book;
            }
        }
        return null;
    }

    public void addBook(Book book){
        bookList.add(book);
    }

    public void alterUnits(String type, int units, String barcode){
        Book book = getBook(barcode);
        switch (type) {
            case "add" -> book.setUnits(book.getUnits() + units);
            case "remove" -> book.setUnits(book.getUnits() - units);
        }
    }

    public void deleteBook(String barcode){
        bookList.remove(getBook(barcode));
    }
}
