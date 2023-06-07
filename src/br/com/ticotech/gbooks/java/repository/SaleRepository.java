package br.com.ticotech.gbooks.java.repository;

import br.com.ticotech.gbooks.java.entities.CartBook;
import br.com.ticotech.gbooks.java.entities.Sale;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SaleRepository {

    private List<Sale> saleList = new ArrayList<>();

    public SaleRepository(){
        List<CartBook> testList = new ArrayList<>();
        testList.add(new CartBook("111","Book1", 2, 26.20, 52.4));
        testList.add(new CartBook("222","Book2", 2, 26.20, 52.4));
        addSale(new Sale("33344455566", new Date(),testList));
    }

    public void addSale(Sale sale){
        saleList.add(sale);
    }

    public List<Sale> getSaleList() {
        return saleList;
    }
}
