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

    public SaleController(StockRepository stockRepository, SaleRepository saleRepository) {
        this.stockRepository = stockRepository;
        this.saleRepository = saleRepository;
        this.cartBookList.add(new CartBook("999","TITLE",1,10.10,10.10));
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
                    }
                }
            } else {
                cartBookList.add(new CartBook(
                        book.getCode(),
                        book.getTitle(),
                        units,
                        book.getFinalPrice(),
                        Double.parseDouble(String.format("%.2f", book.getFinalPrice() * units).replace(",", "."))
                ));
            }

            //String toPay = String.valueOf(String.format("%.2f", sumTotal())).replace(",", ".");
            //saleScreen.getFinishSection().getToPayDisplay().setText(toPay); //TODO
            return true;
        } else {
            String messageError = String.format("Barcode \"%S\" not found!", barcode);
            new Popups(messageError, 1);
            return false;
        }
    }


//        for (Book book : availableBooksList) {
//            try {
//                if (existInStock(barcode) && book.getUnits() > 0) {
//                    int quantityItem = Integer.parseInt(saleScreen.getCartSection().getUnitsTextField().getText());
//
//                    if (quantityItem <= book.getUnits()) {
//                        if (Objects.equals(barcode, book.getCode())) {
//                            if (existInCart(book.getCode())) {
//                                for (CartBook cartBook : cartTable.getSaleCart()) {
//                                    if (Objects.equals(cartBook.getCode(), book.getCode())) {
//                                        cartBook.setUnits(cartBook.getUnits() + quantityItem);
//                                        cartBook.setTotalPrice(book.getInvoicePrice() * cartBook.getUnits());
//                                    }
//                                }
//                                updateItemTable();
//                            } else {
//                                cartBookList.add(new CartBook(
//                                        book.getCode(),
//                                        book.getTitle(),
//                                        quantityItem,
//                                        book.getFinalPrice(),
//                                        Double.parseDouble(String.format("%.2f", book.getFinalPrice() * quantityItem).replace(",", "."))
//                                ));
//                                insertItemTable(cartBookList.size() - 1);
//                            }
//
//                            saleScreen.getFinishSection().getToPayDisplay().setText(String.valueOf(String.format("%.2f", sumTotal())).replace(",", "."));
//                            return true;
//                        }
//                    } else {
//                        String messageError = String.format("Quantity for \"%S\" larger than in stock", barcode);
//                        new Popups (messageError, 1);
//                        break;
//                    }
//                } else {
//                    if (book.getUnits() <= 0) {
//                        String messageError = String.format("Quantity for \"%S\" is 0 in stock", barcode);
//                        new Popups (messageError, 1);
//                        break;
//                    }
//
//                    if (Objects.equals(barcode, "BARCODE")) {
//                        new Popups ("The BARCODE field is empty!!", 1);
//                    } else {
//                        String messageError = String.format("Code bar \"%S\" not found", barcode);
//                        new Popups (messageError, 1);
//                    }
//                    break;
//                }
//            } catch (NumberFormatException quantityEmpty) {
//                new Popups ("The UNITS field is empty!!", 1);
//                break;
//            }
//        }
//        return false;
//    }


    public boolean removeItemTable(String barcode, int units) {
        //double toPay = Double.parseDouble(saleScreen.getFinishSection().getToPayDisplay().getText());

        if (!existInCart(barcode)) {
            String messageError = String.format("Barcode \"%S\" not found", barcode);
            new Popups(messageError, 1);
            return false;
        }
        else {
            for (int row = 0; row < cartTableModel.getRowCount(); row++) {
                String bookBarcode = (String) cartTableModel.getValueAt(row, 0);

                if (Objects.equals(barcode, bookBarcode)) {
                    if (units == 0) {
                        //toPay -= (double) cartTableModel.getValueAt(row, 4);
                        //stockRepository.getStock().get(row).setUnits(cartBookList.get(row).getUnits() + stockRepository.getStock().get(row).getUnits()); //TODO
                        //saleScreen.getFinishSection().getToPayDisplay().setText(String.valueOf(String.format("%.2f", toPay)).replace(",", ".")); //TODO
                        cartTableModel.removeRow(row);
                        cartBookList.remove(row);
                        return true;
                    } else {

                        if (units > (int) cartTableModel.getValueAt(row, 2)) {
                            String messageError = String.format("Quantity for remove of \"%S\" larger than in cart", barcode);
                            new Popups(messageError, 1);
                            return false;
                        } else {
                            if (cartBookList.get(row).getUnits() - units <= 0) {
                                //toPay -= (double) cartTableModel.getValueAt(row, 4);
                                cartTableModel.removeRow(row);
                                cartBookList.remove(row);
                                return true;
                            } else {
                                cartBookList.get(row).setUnits(cartBookList.get(row).getUnits() - units);
                                //toPay -= (double) cartTableModel.getValueAt(row, 3) * units;
                                cartBookList.get(row).setTotalPrice(
                                        Double.parseDouble(
                                                String.format(
                                                        "%.2f",
                                                        cartBookList.get(row).getTotalPrice() - (cartBookList.get(row).getUnitPrice() * units)).replace(",", "."))
                                );
                                return true;
                                //updateItemTable();
                            }
                            //saleScreen.getFinishSection().getToPayDisplay().setText(String.valueOf(String.format("%.2f", toPay)).replace(",", "."));//TODO
                        }
                    }
                    //saleScreen.getCartSection().getCodeBarTextField().reset();
                    //saleScreen.getCartSection().getUnitsTextField().reset();
                    //updateItemTable();
                }
            }
        }
        return false;
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

    private double sumTotal() {
        double total = 0;

        for (CartBook book : cartBookList) {
            total += book.getTotalPrice();
        }
        return total;
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

//    private void cleanTable(String type) { //TODO
//        for (Book book : stockRepository.getStock()) {
//            for (CartBook cartBook : cartTableModel.getSaleCart()) {
//                if (Objects.equals(cartBook.getCode(), book.getCode())) {
//                    try {
//                        if (Objects.equals(type, "finish")) {
//                            book.setUnits(book.getUnits() - cartBook.getUnits());
//                            cartTableModel.removeRow(0);
//                        } else if (Objects.equals(type, "cancel")) {
//                            saleScreen.getCartSection().getModel().removeRow(0);
//                        }
//                    } catch (ArrayIndexOutOfBoundsException cleanTable) {
//                        break;
//                    }
//                }
//            }
//        }
//
//        cartBookList.clear();
//        cartTableModel.getSaleCart().clear();
//    }

}
