package Sale;

import Elements.TextField;
import Storage.Storage;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Sale extends SaleScreen{
    private final List<SaleCart> saleCart = new ArrayList<>();
    private final List<Storage> storage = new ArrayList<>();
    private final TableCart tableCart = new TableCart(saleCart);
    public List<SaleCart> getSaleCart() {
        return saleCart;
    }
    public List<Storage> getStorage() {
        return storage;
    }
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
                    System.out.println(quantityItem);
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

            if (Objects.equals(typePayment, "cash")) {
                if (getPayment == getTotal) {
                    saleScreen.getChangeDisplay().setText("0");
                } else {
                    saleScreen.getChangeDisplay().setText(String.format("%.2f", getPayment - getTotal).replace(",", "."));
                }
            } else if (Objects.equals(typePayment, "credit") || Objects.equals(typePayment, "debit")) {
                saleScreen.getChangeDisplay().setText("0");
            }
        } catch (NumberFormatException isEmpty) {
            System.out.println("Right");
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

        if (saleScreen.getCreditButton().isSelected()) {
            saleScreen.getCreditButton().setSelected(false);
        } else if (saleScreen.getDebitButton().isSelected()) {
            saleScreen.getDebitButton().setSelected(false);
        }

        saleScreen.getToPayDisplay().setText("0");
        saleScreen.getPayedField().setText("0");
        saleScreen.getChangeDisplay().setText("0");
        saleScreen.getCodeBarTextField().setText("0");
    }

    public void addStorage() {
        storage.add(new Storage("CODE1", "Book1", "EDITOR", "PUBLISHER", 10, 25.17));
        storage.add(new Storage("CODE2", "Book2", "EDITOR", "PUBLISHER", 10, 50.13));
        storage.add(new Storage("CODE3", "Book3", "EDITOR", "PUBLISHER", 10, 10.21));
        storage.add(new Storage("CODE4", "Book3", "EDITOR", "PUBLISHER", 10, 10.21));
    }
}
