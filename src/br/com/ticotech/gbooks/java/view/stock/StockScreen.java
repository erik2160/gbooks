package br.com.ticotech.gbooks.java.view.stock;

import br.com.ticotech.gbooks.java.controllers.StockController;
import br.com.ticotech.gbooks.java.view.shared.*;
import br.com.ticotech.gbooks.java.view.shared.Button;
import br.com.ticotech.gbooks.java.view.shared.TextField;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.util.Objects;

public class StockScreen {
    private final StockController stockController;
    private final Table table;
    private final JPanel stockPanel;
    private final TextField searchField;
    private final JScrollPane scrollPane;

    public JPanel getStockPanel() {
        return stockPanel;
    }

    public Table getTable() {
        return table;
    }

    public StockScreen(StockController stockController){
        this.stockController = stockController;

        int [] columnsWidth = {90,60,10,10,10,10,10,10};
        table = new Table(stockController.getStockTableModel() ,columnsWidth);

        stockPanel = new JPanel();
        stockPanel.setLayout(null);
        stockPanel.setBackground(Constants.BABY_BLUE);
        stockPanel.setBounds(40,20,1540,915);

        searchField = new TextField("SEARCH");
        searchField.setBounds(26,23,247,46);
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

        Button editButton = new Button(Constants.EDIT_STOCK_BUTTON);
        editButton.setBounds(972,20,166,50);
        stockPanel.add(editButton);
        editButton.addActionListener(e -> editBook());

        Button addButton = new Button(Constants.ADD_STOCK_BUTTON);
        addButton.setBounds(1160,20,166,50);
        stockPanel.add(addButton);
        addButton.addActionListener(e -> addBook());

        Button removeButton = new Button(Constants.REMOVE_STOCK_BUTTON);
        removeButton.setBounds(1348,20,166,50);
        stockPanel.add(removeButton);
        removeButton.addActionListener(e -> deleteBook());

        scrollPane = new JScrollPane(table);
        scrollPane.setBounds(26,80,1488,810);
        scrollPane.getViewport().setBackground(Color.WHITE);
        stockPanel.add(scrollPane);
    }
    public void setVisible(boolean isVisible){
        stockPanel.setVisible(isVisible);
        doSearch();
    }

    private void doSearch(){
        table.setVisible(false);
        table.setVisible(true);
        stockController.doSearch(searchField.getText().toLowerCase());
    }
    private void editBook(){
        if (Objects.equals(searchField.getText(),"SEARCH") && table.getSelectedRow()<0){
            new Popups("There is no book selected!", 1);
        }
        else if (!Objects.equals(searchField.getText(), "SEARCH")){
            new StockEditor("EDITION", (String) table.getValueAt(0, 0), stockController);
        } else {
            new StockEditor("EDITION", (String) table.getValueAt(table.getSelectedRow(), 0), stockController);
        }
    }
    private void addBook(){
        new StockEditor("ADD", null, stockController);
        table.setVisible(false);
        table.setVisible(true);
    }

    private void deleteBook(){
        if (Objects.equals(searchField.getText(),"SEARCH") && table.getSelectedRow()<0){
            new Popups("There is no book selected!", 1);
        }
        else {
            String barcode;
            if (!Objects.equals(searchField.getText(), "SEARCH")) {
                barcode =(String) table.getValueAt(0, 0);
            } else {
                barcode =(String) table.getValueAt(table.getSelectedRow(), 0);
            }
            String message = "Do you want to remove the book with code " + barcode + " from stock?";
            Popups popups = new Popups(message,2);
            if(popups.getResponse()) {
                stockController.deleteBook(barcode);
                table.setVisible(false);
                table.setVisible(true);
                doSearch();
            }
        }
    }
}
