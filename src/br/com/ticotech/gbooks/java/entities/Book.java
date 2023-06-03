package br.com.ticotech.gbooks.java.entities;

public class Book {
    private String code;
    private String title;
    private String author;
    private int edition;
    private String publisher;
    private int units;
    private double invoicePrice;
    private double finalPrice;

    public Book(String code, String title, String author,int edition, String publisher, int units, double invoicePrice, double finalPrice) {
        this.code = code;
        this.title = title;
        this.author = author;
        this.edition = edition;
        this.publisher = publisher;
        this.units = units;
        this.invoicePrice = invoicePrice;
        this.finalPrice = finalPrice;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getEdition() {
        return edition;
    }

    public void setEdition(int edition) {
        this.edition = edition;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public int getUnits() {
        return units;
    }

    public void setUnits(int units) {
        this.units = units;
    }

    public double getInvoicePrice() {
        return invoicePrice;
    }

    public void setInvoicePrice(double invoicePrice) {
        this.invoicePrice = invoicePrice;
    }

    public void setFinalPrice(double finalPrice) {
        this.finalPrice = finalPrice;
    }

    public double getFinalPrice() {
        return finalPrice;
    }
}
