import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.SwingConstants;

public class CanIWindow extends JFrame {

	private JPanel contentPane;
	private JTextField textRemainingFuel;
	private JTextField textBikeNo;
	private JTextField textThreeNo;
	private JTextField textCarNo;
	private JButton btnNewButton;
	private JButton btnCheck;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CanIWindow frame = new CanIWindow();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	String url = "jdbc:mysql://localhost:3306/fuelmanager";
	String user_name = "root";
	String pass_word = "";
	
	public Connection connect() throws SQLException {
        return DriverManager.getConnection(url, user_name, pass_word);
    }
	
	private double carQ;
	private double bikeQ;
	private double threeQ;
	public static String result;
	public static String greetings;
	private JLabel lblLarge;
	private JLabel lblSmall;
	private JLabel lblNewLabel_2;
	private JLabel lblLarge1;
	private JLabel lblSmall1;
	private JButton btnBack;
	private JLabel lblNewLabel_1_1_1_2;
	
	public void Check() {
		Double fuel = Double.parseDouble(textRemainingFuel.getText());
		int bikes = Integer.parseInt(textBikeNo.getText());
		int threes = Integer.parseInt(textThreeNo.getText());
		int cars = Integer.parseInt(textCarNo.getText());
		
		String SQL = "Select * FROM quotas";
		
		try (Connection conn = connect();
	            Statement stmt = conn.createStatement()) {

	        ResultSet rs = stmt.executeQuery(SQL);
	        rs.next();
	        bikeQ = rs.getDouble(1);
	        threeQ = rs.getDouble(2);
	        carQ = rs.getDouble(3);
	        
	        //amountNeed = Double.toString(rs.getDouble(2)*availableFuel);
	        
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
		
		if ((bikes*bikeQ + threes*threeQ + cars*carQ)<(fuel*0.9)) {
			//System.out.println("Can get Fuel..");
			result = "You Can";
			greetings = "Congratulations!";
			lblLarge.setText(greetings);
			lblSmall.setText(result);
		}
		else {
			//System.out.println("Can not get Fuel..");
			result = "You may not get Fuel";
			greetings = "Sorry!";
			lblLarge1.setText(greetings);
			lblSmall1.setText(result);
		}
		
		
	}

	/**
	 * Create the frame.
	 */
	public CanIWindow() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 550);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Can I Pump?");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel.setBounds(259, 44, 135, 32);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Fuel Capasity remaining at the station :");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(126, 148, 279, 19);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("No of bikes before you :");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1_1.setBounds(126, 201, 263, 19);
		contentPane.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("No of three wheels before you :");
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1_1_1.setBounds(126, 230, 263, 19);
		contentPane.add(lblNewLabel_1_1_1);
		
		JLabel lblNewLabel_1_1_1_1 = new JLabel("No of cars before you :");
		lblNewLabel_1_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1_1_1_1.setBounds(126, 259, 263, 19);
		contentPane.add(lblNewLabel_1_1_1_1);
		
		textRemainingFuel = new JTextField();
		textRemainingFuel.setBounds(405, 150, 149, 19);
		contentPane.add(textRemainingFuel);
		textRemainingFuel.setColumns(10);
		
		textBikeNo = new JTextField();
		textBikeNo.setColumns(10);
		textBikeNo.setBounds(405, 201, 149, 19);
		contentPane.add(textBikeNo);
		
		textThreeNo = new JTextField();
		textThreeNo.setColumns(10);
		textThreeNo.setBounds(405, 230, 149, 19);
		contentPane.add(textThreeNo);
		
		textCarNo = new JTextField();
		textCarNo.setColumns(10);
		textCarNo.setBounds(405, 259, 149, 19);
		contentPane.add(textCarNo);
		
		btnNewButton = new JButton("Clear");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textRemainingFuel.setText(null);
				textCarNo.setText(null);
				textBikeNo.setText(null);
				textThreeNo.setText(null);
				lblLarge.setText(null);
				lblSmall.setText(null);
				lblLarge1.setText(null);
				lblSmall1.setText(null);
				
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton.setBounds(203, 335, 99, 25);
		contentPane.add(btnNewButton);
		
		btnCheck = new JButton("Check");
		btnCheck.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Check();
			}
		});
		btnCheck.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnCheck.setBounds(344, 335, 99, 25);
		contentPane.add(btnCheck);
		
		lblLarge = new JLabel("");
		lblLarge.setHorizontalAlignment(SwingConstants.CENTER);
		lblLarge.setForeground(new Color(0, 128, 0));
		lblLarge.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblLarge.setBounds(136, 370, 400, 51);
		contentPane.add(lblLarge);
		
		lblSmall = new JLabel("");
		lblSmall.setHorizontalAlignment(SwingConstants.CENTER);
		lblSmall.setForeground(new Color(0, 128, 0));
		lblSmall.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblSmall.setBounds(112, 404, 444, 51);
		contentPane.add(lblSmall);
		
		lblNewLabel_2 = new JLabel("Note: This is a prediction only. We cnnot gurantee whether that you can get fuel or not.");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_2.setForeground(new Color(165, 42, 42));
		lblNewLabel_2.setBounds(213, 490, 473, 13);
		contentPane.add(lblNewLabel_2);
		
		lblLarge1 = new JLabel("");
		lblLarge1.setHorizontalAlignment(SwingConstants.CENTER);
		lblLarge1.setForeground(new Color(220, 20, 60));
		lblLarge1.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblLarge1.setBounds(79, 370, 506, 51);
		contentPane.add(lblLarge1);
		
		lblSmall1 = new JLabel("");
		lblSmall1.setHorizontalAlignment(SwingConstants.CENTER);
		lblSmall1.setForeground(new Color(255, 0, 0));
		lblSmall1.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblSmall1.setBounds(79, 404, 506, 51);
		contentPane.add(lblSmall1);
		
		btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VehicleMainWindow newF = new VehicleMainWindow();
				VehicleMainWindow.main(null);
			}
		});
		btnBack.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnBack.setBounds(10, 479, 120, 30);
		contentPane.add(btnBack);
		
		lblNewLabel_1_1_1_2 = new JLabel("Liters");
		lblNewLabel_1_1_1_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1_1_1_2.setBounds(556, 150, 86, 19);
		contentPane.add(lblNewLabel_1_1_1_2);
		
	}
}
