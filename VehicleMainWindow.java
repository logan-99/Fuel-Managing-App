import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class VehicleMainWindow {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VehicleMainWindow window = new VehicleMainWindow();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		
		
		
	}
	

	/**
	 * Create the application.
	 */
	public VehicleMainWindow() {
		initialize();
	}
	
	/////* My Methods And COnstructor*///////
	
	public VehicleMainWindow(String btnClicked, String type) {
		this();
		String btnresponse = btnClicked;
//		String vType = type;
		MakeWindow(btnresponse);
		MakeLables(type);
		
	}
	
	String url = "jdbc:mysql://localhost:3306/fuelmanager";
	String user_name = "root";
	String pass_word = "";
	
	private static String vehicleName;
	private static String vehicleType;
	private static String vehicleRegNumber;
	private static String qrPath;
	private static Double availableFuel;
	private static String strFuel;
	private static String strQuota;
	private static String strUsed;

	

	
	int count = 0;
	
	public Connection connect() throws SQLException {
        return DriverManager.getConnection(url, user_name, pass_word);
    }
	

	public void MakeWindow(String btnresponse) {
		String SQL1 = "Select vehicle_type,reg_number,qr_path,available_fuel FROM vehicles WHERE vehicle_name = ?";
		this.vehicleName = btnresponse;
		
        try (Connection conn = connect();
                PreparedStatement pstmt = conn.prepareStatement(SQL1,
                Statement.RETURN_GENERATED_KEYS)) {

        	pstmt.setString(1, btnresponse);
        	ResultSet rs = pstmt.executeQuery();
        	
        	rs.next();
        	this.vehicleType = rs.getString(1);
        	this.vehicleRegNumber = rs.getString(2);
        	this.qrPath = rs.getString(3);
        	this.availableFuel = rs.getDouble(4);
        	
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        strFuel = String.valueOf(availableFuel);
	}
	
	
	private static String amountNeed;
	private String SQL;
	public void MakeLables(String type) {
	final DecimalFormat dff = new DecimalFormat("0.00");	
		
		System.out.println(type);
		if(type.equals("Car")) { 
			SQL = "Select car,price FROM quotas;"; 
		} 
		if(type.equals("Three Wheel")) { 
			SQL = "Select three_wheel,price FROM quotas;"; 
		} 
		if(type.equals("Bike")) { 
			SQL = "Select bike,price FROM quotas;"; 
		}
		 
        try (Connection conn = connect();
	            Statement stmt = conn.createStatement()) {

	        ResultSet rs = stmt.executeQuery(SQL);
	        rs.next();
	        strQuota = Double.toString(rs.getDouble(1));
	        strUsed = dff.format(rs.getDouble(1)-availableFuel);
	        
	        amountNeed = Double.toString(rs.getDouble(2)*availableFuel);
	        
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
	}
	
	
	public void Pumped(String litersPumped) {
		double litersPumpedDouble = Double.parseDouble(litersPumped);
		double remaining = availableFuel - litersPumpedDouble;
		
		String SQL3 = "UPDATE vehicles SET available_fuel= ? WHERE reg_number = ?";
		try (Connection conn = connect();
                PreparedStatement pstmt = conn.prepareStatement(SQL3,
                Statement.RETURN_GENERATED_KEYS)) {

        	pstmt.setDouble(1, remaining);;
        	pstmt.setString(2, vehicleRegNumber);
        	
        	int affectedRows = pstmt.executeUpdate();
            // check the affected rows 
            if (affectedRows > 0) {
            	System.out.println("Successfully Updated Pumped Liters..");
            	JOptionPane.showMessageDialog(null, "Successfull!");
            	VehicleMainWindow newF = new VehicleMainWindow(vehicleName,vehicleType);
				VehicleMainWindow.main(null);
            }
        	
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
	}
	
	public void RemoveVehicle() {
		String SQL4 = "DELETE FROM vehicles WHERE reg_number = ?";
		int userresponse = JOptionPane.showConfirmDialog(null, "Do you want to delete this vehicle?", "Deleting Confirmation", 1);
		//System.out.println(userresponse);
		if(userresponse == 0) {
			try (Connection conn = connect();
	                PreparedStatement pstmt = conn.prepareStatement(SQL4,
	                Statement.RETURN_GENERATED_KEYS)) {
	
	        	pstmt.setString(1, vehicleRegNumber);;
	        	
	        	int affectedRows = pstmt.executeUpdate();
	            // check the affected rows 
	            if (affectedRows > 0) {
	            	System.out.println("Successfully Removed the vehicle..");
	            	JOptionPane.showMessageDialog(null, "Successfully Removed the Vehicle..");
	            	SelectVehicleWindow newF = new SelectVehicleWindow();
	            	SelectVehicleWindow.main(null);
	            }
			} catch (SQLException ex) {
	            System.out.println(ex.getMessage());
	        }
		}
	}
	

	
	
	
	
	

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 700, 550);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblRegNumber = new JLabel("");
		lblRegNumber.setHorizontalAlignment(SwingConstants.CENTER);
		lblRegNumber.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblRegNumber.setBounds(255, 35, 158, 42);
		frame.getContentPane().add(lblRegNumber);
		
		JLabel lblNewLabel_1 = new JLabel("You have");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(296, 107, 85, 29);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2_1 = new JLabel("L");
		lblNewLabel_2_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_2_1.setForeground(new Color(0, 128, 0));
		lblNewLabel_2_1.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblNewLabel_2_1.setBounds(376, 146, 23, 29);
		frame.getContentPane().add(lblNewLabel_2_1);
		
		JButton btnNewButton = new JButton("View QR");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				QRwindow newF = new QRwindow(qrPath);
				QRwindow.main(null);
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnNewButton.setBounds(138, 236, 117, 29);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnCanI = new JButton("Can I?");
		btnCanI.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CanIWindow newF = new CanIWindow();
				CanIWindow.main(null);
			}
		});
		btnCanI.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnCanI.setBounds(271, 236, 117, 29);
		frame.getContentPane().add(btnCanI);
		
		JButton btnPumped = new JButton("Pumped?");
		btnPumped.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Pumped(JOptionPane.showInputDialog("How many liters pumped?"));
			}
		});
		btnPumped.setForeground(new Color(139, 0, 0));
		btnPumped.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnPumped.setBounds(406, 236, 117, 29);
		frame.getContentPane().add(btnPumped);
		
		JLabel lblNewLabel_3 = new JLabel("Your weekly quota (Liters):");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_3.setBounds(196, 308, 192, 17);
		frame.getContentPane().add(lblNewLabel_3);
		
		JLabel lblNewLabel_3_1 = new JLabel("You have used (Liters):");
		lblNewLabel_3_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_3_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_3_1.setBounds(196, 334, 169, 17);
		frame.getContentPane().add(lblNewLabel_3_1);
		
		JLabel lblWeeklyQuota = new JLabel("");
		lblWeeklyQuota.setHorizontalAlignment(SwingConstants.RIGHT);
		lblWeeklyQuota.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblWeeklyQuota.setBounds(409, 308, 76, 17);
		frame.getContentPane().add(lblWeeklyQuota);
		
		JLabel lblUsed = new JLabel("");
		lblUsed.setHorizontalAlignment(SwingConstants.RIGHT);
		lblUsed.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblUsed.setBounds(409, 334, 76, 17);
		frame.getContentPane().add(lblUsed);
		
		JLabel lblNewLabel_3_1_1 = new JLabel("Amount you need to refill (Rs.) :");
		lblNewLabel_3_1_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_3_1_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_3_1_1.setBounds(196, 361, 209, 17);
		frame.getContentPane().add(lblNewLabel_3_1_1);
		
		JLabel lblAmountNeed = new JLabel("");
		lblAmountNeed.setHorizontalAlignment(SwingConstants.RIGHT);
		lblAmountNeed.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblAmountNeed.setBounds(409, 361, 76, 17);
		frame.getContentPane().add(lblAmountNeed);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SelectVehicleWindow newF = new SelectVehicleWindow();
				SelectVehicleWindow.main(null);
			}
		});
		btnBack.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnBack.setBounds(10, 474, 120, 30);
		frame.getContentPane().add(btnBack);
		
		JButton btnConfigQuota = new JButton("Config Quota");
		btnConfigQuota.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ConfigQuotaWindow newF = new ConfigQuotaWindow();
				ConfigQuotaWindow.main(null);
			}
		});
		btnConfigQuota.setForeground(new Color(0, 0, 0));
		btnConfigQuota.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnConfigQuota.setBounds(524, 474, 152, 29);
		frame.getContentPane().add(btnConfigQuota);
		
		lblRegNumber.setText(this.vehicleRegNumber);
		
		JLabel lblFuelRemamining = new JLabel("");
		lblFuelRemamining.setHorizontalAlignment(SwingConstants.RIGHT);
		lblFuelRemamining.setForeground(new Color(0, 128, 0));
		lblFuelRemamining.setFont(new Font("Tahoma", Font.BOLD, 40));
		lblFuelRemamining.setBounds(210, 133, 162, 42);
		frame.getContentPane().add(lblFuelRemamining);
		//System.out.println(vehicleType);
		lblWeeklyQuota.setText(strQuota);
		lblUsed.setText(strUsed);
		lblAmountNeed.setText(amountNeed);
		
		JLabel lblAmountNeedMain = new JLabel("5050.00");
		lblAmountNeedMain.setHorizontalAlignment(SwingConstants.RIGHT);
		lblAmountNeedMain.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblAmountNeedMain.setBounds(254, 182, 84, 17);
		frame.getContentPane().add(lblAmountNeedMain);
		
		JLabel lblRupees = new JLabel("Rupees");
		lblRupees.setHorizontalAlignment(SwingConstants.LEFT);
		lblRupees.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblRupees.setBounds(343, 183, 84, 17);
		frame.getContentPane().add(lblRupees);
		
		lblAmountNeedMain.setText(amountNeed);
		
		JLabel lblNewLabel = new JLabel("Remove this vehicle");
		lblNewLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				RemoveVehicle();
			}
		});
		lblNewLabel.setForeground(new Color(178, 34, 34));
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel.setBounds(269, 486, 128, 13);
		frame.getContentPane().add(lblNewLabel);
		
		lblFuelRemamining.setText(strFuel);
	}
}



/////Add Quota Column to the datatable..Show the labale in main window
/////Do respective changes in the code











