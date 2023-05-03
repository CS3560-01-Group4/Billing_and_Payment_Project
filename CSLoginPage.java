import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CSLoginPage extends JFrame {
    private JPanel loginPage;
    private JPasswordField passwordField;
    private JTextField emailField;
    private JLabel emailLabel;
    private JLabel username = new JLabel("Enter email");
    private JButton loginButton;
    private JButton signUpButton;
    private JLabel passwordLabel;
    private JButton returnButton;
    private String retrievedPassword, retrievedEmail;

    CSLoginPage(){
        this.setContentPane(loginPage);
        this.setTitle("Customer Login");
        this.setIconImage(new ImageIcon("31-hour.png").getImage());
        this.setSize(600,600);
        JFrame frame = this;
        
        emailField.setBounds(100,20,165,25);
        passwordField.setBounds(100,20,165,25);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Customer validCustomer = null;
                //save the typed-in username and password
                retrievedEmail = emailField.getText();
                retrievedPassword = String.valueOf(passwordField.getPassword());
                //verify login with database
                try {
                    DatabaseManager db = new DatabaseManager();
                    validCustomer = db.getCustomerCredentials(retrievedEmail, retrievedPassword);
                    db.close();
                }catch (Exception ex) {
                    System.out.println(ex);
                }
                //launch customer GUI
                if(validCustomer != null) {
                    dispose();
                    CustomerGUI cGUI = new CustomerGUI(validCustomer);
                }else {
                    JOptionPane.showMessageDialog(null, "Incorrect Login Credentials \nPlease Try Again");
                }

            }

        });
        signUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new SignUpPage();
            }
        });

        returnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new UI();
            }
        });

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }
}
