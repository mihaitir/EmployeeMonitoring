package com.jframe;

import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import com.tools.RezolutieAplicatie;

public class JFrameMesajStergeEchipaSucces extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	public double width;
	public double height;
	
	public JFrameMesajStergeEchipaSucces() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		RezolutieAplicatie ra = new RezolutieAplicatie();
		width = ra.getLungimeComponenta(20);
		height = ra.getInaltimeComponenta(20);
		setBounds(ra.getPozitieX(40), ra.getPozitieY(40), (int) width, (int) height);
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Echipa a fost stearsa cu succes!");
		lblNewLabel.setFont(new Font("Segoe UI Symbol", Font.PLAIN, ra.getLungimeComponenta(1)));
		lblNewLabel.setBounds((int)ra.getPozitieX(12, width),0, ra.getLungimeComponenta(70, width),
				ra.getInaltimeComponenta(90, height));
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel1 = new JLabel();
		lblNewLabel1.setBounds(0,0, (int) width, (int) height);
		lblNewLabel1.setIcon(new ImageIcon(JFrameMesajInserareInsucces.class.getResource("/img/homeBack2.jpg")));
		contentPane.add(lblNewLabel1);
	}

}
