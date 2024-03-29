import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.sql.SQLException;

public class CustomerGUI extends JFrame {
    JMenuBar menuBar;
    JMenu Edit, Purchase, View, LogOut, cancel, Add;
    JMenuItem editName, editAddress, editAddons, editEmailAddress, membership, editPassword, editCreditCardInfo, cancelItem;
    JMenuItem viewAccountInfo, viewSubscriptions, viewCardInfo;
    boolean isSubscriber = false;
    JMenuItem AddAddons;


    JMenuItem signOut;
    Customer customer = null;


    CustomerGUI(Customer customer) {
        this.customer = customer;
        this.setSize(600, 600);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLayout(new FlowLayout());
        this.setIconImage(new ImageIcon("31-hour.png").getImage());
        this.setLocationRelativeTo(null);

        Font f = new Font("sans-serif", Font.BOLD, 20);
        UIManager.put("Menu.font", f);
        UIManager.put("MenuItem.font", f);

        try {
            BufferedImage myPicture = ImageIO.read(new File("interior.jpg"));
            JLabel picLabel = new JLabel(new ImageIcon(myPicture));
            add(picLabel);
        } catch (Exception e) {
            ;
        }


        menuBar = new JMenuBar();
        Edit = new JMenu("Edit");

        membership = new JMenuItem("Subscription");
        cancelItem = new JMenuItem("Cancel Subscription");

        try {
            DatabaseManager db = new DatabaseManager();
            int memberID = db.getMemberID(customer);
            if (memberID == -1 || !db.getMembershipStatus(memberID)) {
                Purchase = new JMenu("Purchase");
                Purchase.add(membership);
                menuBar.add(Purchase);
            } else {
                cancel = new JMenu("Subscription");
                cancel.add(cancelItem);
                menuBar.add(cancel);
                isSubscriber = true;
            }
            db.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        View = new JMenu("View");
        LogOut = new JMenu("Log out");


        Edit.add(editName = new JMenuItem("Edit Name"));
        Edit.add(editAddress = new JMenuItem("Edit Address"));
        Edit.add(editEmailAddress = new JMenuItem("Edit Email"));
        Edit.add(editPassword = new JMenuItem("Edit Password"));
        Edit.add(editCreditCardInfo = new JMenuItem("Edit Credit Card Info"));


        View.add(viewAccountInfo = new JMenuItem("View Account Info"));
        View.add(viewSubscriptions = new JMenuItem("View Subscription"));
        View.add(viewCardInfo = new JMenuItem("View Credit Card Info"));

        Add = new JMenu("Add");

        Add.add(AddAddons = new JMenuItem("Add Addons"));
        LogOut.add(signOut = new JMenuItem("Sign Out"));


        menuBar.add(Edit);
        menuBar.add(View);
        menuBar.add(LogOut);
        menuBar.add(Add);

        //menuBar.setLayout(new GridBagLayout());


        editPassword.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setEditPassword();

            }
        });

        viewCardInfo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    setViewCardInfo(customer);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }


            }
        });


        editCreditCardInfo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    setEditCreditCardInfo(customer);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }


            }
        });


        cancelItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    DatabaseManager db = new DatabaseManager();
                    db.cancelSubscription(customer);
                    db.close();
                    JOptionPane.showMessageDialog(null, "Your Subscription is canceled");
                    dispose();
                    new CustomerGUI(customer);//To refresh menu
                } catch (Exception ex) {
                }
            }
        });

        AddAddons.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(isSubscriber) {
                    try {
                        setAddAddons(customer);
                        dispose();
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                }
                else {
                    JOptionPane.showMessageDialog(null, "You must be a Subscriber to purchase addons");
                }
            }
        });


        membership.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Clicked");
                PurchasePage purchase = new PurchasePage(customer);
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

        editEmailAddress.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EditEmailAddress(customer);
            }
        });

        viewAccountInfo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    ViewAccountInfo(customer);
                } catch (SQLException exception) {
                    exception.printStackTrace();
                }
            }
        });


        viewSubscriptions.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    new ViewSubscriptionGUI(customer);
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        });
        this.setJMenuBar(menuBar);
        this.setVisible(true);
    }


    public void EditName() {
        String inputName = JOptionPane.showInputDialog("Enter new name");
        if (inputName == null || inputName.equals("")) {
            JOptionPane.showMessageDialog(null, "Invalid input");
        } else {

            //update database
            boolean completed = false;
            try {
                DatabaseManager db = new DatabaseManager();
                completed = db.updateCustomerName(customer.getId(), inputName);
                db.close();
            } catch (Exception ex) {
                System.out.println("Error connecting to the database");
            }

            if (completed) {
                JOptionPane.showMessageDialog(null, "Name successfully Updated");
            } else {
                JOptionPane.showMessageDialog(null, "There was an error updating the name");
            }
        }
    }


    public void EditAddress(Customer customer) throws SQLException {
        EditAddressPage editAddress = new EditAddressPage(customer);
    }


    public static void EditEmailAddress(Customer searchCustomer) {
        String inputEmailAddress = JOptionPane.showInputDialog("Enter new email");

        if (inputEmailAddress == null || inputEmailAddress.equals("")) {
            JOptionPane.showMessageDialog(null, "Invalid input");
        } else {

            //update email on database
            boolean completed = false;
            try {
                DatabaseManager db = new DatabaseManager();
                completed = db.updateEmail(searchCustomer.getId(), inputEmailAddress);
                db.close();
            } catch (Exception ex) {
                System.out.println("Error connecting to database");
            }

            if (completed) {
                JOptionPane.showMessageDialog(null, "Email was Successfully Updated");
                searchCustomer.setEmail(inputEmailAddress);
            } else {
                JOptionPane.showMessageDialog(null, "Email could not be updated");
            }
        }
    }

    public void EditSubscription(String newSubscription) {
        try {
            DatabaseManager db = new DatabaseManager();
            db.editSubscription(customer.getId(), newSubscription);

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Failed to Update");
        }
    }


    public void setEditPassword() {
        String inputPassword = JOptionPane.showInputDialog("Enter new password");

        if (inputPassword == null || inputPassword.equals("")) {
            JOptionPane.showMessageDialog(null, "Invalid input");
        } else {
            //update email on database
            boolean completed = false;
            try {
                DatabaseManager db = new DatabaseManager();
                completed = db.updatePassword(customer.getId(), inputPassword);
                db.close();
            } catch (Exception ex) {
                System.out.println("Error connecting to database");
            }

            if (completed) {
                JOptionPane.showMessageDialog(null, "Password was Successfully Updated");
                this.customer.setPassword(inputPassword);
            } else {
                JOptionPane.showMessageDialog(null, "Password could not be updated");
            }
        }
    }


    /*
    This code will break when the account email or the password are updated, so we need to get the new passwords if they have changed.
     */
    public void ViewAccountInfo(Customer customer) throws SQLException {

        viewCustomerAccount viewAcct = new viewCustomerAccount(customer);

    }


    public void setViewCardInfo(Customer customer) throws SQLException {

        viewCustomerCardInfo viewCard = new viewCustomerCardInfo(customer);
    }


    public void setEditCreditCardInfo(Customer customer) throws SQLException {
        CreditPage creditPage = new CreditPage(customer);
    }

    public void setAddAddons(Customer customer) throws SQLException {
        addAddon addAddons = new addAddon(customer);
    }
}