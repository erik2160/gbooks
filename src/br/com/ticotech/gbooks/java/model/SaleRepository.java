package br.com.ticotech.gbooks.java.model;

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
        testList.add(new CartBook("111","Book1", 2, 26.20, 52.4));
        testList.add(new CartBook("222","Book2", 2, 26.20, 52.4));
        addSale(new Sale("33344455566", firstDate,testList));
        List<CartBook> testList2 = new ArrayList<>();
        testList2.add(new CartBook("222","Book2", 1, 26.20, 26.20));
        testList2.add(new CartBook("333","Book3", 2, 26.20, 52.4));
        addSale(new Sale("19122345678", secondDate,testList2));
    }

    public void addSale(Sale sale){
        saleList.add(sale);
    }

    public List<Sale> getSaleList() {
        return saleList;
    }
}
