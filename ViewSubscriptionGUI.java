import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ViewSubscriptionGUI extends JFrame {
    private JPanel viewSubscription;
    private JTextField memberIdText, membershipName, statusText, startText, renewalText, priceText;
    private JButton exitButton;
    private JList jlist;
    private JTextField totalPriceText;

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
            if(membership == null || membership.getMembershipID() == -1) {
                JOptionPane.showMessageDialog(null, "You have no membership associated with your account");
                dispose();
            }else{
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
                totalPriceText.setEditable(false);

                DefaultListModel<String> dlm = new DefaultListModel<>();
                jlist.setModel(dlm);

                Addon[] addons = db.getAddons(membership);
                db.close();

                double sum = 0;
                for(int i = 0; i < addons.length; i++) {
                    dlm.addElement("            " + addons[i].getName() + "          $" + addons[i].getPrice());
                    sum += addons[i].getPrice();
                }
                totalPriceText.setText(     Double.toString(membership.getPrice() + sum)     );
            }
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
