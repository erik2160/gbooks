package br.com.ticotech.gbooks.java.view.shared;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

public class Table extends JTable {

    DefaultTableModel model;

    TableCellRenderer cellRenderer = new DefaultTableCellRenderer(){
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column){
        Component component = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        if (component instanceof JLabel label) {
            label.setHorizontalAlignment (SwingConstants.CENTER);
            label.setVerticalAlignment(SwingConstants.CENTER);
        }
        if (row % 2 != 0) {
            component.setBackground(Constants.LIGHT_BABY_BLUE);
        } else {
            component.setBackground(table.getBackground());
        }
        component.setFont (new Font (Constants.DEFAULT_FONT,Font.PLAIN, 20));
        return component;
        }
    };

    public Table(DefaultTableModel model, int [] columnsWidth){
        this.model = model;
        setModel(model);

        for(int i = 0; i<getColumnCount(); i++){
            getColumnModel().getColumn(i).setPreferredWidth(columnsWidth[i]);
        }

        for (int i = 0; i < getColumnCount(); i++) {
            getColumnModel().getColumn(i).setResizable(false);
            getColumnModel().getColumn(i).setCellRenderer(cellRenderer);
        }

        getTableHeader().setReorderingAllowed(false);
        getTableHeader().setBackground (Constants.DARK_BLUE);
        getTableHeader().setForeground (Color.WHITE);
        getTableHeader().setFont (new Font (Constants.DEFAULT_FONT, Font.BOLD, 22));
        setRowHeight (30);
    }

    @Override
    public DefaultTableModel getModel() {
        return model;
    }
}
