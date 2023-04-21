import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CSLoginPage extends JFrame {
    private JPanel loginPage = new JPanel();
    private JPasswordField passwordField = new JPasswordField(20);
    private JTextField usernameField = new JTextField(20);
    private JLabel Password = new JLabel("Enter Password");
    private JLabel username = new JLabel("Enter Username");
    private JButton loginButton = new JButton("Login");
    private JButton signUpButton = new JButton("Sign Up");
    private String retrievedPassword, retreviedUsername;

    CSLoginPage(){
        this.setContentPane(loginPage);
        this.setTitle("Customer Login");
        this.setIconImage(new ImageIcon("31-hour.png").getImage());
        this.setSize(600,600);
        this.add(signUpButton);
        this.add(loginButton);
        
        usernameField.setBounds(100,20,165,25);
        this.add(username);
        this.add(usernameField);
        passwordField.setBounds(100,20,165,25);
        this.add(Password);
        this.add(passwordField);
        






        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	boolean verified = false;
            	//save the typed-in user name and password
            	retreviedUsername = usernameField.getText();
                retrievedPassword = String.valueOf(passwordField.getPassword());
                //verify login with database
                try {
                	DatabaseManager db = new DatabaseManager("localhost", 3306, "gymmembership","root", "sqlingurmom");
                	verified = db.searchCustomerCredentials(retreviedUsername, retrievedPassword);
                	db.close();
                }catch (Exception ex) {
                	System.out.println(ex);
                }
            	//launch customer GUI
                if(verified) {
                	dispose();
                	CustomerGUI cGUI = new CustomerGUI();
                }else {
                	JOptionPane.showMessageDialog(null, "Incorrect Login Credentials \nPlease Try Again");
                }
            	
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
