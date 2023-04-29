import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SysAdminPageLogin extends JFrame{
    private JTextField usernameField;
    private JLabel passwordLabel;

    private String retrievedPassword, retreviedUsername;
    private JLabel usernameLabel;
    private JButton loginButton;
    private JPasswordField passwordField;
    private JPanel SysAdminLogin;
    private JButton signupButton;

    SysAdminPageLogin() {
        this.setContentPane(SysAdminLogin);
        this.setTitle("System Admin Login");
        this.setIconImage(new ImageIcon("31-hour.png").getImage());
        this.setSize(600, 600);
        JFrame frame = this;

        retreviedUsername = usernameField.getText();
        retrievedPassword = String.valueOf(passwordField.getPassword());

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean verified = false;
                try {
                    DatabaseManager db = new DatabaseManager();
                    verified = db.searchAdmin(retreviedUsername,retrievedPassword);
                    db.close();
                }catch(Exception ex) {
                    JOptionPane.showMessageDialog(null, "Connection failed");
                }

                if(verified) {
                    dispose();
                    AdminGUI aGui = new AdminGUI();
                }else {
                    JOptionPane.showMessageDialog(null, "Incorrect Credentials");
                }
            }
        });

        signupButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new SignUpPage();
            }
        });

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
}