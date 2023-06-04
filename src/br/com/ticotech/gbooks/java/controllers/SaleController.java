package br.com.ticotech.gbooks.java.controllers;

import br.com.ticotech.gbooks.java.entities.CartBook;
import br.com.ticotech.gbooks.java.entities.Book;
import br.com.ticotech.gbooks.java.entities.CartTableModel;
import br.com.ticotech.gbooks.java.repository.SaleRepository;
import br.com.ticotech.gbooks.java.repository.StockRepository;
import br.com.ticotech.gbooks.java.view.shared.Popups;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SaleController {
    private final StockRepository stockRepository;
    private final List<CartBook> cartBookList = new ArrayList<>();
    private final CartTableModel cartTableModel = new CartTableModel(cartBookList);
    private final SaleRepository saleRepository;
    private double toPay;
    private double payedInCash;
    private double cashChange;
    private double payedByCard;
    private double totalPayed = payedInCash+ payedByCard;

    public String getToPay() {
        double toPayRounded = Math.round(toPay*100.0)/100.0;
        return String.valueOf(toPayRounded);
    }

    public double getCashChange() { //TODO
        return cashChange;
    }

    public String getPayedInCash() {
        double payedInCashRounded = Math.round(payedInCash*100.0)/100.0;
        return String.valueOf(payedInCashRounded);
    }
    public String getPayedByCard() {
        double payedByCardRounded = Math.round(payedByCard*100.0)/100.0;
        return String.valueOf(payedByCardRounded);
    }
    public String getTotalPayed() {
        double totalPayedRounded = Math.round(totalPayed*100.0)/100.0;
        return String.valueOf(totalPayedRounded);
    }

    public SaleController(StockRepository stockRepository, SaleRepository saleRepository) {
        this.stockRepository = stockRepository;
        this.saleRepository = saleRepository;
    }

    public CartTableModel getCartTableModel() {
        return cartTableModel;
    }

    public boolean addToCart(String barcode, int units) {
        if (existInStock(barcode)) {
            Book book = stockRepository.getBook(barcode);

            if (book.getUnits() == 0) {
                new Popups("There is no more units of this book in the stock", 1);
                return false;
            }
            else if (units > book.getUnits()) {
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
            }

            return true;
        } else {
            String messageError = String.format("Barcode \"%S\" not found!", barcode);
            new Popups(messageError, 1);
            return false;
        }
    }

    public void removeFromCart(String barcode, int units) {
        if (!existInCart(barcode)) {
            String messageError = String.format("Barcode \"%S\" not found!", barcode);
            new Popups(messageError, 1);
        }
        else {
            CartBook book = getBookInCart(barcode);
            assert book != null;
            if (units > book.getUnits()) {
                String messageError = String.format("Quantity for remove of \"%S\" larger than in cart!", barcode);
                new Popups(messageError, 1);
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
            }
        }
    }

    public void cancelSale() {
        List<CartBook> booksForRemove = new ArrayList<>();
        for (CartBook book:cartBookList){
            System.out.println(book.getCode());
            toPay -= book.getUnitPrice()*book.getUnits();
            System.out.println(toPay);
            booksForRemove.add(book);
        }
        cartBookList.removeAll(booksForRemove);
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

    public void cashPayment(double value){
        toPay-=value;
        payedInCash+=value;
        totalPayed+=value;

    }

    public void creditCardPayment(double value){
        toPay-=value;
        payedByCard+=value;
        totalPayed+=value;
    }


//    public void paymentCash() { //TODO
//        String typePayment = "cash";
//        verifyPayment(typePayment);
//    }

//    public void paymentCard() { //TODO
//        if (saleScreen.getFinishSection().getCreditButton().isSelected()) {
//            String typePayment = "credit";
//            verifyPayment(typePayment);
//        } else if (saleScreen.getFinishSection().getDebitButton().isSelected()) {
//            String typePayment = "debit";
//            verifyPayment(typePayment);
//        }
//    }

//    private void verifyPayment(String typePayment) { //TODO
//        try {
//            if (!Objects.equals(saleScreen.getFinishSection().getPayedField().getText(), "PAYED")) {
//                double getPayment = Double.parseDouble(saleScreen.getFinishSection().getPayedField().getText().replace(",", "."));
//                double getTotal = Double.parseDouble(saleScreen.getFinishSection().getToPayDisplay().getText().replace(",", "."));
//                String summing = String.format("%.2f", getPayment + getTotal).replace(",", ".");
//                String subtraction = String.format("%.2f", getPayment - getTotal).replace(",", ".").replace("-", "");
//
//                if (Objects.equals(typePayment, "cash") || Objects.equals(typePayment, "credit") || Objects.equals(typePayment, "debit")) {
//                    if (getPayment == getTotal) {
//                        saleScreen.getFinishSection().getChangeDisplay().setText("0");
//                        saleScreen.getFinishSection().getToPayDisplay().setText("0");
//                    } else if (getTotal < 0) {
//                        if (getPayment > getTotal) {
//                            saleScreen.getFinishSection().getChangeDisplay().setText(summing);
//                            saleScreen.getFinishSection().getToPayDisplay().setText("0");
//                        } else {
//                            saleScreen.getFinishSection().getToPayDisplay().setText(summing);
//                            saleScreen.getFinishSection().getChangeDisplay().setText("");
//                        }
//                    } else {
//                        if (getPayment > getTotal) {
//                            saleScreen.getFinishSection().getChangeDisplay().setText(subtraction);
//                            saleScreen.getFinishSection().getToPayDisplay().setText("0");
//                        } else {
//                            saleScreen.getFinishSection().getToPayDisplay().setText(subtraction);
//                            saleScreen.getFinishSection().getChangeDisplay().setText("0");
//                        }
//                    }
//                }
//            } else {
//                saleScreen.getFinishSection().getToPayDisplay().setText("0");
//            }
//        } catch (NumberFormatException isEmpty) {
//            new Popups("The PAYED field is empty!!",1);
//        }
//    }

//    public boolean finishSale(String getType) { //TODO
//        //double getTotal = Double.parseDouble(saleScreen.getFinishSection().getToPayDisplay().getText().replace(",", "."));
//        double getTotal = sumTotal();
//
//        if (Objects.equals(getType, "cancel")) {
//            Popups cancelSale = new Popups("Do you want to cancel your entire purchase?",2);
//
//            if (cancelSale.getResponse()) {
//                cleanTable("cancel");
//                return true;
//            }
//        } else if (Objects.equals(getType, "finish")) {
//            if (getTotal > 0 || getTotal < 0) {
//                String messageError = String.format("Purchase cannot be finalized, missing \"%s\" to be paid", saleScreen.getFinishSection().getToPayDisplay().getText());
//                new Popups (messageError, 1);
//            } else {
//                cleanTable("finish");
//                return true;
//            }
//        }
//        return false;
//    }


}
