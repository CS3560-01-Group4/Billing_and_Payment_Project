import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class SalesLogin extends JFrame {
    private JPasswordField passwordField;
    private JTextField usernameField;
    private JLabel usernameLabel;
    private JLabel passwordLabel;
    private String retrievedPassword, retreviedUsername;
    private JButton loginButton;
    private JPanel salesPersonPanel;
    private JButton returnButton;

    SalesLogin(){
        this.setContentPane(salesPersonPanel);
        this.setTitle("Salesperson Login");
        this.setIconImage(new ImageIcon("31-hour.png").getImage());
        this.setSize(600,600);
        usernameField.setBounds(100,20,165,25);
        passwordField.setBounds(100,20,165,25);


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
                else
                    JOptionPane.showMessageDialog(null,"Invalid login credentials");

            }
        });

        returnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new UI();
                dispose();
            }
        });

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }
}
