package library;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import  java.awt.*;

public class LoginPage extends JFrame {
    public LoginPage() {
        setTitle("Library Management System - Login");
        setSize(1166, 750);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null); // Use absolute positioning

        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setBounds(450, 290, 100, 25);
        usernameLabel.setFont(new Font("Arial", Font.BOLD, 18));
        usernameLabel.setForeground(Color.WHITE);
        add(usernameLabel);

        JTextField usernameField = new JTextField();
        usernameField.setBounds(560, 290, 250, 25);
        usernameField.setFont(new Font("Arial", Font.PLAIN, 16));
        add(usernameField);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(450, 340, 100, 25);
        passwordLabel.setFont(new Font("Arial", Font.BOLD, 18));
        passwordLabel.setForeground(Color.WHITE);
        add(passwordLabel);

        JPasswordField passwordField = new JPasswordField();
        passwordField.setBounds(560, 340, 250, 25);
        passwordField.setFont(new Font("Arial", Font.PLAIN, 16));
        add(passwordField);

        JButton loginButton = new JButton("Login");
        loginButton.setBounds(560, 390, 100, 30);
        loginButton.setFont(new Font("Arial", Font.BOLD, 16));
        loginButton.setForeground(Color.WHITE);
        loginButton.setBackground(new Color(99, 3, 7, 255));

        add(loginButton);


        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());

                // Simple hardcoded login validation
                if (username.equals("copyninja") && password.equals("teamlms")) {
                    new MainPage();
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid username or password.");
                }
            }
        });
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("Image/login.jpg"));
        Image i = i1.getImage().getScaledInstance(1280, 720, Image.SCALE_DEFAULT);
        ImageIcon i2 = new ImageIcon(i);
        JLabel image = new JLabel(i2);
        image.setBounds(0, 0, 1280, 720);
        add(image);
        setResizable(false);
        setVisible(true);
    }

    public static void main(String[] args) {
        new LoginPage();
    }
}

