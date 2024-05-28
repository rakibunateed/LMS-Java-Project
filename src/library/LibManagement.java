package library;

import java.util.Date;
import java.util.Scanner;


public class LibManagement {
    private static final String USERNAME = "copyninja";
    private static final String PASSWORD = "teamlms";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Login form
        while (true) {
            System.out.print("Enter username: ");
            String inputUsername = scanner.nextLine();
            System.out.print("Enter password: ");
            String inputPassword = scanner.nextLine();

            if (!USERNAME.equals(inputUsername)) {
                System.out.println("Invalid username. Please try again.");
            } else if (!PASSWORD.equals(inputPassword)) {
                System.out.println("Invalid password. Please try again.");
            } else {
                break; // Exit the loop if the credentials are correct
            }
        }

        Library library = new Library();

        // Only add sample books if the library is empty
        if (library.findBook("Java Programming") == null && library.findBook("Python Basics") == null && library.findBook("Data Structures") == null) {
            library.addBook(new Book("Java Programming", "John Doe"));
            library.addBook(new Book("Python Basics", "Jane Smith"));
            library.addBook(new Book("Data Structures", "Alice Johnson"));
        }

        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1. Display Available Books");
            System.out.println("2. Display Borrowed Books");
            System.out.println("3. Add a Book");
            System.out.println("4. Borrow a Book");
            System.out.println("5. Return a Book");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    library.displayBooks();
                    break;
                case 2:
                    library.displayBorrowedBooks();
                    break;
                case 3:
                    System.out.print("Enter the title of the book: ");
                    String title = scanner.nextLine();
                    System.out.print("Enter the author of the book: ");
                    String author = scanner.nextLine();
                    library.addBook(new Book(title, author));
                    System.out.println("Book added successfully.");
                    break;
                case 4:
                    System.out.print("Enter the title of the book you want to borrow: ");
                    String borrowTitle = scanner.nextLine();
                    Book borrowBook = library.findBook(borrowTitle);
                    if (borrowBook != null) {
                        library.borrowBook(borrowBook, new Date());
                    } else {
                        System.out.println("Book not found or unavailable.");
                    }
                    break;
                case 5:
                    System.out.print("Enter the title of the book you want to return: ");
                    String returnTitle = scanner.nextLine();
                    Book returnBook = library.findBook(returnTitle);
                    if (returnBook != null && !returnBook.isAvailable()) {
                        library.returnBook(returnBook);
                        System.out.println("Book returned successfully.");
                    } else {
                        System.out.println("Book not found or not currently borrowed.");
                    }
                    break;
                case 6:
                    System.out.println("Exiting...");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a number from 1 to 6.");
            }
        }
    }
}
