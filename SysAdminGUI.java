import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class SysAdminGUI extends JFrame {


    JMenuBar menuBar;
    JMenu Edit, View, LogOut;
    JMenuItem  editSubcriptions, editAddons;
    JMenuItem viewSubscriptions;

    JMenuItem signOutBox;

    SysAdminGUI(){
        this.setSize(600,600);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setIconImage(new ImageIcon("31-hour.png").getImage());
        this.setTitle("System Admin Main Page");
        this.setLayout(new FlowLayout());

        menuBar = new JMenuBar();
        Edit = new JMenu("Edit");
        View = new JMenu("View");
        LogOut = new JMenu("Log out");

        editSubcriptions = new JMenuItem("Memberships");
        editAddons = new JMenuItem("Addons");


        viewSubscriptions = new JMenuItem("Memberships");

        signOutBox = new JMenuItem("Sign Out");


        Edit.add(editSubcriptions);
        Edit.add(editAddons);
        View.add(viewSubscriptions);
        LogOut.add(signOutBox);

        menuBar.add(LogOut);
        menuBar.add(Edit);
        menuBar.add(View);



        this.setJMenuBar(menuBar);
        this.setVisible(true);

        editSubcriptions.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ;
            }
        });

        editAddons.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ;
            }
        });

        viewSubscriptions.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AdminViewMemberships();
            }
        });


        signOutBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new UI();
            }
        });

    }


}
