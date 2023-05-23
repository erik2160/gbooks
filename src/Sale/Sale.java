package Sale;

import Storage.Storage;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Sale extends SaleScreen {
    private final List<SaleCart> saleCart = new ArrayList<>();
    private final List<Storage> storage = new ArrayList<>();
    private final TableCart tableCart = new TableCart(saleCart);
    public void addToCart() {
        for (Storage product : storage) {
            try {
                if (existInStock(saleScreen.getCodeBarTextField().getText())) {
                    int quantityItem = Integer.parseInt(saleScreen.getUnitsTextField().getText());

                    if (Objects.equals(saleScreen.getCodeBarTextField().getText(), product.getCode())) {
                        if (existInCart(product.getCode())) {
                            for (SaleCart item : tableCart.getSaleCart()) {
                                if (Objects.equals(item.getCode(), product.getCode())) {
                                    item.setUnits(item.getUnits() + quantityItem);
                                    item.setTotalPrice(product.getPrice() * item.getUnits());
                                }
                            }
                            updateItemTable();
                        } else {
                            saleCart.add(new SaleCart(
                                product.getCode(),
                                product.getTitle(),
                                quantityItem,
                                product.getPrice(),
                                product.getPrice() * quantityItem
                            ));
                            insertItemTable(saleCart.size() - 1);
                        }
                        saleScreen.getToPayDisplay().setText(String.valueOf(String.format("%.2f", sumTotal())).replace(",", "."));
                    }
                } else {
                    // ENTER VALIDATION OF CODE BAR NOT FOUND
                    break;
                }
            } catch (NumberFormatException quantityEmpty) {
                // ENTER VALIDATION OF EMPTY FIELD
            }
        }
    }

    private void insertItemTable(int index) {
        saleScreen.getModel().addRow(new Object[]{
            tableCart.getValueAt(index, 0),
            tableCart.getValueAt(index, 1),
            tableCart.getValueAt(index, 2),
            tableCart.getValueAt(index, 3),
            tableCart.getValueAt(index, 4)
        });
        updateItemTable();
    }

    private void updateItemTable() {
        for (int row = 0; row < saleScreen.getModel().getRowCount(); row++) {
            saleScreen.getModel().setValueAt(tableCart.getValueAt(row, 0), row, 0);
            saleScreen.getModel().setValueAt(tableCart.getValueAt(row, 1), row, 1);
            saleScreen.getModel().setValueAt(tableCart.getValueAt(row, 2), row, 2);
            saleScreen.getModel().setValueAt(tableCart.getValueAt(row, 3), row, 3);
            saleScreen.getModel().setValueAt(tableCart.getValueAt(row, 4), row, 4);
        }
    }

    public void removeItemTable() {
        double getTotal = Double.parseDouble(saleScreen.getToPayDisplay().getText().replace(",", "."));

        for (int row = 0; row < saleScreen.getModel().getRowCount(); row++) {
            if (Objects.equals(saleScreen.getCodeBarTextField().getText(), saleScreen.getModel().getValueAt(row, 0))) {
                getTotal -= (double) saleScreen.getModel().getValueAt(row, 4);
                saleScreen.getToPayDisplay().setText(String.valueOf(String.format("%.2f", getTotal)).replace(",", "."));
                saleScreen.getModel().removeRow(row);
                saleCart.remove(row);
                break;
            } else {
                // ENTER VALIDATION OF CODE BAR NOT FOUND
                break;
            }
        }
    }

    private boolean existInStock(String product) {
        for (Storage item : storage) {
            if (Objects.equals(item.getCode(), product)) {
                return true;
            }
        }
        return false;
    }

    private boolean existInCart(String product) {
        for (SaleCart item : saleCart) {
            if (Objects.equals(item.getCode(), product)) {
                return true;
            }
        }
        return false;
    }

    private double sumTotal() {
        double total = 0;

        for (SaleCart item : saleCart) {
            total += item.getTotalPrice();
        }
        return total;
    }

    public void paymentCash() {
        String typePayment = "cash";
        verifyPayment(typePayment);
    }

    public void paymentCard() {
        if (saleScreen.getCreditButton().isSelected()) {
            String typePayment = "credit";
            verifyPayment(typePayment);
        } else if (saleScreen.getDebitButton().isSelected()) {
            String typePayment = "debit";
            verifyPayment(typePayment);
        }
    }

    private void verifyPayment(String typePayment) {
        try {
            double getPayment = Double.parseDouble(saleScreen.getPayedField().getText().replace(",", "."));
            double getTotal = Double.parseDouble(saleScreen.getToPayDisplay().getText().replace(",", "."));
            String summing = String.format("%.2f", getPayment + getTotal).replace(",", ".");
            String subtraction = String.format("%.2f", getPayment - getTotal).replace(",", ".");

            if (Objects.equals(typePayment, "cash")) {
                if (getPayment == getTotal) {
                    saleScreen.getChangeDisplay().setText("0");
                    saleScreen.getToPayDisplay().setText("0");
                } else if (getTotal < 0) {
                    if (getPayment > getTotal) {
                        saleScreen.getChangeDisplay().setText(summing);
                        saleScreen.getToPayDisplay().setText("0");
                    } else {
                        saleScreen.getToPayDisplay().setText(summing);
                        saleScreen.getChangeDisplay().setText("");
                    }
                } else {
                    if (getPayment > getTotal) {
                        saleScreen.getChangeDisplay().setText(subtraction);
                        saleScreen.getToPayDisplay().setText("0");
                    } else {
                        saleScreen.getToPayDisplay().setText(subtraction);
                        saleScreen.getChangeDisplay().setText("0");
                    }
                }
            } else if (Objects.equals(typePayment, "credit")) {
                // ENTER LOGIC FOR PAYMENT WITH CREDIT CARD
                System.out.println("Empty");
            } else if (Objects.equals(typePayment, "debit")) {
                // ENTER LOGIC FOR PAYMENT WITH DEBIT CARD
                System.out.println("Empty");
            }
        } catch (NumberFormatException isEmpty) {
            // ENTER VALIDATION OF EMPTY FIELD
        }
    }

    public void finishSale(String getType) {
        double getTotal = Double.parseDouble(saleScreen.getToPayDisplay().getText().replace(",", "."));

        if (Objects.equals(getType, "cancel")) {
            // ENTER VALIDATE OF CANCEL SALE
        } else if (Objects.equals(getType, "finish")) {
            if (getTotal > 0 || getTotal < 0) {
                System.out.println("Block finish sale");
            } else {
                for (Storage product : storage) {
                    for (SaleCart item : tableCart.getSaleCart()) {
                        if (Objects.equals(item.getCode(), product.getCode())) {
                            product.setQuantity(product.getQuantity() - item.getUnits());
                            try {
                                saleScreen.getModel().removeRow(0);
                            } catch (ArrayIndexOutOfBoundsException cleanTable) {
                                break;
                            }
                        }
                    }
                }

                saleCart.clear();
                tableCart.getSaleCart().clear();
                saleScreen.getToPayDisplay().reset();
                saleScreen.getPayedField().reset();
                saleScreen.getChangeDisplay().reset();
                saleScreen.getCodeBarTextField().reset();
                saleScreen.getUnitsTextField().reset();
                saleScreen.getCardsButtons().clearSelection();
            }
        }





    }

    public void addStorage() {
        storage.add(new Storage("CODE1", "Book1", "EDITOR", "PUBLISHER", 10, 25.17));
        storage.add(new Storage("CODE2", "Book2", "EDITOR", "PUBLISHER", 10, 50.13));
        storage.add(new Storage("CODE3", "Book3", "EDITOR", "PUBLISHER", 10, 10.21));
        storage.add(new Storage("CODE4", "Book3", "EDITOR", "PUBLISHER", 10, 10.21));
    }
}
