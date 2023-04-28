import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class EditAddressPage extends JFrame {
    String  inputAddress, inputCity, inputZip, inputState;
    private JPanel EditAddressPage;
    private JTextField postalCode;
    private JTextField city;
    private JTextField state;
    private JTextField address;

    EditAddressPage(Customer customer){
        this.setContentPane(EditAddressPage);
        this.setTitle("Edit Address");
        this.setIconImage(new ImageIcon("31-hour.png").getImage());
        this.setSize(600,600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        System.out.print(customer.getAddress().getStreetName());

        address.setText(customer.getAddress().getStreetName());
        city.setText(customer.getAddress().getCity());
        state.setText(customer.getAddress().getState());
        postalCode.setText(customer.getAddress().getZipCode());

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
            JOptionPane.showMessageDialog(EditAddressPage, "Invalid input");
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
}
