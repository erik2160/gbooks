package br.com.ticotech.gbooks.java.view;

import br.com.ticotech.gbooks.java.view.shared.*;
import br.com.ticotech.gbooks.java.view.shared.Button;
import br.com.ticotech.gbooks.java.view.shared.TextField;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.Objects;

public class SaleScreen {
    static SaleScreen saleScreen = new SaleScreen();
    private static TextField codeBarTextField;
    private static TextField unitsTextField;
    private static Table table;
    private static Display pointsDisplay;
    private static Display newPriceDisplay;
    private static TextField payedField;
    private static Display changeDisplay;
    private static RadioButton creditButton;
    private static RadioButton debitButton;
    private static Display toPayDisplay;
    private static ButtonGroup cardsButtons;
    private static Button finishButton;
    private static Button buttonCancel;
    private static Button buttonRemove;
    public TextField getCodeBarTextField() {
        return codeBarTextField;
    }
    public TextField getUnitsTextField() {
        return unitsTextField;
    }
    public Display getToPayDisplay() {
        return toPayDisplay;
    }
    public DefaultTableModel getModel() {
        return table.getModel();
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
    public Button getButtonCancel() {
        return buttonCancel;
    }
    public Button getButtonRemove() {
        return buttonRemove;
    }

    public JPanel insertCartPanel(Sale sale) {
        sale.addStorage();

        JPanel cartPanel = new JPanel();
        cartPanel.setLayout(null);
        cartPanel.setBackground(Constants.DARK_GRAY);
        cartPanel.setBounds(40, 20,950, 335);

        codeBarTextField = new TextField("BARCODE");
        codeBarTextField.setBounds(26, 26, 240, 33);
        codeBarTextField.setFontSize(22);
        cartPanel.add(codeBarTextField);

        unitsTextField = new TextField("UNITS");
        unitsTextField.setBounds(276, 26, 80, 33);
        unitsTextField.setFontSize(22);
        unitsTextField.addActionListener(enterUnit -> {
            codeBarTextField.requestFocus();
            if (sale.addToCart()) {
                disableElements("add");
            }
        });
        cartPanel.add(unitsTextField);

        Button buttonAdd = new Button("ADD");
        buttonAdd.setBounds(366,26, 110, 33);
        buttonAdd.addActionListener(addItem -> {
            codeBarTextField.requestFocus();
            if (sale.addToCart()) {
                disableElements("add");
            }
        });
        cartPanel.add(buttonAdd);

        buttonRemove = new Button("REMOVE",Constants.CANCEL_RED,Color.WHITE);
        buttonRemove.setBounds(678,26, 120, 33);
        buttonRemove.setEnabled(false);
        buttonRemove.addActionListener(removeItem -> sale.removeItemTable());
        cartPanel.add(buttonRemove);

        buttonCancel = new Button("CANCEL", Constants.CANCEL_RED, Color.WHITE);
        buttonCancel.setBounds(806,26, 120, 33);
        buttonCancel.setEnabled(false);
        buttonCancel.addActionListener(finishSale -> {
            if (sale.finishSale("cancel")) {
                disableElements("cancel");
            }
        });
        cartPanel.add(buttonCancel);

        String [] columnsName = {"CODE", "TITLE", "UNITS", "UNIT VAL.", "TOTAL VAL."};
        int [] columnsWidth = {50, 200, 10, 40, 80};
        table = new Table(columnsName, columnsWidth);

        JScrollPane tableScrollPane = new JScrollPane(table);
        tableScrollPane.setBounds(26, 70, 899, 240);
        cartPanel.add(tableScrollPane);

        return cartPanel;
    }

    public JPanel insertPointsPanel(Sale sale) {
        JPanel pointsPanel = new JPanel();
        pointsPanel.setBackground(Constants.DARK_GRAY);
        pointsPanel.setBounds(40, 366,315, 240);
        pointsPanel.setLayout(null);

        TextField cpfText = new TextField("CPF");
        cpfText.setBounds(66, 38, 181, 27);
        pointsPanel.add(cpfText);

        pointsDisplay = new Display("POINTS");
        pointsDisplay.setBounds(66, 80, 181, 27);
        pointsPanel.add(pointsDisplay);

        newPriceDisplay = new Display("NEW PRICE", Constants.CONFIRM_GREEN);
        newPriceDisplay.setBounds(66, 122, 181, 27);
        pointsPanel.add(newPriceDisplay);

        JCheckBox pointsCheckBox = new JCheckBox("USE POINTS");
        pointsCheckBox.setBackground (Color.yellow);
        pointsCheckBox.setOpaque (true);
        pointsCheckBox.setFocusPainted (false);
        pointsCheckBox.setBounds (100, 179, 115, 30);
        pointsCheckBox.setFont (new Font(Constants.DEFAULT_FONT, Font.BOLD, 16));
        pointsPanel.add(pointsCheckBox);

        //APENAS PARA TESTE
        cpfText.addActionListener (e -> {
            pointsDisplay.setText ("300");
            newPriceDisplay.setText ("R$ 20");
        });

        return pointsPanel;
    }

    public JPanel insertFinishPanel(Sale sale) {
        JPanel finishPanel = new JPanel();
        finishPanel.setBackground(Constants.DARK_GRAY);
        finishPanel.setBounds(370, 366,620, 240);
        finishPanel.setLayout(null);

        JLabel cashLabel = new JLabel("CASH");
        cashLabel.setBounds(72,48,60,24);
        cashLabel.setForeground(Color.WHITE);
        cashLabel.setFont(new Font("Ubuntu", Font.BOLD, 20));
        finishPanel.add(cashLabel);

        payedField = new TextField("PAYED");
        payedField.setBounds(36,96,145,30);
        payedField.setEnabled(false);
        payedField.addActionListener(paymentCash -> sale.paymentCash());
        finishPanel.add(payedField);

        changeDisplay = new Display("CHANGE");
        changeDisplay.setBounds(36,154,145,30);
        finishPanel.add(changeDisplay);

        JLabel creditCard = new JLabel("CREDIT CARD");
        creditCard.setBounds(240,48,154,24);
        creditCard.setForeground(Color.WHITE);
        creditCard.setFont(new Font("Ubuntu", Font.BOLD, 20));
        finishPanel.add(creditCard);

        creditButton = new RadioButton("Credit");
        creditButton.setEnabled(false);
        debitButton = new RadioButton("Debit");
        debitButton.setEnabled(false);
        creditButton.setBounds(262,80,120,60);
        debitButton.setBounds(262,136,120,60);

        creditButton.addActionListener(selectCash -> sale.paymentCard());
        debitButton.addActionListener(selectCard -> sale.paymentCard());

        cardsButtons = new ButtonGroup();
        cardsButtons.add(creditButton);
        cardsButtons.add(debitButton);
        finishPanel.add(creditButton);
        finishPanel.add(debitButton);

        JLabel toPayLabel = new JLabel("TO PAY");
        toPayLabel.setBounds(480,48,80,24);
        toPayLabel.setForeground(Color.WHITE);
        toPayLabel.setFont(new Font("Ubuntu", Font.BOLD, 20));
        finishPanel.add(toPayLabel);

        toPayDisplay = new Display("");
        toPayDisplay.setFontSize(22);
        toPayDisplay.setBounds(446, 92, 150,38);
        finishPanel.add(toPayDisplay);

        finishButton = new br.com.ticotech.gbooks.java.view.shared.Button("FINISH");
        finishButton.setBounds(446, 142, 150, 50);
        finishButton.setBackground(Constants.CONFIRM_GREEN);
        finishButton.setForeground(Color.WHITE);
        finishButton.setEnabled(false);
        finishButton.addActionListener(finishSale -> {
            if (sale.finishSale("finish")) {
                disableElements("finish");
            }
        });
        finishPanel.add(finishButton);

        return finishPanel;
    }

    private void disableElements(String getType) {
        if (Objects.equals(getType, "finish") || Objects.equals(getType, "cancel")) {
            saleScreen.getToPayDisplay().reset();
            saleScreen.getPayedField().reset();
            saleScreen.getChangeDisplay().reset();
            saleScreen.getCodeBarTextField().reset();
            saleScreen.getUnitsTextField().reset();
            saleScreen.getCardsButtons().clearSelection();
            saleScreen.getPayedField().setEnabled(false);
            saleScreen.getFinishButton().setEnabled(false);
            saleScreen.getCreditButton().setEnabled(false);
            saleScreen.getDebitButton().setEnabled(false);
            saleScreen.getButtonCancel().setEnabled(false);
            saleScreen.getButtonRemove().setEnabled(false);
        } else if (Objects.equals(getType, "add")) {
            saleScreen.getPayedField().setEnabled(true);
            saleScreen.getFinishButton().setEnabled(true);
            saleScreen.getCreditButton().setEnabled(true);
            saleScreen.getDebitButton().setEnabled(true);
            saleScreen.getButtonCancel().setEnabled(true);
            saleScreen.getButtonRemove().setEnabled(true);
        }


    }
}
