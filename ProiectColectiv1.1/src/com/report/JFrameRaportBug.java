package com.report;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import com.jframe.JFrameMesajRaportTrimis;
import com.tools.RezolutieAplicatie;

public class JFrameRaportBug extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	public double width;
	public double height;
	JLabel labelTitlu;
	JLabel labelNume;
	JLabel labelEmail;
	JLabel descrie;
	JTextField numeF;
	JTextField emailF;
	JTextArea text;
	JScrollPane scroll;
	JButton buton;
	

	public JFrameRaportBug() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		RezolutieAplicatie ra = new RezolutieAplicatie();
		width = ra.getWidth();
		height = ra.getHeight();
		setBounds(ra.getPozitieX(20, width), ra.getPozitieY(20, height), 576, 610);
		contentPane = new JPanel();
		getContentPane().setLayout(null);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		labelTitlu = new JLabel("Raportare eroare");
		labelTitlu.setBounds(213,13,100,100);
		contentPane.add(labelTitlu);
		
		labelNume = new JLabel("nume");
		labelNume.setBounds(74, 120, 53, 43);
		contentPane.add(labelNume);
		
		numeF = new JTextField();
		numeF.setBounds(139, 126, 231, 30);
		contentPane.add(numeF);
		
		labelEmail = new JLabel("email");
		labelEmail.setBounds(74,189,53,43);
		contentPane.add(labelEmail);
		
		emailF = new JTextField();
		emailF.setBounds(139,195,231,30);
		contentPane.add(emailF);
		
		descrie = new JLabel("Descrieti eroarea intampinata");
		descrie.setBounds(180, 255, 190, 40);
		contentPane.add(descrie);
		
		//pun jtextArea intr-un jscrollpane pentru a aparea in dreapta sau in jos bara de scroll
		//in cazul in care pun mai mult text 
		text = new JTextArea();
		scroll = new JScrollPane(text);
		scroll.setBounds(44, 308, 456, 186);
		contentPane.add(scroll);
		
		buton = new JButton("Trimite");
		buton.setBounds(225, 507, 77, 50);
		buton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					String url = "jdbc:mysql://localhost:3306/sys?&serverTimezone=UTC";
					String user = "root";
					String password = "password";
					Connection conn = DriverManager.getConnection(url, user, password);
					String insertSQL = "INSERT INTO eroare(nume, data, email, deschis, descriere) values (?, ?, ?, 0, ?)";
					PreparedStatement preparedStatement = conn.prepareStatement(insertSQL);
					preparedStatement.setString(1, numeF.getText());
					java.util.Date utilDate = new java.util.Date();
				    java.sql.Timestamp timpCurent = new java.sql.Timestamp(utilDate.getTime());
					preparedStatement.setTimestamp(2, timpCurent);
					preparedStatement.setString(3, emailF.getText());
					preparedStatement.setString(4, text.getText());
					preparedStatement.executeUpdate();
				
					JFrameMesajRaportTrimis jfmrt = new JFrameMesajRaportTrimis();
					jfmrt.setVisible(true);
				}
				
				catch (SQLException sqle) {
					System.out.println(sqle.getMessage());
				}
			}		
		});
		contentPane.add(buton);
	}
	
}
