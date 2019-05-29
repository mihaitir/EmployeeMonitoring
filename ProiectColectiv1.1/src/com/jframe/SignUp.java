package com.jframe;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class SignUp extends JFrame 
{
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField usernameField;
	private JPasswordField passwordField;
	JButton btnNewButton;
	JLabel lblNewLabel;
	private static MessageDigest md;

	public SignUp()
	{
		setTitle("SignUp");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(0, 0, 582, 370);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		usernameField = new JTextField();
		usernameField.setBounds(139, 95, 280, 39);
		contentPane.add(usernameField);
		usernameField.setColumns(10);
		
		JLabel lblUsername = new JLabel("");
		lblUsername.setBounds(83, 95, 46, 45);
		lblUsername.setIcon(new ImageIcon(Login.class.getResource("/img/Password2.png")));
		getContentPane().add(lblUsername);
		
		JLabel lblPassword = new JLabel("");
		lblPassword.setBounds(83, 174, 46, 39);
		lblPassword.setIcon(new ImageIcon(Login.class.getResource("/img/Username1.png")));
		getContentPane().add(lblPassword);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(139, 174, 280, 39);
		contentPane.add(passwordField);
		
		btnNewButton = new JButton("Submit");
		btnNewButton.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) 
			{
				Connection connection = null;
				String url="jdbc:mysql://localhost:3306/sys?&serverTimezone=UTC";
		  		String user = "root";
		  		String password = "password";
	
			           try {
			        	   connection = DriverManager.getConnection(url, user, password); 
			        	   String updateSQL = "insert into conturi(nume, parola) values(?,?)";
			        	   PreparedStatement preparedStatement = connection.prepareStatement(updateSQL);
			        	   preparedStatement.setString(1, (usernameField.getText()));
			        	   preparedStatement.setString(2, cryptWithMD5(passwordField.getText()));
			        	   preparedStatement.executeUpdate();
			           }
			           
			      	  
			      catch(SQLException sqle)
			      {
			    	System.out.print(sqle.getMessage());  
			      }
			      	finally
			      	{
			      		if (connection != null) 
			      		{ 
			                 try { 
			                     connection.close();  
			                 	 } 
			                 catch(SQLException sqle) {}  
			                 connection = null;  
			      		}
			      	}

			}
			
		});
		btnNewButton.setBounds(228, 262, 86, 45);
		contentPane.add(btnNewButton);
		
		lblNewLabel = new JLabel("");
		JLabel lblSignUp = new JLabel("Sign Up");
		lblSignUp.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblSignUp.setBounds(228, 11, 98, 39);
		contentPane.add(lblSignUp);
		lblNewLabel.setIcon(new ImageIcon(SignUp.class.getResource("/img/logback.jpg")));
		lblNewLabel.setBounds(0, 0, 582, 370);
		contentPane.add(lblNewLabel);
		
		
	}

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
}
