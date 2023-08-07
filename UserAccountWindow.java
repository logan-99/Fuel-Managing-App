import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class UserAccountWindow {

	private JFrame frame;
	private JTextField textName;
	private JTextField textUsername;
	private JTextField textPassword;
	private JTextField textPhone;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserAccountWindow window = new UserAccountWindow();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	/** My methods ***/
	public void CreatUser(String name,String username,String password,String phone) {
		try {
			UserAccount user = new UserAccount(name, username, password, phone);
			JOptionPane.showMessageDialog(null, "Thank you for registering with us. Please login to your account.");
		}
		catch(Exception ex) {
			JOptionPane.showMessageDialog(null, "Error has occured.. Please try again later");
		}
		finally {
			LoginWindow newF = new LoginWindow();
			LoginWindow.main(null);
		}
	}

	/**
	 * Create the application.
	 */
	public UserAccountWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 700, 550);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Register User");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 35));
		lblNewLabel.setBounds(216, 68, 239, 62);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Name :");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1.setBounds(160, 194, 82, 24);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Username :");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1_1.setBounds(160, 228, 102, 24);
		frame.getContentPane().add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("Password :");
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1_2.setBounds(160, 262, 102, 24);
		frame.getContentPane().add(lblNewLabel_1_2);
		
		JLabel lblNewLabel_1_3 = new JLabel("Tel Number:");
		lblNewLabel_1_3.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1_3.setBounds(160, 296, 139, 24);
		frame.getContentPane().add(lblNewLabel_1_3);
		
		textName = new JTextField();
		textName.setToolTipText("Kalana Perera");
		textName.setBounds(300, 194, 225, 26);
		frame.getContentPane().add(textName);
		textName.setColumns(10);
		
		textUsername = new JTextField();
		textUsername.setToolTipText("Kalana");
		textUsername.setColumns(10);
		textUsername.setBounds(300, 226, 225, 26);
		frame.getContentPane().add(textUsername);
		
		textPassword = new JTextField();
		textPassword.setToolTipText("AvS123!");
		textPassword.setColumns(10);
		textPassword.setBounds(300, 262, 225, 26);
		frame.getContentPane().add(textPassword);
		
		textPhone = new JTextField();
		textPhone.setToolTipText("0717856987");
		textPhone.setColumns(10);
		textPhone.setBounds(300, 296, 225, 26);
		frame.getContentPane().add(textPhone);
		
		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textName.setText("");
				textUsername.setText("");
				textPassword.setText("");
				textPhone.setText("");
			}
		});
		btnClear.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnClear.setBounds(173, 392, 130, 40);
		frame.getContentPane().add(btnClear);
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CreatUser(textName.getText(),textUsername.getText(),textPassword.getText(),textPhone.getText());
			}
		});
		btnSave.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnSave.setBounds(347, 392, 130, 40);
		frame.getContentPane().add(btnSave);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginWindow newF = new LoginWindow();
				LoginWindow.main(null);
			}
		});
		btnBack.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnBack.setBounds(10, 473, 120, 30);
		frame.getContentPane().add(btnBack);
	}
}
