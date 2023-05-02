import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Statement;


/**
 * @authors Cristofer Vargas, , , 
 *
 */
public class UI{
	public static void main(String args[]) {
		UI start = new UI();
	}
	UI(){


		/*
		Begin construction of the main panel the user will enter upon application startup.
		 */
		JFrame frame = new JFrame("31-Hour Fitness");//main frame
		frame.setIconImage(new ImageIcon("31-hour.png").getImage());//set the frame image
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//close frame when the X is clicked
		frame.setSize(600, 600);//set the size of the frame
		frame.setResizable(false);
		frame.setLayout(new GridLayout(2,1));
		frame.add(new JLabel("Welcome. Please select which type of user you are:", JLabel.CENTER), BorderLayout.NORTH);



		JPanel centeredPanel = new JPanel();







		/*
		Create Drop Down styled Menu for user selection
		 */
		String[] userTypes = {"Customer", "Salesman", "System Admin"};
		JComboBox userComboBox = new JComboBox(userTypes);

		/*
		Add action listening to the JComboBox userComboBox
		 */


		userComboBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JComboBox comboBox = (JComboBox) e.getSource();
				String s =  (String) comboBox.getSelectedItem();
				if(s.equals(userTypes[0])){
					//swap to Customer Screen.
					frame.dispose();
					CSLoginPage loginPage = new CSLoginPage();

					System.out.println("C");
				}
				else if(s.equals(userTypes[1])){
					//Swap to SalesPerson screen
					frame.dispose();
					SalesLogin loginPage = new SalesLogin();
				}
				else{
					//swap to Admin screen.
					frame.dispose();
					SysAdminPageLogin loginPage = new SysAdminPageLogin();
				}

			}
		});
		centeredPanel.add(userComboBox);
		frame.add(centeredPanel, BorderLayout.CENTER);
		frame.setVisible(true);//make frame visible
	}
}
