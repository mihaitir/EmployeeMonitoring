package com.jpanel;


import java.awt.Dimension;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import com.JButton.ButonAngajatListaDisponibil;
import com.backend.Angajat;
import com.backend.ManipulareAngajatiDisponibili;
import com.tools.RezolutieAplicatie;

public class JPanelListaAngajatiDisponibiliPeIntregSistemul extends JPanel {

	
	private static final long serialVersionUID = 1L;
	public JPanelAdaugaAngajatInEchipa jpanelAdaugaAngajatInEchipa;

	public JPanelListaAngajatiDisponibiliPeIntregSistemul(JPanelAdaugaAngajatInEchipa jpanelAdaugaAngajatInEchipa) {
		this.jpanelAdaugaAngajatInEchipa = jpanelAdaugaAngajatInEchipa;
//		this.setLayout(null);
		RezolutieAplicatie ra = new RezolutieAplicatie();
		// acum urmeaza sa pun butoanele cu fiecare angajat in parte
		ManipulareAngajatiDisponibili mad = new ManipulareAngajatiDisponibili();
		ArrayList<Angajat> listaAngajati = mad.getListaAngajatiDisponibili();

		int width = ra.getLungimeComponenta(30, jpanelAdaugaAngajatInEchipa.width); // 30% din cat am repartizat pentru
																					// panelul cu adaugaAngajat
		int height = ra.getInaltimeComponenta(15) * listaAngajati.size();
		this.setPreferredSize(new Dimension(width, height));
		int y = 0;
		for (int i = 0; i < listaAngajati.size(); i++) {

			ButonAngajatListaDisponibil b = new ButonAngajatListaDisponibil(this, listaAngajati.get(i));
			b.setOpaque(false);
			b.setContentAreaFilled(false);
			b.setBorderPainted(false);
			if(listaAngajati.get(i).getNume().charAt(listaAngajati.get(i).getNume().length()-1) =='a')
				b.setIcon(new ImageIcon(JPanelListaAngajatiPerEchipa.class.getResource("/img/woman2.png")));
			else 
				b.setIcon(new ImageIcon(JPanelListaAngajatiPerEchipa.class.getResource("/img/angajat2.png")));
			b.setBounds(0, y, ra.getInaltimeComponenta(10), ra.getInaltimeComponenta(10));
			add(b);
			JLabel numeLabel = new JLabel();
			numeLabel.setText(listaAngajati.get(i).getNume());
			numeLabel.setBounds(0, y + ra.getInaltimeComponenta(1), ra.getInaltimeComponenta(10),
					ra.getInaltimeComponenta(1));
			add(numeLabel);
			// e cam irelevant sa pun y si setBounds ca am layoutul nu e NULL...
			y = y + ra.getInaltimeComponenta(15);
		}
	}

}
