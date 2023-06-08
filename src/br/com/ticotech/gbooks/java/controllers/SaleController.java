package br.com.ticotech.gbooks.java.controllers;

import br.com.ticotech.gbooks.java.entities.CartBook;
import br.com.ticotech.gbooks.java.entities.Book;
import br.com.ticotech.gbooks.java.view.sale.CartTableModel;
import br.com.ticotech.gbooks.java.entities.Sale;
import br.com.ticotech.gbooks.java.repository.SaleRepository;
import br.com.ticotech.gbooks.java.repository.StockRepository;
import br.com.ticotech.gbooks.java.view.shared.Popups;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class SaleController {
    private final StockRepository stockRepository;
    private final List<CartBook> cartBookList = new ArrayList<>();
    private final CartTableModel cartTableModel = new CartTableModel(cartBookList);
    private final SaleRepository saleRepository;
    private boolean firstPayment = true;
    private double toPay;
    private double cashChange;

    public boolean isFirstPayment() {
        return firstPayment;
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

    public SaleController(StockRepository stockRepository, SaleRepository saleRepository) {
        this.stockRepository = stockRepository;
        this.saleRepository = saleRepository;
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
                        firstPayment = true;
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
                toPay +=newCartBook.getTotalPrice();
                firstPayment = true;
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
                    cartBookList.remove(book);
                } else {
                    book.setUnits(book.getUnits() - units);
                    book.setTotalPrice(book.getUnitPrice() * book.getUnits());
                    toPay -= book.getUnitPrice() * units;
                    if (book.getUnits() == 0) {
                        cartBookList.remove(book);
                    }
                }
                return true;
            }
        }
    }

    public void cancelSale() {
        List<CartBook> booksForRemove = new ArrayList<>(cartBookList);
        cartBookList.removeAll(booksForRemove);
        toPay= 00.00;
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
        firstPayment = false;
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
        firstPayment = false;
        return true;
    }

    public void registerSecondPayment(){
        toPay = 00.00;
    }

    public boolean finishSale(String cpf){
        if (toPay==0){
            for(CartBook cartBook: cartBookList){
                stockRepository.alterUnits("remove", cartBook.getUnits(),cartBook.getCode());
            }
            List<CartBook> saleList = new ArrayList<>(cartBookList);
            Sale sale = new Sale(cpf,new Date(),saleList);
            saleRepository.addSale(sale);
            firstPayment = true;
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

}
