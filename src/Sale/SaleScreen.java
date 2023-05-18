package Sale;

import Elements.Button;
import Elements.Constants;
import Elements.RadioButton;
import Home.HomeScreen;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class SaleScreen extends HomeScreen {
    private JPanel cartPanel;
    private JPanel pointsPanel;
    private JPanel finishPanel;
    private JLabel cartLabel;
    private JTextField codeBarText;
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

    public JTextField getCodeBarText() {
        return codeBarText;
    }

    public void setCodeBarText(JTextField codeBarText) {
        this.codeBarText = codeBarText;
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

        cartLabel = new JLabel("BARCODE: ");
        cartLabel.setLayout(null);
        cartLabel.setForeground(Color.WHITE);
        cartLabel.setBounds(26, 26,130,33);
        cartLabel.setFont(new Font("Ubuntu", Font.PLAIN,25));
        cartLabel.setOpaque(false);
        cartPanel.add(getCartLabel());

        codeBarText = new JTextField();
        codeBarText.setBounds(154, 26, 300, 33);
        cartPanel.add(codeBarText);

        buttonAdd = new Button ("ADD");
        buttonAdd.setBounds(460,26, 110, 33);
        cartPanel.add(buttonAdd);

        buttonRemove = new Button("REMOVE");
        buttonRemove.setBounds(678,26, 120, 33);
        buttonRemove.setBackground(new Color (139, 0, 0));
        buttonRemove.setForeground(Color.WHITE);
        cartPanel.add(buttonRemove);

        buttonCancel = new Button("CANCEL");
        buttonCancel.setBounds(806,26, 120, 33);
        buttonCancel.setBackground(new Color (139, 0, 0));
        buttonCancel.setForeground(Color.WHITE);
        cartPanel.add(buttonCancel);

        return cartPanel;
    }

    public JPanel insertPointsPanel(Sale sale) {
        pointsPanel = new JPanel();
        pointsPanel.setBackground(Constants.DARKGRAY);
        pointsPanel.setBounds(40, 366,315, 240);
        pointsPanel.setLayout(null);

        JTextField cpfText = new JTextField("CPF");
        cpfText.setBounds(66, 36, 181, 27);
        cpfText.setFont(new Font("Ubuntu", Font.PLAIN, 19));
        cpfText.setForeground(Color.lightGray);
        cpfText.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                cpfText.setText(null);
                cpfText.setForeground (Color.BLACK);
            }

            @Override
            public void focusLost(FocusEvent e) {
                cpfText.setText("CPF");
                cpfText.setForeground(Color.lightGray);
            }
        });

        pointsPanel.add(cpfText);

        JTextField pointsDisplay = new JTextField("POINTS");
        pointsDisplay.setBounds(66, 94, 181, 27);
        pointsDisplay.setFont(new Font("Ubuntu", Font.PLAIN, 19));
        pointsDisplay.setEnabled (false);
        pointsDisplay.setDisabledTextColor (Color.BLACK);
        pointsPanel.add(pointsDisplay);

        JTextField newPriceDisplay = new JTextField("NEW PRICE");
        newPriceDisplay.setBounds(66, 155, 181, 27);
        newPriceDisplay.setFont(new Font("Ubuntu", Font.PLAIN, 19));
        newPriceDisplay.setEnabled (false);
        newPriceDisplay.setDisabledTextColor (new Color(36, 124, 68));
        pointsPanel.add(newPriceDisplay);

        JCheckBox pointsCheckBox = new JCheckBox ("USE POINTS");
        pointsCheckBox.setBackground (Color.yellow);
        pointsCheckBox.setOpaque (true);
        pointsCheckBox.setFocusPainted (false);
        pointsCheckBox.setBounds (100, 194, 115, 30);
        pointsCheckBox.setFont (new Font("Ubuntu", Font.BOLD, 16));
        pointsCheckBox.setForeground (Color.BLACK);
        pointsPanel.add(pointsCheckBox);

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
        cashLabel.setBounds(52,30,60,24);
        cashLabel.setForeground(Color.WHITE);
        cashLabel.setFont(new Font("Ubuntu", Font.PLAIN, 22));
        finishPanel.add(cashLabel);

        JTextField payedField = new JTextField("PAYED");
        payedField.setBounds(30,90,100,30);
        payedField.setFont(new Font("Ubuntu", Font.BOLD, 16));
        payedField.setForeground(Color.lightGray);
        finishPanel.add(payedField);
        payedField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                payedField.setText(null);
                payedField.setForeground (Color.BLACK);
            }

            @Override
            public void focusLost(FocusEvent e) {
                payedField.setText("PAYED");
                payedField.setForeground(Color.lightGray);
            }
        });

        JTextField changeDisplay = new JTextField("CHANGE");
        changeDisplay.setBounds(30,150,100,30);
        changeDisplay.setFont(new Font("Ubuntu", Font.BOLD, 16));
        changeDisplay.setDisabledTextColor(Color.BLACK);
        changeDisplay.setEnabled(false);
        finishPanel.add(changeDisplay);
        payedField.addActionListener(e -> changeDisplay.setText("R$ 110,00"));

        JLabel creditCard = new JLabel("CREDIT CARD:");
        creditCard.setBounds(180,30,144,24);
        creditCard.setForeground(Color.WHITE);
        creditCard.setFont(new Font("Ubuntu", Font.PLAIN, 22));
        finishPanel.add(creditCard);

        RadioButton creditButton = new RadioButton("Credit");
        RadioButton debitButton = new RadioButton("Debit");
        creditButton.setBounds(210,90,100,30);
        debitButton.setBounds(210,150,100,30);
        ButtonGroup creditCardButtons = new ButtonGroup();
        creditCardButtons.add(creditButton);
        creditCardButtons.add(debitButton);
        Cursor cur = new Cursor(Cursor.HAND_CURSOR);
        creditButton.setCursor(cur);
        debitButton.setCursor(cur);
        finishPanel.add(creditButton);
        finishPanel.add(debitButton);

        JLabel toPayLabel = new JLabel("TO PAY:");
        toPayLabel.setBounds(400,30,80,24);
        toPayLabel.setForeground(Color.WHITE);
        toPayLabel.setFont(new Font("Ubuntu", Font.PLAIN, 22));
        finishPanel.add(toPayLabel);

        JTextField toPayDisplay = new JTextField();
        toPayDisplay.setBounds(370, 80, 150,40);
        toPayDisplay.setText("R$ 1149,90");
        toPayDisplay.setFont(new Font("Ubuntu", Font.BOLD, 24));
        toPayDisplay.setDisabledTextColor(Color.BLACK);
        toPayDisplay.setEnabled(false);
        finishPanel.add(toPayDisplay);

        Button finishButton = new Button("FINISH");
        finishButton.setBounds(370, 130, 150, 50);
        finishButton.setBackground(new Color(0, 124, 50));
        finishButton.setForeground(Color.WHITE);
        finishPanel.add(finishButton);

        return finishPanel;
    }
}
