import javax.swing.*;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

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
                EditName();
            }
        });
        editAddress.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EditAddress();
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
                ViewAccountInfo();

            }
        });





        this.setJMenuBar(menuBar);
        this.setVisible(true);















    }


    public void EditName(){
        String inputName;
        JFrame editNameWindow = new JFrame();
        editNameWindow.setSize(600,600);
        editNameWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        editNameWindow.setLayout(new FlowLayout());
        editNameWindow.setIconImage(new ImageIcon("31-hour.png").getImage());


        /**
         * Populate the textfields with information from the DB
         */
        JTextField nameEdit = new JTextField();
        JTextField oldName = new JTextField();

        JLabel nameLabel = new JLabel("Enter New Name");
        JButton confirmButton = new JButton("Confirm");


        editNameWindow.add(nameLabel);
        editNameWindow.add(nameEdit);
        editNameWindow.add(confirmButton);

        inputName = nameEdit.getText();


        if (inputName.equals("")){
            nameEdit.setBackground(new Color(255, 102, 102));
            JOptionPane.showMessageDialog(editNameWindow, "Invalid input");

        }

        /*
        Write code to beam result to the DB.
         */











        
    }


    public void EditAddress(){
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
        /*
        Get the information from the customer DB and display it to the customer

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






    }


    public void EditEmailAddress(){
        String inputEmailAddress;
        JButton ConfirmButton = new JButton("Confirm");

        JFrame editEmailAddressWindow = new JFrame();
        editEmailAddressWindow.setSize(600,600);
        editEmailAddressWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        editEmailAddressWindow.setLayout(new FlowLayout());
        editEmailAddressWindow.setIconImage(new ImageIcon("31-hour.png").getImage());

        JTextField newEmailAddress = new JTextField();
        JTextField oldEmailAddress = new JTextField();

        JLabel emailOld = new JLabel("Current Email Address");
        JLabel emailNew = new JLabel("New Email Address");


        inputEmailAddress = newEmailAddress.getText();
        if(inputEmailAddress.equals("")){
            newEmailAddress.setBackground(new Color(255, 102, 102));
            JOptionPane.showMessageDialog(editEmailAddressWindow, "Invalid input");
        }


        /**
         * Write code to beam the new email address to the DB.
         */























    }


    public void EditSubscription(){
        /*
        We need to get access to the DB
         */

    }


    public void ViewAccountInfo(){
        JFrame editEmailAddressWindow = new JFrame();
        JButton confirmButton = new JButton("Confirm Button");
        editEmailAddressWindow.setTitle("View Account Info");
        editEmailAddressWindow.setSize(600,600);
        editEmailAddressWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        editEmailAddressWindow.setLayout(new FlowLayout());
        editEmailAddressWindow.setIconImage(new ImageIcon("31-hour.png").getImage());


        JTextField address = new JTextField();
        JTextField city = new JTextField();
        JTextField state = new JTextField();
        JTextField postalCode = new JTextField();


        JLabel addressLabel = new JLabel("Address: ");
        JLabel cityLabel = new JLabel("City: ");
        JLabel stateLabel = new JLabel("State: ");
        JLabel postalLabel = new JLabel("Postal Code: ");

        address.setEditable(false);
        city.setEditable(false);
        state.setEditable(false);
        postalCode.setEditable(false);






















    }





}
