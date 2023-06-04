package br.com.ticotech.gbooks.java.entities;

import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class CartTableModel extends DefaultTableModel {
    private final String[] columns = {"CODE", "TITLE", "UNITS", "UNIT VAL.", "TOTAL VAL."};
    private final List<CartBook> cartBookList;

    public CartTableModel(List<CartBook> cartBookList) {
        this.cartBookList = cartBookList;
    }
    public String[] getColumns() {
        return columns;
    }
    public List<CartBook> getSaleCart() {
        return cartBookList;
    }

    @Override
    public int getRowCount() {
        if(this.cartBookList == null){
            return 0;
        }
        return this.cartBookList.size();
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
            case 0 -> this.cartBookList.get(rowIndex).getCode();
            case 1 -> this.cartBookList.get(rowIndex).getTitle();
            case 2 -> this.cartBookList.get(rowIndex).getUnits();
            case 3 -> this.cartBookList.get(rowIndex).getUnitPrice();
            case 4 -> this.cartBookList.get(rowIndex).getTotalPrice();
            default -> 0;
        };
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0 -> this.cartBookList.get(rowIndex).setCode((String) aValue);
            case 1 -> this.cartBookList.get(rowIndex).setTitle((String) aValue);
            case 2 -> this.cartBookList.get(rowIndex).setUnits((Integer) aValue);
            case 3 -> this.cartBookList.get(rowIndex).setUnitPrice((Double) aValue);
            case 4 -> this.cartBookList.get(rowIndex).setTotalPrice((Double) aValue);
        }
    }

    @Override
    public void addTableModelListener(TableModelListener l) {}

    @Override
    public void removeTableModelListener(TableModelListener l) {}
}
