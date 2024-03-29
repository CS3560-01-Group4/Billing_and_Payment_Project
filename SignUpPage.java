import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class SignUpPage extends JFrame {
    private JPanel SignUpPage;
    private JTextField name;
    private JTextField email;
    private JTextField address;
    private JTextField city;
    private JTextField zip;
    private JTextField state;
    private JPasswordField password;
    private JTextField phoneNumber;
    private JLabel nameLabel;
    private JLabel emailLabel;
    private JLabel addressLabel;
    private JLabel cityLabel;
    private JLabel zipLabel;
    private JLabel stateLabel;
    private JLabel passwordLabel;
    private JLabel phoneNumberLabel;
    private JButton nextButton;
    private JCheckBox showPasswordCheckBox;
    private JButton backButton;
    static String inputName, inputEmail, inputAddress, inputCity, inputZip, inputState, inputPassword,
    inputPhone;

    SignUpPage(){
    	//setup JFrame window
        this.setContentPane(SignUpPage);
        this.setTitle("Create A New Account");
        this.setIconImage(new ImageIcon("31-hour.png").getImage());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(600,600);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        //setup labels and text fields and buttons

        
        
        
        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Setting user input
                inputName = name.getText();
                inputEmail = email.getText();
                inputAddress = address.getText();
                inputCity = city.getText();
                inputZip = zip.getText();
                inputState = state.getText();
                inputPhone = phoneNumber.getText();
                inputPassword = String.valueOf(password.getPassword());

                //Input validation
                if (inputName.equals(""))
                    name.setBackground(new Color(255, 102, 102));
                else
                    name.setBackground(Color.white);
                if (inputEmail.equals(""))
                    email.setBackground(new Color(255, 102, 102));
                else
                    email.setBackground(Color.white);
                if (inputAddress.equals(""))
                    address.setBackground(new Color(255, 102, 102));
                else
                    address.setBackground(Color.white);
                if (inputCity.equals(""))
                    city.setBackground(new Color(255, 102, 102));
                else
                    city.setBackground(Color.white);
                if (inputZip.equals(""))
                    zip.setBackground(new Color(255, 102, 102));
                else
                    zip.setBackground(Color.white);
                if (inputState.equals(""))
                    state.setBackground(new Color(255, 102, 102));
                else
                    state.setBackground(Color.white);
                if (inputPhone.equals(""))
                    phoneNumber.setBackground(new Color(255, 102, 102));
                else
                    phoneNumber.setBackground(Color.white);
                if (inputPassword.equals(""))
                    password.setBackground(new Color(255, 102, 102));
                else
                    password.setBackground(Color.white);
                if (inputName.equals("") || inputEmail.equals("") || inputAddress.equals("") || inputCity.equals("")
                        || inputZip.equals("") || inputState.equals("") || inputPhone.equals("") || inputPassword.equals(""))
                    JOptionPane.showMessageDialog(SignUpPage, "Invalid input");
                else{
                    CreditPage enterCredit = new CreditPage();
                    dispose();
                }
            }
        });
        //Input validation
        zip.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if(!Character.isDigit(c))
                    e.consume();
                if(5 == zip.getText().length())
                    e.consume();
            }
        });
        city.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if(Character.isDigit(c))
                    e.consume();
            }
        });
        state.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if(Character.isDigit(c))
                    e.consume();
                if(2 == state.getText().length())
                    e.consume();
            }
        });

        phoneNumber.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if(!Character.isDigit(c))
                    e.consume();
                if(10 == phoneNumber.getText().length())
                    e.consume();
            }
        });

        name.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if(Character.isDigit(c))
                    e.consume();
            }
        });
        showPasswordCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                password.setEchoChar((char)0);
                if(!showPasswordCheckBox.isSelected())
                    password.setEchoChar('*');
            }
        });
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new CSLoginPage();
                dispose();
            }
        });
    }
}
