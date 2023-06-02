package br.com.ticotech.gbooks.java.view.sale;

import br.com.ticotech.gbooks.java.entities.CartBook;
import br.com.ticotech.gbooks.java.entities.Book;
import br.com.ticotech.gbooks.java.entities.CartTable;
import br.com.ticotech.gbooks.java.view.shared.Display;
import br.com.ticotech.gbooks.java.view.shared.Popups;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SaleService{
    private final List<CartBook> cartBookList = new ArrayList<>();
    private final CartTable cartTable = new CartTable(cartBookList);
    private final List<Book> availableBooksList;
    private SaleScreen saleScreen;

    private final String barcode = saleScreen.getCartSection().getCodeBarTextField().getText();
    private final Display toPayDisplay = saleScreen.getFinishSection().getToPayDisplay();

    public SaleService(List<Book> availableBooksList, SaleScreen saleScreen) {
        this.availableBooksList = availableBooksList;
        this.saleScreen = saleScreen;
    }
    public boolean addToCart() {
        // Percorre a lista de livros disponíveis
        for (Book book : availableBooksList) {
            try {
                // Verifica se o livro está em estoque e possui quantidade maior que zero
                if (existInStock(barcode) && book.getQuantity() > 0) {
                    // Obtém a quantidade de itens a ser adicionada ao carrinho a partir do campo de texto
                    int quantityItem = Integer.parseInt(saleScreen.getCartSection().getUnitsTextField().getText());

                    // Verifica se a quantidade é válida (menor ou igual à quantidade em estoque)
                    if (quantityItem <= book.getQuantity()) {
                        // Verifica se o código de barras do livro é igual ao código digitado
                        if (Objects.equals(barcode, book.getCode())) {
                            // Verifica se o livro já está no carrinho
                            if (existInCart(book.getCode())) {
                                // Atualiza o item no carrinho
                                for (CartBook item : cartTable.getSaleCart()) {
                                    if (Objects.equals(item.getCode(), book.getCode())) {
                                        item.setUnits(item.getUnits() + quantityItem);
                                        item.setTotalPrice(book.getPrice() * item.getUnits());
                                    }
                                }
                                updateItemTable();
                            } else {
                                // Adiciona um novo item ao carrinho
                                cartBookList.add(new CartBook(
                                        book.getCode(),
                                        book.getTitle(),
                                        quantityItem,
                                        book.getPrice(),
                                        Double.parseDouble(String.format("%.2f", book.getPrice() * quantityItem).replace(",", "."))
                                ));
                                insertItemTable(cartBookList.size() - 1);
                            }

                            // Atualiza o valor total a pagar na tela
                            saleScreen.getFinishSection().getToPayDisplay().setText(String.valueOf(String.format("%.2f", sumTotal())).replace(",", "."));
                            return true;
                        }
                    } else {
                        // Exibe uma mensagem de erro informando que a quantidade é maior do que a disponível em estoque
                        String messageError = String.format("Quantity for \"%S\" larger than in stock", barcode);
                        new Popups (messageError, 1);
                        break;
                    }
                } else {
                    // Verifica se a quantidade em estoque é zero
                    if (book.getQuantity() <= 0) {
                        // Exibe uma mensagem de erro informando que a quantidade em estoque é zero
                        String messageError = String.format("Quantity for \"%S\" is 0 in stock", barcode);
                        new Popups (messageError, 1);
                        break;
                    }

                    // Verifica se o código de barras é vazio
                    if (Objects.equals(barcode, "BARCODE")) {
                        // Exibe uma mensagem de erro informando que o campo de código de barras está vazio
                        new Popups ("The BARCODE field is empty!!", 1);
                    } else {
                        // Exibe uma mensagem de erro informando que o código de barras não foi encontrado
                        String messageError = String.format("Code bar \"%S\" not found", barcode);
                        new Popups (messageError, 1);
                    }
                    break;
                }
            } catch (NumberFormatException quantityEmpty) {
                // Exibe uma mensagem de erro informando que o campo de quantidade está vazio
                new Popups ("The UNITS field is empty!!", 1);
                break;
            }
        }
        return false;
    }

    private void insertItemTable(int index) {
        saleScreen.getCartSection().getModel().addRow(new Object[]{
            cartTable.getValueAt(index, 0),
            cartTable.getValueAt(index, 1),
            cartTable.getValueAt(index, 2),
            cartTable.getValueAt(index, 3),
            cartTable.getValueAt(index, 4)
        });
        updateItemTable();
    }

    private void updateItemTable() {
        for (int row = 0; row < saleScreen.getCartSection().getModel().getRowCount(); row++) {
            saleScreen.getCartSection().getModel().setValueAt(cartTable.getValueAt(row, 0), row, 0);
            saleScreen.getCartSection().getModel().setValueAt(cartTable.getValueAt(row, 1), row, 1);
            saleScreen.getCartSection().getModel().setValueAt(cartTable.getValueAt(row, 2), row, 2);
            saleScreen.getCartSection().getModel().setValueAt(cartTable.getValueAt(row, 3), row, 3);
            saleScreen.getCartSection().getModel().setValueAt(cartTable.getValueAt(row, 4), row, 4);
        }
    }

    public void removeItemTable() {
        double getTotal = Double.parseDouble(saleScreen.getFinishSection().getToPayDisplay().getText().replace(",", "."));
        int quantityItem;

        for (int row = 0; row < saleScreen.getCartSection().getModel().getRowCount(); row++) {
            String itemTable = (String) saleScreen.getCartSection().getModel().getValueAt(row, 0);
            String getCodeBar = barcode;

            if (Objects.equals(barcode, "BARCODE")) {
                new Popups ("The BARCODE field is empty!" ,1);
            } else if (!existInCart(barcode)) {
                String messageError = String.format("Code bar \"%S\" not found", barcode);
                new Popups (messageError, 1);
            } else {
                if (Objects.equals(getCodeBar, itemTable)) {
                    if (Objects.equals(saleScreen.getCartSection().getUnitsTextField().getText(), "UNITS")) {
                        getTotal -= (double) saleScreen.getCartSection().getModel().getValueAt(row, 4);
                        availableBooksList.get(row).setQuantity(cartBookList.get(row).getUnits() + availableBooksList.get(row).getQuantity());
                        saleScreen.getFinishSection().getToPayDisplay().setText(String.valueOf(String.format("%.2f", getTotal)).replace(",", "."));
                        saleScreen.getCartSection().getModel().removeRow(row);
                        cartBookList.remove(row);
                    } else {
                        quantityItem = Integer.parseInt(saleScreen.getCartSection().getUnitsTextField().getText());

                        if (quantityItem > (int) saleScreen.getCartSection().getModel().getValueAt(row, 2)) {
                            String messageError = String.format("Quantity for \"%S\" larger than in cart", barcode);
                            new Popups(messageError, 1);
                            break;
                        } else {
                            if (cartBookList.get(row).getUnits() - quantityItem <= 0) {
                                getTotal -= (double) saleScreen.getCartSection().getModel().getValueAt(row, 4);
                                saleScreen.getCartSection().getModel().removeRow(row);
                                cartBookList.remove(row);
                            } else {
                                cartBookList.get(row).setUnits(cartBookList.get(row).getUnits() - quantityItem);
                                getTotal -= (double) saleScreen.getCartSection().getModel().getValueAt(row, 3) * quantityItem;
                                cartBookList.get(row).setTotalPrice(
                                    Double.parseDouble(
                                        String.format(
                                            "%.2f",
                                            cartBookList.get(row).getTotalPrice() - (cartBookList.get(row).getUnitPrice() * quantityItem)).replace(",", "."))
                                );
                                updateItemTable();
                            }
                            saleScreen.getFinishSection().getToPayDisplay().setText(String.valueOf(String.format("%.2f", getTotal)).replace(",", "."));
                        }
                    }
                    saleScreen.getCartSection().getCodeBarTextField().reset();
                    saleScreen.getCartSection().getUnitsTextField().reset();
                    updateItemTable();
                    break;
                }
            }
        }
    }

    private boolean existInStock(String product) {
        for (Book item : availableBooksList) {
            if (Objects.equals(item.getCode(), product)) {
                return true;
            }
        }
        return false;
    }

    private boolean existInCart(String product) {
        for (CartBook item : cartBookList) {
            if (Objects.equals(item.getCode(), product)) {
                return true;
            }
        }
        return false;
    }

    private double sumTotal() {
        double total = 0;

        for (CartBook item : cartBookList) {
            total += item.getTotalPrice();
        }
        return total;
    }

    public void paymentCash() {
        String typePayment = "cash";
        verifyPayment(typePayment);
    }

    public void paymentCard() {
        if (saleScreen.getFinishSection().getCreditButton().isSelected()) {
            String typePayment = "credit";
            verifyPayment(typePayment);
        } else if (saleScreen.getFinishSection().getDebitButton().isSelected()) {
            String typePayment = "debit";
            verifyPayment(typePayment);
        }
    }

    private void verifyPayment(String typePayment) {
        try {
            if (!Objects.equals(saleScreen.getFinishSection().getPayedField().getText(), "PAYED")) {
                double getPayment = Double.parseDouble(saleScreen.getFinishSection().getPayedField().getText().replace(",", "."));
                double getTotal = Double.parseDouble(saleScreen.getFinishSection().getToPayDisplay().getText().replace(",", "."));
                String summing = String.format("%.2f", getPayment + getTotal).replace(",", ".");
                String subtraction = String.format("%.2f", getPayment - getTotal).replace(",", ".").replace("-", "");

                if (Objects.equals(typePayment, "cash") || Objects.equals(typePayment, "credit") || Objects.equals(typePayment, "debit")) {
                    if (getPayment == getTotal) {
                        saleScreen.getFinishSection().getChangeDisplay().setText("0");
                        saleScreen.getFinishSection().getToPayDisplay().setText("0");
                    } else if (getTotal < 0) {
                        if (getPayment > getTotal) {
                            saleScreen.getFinishSection().getChangeDisplay().setText(summing);
                            saleScreen.getFinishSection().getToPayDisplay().setText("0");
                        } else {
                            saleScreen.getFinishSection().getToPayDisplay().setText(summing);
                            saleScreen.getFinishSection().getChangeDisplay().setText("");
                        }
                    } else {
                        if (getPayment > getTotal) {
                            saleScreen.getFinishSection().getChangeDisplay().setText(subtraction);
                            saleScreen.getFinishSection().getToPayDisplay().setText("0");
                        } else {
                            saleScreen.getFinishSection().getToPayDisplay().setText(subtraction);
                            saleScreen.getFinishSection().getChangeDisplay().setText("0");
                        }
                    }
                }
            } else {
                saleScreen.getFinishSection().getToPayDisplay().setText("0");
            }
        } catch (NumberFormatException isEmpty) {
            new Popups("The PAYED field is empty!!",1);
        }
    }

    public boolean finishSale(String getType) {
        double getTotal = Double.parseDouble(saleScreen.getFinishSection().getToPayDisplay().getText().replace(",", "."));

        if (Objects.equals(getType, "cancel")) {
            Popups cancelSale = new Popups("Do you want to cancel your entire purchase?",2);

            if (cancelSale.getResponse()) {
                cleanTable("cancel");
                return true;
            }
        } else if (Objects.equals(getType, "finish")) {
            if (getTotal > 0 || getTotal < 0) {
                String messageError = String.format("Purchase cannot be finalized, missing \"%s\" to be paid", saleScreen.getFinishSection().getToPayDisplay().getText());
                new Popups (messageError, 1);
            } else {
                cleanTable("finish");
                return true;
            }
        }
        return false;
    }

    private void cleanTable(String type) {
        for (Book product : availableBooksList) {
            for (CartBook item : cartTable.getSaleCart()) {
                if (Objects.equals(item.getCode(), product.getCode())) {
                    try {
                        if (Objects.equals(type, "finish")) {
                            product.setQuantity(product.getQuantity() - item.getUnits());
                            saleScreen.getCartSection().getModel().removeRow(0);
                        } else if (Objects.equals(type, "cancel")) {
                            saleScreen.getCartSection().getModel().removeRow(0);
                        }
                    } catch (ArrayIndexOutOfBoundsException cleanTable) {
                        break;
                    }
                }
            }
        }

        cartBookList.clear();
        cartTable.getSaleCart().clear();
    }

    public void addStorage() {
        availableBooksList.add(new Book("CODE1", "Book1", "EDITOR", "PUBLISHER", 10, 25.17));
        availableBooksList.add(new Book("CODE2", "Book2", "EDITOR", "PUBLISHER", 10, 50.13));
        availableBooksList.add(new Book("CODE3", "Book3", "EDITOR", "PUBLISHER", 10, 10.21));
        availableBooksList.add(new Book("CODE4", "Book3", "EDITOR", "PUBLISHER", 10, 10.21));
    }
}
