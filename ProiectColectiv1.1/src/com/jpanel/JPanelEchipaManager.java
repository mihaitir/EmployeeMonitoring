package com.jpanel;

import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import com.backend.Echipa;
import com.jframe.JFrameMesajRaportSucces;
import com.raport.RaportEchipa;
import com.tools.ResizeImagine;
import com.tools.RezolutieAplicatie;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JPanelEchipaManager extends JPanel {

	private static final long serialVersionUID = 1L;

	
	public JPanelEchipeSubFormaTabelara jpanelEchipeSubFormaTabelara;
	public double width;
	public double height;
	public Echipa echipa;
	public JLabel labelBackground;
	public JScrollPane jscrollpane;
	public JPanel panelDreapta;
	public JPanelListaAngajatiPerEchipa jpanellistaangajatiperechipa;

	public JPanelEchipaManager(JPanelEchipeSubFormaTabelara jpanelEchipeSubFormaTabelara, Echipa echipa) {
		this.jpanelEchipeSubFormaTabelara = jpanelEchipeSubFormaTabelara;
		this.echipa = echipa;


		jpanellistaangajatiperechipa = new JPanelListaAngajatiPerEchipa(this);
		RezolutieAplicatie ra = new RezolutieAplicatie();
		this.width = ra.getLungimeComponenta(90);
		this.height = ra.getHeight();

		setLayout(null);
		setBounds(0, 0, (int) ra.getLungimeComponenta(90), (int) ra.getHeight());

		JLabel labelNumeEchipa = new JLabel(echipa.getNume());
		labelNumeEchipa.setFont(new Font("Tahoma", Font.PLAIN, 25));
		labelNumeEchipa.setBounds((int) width / 2, 0, ra.getLungimeComponenta(15, width), ra.getInaltimeComponenta(5));
		add(labelNumeEchipa);

		JButton butonHome = new JButton();
		butonHome.setBounds(ra.getLungimeComponenta(93, width), 0, ra.getInaltimeComponenta(10),
				ra.getInaltimeComponenta(10));
		ActionListener actiuneButon = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JPanelEchipaManager.this.jpanelEchipeSubFormaTabelara.jpanelmeniu.jframeprincipal.jpanelhome.removeAll();
				JPanelHome j = new JPanelHome(JPanelEchipaManager.this.jpanelEchipeSubFormaTabelara.jpanelmeniu.jframeprincipal);
				j.setBounds(0, 0, (int) ra.getLungimeComponenta(90), (int) ra.getHeight());
				JPanelEchipaManager.this.jpanelEchipeSubFormaTabelara.jpanelmeniu.jframeprincipal.jpanelhome.add(j);
				JPanelEchipaManager.this.jpanelEchipeSubFormaTabelara.jpanelmeniu.jframeprincipal.repaint();
				JPanelEchipaManager.this.jpanelEchipeSubFormaTabelara.jpanelmeniu.jframeprincipal.revalidate();
			}
		};
		butonHome.addActionListener(actiuneButon);
		try {
			BufferedImage image = ImageIO.read(JPanelHome.class.getResource("/img/home.png"));

			ResizeImagine.creezaImagineRedimensionataDeTipPNG(image, (int) ra.getInaltimeComponenta(10),
					(int) ra.getInaltimeComponenta(10), "home2", "png");
		} catch (IOException ioe) {
			System.out.println(ioe.getMessage());
		}
		butonHome.setIcon(new ImageIcon(JPanelHome.class.getResource("/img/home2.png")));
		butonHome.setOpaque(false);
		butonHome.setContentAreaFilled(false);
		butonHome.setBorderPainted(false);
		add(butonHome);

		jscrollpane = new JScrollPane(jpanellistaangajatiperechipa);
		jscrollpane.setBounds(ra.getPozitieX(10, width), ra.getPozitieY(10, height), ra.getLungimeComponenta(25),
				ra.getInaltimeComponenta(80, height));
		add(jscrollpane);
		
		panelDreapta = new JPanel();
		panelDreapta.setLayout(null);
		panelDreapta.setBounds(ra.getPozitieX(50, width), ra.getPozitieY(10, height),
				ra.getLungimeComponenta(25), ra.getInaltimeComponenta(80, height));
		add(panelDreapta);

		JButton butonAdaugaAngajatInEchipa = new JButton("");
		butonAdaugaAngajatInEchipa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				JPanelEchipaManager.this.panelDreapta.removeAll();
				JPanelAdaugaAngajatInEchipa jaaie = new JPanelAdaugaAngajatInEchipa(JPanelEchipaManager.this);	
				jaaie.setBounds(0, 0, ra.getLungimeComponenta(25), ra.getInaltimeComponenta(80, height));
				JPanelEchipaManager.this.panelDreapta.add(jaaie);
				JPanelEchipaManager.this.panelDreapta.repaint();
				JPanelEchipaManager.this.panelDreapta.revalidate();
				
			}
		});
		butonAdaugaAngajatInEchipa.setOpaque(false);
		butonAdaugaAngajatInEchipa.setContentAreaFilled(false);
		butonAdaugaAngajatInEchipa.setBorderPainted(false);
		try {
			BufferedImage image = ImageIO.read(JPanelHome.class.getResource("/img/adaugaAngajatInEchipa.png"));
			// sa fie imaginea patrata astfel luam 10% din inaltime

			ResizeImagine.creezaImagineRedimensionataDeTipPNG(image, (int) ra.getInaltimeComponenta(10),
					(int) ra.getInaltimeComponenta(10), "adaugaAngajatInEchipa2", "png");
		} catch (IOException ioe) {
			System.out.println(ioe.getMessage());
		}
		butonAdaugaAngajatInEchipa
				.setIcon(new ImageIcon(JPanelHome.class.getResource("/img/adaugaAngajatInEchipa2.png")));
		butonAdaugaAngajatInEchipa.setBounds(ra.getLungimeComponenta(93, width), ra.getPozitieY(13, height),
				(int) ra.getInaltimeComponenta(10), (int) ra.getInaltimeComponenta(10));
		add(butonAdaugaAngajatInEchipa);

		JButton butonRaportEchipa = new JButton("");
		butonRaportEchipa.setOpaque(false);
		butonRaportEchipa.setContentAreaFilled(false);
		butonRaportEchipa.setBorderPainted(false);
		try {
			BufferedImage image = ImageIO.read(JPanelHome.class.getResource("/img/raportEchipa.png"));
			// sa fie imaginea patrata astfel luam 10% din inaltime

			ResizeImagine.creezaImagineRedimensionataDeTipPNG(image, (int) ra.getInaltimeComponenta(10),
					(int) ra.getInaltimeComponenta(10), "raportEchipa2", "png");
		} catch (IOException ioe) {
			System.out.println(ioe.getMessage());
		}
		butonRaportEchipa.setIcon(new ImageIcon(JPanelHome.class.getResource("/img/raportEchipa2.png")));
		butonRaportEchipa.setBounds(ra.getLungimeComponenta(93, width), ra.getPozitieY(26, height),
				(int) ra.getInaltimeComponenta(10), (int) ra.getInaltimeComponenta(10));
		butonRaportEchipa.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				@SuppressWarnings("unused")
				RaportEchipa ra = new RaportEchipa(JPanelEchipaManager.this.echipa);
				JFrameMesajRaportSucces jfmrs = new JFrameMesajRaportSucces();
				jfmrs.setVisible(true);
				
			}
			
		});
		add(butonRaportEchipa);
	
		labelBackground = new JLabel();
		labelBackground.setBounds(0, 0, (int) width, (int) height);
		labelBackground.setIcon(new ImageIcon(JPanelHome.class.getResource("/img/homeBack2.jpg")));
		add(labelBackground);

	}

}
