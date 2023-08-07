import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LoginWindow {

	private JFrame frame;
	private JTextField textUsername;
	private JTextField textPassword;
	private String usern = UserAccount.getUsername();
	private String pw = UserAccount.getPassword();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginWindow window = new LoginWindow();
					window.frame.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	//////**My Methods**/////
	 public void Login(String uname, String passw) {
		 if(uname.equals(usern) && passw.equals(pw)) {
			 JOptionPane.showMessageDialog(null, "Welcome to the Fuel Manager!");
			 SelectVehicleWindow newF = new SelectVehicleWindow();
			 SelectVehicleWindow.main(null);
		 }
		 else {
			 System.out.println("Incorrect username or password!!!");
			 JOptionPane.showMessageDialog(null, "Invalid Username or Password!");
		 }
	 }
	
	

	/**
	 * Create the application.
	 */
	public LoginWindow() {
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
		
		JLabel lblNewLabel = new JLabel("Login");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 35));
		lblNewLabel.setBounds(285, 83, 104, 67);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Username :");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1.setBounds(188, 216, 113, 25);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Password :");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1_1.setBounds(188, 251, 113, 25);
		frame.getContentPane().add(lblNewLabel_1_1);
		
		textUsername = new JTextField();
		textUsername.setFont(new Font("Tahoma", Font.PLAIN, 20));
		textUsername.setBounds(309, 216, 190, 26);
		frame.getContentPane().add(textUsername);
		textUsername.setColumns(10);
		
		textPassword = new JTextField();
		textPassword.setFont(new Font("Tahoma", Font.PLAIN, 20));
		textPassword.setColumns(10);
		textPassword.setBounds(309, 251, 190, 26);
		frame.getContentPane().add(textPassword);
		
		JButton btnNewButton = new JButton("Clear");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textUsername.setText("");
				textPassword.setText("");
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 10));
		btnNewButton.setBounds(418, 287, 81, 15);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Login");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Login(textUsername.getText(), textPassword.getText());
			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnNewButton_1.setBounds(285, 359, 104, 32);
		frame.getContentPane().add(btnNewButton_1);
		
		JButton btnRegister = new JButton("Register");
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UserAccountWindow newF = new UserAccountWindow();
				UserAccountWindow.main(null);
			}
		});
		btnRegister.setFont(new Font("Tahoma", Font.PLAIN, 10));
		btnRegister.setBounds(331, 287, 81, 15);
		frame.getContentPane().add(btnRegister);
	}

}
