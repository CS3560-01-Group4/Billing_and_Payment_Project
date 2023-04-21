import javax.swing.*;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CustomerGUI extends JFrame {
    JMenuBar menuBar;
    JMenu Edit, View, LogOut;
    JMenuItem editName, editAddress, editSubscription, editEmailAddress;
    JMenuItem viewAccountInfo, viewSubscriptions;

    JMenuItem signOut;





    CustomerGUI(){
        this.setSize(600,600);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLayout(new FlowLayout());
        this.setIconImage(new ImageIcon("31-hour.png").getImage());

        menuBar = new JMenuBar();
        Edit = new JMenu("Edit");
        View = new JMenu("View");
        LogOut = new JMenu("Log out");


        Edit.add(editName = new JMenuItem("Edit Name"));
        Edit.add(editAddress = new JMenuItem("Edit Address"));
        Edit.add(editSubscription = new JMenuItem("Edit Subscription"));
        Edit.add(editEmailAddress = new JMenuItem("Edit Email"));


        View.add(viewAccountInfo = new JMenuItem("View Account Info"));
        View.add(viewSubscriptions = new JMenuItem("View Subscription"));

        LogOut.add(signOut = new JMenuItem("Sign Out"));

        menuBar.add(Edit);
        menuBar.add(View);
        menuBar.add(LogOut);




        editName.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("S");
            }
        });
        editAddress.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("S");
            }
        });
        editSubscription.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("S");
            }
        });
        editEmailAddress.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("S");
            }
        });

        viewAccountInfo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("p");
            }
        });

        viewAccountInfo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("z");
            }
        });




        this.setJMenuBar(menuBar);
        this.setVisible(true);















    }





}
