import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
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
    String[] emails = {"gmail.com","outlook.com","hotmail.com", "cpp.edu", "icloud.com","yahoo.com"};


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

        textField11.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!Character.isDigit(c))
                    e.consume();
                if (2 == textField11.getText().length())
                    e.consume();
            }
        });
        textField12.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!Character.isDigit(c))
                    e.consume();
                if (2 == textField12.getText().length())
                    e.consume();
            }
        });
        textField10.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!Character.isDigit(c))
                    e.consume();
                if (3 == textField10.getText().length())
                    e.consume();
            }
        });
        textField9.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!Character.isDigit(c))
                    e.consume();
                if (16 == textField9.getText().length())
                    e.consume();
            }
        });
        textField5.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if(!Character.isDigit(c))
                    e.consume();
                if(5 == textField5.getText().length())
                    e.consume();
            }
        });
        textField6.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if(Character.isDigit(c))
                    e.consume();
                if(2 == textField6.getText().length())
                    e.consume();
            }
        });

        textField7.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if(!Character.isDigit(c))
                    e.consume();
                if(10 == textField7.getText().length())
                    e.consume();
            }
        });
        textField4.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if(Character.isDigit(c))
                    e.consume();
            }
        });

        textField1.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if(Character.isDigit(c))
                    e.consume();
            }
        });

        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Address address = new Address(textField3.getText(), textField4.getText(),
                        textField5.getText(), textField6.getText());

                CreditCard creditCard = new CreditCard(textField9.getText(), textField10.getText(),
                        textField11.getText(), textField12.getText());

                Customer customer = new Customer(textField1.getText(), textField7.getText(),
                        textField2.getText(), textField8.getText(), creditCard, address);
                //User input validation
                if (textField1.getText().equals(""))
                    textField1.setBackground(new Color(255, 102, 102));
                else
                    textField1.setBackground(Color.white);
                if (textField2.getText().equals(""))
                    textField2.setBackground(new Color(255, 102, 102));
                else
                    textField2.setBackground(Color.white);
                if (textField3.getText().equals(""))
                    textField3.setBackground(new Color(255, 102, 102));
                else
                    textField3.setBackground(Color.white);
                if (textField4.getText().equals(""))
                    textField4.setBackground(new Color(255, 102, 102));
                else
                    textField4.setBackground(Color.white);
                if (textField5.equals("") || textField5.getText().length() > 5 || textField5.getText().length() < 5)
                    textField5.setBackground(new Color(255, 102, 102));
                else
                    textField5.setBackground(Color.white);
                if (textField6.equals("") || textField6.getText().length() < 2)
                    textField6.setBackground(new Color(255, 102, 102));
                else
                    textField6.setBackground(Color.white);
                if (textField7.getText().equals("") || textField7.getText().length() < 10)
                    textField7.setBackground(new Color(255, 102, 102));
                else
                    textField7.setBackground(Color.white);
                if (textField8.equals(""))
                    textField8.setBackground(new Color(255, 102, 102));
                else
                    textField8.setBackground(Color.white);
                if (Integer.parseInt(textField12.getText()) < 22 || Integer.parseInt(textField12.getText()) > 31)
                    textField12.setBackground(new Color(255, 102, 102));
                else
                    textField12.setBackground(Color.white);
                if (Integer.parseInt(textField11.getText()) > 12)
                    textField11.setBackground(new Color(255, 102, 102));
                else
                    textField11.setBackground(Color.white);
                if (textField10.getText().length() < 3)
                    textField10.setBackground(new Color(255, 102, 102));
                else
                    textField10.setBackground(Color.white);
                if (textField9.getText().length() < 16)
                    textField9.setBackground(new Color(255, 102, 102));
                else
                    textField9.setBackground(Color.white);
                if (Integer.parseInt(textField11.getText()) > 12 || Integer.parseInt(textField12.getText()) > 31 || Integer.parseInt(textField12.getText()) < 23 || textField9.getText().length() < 16
                        || textField10.getText().length() < 3 || textField1.getText().equals("") || textField2.getText().equals("") || textField3.getText().equals("")
                        || textField4.getText().equals("") || textField5.getText().equals("") || textField6.getText().equals("") || textField7.getText().equals("")
                        || textField8.getText().equals("") || textField5.getText().length() > 5 || textField5.getText().length() < 5 || textField6.getText().length() < 2 || textField7.getText().length() < 10)
                    JOptionPane.showMessageDialog(ConfirmationPage, "Invalid input");
                else {
                    boolean valid = false;
                    String[] arrOfStr = textField2.getText().split("@", 2);
                    for (int i = 0; i < 5; i++) {
                        try {
                            if (arrOfStr[1].equals(emails[i])) {
                                valid = true;
                            }
                        } catch (Exception a) {
                        }

                    }
                    if (!valid) {
                        JOptionPane.showMessageDialog(ConfirmationPage, "Invalid email format");
                        textField2.setBackground(new Color(255, 102, 102));
                    }
                    //save to database
                    if (valid) {
                        boolean completed = false;
                        try {
                            DatabaseManager db = new DatabaseManager();
                            completed = db.saveCustomer(customer);
                            db.close();
                        } catch (SQLException ex) {
                            System.out.println(ex.getMessage());
                            JOptionPane.showMessageDialog(null, "Username already taken");
                            textField2.setBackground(new Color(255, 102, 102));
                        }

                        if (completed) {
                            JOptionPane.showMessageDialog(ConfirmationPage, "Account successfully created");
                            dispose();
                            CSLoginPage cs = new CSLoginPage();
                        }
                    }
                }
            }
        });

    }
}
