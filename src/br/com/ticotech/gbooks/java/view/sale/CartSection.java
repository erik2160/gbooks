package br.com.ticotech.gbooks.java.view.sale;

import br.com.ticotech.gbooks.java.controllers.SaleController;
import br.com.ticotech.gbooks.java.view.shared.*;
import br.com.ticotech.gbooks.java.view.shared.Button;
import br.com.ticotech.gbooks.java.view.shared.TextField;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.Objects;

public class CartSection {
    private final JPanel cartPanel;
    private final TextField codeBarTextField;
    private final TextField unitsTextField;
    private final Table table;
    private final Button buttonCancel;
    private final Button buttonRemove;
    public JPanel getCartPanel(){ return cartPanel;}
    public TextField getCodeBarTextField() {
        return codeBarTextField;
    }
    public TextField getUnitsTextField() {
        return unitsTextField;
    }
    public Button getButtonCancel() {
        return buttonCancel;
    }
    public Button getButtonRemove() {
        return buttonRemove;
    }
    public DefaultTableModel getModel() {
        return table.getModel();
    }
    private final SaleController saleController;


    public CartSection(SaleController saleController, SaleScreen saleScreen) {
        this.saleController = saleController;

        cartPanel = new JPanel();
        cartPanel.setLayout(null);
        cartPanel.setBackground(Constants.DARK_GRAY);
        cartPanel.setBounds(40, 20, 950, 335);

        codeBarTextField = new TextField("BARCODE");
        codeBarTextField.setBounds(26, 26, 240, 33);
        codeBarTextField.setFontSize(22);
        cartPanel.add(codeBarTextField);

        unitsTextField = new TextField("UNITS");
        unitsTextField.setBounds(276, 26, 80, 33);
        unitsTextField.setFontSize(22);
        unitsTextField.addActionListener(enterUnit -> {
            codeBarTextField.requestFocus();
            if (addToCart()) {
                saleScreen.disableElements("add");}
        });
        cartPanel.add(unitsTextField);

        Button buttonAdd = new Button("ADD");
        buttonAdd.setBounds(366, 26, 110, 33);
        buttonAdd.addActionListener(addItem -> {
            codeBarTextField.requestFocus();
            if (addToCart()) {
                saleScreen.disableElements("add");}
        });
        cartPanel.add(buttonAdd);

        buttonRemove = new Button("REMOVE", Constants.CANCEL_RED, Color.WHITE);
        buttonRemove.setBounds(678, 26, 120, 33);
        buttonRemove.setEnabled(false);
        buttonRemove.addActionListener(removeItem -> removeFromCart());
        cartPanel.add(buttonRemove);

        buttonCancel = new Button("CANCEL", Constants.CANCEL_RED, Color.WHITE);
        buttonCancel.setBounds(806, 26, 120, 33);
        buttonCancel.setEnabled(false);
        buttonCancel.addActionListener(finishSale -> {
            if (saleController.finishSale("cancel")) { //TODO
                saleScreen.disableElements("cancel");}
        });
        cartPanel.add(buttonCancel);

        String[] columnsName = {"CODE", "TITLE", "UNITS", "UNIT VAL.", "TOTAL VAL."};
        int[] columnsWidth = {50, 200, 10, 40, 80};
        table = new Table(columnsName, columnsWidth);

        JScrollPane tableScrollPane = new JScrollPane(table);
        tableScrollPane.setBounds(26, 70, 899, 240);
        cartPanel.add(tableScrollPane);
    }

    private boolean addToCart(){
        String barcode = codeBarTextField.getText();
        int units;

        if(Objects.equals(barcode, "BARCODE")){
            new Popups ("The BARCODE field is empty!", 1);
            return false;
        }

        if(Objects.equals(unitsTextField.getText(), "UNITS")){
            new Popups ("The UNITS field is empty!", 1);
            return false;
        }
        try{
            units = Integer.parseInt(unitsTextField.getText());
        } catch (NumberFormatException e){
            new Popups ("Invalid type! Enter a number in the UNITS field.", 1);
            return false;
        }

        return saleController.addToCart(barcode,units);
    }

    private void removeFromCart(){
        String barcode = codeBarTextField.getText();
        String units = unitsTextField.getText();
        int unitsFormatted = 0;
        boolean validInput = true;

        if(Objects.equals(barcode, "BARCODE")){
            validInput = false;
            new Popups ("The BARCODE field is empty!", 1);
        }

        if(Objects.equals(units, "UNITS")){
            units = "0";
        }
        try{
            unitsFormatted = Integer.parseInt(units);
        } catch (NumberFormatException e){
            validInput = false;
            new Popups ("Invalid type! Enter a number in the UNITS field.", 1);
        }

        if (validInput){saleController.removeItemTable(barcode,unitsFormatted);}
    }
}
