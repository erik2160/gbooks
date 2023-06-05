package br.com.ticotech.gbooks.java.view.stock;

import br.com.ticotech.gbooks.java.controllers.StockController;
import br.com.ticotech.gbooks.java.view.shared.*;
import br.com.ticotech.gbooks.java.view.shared.Button;
import br.com.ticotech.gbooks.java.view.shared.TextField;

import javax.swing.*;
import java.awt.*;

public class StockScreen {
    private final JPanel stockPanel;

    public JPanel getStockPanel() {
        return stockPanel;
    }

    public StockScreen(StockController stockController){
        int [] columnsWidth = {90,60,10,10,10,10,10,10};
        Table table = new Table(stockController.getStockTableModel() ,columnsWidth);

        stockPanel = new JPanel();
        stockPanel.setLayout(null);
        stockPanel.setBackground(Constants.MID_GRAY);
        stockPanel.setBounds(40,20,1540,915);

        String [] itemsList = {"BARCODE", "TITLE", "AUTHOR", "PUBLISHER"};
        ComboBox comboBox = new ComboBox(itemsList);
        comboBox.setBounds(26,26,140,33);
        stockPanel.add(comboBox);

        TextField search = new TextField("SEARCH");
        search.setBounds(176,26,240,33);
        stockPanel.add(search);

        Button editButton = new Button("EDIT");
        editButton.setBounds(1138,26,120,33);
        stockPanel.add(editButton);

        Button addButton = new Button("ADD");
        addButton.setBounds(1266,26,120,33);
        stockPanel.add(addButton);

        Button removeButton = new Button("REMOVE", Constants.CANCEL_RED, Color.WHITE);
        removeButton.setBounds(1394,26,120,33);
        stockPanel.add(removeButton);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(26,70,1488,820);
        stockPanel.add(scrollPane);
    }
    public void setVisible(boolean isVisible){
        stockPanel.setVisible(isVisible);
    }
}
