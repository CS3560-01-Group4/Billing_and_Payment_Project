import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class viewCustomerCardInfo extends JFrame{
    private JTextField ccNumberField;
    private JTextField ccvField;
    private JTextField expirationFieldDay;
    private JPanel viewCCInfoPanel;
    private JLabel creditLabel;
    private JTextField monthFieldExpiration;
    private JButton returnButton;

    boolean completed = false;

    //String url = "jdbc:mysql://" + "containers-us-west-34.railway.app" + ":" + 5939 + "/" + "gymmembership";

    //private Connection connection = DriverManager.getConnection(url, "root", "91laqZk1CB5VM13WltEE");;

    viewCustomerCardInfo(Customer customer) throws SQLException {
        this.setContentPane(viewCCInfoPanel);
        this.setTitle("Account Information");
        this.setIconImage(new ImageIcon("31-hour.png").getImage());
        this.setSize(600, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);



        try {

            DatabaseManager dbConnection = new DatabaseManager();
            Customer viewAccount = dbConnection.getCustomerCredentials(customer.getEmail(), customer.getPassword());
            ccNumberField.setText(viewAccount.getCreditCard().getCardNumb());
            ccvField.setText(viewAccount.getCreditCard().getCsv());
            expirationFieldDay.setText(viewAccount.getCreditCard().getExpDay());
            monthFieldExpiration.setText(viewAccount.getCreditCard().getExpMonth());


            ccNumberField.setEditable(false);
            ccvField.setEditable(false);
            expirationFieldDay.setEditable(false);
            monthFieldExpiration.setEditable(false);


        }
        catch (SQLException e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "There's no recovering from this error");
        }


        returnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

    }
}
