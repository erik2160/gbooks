package br.com.ticotech.gbooks.java.view.sale;

import br.com.ticotech.gbooks.java.controllers.SaleController;
import br.com.ticotech.gbooks.java.view.shared.*;
import br.com.ticotech.gbooks.java.view.shared.Button;
import br.com.ticotech.gbooks.java.view.shared.TextField;

import javax.swing.*;
import javax.swing.border.AbstractBorder;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.Objects;

public class FinishSection {
    private final SaleController saleController;
    private final SaleScreen saleScreen;
    private final JPanel finishPanel;
    private final TextField cpfField;
    private final Button finishButton;
    private final Button creditButton;
    private final Button debitButton;

    public JPanel getFinishPanel(){return finishPanel;}
    public Button getCreditButton() {
        return creditButton;
    }

    public Button getDebitButton() {
        return debitButton;
    }

//    public Display getChangeDisplay() {
//        return changeDisplay;
//    }

//    public ButtonGroup getCardsButtons() {
//        return cardsButtons;
//    }

    public Button getFinishButton() {
        return finishButton;
    }

    public FinishSection(SaleController saleController, SaleScreen saleScreen) {
        this.saleController = saleController;
        this.saleScreen = saleScreen;

        finishPanel = new JPanel();
        finishPanel.setBackground(Constants.BABY_BLUE);
        finishPanel.setBounds(40, 690, 1540, 250);
        finishPanel.setLayout(null);

        JPanel pointsPanel = new JPanel();
        pointsPanel.setBackground(Color.WHITE);
        pointsPanel.setBounds(25,10, 441,230);
        pointsPanel.setLayout(null);

        cpfField = new TextField("CPF");
        cpfField.setFont(new Font(Constants.DEFAULT_FONT, Font.BOLD,23));
        cpfField.setBackground(Constants.BABY_BLUE);
        cpfField.setBounds(70, 10, 300, 45);
        pointsPanel.add(cpfField);

        Display pointsDisplay = new Display("POINTS");
        pointsDisplay.setFont(new Font(Constants.DEFAULT_FONT, Font.BOLD,23));
        pointsDisplay.setBackground(Constants.BABY_BLUE);
        pointsDisplay.setBounds(70, 67, 300, 45);
        pointsPanel.add(pointsDisplay);

        Display newPriceDisplay = new Display("NEW PRICE", Constants.CONFIRM_GREEN);
        newPriceDisplay.setFont(new Font(Constants.DEFAULT_FONT, Font.BOLD,23));
        newPriceDisplay.setBackground(Constants.BABY_BLUE);
        newPriceDisplay.setBounds(70, 124, 300, 45);
        pointsPanel.add(newPriceDisplay);

//        JCheckBox pointsCheckBox = new JCheckBox("USE POINTS");
//        pointsCheckBox.setBorder(new LineBorder(Constants.DARK_BLUE));
//        pointsCheckBox.setBackground(Color.WHITE);
//        pointsCheckBox.setForeground(Constants.DARK_BLUE);
//        pointsCheckBox.setOpaque(true);
//        pointsCheckBox.setFocusPainted(false);
//        pointsCheckBox.setBounds(148, 177, 215, 45);
//        pointsCheckBox.setFont(new Font(Constants.DEFAULT_FONT, Font.BOLD, 20));
//        pointsPanel.add(pointsCheckBox);

        CheckBox pointsCheckBox = new CheckBox(Constants.POINTS_CHECKED_BOX, Constants.POINTS_NOT_CHECKED_BOX);
        pointsCheckBox.addActionListener(e-> pointsCheckBox.alterCheck());
        pointsCheckBox.setBounds(100, 177, 245, 45);
        pointsPanel.add(pointsCheckBox);

        finishPanel.add(pointsPanel);

        JPanel totalPanel = new JPanel();
        totalPanel.setBackground(Color.WHITE);
        totalPanel.setBounds(480,10, 1030,230);
        totalPanel.setLayout(null);

        Display totalDisplay = new Display("TOTAL");
        totalDisplay.setFont(new Font(Constants.DEFAULT_FONT, Font.BOLD,23));
        totalDisplay.setBackground(Constants.BABY_BLUE);
        totalDisplay.setBounds(15, 10, 1000, 60);
        totalPanel.add(totalDisplay);

        Button cashButton = new Button();
        cashButton.setIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource(Constants.CASH_BUTTON))));
        cashButton.setBorderPainted(false);
        cashButton.setContentAreaFilled(false);
        cashButton.setBounds(15, 80, 350, 45);
        //buttonCash.addActionListener(addItem -> addToCart());
        totalPanel.add(cashButton);

        this.creditButton = new Button();
        this.creditButton.setIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource(Constants.CREDIT_BUTTON))));
        this.creditButton.setBorderPainted(false);
        this.creditButton.setContentAreaFilled(false);
        this.creditButton.setBounds(15, 125, 350, 45);
        //buttonCash.addActionListener(addItem -> addToCart());
        totalPanel.add(this.creditButton);

        this.debitButton = new Button();
        this.debitButton.setIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource(Constants.DEBIT_BUTTON))));
        this.debitButton.setBorderPainted(false);
        this.debitButton.setContentAreaFilled(false);
        this.debitButton.setBounds(15, 170, 350, 45);
        //buttonCash.addActionListener(addItem -> addToCart());
        totalPanel.add(this.debitButton);

        Display changeDisplay = new Display("CHANGE");
        changeDisplay.setFont(new Font(Constants.DEFAULT_FONT, Font.BOLD,23));
        changeDisplay.setBackground(Constants.BABY_BLUE);
        changeDisplay.setBounds(380, 84, 240, 130);
        totalPanel.add(changeDisplay);

        finishButton = new Button();
        finishButton.setIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource(Constants.FINISH_SALE_BUTTON))));
        finishButton.setBorderPainted(false);
        finishButton.setContentAreaFilled(false);
        finishButton.setBounds(625, 80, 390, 140);
        totalPanel.add(finishButton);

        finishPanel.add(totalPanel);


//        creditButton = new RadioButton("Credit");
//        creditButton.setEnabled(false);
//        debitButton = new RadioButton("Debit");
//        debitButton.setEnabled(false);
//        creditButton.setBounds(735, 125, 120, 60);
//        debitButton.setBounds(735, 160, 120, 60);
//        cardsButtons = new ButtonGroup();
//        cardsButtons.add(creditButton);
//        cardsButtons.add(debitButton);
//        totalPanel.add(creditButton);
//        totalPanel.add(debitButton);
//        creditButton.addActionListener(e -> creditCardPayment());
//        debitButton.addActionListener(e -> creditCardPayment());

    }

//    private void cashPayment(){
//        if(Objects.equals(cashValueField.getText(), "")){
//            new Popups("PAYED field is empty!",1);
//        }
//        else{
//            String stringValue = cashValueField.getText();
//            double doubleValue;
//
//            try {
//                doubleValue = Double.parseDouble(stringValue);
//                int payment = saleController.cashPayment(doubleValue);
//
//                switch (payment) {
//                    case (-1) -> {
//                        cardValueField.setText(saleController.getToPay());
//                    }
//                    case (+1) -> changeDisplay.setText(saleController.getCashChange());
//                }
//                payedInCashDisplay.setText(saleController.getPayedInCash());
//                toPayDisplay.setText(saleController.getToPay());
//                totalPayedDisplay.setText(saleController.getTotalPayed());
//
//            } catch (NumberFormatException e) {
//                new Popups("Invalid type! Enter a number in the PAYED field.", 1);
//            }
//        }
//    }

//    private void creditCardPayment(){
//        if(Objects.equals(cardValueField.getText(),"00.00")){
//            new Popups("VALUE field is empty!",1);
//        }
//        else {
//            String stringValue = cardValueField.getText();
//            double doubleValue;
//            try {
//                doubleValue = Double.parseDouble(stringValue);
//                saleController.creditCardPayment(doubleValue);
//
//                payedByCardDisplay.setText(saleController.getPayedByCard());
//                toPayDisplay.setText(saleController.getToPay());
//                totalPayedDisplay.setText(saleController.getTotalPayed());
//            } catch (NumberFormatException e) {
//                new Popups("Invalid type! Enter a number in the PAYED field.", 1);
//            }
//        }
//    }

    private void finishSale(){
        String cpf = cpfField.getText();
        if (Objects.equals(cpf,"CPF")){
            cpf = "unknown";
        }
        if(saleController.finishSale(cpf)) {
            saleScreen.getCartTable().setVisible(false);
            saleScreen.getCartTable().setVisible(true);
            saleScreen.changeElementsStatus("reset");
        }
    }
}
