package br.com.ticotech.gbooks.java.view.sale;

import br.com.ticotech.gbooks.java.view.shared.Button;
import br.com.ticotech.gbooks.java.view.shared.Constants;
import br.com.ticotech.gbooks.java.view.shared.TextField;

import javax.swing.*;
import java.awt.*;

public class ValueSelector extends JFrame {

    public ValueSelector() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(600, 500);
        setLayout(null);
        setResizable(false);

        TextField valueInput = new TextField("VALUE");
        valueInput.setBounds(50,50,200,50);
        this.add(valueInput);

        Button fullValueButton = new Button(Constants.ADD_BUTTON);
        fullValueButton.setBounds(50,200, 200,100);
        this.add(fullValueButton);

        Button finishButton = new Button(Constants.FINISH_SALE_BUTTON);
        finishButton.setBounds(30,300,450,150);
        this.add(finishButton);
        finishButton.addActionListener(e -> this.dispose());

        this.setVisible(true);
    }
}
