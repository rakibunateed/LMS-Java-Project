package library;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

class BorrowedBook implements Serializable {
    Book book;
    Date borrowDate;

    public BorrowedBook(Book book, Date borrowDate) {
        this.book = book;
        this.borrowDate = borrowDate;
    }

    public Book getBook() {
        return book;
    }

    public Date getBorrowDate() {
        return borrowDate;
    }

    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return book.toString() + "," + sdf.format(borrowDate);
    }

    public static BorrowedBook fromString(String borrowedBookString) {
        String[] parts = borrowedBookString.split(",");
        Book book = Book.fromString(String.join(",", parts[0], parts[1], parts[2]));
        Date borrowDate = new Date();
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            borrowDate = sdf.parse(parts[3] + " " + parts[4]);
        } catch (Exception e) {
//            e.printStackTrace();
        }
        return new BorrowedBook(book, borrowDate);
    }
}
