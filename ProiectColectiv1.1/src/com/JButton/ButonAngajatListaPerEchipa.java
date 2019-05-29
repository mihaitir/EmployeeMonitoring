package com.JButton;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import com.backend.Angajat;
import com.jpanel.JPanelListaAngajatiPerEchipa;

public class ButonAngajatListaPerEchipa extends JButton {
	private static final long serialVersionUID = 1L;

	JPanelListaAngajatiPerEchipa jpanelListaAngajatiPerEchipa;
	Angajat angajat;

	public ButonAngajatListaPerEchipa(JPanelListaAngajatiPerEchipa jpanelListaAngajatiPerEchipa, Angajat angajat) {
		this.jpanelListaAngajatiPerEchipa = jpanelListaAngajatiPerEchipa;
		this.angajat = angajat;
		this.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
	}
}
