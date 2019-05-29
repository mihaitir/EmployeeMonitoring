package com.jpanel;

import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.JButton.ButonAngajatListaPerEchipa;
import com.JButton.*;
import com.backend.*;
import com.tools.ResizeImagine;
import com.tools.RezolutieAplicatie;

public class JPanelListaAngajatiPerEchipa extends JPanel {
	private static final long serialVersionUID = 1L;
	public JPanelEchipaManager jpanelechipamanager;
	public ArrayList<Angajat> listaAngajati;
	public ArrayList<ButonAngajatListaPerEchipa> listaButoane = new ArrayList<ButonAngajatListaPerEchipa>();

	public JPanelListaAngajatiPerEchipa(JPanelEchipaManager jpanelechipamanager) {
		this.jpanelechipamanager = jpanelechipamanager;
		this.setLayout(null);
		listaAngajati = jpanelechipamanager.echipa.getListaAngajati();
		RezolutieAplicatie ra = new RezolutieAplicatie();
		this.setPreferredSize(
				new Dimension(ra.getLungimeComponenta(25), ra.getInaltimeComponenta(12) * listaAngajati.size()));
		int y = 0;

		// o singura data este nevoie sa redimensionez butonul de stergere...el ramane
		// acelasi pentru toti angajatii
		try {
			BufferedImage image = ImageIO.read(JPanelHome.class.getResource("/img/eliminaAngajat.png"));
			// sa fie imaginea patrata astfel luam 10% din inaltime

			ResizeImagine.creezaImagineRedimensionataDeTipPNG(image, (int) ra.getInaltimeComponenta(5),
					(int) ra.getInaltimeComponenta(5), "eliminaAngajat2", "png");
		} catch (IOException ioe) {
			System.out.println(ioe.getMessage());
		}

		try {
			BufferedImage image = ImageIO.read(JPanelHome.class.getResource("/img/angajat.png"));
			// sa fie imaginea patrata astfel luam 10% din inaltime

			ResizeImagine.creezaImagineRedimensionataDeTipPNG(image, (int) ra.getInaltimeComponenta(10),
					(int) ra.getInaltimeComponenta(10), "angajat2", "png");

			BufferedImage image2 = ImageIO.read(JPanelHome.class.getResource("/img/transferAngajat.png"));
			// sa fie imaginea patrata astfel luam 10% din inaltime

			ResizeImagine.creezaImagineRedimensionataDeTipPNG(image2, (int) ra.getInaltimeComponenta(5),
					(int) ra.getInaltimeComponenta(5), "transferAngajat2", "png");
		} catch (IOException ioe) {
			System.out.println(ioe.getMessage());
		}
		
		try {
			BufferedImage image = ImageIO.read(JPanelHome.class.getResource("/img/woman.png"));
			// sa fie imaginea patrata astfel luam 10% din inaltime

			ResizeImagine.creezaImagineRedimensionataDeTipPNG(image, (int) ra.getInaltimeComponenta(10),
					(int) ra.getInaltimeComponenta(10), "woman2", "png");

		} catch (IOException ioe) {
			System.out.println(ioe.getMessage());
		}
		

		for (int i = 0; i < listaAngajati.size(); i++) {

			ButonAngajatListaPerEchipa b = new ButonAngajatListaPerEchipa(this, listaAngajati.get(i));
			b.setOpaque(false);
			b.setContentAreaFilled(false);
			b.setBorderPainted(false);

			if(listaAngajati.get(i).getNume().charAt(listaAngajati.get(i).getNume().length()-1) =='a')
				b.setIcon(new ImageIcon(JPanelListaAngajatiPerEchipa.class.getResource("/img/woman2.png")));
			else 
				b.setIcon(new ImageIcon(JPanelListaAngajatiPerEchipa.class.getResource("/img/angajat2.png")));
			b.setBounds(0, y, ra.getInaltimeComponenta(10), ra.getInaltimeComponenta(10));
			add(b);
			// mai jos pun informatiile despre un angajat nume,si pana cand e atribuit
			// echipei...
			JLabel labelNume = new JLabel("Nume :" + listaAngajati.get(i).getNume());
			labelNume.setBounds(ra.getInaltimeComponenta(12), y, ra.getLungimeComponenta(10),
					ra.getInaltimeComponenta(2));
			add(labelNume);

			JLabel labelTimp1 = new JLabel("De la :" + listaAngajati.get(i).getTimpInceput());
			labelTimp1.setBounds(ra.getInaltimeComponenta(12), y + ra.getInaltimeComponenta(2),
					ra.getLungimeComponenta(10), ra.getInaltimeComponenta(5));
			add(labelTimp1);

			JLabel labelTimp2 = new JLabel("Pana la :" + listaAngajati.get(i).getTimpSfarsit());
			labelTimp2.setBounds(ra.getInaltimeComponenta(12), y + ra.getInaltimeComponenta(4),
					ra.getLungimeComponenta(10), ra.getInaltimeComponenta(5));
			add(labelTimp2);
			// si acum pun butonul de stergere anumit angajat
			ButonTransferaAngajat bta = new ButonTransferaAngajat(this, listaAngajati.get(i));
			bta.setOpaque(false);
			bta.setContentAreaFilled(false);
			bta.setBorderPainted(false);
			bta.setIcon(new ImageIcon(JPanelListaAngajatiPerEchipa.class.getResource("/img/transferAngajat2.png")));
			bta.setBounds(ra.getLungimeComponenta(19), y, ra.getInaltimeComponenta(5), ra.getInaltimeComponenta(5));
			add(bta);

		/*	ButonStergeAngajatDinListaDisponibil b2 = new ButonStergeAngajatDinListaDisponibil(this,
					listaAngajati.get(i));
			b2.setOpaque(false);
			b2.setContentAreaFilled(false);
			b2.setBorderPainted(false);
			b2.setIcon(new ImageIcon(JPanelListaAngajatiPerEchipa.class.getResource("/img/eliminaAngajat2.png")));
			b2.setBounds(ra.getLungimeComponenta(22), y, ra.getInaltimeComponenta(5), ra.getInaltimeComponenta(5));
			add(b2);*/

			y = y + ra.getInaltimeComponenta(12);
		}
	}
}