package com.JButton;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import com.backend.Angajat;
import com.jpanel.JPanelListaAngajatiDisponibiliPeIntregSistemul;
import com.jpanel.JPanelSpecificTimpAngajat;
import com.tools.RezolutieAplicatie;

public class ButonAngajatListaDisponibil extends JButton {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public Angajat angajat;
	public JPanelListaAngajatiDisponibiliPeIntregSistemul jpanelListaAngajatiDisponibiliPeIntregSistemul;

	public ButonAngajatListaDisponibil(
			JPanelListaAngajatiDisponibiliPeIntregSistemul jpanelListaAngajatiDisponibiliPeIntregSistemul,
			Angajat angajat) {
		this.angajat = angajat;
		this.jpanelListaAngajatiDisponibiliPeIntregSistemul = jpanelListaAngajatiDisponibiliPeIntregSistemul;
		RezolutieAplicatie ra = new RezolutieAplicatie();
		this.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				jpanelListaAngajatiDisponibiliPeIntregSistemul.jpanelAdaugaAngajatInEchipa.panel.removeAll();

				JPanelSpecificTimpAngajat jpanelSpecifiTimpAngajat = new JPanelSpecificTimpAngajat(
						ButonAngajatListaDisponibil.this, angajat);
				jpanelSpecifiTimpAngajat.setBounds(0, 0,
						ra.getLungimeComponenta(64,
								jpanelListaAngajatiDisponibiliPeIntregSistemul.jpanelAdaugaAngajatInEchipa.width),
						ra.getInaltimeComponenta(50,
								jpanelListaAngajatiDisponibiliPeIntregSistemul.jpanelAdaugaAngajatInEchipa.height));
				jpanelListaAngajatiDisponibiliPeIntregSistemul.jpanelAdaugaAngajatInEchipa.panel
						.add(jpanelSpecifiTimpAngajat);
				jpanelListaAngajatiDisponibiliPeIntregSistemul.jpanelAdaugaAngajatInEchipa.panel.repaint();
				jpanelListaAngajatiDisponibiliPeIntregSistemul.jpanelAdaugaAngajatInEchipa.panel.revalidate();

			}
		});
	}

}
