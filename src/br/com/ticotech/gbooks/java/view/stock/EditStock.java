package br.com.ticotech.gbooks.java.view.stock;

import br.com.ticotech.gbooks.java.view.shared.Constants;
import br.com.ticotech.gbooks.java.view.shared.Button;
import br.com.ticotech.gbooks.java.view.shared.TextField;

import javax.swing.*;
import java.awt.*;

public class EditStock extends JFrame {

    public EditStock(String title) {
        setTitle(title);
        setSize(405, 535);
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
        barcodeLabel.setBounds(35,40,110,33);
        barcodeLabel.setFont(new Font(Constants.DEFAULT_FONT,Font.PLAIN,22));
        panel.add(barcodeLabel);

        TextField barcodeTextField = new TextField("BARCODE");
        barcodeTextField.setBounds(160,40,220,33);
        panel.add(barcodeTextField);

        JLabel titleLabel = new JLabel("TITLE:");
        titleLabel.setBounds(85,90,65,33);
        titleLabel.setFont(new Font(Constants.DEFAULT_FONT,Font.PLAIN,22));
        panel.add(titleLabel);

        TextField titleTextField = new TextField("TITLE");
        titleTextField.setBounds(160,90,220,33);
        panel.add(titleTextField);

        JLabel authorsLabel = new JLabel("AUTHOR:");
        authorsLabel.setBounds(50,140,100,33);
        authorsLabel.setFont(new Font(Constants.DEFAULT_FONT,Font.PLAIN,22));
        panel.add(authorsLabel);

        TextField author = new TextField("AUTHOR");
        author.setBounds(160,140,220,33);
        panel.add(author);

        JLabel publisherLabel = new JLabel("PUBLISHER:");
        publisherLabel.setBounds(25,190,125,33);
        publisherLabel.setFont(new Font(Constants.DEFAULT_FONT,Font.PLAIN,22));
        panel.add(publisherLabel);

        TextField publisher = new TextField("PUBLISHER");
        publisher.setBounds(160,190,220,33);
        panel.add(publisher);

        JLabel editionLabel = new JLabel("EDITION:");
        editionLabel.setBounds(52,240,100,33);
        editionLabel.setFont(new Font(Constants.DEFAULT_FONT,Font.PLAIN,22));
        panel.add(editionLabel);

        TextField edition = new TextField("EDITION");
        edition.setBounds(160,240,220,33);
        panel.add(edition);

        JLabel sellPriceLabel = new JLabel("SELL PRICE:");
        sellPriceLabel.setBounds(25,290,125,33);
        sellPriceLabel.setFont(new Font(Constants.DEFAULT_FONT,Font.PLAIN,22));
        panel.add(sellPriceLabel);

        TextField sellPrice = new TextField("PRICE FOR SALE");
        sellPrice.setBounds(160,290,220,33);
        panel.add(sellPrice);

        JLabel buyPriceLabel = new JLabel("BUY PRICE:");
        buyPriceLabel.setBounds(32,340,120,33);
        buyPriceLabel.setFont(new Font(Constants.DEFAULT_FONT,Font.PLAIN,22));
        panel.add(buyPriceLabel);

        TextField buyPrice = new TextField("BUYING PRICE");
        buyPrice.setBounds(160,340,220,33);
        panel.add(buyPrice);

        Button finishButton = new Button("FINISH", Constants.CONFIRM_GREEN, Color.WHITE);
        finishButton.setBounds(127,410,150,55);
        panel.add(finishButton);

        setVisible(true);
    }
}
