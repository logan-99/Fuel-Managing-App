import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.Button;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class SelectVehicleWindow {

	private JFrame frame;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SelectVehicleWindow window = new SelectVehicleWindow();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	
	/////*  My MEthods */////
	String url = "jdbc:mysql://localhost:3306/fuelmanager";
	String user_name = "root";
	String pass_word = "";
	
	int count = 0;
	
	public Connection connect() throws SQLException {
        return DriverManager.getConnection(url, user_name, pass_word);
    }
	
	public int CountBtns() {
		String SQL = "SELECT COUNT(reg_number) FROM vehicles;";
		
		try (Connection conn = connect();
                Statement stmt = conn.createStatement()) {
			ResultSet rs = stmt.executeQuery(SQL);
			rs.next();
		    count = rs.getInt(1);
		    conn.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
		return count;
	}
	
	public ArrayList<String> vehicleList = new ArrayList<String>(3);
	public ArrayList<String> RetriveVehicleNames() {
		String SQL = "SELECT vehicle_name FROM vehicles;";
		try (Connection conn = connect();
            Statement stmt = conn.createStatement()) {
			ResultSet rs1 = stmt.executeQuery(SQL);
			while(rs1.next()) {
				vehicleList.add(rs1.getString(1));
			}
			//System.out.println(vehicleList);
			conn.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
		return vehicleList;
	}
	
	public ArrayList<String> vehicleTypes = new ArrayList<String>(3);
	public ArrayList<String> RetriveVehicleTypes() {
		String SQL = "SELECT vehicle_type FROM vehicles;";
		try (Connection conn = connect();
            Statement stmt = conn.createStatement()) {
			ResultSet rs1 = stmt.executeQuery(SQL);
			while(rs1.next()) {
				vehicleTypes.add(rs1.getString(1));
			}
			//System.out.println(vehicleTypes);
			conn.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
		return vehicleTypes;
	}
	

	/**
	 * Create the application.
	 */
	public SelectVehicleWindow() {
		initialize();
	}
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		int n = CountBtns();
		//System.out.println(n);
		
		RetriveVehicleNames();
		RetriveVehicleTypes();
		
		frame = new JFrame();
		frame.setBounds(100, 100, 700, 550);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Select Vehicle");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 35));
		lblNewLabel.setBounds(209, 57, 254, 61);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("You can add only upto 3 vehicles...");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(220, 425, 254, 20);
		frame.getContentPane().add(lblNewLabel_1);
		
		JButton btnVehicle1 = new JButton("Vehicle 1");
		btnVehicle1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VehicleMainWindow newF = new VehicleMainWindow(vehicleList.get(0),vehicleTypes.get(0));
				VehicleMainWindow.main(null);
				
			}
		});
		btnVehicle1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnVehicle1.setBounds(246, 156, 172, 47);
		frame.getContentPane().add(btnVehicle1);
		
		JButton btnVehicle2 = new JButton("Vehicle 2");
		btnVehicle2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VehicleMainWindow newF = new VehicleMainWindow(vehicleList.get(1),vehicleTypes.get(1));
				VehicleMainWindow.main(null);
			}
		});
		btnVehicle2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnVehicle2.setBounds(246, 222, 172, 47);
		frame.getContentPane().add(btnVehicle2);
		
		JButton btnVehicle3 = new JButton("Vehicle 3");
		btnVehicle3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VehicleMainWindow newF = new VehicleMainWindow(vehicleList.get(2),vehicleTypes.get(2));
				VehicleMainWindow.main(null);
			}
		});
		btnVehicle3.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnVehicle3.setBounds(246, 287, 172, 47);
		frame.getContentPane().add(btnVehicle3);
		
		JButton btnAddVehicle = new JButton("");
		btnAddVehicle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddVehicleWindow newF = new AddVehicleWindow();
				AddVehicleWindow.main(null);
			}
		});
		btnAddVehicle.setBackground(new Color(240, 240, 240));
		btnAddVehicle.setIcon(new ImageIcon("C:\\Users\\Malith Jayasinghe\\Downloads\\icons8-add-new-50.png"));
		btnAddVehicle.setSelectedIcon(null);
		btnAddVehicle.setBounds(309, 374, 44, 42);
		frame.getContentPane().add(btnAddVehicle);
		
		
		if(n == 0) {
			btnVehicle1.setVisible(false);
			btnVehicle2.setVisible(false);
			btnVehicle3.setVisible(false);
		}
		
		
		if(n == 1) {
			btnVehicle1.setVisible(true);
			btnVehicle2.setVisible(false);
			btnVehicle3.setVisible(false);
			
			btnVehicle1.setText(vehicleList.get(0));
		}
		
		if(n == 2) {
			btnVehicle1.setVisible(true);
			btnVehicle2.setVisible(true);
			btnVehicle3.setVisible(false);
			
			btnVehicle1.setText(vehicleList.get(0));
			btnVehicle2.setText(vehicleList.get(1));
		}
		
		if(n == 3) {
			btnVehicle1.setVisible(true);
			btnVehicle2.setVisible(true);
			btnVehicle3.setVisible(true);
			
			btnVehicle1.setText(vehicleList.get(0));
			btnVehicle2.setText(vehicleList.get(1));
			btnVehicle3.setText(vehicleList.get(2));
		}
		
		if(btnVehicle1.isVisible() == true && btnVehicle2.isVisible() == true && btnVehicle3.isVisible() == true) {
			btnAddVehicle.setVisible(false);
		}
		else {
			btnAddVehicle.setVisible(true);
		}

	}
}
