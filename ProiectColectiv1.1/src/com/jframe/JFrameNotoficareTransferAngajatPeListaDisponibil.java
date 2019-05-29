package com.jframe;


import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import com.tools.RezolutieAplicatie;

public class JFrameNotoficareTransferAngajatPeListaDisponibil extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	public double width;
	public double height;
	
	
	public JFrameNotoficareTransferAngajatPeListaDisponibil(String nume) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		RezolutieAplicatie ra = new RezolutieAplicatie();
		width = ra.getLungimeComponenta(20);
		height = ra.getInaltimeComponenta(20);
		setBounds(0, 0, 648, 246);
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Angajatul "+ nume +" si-a incheiat toate sarcinile in echipele in care a activat");
		lblNewLabel.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 14));
		lblNewLabel.setBounds(0,0, 618,
				104);
		contentPane.add(lblNewLabel);
		
	
	}
}
