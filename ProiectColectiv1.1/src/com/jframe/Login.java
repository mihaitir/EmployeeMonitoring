package com.jframe;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionListener;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
public class Login {

	public JFrame frmLogin;
	@SuppressWarnings("unused")
	private JTextField username;
	@SuppressWarnings("unused")
	private JPasswordField passwordField;
	public static String numeUtilizator;
	private JTextField usernameTextField;
	private JPasswordField passwordTextField;
	private JButton loginButton;
	private JLabel lblNewLabel;
	JButton signUpButton;

	public Login() {
		initialize();
	}
	
	private static MessageDigest md;

	   public static String cryptWithMD5(String pass){
	    try {
	        md = MessageDigest.getInstance("MD5");
	        byte[] passBytes = pass.getBytes();
	        md.reset();
	        byte[] digested = md.digest(passBytes);
	        StringBuffer sb = new StringBuffer();
	        for(int i=0;i<digested.length;i++){
	            sb.append(Integer.toHexString(0xff & digested[i]));
	        }
	        return sb.toString();
	    } catch (NoSuchAlgorithmException ex) {
	    }
	        return null;
}

	private void initialize() {
		frmLogin = new JFrame();
		frmLogin.setResizable(false);
		frmLogin.setTitle("Login");
		frmLogin.getContentPane().setBackground(Color.WHITE);
		frmLogin.getContentPane().setLayout(null);

		usernameTextField = new JTextField();
		usernameTextField.setBounds(197, 87, 177, 39);
		frmLogin.getContentPane().add(usernameTextField);
		usernameTextField.setColumns(10);

		passwordTextField = new JPasswordField();
		passwordTextField.setBounds(197, 153, 177, 39);
		frmLogin.getContentPane().add(passwordTextField);
		passwordTextField.setColumns(10);

		JLabel lblUsername = new JLabel("");
		lblUsername.setBounds(152, 85, 46, 45);
		lblUsername.setIcon(new ImageIcon(Login.class.getResource("/img/Username1.png")));
		frmLogin.getContentPane().add(lblUsername);

		JLabel lblPassword = new JLabel("");
		lblPassword.setBounds(152, 155, 46, 39);
		lblPassword.setIcon(new ImageIcon(Login.class.getResource("/img/Password2.png")));
		frmLogin.getContentPane().add(lblPassword);

		loginButton = new JButton("Login");
		loginButton.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {

				String url = "jdbc:mysql://localhost:3306/sys?&serverTimezone=UTC";
				String user = "root";
				String password = "password";
				Statement selectStatement = null;
				ResultSet rs = null;
				Connection connection = null;

				try {
					connection = DriverManager.getConnection(url, user, password);

					selectStatement = connection.createStatement();
					selectStatement.execute("SELECT * FROM conturi");
					rs = selectStatement.getResultSet();
					String numeParola = null; 
					numeParola = cryptWithMD5(passwordTextField.getText());
					boolean contGasit = false;
					while (rs.next()) {
						
						if (rs.getString("nume").compareTo(usernameTextField.getText()) == 0
								&& rs.getString("parola").compareTo(numeParola )== 0) {
							System.out.println("Conectarea ruleaza...");
							contGasit = true;
							
							try {

								JFramePrincipal frame = new JFramePrincipal();
								frame.setVisible(true);
								break;
							} catch (Exception ex) {
								ex.printStackTrace();
							}

						}
					}
					if (!contGasit)  {
						JFrameAlertaLogin frameAlerta = new JFrameAlertaLogin();
						frameAlerta.setVisible(true);
					}
				}

				catch (SQLException sqle) {

				} finally {
					if (connection != null) {
						try {
							connection.close();
						} catch (SQLException sqle) {
						}
						connection = null;
					}
				}
			}
		});
		loginButton.setBounds(239, 242, 89, 23);
		frmLogin.getContentPane().add(loginButton);
		lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(Login.class.getResource("/img/logback.jpg")));
		signUpButton = new JButton("Sign up");
		signUpButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							SignUp frame = new SignUp();
							frame.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}); 
			}
		});
		
		
		
		signUpButton.setBounds(45, 310, 89, 23);
		frmLogin.getContentPane().add(signUpButton);
		frmLogin.setBounds(100, 100, 586, 398);
		frmLogin.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		lblNewLabel.setBounds(0, 0, 582, 370);
		frmLogin.getContentPane().add(lblNewLabel);
		
		

	}
}