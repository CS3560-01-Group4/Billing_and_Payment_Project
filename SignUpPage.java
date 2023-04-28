import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class SignUpPage extends JFrame {
    private JPanel SignUpPage = new JPanel();
    private JTextField name = new JTextField(20);
    private JTextField email = new JTextField(20);
    private JTextField address =  new JTextField(20);
    private JTextField city = new JTextField(20);
    private JTextField zip = new JTextField(10);
    private JTextField state = new JTextField(2);
    private JPasswordField password = new JPasswordField(20);
    private JTextField phoneNumber = new JTextField(20);
    private JLabel nameLabel = new JLabel("Enter Name");
    private JLabel emailLabel = new JLabel("Enter Email");
    private JLabel addressLabel = new JLabel("Enter Address");
    private JLabel cityLabel = new JLabel("City");
    private JLabel zipLabel = new JLabel("Zipcode");
    private JLabel stateLabel = new JLabel("State Abbreviation");
    private JLabel passwordLabel = new JLabel("Enter Password");
    private JLabel phoneNumberLabel = new JLabel("Enter Phone Number");
    private JButton nextButton = new JButton("Next");
    private JCheckBox showPasswordCheckBox = new JCheckBox();
    static String inputName, inputEmail, inputAddress, inputCity, inputZip, inputState, inputPassword,
    inputPhone;

    SignUpPage(){
    	//setup JFrame window
        this.setContentPane(SignUpPage);
        this.setTitle("Create A New Account");
        this.setIconImage(new ImageIcon("31-hour.png").getImage());
        this.setSize(600,600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        //setup labels and text fields and buttons
        this.add(nameLabel);
        name.setBounds(100,20,165,25);
        this.add(name);
        this.add(emailLabel);
        email.setBounds(100,20,165,25);
        this.add(email);
        this.add(addressLabel);
        address.setBounds(100,20,165,25);
        this.add(address);
        this.add(cityLabel);
        city.setBounds(100,20,165,25);
        this.add(city);
        this.add(zipLabel);
        zip.setBounds(100,20,165,25);
        this.add(zip);
        this.add(stateLabel);
        state.setBounds(100,20,165,25);
        this.add(state);
        this.add(phoneNumberLabel);
        phoneNumber.setBounds(100,20,165,25);
        this.add(phoneNumber);
        this.add(passwordLabel);
        password.setBounds(100,20,165,25);
        this.add(password);
        
        this.add(nextButton);
        
        
        
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
    }
}
