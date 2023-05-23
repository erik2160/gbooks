package Sale;

import Elements.Button;
import Elements.Constants;
import Elements.Display;
import Elements.RadioButton;
import Elements.TextField;
import Home.HomeScreen;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class SaleScreen extends HomeScreen {
    static SaleScreen saleScreen = new SaleScreen();
    private static TextField codeBarTextField;
    private static TextField unitsTextField;
    private static DefaultTableModel model;
    private static TextField cpfText;
    private static Display pointsDisplay;
    private static Display newPriceDisplay;
    private static JCheckBox pointsCheckBox;
    private static TextField payedField;
    private static Display changeDisplay;
    private static RadioButton creditButton;
    private static RadioButton debitButton;
    private static Display toPayDisplay;
    private static ButtonGroup cardsButtons;
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
        return model;
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
            sale.addToCart();
            codeBarTextField.reset();
            unitsTextField.reset();
        });
        cartPanel.add(unitsTextField);

        Button buttonAdd = new Button("ADD");
        buttonAdd.setBounds(366,26, 110, 33);
        buttonAdd.addActionListener(addItem -> {
            sale.addToCart();
            codeBarTextField.reset();
            unitsTextField.reset();
        });
        cartPanel.add(buttonAdd);

        Button buttonRemove = new Button("REMOVE");
        buttonRemove.setBounds(678,26, 120, 33);
        buttonRemove.setBackground(Constants.CANCEL_RED);
        buttonRemove.setForeground(Color.WHITE);
        buttonRemove.addActionListener(removeItem -> sale.removeItemTable());
        cartPanel.add(buttonRemove);

        Button buttonCancel = new Button("CANCEL");
        buttonCancel.setBounds(806,26, 120, 33);
        buttonCancel.setBackground(Constants.CANCEL_RED);
        buttonCancel.setForeground(Color.WHITE);
        cartPanel.add(buttonCancel);

        model = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        model.addColumn("CODE");
        model.addColumn("TITLE");
        model.addColumn("UNITS");
        model.addColumn("UNIT VAL.");
        model.addColumn("TOTAL VAL.");

        JTable table = new JTable(model);
        table.getTableHeader().setReorderingAllowed(false);

        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setResizable(false);
        }

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

        cpfText = new TextField("CPF");
        cpfText.setBounds(66, 38, 181, 27);
        pointsPanel.add(cpfText);

        pointsDisplay = new Display("POINTS");
        pointsDisplay.setBounds(66, 80, 181, 27);
        pointsPanel.add(pointsDisplay);

        newPriceDisplay = new Display("NEW PRICE", Constants.CONFIRM_GREEN);
        newPriceDisplay.setBounds(66, 122, 181, 27);
        pointsPanel.add(newPriceDisplay);

        pointsCheckBox = new JCheckBox ("USE POINTS");
        pointsCheckBox.setBackground (Color.yellow);
        pointsCheckBox.setOpaque (true);
        pointsCheckBox.setFocusPainted (false);
        pointsCheckBox.setBounds (100, 179, 115, 30);
        pointsCheckBox.setFont (new Font(Constants.DEFAULT_FONT, Font.BOLD, 16));
        pointsPanel.add(pointsCheckBox);

        //APENAS PARA TESTE
        cpfText.addActionListener (e -> {
            pointsDisplay.setText ("3000");
            newPriceDisplay.setText ("R$ 2");
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
        debitButton = new RadioButton("Debit");
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

        Button finishButton = new Button("FINISH");
        finishButton.setBounds(446, 142, 150, 50);
        finishButton.setBackground(Constants.CONFIRM_GREEN);
        finishButton.setForeground(Color.WHITE);
        finishButton.addActionListener(finishSale -> sale.finishSale());
        finishPanel.add(finishButton);

        return finishPanel;
    }
}
