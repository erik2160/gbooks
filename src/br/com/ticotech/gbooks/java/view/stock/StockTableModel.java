package br.com.ticotech.gbooks.java.view.stock;

import br.com.ticotech.gbooks.java.entities.Book;

import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class StockTableModel extends DefaultTableModel {

    private final String[] columns = {"CODE","TITLE","AUTHOR","EDITION","PUBLISHER","UNITS","FINAL PRICE","INVOICE PRICE"};
    private final List<Book> stockBookList;

    public List<Book> getStockBookList() {
        return stockBookList;
    }

    public StockTableModel(List<Book> stockBookList){
        this.stockBookList = stockBookList;
    }

    public String[] getColumns() {
        return columns;
    }
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    @Override
    public int getRowCount() {
        if(this.stockBookList == null){
            return 0;
        }
        return this.stockBookList.size();
    }

    @Override
    public int getColumnCount() {
        return getColumns().length;
    }

    @Override
    public String getColumnName(int columnIndex) {
        return getColumns()[columnIndex];
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return null;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return switch (columnIndex) {
            case 0 -> this.stockBookList.get(rowIndex).getCode();
            case 1 -> this.stockBookList.get(rowIndex).getTitle();
            case 2 -> this.stockBookList.get(rowIndex).getAuthor();
            case 3 -> this.stockBookList.get(rowIndex).getEdition();
            case 4 -> this.stockBookList.get(rowIndex).getPublisher();
            case 5 -> this.stockBookList.get(rowIndex).getUnits();
            case 6 -> this.stockBookList.get(rowIndex).getFinalPrice();
            case 7 -> this.stockBookList.get(rowIndex).getInvoicePrice();
            default -> 0;
        };
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0 -> this.stockBookList.get(rowIndex).setCode((String) aValue);
            case 1 -> this.stockBookList.get(rowIndex).setTitle((String) aValue);
            case 2 -> this.stockBookList.get(rowIndex).setAuthor((String) aValue);
            case 3 -> this.stockBookList.get(rowIndex).setEdition((Integer) aValue);
            case 4 -> this.stockBookList.get(rowIndex).setPublisher((String) aValue);
            case 5 -> this.stockBookList.get(rowIndex).setUnits((Integer) aValue);
            case 6 -> this.stockBookList.get(rowIndex).setFinalPrice((Double) aValue);
            case 7 -> this.stockBookList.get(rowIndex).setInvoicePrice((Double) aValue);
        }
    }

    @Override
    public void addTableModelListener(TableModelListener l) {}

    @Override
    public void removeTableModelListener(TableModelListener l) {}
}
