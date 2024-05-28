package library;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;

public class MainPage extends JFrame {
    private final Library library;
    private final JTextArea bookListArea;
    private final JTextArea eventLogArea;

    public MainPage() {
        library = new Library();
        setTitle("Library Management System");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        // Book list area
        JLabel bookListLabel = new JLabel("Books:");
        bookListLabel.setBounds(50, 20, 100, 25);
        bookListLabel.setFont(new Font("Arial", Font.BOLD, 16));
        bookListLabel.setForeground(Color.WHITE);
        add(bookListLabel);

        bookListArea = new JTextArea();
        bookListArea.setBounds(50, 50, 300, 400);
        bookListArea.setEditable(false);
        JScrollPane bookListScrollPane = new JScrollPane(bookListArea);
        bookListScrollPane.setBounds(50, 50, 300, 400);
        add(bookListScrollPane);

        // Event log area
        JLabel eventLogLabel = new JLabel("Event Log:");
        eventLogLabel.setBounds(400, 20, 100, 25);
        eventLogLabel.setFont(new Font("Arial", Font.BOLD, 16));
        eventLogLabel.setForeground(Color.WHITE);
        add(eventLogLabel);

        eventLogArea = new JTextArea();
        eventLogArea.setBounds(400, 50, 300, 400);
        eventLogArea.setEditable(false);
        JScrollPane eventLogScrollPane = new JScrollPane(eventLogArea);
        eventLogScrollPane.setBounds(400, 50, 300, 400);
        add(eventLogScrollPane);

        // Add book button
        JButton addBookButton = new JButton("Add Book");
        addBookButton.setBounds(50, 470, 120, 25);
        addBookButton.setFont(new Font("Arial", Font.PLAIN, 14));
        addBookButton.setForeground(Color.WHITE);
        addBookButton.setBackground(new Color(99, 3, 7, 255));
        add(addBookButton);
        addBookButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String title = JOptionPane.showInputDialog("Enter book title:");
                String author = JOptionPane.showInputDialog("Enter book author:");
                if (title != null && author != null) {
                    library.addBook(new Book(title, author));
                    updateBookList();
                    updateEventLog();
                }
            }
        });

        // Borrow book button
        JButton borrowBookButton = new JButton("Borrow Book");
        borrowBookButton.setBounds(180, 470, 120, 25);
        borrowBookButton.setFont(new Font("Arial", Font.PLAIN, 14));
        borrowBookButton.setForeground(Color.WHITE);
        borrowBookButton.setBackground(new Color(99, 3, 7, 255));
        add(borrowBookButton);
        borrowBookButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String title = JOptionPane.showInputDialog("Enter book title to borrow:");
                for (Book book : library.getBooks()) {
                    if (book.getTitle().equalsIgnoreCase(title) && !book.isBorrowed()) {
                        library.borrowBook(book);
                        updateBookList();
                        updateEventLog();
                        return;
                    }
                }
                JOptionPane.showMessageDialog(null, "Book not found or already borrowed.");
            }
        });

        // Return book button
        JButton returnBookButton = new JButton("Return Book");
        returnBookButton.setBounds(310, 470, 120, 25);
        returnBookButton.setFont(new Font("Arial", Font.PLAIN, 14));
        returnBookButton.setForeground(Color.WHITE);
        returnBookButton.setBackground(new Color(99, 3, 7, 255));
        add(returnBookButton);
        returnBookButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String title = JOptionPane.showInputDialog("Enter book title to return:");
                for (Book book : library.getBooks()) {
                    if (book.getTitle().equalsIgnoreCase(title) && book.isBorrowed()) {
                        library.returnBook(book);
                        updateBookList();
                        updateEventLog();
                        return;
                    }
                }
                JOptionPane.showMessageDialog(null, "Book not found or not borrowed.");
            }
        });

        // Delete book button
        JButton deleteBookButton = new JButton("Delete Book");
        deleteBookButton.setBounds(440, 470, 120, 25);
        deleteBookButton.setFont(new Font("Arial", Font.PLAIN, 14));
        deleteBookButton.setForeground(Color.WHITE);
        deleteBookButton.setBackground(new Color(99, 3, 7, 255));
        add(deleteBookButton);
        deleteBookButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String title = JOptionPane.showInputDialog("Enter book title to delete:");
                for (Book book : library.getBooks()) {
                    if (book.getTitle().equalsIgnoreCase(title)) {
                        library.deleteBook(book);
                        updateBookList();
                        updateEventLog();
                        return;
                    }
                }
                JOptionPane.showMessageDialog(null, "Book not found.");

            }
        });

        // Update book button
        JButton updateBookButton = new JButton("Update Book");
        updateBookButton.setBounds(570, 470, 120, 25);
        updateBookButton.setFont(new Font("Arial", Font.PLAIN, 14));
        updateBookButton.setForeground(Color.WHITE);
        updateBookButton.setBackground(new Color(99, 3, 7, 255));
        add(updateBookButton);
        updateBookButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String oldTitle = JOptionPane.showInputDialog("Enter current book title:");
                for (Book book : library.getBooks()) {
                    if (book.getTitle().equalsIgnoreCase(oldTitle)) {
                        String newTitle = JOptionPane.showInputDialog("Enter new book title:");
                        String newAuthor = JOptionPane.showInputDialog("Enter new book author:");
                        if (newTitle != null && newAuthor != null) {
                            library.deleteBook(book);
                            library.addBook(new Book(newTitle, newAuthor));
                            updateBookList();
                            updateEventLog();
                            return;
                        }
                    }
                }
                JOptionPane.showMessageDialog(null, "Book not found.");
            }
        });
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("Image/Home.jpg"));
        Image i = i1.getImage().getScaledInstance(1280, 720, Image.SCALE_DEFAULT);
        ImageIcon i2 = new ImageIcon(i);
        JLabel image = new JLabel(i2);
        image.setBounds(0, 0, 1280, 720);
        add(image);


        updateBookList();
        updateEventLog();
        setResizable(false);
        setVisible(true);
    }


    private void updateBookList() {
        bookListArea.setText("");
        for (Book book : library.getBooks()) {
            bookListArea.append(book + "\n");
        }
    }

    private void updateEventLog() {
        eventLogArea.setText("");
        for (String event : library.getEvents()) {
            eventLogArea.append(event + "\n");
        }
    }

    public static void main(String[] args) {
        new MainPage();
    }
}
