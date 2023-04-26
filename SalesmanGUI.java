import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class SalesmanGUI extends JFrame {
    JMenuBar menuBar;
    JMenu Edit, View, LogOut;
    JMenuItem  editCustomerAddress, editCustomerSubscription, editEmailAddress;
    JMenuItem viewAccountInfo, viewSubscriptions;

    JMenuItem signOut;

    SalesmanGUI(){
        this.setSize(600,600);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setIconImage(new ImageIcon("31-hour.png").getImage());
        this.setTitle("System Admin Main Page");
        this.setLayout(new FlowLayout());

        menuBar = new JMenuBar();
        Edit = new JMenu("Edit");
        View = new JMenu("View");
        LogOut = new JMenu("Sign out");

        editCustomerAddress = new JMenuItem("Customer Address");
        editCustomerSubscription = new JMenuItem("Customer Subscription");
        editEmailAddress = new JMenuItem("Customer Email Address");

        viewAccountInfo = new JMenuItem("Account Info");
        viewSubscriptions = new JMenuItem("Subscriptions");
        signOut = new JMenuItem("Sign out");

        Edit.add(editCustomerAddress);
        Edit.add(editCustomerSubscription);
        Edit.add(editEmailAddress);

        View.add(viewAccountInfo);
        View.add(viewSubscriptions);

        LogOut.add(signOut);

        menuBar.add(Edit);
        menuBar.add(View);
        menuBar.add(signOut);

        this.setJMenuBar(menuBar);
        this.setVisible(true);

    }


    public void setEditCustomerAddress(){

        String  inputAddress, inputCity, inputZip, inputState;

        JFrame editAddressWindow = new JFrame();
        editAddressWindow.setSize(600,600);
        editAddressWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        editAddressWindow.setLayout(new FlowLayout());
        editAddressWindow.setIconImage(new ImageIcon("31-hour.png").getImage());


        JTextField address = new JTextField();
        JTextField city = new JTextField();
        JTextField state = new JTextField();
        JTextField postalCode = new JTextField();

        JButton SearchButton = new JButton("Search");

        /*
        Get the information from the customer DB and display it to the customer
        customer.getAddress();
        TODO
         */

        address.setText("Customer Address");
        city.setText("Customer City");
        state.setText("Customer State");
        postalCode.setText("Customer Postal Code");

        inputAddress = address.getText();
        inputCity = city.getText();
        inputZip = postalCode.getText();
        inputState = state.getText();

       /*
       Input validation for editing customer credentials
        */

        if(inputAddress.equals("")){
            address.setBackground(new Color(255, 102, 102));

        } else if (inputCity.equals("")) {
            city.setBackground(new Color(255, 102, 102));

        }
        else if (inputZip.equals("")){
            postalCode.setBackground(new Color(255, 102, 102));
        } else if (inputState.equals("")) {
            state.setBackground(new Color(255, 102, 102));

        }

        if(inputAddress.equals("") || inputZip.equals("") || inputCity.equals("") ||inputState.equals("") ){
            JOptionPane.showMessageDialog(editAddressWindow, "Invalid input");
        }

        state.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if(Character.isDigit(c)){
                    e.consume();
                }
                if(2 == state.getText().length()){
                    e.consume();
                }

            }
        });


        postalCode.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if(!Character.isDigit(c)){
                    e.consume();
                }

                if(5 == postalCode.getText().length()){
                    e.consume();
                }

            }
        });
        /*

        Add code to push changes to the DB.


         */


    }

    public void setEditCustomerSubscription(){
        /*
        Update after we add DB functions to get the required info
         */

    }

    public void setEditEmailAddress(){
        String inputEmailAddress;
        JButton ConfirmButton = new JButton("Confirm");
        JButton SearchButton = new JButton("Search");

        JFrame editEmailAddressWindow = new JFrame();
        editEmailAddressWindow.setSize(600,600);
        editEmailAddressWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        editEmailAddressWindow.setLayout(new FlowLayout());
        editEmailAddressWindow.setIconImage(new ImageIcon("31-hour.png").getImage());

        JTextField CustomerID = new JTextField();
        JLabel customerID = new JLabel("Customer ID:");

        SearchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                /*
                Add code to search for customer account
                 */
            }
        });




        JTextField newEmailAddress = new JTextField();
        JTextField oldEmailAddress = new JTextField();

        JLabel emailOld = new JLabel("Current Email Address");
        JLabel emailNew = new JLabel("New Email Address");


        inputEmailAddress = newEmailAddress.getText();
        if(inputEmailAddress.equals("")){
            newEmailAddress.setBackground(new Color(255, 102, 102));
            JOptionPane.showMessageDialog(editEmailAddressWindow, "Invalid input");
        }


        //update email on database
        boolean completed = false;
        try {
            DatabaseManager db = new DatabaseManager("localhost",3306,"gymmembership","root","sqlingurmom");
//            completed = db.updateEmail(customer.getID(), inputEmailAddress);
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


}
