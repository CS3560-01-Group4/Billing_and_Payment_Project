import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class PurchasePage extends JFrame {
    private JPanel PurchasePage;
    private JRadioButton monthly;
    private JRadioButton yearly;
    private JCheckBox classAddon;
    private JCheckBox trainerAddon;
    private JButton makePaymentButton;
    private JTable AddonTable;


    private ButtonGroup group = new ButtonGroup();
    public static int total;

    private int memberID;
    private Addon addons[];


    PurchasePage(Customer customer) {
        this.setContentPane(PurchasePage);
        this.setTitle("Select products");
        this.setIconImage(new ImageIcon("31-hour.png").getImage());
        this.setSize(600, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setLocationRelativeTo(null);

        group.add(monthly);
        group.add(yearly);



        makePaymentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String membershipName = "";
                if(monthly.isSelected()){
                    total += 40;
                    membershipName = "monthly";
                }
                else if(yearly.isSelected()) {
                    total += 480;
                    membershipName = "yearly";
                }

                //TODO if addon selected, add the addon to the Addons array

                //save to database
                try {
                    DatabaseManager db = new DatabaseManager();
                    memberID = db.createOwnedMembership(customer.getId(), membershipName);

                    for(int i = 0; i < addons.length; i++) {
                        db.saveEnrollment(memberID, membershipName, addons[i].getAddonID());
                        total += addons[i].getPrice();
                    }
                    db.saveSale(total, customer.getId(), memberID, membershipName);
                    JOptionPane.showMessageDialog(null, "Successfully Purchased");
                    new CustomerGUI(customer);
                    db.close();
                    dispose();
                }catch(Exception ex) {
                    System.out.println(ex);
                    JOptionPane.showMessageDialog(null, "Failed to Purchase");
                    dispose();
                }
            }
        });
    }

    private void createUIComponents() {
        DefaultTableModel tableModel = new DefaultTableModel();
        AddonTable = new JTable(tableModel);
        PersonalTrainer[] trainers = new PersonalTrainer[0];
        Class[] classes = new Class[0];
        try {
            DatabaseManager db = new DatabaseManager();
            trainers = db.getTrainers();
            db.getTrainers();
            classes = db.getClasses();

            if(trainers == null && classes == null){
                JOptionPane.showMessageDialog(null, "There are no Addons in the DB");
                dispose();
            }
            db.close();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "There are no trainers in the DB");
        }


        tableModel.addColumn("Addon");
        tableModel.addColumn("Addon ID");
        tableModel.addColumn("Name");
        tableModel.addColumn("Addon Cost");
        tableModel.addColumn("Date");

        for (int i = 0; i < trainers.length; i++) {
            tableModel.addRow(new Object[]{"Trainer", trainers[i].getAddonID(), trainers[i].getTrainerName(), trainers[i].getPrice(), trainers[i].getBookingDate()});

        }

        for (int i = 0; i < classes.length; i++) {
            tableModel.addRow(new Object[]{"Class", classes[i].getAddonID(), classes[i].getName(), classes[i].getPrice(), classes[i].getClassDate()});

        }

    }
}

