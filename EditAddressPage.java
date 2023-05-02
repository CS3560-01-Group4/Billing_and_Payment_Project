import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class EditAddressPage extends JFrame {
    String inputAddress, inputCity, inputZip, inputState;
    private JPanel EditAddressPage;
    private JTextField postalCode;
    private JTextField city;
    private JTextField state;
    private JTextField address;
    private JButton Update;
    boolean completed = false;
    String url = "jdbc:mysql://" + "containers-us-west-34.railway.app" + ":" + 5939 + "/" + "gymmembership";

    private Connection connection = DriverManager.getConnection(url, "root", "91laqZk1CB5VM13WltEE");;

    EditAddressPage(Customer customer) throws SQLException {
        this.setContentPane(EditAddressPage);
        this.setTitle("Edit Address");
        this.setIconImage(new ImageIcon("31-hour.png").getImage());
        this.setSize(600, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setLocationRelativeTo(null);

        state.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (Character.isDigit(c)) {
                    e.consume();
                }
                if (2 == state.getText().length()) {
                    e.consume();
                }

            }
        });
        postalCode.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!Character.isDigit(c)) {
                    e.consume();
                }

                if (5 == postalCode.getText().length()) {
                    e.consume();
                }

            }
        });

        Update.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                inputAddress = address.getText();
                inputCity = city.getText();
                inputZip = postalCode.getText();
                inputState = state.getText();
       /*
       Input validation for editing customer credentials
        */

                if (inputAddress.equals("")) {
                    address.setBackground(new Color(255, 102, 102));

                }
                if (inputCity.equals("")) {
                    city.setBackground(new Color(255, 102, 102));

                }
                if (inputZip.equals("")) {
                    postalCode.setBackground(new Color(255, 102, 102));
                }
                if (inputState.equals("")) {
                    state.setBackground(new Color(255, 102, 102));
                }
                if (inputAddress.equals("") || inputZip.equals("") || inputCity.equals("") || inputState.equals("")) {
                    JOptionPane.showMessageDialog(EditAddressPage, "Invalid input");
                }
                else{
                    //open up database
                    try {
                        DatabaseManager db = new DatabaseManager();
                        Statement statement = connection.createStatement();
                        String sql = "UPDATE Address SET streetName='" + inputAddress +"', city='" + inputCity + "', zipcode='" + inputZip + "',state='" + inputState +
                                 "'WHERE Customer_Account_accountID='" + customer.getId() + "';"; //Input primary key
                        statement.executeUpdate(sql);
                        db.close();
                        JOptionPane.showMessageDialog(null, "Updated");
                        dispose();
                    }
                    catch (SQLException throwables) {
                        throwables.printStackTrace();
                        JOptionPane.showMessageDialog(null, "Error");
                    }
                }
            }
        });
    }
}
