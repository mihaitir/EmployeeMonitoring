package com.jpanel;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import com.tools.RezolutieAplicatie;

public class JPanelAdaugaAngajatInEchipa extends JPanel {

	private static final long serialVersionUID = 1L;
	public JPanelEchipaManager jpanelechipamanager;
	public double width;
	public double height;
	// jscrollPanel pe care pun lista tutor angajatilor din sistem
	// si un panel care contine butoane pentru data
	public JScrollPane jscrollpane; // pe asta pun lista aia de angajati din tot sistemul
	public JPanel panel; // aici pun formularul ala care contine ziua data luna,,,,si ok-ul cel mai

	public JPanelAdaugaAngajatInEchipa(JPanelEchipaManager jpanelechipamanager) {
		this.jpanelechipamanager = jpanelechipamanager;
		this.setLayout(null);
		RezolutieAplicatie ra = new RezolutieAplicatie();
		this.width = ra.getLungimeComponenta(25);
		this.height = ra.getInaltimeComponenta(80);
			JPanelListaAngajatiDisponibiliPeIntregSistemul jpanelListaAngajatiDisponibiliPeIntregSistemul = new JPanelListaAngajatiDisponibiliPeIntregSistemul(
				this);
		jscrollpane = new JScrollPane(jpanelListaAngajatiDisponibiliPeIntregSistemul);
		jscrollpane.setBounds(0, 0, ra.getLungimeComponenta(30, width), (int) height);
		add(jscrollpane);

		panel = new JPanel();
		panel.setBounds(ra.getPozitieX(35, width), ra.getPozitieY(5, height), ra.getLungimeComponenta(64, width),
				ra.getInaltimeComponenta(50, height));
		panel.setLayout(null);
		add(panel);

	}

}
