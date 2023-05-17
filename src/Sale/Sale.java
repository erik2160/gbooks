package Sale;

import Storage.Storage;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Sale extends SaleScreen {
    private final List<SaleCart> saleCart = new ArrayList<>();
    private final List<Storage> storage = new ArrayList<>();

    public List<SaleCart> getSaleCart() {
        return saleCart;
    }

    public List<Storage> getStorage() {
        return storage;
    }

    public void addToCart() {

    }

    private boolean isExist(String key) {
        for (SaleCart item : saleCart) {
            if (Objects.equals(item.getCode(), key)) {
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
}
