import javax.swing.*;
import java.awt.*;

public class SalesmanGUI extends JFrame {
    JMenuBar menuBar;
    JMenu Edit, View;
    JMenuItem  editCustomerAddress, editCustomerSubscription, editEmailAddress;
    JMenuItem viewAccountInfo, viewSubscriptions;

    SalesmanGUI(){
        this.setSize(600,600);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setLayout(new FlowLayout());

        menuBar = new JMenuBar();
        Edit = new JMenu("Edit");
        View = new JMenu("View");

        editCustomerAddress = new JMenuItem("Customer Address");
        editCustomerSubscription = new JMenuItem("Customer Subscription");
        editEmailAddress = new JMenuItem("Customer Email Address");

        viewAccountInfo = new JMenuItem("Account Info");
        viewSubscriptions = new JMenuItem("Subscriptions");

        Edit.add(editCustomerAddress);
        Edit.add(editCustomerAddress);
        Edit.add(editEmailAddress);

        View.add(viewAccountInfo);
        View.add(viewSubscriptions);

        menuBar.add(Edit);
        menuBar.add(View);

        this.setJMenuBar(menuBar);
        this.setVisible(true);

    }
}
