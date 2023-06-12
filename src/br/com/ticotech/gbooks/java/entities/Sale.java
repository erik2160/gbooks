package br.com.ticotech.gbooks.java.entities;

import java.util.Date;
import java.util.List;

public class Sale {

    private final String cpf;
    private final Date date;
    private final List<CartBook> bookList;

    public Sale(String cpf, Date date, List<CartBook> bookList){
        this.cpf = cpf;
        this.date = date;
        this.bookList = bookList;
    }

    public String getCpf() {
        return cpf;
    }

    public Date getDate() {
        return date;
    }

    public List<CartBook> getBookList() {
        return bookList;
    }
}
