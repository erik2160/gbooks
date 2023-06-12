package br.com.ticotech.gbooks.java.entities;

import br.com.ticotech.gbooks.java.controllers.SaleController;
import br.com.ticotech.gbooks.java.repository.SaleRepository;
import br.com.ticotech.gbooks.java.repository.StockRepository;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class CartTest {
    StockRepository stockRepository = new StockRepository();
    SaleRepository saleRepository;
    List<CartBook> cartBooks = new ArrayList<>();
    SaleController saleController = new SaleController(stockRepository, saleRepository);

    @Test
    void addToCart() {
        var book1 = stockRepository.getBook("111");
        var code1 = book1.getCode();
        var title1 = book1.getTitle();
        var invoicePrice1 = book1.getInvoicePrice();
        var finalPrice1 = book1.getFinalPrice();

        var book2 = stockRepository.getBook("222");
        var code2 = book2.getCode();
        var title2 = book2.getTitle();
        var invoicePrice2 = book2.getInvoicePrice();
        var finalPrice2 = book2.getFinalPrice();

        if (saleController.addToCart(code1, 5) && saleController.addToCart(code2, 5)) {
            this.cartBooks.add(new CartBook(code1, title1, 5, invoicePrice1, finalPrice1));
            this.cartBooks.add(new CartBook(code2, title2, 5, invoicePrice2, finalPrice2));
        }

        assertEquals(cartBooks.get(0).getCode(), code1);
        assertEquals(cartBooks.get(0).getTitle(), title1);
        assertEquals(cartBooks.get(0).getUnitPrice(), invoicePrice1);
        assertEquals(cartBooks.get(0).getTotalPrice(), finalPrice1);
        assertEquals(cartBooks.get(1).getCode(), code2);
        assertEquals(cartBooks.get(1).getTitle(), title2);
        assertEquals(cartBooks.get(1).getUnitPrice(), invoicePrice2);
        assertEquals(cartBooks.get(1).getTotalPrice(), finalPrice2);
    }

    @Test
    void removeFromCart() {
        addToCart();

        var code1 = cartBooks.get(0).getCode();
        var units1 = cartBooks.get(0).getUnits();
        var code2 = cartBooks.get(1).getCode();

        if (saleController.removeFromCart(code1, units1) && saleController.removeFromCart(code2, 2)) {
            this.cartBooks.remove(0);
            this.cartBooks.get(0).setUnits(cartBooks.get(0).getUnits() - 2);
        }

        assertNotEquals(cartBooks.get(0).getCode(), "111");
        assertEquals(cartBooks.get(0).getCode(), "222");
        assertEquals(cartBooks.get(0).getUnits(), 3);
    }
}
