package br.com.ticotech.gbooks.java.view.sale;

import br.com.ticotech.gbooks.java.controllers.SaleController;
import br.com.ticotech.gbooks.java.view.shared.Display;
import br.com.ticotech.gbooks.java.view.shared.Table;

import javax.swing.*;
import java.util.Objects;

public class SaleScreen {
    private final CartSection cartSection;
    private final FinishSection finishSection;
    private final SaleController saleController;
    public Display getTotalDisplay(){return finishSection.getTotalDisplay();}
    public Table getCartTable(){return cartSection.getTable();}
    public JPanel getCartPanel() {
        return cartSection.getCartPanel();
    }
    public JPanel getFinishPanel() {
        return finishSection.getFinishPanel();
    }

    public SaleScreen(SaleController saleController) {
        this.saleController = saleController;
        cartSection = new CartSection(saleController, this);
        finishSection = new FinishSection(saleController, this);
    }

    public void changeElementsStatus(String getType) {
        if (Objects.equals(getType, "reset")) {
            cartSection.getBarcodeTextField().reset();
            cartSection.getUnitsTextField().reset();
            cartSection.getButtonRemove().setEnabled(false);
            cartSection.getButtonCancel().setEnabled(false);
            finishSection.getCpfField().reset();
            finishSection.getCpfField().setEnabled(true);
            finishSection.getPointsDisplay().reset();
            finishSection.getDiscountDisplay().reset();
            finishSection.getPointsCheckBox().setEnable(false);
            finishSection.getTotalDisplay().reset();
            finishSection.getChangeDisplay().reset();
            finishSection.getCashButton().setEnabled(false);
            finishSection.getCreditButton().setEnabled(false);
            finishSection.getDebitButton().setEnabled(false);
            finishSection.getFinishButton().setEnabled(false);
        } else if (Objects.equals(getType, "add")) {
            cartSection.getButtonCancel().setEnabled(true);
            cartSection.getButtonRemove().setEnabled(true);
            finishSection.getCpfField().setEnabled(true);
            finishSection.getCashButton().setEnabled(true);
            finishSection.getFinishButton().setEnabled(true);
            finishSection.getCreditButton().setEnabled(true);
            finishSection.getDebitButton().setEnabled(true);
        }
    }
    public void reset(){
        changeElementsStatus("reset");
        saleController.cancelSale();
    }

    public void setVisible(boolean isVisible) {
        cartSection.getCartPanel().setVisible(isVisible);
        finishSection.getFinishPanel().setVisible(isVisible);
    }

}
