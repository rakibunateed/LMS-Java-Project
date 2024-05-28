package library;

import java.io.*;

class Book implements Serializable {
    String title;
    String author;
    boolean available;

    public Book(String title, String author) {
        this.title = title;
        this.author = author;
        this.available = true;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    @Override
    public String toString() {
        return title + "," + author + "," + available;
    }

    public static Book fromString(String bookString) {
        String[] parts = bookString.split(",");
        Book book = new Book(parts[0], parts[1]);
        book.setAvailable(Boolean.parseBoolean(parts[2]));
        return book;
    }
}