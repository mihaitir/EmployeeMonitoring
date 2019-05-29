package com.jframe;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


import javax.swing.JLabel;

public class JFrameAlertaLogin extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	

	public JFrameAlertaLogin() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 358, 139);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNumeSauParola = new JLabel("Nume sau parola gresita.");
		lblNumeSauParola.setBounds(101, 31, 227, 37);
		contentPane.add(lblNumeSauParola);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(0, 0, 358, 139);
		lblNewLabel.setIcon(new ImageIcon(Login.class.getResource("/img/logback.jpg")));
		contentPane.add(lblNewLabel);
	}

}
