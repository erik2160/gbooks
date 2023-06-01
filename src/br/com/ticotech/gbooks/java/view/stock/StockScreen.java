package br.com.ticotech.gbooks.java.view.stock;

import br.com.ticotech.gbooks.java.view.shared.ComboBox;
import br.com.ticotech.gbooks.java.view.shared.Constants;
import br.com.ticotech.gbooks.java.view.shared.Table;
import br.com.ticotech.gbooks.java.view.shared.TextField;
import br.com.ticotech.gbooks.java.view.shared.Button;

import javax.swing.*;
import java.awt.*;

public class StockScreen {
    public JPanel insertStockPanel(){
        JPanel stockPanel = new JPanel();
        stockPanel.setLayout(null);
        stockPanel.setBackground(Constants.DARK_GRAY);
        stockPanel.setBounds(40,20,950,582);

        String [] itemsList = {"BARCODE", "TITLE", "AUTHOR", "PUBLISHER"};
        ComboBox comboBox = new ComboBox(itemsList);
        comboBox.setBounds(26,26,140,33);
        stockPanel.add(comboBox);

        TextField search = new TextField("SEARCH");
        search.setBounds(176,26,240,33);
        stockPanel.add(search);

        Button editButton = new Button("EDIT");
        editButton.setBounds(604,26,100,33);
        stockPanel.add(editButton);
        editButton.addActionListener(e -> new EditStock("EDIT BOOK"));

        Button addButton = new Button("ADD");
        addButton.setBounds(714,26,100,33);
        stockPanel.add(addButton);
        addButton.addActionListener(e -> new EditStock("ADD BOOK"));

        Button removeButton = new Button("REMOVE", Constants.CANCEL_RED, Color.WHITE);
        removeButton.setBounds(824,26,100,33);
        stockPanel.add(removeButton);

        String[] columnsName = {"CODE","TITLE","AUTHOR","EDITION","PUBLISHER", "UNITS","SELL PRICE","INVOICE PRICE"};
        int [] columnsWidth = {50,50,30,5,30,5,40,40};
        Table table = new Table(columnsName,columnsWidth);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(26,70,899,485);
        stockPanel.add(scrollPane);

        return stockPanel;
    }
}
