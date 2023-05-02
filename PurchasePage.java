import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
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
    private JTable trainerTable;


    private ButtonGroup group = new ButtonGroup();
    public static int total;
    private String AddonType,AddonID, nameTrainer, price;
    private static String AddonTypes[] = {"Trainer", "Class"};

    boolean addonSelected = false;
    private Addon chosenAddon;



    private int memberID;
    private Addon addons[];


    PurchasePage(Customer customer) {
        
        
        this.setContentPane(PurchasePage);
        this.setTitle("Select products");
        this.setIconImage(new ImageIcon("31-hour.png").getImage());
        this.setSize(1200, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setLocationRelativeTo(null);

        group.add(monthly);
        group.add(yearly);

        trainerTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                int row = trainerTable.getSelectedRow();
                DefaultTableModel model = (DefaultTableModel) trainerTable.getModel();
                addonSelected = true;
                /*
                Prevents double event issue, wtf

                 */
                if(e.getValueIsAdjusting() && trainerTable.getSelectedRow() !=-1 ){
                    AddonType =(String) model.getValueAt(row,0);
                    AddonID = (String) model.getValueAt(row,1);
                    nameTrainer = (String) model.getValueAt(row, 2);

                    
                    System.out.println(model.getValueAt(row,0));
                    System.out.println(model.getValueAt(row,1));
                    System.out.println(model.getValueAt(row,2));
                    System.out.println(model.getValueAt(row,3));

                }




            }
        });





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

                if(addonSelected){
                    try {
                        DatabaseManager db = new DatabaseManager();
                        chosenAddon = db.getAddon(Integer.parseInt(AddonID));
                        total += chosenAddon.getPrice();


                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                }
                



                //save to database
                try {
                    DatabaseManager db = new DatabaseManager();
                    memberID = db.createOwnedMembership(customer.getId(), membershipName);


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
        trainerTable = new JTable(tableModel);
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
        trainerTable.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        trainerTable.setDefaultEditor(Object.class, null);

    }



}

