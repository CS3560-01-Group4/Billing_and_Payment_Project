import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CSLoginPage extends JFrame {
    private JPanel loginPage;
    private JPasswordField passwordField;
    private JTextField usernameField;
    private JLabel Password;
    private JLabel username = new JLabel("Enter Username");
    private JButton loginButton;
    private JButton signUpButton;
    private JLabel passwordLabel;
    private String retrievedPassword, retreviedUsername;

    CSLoginPage(){
        this.setContentPane(loginPage);
        this.setTitle("Customer Login");
        this.setIconImage(new ImageIcon("31-hour.png").getImage());
        this.setSize(600,600);
        JFrame frame = this;
        
        usernameField.setBounds(100,20,165,25);
        passwordField.setBounds(100,20,165,25);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	boolean verified = false;
            	//save the typed-in username and password
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
                frame.dispose();
                new SignUpPage();
            }
        });
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
}
