import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class viewCustomerAccount extends JFrame {
    private JPanel viewCustAccount;
    private JTextField addressText;
    private JTextField cityTextField;
    private JTextField postalField;
    private JTextField stateField;
    private JButton exitButton;
    private JTextField nameText;
    private JTextField emailField;
    private JPasswordField passwordField1;
    private JCheckBox showPasswordCheckBox;

    boolean completed = false;

    String url = "jdbc:mysql://" + "containers-us-west-34.railway.app" + ":" + 5939 + "/" + "gymmembership";

    private Connection connection = DriverManager.getConnection(url, "root", "91laqZk1CB5VM13WltEE");;

    viewCustomerAccount(Customer customer) throws SQLException {
        this.setContentPane(viewCustAccount);
        this.setTitle("Account Information");
        this.setIconImage(new ImageIcon("31-hour.png").getImage());
        this.setSize(600, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);




        try {
            DatabaseManager dbConnection = new DatabaseManager();
            Customer viewAccount = dbConnection.getCustomerCredentials(customer.getEmail(), customer.getPassword());
            addressText.setText(viewAccount.getAddress().getStreetName());
            cityTextField.setText(viewAccount.getAddress().getCity());
            postalField.setText(viewAccount.getAddress().getZipCode());
            stateField.setText(viewAccount.getAddress().getState());
            emailField.setText(viewAccount.getEmail());
            nameText.setText(viewAccount.getName());
            passwordField1.setText(viewAccount.getPassword());

            addressText.setEditable(false);
            cityTextField.setEditable(false);
            postalField.setEditable(false);
            stateField.setEditable(false);
            emailField.setEditable(false);
            nameText.setEditable(false);
            passwordField1.setEditable(false);

            dbConnection.close();



        }
        catch (SQLException throwables){
            throwables.printStackTrace();
            JOptionPane.showMessageDialog(null, "There's not recovering from this error");


        }

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        showPasswordCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                passwordField1.setEchoChar('\u0000');
            }
        });






    }

}
