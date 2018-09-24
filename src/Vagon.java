import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;

public class Vagon extends JPanel {

	/**
	 * Create the panel.
	 */
	JLabel lblNpassageiros = new JLabel("");
	
	public Vagon() {
		setLayout(null);
		lblNpassageiros.setHorizontalAlignment(SwingConstants.CENTER);
		
		lblNpassageiros.setBounds(4, 9, 32, 16);
		add(lblNpassageiros);
		
		JLabel lblImgvagon = new JLabel("");
		lblImgvagon.setBounds(0, 0, 100, 38);
		lblImgvagon.setIcon(new ImageIcon(System.getProperty("user.dir") + "/img/vagon.png"));
		add(lblImgvagon);

	}

}
