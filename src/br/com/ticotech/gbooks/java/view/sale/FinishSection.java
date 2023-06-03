package br.com.ticotech.gbooks.java.view.sale;

import br.com.ticotech.gbooks.java.controllers.SaleController;
import br.com.ticotech.gbooks.java.entities.Sale;
import br.com.ticotech.gbooks.java.view.shared.*;
import br.com.ticotech.gbooks.java.view.shared.Button;
import br.com.ticotech.gbooks.java.view.shared.TextField;

import javax.swing.*;
import java.awt.*;

public class FinishSection {
    private final TextField payedField;
    private final Display changeDisplay;
    private final RadioButton creditButton;
    private final RadioButton debitButton;
    private final Display toPayDisplay;
    private final ButtonGroup cardsButtons;
    private final Button finishButton;

    public Display getToPayDisplay() {
        return toPayDisplay;
    }

    public RadioButton getCreditButton() {
        return creditButton;
    }

    public RadioButton getDebitButton() {
        return debitButton;
    }

    public TextField getPayedField() {
        return payedField;
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

        JPanel finishPanel = new JPanel();
        finishPanel.setBackground(Constants.DARK_GRAY);
        finishPanel.setBounds(370, 366, 620, 240);
        finishPanel.setLayout(null);

        JLabel cashLabel = new JLabel("CASH");
        cashLabel.setBounds(72, 48, 60, 24);
        cashLabel.setForeground(Color.WHITE);
        cashLabel.setFont(new Font("Ubuntu", Font.BOLD, 20));
        finishPanel.add(cashLabel);

        payedField = new TextField("PAYED");
        payedField.setBounds(36, 96, 145, 30);
        payedField.setEnabled(false);
        payedField.addActionListener(paymentCash -> saleController.paymentCash());
        finishPanel.add(payedField);

        changeDisplay = new Display("CHANGE");
        changeDisplay.setBounds(36, 154, 145, 30);
        finishPanel.add(changeDisplay);

        JLabel creditCard = new JLabel("CREDIT CARD");
        creditCard.setBounds(240, 48, 154, 24);
        creditCard.setForeground(Color.WHITE);
        creditCard.setFont(new Font("Ubuntu", Font.BOLD, 20));
        finishPanel.add(creditCard);

        creditButton = new RadioButton("Credit");
        creditButton.setEnabled(false);
        debitButton = new RadioButton("Debit");
        debitButton.setEnabled(false);
        creditButton.setBounds(262, 80, 120, 60);
        debitButton.setBounds(262, 136, 120, 60);

        creditButton.addActionListener(selectCash -> saleController.paymentCard());
        debitButton.addActionListener(selectCard -> saleController.paymentCard());

        cardsButtons = new ButtonGroup();
        cardsButtons.add(creditButton);
        cardsButtons.add(debitButton);
        finishPanel.add(creditButton);
        finishPanel.add(debitButton);

        JLabel toPayLabel = new JLabel("TO PAY");
        toPayLabel.setBounds(480, 48, 80, 24);
        toPayLabel.setForeground(Color.WHITE);
        toPayLabel.setFont(new Font("Ubuntu", Font.BOLD, 20));
        finishPanel.add(toPayLabel);

        toPayDisplay = new Display("");
        toPayDisplay.setFontSize(22);
        toPayDisplay.setBounds(446, 92, 150, 38);
        finishPanel.add(toPayDisplay);

        finishButton = new Button("FINISH");
        finishButton.setBounds(446, 142, 150, 50);
        finishButton.setBackground(Constants.CONFIRM_GREEN);
        finishButton.setForeground(Color.WHITE);
        finishButton.setEnabled(false);
        finishButton.addActionListener(finishSale -> {
            if (saleController.finishSale("finish")) {
                saleScreen.disableElements("finish");
            }
        });
        finishPanel.add(finishButton);
    }
}
