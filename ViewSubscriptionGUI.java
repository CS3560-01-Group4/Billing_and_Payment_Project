import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ViewSubscriptionGUI extends JFrame {
    private JPanel viewSubscription;
    private JTextField memberIdText, membershipName, statusText, startText, renewalText, priceText;
    private JButton exitButton;
    private JList jlist;

    public ViewSubscriptionGUI(Customer customer) {
        this.setContentPane(viewSubscription);
        this.setSize(600,600);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setIconImage(new ImageIcon("31-hour.png").getImage());

        this.setVisible(true);
        Membership membership;

        try {
            DatabaseManager db = new DatabaseManager();
            membership = db.getMembership(customer);

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

            DefaultListModel<Addon[]> dlm = new DefaultListModel<>();
            jlist.setModel(dlm);
            //remember to change the price too (and mayber show the price of each individual one)
            Addon[] addons = db.getAddons(membership);
            db.close();
            dlm.addElement(addons);

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
