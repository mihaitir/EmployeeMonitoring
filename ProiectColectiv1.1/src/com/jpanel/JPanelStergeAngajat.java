package com.jpanel;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import com.backend.Angajat;
import com.jframe.JFrameAlertaEchipaNeselectata;
import com.jframe.JFrameMesajRaportSucces;
import com.jframe.JFrameMesajStergeAngajatSucces;
import com.raport.RaportAngajat;
import com.tools.ResizeImagine;
import com.tools.RezolutieAplicatie;

public class JPanelStergeAngajat extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public double width;
	public double height;
	public JPanelMeniu jpanelmeniu;
	public ArrayList<Angajat> listaAngajati = new ArrayList<Angajat>();
	public JLabel labelBackground;
	JTable jt;
	
	public JPanelStergeAngajat(JPanelMeniu jpanelmeniu) {
	
		this.jpanelmeniu = jpanelmeniu;
		this.setLayout(null);
		RezolutieAplicatie ra = new RezolutieAplicatie();
		width = ra.getLungimeComponenta(90);
		height = ra.getHeight();
		setBounds((int) ra.getPozitieX(10), (int) ra.getPozitieY(10), (int) width, (int) height);
		try {
			String url = "jdbc:mysql://localhost:3306/sys?&serverTimezone=UTC";
			String user = "root";
			String password = "password";

			Connection conn = DriverManager.getConnection(url, user, password);
			Statement st = conn.createStatement();

			ResultSet rs = st.executeQuery("select * from angajati");

			while (rs.next()) {
				int id = rs.getInt("idAngajat");
				String nume = rs.getString("nume");
				Angajat a = new Angajat(id,nume,null,false,null,null);
				listaAngajati.add(a);
			}
			conn.close();
		} catch (SQLException sqle) {
			System.out.println(sqle.getMessage());
		}
		
		ArrayList<String> lista = new ArrayList<String>();
		lista.add("ID");
		lista.add("Nume angajati");

		ArrayList<ArrayList<String>> outer = new ArrayList<ArrayList<String>>();

		for (int i = 0; i < listaAngajati.size(); i++) {
			ArrayList<String> inner = new ArrayList<String>();
			inner.add(String.valueOf(listaAngajati.get(i).getId()));
			inner.add(listaAngajati.get(i).getNume());
			outer.add(inner);
		}

		String[][] array = new String[outer.size()][];
		for (int i = 0; i < outer.size(); i++) {
			ArrayList<String> row = outer.get(i);
			array[i] = row.toArray(new String[row.size()]);
		}
		String[] column = lista.toArray(new String[0]);
		jt = new JTable(array, column);
		jt.setRowHeight(65);
		jt.setFont(new Font("Serif", Font.BOLD, 25));
		jt.setBounds(0, 0, ra.getLungimeComponenta(70, width), ra.getInaltimeComponenta(20, height));

		// folosim clasa DefaultTableCellRenderer pentru a obtine o referinta pe care sa
		// o folsim drept model al unei coloane
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(JLabel.CENTER); // alegem alinierea
		jt.getColumnModel().getColumn(0).setCellRenderer(centerRenderer); // setam acest model pentru fiecare coloana in																			// parte...
		jt.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);

		JScrollPane sp = new JScrollPane(jt);
		sp.setBounds(ra.getPozitieX(25, width), ra.getPozitieY(10, height), ra.getLungimeComponenta(70, height),
				ra.getInaltimeComponenta(30, width));
		add(sp);

		JButton butonStergeAngajat = new JButton();
		butonStergeAngajat.setBounds(ra.getPozitieX(45, width), ra.getPozitieY(80, height),
				ra.getInaltimeComponenta(10), ra.getInaltimeComponenta(10));
		ActionListener actiuneButonAfisareEchipa = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (JPanelStergeAngajat.this.jt.getSelectedRow() == -1) {
					JFrameAlertaEchipaNeselectata jfaen = new JFrameAlertaEchipaNeselectata();
					jfaen.setVisible(true);
				}
				try {
					
				int id = Integer.parseInt(
						(String) JPanelStergeAngajat.this.jt.getValueAt(JPanelStergeAngajat.this.jt.getSelectedRow(), 0));
				
					String url = "jdbc:mysql://localhost:3306/sys?&serverTimezone=UTC";
					String user = "root";
					String password = "password";

					Connection conn = DriverManager.getConnection(url, user, password);
					PreparedStatement ps = conn.prepareStatement("delete from angajati where idAngajat = ?");
					ps.setInt(1, id);
					ps.execute();
					JPanelStergeAngajat jpanelstergeangajat = new JPanelStergeAngajat(JPanelStergeAngajat.this.jpanelmeniu);
					jpanelstergeangajat.setBounds(0, 0, (int) ra.getLungimeComponenta(90), (int) ra.getHeight());
					JPanelStergeAngajat.this.jpanelmeniu.jframeprincipal.jpanelhome.removeAll();
					JPanelStergeAngajat.this.jpanelmeniu.jframeprincipal.jpanelhome.add(jpanelstergeangajat);
					JPanelStergeAngajat.this.jpanelmeniu.jframeprincipal.jpanelhome.repaint();
					JPanelStergeAngajat.this.jpanelmeniu.jframeprincipal.jpanelhome.revalidate();

					JFrameMesajStergeAngajatSucces jfmsas = new JFrameMesajStergeAngajatSucces();
					jfmsas.setVisible(true);

				} catch (ArrayIndexOutOfBoundsException aiobe) {

				} catch (SQLException sqle) {
					System.out.println(sqle.getMessage());
				}
			}
		};
		butonStergeAngajat.addActionListener(actiuneButonAfisareEchipa);
		try {
			BufferedImage image = ImageIO.read(JPanelHome.class.getResource("/img/removeTeam.png"));
			ResizeImagine.creezaImagineRedimensionataDeTipPNG(image, (int) ra.getInaltimeComponenta(10),
					(int) ra.getInaltimeComponenta(10), "removeTeam2", "png");
		} catch (IOException ioe) {
			System.out.println(ioe.getMessage());
		}

		butonStergeAngajat.setIcon(new ImageIcon(JPanelHome.class.getResource("/img/removeTeam2.png")));
		butonStergeAngajat.setOpaque(false);
		butonStergeAngajat.setContentAreaFilled(false);
		butonStergeAngajat.setBorderPainted(false);
		add(butonStergeAngajat);

		JButton butonHome = new JButton();
		butonHome.setBounds(ra.getLungimeComponenta(93, width), 0, ra.getInaltimeComponenta(10),
				ra.getInaltimeComponenta(10));
		ActionListener actiuneButon = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				JPanelStergeAngajat.this.jpanelmeniu.jframeprincipal.jpanelhome.removeAll();
				JPanelHome j = new JPanelHome(JPanelStergeAngajat.this.jpanelmeniu.jframeprincipal);
				j.setBounds(0, 0, (int) ra.getLungimeComponenta(90), (int) ra.getHeight());
				JPanelStergeAngajat.this.jpanelmeniu.jframeprincipal.jpanelhome.add(j);
				JPanelStergeAngajat.this.jpanelmeniu.jframeprincipal.jpanelhome.repaint();
				JPanelStergeAngajat.this.jpanelmeniu.jframeprincipal.jpanelhome.revalidate();

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
		
		

		JButton butonDetalii = new JButton();
		butonDetalii.setBounds(ra.getLungimeComponenta(93, width), ra.getInaltimeComponenta(15), ra.getInaltimeComponenta(10),
				ra.getInaltimeComponenta(10));
		ActionListener actiuneButonDetalii = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (JPanelStergeAngajat.this.jt.getSelectedRow() == -1) {
					JFrameAlertaEchipaNeselectata jfaen = new JFrameAlertaEchipaNeselectata();
					jfaen.setVisible(true);
				}
				
				else {
				int id = Integer.parseInt(
						(String) JPanelStergeAngajat.this.jt.getValueAt(JPanelStergeAngajat.this.jt.getSelectedRow(), 0));
				String nume = (String) 
						JPanelStergeAngajat.this.jt.getValueAt(JPanelStergeAngajat.this.jt.getSelectedRow(),1);
				
				Angajat a = new Angajat();
				a.setId(id);
				a.setNume(nume);
				@SuppressWarnings("unused")
				RaportAngajat ra = new RaportAngajat(a);
				JFrameMesajRaportSucces jfmrs = new JFrameMesajRaportSucces();
				jfmrs.setVisible(true);
				}   
			}
		};
		butonDetalii.addActionListener(actiuneButonDetalii);
		try {
			BufferedImage image = ImageIO.read(JPanelHome.class.getResource("/img/detalii.png"));
			ResizeImagine.creezaImagineRedimensionataDeTipPNG(image, (int) ra.getInaltimeComponenta(10),
					(int) ra.getInaltimeComponenta(10), "detalii2", "png");
		} catch (IOException ioe) {
			System.out.println(ioe.getMessage());
		}

		butonDetalii.setIcon(new ImageIcon(JPanelHome.class.getResource("/img/detalii2.png")));
		butonDetalii.setOpaque(false);
		butonDetalii.setContentAreaFilled(false);
		butonDetalii.setBorderPainted(false);
		add(butonDetalii);
			
		JLabel jlt = new JLabel("Alegeti angajatul pe care doriti sa il stergeti");
		jlt.setBounds(ra.getPozitieX(30), ra.getPozitieY(2), ra.getLungimeComponenta(35, width), ra.getInaltimeComponenta(10, height));
		jlt.setFont(new Font("Tahoma", Font.PLAIN, 25));
		add(jlt);

		labelBackground = new JLabel();
		labelBackground.setBounds(0, 0, (int) width, (int) height);
		labelBackground.setIcon(new ImageIcon(JPanelHome.class.getResource("/img/homeBack2.jpg")));
		add(labelBackground);

		
	}

}
