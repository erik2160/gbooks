package Storage;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StorageTest {

    private Storage obj;
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
        var editor = "E1";
        var publisher = "P1";
        var quantity = 5;
        var price = 123.12;
        this.obj = new Storage(code, title, editor, publisher, quantity, price);
        //Assert
        assertEquals(obj.getCode(), code);
    }

    @Test
    void setCode() {
        //Arrange & Act
        var code = "C1";
        var code2 = "C2";
        var title = "T1";
        var editor = "E1";
        var publisher = "P1";
        var quantity = 5;
        var price = 123.12;
        this.obj = new Storage(code, title, editor, publisher, quantity, price);

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
        var editor = "E1";
        var publisher = "P1";
        var quantity = 5;
        var price = 123.12;
        this.obj = new Storage(code, title, editor, publisher, quantity, price);
        //Assert
        assertEquals(obj.getTitle(), title);
    }

    @Test
    void setTitle() {
        //Arrange & Act
        var code = "C1";
        var title = "T1";
        var title2 = "T2";
        var editor = "E1";
        var publisher = "P1";
        var quantity = 5;
        var price = 123.12;
        this.obj = new Storage(code, title, editor, publisher, quantity, price);

        //Act
        this.obj.setTitle(title2);
        //Assert
        assertEquals(obj.getTitle(), title2);
    }

    @Test
    void getEditor() {
        //TODO
    }

    @Test
    void setEditor() {
        //TODO
    }

    @Test
    void getPublisher() {
        //TODO
    }

    @Test
    void setPublisher() {
        //TODO
    }

    @Test
    void getQuantity() {
        //TODO
    }

    @Test
    void setQuantity() {
        //TODO
    }

    @Test
    void getPrice() {
        //TODO
    }

    @Test
    void setPrice() {
        //TODO
    }
}