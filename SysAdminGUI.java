import javax.swing.*;
import java.awt.*;

public class SysAdminGUI extends JFrame {

    JMenuBar menuBar;
    JMenu Edit, View;
    JMenuItem  editSubcriptions, editAddons;
    JMenuItem viewSubscriptions, viewAddons;

    SysAdminGUI(){
        this.setSize(600,600);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setIconImage(new ImageIcon("31-hour.png").getImage());
        this.setTitle("System Admin Main Page");
        this.setLayout(new FlowLayout());

        menuBar = new JMenuBar();
        Edit = new JMenu("Edit");
        View = new JMenu("View");

        editSubcriptions = new JMenuItem("Subscription");
        editAddons = new JMenuItem("Addons");


        viewAddons = new JMenuItem("Addons");
        viewSubscriptions = new JMenuItem("Subscriptions");

        Edit.add(editSubcriptions);
        Edit.add(editAddons);


        View.add(viewSubscriptions);
        View.add(viewAddons);

        menuBar.add(Edit);
        menuBar.add(View);

        this.setJMenuBar(menuBar);
        this.setVisible(true);

    }


}
