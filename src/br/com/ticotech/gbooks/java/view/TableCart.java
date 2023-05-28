package br.com.ticotech.gbooks.java.view;

import br.com.ticotech.gbooks.java.entities.SaleCart;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import java.util.List;

public class TableCart implements TableModel {
    private final String[] columns = {"CODE", "TITLE", "UNITS", "UNIT VAL.", "TOTAL VAL."};
    private final List<SaleCart> saleCart;
    public TableCart(List<SaleCart> saleCart) {
        this.saleCart = saleCart;
    }
    public String[] getColumns() {
        return columns;
    }
    public List<SaleCart> getSaleCart() {
        return saleCart;
    }

    @Override
    public int getRowCount() {
        if(this.saleCart == null){
            return 0;
        }
        return this.saleCart.size();
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
            case 0 -> this.saleCart.get(rowIndex).getCode();
            case 1 -> this.saleCart.get(rowIndex).getTitle();
            case 2 -> this.saleCart.get(rowIndex).getUnits();
            case 3 -> this.saleCart.get(rowIndex).getUnitPrice();
            case 4 -> this.saleCart.get(rowIndex).getTotalPrice();
            default -> 0;
        };
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0 -> this.saleCart.get(rowIndex).setCode((String) aValue);
            case 1 -> this.saleCart.get(rowIndex).setTitle((String) aValue);
            case 2 -> this.saleCart.get(rowIndex).setUnits((Integer) aValue);
            case 3 -> this.saleCart.get(rowIndex).setUnitPrice((Double) aValue);
            case 4 -> this.saleCart.get(rowIndex).setTotalPrice((Double) aValue);
        }
    }

    @Override
    public void addTableModelListener(TableModelListener l) {}

    @Override
    public void removeTableModelListener(TableModelListener l) {}
}
