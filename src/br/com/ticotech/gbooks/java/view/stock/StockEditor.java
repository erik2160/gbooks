package br.com.ticotech.gbooks.java.view.stock;

import br.com.ticotech.gbooks.java.controllers.StockController;
import br.com.ticotech.gbooks.java.entities.Book;
import br.com.ticotech.gbooks.java.view.shared.Constants;
import br.com.ticotech.gbooks.java.view.shared.Popups;
import br.com.ticotech.gbooks.java.view.shared.TextField;
import br.com.ticotech.gbooks.java.view.shared.Button;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class StockEditor extends JFrame{

    private final TextField barcodeTextField;
    private final TextField titleTextField;
    private final TextField author;
    private final TextField edition;
    private final TextField publisher;
    private final TextField unitsField;
    private final TextField finalPrice;
    private final TextField invoicePrice;
    private Book book;
    public StockEditor(String type, String barcode, StockController stockController, StockScreen stockScreen) {
        setTitle(type);
        setSize(430, 585);
        setResizable(false);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (screenSize.width - 430) / 2;
        int y = (screenSize.height - 585) / 2;
        setLocation(x,y);

        JPanel panel = new JPanel();
        panel.setBackground(Constants.LIGHT_GRAY);
        panel.setLayout(null);
        add(panel);

        JLabel barcodeLabel = new JLabel("BARCODE:");
        barcodeLabel.setBounds(64,40,110,33);
        barcodeLabel.setFont(new Font(Constants.DEFAULT_FONT,Font.PLAIN,22));
        panel.add(barcodeLabel);

        barcodeTextField = new TextField("");
        barcodeTextField.setBounds(190,40,220,33);
        panel.add(barcodeTextField);

        JLabel titleLabel = new JLabel("TITLE:");
        titleLabel.setBounds(112,90,65,33);
        titleLabel.setFont(new Font(Constants.DEFAULT_FONT,Font.PLAIN,22));
        panel.add(titleLabel);

        titleTextField = new TextField("");
        titleTextField.setBounds(190,90,220,33);
        panel.add(titleTextField);

        JLabel authorsLabel = new JLabel("AUTHOR:");
        authorsLabel.setBounds(80,140,100,33);
        authorsLabel.setFont(new Font(Constants.DEFAULT_FONT,Font.PLAIN,22));
        panel.add(authorsLabel);

        author = new TextField("");
        author.setBounds(190,140,220,33);
        panel.add(author);

        JLabel editionLabel = new JLabel("EDITION:");
        editionLabel.setBounds(83,190,100,33);
        editionLabel.setFont(new Font(Constants.DEFAULT_FONT,Font.PLAIN,22));
        panel.add(editionLabel);

        edition = new TextField("");
        edition.setBounds(190,190,220,33);
        panel.add(edition);

        JLabel publisherLabel = new JLabel("PUBLISHER:");
        publisherLabel.setBounds(55,240,125,33);
        publisherLabel.setFont(new Font(Constants.DEFAULT_FONT,Font.PLAIN,22));
        panel.add(publisherLabel);

        publisher = new TextField("");
        publisher.setBounds(190,240,220,33);
        panel.add(publisher);

        JLabel unitsLabel = new JLabel("UNITS:");
        unitsLabel.setBounds(108,290,100,33);
        unitsLabel.setFont(new Font(Constants.DEFAULT_FONT,Font.PLAIN,22));
        panel.add(unitsLabel);

        unitsField = new TextField("");
        unitsField.setBounds(190,290,220,33);
        panel.add(unitsField);

        JLabel buyPriceLabel = new JLabel("INVOICE PRICE:");
        buyPriceLabel.setBounds(17,340,160,33);
        buyPriceLabel.setFont(new Font(Constants.DEFAULT_FONT,Font.PLAIN,22));
        panel.add(buyPriceLabel);

        invoicePrice = new TextField("");
        invoicePrice.setBounds(190,340,220,33);
        panel.add(invoicePrice);

        JLabel sellPriceLabel = new JLabel("SELL PRICE:");
        sellPriceLabel.setBounds(55,390,130,33);
        sellPriceLabel.setFont(new Font(Constants.DEFAULT_FONT,Font.PLAIN,22));
        panel.add(sellPriceLabel);

        finalPrice = new TextField("");
        finalPrice.setBounds(190,390,220,33);
        panel.add(finalPrice);

        Button finishButton = new Button(Constants.FINISH_STOCK_BUTTON);
        finishButton.setBounds(25,410,374,140);
        panel.add(finishButton);

        switch (type) {
            case ("EDITION") -> {
                book = stockController.getBook(barcode);
                getBookAttributes();
                finishButton.addActionListener(e -> {
                    if(verifyInput()) {
                        if ((stockController.getBook(barcodeTextField.getText()) == null) || Objects.equals(barcodeTextField.getText(), book.getCode())) {
                            setBookAttributes();
                            stockScreen.getTable().setVisible(false);
                            stockScreen.getTable().setVisible(true);
                            dispose();
                        } else {
                            new Popups("There is already a book with this code", 1);
                        }
                    }
                });
            }
            case ("ADD") -> finishButton.addActionListener(e -> {
                if(verifyInput()) {
                    if (stockController.getBook(barcodeTextField.getText()) == null) {
                        stockController.addBook(getNewBook());
                        stockScreen.getTable().setVisible(false);
                        stockScreen.getTable().setVisible(true);
                        dispose();
                    } else {
                        new Popups("There is already a book with this code", 1);
                    }
                }
            });
        }

        setVisible(true);
    }
    
    private boolean verifyInput(){
        TextField[] textFields = {barcodeTextField, titleTextField, author,edition, publisher,unitsField, invoicePrice,finalPrice};
        for (TextField input : textFields){
            if (Objects.equals(input.getText(), "")){
                new Popups("There is a empty field.",1);
                return false;
            }
        }
        TextField[] intInputs = {edition, unitsField};
        for (TextField input : intInputs){
            try {
                Integer.parseInt(input.getText());
            }catch (NumberFormatException e){
                new Popups("Letter in number field.",1);
                return false;
            }
        }
        TextField[] doubleInputs = {invoicePrice, finalPrice};
        for (TextField input : doubleInputs) {
            try {
                Double.parseDouble(input.getText().replace(",","."));
            }catch (NumberFormatException e){
                new Popups("Letter in number field",1);
                return false;
            }
        }
        return true;
    }

    private void getBookAttributes(){
        barcodeTextField.setText(book.getCode());
        titleTextField.setText(book.getTitle());
        author.setText(book.getAuthor());
        edition.setText(book.getEdition());
        publisher.setText(book.getPublisher());
        unitsField.setText(String.valueOf(book.getUnits()));
        invoicePrice.setText(String.valueOf(book.getInvoicePrice()));
        finalPrice.setText(String.valueOf(book.getFinalPrice()));
    }

    private void setBookAttributes(){
        book.setCode(barcodeTextField.getText());
        book.setTitle(titleTextField.getText());
        book.setAuthor(author.getText());
        book.setEdition(edition.getText());
        book.setPublisher(publisher.getText());
        book.setUnits(Integer.parseInt(unitsField.getText()));
        book.setInvoicePrice(Double.parseDouble(invoicePrice.getText().replace(",",".")));
        book.setFinalPrice(Double.parseDouble(finalPrice.getText().replace(",",".")));
    }

    private Book getNewBook(){
        return new Book(
                barcodeTextField.getText(),
                titleTextField.getText(),
                author.getText(),
                edition.getText(),
                publisher.getText(),
                Integer.parseInt(unitsField.getText()),
                Double.parseDouble(invoicePrice.getText().replace(",",".")),
                Double.parseDouble(finalPrice.getText().replace(",","."))
        );
    }
}
