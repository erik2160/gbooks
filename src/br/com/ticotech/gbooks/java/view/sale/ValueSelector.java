package br.com.ticotech.gbooks.java.view.sale;

import br.com.ticotech.gbooks.java.controllers.SaleController;
import br.com.ticotech.gbooks.java.view.shared.Button;
import br.com.ticotech.gbooks.java.view.shared.Constants;
import br.com.ticotech.gbooks.java.view.shared.Popups;
import br.com.ticotech.gbooks.java.view.shared.TextField;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class ValueSelector extends JFrame {

    int type;
    SaleController saleController;
    TextField valueInput;
    FinishSection finishSection;

    public ValueSelector(int type, SaleController saleController, FinishSection finishSection) {
        this.type = type;
        this.saleController = saleController;
        this.finishSection = finishSection;
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(500, 210);
        setLayout(null);
        setLocationRelativeTo(null);
        setTitle("Value Selector");
        setResizable(false);

        TextField firstField = new TextField("");
        firstField.setOpaque(false);
        firstField.setBorder(null);
        firstField.setBounds(830, 1,  1, 1);
        this.add(firstField);

        valueInput = new TextField("INSERT VALUE");
        valueInput.setBounds(40,20,420,50);
        this.add(valueInput);


        Button fullValueButton = new Button(Constants.TOTAL_VALUE_BUTTON);
        fullValueButton.setBounds(35,90, 208,58);
        fullValueButton.addActionListener(e -> {
            valueInput.setText(saleController.getToPay());
            doPayment();
        });
        this.add(fullValueButton);

        Button finishButton = new Button(Constants.PAY_BUTTON);
        finishButton.setBounds(258,90,208,58);
        finishButton.addActionListener(e -> doPayment());
        this.add(finishButton);

        this.setVisible(true);
    }
    private void doPayment(){
        double value;
        try {
            value = Double.parseDouble(valueInput.getText().replace(",","."));
            if (value <= 0){
                new Popups("The value needs to be positive.",1);
            }
            else {
                switch (type) {
                    case 1 -> {
                        saleController.registerCashPayment(value);
                        finishSection.getTotalDisplay().setText("TOTAL: $" + saleController.getToPay());
                        finishSection.getChangeDisplay().setText("CHANGE: $" + saleController.getCashChange());
                        dispose();
                    }
                    case 2 -> {
                        if (saleController.registerCardPayment(value)) {
                            finishSection.getTotalDisplay().setText("TOTAL: $" + saleController.getToPay());
                            dispose();
                        }
                    }
                }
            }
        }
        catch (NumberFormatException e){
            new Popups("Invalid type! Enter a number in the PAYED field.", 1);
        }
    }



}
