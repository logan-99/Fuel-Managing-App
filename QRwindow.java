import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.Panel;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class QRwindow extends JFrame {

	private JPanel contentPane;
	private static String QRpath;
	public QRwindow(String path) {
		this.QRpath = path;
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					QRwindow frame = new QRwindow();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public QRwindow() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 550);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("QR Code");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblNewLabel.setBounds(265, 44, 131, 47);
		contentPane.add(lblNewLabel);
		
		JLabel lblQR = new JLabel("");
		lblQR.setIcon(new ImageIcon(QRpath));
		lblQR.setHorizontalAlignment(SwingConstants.CENTER);
		lblQR.setBackground(Color.DARK_GRAY);
		lblQR.setBounds(180, 120, 300, 300);
		contentPane.add(lblQR);
		
		JButton btnNewButton = new JButton("Back");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VehicleMainWindow newF = new VehicleMainWindow();
				VehicleMainWindow.main(null);
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnNewButton.setBounds(10, 473, 120, 30);
		contentPane.add(btnNewButton);
		
	}
}
