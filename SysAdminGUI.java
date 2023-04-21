import javax.swing.*;
import java.awt.*;

public class SysAdminGUI extends JFrame {

    JMenuBar menuBar;
    JMenu Edit, View, LogOut;
    JMenuItem  editSubcriptions, editAddons;
    JMenuItem viewSubscriptions, viewAddons;

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

        editSubcriptions = new JMenuItem("Subscription");
        editAddons = new JMenuItem("Addons");


        viewAddons = new JMenuItem("Addons");
        viewSubscriptions = new JMenuItem("Subscriptions");

        signOutBox = new JMenuItem("Sign Out");


        Edit.add(editSubcriptions);
        Edit.add(editAddons);


        View.add(viewSubscriptions);
        View.add(viewAddons);

        LogOut.add(signOutBox);

        menuBar.add(LogOut);
        menuBar.add(Edit);
        menuBar.add(View);

        this.setJMenuBar(menuBar);
        this.setVisible(true);

    }


}
