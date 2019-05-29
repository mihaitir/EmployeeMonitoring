package com.jframe;

import javax.swing.JFrame;
import com.tools.*;
import com.jpanel.JPanelHome;
import com.jpanel.JPanelMeniu;

public class JFramePrincipal extends JFrame {

	private static final long serialVersionUID = 1L;
	public JPanelHome jpanelhome;
	public JPanelMeniu jpanelmeniu;
	public JFramePrincipal() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		RezolutieAplicatie ra = new RezolutieAplicatie();
		setBounds(0, 0, (int) ra.getWidth(), (int) ra.getHeight());
		setLayout(null);

		jpanelmeniu = new JPanelMeniu(this);
		add(jpanelmeniu); 
		
		jpanelhome = new JPanelHome(this);
		add(jpanelhome);
		
	}

}
