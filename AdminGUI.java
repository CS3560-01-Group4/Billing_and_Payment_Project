import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;

public class AdminGUI extends JFrame {

    private final JMenuBar menuBar;
    private final JMenuItem membership;
    private final JMenu Edit;

    public AdminGUI() {
        this.setSize(600, 600);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLayout(new FlowLayout());
        this.setIconImage(new ImageIcon("31-hour.png").getImage());
        this.setLocationRelativeTo(null);
        this.setVisible(true);

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
        membership = new JMenuItem("Membership Price");
        Edit.add(membership);
        menuBar.add(Edit);
        this.setJMenuBar(menuBar);


        membership.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                new UpdatePricePage();
            }
        });
    }


}
