package com.jframe;


import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import com.tools.RezolutieAplicatie;

public class JFrameNotoficareTransferAngajatAltaEchipa extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	public double width;
	public double height;
	
	public JFrameNotoficareTransferAngajatAltaEchipa(String echipa1, String echipa2, String nume) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		RezolutieAplicatie ra = new RezolutieAplicatie();
		width = ra.getLungimeComponenta(20);
		height = ra.getInaltimeComponenta(20);
		setBounds(0, 0, 609, 232);
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel labelInformatii = new JLabel("");
		labelInformatii.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 13));
		labelInformatii.setBounds(12,44, 579,
				79);
		labelInformatii.setText("Angajatul "+ nume + " a fost transferat automat din echipa " + echipa1 + " in echipa "+ echipa2);
		contentPane.add(labelInformatii);
		

	}

}
