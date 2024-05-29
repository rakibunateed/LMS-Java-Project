package library;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Library {
    private List<Book> books;
    private static final String BOOKS_FILE = "data/books.txt";
    private final EventLogger eventLogger;

    public Library() {
        books = new ArrayList<>();
        eventLogger = new EventLogger();
        loadBooks();
    }

    public void addBook(Book book) {
        books.add(book);
        saveBooks();
        eventLogger.logEvent("Added book: " + book.getTitle());
    }

    public void deleteBook(Book book) {
        books.remove(book);
        saveBooks();
        eventLogger.logEvent("Deleted book: " + book.getTitle());
    }

    public void borrowBook(Book book) {
        book.setBorrowed(true);
        saveBooks();
        eventLogger.logEvent("Borrowed book: " + book.getTitle());
    }

    public void returnBook(Book book) {
        book.setBorrowed(false);
        saveBooks();
        eventLogger.logEvent("Returned book: " + book.getTitle());
    }

    public List<Book> getBooks() {
        return books;
    }

    private void loadBooks() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(BOOKS_FILE))) {
            books = (List<Book>) ois.readObject();
        } catch (FileNotFoundException e) {
            // File not found, creating a new one on save
        } catch (IOException | ClassNotFoundException e) {
            // e.printStackTrace();
        }
    }

    private void saveBooks() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(BOOKS_FILE))) {
            oos.writeObject(books);
        } catch (IOException e) {
            // e.printStackTrace();
        }
    }

    public List<String> getEvents() {
        return eventLogger.getEvents();
    }
}
