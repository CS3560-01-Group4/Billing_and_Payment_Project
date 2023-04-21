import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SalesLogin extends JFrame {
    private JPasswordField passwordField = new JPasswordField(20);
    private JTextField usernameField = new JTextField(20);
    private JLabel usernameLabel = new JLabel("Enter Username");
    private JLabel passwordLabel = new JLabel("Enter Password");
    private String retrievedPassword, retreviedUsername;
    private JButton loginButton = new JButton("Login");//how can a salesperson sign up? 
    private JPanel salesPersonPanel = new JPanel();


    SalesLogin(){
        this.setContentPane(salesPersonPanel);
        this.setTitle("Customer Login");
        this.setIconImage(new ImageIcon("31-hour.png").getImage());
        this.setSize(600,600);
        this.add(loginButton);
        
        usernameField.setBounds(100,20,165,25);
        this.add(usernameLabel);
        this.add(usernameField);
        passwordField.setBounds(100,20,165,25);
        this.add(passwordLabel);
        this.add(passwordField);

        //retreviedUsername = usernameField.getText();
        //retrievedPassword = String.valueOf(passwordField.getPassword());




        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                SalesmanGUI salesmanGUI = new SalesmanGUI();
                /*
                Add in code for login verification
                 */

            }
        });
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
}
