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
            int quantityItem = Integer.parseInt(saleScreen.getUnitsTextField().getText());

            if (Objects.equals(saleScreen.getCodeBarTextField().getText(), product.getCode())) {
                if (isExist(product.getCode())) {
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

    private boolean isExist(String product) {
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
                    saleScreen.getToPayDisplay().setText(summing);
                    saleScreen.getChangeDisplay().setText("");
                } else {
                    if (getPayment > getTotal) {
                        saleScreen.getChangeDisplay().setText(subtraction);
                        saleScreen.getToPayDisplay().setText("0");
                    } else {
                        saleScreen.getToPayDisplay().setText(subtraction);
                        saleScreen.getChangeDisplay().setText("0");
                    }
                }
            } else if (Objects.equals(typePayment, "credit") || Objects.equals(typePayment, "debit")) {
                // ENTER LOGIC FOR PAYMENT WITH CARD
                System.out.println("Empty");
            }
        } catch (NumberFormatException isEmpty) {
            // ENTER VALIDATE EXCEPTION
        }
    }

    public void finishSale() {
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

        if (saleScreen.getCreditButton().isSelected() || saleScreen.getDebitButton().isSelected()) {
            saleScreen.getCreditButton().setSelected(false);
        }

        saleScreen.getToPayDisplay().setText("0");
        saleScreen.getPayedField().setText("");
        saleScreen.getChangeDisplay().setText("");
        saleScreen.getCodeBarTextField().setText("");
    }

    public void addStorage() {
        storage.add(new Storage("CODE1", "Book1", "EDITOR", "PUBLISHER", 10, 25.17));
        storage.add(new Storage("CODE2", "Book2", "EDITOR", "PUBLISHER", 10, 50.13));
        storage.add(new Storage("CODE3", "Book3", "EDITOR", "PUBLISHER", 10, 10.21));
        storage.add(new Storage("CODE4", "Book3", "EDITOR", "PUBLISHER", 10, 10.21));
    }
}
