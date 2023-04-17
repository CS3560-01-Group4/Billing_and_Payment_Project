import javax.swing.*;
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

    SysAdminPageLogin() {
        this.setContentPane(SysAdminLogin);
        this.setTitle("System Admin Login");
        this.setIconImage(new ImageIcon("31-hour.png").getImage());
        this.setSize(600, 600);

        retreviedUsername = String.valueOf(passwordField.getPassword());
        retrievedPassword = usernameField.getText();


        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SysAdminGUI sysAdminGUI = new SysAdminGUI();

                /*
                Add in code for login verification
                 */

            }
        });
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

}
