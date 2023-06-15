package br.com.ticotech.gbooks.java.repository;

import br.com.ticotech.gbooks.java.entities.Book;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class StockRepository {
    private final List<Book> bookList = new ArrayList<>();

    public StockRepository(){
        addBook(new Book("1231231231", "The Great Gatsby", "Scott Fitzgerald","7", "Scribner", 10, 25.19, 26.20));
        addBook(new Book("1231231232", "To Kill a Mockingbird", " Harper Lee","3", "J. B. Lippincott & Co.", 7, 32.15, 35.10));
        addBook(new Book("1231231233", "1984", "George Orwell","1", "Secker & Warburg", 22, 23.33, 25.14));
        addBook(new Book("8642097535", "Pride and Prejudice", "Jane Austen","2", "T. Egerton", 25, 12.39, 15.34));
        addBook(new Book("6927403820", "The Catcher in the Rye", "J.D. Salinger","2", "Little, Brown and Company", 30, 45.39, 50.00));
        addBook(new Book("2081649277", "The Lord of the Rings", "J.R.R. Tolkien","4", "George Allen & Unwin", 12, 45.39, 48.13));
        addBook(new Book("7890123460", "Harry Potter and the Sorcerer's Stone", "J.K. Rowling","1", "Bloomsbury", 14, 14.10, 23.22));
        addBook(new Book("4567890127", "The Hunger Games", "Suzanne Collins","3", "Scholastic Corporation", 20, 30.15, 32.15));
        addBook(new Book("0123456783", "The Da Vinci Code", "Dan Brown","2", "Doubleday", 22, 56.12, 60.00));
        addBook(new Book("9388476102", "The Alchemist", "Paulo Coelho","5", "HarperOne", 11, 13.00, 14.00));
        addBook(new Book("5432109880", "Brave New World", "Aldous Huxley","1", "Chatto & Windus", 22, 20.17, 22.16));
        addBook(new Book("9998276301", "The Chronicles of Narnia", "C.S. Lewis","1", "Geoffrey Bles", 16, 32.29, 40.00));
        addBook(new Book("2718281822", "Gone with the Wind", "Margaret Mitchell","2", "Macmillan Publishers", 22, 23.44, 25.00));
        addBook(new Book("9753108646", "The Fault in Our Stars", "John Green","3", "Dutton Books", 30, 17.00, 20.00));
        addBook(new Book("3692581474", "The Kite Runner", "Khaled Hosseini","1", "Riverhead Books", 15, 9.35, 12.00));
        addBook(new Book("9876543214", "Fahrenheit 451", "Ray Bradbury","3", "Ballantine Books", 14, 29.00, 35.39));
        addBook(new Book("1234567894", "Jane Eyre", "Charlotte Brontë","1", "Smith, Elder & Co.", 9, 45.13, 50.59));
        addBook(new Book("2468135794", "The Hobbit", "J.R.R. Tolkien","2", "George Allen & Unwin", 3, 14.00, 17.00));
        addBook(new Book("7896541233", "Animal Farm", "George Orwell","3", "Secker & Warburg", 20, 27.15, 30.12));
        addBook(new Book("0987654324", "Catch-22", "Joseph Heller","3", "Simon & Schuster", 23, 45.32, 48.23));
        addBook(new Book("6542089130", "Moby-Dick", "Herman Melville","1", "Harper & Brothers", 10, 30.11, 34.00));
        addBook(new Book("5038169277", "The Odyssey", "Homer","3", "Various publishers", 30, 10.00, 15.39));
        addBook(new Book("8642097534", "Lord of the Flies", "William Golding","3", "Faber and Faber", 24, 37.00, 41.20));
        addBook(new Book("6927403819", "The Shining", "Stephen King","1", "Doubleday", 28, 23.18, 25.40));
        addBook(new Book("2081649276", "The Picture of Dorian Gray", "Oscar Wilde","3", "Ward, Lock & Co.", 24, 24.93, 30.00));
        addBook(new Book("7890123459", "The Book Thief", "Markus Zusak","1", "Picador", 2, 48.39, 63.00));
        addBook(new Book("4567890126", "A Game of Thrones", "R.R. Martin","3", "Bantam Spectra", 48, 33.33, 35.17));
        addBook(new Book("0123456782", "The Girl on the Train", "Paula Hawkins","1", "Transworld Publishers", 4, 92.00, 112.30));
        addBook(new Book("5432109879", "The Road", "Cormac McCarthy","2", "Alfred A. Knopf", 1, 20.17, 22.16));
        addBook(new Book("2718281821", "The Lovely Bones", "Alice Sebold","1", "Little, Brown and Company", 44, 32.00, 47.00));
        addBook(new Book("9753108645", "Little Women", "Louisa May Alcott","1", "Roberts Brothers", 20, 32.00, 35.00));
        addBook(new Book("3692581473", "Frankenstein", "Mary Shelley","5", "Lackington, Hughes, Harding, Mavor & Jones", 21, 30.00, 35.00));
        addBook(new Book("9876543213", "The Secret Garden", "Frances Hodgson Burnett","2", "Frederick Warne & Co.", 14, 45.32, 50.12));
        addBook(new Book("1234567893", "The Color Purple", "Alice Walker ","1", "Harcourt Brace Jovanovich", 8, 12.12, 15.12));
        addBook(new Book("2468135793", "One Hundred Years of Solitude", "Gabriel García Márquez","1", "Harper & Row", 22, 23.12, 25.00));
        addBook(new Book("1357913571", "The Help", "Kathryn Stockett","3", "Amy Einhorn Books", 23, 45.00, 51.00));
        addBook(new Book("7896541232", "The Handmaid's Tale", "Margaret Atwood ","3", "McClelland and Stewart", 15, 33.23, 36.93));
        addBook(new Book("0987654323", "Dracula", "Bram Stoker","4", "Archibald Constable and Company", 20, 32.02, 34.03));
        addBook(new Book("6542089129", "The Adventures of Tom Sawyer", "Mark Twain","1", "American Publishing Company", 23, 30.00, 35.00));
        addBook(new Book("5038169276", "The Curious Incident of the Dog in the Night-Time", "Mark Haddon","3", "Jonathan Cape", 15, 23.00, 27.00));
        addBook(new Book("8642097533", "The Count of Monte Cristo", "Alexandre Dumas","1", "Pétion", 4, 44.00, 51.23));
        addBook(new Book("2081649275", "Anna Karenina", "Leo Tolstoy","1", "The Russian Messenger", 42, 12.09, 16.02));
    }

    public List<Book> getStock(){
        return bookList;
    }

    public Book getBook(String barcode){
        for(Book book : bookList){
            if (Objects.equals(book.getCode(), barcode)){
                return book;
            }
        }
        return null;
    }

    public void addBook(Book book){
        bookList.add(book);
    }

    public void alterUnits(String type, int units, String barcode){
        Book book = getBook(barcode);
        switch (type) {
            case "add" -> book.setUnits(book.getUnits() + units);
            case "remove" -> book.setUnits(book.getUnits() - units);
        }
    }

    public void deleteBook(String barcode){
        bookList.remove(getBook(barcode));
    }
}
