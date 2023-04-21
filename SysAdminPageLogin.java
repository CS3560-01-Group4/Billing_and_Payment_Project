import javax.swing.*;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SysAdminPageLogin{
    
    // window components
    private JTextField usernameField = new JTextField();
    private JPasswordField passwordField = new JPasswordField();
    private JButton loginButton = new JButton("Login");
    private JButton signUpButton = new JButton("Sign Up");
    private JLabel usernameLabel = new JLabel("Enter username");
    private JLabel passwordLabel = new JLabel("Enter password");
    
    SysAdminPageLogin() {
        // create and setup window
        JFrame frame = new JFrame("Admin Login");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600,600);
        //frame.setResizable(false);
        frame.setIconImage(new ImageIcon("31-hour.png").getImage());

        // set layout manager to boxlayout
        Container panel = frame.getContentPane();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setMaximumSize(new Dimension(100, 100));

        // set component allignment
        usernameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        passwordLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        loginButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        signUpButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        usernameField.setAlignmentX(Component.CENTER_ALIGNMENT);
        passwordField.setAlignmentX(Component.CENTER_ALIGNMENT);

        // set max size of textfield
        usernameField.setMaximumSize(new Dimension(200, 25));
        passwordField.setMaximumSize(new Dimension(200, 25));

        // add window content
        panel.add(usernameLabel);
        panel.add(usernameField);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(loginButton);
        panel.add(signUpButton);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // user login here
            }
        });

        signUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new SignUpPage();
            }
        });
        
        // display windoow
        frame.setVisible(true);
    }
}