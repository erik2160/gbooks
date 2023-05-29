package br.com.ticotech.gbooks.java.view.shared;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

public class Table extends JTable {

    DefaultTableModel model = new DefaultTableModel() {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };

    TableCellRenderer cellRenderer = new DefaultTableCellRenderer(){
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column){
        Component component = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        if (component instanceof JLabel label) {
            label.setHorizontalAlignment (SwingConstants.CENTER);
            label.setVerticalAlignment(SwingConstants.CENTER);
        }
        if (row % 2 != 0) {
            component.setBackground(new Color (240, 240, 240));
        } else {
            component.setBackground(table.getBackground());
        }
        component.setFont (new Font (Constants.DEFAULT_FONT,Font.PLAIN, 16));
        return component;
        }
    };

    public Table(String[] columnsName, int [] columnsWidth){
        setModel(model);

        for(String columnName: columnsName){
            model.addColumn(columnName);
        }

        for(int i = 0; i<getColumnCount(); i++){
            getColumnModel().getColumn(i).setPreferredWidth(columnsWidth[i]);
        }

        for (int i = 0; i < getColumnCount(); i++) {
            getColumnModel().getColumn(i).setResizable(false);
            getColumnModel().getColumn(i).setCellRenderer(cellRenderer);
        }

        getTableHeader().setReorderingAllowed(false);
        getTableHeader ().setBackground (Constants.LIGHT_GRAY);
        getTableHeader ().setForeground (Color.WHITE);
        getTableHeader ().setFont (new Font (Constants.DEFAULT_FONT, Font.BOLD, 18));
        setRowHeight (30);
    }

    @Override
    public DefaultTableModel getModel() {
        return model;
    }
}