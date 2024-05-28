package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login extends JFrame implements ActionListener {

    private static final String USERNAME = "copyninja";
    private static final String PASSWORD = "teamlms";

    JTextField userIdText, passwordText;
    JButton loginBtn, cancel;
    JLabel name, pass, wrongPassText, wrongUserIDText;

    Login() {
        super("Library Management System by Ateed!");
        setLayout(null);

        name = new JLabel("User ID: ");
        name.setBounds(500, 180, 265, 20);
        name.setForeground(Color.WHITE);
        name.setFont(new Font("Arial", Font.BOLD, 14));
        add(name);

        pass = new JLabel("Password: ");
        pass.setBounds(500, 230, 265, 20);
        pass.setForeground(Color.WHITE);
        pass.setFont(new Font("Arial", Font.BOLD, 14));
        add(pass);

        wrongPassText = new JLabel("Wrong password!");
        wrongPassText.setBounds(500, 350, 265, 20);
        add(wrongPassText);
        wrongPassText.setVisible(false);
        wrongUserIDText = new JLabel("Wrong User Id!");
        wrongUserIDText.setBounds(500, 330, 265, 20);
        add(wrongUserIDText);
        wrongUserIDText.setVisible(false);


        userIdText = new JTextField();
        userIdText.setBounds(500, 200, 265, 25);
        userIdText.setFont(new Font("Arial", Font.PLAIN, 16));
        userIdText.setBorder(null);
        add(userIdText);

        passwordText = new JTextField();
        passwordText.setBounds(500, 250, 265, 25);
        passwordText.setFont(new Font("Arial", Font.PLAIN, 16));
        passwordText.setBorder(null);
        add(passwordText);

        loginBtn = new JButton("Login");
        loginBtn.setBounds(500, 300, 120, 25);
        loginBtn.setFont(new Font("Arial", Font.BOLD, 16));
        loginBtn.setForeground(Color.WHITE);
        loginBtn.setBackground(Color.blue);
        loginBtn.addActionListener(this);
        add(loginBtn);

        cancel = new JButton("Cancel");
        cancel.setBounds(645, 300, 120, 25);
        cancel.setFont(new Font("Arial", Font.BOLD, 16));
        cancel.setForeground(Color.WHITE);
        cancel.setBackground(Color.blue);
        cancel.addActionListener(this);
        add(cancel);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("Image/Login.jpg"));
        Image i = i1.getImage().getScaledInstance(1280, 720, Image.SCALE_DEFAULT);
        ImageIcon i2 = new ImageIcon(i);
        JLabel image = new JLabel(i2);
        image.setBounds(0, 0, 1280, 720);
        add(image);

        setSize(1280, 720);
        setLocation(40, 10);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);
        setResizable(false);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        wrongPassText.setVisible(false);
        wrongUserIDText.setVisible(false);
        if (e.getSource() == loginBtn) {
            if (userIdText.getText().equals(USERNAME)) {
                if (passwordText.getText().equals(PASSWORD)) {
                    // New class load
                    setVisible(false);
                } else {
                    wrongPassText.setVisible(true);
                }
            } else {
                wrongUserIDText.setVisible(true);
            }
        } else if (e.getSource() == cancel) {
            setVisible(false);
            System.exit(50);
        }
    }

    public static void main(String[] args) {
        new Login();
    }
}
