package br.com.ticotech.gbooks.java.view.sale;

import br.com.ticotech.gbooks.java.controllers.SaleController;
import br.com.ticotech.gbooks.java.view.shared.*;
import br.com.ticotech.gbooks.java.view.shared.Button;
import br.com.ticotech.gbooks.java.view.shared.TextField;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class FinishSection {
    private final SaleController saleController;
    private final SaleScreen saleScreen;
    private final JPanel finishPanel;
    private final TextField cpfField;
    private final Display pointsDisplay;
    private final Display discountDisplay;
    private final CheckBox pointsCheckBox;
    private final Display totalDisplay;
    private final Button cashButton;
    private final Button creditButton;
    private final Button debitButton;
    private final Display changeDisplay;
    private final Button finishButton;

    public TextField getCpfField() {
        return cpfField;
    }
    public Display getDiscountDisplay() {
        return discountDisplay;
    }
    public Display getPointsDisplay() {
        return pointsDisplay;
    }
    public CheckBox getPointsCheckBox() {
        return pointsCheckBox;
    }
    public Display getTotalDisplay() {
        return totalDisplay;
    }
    public Display getChangeDisplay() {
        return changeDisplay;
    }
    public JPanel getFinishPanel(){return finishPanel;}
    public Button getCashButton() {
        return cashButton;
    }
    public Button getCreditButton() {
        return creditButton;
    }
    public Button getDebitButton() {
        return debitButton;
    }
    public Button getFinishButton() {
        return finishButton;
    }

    public FinishSection(SaleController saleController, SaleScreen saleScreen) {
        this.saleController = saleController;
        this.saleScreen = saleScreen;

        finishPanel = new JPanel();
        finishPanel.setBackground(Constants.BABY_BLUE);
        finishPanel.setBounds(40, 685, 1540, 250);
        finishPanel.setLayout(null);

        //Points section start.
        JPanel pointsPanel = new JPanel();
        pointsPanel.setBackground(Color.WHITE);
        pointsPanel.setBounds(25,10, 441,230);
        pointsPanel.setLayout(null);

        cpfField = new TextField("CPF");
        cpfField.setFont(new Font(Constants.DEFAULT_FONT, Font.BOLD,23));
        cpfField.setBackground(Constants.BABY_BLUE);
        cpfField.setBounds(70, 10, 300, 45);
        cpfField.addActionListener(e -> setClient());
        cpfField.setEnabled(false);
        pointsPanel.add(cpfField);

        pointsDisplay = new Display("POINTS");
        pointsDisplay.setFont(new Font(Constants.DEFAULT_FONT, Font.BOLD,23));
        pointsDisplay.setBackground(Constants.BABY_BLUE);
        pointsDisplay.setBounds(70, 67, 300, 45);
        pointsPanel.add(pointsDisplay);

        discountDisplay = new Display("DISCOUNT", Constants.CONFIRM_GREEN);
        discountDisplay.setFont(new Font(Constants.DEFAULT_FONT, Font.BOLD,23));
        discountDisplay.setBackground(Constants.BABY_BLUE);
        discountDisplay.setBounds(70, 124, 300, 45);
        pointsPanel.add(discountDisplay);

        pointsCheckBox = new CheckBox(Constants.POINTS_CHECKED_BOX, Constants.POINTS_NOT_CHECKED_BOX);
        pointsCheckBox.setBounds(100, 177, 245, 45);
        pointsCheckBox.addActionListener(e-> alterUsePoints());
        pointsCheckBox.setEnabled(false);
        pointsPanel.add(pointsCheckBox);

        finishPanel.add(pointsPanel);

        //Payment section start.
        JPanel totalPanel = new JPanel();
        totalPanel.setBackground(Color.WHITE);
        totalPanel.setBounds(480,10, 1030,230);
        totalPanel.setLayout(null);

        totalDisplay = new Display("TOTAL");
        totalDisplay.setFont(new Font(Constants.DEFAULT_FONT, Font.BOLD,23));
        totalDisplay.setBackground(Constants.BABY_BLUE);
        totalDisplay.setBounds(15, 10, 1000, 60);
        totalPanel.add(totalDisplay);

        cashButton = new Button(Constants.CASH_BUTTON);
        cashButton.setBounds(15, 85, 350, 40);
        cashButton.setEnabled(false);
        cashButton.addActionListener(e -> doPayment(1));
        totalPanel.add(cashButton);

        creditButton = new Button(Constants.CREDIT_BUTTON);
        creditButton.setBounds(15, 130, 350, 40);
        creditButton.setEnabled(false);
        creditButton.addActionListener(e -> doPayment(2));
        totalPanel.add(this.creditButton);

        debitButton = new Button(Constants.DEBIT_BUTTON);
        debitButton.setBounds(15, 175, 350, 40);
        debitButton.setEnabled(false);
        debitButton.addActionListener(e -> doPayment(2));
        totalPanel.add(this.debitButton);

        changeDisplay = new Display("CHANGE");
        changeDisplay.setFont(new Font(Constants.DEFAULT_FONT, Font.BOLD,23));
        changeDisplay.setBackground(Constants.BABY_BLUE);
        changeDisplay.setBounds(379, 84, 240, 130);
        totalPanel.add(changeDisplay);

        finishButton = new Button(Constants.FINISH_SALE_BUTTON);
        finishButton.setBounds(630, 80, 385, 142);
        finishButton.setEnabled(false);
        finishButton.addActionListener(e -> finishSale());
        totalPanel.add(finishButton);

        finishPanel.add(totalPanel);
    }

    private void setClient(){
        if (cpfField.getText().length() != 11){
            new Popups("The entered CPF is invalid!",1);
        }
        else {
            saleController.setClient(cpfField.getText());
            pointsDisplay.setText(String.valueOf(saleController.getPoints()));
            discountDisplay.setText("$" + saleController.getDiscount());
            if (pointsCheckBox.isChecked()) {
                pointsCheckBox.alterCheck();
                alterUsePoints();
            }
            pointsCheckBox.setEnabled(true);
        }
    }

    private void alterUsePoints(){
        pointsCheckBox.alterCheck();
        if (pointsCheckBox.isChecked()){
            if(!saleController.usePoints()){
                pointsCheckBox.alterCheck();
            }
        }
        else {saleController.cancelUsePoints();}
        totalDisplay.setText("TOTAL: $" + saleController.getToPay());
    }

    private void finishSale(){
        String cpf = cpfField.getText();
        if (Objects.equals(cpf,"CPF")|| cpf.length()!=11){
            cpf = "unknown";
        }
        if(saleController.finishSale(cpf)) {
            saleScreen.getCartTable().setVisible(false);
            saleScreen.getCartTable().setVisible(true);
            saleScreen.changeElementsStatus("reset");
        }
    }

    private void doPayment(int type) {
        new ValueSelector(type, saleController, this);
    }
}
