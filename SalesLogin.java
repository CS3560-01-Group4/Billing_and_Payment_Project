import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SalesLogin extends JFrame {
    private JPasswordField passwordField;
    private JTextField usernameField;
    private JLabel usernameLabel;
    private JLabel passwordLabel;
    private String retrievedPassword, retreviedUsername;
    private JButton loginButton;
    private JPanel salesPersonPanel;


    SalesLogin(){
        this.setContentPane(salesPersonPanel);
        this.setTitle("Customer Login");
        this.setIconImage(new ImageIcon("31-hour.png").getImage());
        this.setSize(600,600);

        retreviedUsername = String.valueOf(passwordField.getPassword());
        retrievedPassword = usernameField.getText();




        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                /*
                Add in code for login verification
                 */

            }
        });
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
}
