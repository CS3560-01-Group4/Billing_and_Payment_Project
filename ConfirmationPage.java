import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class ConfirmationPage extends JFrame{
    private JPanel ConfirmationPage;
    private JButton confirmButton;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JTextField textField5;
    private JTextField textField6;
    private JTextField textField7;
    private JTextField textField8;
    private JTextField textField9;
    private JTextField textField10;
    private JTextField textField11;
    private JTextField textField12;


    ConfirmationPage(){
        this.setContentPane(ConfirmationPage);
        this.setTitle("Confirmation");
        this.setIconImage(new ImageIcon("31-hour.png").getImage());
        this.setSize(600, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        textField1.setText(SignUpPage.inputName);
        textField2.setText(SignUpPage.inputEmail);
        textField3.setText(SignUpPage.inputAddress);
        textField4.setText(SignUpPage.inputCity);
        textField5.setText(SignUpPage.inputZip);
        textField6.setText(SignUpPage.inputState);
        textField7.setText(SignUpPage.inputPhone);
        textField8.setText(SignUpPage.inputPassword);
        textField9.setText(CreditPage.inputCard);
        textField10.setText(CreditPage.inputCSV);
        textField11.setText(CreditPage.inputMonth);
        textField12.setText(CreditPage.inputDate);

        SignUpPage.inputName = textField1.getText();
        SignUpPage.inputEmail = textField2.getText();
        SignUpPage.inputAddress = textField3.getText();
        SignUpPage.inputCity = textField4.getText();
        SignUpPage.inputZip = textField5.getText();
        SignUpPage.inputState = textField6.getText();
        SignUpPage.inputPhone = textField7.getText();
        SignUpPage.inputPassword = textField8.getText();
        CreditPage.inputCard = textField9.getText();
        CreditPage.inputCSV = textField10.getText();
        CreditPage.inputMonth = textField11.getText();
        CreditPage.inputDate = textField12.getText();

        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Address address = new Address(SignUpPage.inputAddress, SignUpPage.inputCity,
                        SignUpPage.inputZip, SignUpPage.inputState);

                CreditCard creditCard = new CreditCard(CreditPage.inputCard, CreditPage.inputCSV,
                        CreditPage.inputMonth, CreditPage.inputDate);

                Customer customer = new Customer(textField1.getText(), textField7.getText(),
                        textField2.getText(), textField8.getText(), creditCard, address);
                //save to database
                boolean completed = false;
                try{
                    DatabaseManager db = new DatabaseManager();
                    completed = db.saveCustomer(customer);
                    db.close();
                }catch(SQLException ex) {
                    System.out.println(ex.getMessage());
                    JOptionPane.showMessageDialog(null,"Username already taken");
                    textField2.setBackground(new Color(255, 102, 102));
                }

                if(completed) {
                    JOptionPane.showMessageDialog(ConfirmationPage, "Account successfully created");
                    dispose();
                    CSLoginPage cs = new CSLoginPage();
                }
            }
        });

    }
}
