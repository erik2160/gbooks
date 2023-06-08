package br.com.ticotech.gbooks.java.view.sale;

import br.com.ticotech.gbooks.java.controllers.SaleController;
import br.com.ticotech.gbooks.java.view.shared.Button;
import br.com.ticotech.gbooks.java.view.shared.Constants;
import br.com.ticotech.gbooks.java.view.shared.Popups;
import br.com.ticotech.gbooks.java.view.shared.TextField;

import javax.swing.*;

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
        setSize(600, 500);
        setLayout(null);
        setResizable(false);

        valueInput = new TextField("VALUE");
        valueInput.setBounds(50,50,200,50);
        this.add(valueInput);

        Button fullValueButton = new Button(Constants.ADD_BUTTON);
        fullValueButton.setBounds(50,200, 200,100);
        fullValueButton.addActionListener(e -> valueInput.setText(saleController.getToPay()));
        this.add(fullValueButton);

        Button finishButton = new Button(Constants.FINISH_SALE_BUTTON);
        finishButton.setBounds(30,300,450,150);
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
                        saleController.cashPayment(value);
                        finishSection.getTotalDisplay().setText(saleController.getToPay());
                        finishSection.getChangeDisplay().setText(saleController.getCashChange());
                        dispose();
                    }
                    case 2 -> {
                        if (saleController.creditCardPayment(value)) {
                            finishSection.getTotalDisplay().setText(saleController.getToPay());
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
