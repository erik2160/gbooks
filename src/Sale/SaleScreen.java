package Sale;

import Elements.Button;
import Elements.Constants;
import Elements.Display;
import Elements.RadioButton;
import Elements.TextField;
import Home.HomeScreen;

import javax.swing.*;
import java.awt.*;

public class SaleScreen extends HomeScreen {
    private JPanel cartPanel;
    private JPanel pointsPanel;
    private JPanel finishPanel;
    private JLabel cartLabel;
    private TextField codeBarTextField;
    private TextField unitsTextField;
    private Button buttonAdd;
    private Button buttonRemove;
    private Button buttonCancel;

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

    public JLabel getCartLabel() {
        return cartLabel;
    }

    public void setCartLabel(JLabel cartLabel) {
        this.cartLabel = cartLabel;
    }

    public JTextField getCodeBarTextField() {
        return codeBarTextField;
    }

    public void setCodeBarTextField(TextField codeBarTextField) {
        this.codeBarTextField = codeBarTextField;
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

    public JPanel insertCartPanel(Sale sale) {
        cartPanel = new JPanel();
        cartPanel.setLayout(null);
        cartPanel.setBackground(Constants.DARKGRAY);
        cartPanel.setBounds(40, 20,950, 335);

        /*
        cartLabel = new JLabel("BARCODE: ");
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

        TextField cpfText = new TextField("CPF");
        cpfText.setBounds(66, 38, 181, 27);
        pointsPanel.add(cpfText);

        Display pointsDisplay = new Display("POINTS");
        pointsDisplay.setBounds(66, 80, 181, 27);
        pointsPanel.add(pointsDisplay);

        Display newPriceDisplay = new Display("NEW PRICE", Constants.CONFIRMGREEN);
        newPriceDisplay.setBounds(66, 122, 181, 27);
        pointsPanel.add(newPriceDisplay);

        JCheckBox pointsCheckBox = new JCheckBox ("USE POINTS");
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

        JLabel cashLabel = new JLabel("CASH");
        cashLabel.setBounds(72,48,60,24);
        cashLabel.setForeground(Color.WHITE);
        cashLabel.setFont(new Font("Ubuntu", Font.BOLD, 20));
        finishPanel.add(cashLabel);

        TextField payedField = new TextField("PAYED");
        payedField.setBounds(36,96,145,30);
        finishPanel.add(payedField);

        Display changeDisplay = new Display("CHANGE");
        changeDisplay.setBounds(36,154,145,30);
        finishPanel.add(changeDisplay);

        JLabel creditCard = new JLabel("CREDIT CARD");
        creditCard.setBounds(240,48,154,24);
        creditCard.setForeground(Color.WHITE);
        creditCard.setFont(new Font("Ubuntu", Font.BOLD, 20));
        finishPanel.add(creditCard);

        RadioButton creditButton = new RadioButton("Credit");
        RadioButton debitButton = new RadioButton("Debit");
        creditButton.setBounds(262,80,120,60);
        debitButton.setBounds(262,136,120,60);
        ButtonGroup creditCardButtons = new ButtonGroup();
        creditCardButtons.add(creditButton);
        creditCardButtons.add(debitButton);
        finishPanel.add(creditButton);
        finishPanel.add(debitButton);

        JLabel toPayLabel = new JLabel("TO PAY");
        toPayLabel.setBounds(480,48,80,24);
        toPayLabel.setForeground(Color.WHITE);
        toPayLabel.setFont(new Font("Ubuntu", Font.BOLD, 20));
        finishPanel.add(toPayLabel);

        Display toPayDisplay = new Display("");
        toPayDisplay.setFontSize(22);
        toPayDisplay.setBounds(446, 92, 150,38);

        //APENAS PARA TESTE
        payedField.addActionListener(e -> {
            toPayDisplay.setText("R$ 1234,90");
            changeDisplay.setText("R$ 110,00");
        });

        finishPanel.add(toPayDisplay);

        Button finishButton = new Button("FINISH");
        finishButton.setBounds(446, 142, 150, 50);
        finishButton.setBackground(Constants.CONFIRMGREEN);
        finishButton.setForeground(Color.WHITE);
        finishPanel.add(finishButton);

        return finishPanel;
    }
}
