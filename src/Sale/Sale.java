package Sale;

import Storage.Storage;

import javax.swing.*;
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

                    if (quantityItem <= product.getQuantity()) {
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
                                saleScreen.getPayedField().setEnabled(true);
                                saleScreen.getFinishButton().setEnabled(true);
                                saleScreen.getCreditButton().setEnabled(true);
                                saleScreen.getDebitButton().setEnabled(true);
                                saleScreen.getButtonCancel().setEnabled(true);
                                saleScreen.getButtonRemove().setEnabled(true);
                            }
                            saleScreen.getToPayDisplay().setText(String.valueOf(String.format("%.2f", sumTotal())).replace(",", "."));
                        }
                    } else {
                        String messageError = String.format("Quantity for \"%S\" larger than in stock", saleScreen.getCodeBarTextField().getText());
                        JOptionPane.showMessageDialog(null, messageError, "Title", JOptionPane.ERROR_MESSAGE);
                        break;
                    }
                } else {
                    if (Objects.equals(saleScreen.getCodeBarTextField().getText(), "BARCODE")) {
                        JOptionPane.showMessageDialog(null, "MessageBarcodeEmpty", "Title", JOptionPane.WARNING_MESSAGE);
                    } else {
                        String messageError = String.format("Code bar \"%S\" not found", saleScreen.getCodeBarTextField().getText());
                        JOptionPane.showMessageDialog(null, messageError, "Title", JOptionPane.ERROR_MESSAGE);
                    }
                    break;
                }
            } catch (NumberFormatException quantityEmpty) {
                JOptionPane.showMessageDialog(null, "MessageUnitsEmpty", "Title", JOptionPane.WARNING_MESSAGE);
                break;
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
            String itemTable = (String) saleScreen.getModel().getValueAt(row, 0);
            String getCodeBar = saleScreen.getCodeBarTextField().getText();

            if (Objects.equals(getCodeBar, itemTable)) {
                getTotal -= (double) saleScreen.getModel().getValueAt(row, 4);
                saleScreen.getToPayDisplay().setText(String.valueOf(String.format("%.2f", getTotal)).replace(",", "."));
                saleScreen.getModel().removeRow(row);
                saleCart.remove(row);
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
            if (!Objects.equals(saleScreen.getPayedField().getText(), "PAYED")) {
                double getPayment = Double.parseDouble(saleScreen.getPayedField().getText().replace(",", "."));
                double getTotal = Double.parseDouble(saleScreen.getToPayDisplay().getText().replace(",", "."));
                String summing = String.format("%.2f", getPayment + getTotal).replace(",", ".");
                String subtraction = String.format("%.2f", getPayment - getTotal).replace(",", ".").replace("-", "");

                if (Objects.equals(typePayment, "cash") || Objects.equals(typePayment, "credit") || Objects.equals(typePayment, "debit")) {
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
                }
            } else {
                saleScreen.getToPayDisplay().setText("0");
            }
        } catch (NumberFormatException isEmpty) {
            JOptionPane.showMessageDialog(null, "Message", "Title", JOptionPane.WARNING_MESSAGE);
        }
    }

    public void finishSale(String getType) {
        double getTotal = Double.parseDouble(saleScreen.getToPayDisplay().getText().replace(",", "."));

        if (Objects.equals(getType, "cancel")) {
            int getOption = JOptionPane.showConfirmDialog(null, "Message", "Title", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

            if (getOption == JOptionPane.YES_OPTION) {
                cleanTable();
            }
        } else if (Objects.equals(getType, "finish")) {
            if (getTotal > 0 || getTotal < 0) {
                System.out.println("Block finish sale");
            } else {
                cleanTable();
            }
        }
    }

    private void cleanTable() {
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
        saleScreen.getPayedField().setEnabled(false);
        saleScreen.getFinishButton().setEnabled(false);
        saleScreen.getCreditButton().setEnabled(false);
        saleScreen.getDebitButton().setEnabled(false);
        saleScreen.getButtonCancel().setEnabled(false);
        saleScreen.getButtonRemove().setEnabled(false);
    }

    public void addStorage() {
        storage.add(new Storage("CODE1", "Book1", "EDITOR", "PUBLISHER", 10, 25.17));
        storage.add(new Storage("CODE2", "Book2", "EDITOR", "PUBLISHER", 10, 50.13));
        storage.add(new Storage("CODE3", "Book3", "EDITOR", "PUBLISHER", 10, 10.21));
        storage.add(new Storage("CODE4", "Book3", "EDITOR", "PUBLISHER", 10, 10.21));
    }
}
