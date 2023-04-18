import javax.swing.*;

public class SignUpPage extends JFrame {
    private JPanel SignUpPage;
    private JTextField firstName;
    private JTextField lastName;
    private JTextField email;
    private JTextField address;
    private JTextField city;
    private JTextField zip;
    private JTextField state;
    private JPasswordField password;
    private JButton nextButton;
    private JTextField phoneNumber;
    private String inputFirst, inputLast, inputEmail, inputAddress, inputCity, inputZip, inputState, inputPassword,
    inputPhone;

    SignUpPage(){
        this.setContentPane(SignUpPage);
        this.setTitle("Create A New Account");
        this.setIconImage(new ImageIcon("31-hour.png").getImage());
        this.setSize(600,600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        inputFirst = firstName.getText();
        inputLast = lastName.getText();
        inputEmail = email.getText();
        inputAddress = address.getText();
        inputCity = city.getText();
        inputZip = zip.getText();
        inputState = state.getText();
        inputPhone = phoneNumber.getText();
        inputPassword = String.valueOf(password.getPassword());
    }
}
