import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PurchasePage extends JFrame {
    private JPanel PurchasePage;
    private JRadioButton monthly;
    private JRadioButton yearly;
    private JCheckBox classAddon;
    private JCheckBox trainerAddon;
    private JButton makePaymentButton;
    private ButtonGroup group = new ButtonGroup();
    public static int total, memberID, membershipName, addonID;

    PurchasePage(Customer customer) {
        this.setContentPane(PurchasePage);
        this.setTitle("Select products");
        this.setIconImage(new ImageIcon("31-hour.png").getImage());
        this.setSize(600, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        group.add(monthly);
        group.add(yearly);

        makePaymentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(monthly.isSelected()){
                    total += 30;
                }
                else if(yearly.isSelected()) {
                    total += 300;
                }
                if(classAddon.isSelected()){
                    total += 100;
                }
                if(trainerAddon.isSelected()) {
                    total += 200;
                }
                //PaymentPage payment = new PaymentPage();

                //save to database
                try {
                    DatabaseManager db = new DatabaseManager();
                    memberID = db.getOwnedMembership(customer.getId());
                    membershipName = db.getMembership(memberID);

                    if(classAddon.isSelected() || trainerAddon.isSelected()) {
                        db.saveEnrollment(memberID, membershipName, addonID);
                    }
                    db.saveSale(total, customer.getId(), memberID, membershipName);
                    db.close();
                }catch(Exception ex) {
                    System.out.println(ex);
                }
            }
        });
    }
}

