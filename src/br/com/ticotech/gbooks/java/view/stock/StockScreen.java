package br.com.ticotech.gbooks.java.view.stock;

import br.com.ticotech.gbooks.java.view.shared.ComboBox;
import br.com.ticotech.gbooks.java.view.shared.Constants;
import br.com.ticotech.gbooks.java.view.shared.Table;
import br.com.ticotech.gbooks.java.view.shared.TextField;
import br.com.ticotech.gbooks.java.view.shared.Button;

import javax.swing.*;

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

        Button addButton = new Button("ADD");
        addButton.setBounds(714,26,100,33);
        stockPanel.add(addButton);

        Button editButton = new Button("EDIT");
        editButton.setBounds(824,26,100,33);
        stockPanel.add(editButton);

        String[] columnsName = {"CODE","TITLE","AUTHOR","EDITION","PUBLISHER","SELL PRICE","BUY PRICE"};
        int [] columnsWidth = {90,60,10,10,10,10,10};
        Table table = new Table(columnsName,columnsWidth);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(26,70,899,485);
        stockPanel.add(scrollPane);

        return stockPanel;
    }
}
