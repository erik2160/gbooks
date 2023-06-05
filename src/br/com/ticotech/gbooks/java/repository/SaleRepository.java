package br.com.ticotech.gbooks.java.repository;

import br.com.ticotech.gbooks.java.entities.Sale;

import java.util.ArrayList;
import java.util.List;

public class SaleRepository {

    private List<Sale> saleList = new ArrayList<>();

    public SaleRepository(){

    }

    public void addSale(Sale sale){saleList.add(sale);}

    public List<Sale> getSaleList() {
        return saleList;
    }
}
