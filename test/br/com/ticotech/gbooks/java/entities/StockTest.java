package br.com.ticotech.gbooks.java.entities;

import br.com.ticotech.gbooks.java.controllers.StockController;
import br.com.ticotech.gbooks.java.repository.StockRepository;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class StockTest {
    StockRepository stockRepository = new StockRepository();
    StockController stockController = new StockController(stockRepository);

    @Test
    void getBook() {
        assertEquals(stockController.getBook("111").getCode(), "111");
        assertEquals(stockController.getBook("222").getCode(), "222");
        assertNotEquals(stockController.getBook("111").getCode(), "222");
        assertNotEquals(stockController.getBook("222").getCode(), "111");
    }

    @Test
    void addBook() {
        var code = "C1";
        var title = "T1";
        var author = "A1";
        var edition = "E1";
        var publisher = "P1";
        var units = 5;
        var invoicePrice = 123.12;
        var finalPrice = 25.12;
        this.stockRepository.addBook(new Book(code, title, author, edition, publisher, units, invoicePrice, finalPrice));

        assertEquals(stockRepository.getStock().get(3).getCode(), code);
        assertEquals(stockRepository.getStock().get(3).getTitle(), title);
        assertEquals(stockRepository.getStock().get(3).getAuthor(), author);
        assertEquals(stockRepository.getStock().get(3).getEdition(), edition);
        assertEquals(stockRepository.getStock().get(3).getPublisher(), publisher);
        assertEquals(stockRepository.getStock().get(3).getUnits(), units);
        assertEquals(stockRepository.getStock().get(3).getInvoicePrice(), invoicePrice);
        assertEquals(stockRepository.getStock().get(3).getFinalPrice(), finalPrice);
        assertNotEquals(stockRepository.getStock().get(2).getCode(), code);
        assertNotEquals(stockRepository.getStock().get(2).getTitle(), title);
        assertNotEquals(stockRepository.getStock().get(2).getAuthor(), author);
        assertNotEquals(stockRepository.getStock().get(2).getEdition(), edition);
        assertNotEquals(stockRepository.getStock().get(2).getPublisher(), publisher);
        assertNotEquals(stockRepository.getStock().get(2).getUnits(), units);
        assertNotEquals(stockRepository.getStock().get(2).getInvoicePrice(), invoicePrice);
        assertNotEquals(stockRepository.getStock().get(2).getFinalPrice(), finalPrice);
    }

    @Test
    void deleteBook() {
        stockController.deleteBook(stockRepository.getBook("111").getCode());
        stockController.deleteBook(stockRepository.getBook("222").getCode());
        assertNotEquals(stockRepository.getStock().get(0).getCode(), "111");
        assertNotEquals(stockRepository.getStock().get(0).getCode(), "222");
        assertEquals(stockRepository.getStock().get(0).getCode(), "333");
    }


}
