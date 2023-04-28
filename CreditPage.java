import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class CreditPage extends JFrame {
    private JPanel CreditPage;
    private JTextField month;
    private JTextField cardNumber;
    private JPasswordField csv;
    private JTextField date;
    private JButton confirmPaymentButton;

    static String inputCard, inputCSV, inputDate, inputMonth;

    CreditPage() {
        this.setContentPane(CreditPage);
        this.setTitle("Enter Credit Information");
        this.setIconImage(new ImageIcon("31-hour.png").getImage());
        this.setSize(600, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        month.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!Character.isDigit(c))
                    e.consume();
                if (2 == month.getText().length())
                    e.consume();
            }
        });
        date.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!Character.isDigit(c))
                    e.consume();
                if (2 == date.getText().length())
                    e.consume();
            }
        });
        csv.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!Character.isDigit(c))
                    e.consume();
                if (3 == csv.getText().length())
                    e.consume();
            }
        });
        cardNumber.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!Character.isDigit(c))
                    e.consume();
                if (16 == cardNumber.getText().length())
                    e.consume();
            }
        });

        confirmPaymentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                inputCard = cardNumber.getText();
                inputCSV = String.valueOf(csv.getPassword());
                inputDate = date.getText();
                inputMonth = month.getText();
                if(Integer.parseInt(inputDate) > 31)
                    date.setBackground(new Color(255, 102, 102));
                else
                    date.setBackground(Color.white);
                if(Integer.parseInt(inputMonth) >12)
                    month.setBackground(new Color(255, 102, 102));
                else
                    month.setBackground(Color.white);

                if(inputCard.length() < 16)
                    cardNumber.setBackground(new Color(255, 102, 102));
                else
                    cardNumber.setBackground(Color.white);
                if(Integer.parseInt(inputMonth) >12 ||Integer.parseInt(inputDate) > 31 || inputCard.length() < 16)
                    JOptionPane.showMessageDialog(CreditPage, "Invalid input");
                else{
                    ConfirmationPage ConfirmationPage = new ConfirmationPage();
                    dispose();
                }

            }
        });
    }
}
