import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;

public class Station extends JPanel {

	/**
	 * Create the panel.
	 */
	JLabel lblStationPassagers = new JLabel("");
	
	public Station() {
		setLayout(null);
		
		lblStationPassagers.setHorizontalAlignment(SwingConstants.CENTER);
		lblStationPassagers.setBounds(40, 6, 60, 16);
		add(lblStationPassagers);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(System.getProperty("user.dir") + "/img/station.png"));
		lblNewLabel_1.setBounds(40, 24, 60, 60);
		add(lblNewLabel_1);

	}
}
