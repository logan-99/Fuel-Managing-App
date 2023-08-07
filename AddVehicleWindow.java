import java.awt.EventQueue;

import java.sql.*;


import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.io.File;
import javax.swing.JFileChooser;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JRadioButton;
import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.Enumeration;
import java.awt.event.ActionEvent;



public class AddVehicleWindow {

	private JFrame frame;
	private JTextField textVehicleName;
	private JTextField textRegNumber;
	private JTextField textQrPath;
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		/*
		 * try { String url = "jdbc:mysql://localhost:3306/fuelmanager"; String
		 * user_name = "root"; String pass_word = "";
		 * 
		 * Class.forName("com.mysql.jdbc.Driver"); Connection con =
		 * DriverManager.getConnection(url,user_name,pass_word); } catch (Exception e) {
		 * e.printStackTrace(); }
		 */
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddVehicleWindow window = new AddVehicleWindow();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	///////* My Classes */////
	public class Vehicle {
		String vehicleName;
		String vehicleType;
		String regNumber;
		//String qrCode;
		String qrPath;
		Double availableFuel;
		
		
		public String getVehicleName() {
			return vehicleName;
		}
		
		
		public String getRegNumber() {
			return regNumber;
		}
		
		public String getQrPath() {
			return qrPath;
		}
		
		public Double getAvailabelFuel() {
			return availableFuel;
		}
		
		
		
	}
	
	public class Bike extends Vehicle{
		double bikeQuota = 4;
		
		public double getQuota() {
			return bikeQuota;
		}
		
		public Bike(String vehicleName, String type, String regNum, String qrPath, Double availableFuel) {
			this.vehicleName = vehicleName;
			this.vehicleType = type;
			this.regNumber = regNum;
			this.qrPath = qrPath;
			this.availableFuel = availableFuel;
			System.out.println("Bike Constructer Ran...");
		}
	}
	
	public class ThreeWheel extends Vehicle{
		double threewheelQuota = 5;
		
		public double getQuota() {
			return threewheelQuota;
		}
		
		public ThreeWheel(String vehicleName, String type, String regNum, String qrPath, Double availableFuel) {
			this.vehicleName = vehicleName;
			this.vehicleType = type;
			this.regNumber = regNum;
			this.qrPath = qrPath;
			this.availableFuel = availableFuel;
			System.out.println("ThreeWheel Constructer Ran...");
		}
	}
	
	public class Car extends Vehicle{
		double carQuota = 20;
		
		public double getQuota() {
			return carQuota;
		}
		
		public Car(String vehicleName, String type, String regNum, String qrPath, Double availableFuel) {
			this.vehicleName = vehicleName;
			this.vehicleType = type;
			this.regNumber = regNum;
			this.qrPath = qrPath;
			this.availableFuel = availableFuel;
			System.out.println("Car Constructer Ran...");
		}
	}

	///////* My methods */////
	
	String url = "jdbc:mysql://localhost:3306/fuelmanager";
	String user_name = "root";
	String pass_word = "";
	private JTextField textAvailableFuel;
	
	public Connection connect() throws SQLException {
        return DriverManager.getConnection(url, user_name, pass_word);
    }
	
	public void insertBike(Bike bike) {
        String SQL = "INSERT INTO vehicles(vehicle_name,vehicle_type,reg_number,qr_path,available_fuel)"
        		+ "VALUES(?,?,?,?,?)";

        try (Connection conn = connect();
                PreparedStatement pstmt = conn.prepareStatement(SQL,
                Statement.RETURN_GENERATED_KEYS)) {

        	pstmt.setString(1, bike.getVehicleName());
            pstmt.setString(2, "Bike");
            pstmt.setString(3, bike.getRegNumber());
            pstmt.setString(4, bike.getQrPath());
            pstmt.setDouble(5, bike.getAvailabelFuel());

            int affectedRows = pstmt.executeUpdate();
            // check the affected rows 
            if (affectedRows > 0) {
            	System.out.println("Successfully Inserted the row..");
            	JOptionPane.showMessageDialog(null, "Successfully added the vehicle!");
            	SelectVehicleWindow newF = new SelectVehicleWindow();
				SelectVehicleWindow.main(null);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }
	
	public void insertThreeWheel(ThreeWheel threewheel) {
        String SQL = "INSERT INTO vehicles(vehicle_name,vehicle_type,reg_number,qr_path, available_fuel)"
        		+ "VALUES(?,?,?,?,?)";

        try (Connection conn = connect();
                PreparedStatement pstmt = conn.prepareStatement(SQL,
                Statement.RETURN_GENERATED_KEYS)) {

        	pstmt.setString(1, threewheel.getVehicleName());
            pstmt.setString(2, "Three Wheel");
            pstmt.setString(3, threewheel.getRegNumber());
            pstmt.setString(4, threewheel.getQrPath());
            pstmt.setDouble(5, threewheel.getAvailabelFuel());
            
            int affectedRows = pstmt.executeUpdate();
            // check the affected rows 
            if (affectedRows > 0) {
            	System.out.println("Successfully Inserted the row..");
            	JOptionPane.showMessageDialog(null, "Successfully added the vehicle!");
            	SelectVehicleWindow newF = new SelectVehicleWindow();
				SelectVehicleWindow.main(null);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }
	
	public void insertCar(Car car) {
        String SQL = "INSERT INTO vehicles(vehicle_name,vehicle_type,reg_number,qr_path, available_fuel)"
        		+ "VALUES(?,?,?,?,?)";

        try (Connection conn = connect();
                PreparedStatement pstmt = conn.prepareStatement(SQL,
                Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setString(1, car.getVehicleName());
            pstmt.setString(2, "Car");
            pstmt.setString(3, car.getRegNumber());
            pstmt.setString(4, car.getQrPath());
            pstmt.setDouble(5, car.getAvailabelFuel());

            int affectedRows = pstmt.executeUpdate();
            // check the affected rows 
            if (affectedRows > 0) {
            	System.out.println("Successfully Inserted the row..");
            	JOptionPane.showMessageDialog(null, "Successfully added the vehicle!");
            	SelectVehicleWindow newF = new SelectVehicleWindow();
				SelectVehicleWindow.main(null);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }
	
	
	
	public String getSelectedButtonText(ButtonGroup typegroup) {
        for (Enumeration<AbstractButton> buttons = typegroup.getElements(); buttons.hasMoreElements();) {
            AbstractButton button = buttons.nextElement();

            if (button.isSelected()) {
                return button.getText();
            }
        }
        return null;
    }
	
	
	public void SaveVehicle(String vehicleName, String type, String regNum, String QRpath, Double availableFuel) {
		if(type == "Bike") {
			Bike bike = new Bike(vehicleName, type, regNum, QRpath, availableFuel);
			System.out.println("bike Object Created..!");
			insertBike(bike);
		}
		
		if(type == "Three Wheel") {
			ThreeWheel threewheel = new ThreeWheel(vehicleName, type, regNum, QRpath, availableFuel);
			System.out.println("threewheel Object Created..!");
			insertThreeWheel(threewheel);
		}
		
		if(type == "Car") {
			Car car = new Car(vehicleName, type, regNum, QRpath, availableFuel);
			System.out.println("car Object Created..!");
			insertCar(car);
		}
	}
	
	public void BtnSaveDir() {
		String pathText;
		JFileChooser chooser = new JFileChooser();
		chooser.setCurrentDirectory(new java.io.File("."));
		chooser.setDialogTitle("choosertitle");
		chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		chooser.setAcceptAllFileFilterUsed(true);

		if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
			pathText = chooser.getSelectedFile().getAbsolutePath();
			System.out.println(pathText);
			textQrPath.setText(pathText);
		} else {
		  System.out.println("No Selection ");
		}
	}
	
	
	
	/*
	 * public void insertBike(Bike bike) { String SQL =
	 * "INSERT INTO vehicles(vehicle_name,vehicle_type,reg_number,qr_path) " +
	 * "VALUES(?,?,?,?)";
	 * 
	 * try {
	 * 
	 * } catch(SQLException ex) { JOptionPane.showMessageDialog(null,
	 * "Error Occured"); } }
	 */
	
	
	
	
	/**
	 * Create the application.
	 */
	public AddVehicleWindow() {
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
		
		JLabel lblNewLabel = new JLabel("Add Vehicle");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblNewLabel.setBounds(247, 40, 178, 50);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Name :");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(83, 133, 135, 19);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("This is the name you refre the vehicle from here onwards*");
		lblNewLabel_2.setBounds(83, 149, 270, 13);
		frame.getContentPane().add(lblNewLabel_2);
		
		textVehicleName = new JTextField();
		textVehicleName.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textVehicleName.setBounds(407, 133, 201, 19);
		frame.getContentPane().add(textVehicleName);
		textVehicleName.setColumns(10);
		
		textRegNumber = new JTextField();
		textRegNumber.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textRegNumber.setColumns(10);
		textRegNumber.setBounds(407, 176, 201, 19);
		frame.getContentPane().add(textRegNumber);
		
		JLabel lblNewLabel_1_1 = new JLabel("Reg Number :");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1_1.setBounds(83, 176, 135, 19);
		frame.getContentPane().add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_2_1 = new JLabel("Ex: BHM - 3789");
		lblNewLabel_2_1.setBounds(83, 195, 270, 13);
		frame.getContentPane().add(lblNewLabel_2_1);
		
		JRadioButton rdbtnNewRadioButton = new JRadioButton("Bike");
		rdbtnNewRadioButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		rdbtnNewRadioButton.setBounds(229, 274, 103, 21);
		frame.getContentPane().add(rdbtnNewRadioButton);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Select Type :");
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1_1_1.setBounds(100, 274, 135, 19);
		frame.getContentPane().add(lblNewLabel_1_1_1);
		
		JRadioButton rdbtnCar = new JRadioButton("Three Wheel");
		rdbtnCar.setFont(new Font("Tahoma", Font.PLAIN, 15));
		rdbtnCar.setBounds(229, 297, 152, 21);
		frame.getContentPane().add(rdbtnCar);
		
		JRadioButton rdbtnThreeWheel = new JRadioButton("Car");
		rdbtnThreeWheel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		rdbtnThreeWheel.setBounds(229, 320, 178, 21);
		frame.getContentPane().add(rdbtnThreeWheel);
		
		/* Grouping Radio Buttons*/
		ButtonGroup typegroup = new ButtonGroup();
		typegroup.add(rdbtnThreeWheel);
		typegroup.add(rdbtnCar);
		typegroup.add(rdbtnNewRadioButton);   //Use isSelected() to validate this
		
		JLabel lblNewLabel_1_1_2 = new JLabel("QR Path :");
		lblNewLabel_1_1_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1_1_2.setBounds(100, 347, 135, 19);
		frame.getContentPane().add(lblNewLabel_1_1_2);
		
		textQrPath = new JTextField();
		textQrPath.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textQrPath.setColumns(10);
		textQrPath.setBounds(295, 347, 211, 19);
		frame.getContentPane().add(textQrPath);
		
		JLabel lblNewLabel_2_1_1 = new JLabel("Resolution must be 200*200 for a ideal view");
		lblNewLabel_2_1_1.setBounds(100, 366, 270, 13);
		frame.getContentPane().add(lblNewLabel_2_1_1);
		
		JButton btnNewButton = new JButton("Browse");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BtnSaveDir();
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnNewButton.setBounds(506, 346, 85, 21);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnSaveVehicle = new JButton("Save Vehicle");
		btnSaveVehicle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SaveVehicle(textVehicleName.getText(),getSelectedButtonText(typegroup),textRegNumber.getText(),textQrPath.getText(),Double.parseDouble(textAvailableFuel.getText()));
			}
		});
		btnSaveVehicle.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnSaveVehicle.setBounds(276, 430, 142, 38);
		frame.getContentPane().add(btnSaveVehicle);
		
		JButton btnNewButton_1 = new JButton("Back");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SelectVehicleWindow newF = new SelectVehicleWindow();
				SelectVehicleWindow.main(null);
			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnNewButton_1.setBounds(10, 474, 120, 30);
		frame.getContentPane().add(btnNewButton_1);
		
		textAvailableFuel = new JTextField();
		textAvailableFuel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textAvailableFuel.setColumns(10);
		textAvailableFuel.setBounds(407, 219, 201, 19);
		frame.getContentPane().add(textAvailableFuel);
		
		JLabel lblNewLabel_1_1_3 = new JLabel("Available Fuel in your quota :");
		lblNewLabel_1_1_3.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1_1_3.setBounds(83, 221, 211, 19);
		frame.getContentPane().add(lblNewLabel_1_1_3);
		
		JLabel lblNewLabel_2_1_2 = new JLabel("Tpye the currently remaining capasity in your weekly quota");
		lblNewLabel_2_1_2.setBounds(83, 240, 342, 13);
		frame.getContentPane().add(lblNewLabel_2_1_2);
	}
}
