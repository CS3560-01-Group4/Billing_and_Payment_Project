import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class ViewSubscriptionGUI extends JFrame {
    private JPanel viewSubscription;
    private JTextField memberIdText, membershipName, statusText, startText, renewalText, priceText;
    //TODO something for addons list
    //also the price will show the price of membership, not total price of membership + addon
    private JButton exitButton;

    public ViewSubscriptionGUI(Customer customer) {
        this.setContentPane(viewSubscription);
        this.setSize(600,600);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setIconImage(new ImageIcon("31-hour.png").getImage());

        this.setVisible(true);

        try {
            DatabaseManager db = new DatabaseManager();
            Membership membership = db.getMembership(customer);
            db.close();
            memberIdText.setText(Integer.toString(membership.getMembershipID()));
            membershipName.setText( membership.getName() );
            startText.setText(  membership.getMembershipStartDate() );
            renewalText.setText(    membership.getMembershipRenewalDate()   );
            priceText.setText(  Double.toString(membership.getPrice())  );
            if(membership.isMembershipActive()) {
                statusText.setText("Active");
            }else{
                statusText.setText("Disabled");
            }

            memberIdText.setEditable(false);
            membershipName.setEditable(false);
            statusText.setEditable(false);
            startText.setEditable(false);
            renewalText.setEditable(false);
            priceText.setEditable(false);
        }
        catch (Exception e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "There was an issue getting Subscription info");
        }


        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }
}
