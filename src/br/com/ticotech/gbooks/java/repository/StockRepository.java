package br.com.ticotech.gbooks.java.repository;

import br.com.ticotech.gbooks.java.entities.ListStock;

import java.util.ArrayList;
import java.util.List;

public class StockRepository {
    public List<ListStock> getItems(){
        var listStock = new ArrayList<ListStock>();
        listStock.add(new ListStock("CODE1", "Book1", "EDITOR", "PUBLISHER", 10, 25.17));
        listStock.add(new ListStock("CODE2", "Book2", "EDITOR", "PUBLISHER", 10, 50.13));
        listStock.add(new ListStock("CODE3", "Book3", "EDITOR", "PUBLISHER", 10, 10.21));
        listStock.add(new ListStock("CODE4", "Book4", "EDITOR", "PUBLISHER", 10, 13.21));
        listStock.add(new ListStock("CODE5", "Book5", "EDITOR", "PUBLISHER", 10, 14.21));
        listStock.add(new ListStock("CODE6", "Book6", "EDITOR", "PUBLISHER", 10, 19.50));
        return listStock;
    }
}
