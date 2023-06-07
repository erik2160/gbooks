package br.com.ticotech.gbooks.java.view.sale;

import br.com.ticotech.gbooks.java.controllers.SaleController;
import br.com.ticotech.gbooks.java.view.shared.*;
import br.com.ticotech.gbooks.java.view.shared.Button;
import br.com.ticotech.gbooks.java.view.shared.TextField;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class CartSection {
    private final JPanel cartPanel;
    private final TextField barcodeTextField;
    private final TextField unitsTextField;
    private final Table table;
    private final Button buttonCancel;
    private final Button buttonRemove;
    public JPanel getCartPanel(){ return cartPanel;}
    public TextField getBarcodeTextField() {
        return barcodeTextField;
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

    public Table getTable() {
        return table;
    }

    private final SaleController saleController;
    private final SaleScreen saleScreen;


    public CartSection(SaleController saleController, SaleScreen saleScreen) {
        this.saleController = saleController;
        this.saleScreen = saleScreen;

        int[] columnsWidth = {50, 200, 10, 40, 80};
        table = new Table(saleController.getCartTableModel(), columnsWidth);

        cartPanel = new JPanel();
        cartPanel.setLayout(null);
        cartPanel.setBackground(Constants.MID_GRAY);
        cartPanel.setBounds(40, 20, 1540, 660);

        barcodeTextField = new TextField("BARCODE");
        barcodeTextField.setBounds(26, 26, 240, 33);
        barcodeTextField.setFontSize(22);
        cartPanel.add(barcodeTextField);

        unitsTextField = new TextField("UNITS");
        unitsTextField.setBounds(276, 26, 80, 33);
        unitsTextField.setFontSize(22);
        unitsTextField.addActionListener(enterUnit -> addToCart());
        cartPanel.add(unitsTextField);

        Button buttonAdd = new Button("ADD");
        buttonAdd.setBounds(366, 26, 110, 33);
        buttonAdd.addActionListener(addItem -> addToCart());
        cartPanel.add(buttonAdd);

        buttonRemove = new Button("REMOVE", Constants.CANCEL_RED, Color.WHITE);
        buttonRemove.setBounds(1266, 26, 120, 33);
        buttonRemove.setEnabled(false);
        buttonRemove.addActionListener(removeItem ->removeFromCart());
        cartPanel.add(buttonRemove);

        buttonCancel = new Button("CANCEL", Constants.CANCEL_RED, Color.WHITE);
        buttonCancel.setBounds(1394, 26, 120, 33);
        buttonCancel.setEnabled(false);
        buttonCancel.addActionListener(finishSale -> cancelSale());
        cartPanel.add(buttonCancel);

        JScrollPane tableScrollPane = new JScrollPane(table);
        tableScrollPane.setBounds(26, 70, 1488, 560);
        cartPanel.add(tableScrollPane);
    }

    private void addToCart(){
        String barcode = barcodeTextField.getText();
        int units;

        if(Objects.equals(barcode, "BARCODE")){
            new Popups ("The BARCODE field is empty!", 1);
            return;
        }
        else if(Objects.equals(unitsTextField.getText(), "UNITS")){
            new Popups ("The UNITS field is empty!", 1);
            return;
        }
        try{
            units = Integer.parseInt(unitsTextField.getText());
        } catch (NumberFormatException e){
            new Popups ("Invalid type! Enter a number in the UNITS field.", 1);
            return;
        }

        if(saleController.addToCart(barcode,units)){
            table.setVisible(false);
            table.setVisible(true);
            saleScreen.changeElementsStatus("add");
            barcodeTextField.reset();
            unitsTextField.reset();
            barcodeTextField.requestFocus();
            saleScreen.getToPayDisplay().setText(saleController.getToPay());
        }
    }

    private void removeFromCart(){
        String barcode = barcodeTextField.getText();
        String units = unitsTextField.getText();
        int unitsFormatted;

        if(Objects.equals(barcode, "BARCODE")){
            new Popups ("The BARCODE field is empty!", 1);
        }
        else {
            if (Objects.equals(units, "UNITS")) {
                units = "0";
            }
            try {
                unitsFormatted = Integer.parseInt(units);
                if(saleController.removeFromCart(barcode, unitsFormatted)){
                    table.setVisible(false);
                    table.setVisible(true);
                    saleScreen.getToPayDisplay().setText(saleController.getToPay());
                    if (table.getRowCount() ==0){
                        saleScreen.changeElementsStatus("reset");
                    }
                }
            } catch (NumberFormatException e) {
                new Popups("Invalid type! Enter a number in the UNITS field.", 1);
            }
        }
    }

    private void cancelSale(){
        Popups cancelPopup = new Popups("Do you want to cancel the purchase?",2);
        if(cancelPopup.getResponse()){
            saleScreen.changeElementsStatus("reset");
            table.setVisible(false);
            table.setVisible(true);
            saleController.cancelSale();
        }
    }
}
