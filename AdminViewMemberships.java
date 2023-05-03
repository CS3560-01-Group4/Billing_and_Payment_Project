import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.SQLException;

public class AdminViewMemberships extends JFrame{
    private JTextField textField1;
    private JTextField textField2;
    private JLabel membershipL;
    private JLabel MonthlyL;
    private JLabel yearlyL;
    private JTable trainerTable;


    AdminViewMemberships() {
        this.setTitle("Select products");
        this.setIconImage(new ImageIcon("31-hour.png").getImage());
        this.setSize(1200, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setLocationRelativeTo(null);


        try {
            DatabaseManager db = new DatabaseManager();
            textField1.setText("$   " + db.getMonthlyCost());
            textField2.setText("$   " + db.getYearlyCost());

            textField1.setEditable(false);
            textField2.setEditable(false);
        }
        catch (SQLException e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "There's no recovering from this error");
        }

        createUIComponents();
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
        this.add(trainerTable);

    }
}
