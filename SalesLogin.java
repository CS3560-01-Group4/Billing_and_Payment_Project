import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class SalesLogin extends JFrame {
    private JPasswordField passwordField = new JPasswordField(20);
    private JTextField usernameField = new JTextField(20);
    private JLabel usernameLabel = new JLabel("Enter Username");
    private JLabel passwordLabel = new JLabel("Enter Password");
    private String retrievedPassword, retreviedUsername;
    private JButton loginButton = new JButton("Login");
    private JPanel salesPersonPanel = new JPanel();


    SalesLogin(){
        this.setContentPane(salesPersonPanel);
        this.setTitle("Salesperson Login");
        this.setIconImage(new ImageIcon("31-hour.png").getImage());
        this.setSize(600,600);
        this.add(loginButton);
        
        usernameField.setBounds(100,20,165,25);
        this.add(usernameLabel);
        this.add(usernameField);
        passwordField.setBounds(100,20,165,25);
        this.add(passwordLabel);
        this.add(passwordField);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                retreviedUsername = usernameField.getText();
                retrievedPassword = String.valueOf(passwordField.getPassword());

            	boolean verified = false;
            	try {
            		DatabaseManager db = new DatabaseManager();
            		verified = db.searchSalespersonCredentials(retreviedUsername, retrievedPassword);
            		db.close();
            	}catch(SQLException ex) {

            		System.out.println("error with database");;
            	}
            	if(verified) {
            		SalesmanGUI salesmanGUI = new SalesmanGUI();
            		dispose();
            	}

            }
        });
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
}
