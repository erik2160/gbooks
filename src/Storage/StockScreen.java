package Storage;

import Elements.ComboBox;
import Elements.Constants;
import Elements.TextField;
import Elements.Button;

import javax.swing.*;
import java.awt.*;

public class StockScreen {

    public JPanel insertStockPanel(){
        JPanel stockPanel = new JPanel();
        stockPanel.setLayout(null);
        stockPanel.setBackground(Constants.DARK_GRAY);
        stockPanel.setBounds(40,20,950,582);

        String [] itemsList = {"CODE BAR", "TITLE", "AUTHOR", "PUBLISHER"};
        ComboBox comboBox = new ComboBox(itemsList);
        comboBox.setBounds(26,26,140,33);
        stockPanel.add(comboBox);

        TextField search = new TextField("SEARCH");
        search.setBounds(176,26,240,33);
        stockPanel.add(search);

        Button addButton = new Button("ADD");
        addButton.setBounds(714,26,100,33);
        stockPanel.add(addButton);

        Button editButton = new Button("EDIT");
        editButton.setBounds(824,26,100,33);
        stockPanel.add(editButton);

        return stockPanel;
    }
}
