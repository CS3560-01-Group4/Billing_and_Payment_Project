import javax.swing.*;

public class SignUpPage extends JFrame {
    private JPanel SignUpPage;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JTextField textField5;
    private JTextField textField6;
    private JTextField textField7;
    private JPasswordField passwordField1;
    private JButton nextButton;

    SignUpPage(){
        this.setContentPane(SignUpPage);
        this.setTitle("Create A New Account");
        this.setIconImage(new ImageIcon("31-hour.png").getImage());
        this.setSize(600,600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
}
