import javax.swing.*;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.SQLException;

public class CustomerGUI extends JFrame {
    JMenuBar menuBar;
    JMenu Edit, Purchase, View, LogOut;
    JMenuItem editName, editAddress, editSubscription, editEmailAddress, membership;
    JMenuItem viewAccountInfo, viewSubscriptions;
    JMenuItem signOut;
    Customer customer = null;






    CustomerGUI(Customer customer){
    	this.customer = customer;
        this.setSize(600,600);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLayout(new FlowLayout());
        this.setIconImage(new ImageIcon("31-hour.png").getImage());

        menuBar = new JMenuBar();
        Edit = new JMenu("Edit");
        Purchase = new JMenu("Purchase");
        View = new JMenu("View");
        LogOut = new JMenu("Log out");

        JLabel welcomeText = new JLabel("Welcome: " + customer.getName());
        this.add(welcomeText);


        Edit.add(editName = new JMenuItem("Edit Name"));
        Edit.add(editAddress = new JMenuItem("Edit Address"));
        Edit.add(editSubscription = new JMenuItem("Edit Subscription"));
        Edit.add(editEmailAddress = new JMenuItem("Edit Email"));


        View.add(viewAccountInfo = new JMenuItem("View Account Info"));
        View.add(viewSubscriptions = new JMenuItem("View Subscription"));

        Purchase.add(membership = new JMenuItem("Subscription"));

        LogOut.add(signOut = new JMenuItem("Sign Out"));

        menuBar.add(Purchase);
        menuBar.add(Edit);
        menuBar.add(View);
        menuBar.add(LogOut);



        membership.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Clicked");
                PurchasePage purchase = new PurchasePage();
            }
        });
        editName.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EditName();
            }
        });
        signOut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                CSLoginPage loginPage = new CSLoginPage();
            }
        });
        editAddress.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    EditAddress(customer);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });
        editSubscription.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EditSubscription();

            }
        });
        editEmailAddress.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EditEmailAddress();
            }
        });

        viewAccountInfo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    ViewAccountInfo(customer);
                }
                catch (SQLException exception){
                    exception.printStackTrace();
                }
            }
        });





        this.setJMenuBar(menuBar);
        this.setVisible(true);















    }


    public void EditName(){
        String inputName = JOptionPane.showInputDialog("Enter new name");
        if (inputName.equals("")){
            JOptionPane.showMessageDialog(null, "Invalid input");
        }

       //update database
        boolean completed = false;
        try {
        	DatabaseManager db = new DatabaseManager();
        	completed = db.updateCustomerName(customer.getId(), inputName);
        	db.close();
        }catch(Exception ex) {
        	System.out.println("Error connecting to the database");
        }
        
        if(completed) {
        	JOptionPane.showMessageDialog(null, "Successfully Updated");
        }
        else {
        	JOptionPane.showMessageDialog(null, "There was an error updating the name");
        }
    }


    public void EditAddress(Customer customer) throws SQLException {
        EditAddressPage editAddress = new EditAddressPage(customer);
    }


    public void EditEmailAddress(){
        String inputEmailAddress = JOptionPane.showInputDialog("Enter new email");

        if (inputEmailAddress.equals(""))
            JOptionPane.showMessageDialog(null, "Invalid input");

        //update email on database
        boolean completed = false;
        try {
            DatabaseManager db = new DatabaseManager();
        	completed = db.updateEmail(1, inputEmailAddress);
            db.close();
        }catch (Exception ex) {
        	System.out.println("Error connecting to database");
        }
        
        if(completed) {
        	JOptionPane.showMessageDialog(null, "Email was Successfully Updated");
        }else {
        	JOptionPane.showMessageDialog(null, "Email could not be updated");
        }
        





















    }


    public void EditSubscription(){
        /*
        We need to get access to the DB
        TODO
         */

    }


    public void ViewAccountInfo( Customer customer) throws SQLException{
        viewCustomerAccount viewAcct = new viewCustomerAccount(customer);

    }


























}
