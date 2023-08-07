import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.SwingConstants;

public class ConfigQuotaWindow extends JFrame {

	private JPanel contentPane;
	private JTextField textBike;
	private JTextField textThreeWheel;
	private JTextField textCar;
	private JLabel lblCars;
	private JButton btnNewButton;
	private JButton btnSave;
	private JButton btnBack;
	private JLabel lblNewLabel_1;
	private JLabel lblPricePerLier;
	private JTextField textPrice;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ConfigQuotaWindow frame = new ConfigQuotaWindow();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	//*** My methods ****/////
	String url = "jdbc:mysql://localhost:3306/fuelmanager";
	String user_name = "root";
	String pass_word = "";
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;
	
	public Connection connect() throws SQLException {
        return DriverManager.getConnection(url, user_name, pass_word);
    }
	
	public void SaveQuotas() {
		String SQL = "UPDATE quotas SET bike = ?, three_wheel = ?, car = ?, price = ?;";
		
        try (Connection conn = connect();
                PreparedStatement pstmt = conn.prepareStatement(SQL,
                Statement.RETURN_GENERATED_KEYS)) {
        	
        	pstmt.setDouble(1, Double.parseDouble(textBike.getText()));
            pstmt.setDouble(2, Double.parseDouble(textThreeWheel.getText()));
            pstmt.setDouble(3, Double.parseDouble(textCar.getText()));
            pstmt.setDouble(4, Double.parseDouble(textPrice.getText()));

            int affectedRows = pstmt.executeUpdate();
            // check the affected rows 
            if (affectedRows > 0) {
            	System.out.println("Successfully Updated the row..");
            	JOptionPane.showMessageDialog(null, "Successfully updated the quota!");
            	SelectVehicleWindow newF = new SelectVehicleWindow();
   			 	SelectVehicleWindow.main(null);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            JOptionPane.showMessageDialog(null, "Enter Valid Inputs..");
        }
	}

	/**
	 * Create the frame.
	 */
	public ConfigQuotaWindow() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 550);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Bikes :");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel.setBounds(164, 144, 89, 27);
		contentPane.add(lblNewLabel);
		
		textBike = new JTextField();
		textBike.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textBike.setBounds(290, 144, 172, 25);
		contentPane.add(textBike);
		textBike.setColumns(10);
		
		JLabel lblThreeWheels = new JLabel("Three Wheels :");
		lblThreeWheels.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblThreeWheels.setBounds(163, 181, 116, 27);
		contentPane.add(lblThreeWheels);
		
		textThreeWheel = new JTextField();
		textThreeWheel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textThreeWheel.setColumns(10);
		textThreeWheel.setBounds(289, 181, 172, 25);
		contentPane.add(textThreeWheel);
		
		textCar = new JTextField();
		textCar.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textCar.setColumns(10);
		textCar.setBounds(290, 218, 172, 25);
		contentPane.add(textCar);
		
		lblCars = new JLabel("Cars :");
		lblCars.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblCars.setBounds(164, 218, 89, 27);
		contentPane.add(lblCars);
		
		btnNewButton = new JButton("Clear");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textBike.setText("");
				textThreeWheel.setText("");
				textCar.setText("");
				textPrice.setText("");
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnNewButton.setBounds(194, 362, 96, 27);
		contentPane.add(btnNewButton);
		
		btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SaveQuotas();
			}
		});
		btnSave.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnSave.setBounds(340, 362, 96, 27);
		contentPane.add(btnSave);
		
		btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VehicleMainWindow newF = new VehicleMainWindow();
				VehicleMainWindow.main(null);
			}
		});
		btnBack.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnBack.setBounds(10, 473, 120, 30);
		contentPane.add(btnBack);
		
		lblNewLabel_1 = new JLabel("Config Quotas");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblNewLabel_1.setBounds(227, 60, 182, 37);
		contentPane.add(lblNewLabel_1);
		
		lblPricePerLier = new JLabel("Price per Liter :");
		lblPricePerLier.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblPricePerLier.setBounds(194, 274, 140, 27);
		contentPane.add(lblPricePerLier);
		
		textPrice = new JTextField();
		textPrice.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textPrice.setColumns(10);
		textPrice.setBounds(320, 275, 130, 25);
		contentPane.add(textPrice);
		
		lblNewLabel_2 = new JLabel("Note : Please fill up all the text fields before save..");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setForeground(new Color(178, 34, 34));
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_2.setBounds(137, 309, 372, 13);
		contentPane.add(lblNewLabel_2);
		
		lblNewLabel_3 = new JLabel("Weekly Quota in Liters");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setForeground(new Color(0, 0, 0));
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_3.setBounds(137, 95, 372, 13);
		contentPane.add(lblNewLabel_3);
	}

	private static class __Tmp {
		private static void __tmp() {
			  javax.swing.JPanel __wbp_panel = new javax.swing.JPanel();
		}
	}
}
