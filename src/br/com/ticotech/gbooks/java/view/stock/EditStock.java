package br.com.ticotech.gbooks.java.view.stock;

import br.com.ticotech.gbooks.java.view.shared.Constants;
import br.com.ticotech.gbooks.java.view.shared.Button;
import br.com.ticotech.gbooks.java.view.shared.TextField;

import javax.swing.*;
import java.awt.*;

public class EditStock extends JFrame {

    public EditStock(String title) {
        setTitle(title);
        setSize(430, 535);
        setResizable(false);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (screenSize.width - 405) / 2;
        int y = (screenSize.height - 535) / 2;
        setLocation(x,y);

        JPanel panel = new JPanel();
        panel.setBackground(Constants.LIGHT_GRAY);
        panel.setLayout(null);
        add(panel);

        JLabel barcodeLabel = new JLabel("BARCODE:");
        barcodeLabel.setBounds(65,40,110,33);
        barcodeLabel.setFont(new Font(Constants.DEFAULT_FONT,Font.PLAIN,22));
        panel.add(barcodeLabel);

        TextField barcodeTextField = new TextField("");
        barcodeTextField.setBounds(190,40,220,33);
        panel.add(barcodeTextField);

        JLabel titleLabel = new JLabel("TITLE:");
        titleLabel.setBounds(115,90,65,33);
        titleLabel.setFont(new Font(Constants.DEFAULT_FONT,Font.PLAIN,22));
        panel.add(titleLabel);

        TextField titleTextField = new TextField("");
        titleTextField.setBounds(190,90,220,33);
        panel.add(titleTextField);

        JLabel authorsLabel = new JLabel("AUTHOR:");
        authorsLabel.setBounds(80,140,100,33);
        authorsLabel.setFont(new Font(Constants.DEFAULT_FONT,Font.PLAIN,22));
        panel.add(authorsLabel);

        TextField author = new TextField("");
        author.setBounds(190,140,220,33);
        panel.add(author);

        JLabel publisherLabel = new JLabel("PUBLISHER:");
        publisherLabel.setBounds(55,190,125,33);
        publisherLabel.setFont(new Font(Constants.DEFAULT_FONT,Font.PLAIN,22));
        panel.add(publisherLabel);

        TextField publisher = new TextField("");
        publisher.setBounds(190,190,220,33);
        panel.add(publisher);

        JLabel editionLabel = new JLabel("EDITION:");
        editionLabel.setBounds(83,240,100,33);
        editionLabel.setFont(new Font(Constants.DEFAULT_FONT,Font.PLAIN,22));
        panel.add(editionLabel);

        TextField edition = new TextField("");
        edition.setBounds(190,240,220,33);
        panel.add(edition);

        JLabel sellPriceLabel = new JLabel("SELL PRICE:");
        sellPriceLabel.setBounds(57,290,125,33);
        sellPriceLabel.setFont(new Font(Constants.DEFAULT_FONT,Font.PLAIN,22));
        panel.add(sellPriceLabel);

        TextField sellPrice = new TextField("");
        sellPrice.setBounds(190,290,220,33);
        panel.add(sellPrice);

        JLabel buyPriceLabel = new JLabel("INVOICE PRICE:");
        buyPriceLabel.setBounds(20,340,160,33);
        buyPriceLabel.setFont(new Font(Constants.DEFAULT_FONT,Font.PLAIN,22));
        panel.add(buyPriceLabel);

        TextField buyPrice = new TextField("");
        buyPrice.setBounds(190,340,220,33);
        panel.add(buyPrice);

        Button finishButton = new Button("FINISH", Constants.CONFIRM_GREEN, Color.WHITE);
        finishButton.setBounds(140,410,150,55);
        panel.add(finishButton);

        setVisible(true);
    }
}
