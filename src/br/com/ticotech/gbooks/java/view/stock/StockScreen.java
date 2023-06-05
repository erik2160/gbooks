package br.com.ticotech.gbooks.java.view.stock;

import br.com.ticotech.gbooks.java.controllers.SaleController;
import br.com.ticotech.gbooks.java.controllers.StockController;
import br.com.ticotech.gbooks.java.entities.Book;
import br.com.ticotech.gbooks.java.view.shared.*;
import br.com.ticotech.gbooks.java.view.shared.Button;
import br.com.ticotech.gbooks.java.view.shared.TextField;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.util.List;
import java.util.Objects;
import java.util.regex.PatternSyntaxException;

public class StockScreen {
    private StockController stockController;
    private final Table table;
    private final JPanel stockPanel;
    private final TextField searchField;
    private ComboBox comboBox;

    public JPanel getStockPanel() {
        return stockPanel;
    }

    public TextField getSearchField() {
        return searchField;
    }

    public StockScreen(StockController stockController){
        this.stockController = stockController;

        int [] columnsWidth = {90,60,10,10,10,10,10,10};
        table = new Table(stockController.getStockTableModel() ,columnsWidth);

        stockPanel = new JPanel();
        stockPanel.setLayout(null);
        stockPanel.setBackground(Constants.MID_GRAY);
        stockPanel.setBounds(40,20,1540,915);

//        String [] itemsList = {"BARCODE", "TITLE", "AUTHOR", "PUBLISHER"};

        searchField = new TextField("SEARCH");
        searchField.setBounds(26,26,240,33);
        stockPanel.add(searchField);
        searchField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                doSearch();
            }
            @Override
            public void removeUpdate(DocumentEvent e) {
                doSearch();
            }
            @Override
            public void changedUpdate(DocumentEvent e) {
                doSearch();
            }
        });

        Button editButton = new Button("EDIT");
        editButton.setBounds(1138,26,120,33);
        stockPanel.add(editButton);
        editButton.setEnabled(false);

        Button addButton = new Button("ADD");
        addButton.setBounds(1266,26,120,33);
        stockPanel.add(addButton);
        addButton.setEnabled(false);

        Button removeButton = new Button("REMOVE", Constants.CANCEL_RED, Color.WHITE);
        removeButton.setBounds(1394,26,120,33);
        stockPanel.add(removeButton);
        removeButton.setEnabled(false);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(26,70,1488,820);
        stockPanel.add(scrollPane);
    }
    public void setVisible(boolean isVisible){
        stockPanel.setVisible(isVisible);
    }

    public void doSearch(){
        table.setVisible(false);
        table.setVisible(true);
        stockController.doSearch(searchField.getText().toLowerCase());
    }
}
