import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UpdatePricePage extends JFrame {
    private JTextField textField1;
    private JTextField textField2;
    private JButton updateYearlyCostButton;
    private JButton updateMonthlyCostButton;
    private JButton OKButton;
    private JPanel panel;

    public UpdatePricePage() {
        this.setContentPane(panel);
        this.setSize(600, 600);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLayout(new FlowLayout());
        this.setIconImage(new ImageIcon("31-hour.png").getImage());
        this.setLocationRelativeTo(null);


        //set the textfields with the price of Memberships
        double monthly = 0, yearly = 0;
        try {
            DatabaseManager db = new DatabaseManager();
            monthly = db.getMonthlyCost();
            yearly = db.getYearlyCost();
            db.close();
        }catch(Exception ex) {
            ex.printStackTrace();
        }
        textField1.setText("$   " + monthly);
        textField2.setText("$   " + yearly);
        textField1.setEditable(false);
        textField2.setEditable(false);


        OKButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        updateYearlyCostButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String inputPrice = JOptionPane.showInputDialog("Enter new Yearly price: ");
                if (inputPrice == null || inputPrice.equals("")) {
                    JOptionPane.showMessageDialog(null, "Invalid input");
                }else {
                    try {
                        DatabaseManager db = new DatabaseManager();
                        db.setYearlyCost( Double.parseDouble(inputPrice) );
                        JOptionPane.showMessageDialog(null, "Successfully update yearly price");
                    }catch(Exception ex) {
                        ex.printStackTrace();
                    }
                }
                new UpdatePricePage();
                dispose();
            }
        });
        updateMonthlyCostButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String inputPrice = JOptionPane.showInputDialog("Enter new Monthly price: ");
                if (inputPrice == null || inputPrice.equals("")) {
                    JOptionPane.showMessageDialog(null, "Invalid input");
                }else {
                    try {
                        DatabaseManager db = new DatabaseManager();
                        db.setMonthlyCost( Double.parseDouble(inputPrice) );
                        JOptionPane.showMessageDialog(null, "Successfully update monthly price");
                    }catch(Exception ex) {
                        ex.printStackTrace();
                    }
                }
                new UpdatePricePage();
                dispose();
            }
        });

        this.setVisible(true);
    }
}
