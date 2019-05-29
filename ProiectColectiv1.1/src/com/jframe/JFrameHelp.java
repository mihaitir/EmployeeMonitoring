package com.jframe;

import java.awt.Font;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import com.jpanel.JPanelMeniu;
import com.tools.ResizeImagine;
import com.tools.RezolutieAplicatie;

public class JFrameHelp extends JFrame {

	private static final long serialVersionUID = 1L;
	public JPanelMeniu jpanelmeniu;
	public double width, height;

	public JFrameHelp(JPanelMeniu jpanelmeniu) {
		this.jpanelmeniu = jpanelmeniu;
		this.setLayout(null);
		RezolutieAplicatie ra = new RezolutieAplicatie();
		width = ra.getLungimeComponenta(50);
		height = ra.getInaltimeComponenta(70);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(ra.getPozitieX(30), ra.getPozitieY(15), (int) width, (int) height);
		
		JLabel LabelTitlu = new JLabel("Ghid de utilizare");
		LabelTitlu.setBounds(ra.getPozitieX(37, width), ra.getPozitieY(3, height), ra.getLungimeComponenta(20), ra.getLungimeComponenta(4));
		LabelTitlu.setFont(new Font("Arial", Font.BOLD, 30));
		this.add(LabelTitlu);
		
		JLabel LabelAdaugaAngajat = new JLabel();
		try {
			BufferedImage image = ImageIO.read(JFrameHelp.class.getResource("/img/adaugaAngajat.png"));
			ResizeImagine.creezaImagineRedimensionataDeTipPNG(image, (int) ra.getInaltimeComponenta(6),
					(int) ra.getInaltimeComponenta(6), "adaugaAngajat3", "png");
		} catch (IOException ioe) {
			System.out.println(ioe.getMessage());
		}
		LabelAdaugaAngajat.setIcon(new ImageIcon(JFrameHelp.class.getResource("/img/adaugaAngajat3.png")));
		LabelAdaugaAngajat.setBounds(ra.getPozitieX(10, width), ra.getPozitieY(15, height), ra.getLungimeComponenta(6), ra.getLungimeComponenta(6));
		this.add(LabelAdaugaAngajat);
		
		JLabel LabelTextAdaugaAngajat = new JLabel("Acest buton este folosit pentru adaugarea unui nou angajat.");
		LabelTextAdaugaAngajat.setBounds(ra.getPozitieX(21, width), ra.getPozitieY(14, height), ra.getLungimeComponenta(30), ra.getLungimeComponenta(7));
		LabelTextAdaugaAngajat.setFont(new Font("Arial", Font.BOLD, 20));
		this.add(LabelTextAdaugaAngajat);
		
		JLabel LabelStergeAngajat = new JLabel();
		try {
			BufferedImage image = ImageIO.read(JFrameHelp.class.getResource("/img/stergeAngajat.png"));
			ResizeImagine.creezaImagineRedimensionataDeTipPNG(image, (int) ra.getInaltimeComponenta(6),
					(int) ra.getInaltimeComponenta(6), "stergeAngajat3", "png");
		} catch (IOException ioe) {
			System.out.println(ioe.getMessage());
		}
		LabelStergeAngajat.setIcon(new ImageIcon(JFrameHelp.class.getResource("/img/stergeAngajat3.png")));
		LabelStergeAngajat.setBounds(ra.getPozitieX(10, width), ra.getPozitieY(27, height), ra.getLungimeComponenta(6), ra.getLungimeComponenta(6));
		this.add(LabelStergeAngajat);
		
		JLabel LabelTextStergeAngajat = new JLabel("Acest buton este folosit pentru stergerea unui angajat.");
		LabelTextStergeAngajat.setBounds(ra.getPozitieX(21, width), ra.getPozitieY(26, height), ra.getLungimeComponenta(30), ra.getLungimeComponenta(7));
		LabelTextStergeAngajat.setFont(new Font("Arial", Font.BOLD, 20));
		this.add(LabelTextStergeAngajat);
		
		JLabel LabelAdaugaEchipa = new JLabel();
		try {
			BufferedImage image = ImageIO.read(JFrameHelp.class.getResource("/img/echipaPlus.png"));
			ResizeImagine.creezaImagineRedimensionataDeTipPNG(image, (int) ra.getInaltimeComponenta(6),
					(int) ra.getInaltimeComponenta(6), "echipaPlus3", "png");
		} catch (IOException ioe) {
			System.out.println(ioe.getMessage());
		}
		LabelAdaugaEchipa.setIcon(new ImageIcon(JFrameHelp.class.getResource("/img/echipaPlus3.png")));
		LabelAdaugaEchipa.setBounds(ra.getPozitieX(10, width), ra.getPozitieY(39, height), ra.getLungimeComponenta(6), ra.getLungimeComponenta(6));
		this.add(LabelAdaugaEchipa);
		
		JLabel LabelTextAdaugaEchipa = new JLabel("Acest buton este folosit pentru adaugarea unei echipe.");
		LabelTextAdaugaEchipa.setBounds(ra.getPozitieX(21, width), ra.getPozitieY(38, height), ra.getLungimeComponenta(30), ra.getLungimeComponenta(7));
		LabelTextAdaugaEchipa.setFont(new Font("Arial", Font.BOLD, 20));
		this.add(LabelTextAdaugaEchipa);
		
		JLabel LabelStergeEchipa = new JLabel();
		try {
			BufferedImage image = ImageIO.read(JFrameHelp.class.getResource("/img/echipaMinus.png"));
			ResizeImagine.creezaImagineRedimensionataDeTipPNG(image, (int) ra.getInaltimeComponenta(6),
					(int) ra.getInaltimeComponenta(6), "echipaMinus3", "png");
		} catch (IOException ioe) {
			System.out.println(ioe.getMessage());
		}
		LabelStergeEchipa.setIcon(new ImageIcon(JFrameHelp.class.getResource("/img/echipaMinus3.png")));
		LabelStergeEchipa.setBounds(ra.getPozitieX(10, width), ra.getPozitieY(51, height), ra.getLungimeComponenta(6), ra.getLungimeComponenta(6));
		this.add(LabelStergeEchipa);
		
		JLabel LabelTextStergeEchipa = new JLabel("Acest buton este folosit pentru stergerea unui angajat.");
		LabelTextStergeEchipa.setBounds(ra.getPozitieX(21, width), ra.getPozitieY(50, height), ra.getLungimeComponenta(30), ra.getLungimeComponenta(7));
		LabelTextStergeEchipa.setFont(new Font("Arial", Font.BOLD, 20));
		this.add(LabelTextStergeEchipa);
		
		JLabel LabelCauta = new JLabel();
		try {
			BufferedImage image = ImageIO.read(JFrameHelp.class.getResource("/img/cautaAngajatInBD.png"));
			ResizeImagine.creezaImagineRedimensionataDeTipPNG(image, (int) ra.getInaltimeComponenta(6),
					(int) ra.getInaltimeComponenta(6), "cautaAngajatInBD3", "png");
		} catch (IOException ioe) {
			System.out.println(ioe.getMessage());
		}
		LabelCauta.setIcon(new ImageIcon(JFrameHelp.class.getResource("/img/cautaAngajatInBD3.png")));
		LabelCauta.setBounds(ra.getPozitieX(10, width), ra.getPozitieY(63, height), ra.getLungimeComponenta(6), ra.getLungimeComponenta(6));
		this.add(LabelCauta);
		
		JLabel LabelTextCauta = new JLabel("Acest buton este folosit pentru cautarea unui angajat in baza de date.");
		LabelTextCauta.setBounds(ra.getPozitieX(21, width), ra.getPozitieY(62, height), ra.getLungimeComponenta(35), ra.getLungimeComponenta(7));
		LabelTextCauta.setFont(new Font("Arial", Font.BOLD, 20));
		this.add(LabelTextCauta);
	
		JLabel lblNewLabel = new JLabel();
		lblNewLabel.setBounds(0, 0, (int)width,(int) height);
		lblNewLabel.setIcon(new ImageIcon(JFrameHelp.class.getResource("/img/homeBack2.jpg")));
		this.add(lblNewLabel);
	}

}
