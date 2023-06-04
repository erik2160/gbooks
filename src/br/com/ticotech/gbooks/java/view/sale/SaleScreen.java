package br.com.ticotech.gbooks.java.view.sale;

import br.com.ticotech.gbooks.java.controllers.SaleController;
import br.com.ticotech.gbooks.java.view.shared.Display;

import javax.swing.*;
import java.util.Objects;

public class SaleScreen {
        private final CartSection cartSection;
        private final FinishSection finishSection;

        public SaleScreen(SaleController saleController) {
            cartSection = new CartSection(saleController, this);
            finishSection = new FinishSection(saleController, this);
        }

        public void changeElementsStatus(String getType) {
            if (Objects.equals(getType, "reset")) {
                cartSection.getButtonCancel().setEnabled(false);
                cartSection.getButtonRemove().setEnabled(false);
                cartSection.getCodeBarTextField().reset();
                cartSection.getUnitsTextField().reset();
                finishSection.getCashValueField().setEnabled(false);
                finishSection.getCardValueField().setEnabled(false);
                finishSection.getFinishButton().setEnabled(false);
                finishSection.getCreditButton().setEnabled(false);
                finishSection.getDebitButton().setEnabled(false);
                finishSection.getPayedInCashDisplay().reset();
                finishSection.getPayedByCardDisplay().reset();
                finishSection.getTotalPayedDisplay().reset();
                finishSection.getToPayDisplay().reset();
                finishSection.getCashValueField().reset();
                finishSection.getChangeDisplay().reset();
                finishSection.getCardsButtons().clearSelection();
            } else if (Objects.equals(getType, "add")) {
                finishSection.getCashValueField().setEnabled(true);
                finishSection.getCardValueField().setEnabled(true);
                finishSection.getFinishButton().setEnabled(true);
                finishSection.getCreditButton().setEnabled(true);
                finishSection.getDebitButton().setEnabled(true);
                cartSection.getButtonCancel().setEnabled(true);
                cartSection.getButtonRemove().setEnabled(true);
            }
        }
        public Display getToPayDisplay(){return finishSection.getToPayDisplay();}
        public JPanel getCartPanel() {
            return cartSection.getCartPanel();
        }
        public JPanel getFinishPanel() {
            return finishSection.getFinishPanel();
        }

        public void setVisible(boolean isVisible){
                cartSection.getCartPanel().setVisible(isVisible);
                finishSection.getFinishPanel().setVisible(isVisible);
        }

}
