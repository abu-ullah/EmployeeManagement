import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.proteanit.sql.DbUtils;

import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JLabel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class EmployeeForm extends JFrame {
	
	private Connection connection = null;
	private JPanel contentPane;
	private JTable Emp_table;
	private JTextField textEID;
	private JTextField textName;
	private JTextField textUsername;
	private JTextField textAge;
	private JPasswordField textPassword;
	private JLabel lblPassword_1;
	private JLabel lblPassword_2;
	private JButton btnAdd;
	private JButton btnUpdate;
	private JButton btnDelete;
	private JTextField textSearch;
	private JComboBox comboBoxSearch;
	
	
	/***
	 * General Methods to load the table and make fields empty
	 */
	
	private void refreshTable() {
		
		
		try {
			
			String query = "select EID, Name, Age from EmployeeInfo";
			PreparedStatement pst = connection.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
			//Add rs2xml to make our result compatible with JTabble
			Emp_table.setModel(DbUtils.resultSetToTableModel(rs));
			
			pst.close();
			rs.close();
			
			
		}catch(Exception e3) {
			
			JOptionPane.showMessageDialog(null, e3);
			
		}
		
		
		
	}
	
	private void refreshFields() {
		
		
		textEID.setText(null);
		textName.setText(null);
		textUsername.setText(null);
		textPassword.setText(null);
		textAge.setText(null);
		
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EmployeeForm frame = new EmployeeForm();
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
	public EmployeeForm() {
		connection = SqliteConnection.dbConnector();
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\English\\eclipse-workspace\\Employee\\img\\team.png"));
		setTitle("Employee Managment");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 693, 481);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(275, 144, 375, 265);
		contentPane.add(scrollPane);
		
		Emp_table = new JTable();
		Emp_table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				
				try {
					
					int row = Emp_table.getSelectedRow();
					String EId = (Emp_table.getModel().getValueAt(row, 0)).toString();
					
					String query = "select * from EmployeeInfo where EID='" + EId +"'";
					PreparedStatement pst = connection.prepareStatement(query);
					ResultSet rs = pst.executeQuery();
					
					while(rs.next()) {
						
						textEID.setText(rs.getString("EID"));
						textName.setText(rs.getString("Name"));
						textUsername.setText(rs.getString("Username"));
						textPassword.setText(rs.getString("Password"));
						textAge.setText(rs.getString("Age"));
						
					}
					
					pst.close();
					rs.close();
				}catch(Exception e2) {
					
					JOptionPane.showMessageDialog(null, e2);
					
				}
			}
		});
		Emp_table.setFont(new Font("Tahoma", Font.BOLD, 18));
		scrollPane.setViewportView(Emp_table);
		
		JButton btnLoadTable = new JButton("Load Table");
		btnLoadTable.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					
					String query = "select EID, Name, Age from EmployeeInfo";
					PreparedStatement pst = connection.prepareStatement(query);
					ResultSet rs = pst.executeQuery();
					//Add rs2xml to make our result compatible with JTabble
					Emp_table.setModel(DbUtils.resultSetToTableModel(rs));
					
					pst.close();
					rs.close();
					
					
				}catch(Exception e1) {
					
					JOptionPane.showMessageDialog(null, e1);
					
				}
				
				
				
			}
		});
		btnLoadTable.setForeground(Color.LIGHT_GRAY);
		btnLoadTable.setBackground(Color.DARK_GRAY);
		btnLoadTable.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
		btnLoadTable.setBounds(424, 27, 188, 32);
		btnLoadTable.setFocusable(false);
		btnLoadTable.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
		contentPane.add(btnLoadTable);
		
		textEID = new JTextField();
		textEID.setFont(new Font("Tahoma", Font.BOLD, 18));
		textEID.setBounds(132, 104, 118, 26);
		contentPane.add(textEID);
		textEID.setColumns(10);
		
		textName = new JTextField();
		textName.setFont(new Font("Tahoma", Font.BOLD, 18));
		textName.setColumns(10);
		textName.setBounds(132, 151, 118, 26);
		contentPane.add(textName);
		
		textUsername = new JTextField();
		textUsername.setFont(new Font("Tahoma", Font.BOLD, 18));
		textUsername.setColumns(10);
		textUsername.setBounds(132, 198, 118, 26);
		contentPane.add(textUsername);
		
		textAge = new JTextField();
		textAge.setFont(new Font("Tahoma", Font.BOLD, 18));
		textAge.setColumns(10);
		textAge.setBounds(132, 292, 118, 26);
		contentPane.add(textAge);
		
		textPassword = new JPasswordField();
		textPassword.setFont(new Font("Tahoma", Font.BOLD, 18));
		textPassword.setBounds(132, 247, 118, 26);
		contentPane.add(textPassword);
		
		JLabel lblNewLabel = new JLabel("ID:");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel.setBounds(23, 110, 46, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblUsername = new JLabel("Name:");
		lblUsername.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblUsername.setBounds(10, 151, 111, 26);
		contentPane.add(lblUsername);
		
		JLabel lblPassword = new JLabel("Username:");
		lblPassword.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblPassword.setBounds(10, 198, 111, 26);
		contentPane.add(lblPassword);
		
		lblPassword_1 = new JLabel("Password:");
		lblPassword_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblPassword_1.setBounds(10, 247, 111, 26);
		contentPane.add(lblPassword_1);
		
		lblPassword_2 = new JLabel("Age:");
		lblPassword_2.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblPassword_2.setBounds(11, 292, 111, 26);
		contentPane.add(lblPassword_2);
		
		btnAdd = new JButton("Save");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					
					String query = "insert into EmployeeInfo (EID,Name,Username, Password, Age) values (?,?,?,?,?)";
					PreparedStatement pst = connection.prepareStatement(query);
					
					pst.setString(1, textEID.getText());
					pst.setString(2, textName.getText());
					pst.setString(3, textUsername.getText());
					pst.setString(4, textPassword.getText());
					pst.setString(5, textAge.getText());
					
					pst.execute();
					JOptionPane.showMessageDialog(null,"Data Saved.");
					pst.close();
					
					
					
				}catch(Exception e1) {
					
					JOptionPane.showMessageDialog(null, e1);
					
				}
				
				
				refreshTable();
				refreshFields();
				
			}
		});
		btnAdd.setForeground(Color.LIGHT_GRAY);
		btnAdd.setBackground(Color.DARK_GRAY);
		btnAdd.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 16));
		btnAdd.setBounds(119, 339, 111, 23);
		contentPane.add(btnAdd);
		
		btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				try {
					
					String query = "update EmployeeInfo set EID='" + textEID.getText() + "',Name='" +
							textName.getText() + "', Username='" + textUsername.getText() + "', Password='" +
							textPassword.getText() + "', Age='" + textAge.getText() + "' where EID='" + textEID.getText() + "'";
					PreparedStatement pst = connection.prepareStatement(query);
					pst.execute();
					JOptionPane.showMessageDialog(null,"Data Updated.");
					pst.close();
					
				}catch(Exception e1) {
					
					JOptionPane.showMessageDialog(null, e1);
					
				}
				
				
				refreshTable();
				refreshFields();
				
			}
				
		});
		btnUpdate.setForeground(Color.LIGHT_GRAY);
		btnUpdate.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 16));
		btnUpdate.setBackground(Color.DARK_GRAY);
		btnUpdate.setBounds(119, 373, 111, 23);
		contentPane.add(btnUpdate);
		
		btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int action  = JOptionPane.showConfirmDialog(null, "Are You Sure to Delete?" , "Delete", JOptionPane.YES_NO_OPTION);
				
				if(action == 0) {
					
					try {
						
						String query = "delete from EmployeeInfo where EID='" + textEID.getText() + "'";
						PreparedStatement pst = connection.prepareStatement(query);
						pst.execute();
						JOptionPane.showMessageDialog(null,"Data Removed.");
						pst.close();
						
					}catch(Exception e1) {
						
						JOptionPane.showMessageDialog(null, e1);
						
					}
					
					
					refreshTable();
					
					
					
				}
				
				refreshFields();
				
			}
		});
		btnDelete.setForeground(Color.LIGHT_GRAY);
		btnDelete.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 16));
		btnDelete.setBackground(Color.DARK_GRAY);
		btnDelete.setBounds(119, 407, 111, 23);
		contentPane.add(btnDelete);
		
		textSearch = new JTextField();
		textSearch.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				
				
				try {
					
					String selection = comboBoxSearch.getSelectedItem().toString();
					
					String query = "select EID, Name, Age from EmployeeInfo where "+  selection + "=?";
					
//					String query = "select EID, Name, Age from EmployeeInfo where EID= ?";
					PreparedStatement pst = connection.prepareStatement(query);
					
					pst.setString(1, textSearch.getText());
					ResultSet rs = pst.executeQuery();
					
					Emp_table.setModel(DbUtils.resultSetToTableModel(rs));
					pst.close();
					rs.close();
					
				}catch(Exception e4) {
					
					JOptionPane.showMessageDialog(null, e4);
					
				}
				
				
				
			}
		});
		textSearch.setFont(new Font("Tahoma", Font.BOLD, 18));
		textSearch.setBounds(445, 98, 167, 26);
		contentPane.add(textSearch);
		textSearch.setColumns(10);
		
		comboBoxSearch = new JComboBox();
		comboBoxSearch.setModel(new DefaultComboBoxModel(new String[] {"EID", "Name", "Age"}));
		comboBoxSearch.setFont(new Font("Tahoma", Font.BOLD, 18));
		comboBoxSearch.setBounds(314, 103, 100, 22);
		contentPane.add(comboBoxSearch);
		
		
		//refreshTable();
	}
}
