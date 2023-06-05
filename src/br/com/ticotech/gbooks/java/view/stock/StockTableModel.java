package br.com.ticotech.gbooks.java.view.stock;

import br.com.ticotech.gbooks.java.entities.Book;

import javax.swing.table.DefaultTableModel;
import java.util.List;

public class StockTableModel extends DefaultTableModel {

    private final String[] columns = {"CODE","TITLE","AUTHOR","EDITION","PUBLISHER","SELL PRICE","BUY PRICE"};
    private final List<Book> stockBookList;

    public List<Book> getStockBookList() {
        return stockBookList;
    }

    public StockTableModel(List<Book> stockBookList){
        this.stockBookList = stockBookList;
    }

    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }
}
