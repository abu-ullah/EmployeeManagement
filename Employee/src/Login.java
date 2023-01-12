import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.UIManager;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import java.awt.SystemColor;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Login {

	private JFrame frmLogin;
	private Connection connection;
	private JTextField textUsername;
	private JPasswordField textPassword;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login window = new Login();
					window.frmLogin.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Login() {
		initialize();
		connection = SqliteConnection.dbConnector();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmLogin = new JFrame();
		frmLogin.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\English\\eclipse-workspace\\Employee\\img\\key.png"));
		frmLogin.getContentPane().setBackground(new Color(0, 128, 128));
		frmLogin.setBackground(new Color(0, 128, 128));
		frmLogin.setTitle("Login");
		frmLogin.setBounds(100, 100, 480, 352);
		frmLogin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmLogin.getContentPane().setLayout(null);
		
		textUsername = new JTextField();
		textUsername.setForeground(UIManager.getColor("Button.light"));
		textUsername.setFont(new Font("Tahoma", Font.BOLD, 18));
		textUsername.setBackground(new Color(128, 128, 128));
		textUsername.setBounds(301, 109, 130, 30);
		frmLogin.getContentPane().add(textUsername);
		textUsername.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Username:");
		lblNewLabel.setForeground(UIManager.getColor("Button.light"));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
		lblNewLabel.setBounds(171, 108, 120, 30);
		frmLogin.getContentPane().add(lblNewLabel);
		
		textPassword = new JPasswordField();
		textPassword.setBackground(new Color(128, 128, 128));
		textPassword.setForeground(UIManager.getColor("Button.light"));
		textPassword.setFont(new Font("Tahoma", Font.BOLD, 18));
		textPassword.setBounds(301, 166, 130, 30);
		frmLogin.getContentPane().add(textPassword);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setForeground(SystemColor.controlHighlight);
		lblPassword.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
		lblPassword.setBounds(171, 165, 120, 30);
		frmLogin.getContentPane().add(lblPassword);
		
		JButton btnLogin = new JButton("LOGIN");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					
					String query = "select * from EmployeeInfo where Username=? and Password=?";
					PreparedStatement pst = connection.prepareStatement(query);
					pst.setString(1, textUsername.getText());
					pst.setString(2, textPassword.getText());
					
					ResultSet rs = pst.executeQuery();
					
					int counter = 0;
					while (rs.next()) {
						counter++;
					}
					
					if (counter == 1) {
						
						JOptionPane.showMessageDialog(null, "Username and Password is correct.");
						frmLogin.dispose();
						EmployeeForm Empframe = new EmployeeForm();
						Empframe.setVisible(true);
						
					} else if(counter > 1){
						
						JOptionPane.showMessageDialog(null, "Username and Password is duplicated.");
					
					}else {
						
						JOptionPane.showMessageDialog(null, "Username and Password is wrong.");
					}
					
					
					pst.close();
					rs.close();
					
					
				} catch (Exception e2) {
					// TODO: handle exception
					JOptionPane.showMessageDialog(null, e);
				}
				
			}
		});
		btnLogin.setForeground(UIManager.getColor("Button.light"));
		btnLogin.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 18));
		btnLogin.setBackground(new Color(128, 128, 128));
		btnLogin.setBounds(213, 235, 130, 30);
		btnLogin.setBorder(BorderFactory.createLineBorder(new Color(128, 128, 128)));
		btnLogin.setFocusable(false);
		frmLogin.getContentPane().add(btnLogin);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon("C:\\Users\\English\\eclipse-workspace\\Employee\\img\\key.png"));
		lblNewLabel_1.setBounds(25, 11, 161, 128);
		frmLogin.getContentPane().add(lblNewLabel_1);
	}
}
