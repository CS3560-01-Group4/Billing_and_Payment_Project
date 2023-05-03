import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class addAddon extends JFrame{
    private JTable addonsTable;
    private JButton purchaseButton;
    private JPanel addAddonWindow;

    boolean addonSelected;

    private int total, addonIDint;

    private int memberID;
    private Integer addonID;
    private Addon chosenAddon;

    private String AddonType,AddonID, nameTrainer, price;
    private static String AddonTypes[] = {"Trainer", "Class"};

    addAddon(Customer customer){
        this.setContentPane(addAddonWindow);
        this.setTitle("Select products");
        this.setIconImage(new ImageIcon("31-hour.png").getImage());
        this.setSize(1200, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setLocationRelativeTo(null);






        addonsTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                int row = addonsTable.getSelectedRow();
                DefaultTableModel model = (DefaultTableModel) addonsTable.getModel();

                /*
                Prevents double event issue, wtf

                 */
                if(e.getValueIsAdjusting() && addonsTable.getSelectedRow() !=-1 ){
                    AddonType =(String) model.getValueAt(row,0);
                    addonID = (Integer) model.getValueAt(row,1);
                    nameTrainer = (String) model.getValueAt(row, 2);

                    addonSelected = true;

                    System.out.println(model.getValueAt(row,0));
                    System.out.println(model.getValueAt(row,1));
                    System.out.println(model.getValueAt(row,2));
                    System.out.println(model.getValueAt(row,3));

                }




            }
        });


        purchaseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String membershipName = "";

                if(addonSelected){
                    try {
                        DatabaseManager db = new DatabaseManager();
                        chosenAddon = db.getAddon(addonID.intValue());
                        total += chosenAddon.getPrice();


                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                }




                //save to database
                try {
                    DatabaseManager db = new DatabaseManager();
                    Membership existingMembership = db.getMembership(customer);
                    Addon addons[] = db.getAddons(existingMembership);


                    for(int i = 0; i < db.getAddons(existingMembership).length; i++){
                        if(addonID.intValue() == addons[i].getAddonID() && AddonType.equals(addons[i].getName()) ){
                            JOptionPane.showMessageDialog(null, "You cannot add a duplicate of the same addon! Select a different addon.");
                            dispose();
                            db.close();
                        }
                    }


                    db.saveEnrollment(memberID, membershipName, chosenAddon.getAddonID());
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
        addonsTable = new JTable(tableModel);
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
        tableModel.addColumn("Name/Class Type");
        tableModel.addColumn("Addon Cost");
        tableModel.addColumn("Date");

        for (int i = 0; i < trainers.length; i++) {
            tableModel.addRow(new Object[]{"Trainer", trainers[i].getAddonID(), trainers[i].getTrainerName(), trainers[i].getPrice(), trainers[i].getBookingDate()});

        }

        for (int i = 0; i < classes.length; i++) {
            tableModel.addRow(new Object[]{"Class", classes[i].getAddonID(), classes[i].getName(), classes[i].getPrice(), classes[i].getClassDate()});

        }
        /*
        Make's cells uneditable by the user, doesn't allow for a user to fuck up

         */
        addonsTable.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        addonsTable.setDefaultEditor(Object.class, null);

    }
}
