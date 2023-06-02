package br.com.ticotech.gbooks.java.entities;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import java.util.List;

public class CartTable implements TableModel {
    private final String[] columns = {"CODE", "TITLE", "UNITS", "UNIT VAL.", "TOTAL VAL."};
    private final List<CartBook> cartBook;
    public CartTable(List<CartBook> cartBook) {
        this.cartBook = cartBook;
    }
    public String[] getColumns() {
        return columns;
    }
    public List<CartBook> getSaleCart() {
        return cartBook;
    }

    @Override
    public int getRowCount() {
        if(this.cartBook == null){
            return 0;
        }
        return this.cartBook.size();
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
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return switch (columnIndex) {
            case 0 -> this.cartBook.get(rowIndex).getCode();
            case 1 -> this.cartBook.get(rowIndex).getTitle();
            case 2 -> this.cartBook.get(rowIndex).getUnits();
            case 3 -> this.cartBook.get(rowIndex).getUnitPrice();
            case 4 -> this.cartBook.get(rowIndex).getTotalPrice();
            default -> 0;
        };
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0 -> this.cartBook.get(rowIndex).setCode((String) aValue);
            case 1 -> this.cartBook.get(rowIndex).setTitle((String) aValue);
            case 2 -> this.cartBook.get(rowIndex).setUnits((Integer) aValue);
            case 3 -> this.cartBook.get(rowIndex).setUnitPrice((Double) aValue);
            case 4 -> this.cartBook.get(rowIndex).setTotalPrice((Double) aValue);
        }
    }

    @Override
    public void addTableModelListener(TableModelListener l) {}

    @Override
    public void removeTableModelListener(TableModelListener l) {}
}
