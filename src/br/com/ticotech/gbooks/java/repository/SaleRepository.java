package br.com.ticotech.gbooks.java.repository;

import br.com.ticotech.gbooks.java.entities.CartBook;
import br.com.ticotech.gbooks.java.entities.Sale;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SaleRepository {

    private final List<Sale> saleList = new ArrayList<>();

    public SaleRepository(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String firstDateString = "02/06/2023";
        String secondDateString = "06/06/2023";
        Date firstDate;
        Date secondDate;
        try {
            firstDate = dateFormat.parse(firstDateString);
            secondDate = dateFormat.parse(secondDateString);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        List<CartBook> testList = new ArrayList<>();
        testList.add(new CartBook("8642097535","Pride and Prejudice", 1, 25.20, 25.20));
        testList.add(new CartBook("6927403820","The Catcher in the Rye", 2, 26.20, 52.4));
        List<Double> invoiceList = new ArrayList<>();
        invoiceList.add(21.55);
        invoiceList.add(23.26);
        addSale(new Sale("33344455566", firstDate,testList,invoiceList));
        List<CartBook> testList2 = new ArrayList<>();
        testList2.add(new CartBook("2081649277","The Lord of the Rings", 1, 26.20, 26.20));
        testList2.add(new CartBook("4567890127","The Hunger Games", 2, 30.35, 60.70));
        List<Double> invoiceList2 = new ArrayList<>();
        invoiceList2.add(24.0);
        invoiceList2.add(27.0);
        addSale(new Sale("19122345678", secondDate,testList2,invoiceList2));
    }

    public void addSale(Sale sale){
        saleList.add(sale);
    }

    public List<Sale> getSaleList() {
        return saleList;
    }
}
