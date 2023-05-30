package br.com.ticotech.gbooks.java.view.sale;

import br.com.ticotech.gbooks.java.entities.SaleCart;
import br.com.ticotech.gbooks.java.entities.ListStock;
import br.com.ticotech.gbooks.java.entities.TableCart;
import br.com.ticotech.gbooks.java.view.shared.Popups;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Sale extends SaleScreen {
    private final List<SaleCart> saleCart = new ArrayList<>();
    private final TableCart tableCart = new TableCart(saleCart);
    private final List<ListStock> listStock;
    public Sale(List<ListStock> listStock) {
        this.listStock = listStock;
    }
    public boolean addToCart() {
        for (ListStock product : listStock) {
            try {
                if (existInStock(saleScreen.getCodeBarTextField().getText()) && product.getQuantity() > 0) {
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
                                    Double.parseDouble(String.format("%.2f", product.getPrice() * quantityItem).replace(",", "."))
                                ));
                                insertItemTable(saleCart.size() - 1);
                            }
                            saleScreen.getToPayDisplay().setText(String.valueOf(String.format("%.2f", sumTotal())).replace(",", "."));
                            return true;
                        }
                    } else {
                        String messageError = String.format("Quantity for \"%S\" larger than in stock", saleScreen.getCodeBarTextField().getText());
                        new Popups (messageError, 1);
                        break;
                    }
                } else {
                    if (product.getQuantity() <= 0) {
                        String messageError = String.format("Quantity for \"%S\" is 0 in stock", saleScreen.getCodeBarTextField().getText());
                        new Popups (messageError, 1);
                        break;
                    }

                    if (Objects.equals(saleScreen.getCodeBarTextField().getText(), "BARCODE")) {
                        new Popups ("The BARCODE field is empty!!", 1);
                    } else {
                        String messageError = String.format("Code bar \"%S\" not found", saleScreen.getCodeBarTextField().getText());
                        new Popups (messageError, 1);
                    }
                    break;
                }
            } catch (NumberFormatException quantityEmpty) {
                new Popups ("The UNITS field is empty!!", 1);
                break;
            }
        }
        return false;
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
        int quantityItem;

        for (int row = 0; row < saleScreen.getModel().getRowCount(); row++) {
            String itemTable = (String) saleScreen.getModel().getValueAt(row, 0);
            String getCodeBar = saleScreen.getCodeBarTextField().getText();

            if (Objects.equals(saleScreen.getCodeBarTextField().getText(), "BARCODE")) {
                new Popups ("The BARCODE field is empty!" ,1);
            } else if (!existInCart(saleScreen.getCodeBarTextField().getText())) {
                String messageError = String.format("Code bar \"%S\" not found", saleScreen.getCodeBarTextField().getText());
                new Popups (messageError, 1);
            } else {
                if (Objects.equals(getCodeBar, itemTable)) {
                    if (Objects.equals(saleScreen.getUnitsTextField().getText(), "UNITS")) {
                        getTotal -= (double) saleScreen.getModel().getValueAt(row, 4);
                        listStock.get(row).setQuantity(saleCart.get(row).getUnits() + listStock.get(row).getQuantity());
                        saleScreen.getToPayDisplay().setText(String.valueOf(String.format("%.2f", getTotal)).replace(",", "."));
                        saleScreen.getModel().removeRow(row);
                        saleCart.remove(row);
                    } else {
                        quantityItem = Integer.parseInt(saleScreen.getUnitsTextField().getText());

                        if (quantityItem > (int) saleScreen.getModel().getValueAt(row, 2)) {
                            String messageError = String.format("Quantity for \"%S\" larger than in cart", saleScreen.getCodeBarTextField().getText());
                            new Popups(messageError, 1);
                            break;
                        } else {
                            if (saleCart.get(row).getUnits() - quantityItem <= 0) {
                                getTotal -= (double) saleScreen.getModel().getValueAt(row, 4);
                                saleScreen.getModel().removeRow(row);
                                saleCart.remove(row);
                            } else {
                                saleCart.get(row).setUnits(saleCart.get(row).getUnits() - quantityItem);
                                getTotal -= (double) saleScreen.getModel().getValueAt(row, 3) * quantityItem;
                                saleCart.get(row).setTotalPrice(
                                    Double.parseDouble(
                                        String.format(
                                            "%.2f",
                                            saleCart.get(row).getTotalPrice() - (saleCart.get(row).getUnitPrice() * quantityItem)).replace(",", "."))
                                );
                                updateItemTable();
                            }
                            saleScreen.getToPayDisplay().setText(String.valueOf(String.format("%.2f", getTotal)).replace(",", "."));
                        }
                    }
                    saleScreen.getCodeBarTextField().reset();
                    saleScreen.getUnitsTextField().reset();
                    updateItemTable();
                    break;
                }
            }
        }
    }

    private boolean existInStock(String product) {
        for (ListStock item : listStock) {
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
            new Popups("The PAYED field is empty!!",1);
        }
    }

    public boolean finishSale(String getType) {
        double getTotal = Double.parseDouble(saleScreen.getToPayDisplay().getText().replace(",", "."));

        if (Objects.equals(getType, "cancel")) {
            Popups cancelSale = new Popups("Do you want to cancel your entire purchase?",2);

            if (cancelSale.getResponse()) {
                cleanTable("cancel");
                return true;
            }
        } else if (Objects.equals(getType, "finish")) {
            if (getTotal > 0 || getTotal < 0) {
                String messageError = String.format("Purchase cannot be finalized, missing \"%s\" to be paid", saleScreen.getToPayDisplay().getText());
                new Popups (messageError, 1);
            } else {
                cleanTable("finish");
                return true;
            }
        }
        return false;
    }

    private void cleanTable(String type) {
        for (ListStock product : listStock) {
            for (SaleCart item : tableCart.getSaleCart()) {
                if (Objects.equals(item.getCode(), product.getCode())) {
                    try {
                        if (Objects.equals(type, "finish")) {
                            product.setQuantity(product.getQuantity() - item.getUnits());
                            saleScreen.getModel().removeRow(0);
                        } else if (Objects.equals(type, "cancel")) {
                            saleScreen.getModel().removeRow(0);
                        }
                    } catch (ArrayIndexOutOfBoundsException cleanTable) {
                        break;
                    }
                }
            }
        }

        saleCart.clear();
        tableCart.getSaleCart().clear();
    }

    public void addStorage() {
        listStock.add(new ListStock("CODE1", "Book1", "EDITOR", "PUBLISHER", 10, 25.17));
        listStock.add(new ListStock("CODE2", "Book2", "EDITOR", "PUBLISHER", 10, 50.13));
        listStock.add(new ListStock("CODE3", "Book3", "EDITOR", "PUBLISHER", 10, 10.21));
        listStock.add(new ListStock("CODE4", "Book3", "EDITOR", "PUBLISHER", 10, 10.21));
    }
}
