import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.sql.SQLException;

public class SalesmanGUI extends JFrame {
    JMenuBar menuBar;
    JMenu Edit, View, LogOut;
    JMenuItem  editCustomerAddress, editCustomerSubscription, editEmailAddress, editCancelMembership;
    JMenuItem viewAccountInfo, viewSubscriptions;

    JMenuItem signOut;

    SalesmanGUI(){
        JLabel welcomeText = new JLabel("Welcome: ");
        this.setSize(600,600);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setIconImage(new ImageIcon("31-hour.png").getImage());
        this.setTitle("Sales Person account");
        this.setLayout(new FlowLayout());


        Font f = new Font("sans-serif", Font.BOLD, 20);
        UIManager.put("Menu.font", f);
        UIManager.put("MenuItem.font", f);

        try {
            BufferedImage myPicture = ImageIO.read(new File("interior.jpg"));
            JLabel picLabel = new JLabel(new ImageIcon(myPicture));
            add(picLabel);
        } catch (Exception e) {
            ;
        }

        menuBar = new JMenuBar();
        Edit = new JMenu("Edit");
        View = new JMenu("View");
        LogOut = new JMenu("Sign out");

        editCustomerAddress = new JMenuItem("Customer Address");
        editCustomerSubscription = new JMenuItem("Customer Purchase Subscription");
        editCancelMembership = new JMenuItem("Cancel Subscription");
        editEmailAddress = new JMenuItem("Customer Email Address");

        viewAccountInfo = new JMenuItem("Account Info");
        viewSubscriptions = new JMenuItem("Subscriptions");
        signOut = new JMenuItem("Sign out");

        Edit.add(editCustomerAddress);
        Edit.add(editCustomerSubscription);
        Edit.add(editCancelMembership);
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
                salesmanSearchCustomer viewAccount = new salesmanSearchCustomer(2);
            }
        });

        editCustomerSubscription.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new salesmanSearchCustomer(4);
            }
        });

        editEmailAddress.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                salesmanSearchCustomer viewAccount = new salesmanSearchCustomer(3);
            }
        });

        editCancelMembership.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                salesmanSearchCustomer cancelAccount = new salesmanSearchCustomer(6);
            }
        });

        viewAccountInfo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                salesmanSearchCustomer viewAccount = new salesmanSearchCustomer(1);
            }
        });

        viewSubscriptions.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new salesmanSearchCustomer(5);

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


    }
}
