package br.com.ticotech.gbooks.java.entities;

import java.util.Date;

public class ReportItem {
    private Date date;
    private String barcode;
    private String title;
    private int units;
    private double finalPrice;
    private double total;
    private double invoiceCost;
    private double profit;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getUnits() {
        return units;
    }

    public void setUnits(int units) {
        this.units = units;
    }

    public double getFinalPrice() {
        return finalPrice;
    }

    public void setFinalPrice(double finalPrice) {
        this.finalPrice = finalPrice;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public double getInvoiceCost() {
        return invoiceCost;
    }

    public void setInvoiceCost(double invoiceCost) {
        this.invoiceCost = invoiceCost;
    }

    public double getProfit() {
        return profit;
    }

    public void setProfit(double profit) {
        this.profit = profit;
    }

    public ReportItem(){

    }
}
