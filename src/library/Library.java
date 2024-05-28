package library;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

class Library {
    private static final String BOOKS_FILE = "books.txt";
    private static final String BORROWED_BOOKS_FILE = "borrowed_books.txt";
    ArrayList<Book> books;
    ArrayList<BorrowedBook> borrowedBooks;

    public Library() {
        this.books = loadBooksFromFile();
        this.borrowedBooks = loadBorrowedBooksFromFile();
    }

    public void addBook(Book book) {
        books.add(book);
        saveBooksToFile(books);
    }

    public void displayBooks() {
        System.out.println("Library Books:");
        for (Book book : books) {
            System.out.println(book.getTitle() + " by " + book.getAuthor() + " - Available: " + (book.isAvailable() ? "Yes" : "No"));
        }
    }

    public void displayBorrowedBooks() {
        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm a");
        System.out.println("Borrowed Books:");
        for (BorrowedBook borrowedBook : borrowedBooks) {
            System.out.println(borrowedBook.getBook().getTitle() + " by " + borrowedBook.getBook().getAuthor() +
                    " - Borrowed on: " + sdf.format(borrowedBook.getBorrowDate()));
        }
    }

    public Book findBook(String title) {
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                return book;
            }
        }
        return null;
    }

    public void borrowBook(Book book, Date borrowDate) {
        if (book.isAvailable()) {
            book.setAvailable(false);
            borrowedBooks.add(new BorrowedBook(book, borrowDate));
            saveBooksToFile(books);
            saveBorrowedBooksToFile(borrowedBooks);
            System.out.println("You have borrowed: " + book.getTitle());
        } else {
            System.out.println("Book is not available for borrowing.");
        }
    }

    public void returnBook(Book book) {
        book.setAvailable(true);
        borrowedBooks.removeIf(borrowedBook -> borrowedBook.getBook().equals(book));
        saveBooksToFile(books);
        saveBorrowedBooksToFile(borrowedBooks);
        System.out.println("Thank you for returning: " + book.getTitle());
    }

    private ArrayList<Book> loadBooksFromFile() {
        ArrayList<Book> books = new ArrayList<>();
        File file = new File(Library.BOOKS_FILE);
        if (file.exists()) {
            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = br.readLine()) != null) {
                    books.add(Book.fromString(line));
                }
            } catch (IOException e) {
//                e.printStackTrace();
            }
        }
        return books;
    }

    private ArrayList<BorrowedBook> loadBorrowedBooksFromFile() {
        ArrayList<BorrowedBook> borrowedBooks = new ArrayList<>();
        File file = new File(Library.BORROWED_BOOKS_FILE);
        if (file.exists()) {
            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = br.readLine()) != null) {
                    borrowedBooks.add(BorrowedBook.fromString(line));
                }
            } catch (IOException e) {
//                e.printStackTrace();
            }
        }
        return borrowedBooks;
    }

    private void saveBooksToFile(ArrayList<Book> books) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(Library.BOOKS_FILE))) {
            for (Book book : books) {
                bw.write(book.toString());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving books to file.");
        }
    }

    private void saveBorrowedBooksToFile(ArrayList<BorrowedBook> borrowedBooks) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(Library.BORROWED_BOOKS_FILE))) {
            for (BorrowedBook borrowedBook : borrowedBooks) {
                bw.write(borrowedBook.toString());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving borrowed books to file.");
        }
    }
}
