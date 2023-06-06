package br.com.ticotech.gbooks.java.entities;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BookTest {

    private Book obj;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getCode() {
        //Arrange & Act
        var code = "C1";
        var title = "T1";
        var author = "A1";
        var edition = "E1";
        var publisher = "P1";
        var units = 5;
        var invoicePrice = 123.12;
        var finalPrice = 25.12;
        this.obj = new Book(code, title, author, edition, publisher, units, invoicePrice, finalPrice);
        //Assert
        assertEquals(obj.getCode(), code);
    }

    @Test
    void setCode() {
        //Arrange & Act
        var code = "C1";
        var code2 = "C2";
        var title = "T1";
        var author = "A1";
        var edition = "E1";
        var publisher = "P1";
        var units = 5;
        var invoicePrice = 123.12;
        var finalPrice = 25.12;
        this.obj = new Book(code, title, author, edition, publisher, units, invoicePrice, finalPrice);

        //Act
        this.obj.setCode(code2);
        //Assert
        assertEquals(obj.getCode(), code2);
    }

    @Test
    void getTitle() {
        //Arrange & Act
        var code = "C1";
        var title = "T1";
        var author = "A1";
        var edition = "E1";
        var publisher = "P1";
        var units = 5;
        var invoicePrice = 123.12;
        var finalPrice = 25.12;
        this.obj = new Book(code, title, author, edition, publisher, units, invoicePrice, finalPrice);
        //Assert
        assertEquals(obj.getTitle(), title);
    }

    @Test
    void setTitle() {
        //Arrange & Act
        var code = "C1";
        var title = "T1";
        var title2 = "T2";
        var author = "A1";
        var edition = "E1";
        var publisher = "P1";
        var units = 5;
        var invoicePrice = 123.12;
        var finalPrice = 25.12;
        this.obj = new Book(code, title, author, edition, publisher, units, invoicePrice, finalPrice);

        //Act
        this.obj.setTitle(title2);
        //Assert
        assertEquals(obj.getTitle(), title2);
    }

    @Test
    void getAuthor() {
        //Arrange & Act
        var code = "C1";
        var title = "T1";
        var author = "A1";
        var edition = "E1";
        var publisher = "P1";
        var units = 5;
        var invoicePrice = 123.12;
        var finalPrice = 25.12;
        this.obj = new Book(code, title, author, edition, publisher, units, invoicePrice, finalPrice);
        //Assert
        assertEquals(obj.getAuthor(), author);
    }

    @Test
    void setAuthor() {
        //Arrange & Act
        var code = "C1";
        var title = "T1";
        var author = "A1";
        var author2 = "A2";
        var edition = "E1";
        var publisher = "P1";
        var units = 5;
        var invoicePrice = 123.12;
        var finalPrice = 25.12;
        this.obj = new Book(code, title, author, edition, publisher, units, invoicePrice, finalPrice);

        //Act
        this.obj.setAuthor(author2);
        //Assert
        assertEquals(obj.getAuthor(), author2);
    }

    @Test
    void getEdition() {
        //Arrange & Act
        var code = "C1";
        var title = "T1";
        var author = "A1";
        var edition = "E1";
        var publisher = "P1";
        var units = 5;
        var invoicePrice = 123.12;
        var finalPrice = 25.12;
        this.obj = new Book(code, title, author, edition, publisher, units, invoicePrice, finalPrice);
        //Assert
        assertEquals(obj.getEdition(), edition);
    }

    @Test
    void setEdition() {
        //Arrange & Act
        var code = "C1";
        var title = "T1";
        var author = "A1";
        var edition = "E1";
        var edition2 = "A2";
        var publisher = "P1";
        var units = 5;
        var invoicePrice = 123.12;
        var finalPrice = 25.12;
        this.obj = new Book(code, title, author, edition, publisher, units, invoicePrice, finalPrice);

        //Act
        this.obj.setEdition(edition2);
        //Assert
        assertEquals(obj.getEdition(), edition2);
    }

    @Test
    void getPublisher() {
        //Arrange & Act
        var code = "C1";
        var title = "T1";
        var author = "A1";
        var edition = "E1";
        var publisher = "P1";
        var units = 5;
        var invoicePrice = 123.12;
        var finalPrice = 25.12;
        this.obj = new Book(code, title, author, edition, publisher, units, invoicePrice, finalPrice);
        //Assert
        assertEquals(obj.getPublisher(), publisher);
    }

    @Test
    void setPublisher() {
        //Arrange & Act
        var code = "C1";
        var title = "T1";
        var author = "A1";
        var edition = "E1";
        var publisher = "P1";
        var publisher2 = "P2";
        var units = 5;
        var invoicePrice = 123.12;
        var finalPrice = 25.12;
        this.obj = new Book(code, title, author, edition, publisher, units, invoicePrice, finalPrice);

        //Act
        this.obj.setPublisher(publisher2);
        //Assert
        assertEquals(obj.getPublisher(), publisher2);
    }

    @Test
    void getUnits() {
        //Arrange & Act
        var code = "C1";
        var title = "T1";
        var author = "A1";
        var edition = "E1";
        var publisher = "P1";
        var units = 5;
        var invoicePrice = 123.12;
        var finalPrice = 25.12;
        this.obj = new Book(code, title, author, edition, publisher, units, invoicePrice, finalPrice);
        //Assert
        assertEquals(obj.getUnits(), units);
    }

    @Test
    void setUnits() {
        //Arrange & Act
        var code = "C1";
        var title = "T1";
        var author = "A1";
        var edition = "E1";
        var publisher = "P1";
        var units = 5;
        var units2 = 50;
        var invoicePrice = 123.12;
        var finalPrice = 25.12;
        this.obj = new Book(code, title, author, edition, publisher, units, invoicePrice, finalPrice);

        //Act
        this.obj.setUnits(units2);
        //Assert
        assertEquals(obj.getUnits(), units2);
    }

    @Test
    void getInvoicePrice() {
        //Arrange & Act
        var code = "C1";
        var title = "T1";
        var author = "A1";
        var edition = "E1";
        var publisher = "P1";
        var units = 5;
        var invoicePrice = 123.12;
        var finalPrice = 25.12;
        this.obj = new Book(code, title, author, edition, publisher, units, invoicePrice, finalPrice);
        //Assert
        assertEquals(obj.getInvoicePrice(), invoicePrice);
    }

    @Test
    void setInvoicePrice() {
        //Arrange & Act
        var code = "C1";
        var title = "T1";
        var author = "A1";
        var edition = "E1";
        var publisher = "P1";
        var units = 5;
        var invoicePrice = 123.12;
        var invoicePrice2 = 150.11;
        var finalPrice = 25.12;
        this.obj = new Book(code, title, author, edition, publisher, units, invoicePrice, finalPrice);

        //Act
        this.obj.setInvoicePrice(invoicePrice2);
        //Assert
        assertEquals(obj.getInvoicePrice(), invoicePrice2);
    }

    @Test
    void getFinalPrice() {
        //Arrange & Act
        var code = "C1";
        var title = "T1";
        var author = "A1";
        var edition = "E1";
        var publisher = "P1";
        var units = 5;
        var invoicePrice = 123.12;
        var finalPrice = 25.12;
        this.obj = new Book(code, title, author, edition, publisher, units, invoicePrice, finalPrice);
        //Assert
        assertEquals(obj.getFinalPrice(), finalPrice);
    }

    @Test
    void setFinalPrice() {
        //Arrange & Act
        var code = "C1";
        var title = "T1";
        var author = "A1";
        var edition = "E1";
        var publisher = "P1";
        var units = 5;
        var invoicePrice = 123.12;
        var finalPrice = 25.12;
        var finalPrice2 = 15.10;
        this.obj = new Book(code, title, author, edition, publisher, units, invoicePrice, finalPrice);

        //Act
        this.obj.setFinalPrice(finalPrice2);
        //Assert
        assertEquals(obj.getFinalPrice(), finalPrice2);
    }

}