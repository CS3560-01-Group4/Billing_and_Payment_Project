import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;


/**
 * @authors Cristofer Vargas, , , 
 *
 */
public class UI {
	public static void main(String args[]) {
		JFrame frame = new JFrame("31-Hour Fitness");//main frame
		frame.setIconImage(new ImageIcon("31-hour.png").getImage());//set the frame image
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//close frame when the X is clicked
		frame.setSize(1000, 800);//set the size of the frame
		frame.setVisible(true);//make frame visible
	}
}
