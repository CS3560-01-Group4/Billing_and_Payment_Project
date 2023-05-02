import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class salesmanSearchCustomer extends JFrame {
    String username, password;
    private JTextField usernameText;
    private JTextField passwordText;
    private JButton search;
    private JPanel salesmeanSearchPanel;
    private JButton backButton;

    boolean completed = false;

    String url = "jdbc:mysql://" + "containers-us-west-34.railway.app" + ":" + 5939 + "/" + "gymmembership";

    private Connection connection = DriverManager.getConnection(url, "root", "91laqZk1CB5VM13WltEE");
    ;
    /*
    Function overloading that is context based, use an INT if you plan to view/edit a customer's account;
    A 1 allows you to view an account, a 2 allows you to edit an account's address
     */

    salesmanSearchCustomer(int Num) throws SQLException {
        this.setContentPane(salesmeanSearchPanel);
        this.setTitle("Confirmation");
        this.setIconImage(new ImageIcon("31-hour.png").getImage());
        this.setSize(600, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);


        search.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    username = usernameText.getText();
                    password = passwordText.getText();
                    if (username.equals("")) {
                        usernameText.setBackground(new Color(255, 102, 102));
                    } else if (password.equals("")) {
                        passwordText.setBackground(new Color(255, 102, 102));

                    }

                    if (username.equals("") || password.equals("")) {
                        JOptionPane.showMessageDialog(null, "Invalid input");

                    }

                    DatabaseManager dbConnection = new DatabaseManager();
                    Customer searchCustomer = dbConnection.getCustomerCredentials(username, password);
                    if (searchCustomer == null) {
                        JOptionPane.showMessageDialog(null, "Customer does not exist!");

                    } else {
                        switch (Num) {
                            case 1:
                                dispose();
                                viewCustomerAccount view = new viewCustomerAccount(searchCustomer);
                                break;
                            case 2:
                                dispose();
                                EditAddressPage edit = new EditAddressPage(searchCustomer);
                                break;
                            case 3:
                                dispose();
                                CustomerGUI.EditEmailAddress(searchCustomer);
                                break;
                        }
                    }

                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }


            }
        });


        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SalesmanGUI back = new SalesmanGUI();
                dispose();
            }
        });
    }
}
