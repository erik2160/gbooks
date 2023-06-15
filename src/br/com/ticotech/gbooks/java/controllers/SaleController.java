package br.com.ticotech.gbooks.java.controllers;

import br.com.ticotech.gbooks.java.entities.CartBook;
import br.com.ticotech.gbooks.java.entities.Book;
import br.com.ticotech.gbooks.java.entities.Client;
import br.com.ticotech.gbooks.java.repository.ClientRepository;
import br.com.ticotech.gbooks.java.view.sale.CartTableModel;
import br.com.ticotech.gbooks.java.entities.Sale;
import br.com.ticotech.gbooks.java.repository.SaleRepository;
import br.com.ticotech.gbooks.java.repository.StockRepository;
import br.com.ticotech.gbooks.java.view.shared.Popups;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class SaleController {
    private final StockRepository stockRepository;
    private final List<CartBook> cartBookList = new ArrayList<>();
    private final CartTableModel cartTableModel = new CartTableModel(cartBookList);
    private final SaleRepository saleRepository;
    private final ClientRepository clientRepository;
    private Client client;
    private int points;
    private boolean usingPoints;
    private double discount = 00.00;
    private double total;
    private double toPay;
    private double cashChange;
    private int nfeNumber = 0;

    public int getPoints() {
        return points;
    }
    public double getDiscount() {
        return discount;
    }
    public String getToPay() {
        double toPayRounded = Math.round(toPay*100.0)/100.0;
        return String.valueOf(toPayRounded);
    }
    public String getCashChange() {
        double cashChangeRounded = Math.round(cashChange*100.0)/100.0;
        return String.valueOf(cashChangeRounded);
    }
    public CartTableModel getCartTableModel() {
        return cartTableModel;
    }

    public SaleController(StockRepository stockRepository, SaleRepository saleRepository, ClientRepository clientRepository) {
        this.stockRepository = stockRepository;
        this.saleRepository = saleRepository;
        this.clientRepository = clientRepository;
    }

    public boolean addToCart(String barcode, int units) {
        if (existInStock(barcode)) {
            Book book = stockRepository.getBook(barcode);

            if (book.getUnits() == 0) {
                new Popups("There is no more units of this book in the stock", 1);
                return false;
            } else if (units < 1) {
                new Popups("UNITS number needs to be positive", 1);
                return false;
            } else if (units > book.getUnits()) {
                new Popups("Units greater than in stock", 1);
                return false;
            }
            else if (existInCart(barcode)) {
                for (CartBook cartBook : cartBookList) {
                    if (Objects.equals(cartBook.getCode(), book.getCode())) {
                        if(cartBook.getUnits()+units>book.getUnits()){
                            new Popups("There is no more units of this book in the stock", 1);
                            return false;
                        }
                        cartBook.setUnits(cartBook.getUnits() + units);
                        cartBook.setTotalPrice(book.getFinalPrice() * cartBook.getUnits());
                        toPay += cartBook.getUnitPrice()*units;
                        total += cartBook.getUnitPrice()*units;
                        return true;
                    }
                }
            } else {
                CartBook newCartBook = new CartBook(
                        book.getCode(),
                        book.getTitle(),
                        units,
                        book.getFinalPrice(),
                        Double.parseDouble(String.format("%.2f", book.getFinalPrice() * units).replace(",", "."))
                );
                cartBookList.add(newCartBook);
                toPay += newCartBook.getTotalPrice();
                total += newCartBook.getUnitPrice()*units;
                return true;
            }
        } else {
            String messageError = String.format("Barcode \"%S\" not found!", barcode);
            new Popups(messageError, 1);
            return false;
        }
        return false;
    }

    public boolean removeFromCart(String barcode, int units) {
        if (!existInCart(barcode)) {
            String messageError = String.format("Barcode \"%S\" not found!", barcode);
            new Popups(messageError, 1);
            return false;
        }
        else {
            CartBook book = getBookInCart(barcode);
            assert book != null;
            if (units > book.getUnits()) {
                String messageError = String.format("Quantity for remove of \"%S\" larger than in cart!", barcode);
                new Popups(messageError, 1);
                return false;
            }else {
                if (units == 0) {
                    toPay -= book.getTotalPrice();
                    total -= book.getTotalPrice();
                    cartBookList.remove(book);
                } else {
                    book.setUnits(book.getUnits() - units);
                    book.setTotalPrice(book.getUnitPrice() * book.getUnits());
                    toPay -= book.getUnitPrice() * units;
                    total -= book.getUnitPrice() * units;
                    if (book.getUnits() == 0) {
                        cartBookList.remove(book);
                    }
                }
                if (toPay <0){toPay= 0.0;}
                return true;
            }
        }
    }

    public void cancelSale() {
        List<CartBook> booksForRemove = new ArrayList<>(cartBookList);
        cartBookList.removeAll(booksForRemove);
        toPay= 00.00;
        total = 00.00;
    }

    public void setClient(String cpf){
        if (clientRepository.getClient(cpf)!=null) {
            this.client = clientRepository.getClient(cpf);
            points = client.getPoints();
            discount = Math.round(points * 0.05 * 100.0) / 100.0;
        }
        else {
            clientRepository.addClient(new Client(cpf, 0));
            points = 0;
            discount = 00.00;
        }
    }

    public boolean usePoints(){
        if (points == 0){
            new Popups("There is no points for use!",1);
            return false;
        }
        if (discount > toPay){
            toPay = 00.00;
        }
        else {
            toPay = toPay - discount;
        }
        usingPoints = true;
        return true;
    }

    public void cancelUsePoints(){
        toPay += discount;
        usingPoints = false;
    }

    public void registerCashPayment(double value){
        if(value>toPay) {
            cashChange = value - toPay;
            toPay=00.00;
        } else if (value == toPay) {
            toPay=00.00;
        } else {
            toPay -= value;
        }
    }

    public boolean registerCardPayment(double value){
        if(value>toPay){
            new Popups("Value greater than necessary!",1);
            return false;
        } else if (toPay == value) {
            toPay = 00.00;
        } else {
            toPay -= value;
        }
        return true;
    }

    public boolean finishSale(String cpf){
        if (toPay==0){
            if(client!=null) {
                if (usingPoints) {
                    client.setPoints(0);
                } else {
                    client.setPoints(client.getPoints() + (int) total);
                }
            }

            for(CartBook cartBook: cartBookList){
                stockRepository.alterUnits("remove", cartBook.getUnits(),cartBook.getCode());
            }

            List<CartBook> bookList = new ArrayList<>(cartBookList);
            List<Double> invoicePriceList = new ArrayList<>();
            for (CartBook book :bookList){
                invoicePriceList.add(stockRepository.getBook(book.getCode()).getInvoicePrice());
            }
            Sale sale = new Sale(cpf,new Date(),bookList, invoicePriceList);
            saleRepository.addSale(sale);

            createNfe(cpf);

            cancelSale();
            return true;
        }
        else{
            new Popups("The total amount has not yet been paid!",1);
            return false;
        }
    }

    private boolean existInStock(String barcode) {
        for (Book book : stockRepository.getStock()) {
            if (Objects.equals(book.getCode(), barcode)) {
                return true;
            }
        }
        return false;
    }
    private boolean existInCart(String barcode) {
        for (CartBook book : cartBookList) {
            if (Objects.equals(book.getCode(), barcode)) {
                return true;
            }
        }
        return false;
    }

    private CartBook getBookInCart(String barcode){
        for(CartBook book : cartBookList){
            if (Objects.equals(book.getCode(), barcode)){
                return book;
            }
        }
        return null;
    }

    private void createNfe(String cpf){
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy - HH:mm:ss");
        String fileName = "NFE("+nfeNumber++ +").txt";
        double total = 0;

        try {
            FileWriter writer = new FileWriter(fileName);
            BufferedWriter buffer = new BufferedWriter(writer);

            buffer.write("--------------------------------------\n");
            buffer.write("BOOKSTORE G-BOOKS\n");
            buffer.write("CNPJ 32.456.789/0001-23\n");
            buffer.write("--------------------------------------\n");
            buffer.write("PRODUCTS:\n");
            for (CartBook book : cartBookList){
                buffer.write(book.getTitle() + " x" + book.getUnits() +"\n");
                total+= book.getTotalPrice();
            }
            total = Math.round(total*100.0)/100.0;
            buffer.write("TOTAL: " + total + "\n");
            if(usingPoints){buffer.write("DISCOUNT: " + discount + "\n");}
            buffer.write("--------------------------------------\n");
            buffer.write("EMISSION DATE: " + dateFormat.format(new Date())+"\n");
            buffer.write("--------------------------------------\n");
            buffer.write("CONSUMER: " + cpf);

            buffer.close();
        } catch (IOException ignored) {
        }
    }

}
