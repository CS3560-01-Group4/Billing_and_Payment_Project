import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.SQLException;

public class SalesmanGUI extends JFrame {
    JMenuBar menuBar;
    JMenu Edit, View, LogOut;
    JMenuItem  editCustomerAddress, editCustomerSubscription, editEmailAddress;
    JMenuItem viewAccountInfo, viewSubscriptions;

    JMenuItem signOut;

    SalesmanGUI(){
        JLabel welcomeText = new JLabel("Welcome: ");
        this.setSize(600,600);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setIconImage(new ImageIcon("31-hour.png").getImage());
        this.setTitle("Sales Person account");
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
        Edit.add(editCustomerSubscription);
        Edit.add(editEmailAddress);

        View.add(viewAccountInfo);
        View.add(viewSubscriptions);

        LogOut.add(signOut);

        menuBar.add(Edit);
        menuBar.add(View);
        menuBar.add(signOut);

        this.setJMenuBar(menuBar);
        this.setVisible(true);

        signOut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UI back = new UI();
                dispose();
            }
        });

        editCustomerAddress.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    salesmanSearchCustomer viewAccount = new salesmanSearchCustomer(2);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        editCustomerSubscription.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        editEmailAddress.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    salesmanSearchCustomer viewAccount = new salesmanSearchCustomer(3);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });


        viewAccountInfo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    salesmanSearchCustomer viewAccount = new salesmanSearchCustomer(1);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }


            }
        });

        viewSubscriptions.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

    }


    public void setEditCustomerAddress() throws SQLException {
        int flag = 2;

     salesmanSearchCustomer search = new salesmanSearchCustomer(flag);


    }


    public void getCustomerAccountInfo() throws SQLException{
        int flag = 1;
        salesmanSearchCustomer search = new salesmanSearchCustomer(flag);

    }

    public void setEditCustomerSubscription(){
        /*
        Update after we add DB functions to get the required info
         */

    }








}
