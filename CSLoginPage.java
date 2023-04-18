import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CSLoginPage extends JFrame {
    private JPanel loginPage;
    private JPasswordField passwordField;
    private JTextField usernameField;
    private String retrievedPassword, retreviedUsername;
    private JLabel Password;
    private JButton loginButton;
    private JButton signUpButton;

    CSLoginPage(){
        this.setContentPane(loginPage);
        this.setTitle("Customer Login");
        this.setIconImage(new ImageIcon("31-hour.png").getImage());
        this.setSize(600,600);

        retreviedUsername = String.valueOf(passwordField.getPassword());
        retrievedPassword = usernameField.getText();




        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CustomerGUI cGUI = new CustomerGUI();
                /*
                Add in code for login verification
                 */

            }


        });
        signUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SignUpPage sGUI = new SignUpPage();
            }
        });
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

}
