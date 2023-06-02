package br.com.ticotech.gbooks.java.view.sale;

import javax.swing.*;
import java.util.Objects;

public class SaleScreen {
        private final CartSection cartSection;
        //private PointsPanel pointsPanel;
        private final FinishSection finishSection;

        public SaleScreen() {
            cartSection = new CartSection(this);
            //pointsPanel = new PointsPanel();
            finishSection = new FinishSection( this);
        }

        public void disableElements(String getType) {
            if (Objects.equals(getType, "finish") || Objects.equals(getType, "cancel")) {
                finishSection.getToPayDisplay().reset();
                finishSection.getPayedField().reset();
                finishSection.getChangeDisplay().reset();
                cartSection.getCodeBarTextField().reset();
                cartSection.getUnitsTextField().reset();
                finishSection.getCardsButtons().clearSelection();
                finishSection.getPayedField().setEnabled(false);
                finishSection.getFinishButton().setEnabled(false);
                finishSection.getCreditButton().setEnabled(false);
                finishSection.getDebitButton().setEnabled(false);
                cartSection.getButtonCancel().setEnabled(false);
                cartSection.getButtonRemove().setEnabled(false);
            } else if (Objects.equals(getType, "add")) {
                finishSection.getPayedField().setEnabled(true);
                finishSection.getFinishButton().setEnabled(true);
                finishSection.getCreditButton().setEnabled(true);
                finishSection.getDebitButton().setEnabled(true);
                cartSection.getButtonCancel().setEnabled(true);
                cartSection.getButtonRemove().setEnabled(true);
            }
        }

    public CartSection getCartSection(){
            return cartSection;
    }
    public JPanel getCartPanel() {
            return cartSection.getCartPanel();
    }
    public void setVisible(boolean isVisible){
            cartSection.getCartPanel().setVisible(isVisible);
    }
    public FinishSection getFinishSection(){
            return finishSection;
    }
}
