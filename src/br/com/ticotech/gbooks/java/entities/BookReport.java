package br.com.ticotech.gbooks.java.entities;

import java.text.SimpleDateFormat;
import java.util.Date;

public class BookReport {

    private String date;
    private String barcode;
    private String title;
    private int units;
    private double sellPrice;
    private double invoicePrice;
    private double totalPrice;
    private double profit;
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    public BookReport(Date date, String barcode, String title, int units, double sellPrice, double invoicePrice, double totalPrice, double profit){
        this.date = dateFormat.format(date);
        this.barcode = barcode;
        this.title = title;
        this.units = units;
        this.sellPrice = sellPrice;
        this.invoicePrice = invoicePrice;
        this.totalPrice = totalPrice;
        this.profit = Math.round(profit*100.0)/100.0;
    }

    public String getDate() {
        return date;
    }

    public String getBarcode() {
        return barcode;
    }

    public String getTitle() {
        return title;
    }

    public int getUnits() {
        return units;
    }

    public double getSellPrice() {
        return sellPrice;
    }

    public double getInvoicePrice() {
        return invoicePrice;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public double getProfit() {
        return profit;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setUnits(int units) {
        this.units = units;
    }

    public void setSellPrice(double sellPrice) {
        this.sellPrice = sellPrice;
    }

    public void setInvoicePrice(double invoicePrice) {
        this.invoicePrice = invoicePrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void setProfit(double profit) {
        this.profit = profit;
    }
}
