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
    private JPanel cartPanel;
    private JPanel pointsPanel;
    private JPanel finishPanel;
    private TextField codeBarTextField;
    private TextField unitsTextField;
    private Button buttonAdd;
    private Button buttonRemove;
    private Button buttonCancel;
    private TextField cpfText;
    private Display pointsDisplay;
    private Display newPriceDisplay;
    private JCheckBox pointsCheckBox;
    private JLabel cashLabel;
    private TextField payedField;
    private Display changeDisplay;
    private JLabel creditCard;
    private RadioButton creditButton;
    private RadioButton debitButton;
    private ButtonGroup creditCardButtons;
    private JLabel toPayLabel;
    private Display toPayDisplay;
    private Button finishButton;
    private DefaultTableModel model;

    public JPanel getCartPanel() {
        return cartPanel;
    }

    public void setCartPanel(JPanel cartPanel) {
        this.cartPanel = cartPanel;
    }

    public JPanel getPointsPanel() {
        return pointsPanel;
    }

    public void setPointsPanel(JPanel pointsPanel) {
        this.pointsPanel = pointsPanel;
    }

    public JPanel getFinishPanel() {
        return finishPanel;
    }

    public void setFinishPanel(JPanel finishPanel) {
        this.finishPanel = finishPanel;
    }

    public TextField getCodeBarTextField() {
        return codeBarTextField;
    }

    public void setCodeBarTextField(TextField codeBarTextField) {
        this.codeBarTextField = codeBarTextField;
    }

    public TextField getUnitsTextField() {
        return unitsTextField;
    }

    public void setUnitsTextField(TextField unitsTextField) {
        this.unitsTextField = unitsTextField;
    }

    public Button getButtonAdd() {
        return buttonAdd;
    }

    public void setButtonAdd(Button buttonAdd) {
        this.buttonAdd = buttonAdd;
    }

    public Button getButtonRemove() {
        return buttonRemove;
    }

    public void setButtonRemove(Button buttonRemove) {
        this.buttonRemove = buttonRemove;
    }

    public Button getButtonCancel() {
        return buttonCancel;
    }

    public void setButtonCancel(Button buttonCancel) {
        this.buttonCancel = buttonCancel;
    }

    public TextField getCpfText() {
        return cpfText;
    }

    public void setCpfText(TextField cpfText) {
        this.cpfText = cpfText;
    }

    public Display getPointsDisplay() {
        return pointsDisplay;
    }

    public void setPointsDisplay(Display pointsDisplay) {
        this.pointsDisplay = pointsDisplay;
    }

    public Display getNewPriceDisplay() {
        return newPriceDisplay;
    }

    public void setNewPriceDisplay(Display newPriceDisplay) {
        this.newPriceDisplay = newPriceDisplay;
    }

    public JCheckBox getPointsCheckBox() {
        return pointsCheckBox;
    }

    public void setPointsCheckBox(JCheckBox pointsCheckBox) {
        this.pointsCheckBox = pointsCheckBox;
    }

    public JLabel getCashLabel() {
        return cashLabel;
    }

    public void setCashLabel(JLabel cashLabel) {
        this.cashLabel = cashLabel;
    }

    public TextField getPayedField() {
        return payedField;
    }

    public void setPayedField(TextField payedField) {
        this.payedField = payedField;
    }

    public Display getChangeDisplay() {
        return changeDisplay;
    }

    public void setChangeDisplay(Display changeDisplay) {
        this.changeDisplay = changeDisplay;
    }

    public JLabel getCreditCard() {
        return creditCard;
    }

    public void setCreditCard(JLabel creditCard) {
        this.creditCard = creditCard;
    }

    public RadioButton getCreditButton() {
        return creditButton;
    }

    public void setCreditButton(RadioButton creditButton) {
        this.creditButton = creditButton;
    }

    public RadioButton getDebitButton() {
        return debitButton;
    }

    public void setDebitButton(RadioButton debitButton) {
        this.debitButton = debitButton;
    }

    public ButtonGroup getCreditCardButtons() {
        return creditCardButtons;
    }

    public void setCreditCardButtons(ButtonGroup creditCardButtons) {
        this.creditCardButtons = creditCardButtons;
    }

    public JLabel getToPayLabel() {
        return toPayLabel;
    }

    public void setToPayLabel(JLabel toPayLabel) {
        this.toPayLabel = toPayLabel;
    }

    public Display getToPayDisplay() {
        return toPayDisplay;
    }

    public void setToPayDisplay(Display toPayDisplay) {
        this.toPayDisplay = toPayDisplay;
    }

    public Button getFinishButton() {
        return finishButton;
    }

    public void setFinishButton(Button finishButton) {
        this.finishButton = finishButton;
    }

    public DefaultTableModel getModel() {
        return model;
    }

    public void setModel(DefaultTableModel model) {
        this.model = model;
    }

    public JPanel insertCartPanel(Sale sale) {
        cartPanel = new JPanel();
        cartPanel.setLayout(null);
        cartPanel.setBackground(Constants.DARKGRAY);
        cartPanel.setBounds(40, 20,950, 335);

        /*
        JLabel cartLabel = new JLabel("BARCODE: ");
        cartLabel.setLayout(null);
        cartLabel.setForeground(Color.WHITE);
        cartLabel.setBounds(26, 26,130,33);
        cartLabel.setFont(new Font("Ubuntu", Font.PLAIN,25));
        cartLabel.setOpaque(false);
        cartPanel.add(getCartLabel());

         */

        codeBarTextField = new TextField("BARCODE");
        codeBarTextField.setBounds(26, 26, 240, 33);
        codeBarTextField.setFontSize(22);
        cartPanel.add(codeBarTextField);

        unitsTextField = new TextField("UNITS");
        unitsTextField.setBounds(276, 26, 80, 33);
        unitsTextField.setFontSize(22);
        cartPanel.add(unitsTextField);

        buttonAdd = new Button ("ADD");
        buttonAdd.setBounds(366,26, 110, 33);
//        buttonAdd.addActionListener(addItem -> sale.addToCart());
        cartPanel.add(buttonAdd);

        buttonRemove = new Button("REMOVE");
        buttonRemove.setBounds(678,26, 120, 33);
        buttonRemove.setBackground(Constants.CANCELRED);
        buttonRemove.setForeground(Color.WHITE);
        cartPanel.add(buttonRemove);

        buttonCancel = new Button("CANCEL");
        buttonCancel.setBounds(806,26, 120, 33);
        buttonCancel.setBackground(Constants.CANCELRED);
        buttonCancel.setForeground(Color.WHITE);
        cartPanel.add(buttonCancel);

        return cartPanel;
    }

    public JPanel insertPointsPanel(Sale sale) {
        pointsPanel = new JPanel();
        pointsPanel.setBackground(Constants.DARKGRAY);
        pointsPanel.setBounds(40, 366,315, 240);
        pointsPanel.setLayout(null);

        cpfText = new TextField("CPF");
        cpfText.setBounds(66, 38, 181, 27);
        pointsPanel.add(cpfText);

        pointsDisplay = new Display("POINTS");
        pointsDisplay.setBounds(66, 80, 181, 27);
        pointsPanel.add(pointsDisplay);

        newPriceDisplay = new Display("NEW PRICE", Constants.CONFIRMGREEN);
        newPriceDisplay.setBounds(66, 122, 181, 27);
        pointsPanel.add(newPriceDisplay);

        pointsCheckBox = new JCheckBox ("USE POINTS");
        pointsCheckBox.setBackground (Color.yellow);
        pointsCheckBox.setOpaque (true);
        pointsCheckBox.setFocusPainted (false);
        pointsCheckBox.setBounds (100, 179, 115, 30);
        pointsCheckBox.setFont (new Font(Constants.DEFAULTFONT, Font.BOLD, 16));
        pointsPanel.add(pointsCheckBox);

        //APENAS PARA TESTE
        cpfText.addActionListener (e -> {
            pointsDisplay.setText ("3000");
            newPriceDisplay.setText ("R$ 2");
        });

        return pointsPanel;
    }

    public JPanel insertFinishPanel(Sale sale) {
        finishPanel = new JPanel();
        finishPanel.setBackground(Constants.DARKGRAY);
        finishPanel.setBounds(370, 366,620, 240);
        finishPanel.setLayout(null);

        cashLabel = new JLabel("CASH");
        cashLabel.setBounds(72,48,60,24);
        cashLabel.setForeground(Color.WHITE);
        cashLabel.setFont(new Font("Ubuntu", Font.BOLD, 20));
        finishPanel.add(cashLabel);

        payedField = new TextField("PAYED");
        payedField.setBounds(36,96,145,30);
        finishPanel.add(payedField);

        changeDisplay = new Display("CHANGE");
        changeDisplay.setBounds(36,154,145,30);
        finishPanel.add(changeDisplay);

        creditCard = new JLabel("CREDIT CARD");
        creditCard.setBounds(240,48,154,24);
        creditCard.setForeground(Color.WHITE);
        creditCard.setFont(new Font("Ubuntu", Font.BOLD, 20));
        finishPanel.add(creditCard);

        creditButton = new RadioButton("Credit");
        debitButton = new RadioButton("Debit");
        creditButton.setBounds(262,80,120,60);
        debitButton.setBounds(262,136,120,60);
        creditCardButtons = new ButtonGroup();
        creditCardButtons.add(creditButton);
        creditCardButtons.add(debitButton);
        finishPanel.add(creditButton);
        finishPanel.add(debitButton);

        toPayLabel = new JLabel("TO PAY");
        toPayLabel.setBounds(480,48,80,24);
        toPayLabel.setForeground(Color.WHITE);
        toPayLabel.setFont(new Font("Ubuntu", Font.BOLD, 20));
        finishPanel.add(toPayLabel);

        toPayDisplay = new Display("");
        toPayDisplay.setFontSize(22);
        toPayDisplay.setBounds(446, 92, 150,38);

        //APENAS PARA TESTE
        payedField.addActionListener(e -> {
            toPayDisplay.setText("R$ 1234,90");
            changeDisplay.setText("R$ 110,00");
        });

        finishPanel.add(toPayDisplay);

        finishButton = new Button("FINISH");
        finishButton.setBounds(446, 142, 150, 50);
        finishButton.setBackground(Constants.CONFIRMGREEN);
        finishButton.setForeground(Color.WHITE);
        finishPanel.add(finishButton);

        return finishPanel;
    }
}
