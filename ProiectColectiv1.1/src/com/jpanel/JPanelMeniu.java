package com.jpanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import com.jframe.JFrameHelp;
import com.jframe.JFramePrincipal;
import com.report.JFrameRaportBug;
import com.tools.ResizeImagine;
import com.tools.RezolutieAplicatie;

public class JPanelMeniu extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public JFramePrincipal jframeprincipal;
	public double width;
	public double height;
	
	public JPanelMeniu(JFramePrincipal jframeprincipal) {
		
		RezolutieAplicatie ra = new RezolutieAplicatie();
		this.width = ra.getLungimeComponenta(10);
		this.height = ra.getHeight();
		this.jframeprincipal = jframeprincipal;
		this.setBounds(0, 0, (int)width, (int)height);
		setLayout(null);
		
		JButton butonAdaugaAngajat = new JButton("");
		butonAdaugaAngajat.setOpaque(false);
		butonAdaugaAngajat.setContentAreaFilled(false);
		butonAdaugaAngajat.setBorderPainted(false);
		try {
			BufferedImage image = ImageIO.read(JPanelMeniu.class.getResource("/img/adaugaAngajat.png"));
			// sa fie imaginea patrata astfel luam 10% din inaltime

			ResizeImagine.creezaImagineRedimensionataDeTipPNG(image, (int) ra.getInaltimeComponenta(8),
					(int) ra.getInaltimeComponenta(8), "adaugaAngajat2", "png");
		} catch (IOException ioe) {
			System.out.println(ioe.getMessage());
		}
		butonAdaugaAngajat.setIcon(new ImageIcon(JPanelMeniu.class.getResource("/img/adaugaAngajat2.png")));
		
		butonAdaugaAngajat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				JPanelAdaugaAngajat jpaneladaugaangajat = new JPanelAdaugaAngajat(JPanelMeniu.this);
				jpaneladaugaangajat.setBounds(0, 0, (int) ra.getLungimeComponenta(90), (int) ra.getHeight());

				JPanelMeniu.this.jframeprincipal.jpanelhome.removeAll();
				JPanelMeniu.this.jframeprincipal.jpanelhome.add(jpaneladaugaangajat);
				JPanelMeniu.this.jframeprincipal.jpanelhome.repaint();
				JPanelMeniu.this.jframeprincipal.jpanelhome.revalidate();
				
			}
		});
		butonAdaugaAngajat.setBounds((int) ra.getPozitieX(33, width), (int) ra.getPozitieY(2, height),
				(int) ra.getInaltimeComponenta(8), (int) ra.getInaltimeComponenta(8));
		add(butonAdaugaAngajat);

		
		
		// Buton sterge angajat
		JButton butonEliminaAngajat = new JButton("");
		butonEliminaAngajat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JPanelStergeAngajat jpanelstergeangajat = new JPanelStergeAngajat(JPanelMeniu.this);
				jpanelstergeangajat.setBounds(0, 0, (int) ra.getLungimeComponenta(90), (int) ra.getHeight());

				JPanelMeniu.this.jframeprincipal.jpanelhome.removeAll();
				JPanelMeniu.this.jframeprincipal.jpanelhome.add(jpanelstergeangajat);
				JPanelMeniu.this.jframeprincipal.jpanelhome.repaint();
				JPanelMeniu.this.jframeprincipal.jpanelhome.revalidate();
			}
		});
		butonEliminaAngajat.setOpaque(false);
		butonEliminaAngajat.setContentAreaFilled(false);
		butonEliminaAngajat.setBorderPainted(false);
		try {
			BufferedImage image = ImageIO.read(JPanelHome.class.getResource("/img/stergeAngajat.png"));
			// sa fie imaginea patrata astfel luam 10% din inaltime

			ResizeImagine.creezaImagineRedimensionataDeTipPNG(image, (int) ra.getInaltimeComponenta(8),
					(int) ra.getInaltimeComponenta(8), "stergeAngajat2", "png");
		} catch (IOException ioe) {
			System.out.println(ioe.getMessage());
		}
		butonEliminaAngajat.setIcon(new ImageIcon(JPanelHome.class.getResource("/img/stergeAngajat2.png")));
		butonEliminaAngajat.setBounds((int) ra.getPozitieX(28, width), (int) ra.getPozitieY(11, height),
				(int) ra.getInaltimeComponenta(10), (int) ra.getInaltimeComponenta(10));
		add(butonEliminaAngajat);

		
		
		//Buton de adaugare a unei echipe
		JButton butonAdaugaEchipa = new JButton("");
		butonAdaugaEchipa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JPanelAdaugaEchipa jpaneladaugaechipa = new JPanelAdaugaEchipa(JPanelMeniu.this);
				 jpaneladaugaechipa.setBounds(0, 0, (int) ra.getLungimeComponenta(90), (int) ra.getHeight());

				JPanelMeniu.this.jframeprincipal.jpanelhome.removeAll();
				JPanelMeniu.this.jframeprincipal.jpanelhome.add(jpaneladaugaechipa);
				JPanelMeniu.this.jframeprincipal.jpanelhome.repaint();
				JPanelMeniu.this.jframeprincipal.jpanelhome.revalidate();
				
				
				
			}
		});
		butonAdaugaEchipa.setOpaque(false);
		butonAdaugaEchipa.setContentAreaFilled(false);
		butonAdaugaEchipa.setBorderPainted(false);
		try {
			BufferedImage image = ImageIO.read(JPanelHome.class.getResource("/img/echipaPlus.png"));
			// sa fie imaginea patrata astfel luam 10% din inaltime

			ResizeImagine.creezaImagineRedimensionataDeTipPNG(image, (int) ra.getInaltimeComponenta(8),
					(int) ra.getInaltimeComponenta(8), "echipaPlus2", "png");
		} catch (IOException ioe) {
			System.out.println(ioe.getMessage());
		}
		butonAdaugaEchipa.setIcon(new ImageIcon(JPanelHome.class.getResource("/img/echipaPlus2.png")));
		butonAdaugaEchipa.setBounds(ra.getPozitieX(30, width), ra.getPozitieY(23, height),(int) ra.getInaltimeComponenta(8), (int) ra.getInaltimeComponenta(8));
		add(butonAdaugaEchipa);

		
		
		//Buton eliminaEchipa
		JButton butonEliminaEchipa = new JButton("");
		butonEliminaEchipa.setOpaque(false);
		butonEliminaEchipa.setContentAreaFilled(false);
		butonEliminaEchipa.setBorderPainted(false);
		try {
			BufferedImage image = ImageIO.read(JPanelHome.class.getResource("/img/echipaMinus.png"));
			// sa fie imaginea patrata astfel luam 10% din inaltime

			ResizeImagine.creezaImagineRedimensionataDeTipPNG(image, (int) ra.getInaltimeComponenta(8),
					(int) ra.getInaltimeComponenta(8), "echipaMinus2", "png");
		} catch (IOException ioe) {
			System.out.println(ioe.getMessage());
		}
		butonEliminaEchipa.setIcon(new ImageIcon(JPanelHome.class.getResource("/img/EchipaMinus2.png")));
		butonEliminaEchipa.setBounds(ra.getPozitieX(30,width),ra.getPozitieY(33, height),(int) ra.getInaltimeComponenta(8), (int) ra.getInaltimeComponenta(8));
		butonEliminaEchipa.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				JPanelStergeEchipa jpanelstergeechipa = new JPanelStergeEchipa(JPanelMeniu.this); 
				jpanelstergeechipa.setBounds(0, 0, (int) ra.getLungimeComponenta(90), (int) ra.getHeight());
				JPanelMeniu.this.jframeprincipal.jpanelhome.removeAll();
				JPanelMeniu.this.jframeprincipal.jpanelhome.add(jpanelstergeechipa);
				JPanelMeniu.this.jframeprincipal.jpanelhome.repaint();
				JPanelMeniu.this.jframeprincipal.jpanelhome.revalidate();
			}
			
		});
		add(butonEliminaEchipa);
		
		
	

		JButton butonHelp = new JButton("");
		butonHelp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				JFrameHelp jfp= new JFrameHelp(JPanelMeniu.this);
				jfp.setVisible(true);
			}
		});
		butonHelp.setOpaque(false);
		butonHelp.setContentAreaFilled(false);
		butonHelp.setBorderPainted(false);
		try {
			BufferedImage image = ImageIO.read(JPanelHome.class.getResource("/img/imagineHelp.png"));
			// sa fie imaginea patrata astfel luam 10% din inaltime

			ResizeImagine.creezaImagineRedimensionataDeTipPNG(image, (int) ra.getInaltimeComponenta(8),
					(int) ra.getInaltimeComponenta(8), "imagineHelp2", "png");
		} catch (IOException ioe) {
			System.out.println(ioe.getMessage());
		}
		butonHelp.setIcon(new ImageIcon(JPanelHome.class.getResource("/img/imagineHelp2.png")));
		butonHelp.setBounds(ra.getPozitieX(30,width), ra.getPozitieY(44, height),(int) ra.getInaltimeComponenta(8), (int) ra.getInaltimeComponenta(8));
	butonHelp.addActionListener(new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
//			JFrameHelp jframeHelp = new JFrameHelp(JPanelHome.this);
//			jframeHelp.setVisible(true);
		}
		
	});
		add(butonHelp);
		
		JButton butonBug = new JButton("");
		butonBug.setOpaque(false);
		butonBug.setContentAreaFilled(false);
		butonBug.setBorderPainted(false);
		try {
			BufferedImage image = ImageIO.read(JPanelHome.class.getResource("/img/bug.png"));
			// sa fie imaginea patrata astfel luam 10% din inaltime

			ResizeImagine.creezaImagineRedimensionataDeTipPNG(image, (int) ra.getInaltimeComponenta(8),
					(int) ra.getInaltimeComponenta(8), "bug2", "png");
		} catch (IOException ioe) {
			System.out.println(ioe.getMessage());
		}
		butonBug.setIcon(new ImageIcon(JPanelHome.class.getResource("/img/bug2.png")));
		butonBug.setBounds(ra.getPozitieX(30, width), ra.getPozitieY(55, height),(int) ra.getInaltimeComponenta(10), (int) ra.getInaltimeComponenta(10));
		butonBug.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
					JFrameRaportBug jfrb = new JFrameRaportBug();
					jfrb.setVisible(true);
			}
			
		});
		add(butonBug);
		
		
		JButton butonTabel = new JButton("");
		butonTabel.setOpaque(false);
		butonTabel.setContentAreaFilled(false);
		butonTabel.setBorderPainted(false);
		try {
			BufferedImage image = ImageIO.read(JPanelHome.class.getResource("/img/imagineTabel.png"));
			// sa fie imaginea patrata astfel luam 10% din inaltime

			ResizeImagine.creezaImagineRedimensionataDeTipPNG(image, (int) ra.getInaltimeComponenta(8),
					(int) ra.getInaltimeComponenta(8), "imagineTabel2", "png");
		} catch (IOException ioe) {
			System.out.println(ioe.getMessage());
		}
		butonTabel.setIcon(new ImageIcon(JPanelHome.class.getResource("/img/imagineTabel2.png")));
		butonTabel.setBounds(ra.getPozitieX(30, width),ra.getPozitieY(66, height), (int) ra.getInaltimeComponenta(8), (int) ra.getInaltimeComponenta(8));
		butonTabel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				JPanelEchipeSubFormaTabelara jpanelEchipeSubFormaTabelara = new JPanelEchipeSubFormaTabelara(JPanelMeniu.this); 
				jpanelEchipeSubFormaTabelara.setBounds(0, 0, (int) ra.getLungimeComponenta(90), (int) ra.getHeight());
				JPanelMeniu.this.jframeprincipal.jpanelhome.removeAll();
				JPanelMeniu.this.jframeprincipal.jpanelhome.add(jpanelEchipeSubFormaTabelara);
				JPanelMeniu.this.jframeprincipal.jpanelhome.repaint();
				JPanelMeniu.this.jframeprincipal.jpanelhome.revalidate();
			}
		});
		add(butonTabel);
		
		
		JButton butonExit = new JButton("");
		butonExit.setOpaque(false);
		butonExit.setContentAreaFilled(false);
		butonExit.setBorderPainted(false);
		try {
			BufferedImage image = ImageIO.read(JPanelHome.class.getResource("/img/imagineExit.png"));
			// sa fie imaginea patrata astfel luam 10% din inaltime

			ResizeImagine.creezaImagineRedimensionataDeTipPNG(image, (int) ra.getInaltimeComponenta(8),
					(int) ra.getInaltimeComponenta(8), "imagineExit2", "png");
		} catch (IOException ioe) {
			System.out.println(ioe.getMessage());
		}
		butonExit.setIcon(new ImageIcon(JPanelHome.class.getResource("/img/imagineExit2.png")));
		butonExit.setBounds(ra.getPozitieX(30, width),ra.getPozitieY(77, height), (int) ra.getInaltimeComponenta(8), (int) ra.getInaltimeComponenta(8));
		butonExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(1);
			}
		});
		
		add(butonExit);
		

		JLabel labelBackground = new JLabel();
		labelBackground.setBounds(0, 0, (int) width, (int) height);
		try {
			BufferedImage image = ImageIO.read(JPanelMeniu.class.getResource("/img/homeBack.jpg"));
			ResizeImagine.creezaImagineRedimensionataDeTipJPG(image, (int) width, (int) height, "homeBackMeniu", "jpg");
		} catch (IOException ioe) {
			System.out.println(ioe.getMessage());
		}
		labelBackground.setIcon(new ImageIcon(JPanelMeniu.class.getResource("/img/homeBackMeniu.jpg")));
		add(labelBackground);
		
		

	}

}
