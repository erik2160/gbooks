package br.com.ticotech.gbooks.java.entities;

import java.util.Date;
import java.util.List;

public class Sale {

    private String cpf;
    private Date date;
    private List<CartBook> bookList;

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

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setBookList(List<CartBook> bookList) {
        this.bookList = bookList;
    }
}
