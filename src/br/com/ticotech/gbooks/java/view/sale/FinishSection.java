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
    private final TextField cashValueField;
    private final Display changeDisplay;
    private final TextField cardValueField;
    private final RadioButton creditButton;
    private final RadioButton debitButton;
    private final Display payedInCashDisplay;
    private final Display payedByCardDisplay;
    private final Display totalPayedDisplay;
    private final Display toPayDisplay;
    private final ButtonGroup cardsButtons;
    private final Button finishButton;

    public JPanel getFinishPanel(){return finishPanel;}
    public Display getToPayDisplay() {
        return toPayDisplay;
    }

    public TextField getCardValueField() {
        return cardValueField;
    }

    public Display getPayedInCashDisplay() {
        return payedInCashDisplay;
    }

    public Display getPayedByCardDisplay() {
        return payedByCardDisplay;
    }

    public Display getTotalPayedDisplay() {
        return totalPayedDisplay;
    }

    public RadioButton getCreditButton() {
        return creditButton;
    }

    public RadioButton getDebitButton() {
        return debitButton;
    }

    public TextField getCashValueField() {
        return cashValueField;
    }

    public Display getChangeDisplay() {
        return changeDisplay;
    }

    public ButtonGroup getCardsButtons() {
        return cardsButtons;
    }

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
        pointsPanel.setBounds(10,10, 441,230);
        pointsPanel.setLayout(null);

        cpfField = new TextField("CPF");
        cpfField.setFont(new Font(Constants.DEFAULT_FONT, Font.BOLD,23));
        cpfField.setBackground(Constants.BABY_BLUE);
        cpfField.setBorder(new LineBorder(Constants.DARK_BLUE));
        cpfField.setBounds(70, 10, 300, 45);
        pointsPanel.add(cpfField);

        Display pointsDisplay = new Display("POINTS");
        pointsDisplay.setFont(new Font(Constants.DEFAULT_FONT, Font.BOLD,23));
        pointsDisplay.setBackground(Constants.BABY_BLUE);
        pointsDisplay.setBorder(new LineBorder(Constants.DARK_BLUE));
        pointsDisplay.setBounds(70, 67, 300, 45);
        pointsPanel.add(pointsDisplay);

        Display newPriceDisplay = new Display("NEW PRICE", Constants.CONFIRM_GREEN);
        newPriceDisplay.setFont(new Font(Constants.DEFAULT_FONT, Font.BOLD,23));
        newPriceDisplay.setBackground(Constants.BABY_BLUE);
        newPriceDisplay.setBorder(new LineBorder(Constants.DARK_BLUE));
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

        JLabel cashLabel = new JLabel("CASH");
        cashLabel.setBounds(410, 48, 60, 24);
        cashLabel.setForeground(Constants.DARK_BLUE);
        cashLabel.setFont(new Font("Ubuntu", Font.BOLD, 20));
        finishPanel.add(cashLabel);

        JLabel cashPayed = new JLabel("PAYED:");
        cashPayed.setBounds(345, 98, 80, 24);
        cashPayed.setForeground(Constants.DARK_BLUE);
        cashPayed.setFont(new Font("Ubuntu", Font.BOLD, 20));
        finishPanel.add(cashPayed);

        cashValueField = new TextField("00.00");
        cashValueField.setBounds(430, 96, 120, 30);
        cashValueField.setEnabled(false);
        cashValueField.addActionListener(e -> cashPayment());
        finishPanel.add(cashValueField);

        JLabel cashChange = new JLabel("CHANGE:");
        cashChange.setBounds(330, 156, 90, 24);
        cashChange.setForeground(Constants.DARK_BLUE);
        cashChange.setFont(new Font("Ubuntu", Font.BOLD, 20));
        finishPanel.add(cashChange);

        changeDisplay = new Display("00.00");
        changeDisplay.setBounds(430, 154, 120, 30);
        finishPanel.add(changeDisplay);

        JLabel creditCard = new JLabel("CREDIT CARD");
        creditCard.setBounds(670, 48, 154, 24);
        creditCard.setForeground(Constants.DARK_BLUE);
        creditCard.setFont(new Font("Ubuntu", Font.BOLD, 20));
        finishPanel.add(creditCard);

        JLabel cardValue = new JLabel("VALUE:");
        cardValue.setBounds(630, 98, 90, 24);
        cardValue.setForeground(Constants.DARK_BLUE);
        cardValue.setFont(new Font("Ubuntu", Font.BOLD, 20));
        finishPanel.add(cardValue);

        cardValueField = new TextField("00.00");
        cardValueField.setBounds(720, 96, 120, 30);
        cardValueField.setEnabled(false);
        finishPanel.add(cardValueField);

        JLabel cardOption = new JLabel("OPTION:");
        cardOption.setBounds(620, 156, 90, 24);
        cardOption.setForeground(Constants.DARK_BLUE);
        cardOption.setFont(new Font("Ubuntu", Font.BOLD, 20));
        finishPanel.add(cardOption);

        creditButton = new RadioButton("Credit");
        creditButton.setEnabled(false);
        debitButton = new RadioButton("Debit");
        debitButton.setEnabled(false);
        creditButton.setBounds(735, 125, 120, 60);
        debitButton.setBounds(735, 160, 120, 60);
        cardsButtons = new ButtonGroup();
        cardsButtons.add(creditButton);
        cardsButtons.add(debitButton);
        finishPanel.add(creditButton);
        finishPanel.add(debitButton);
        creditButton.addActionListener(e -> creditCardPayment());
        debitButton.addActionListener(e -> creditCardPayment());

        JLabel payedInCash = new JLabel("PAYED IN CASH:");
        payedInCash.setBounds(905, 52, 170, 24);
        payedInCash.setForeground(Constants.DARK_BLUE);
        payedInCash.setFont(new Font("Ubuntu", Font.BOLD, 20));
        finishPanel.add(payedInCash);

        payedInCashDisplay = new Display("00.00");
        payedInCashDisplay.setBounds(1080, 50, 120, 30);
        finishPanel.add(payedInCashDisplay);

        JLabel payedByCard = new JLabel("PAYED BY CARD:");
        payedByCard.setBounds(900, 112, 170, 24);
        payedByCard.setForeground(Constants.DARK_BLUE);
        payedByCard.setFont(new Font("Ubuntu", Font.BOLD, 20));
        finishPanel.add(payedByCard);

        payedByCardDisplay = new Display("00.00");
        payedByCardDisplay.setBounds(1080, 110, 120, 30);
        finishPanel.add(payedByCardDisplay);

        JLabel totalPayed = new JLabel("TOTAL PAYED:");
        totalPayed.setBounds(924, 172, 160, 24);
        totalPayed.setForeground(Constants.DARK_BLUE);
        totalPayed.setFont(new Font("Ubuntu", Font.BOLD, 20));
        finishPanel.add(totalPayed);

        totalPayedDisplay = new Display("00.00");
        totalPayedDisplay.setBounds(1080, 170, 120, 30);
        finishPanel.add(totalPayedDisplay);

        JLabel toPayLabel = new JLabel("TO BE PAYED");
        toPayLabel.setBounds(1300, 48, 160, 24);
        toPayLabel.setForeground(Constants.DARK_BLUE);
        toPayLabel.setFont(new Font("Ubuntu", Font.BOLD, 20));
        finishPanel.add(toPayLabel);

        toPayDisplay = new Display("00.00");
        toPayDisplay.setFontSize(26);
        toPayDisplay.setBounds(1300, 82, 150, 46);
        finishPanel.add(toPayDisplay);

        finishButton = new Button("FINISH");
        finishButton.setBounds(1300, 142, 150, 50);
        finishButton.setBackground(Constants.CONFIRM_GREEN);
        finishButton.setForeground(Color.WHITE);
        finishButton.setEnabled(false);
        finishButton.addActionListener(e -> finishSale());
        finishPanel.add(finishButton);
    }

    private void cashPayment(){
        if(Objects.equals(cashValueField.getText(), "")){
            new Popups("PAYED field is empty!",1);
        }
        else{
            String stringValue = cashValueField.getText();
            double doubleValue;

            try {
                doubleValue = Double.parseDouble(stringValue);
                int payment = saleController.cashPayment(doubleValue);

                switch (payment) {
                    case (-1) -> {
                        cardValueField.setText(saleController.getToPay());
                    }
                    case (+1) -> changeDisplay.setText(saleController.getCashChange());
                }
                payedInCashDisplay.setText(saleController.getPayedInCash());
                toPayDisplay.setText(saleController.getToPay());
                totalPayedDisplay.setText(saleController.getTotalPayed());

            } catch (NumberFormatException e) {
                new Popups("Invalid type! Enter a number in the PAYED field.", 1);
            }
        }
    }

    private void creditCardPayment(){
        if(Objects.equals(cardValueField.getText(),"00.00")){
            new Popups("VALUE field is empty!",1);
        }
        else {
            String stringValue = cardValueField.getText();
            double doubleValue;
            try {
                doubleValue = Double.parseDouble(stringValue);
                saleController.creditCardPayment(doubleValue);

                payedByCardDisplay.setText(saleController.getPayedByCard());
                toPayDisplay.setText(saleController.getToPay());
                totalPayedDisplay.setText(saleController.getTotalPayed());
            } catch (NumberFormatException e) {
                new Popups("Invalid type! Enter a number in the PAYED field.", 1);
            }
        }
    }

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
