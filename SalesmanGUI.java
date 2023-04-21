import javax.swing.*;
import java.awt.*;

public class SalesmanGUI extends JFrame {
    JMenuBar menuBar;
    JMenu Edit, View, LogOut;
    JMenuItem  editCustomerAddress, editCustomerSubscription, editEmailAddress;
    JMenuItem viewAccountInfo, viewSubscriptions;

    JMenuItem signOut;

    SalesmanGUI(){
        this.setSize(600,600);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setIconImage(new ImageIcon("31-hour.png").getImage());
        this.setTitle("System Admin Main Page");
        this.setLayout(new FlowLayout());

        menuBar = new JMenuBar();
        Edit = new JMenu("Edit");
        View = new JMenu("View");
        LogOut = new JMenu("Sign out");

        editCustomerAddress = new JMenuItem("Customer Address");
        editCustomerSubscription = new JMenuItem("Customer Subscription");
        editEmailAddress = new JMenuItem("Customer Email Address");

        viewAccountInfo = new JMenuItem("Account Info");
        viewSubscriptions = new JMenuItem("Subscriptions");
        signOut = new JMenuItem("Sign out");

        Edit.add(editCustomerAddress);
        Edit.add(editCustomerAddress);
        Edit.add(editEmailAddress);

        View.add(viewAccountInfo);
        View.add(viewSubscriptions);

        LogOut.add(signOut);

        menuBar.add(Edit);
        menuBar.add(View);
        menuBar.add(signOut);

        this.setJMenuBar(menuBar);
        this.setVisible(true);

    }
}
