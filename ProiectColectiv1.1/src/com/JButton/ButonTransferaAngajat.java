package com.JButton;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import com.backend.Angajat;
import com.jpanel.JPanelListaAngajatiPerEchipa;
import com.jpanel.JPanelTransferAngajat;
import com.tools.RezolutieAplicatie;

public class ButonTransferaAngajat extends JButton {

	private static final long serialVersionUID = 1L;
	JPanelListaAngajatiPerEchipa jpanellistaanagajatiperechipa;
	Angajat angajat;
	public int width;
	public int height;

	public ButonTransferaAngajat(JPanelListaAngajatiPerEchipa jpanellistaanagajatiperechipa, Angajat angajat) {
		this.jpanellistaanagajatiperechipa = jpanellistaanagajatiperechipa;
		this.angajat = angajat;
		RezolutieAplicatie ra = new RezolutieAplicatie();
		this.width = ra.getLungimeComponenta(90);
		this.height = (int) ra.getHeight();
		this.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				ButonTransferaAngajat.this.jpanellistaanagajatiperechipa.jpanelechipamanager.panelDreapta.removeAll();
				JPanelTransferAngajat pta = new JPanelTransferAngajat(jpanellistaanagajatiperechipa.jpanelechipamanager,angajat);
				pta.setBounds(0, 0, ra.getLungimeComponenta(25), ra.getInaltimeComponenta(80, height));
				jpanellistaanagajatiperechipa.jpanelechipamanager.panelDreapta.add(pta);
				jpanellistaanagajatiperechipa.jpanelechipamanager.repaint();
				jpanellistaanagajatiperechipa.jpanelechipamanager.revalidate();
			}

		});
	}

}
