package com.jpanel;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
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
import com.backend.Echipa;
import com.jframe.JFrameAlertaEchipaNeselectata;
import com.tools.ResizeImagine;
import com.tools.RezolutieAplicatie;

public class JPanelEchipeSubFormaTabelara extends JPanel {

	private static final long serialVersionUID = 1L;
	public JPanelMeniu jpanelmeniu;
	public double width;
	public double height;
	public JTable jt;
	public JLabel labelBackground;
	public ArrayList<Echipa> listaEchipe = new ArrayList<Echipa>();

	public JPanelEchipeSubFormaTabelara(JPanelMeniu jpanelmeniu) {
		this.jpanelmeniu = jpanelmeniu;
		RezolutieAplicatie ra = new RezolutieAplicatie();
		width = ra.getLungimeComponenta(90); // jpanel are lungime 75% din componenta parinte
		height = ra.getHeight();
		setLayout(null);
		try {
			String url = "jdbc:mysql://localhost:3306/sys?&serverTimezone=UTC";
			String user = "root";
			String password = "password";

			Connection conn = DriverManager.getConnection(url, user, password);
			Statement st = conn.createStatement();

			ResultSet rs = st.executeQuery("select * from echipe");

			while (rs.next()) {
				int id = rs.getInt("id");
				String nume = rs.getString("numeEchipa");
				String departament = rs.getString("departament");
				Echipa e = new Echipa(id, nume, departament);
				listaEchipe.add(e);
			}
		} catch (SQLException sqle) {
			System.out.println(sqle.getMessage());
		}

		ArrayList<String> lista = new ArrayList<String>();
		lista.add("ID");
		lista.add("Nume echipa");
		lista.add("Departament");
		lista.add("Numar angajati");

		ArrayList<ArrayList<String>> outer = new ArrayList<ArrayList<String>>();

		for (int i = 0; i < listaEchipe.size(); i++) {
			ArrayList<String> inner = new ArrayList<String>();
			inner.add(String.valueOf(listaEchipe.get(i).getId()));
			inner.add(listaEchipe.get(i).getNume());
			inner.add(listaEchipe.get(i).getDepartament());
			inner.add(String.valueOf(listaEchipe.get(i).getListaAngajati().size()));
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
		jt.getColumnModel().getColumn(0).setCellRenderer(centerRenderer); // setam acest model pentru fiecare coloana in
																			// parte...
		jt.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
		jt.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
		jt.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);

		JScrollPane sp = new JScrollPane(jt);
		sp.setBounds(ra.getPozitieX(25, width), ra.getPozitieY(10, height), ra.getLungimeComponenta(70, height),
				ra.getInaltimeComponenta(30, width));
		add(sp);

		JButton butonHome = new JButton();
		butonHome.setBounds(ra.getLungimeComponenta(93, width), 0, ra.getInaltimeComponenta(10),
				ra.getInaltimeComponenta(10));
		ActionListener actiuneButon = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				JPanelEchipeSubFormaTabelara.this.jpanelmeniu.jframeprincipal.jpanelhome.removeAll();
				JPanelHome j = new JPanelHome(JPanelEchipeSubFormaTabelara.this.jpanelmeniu.jframeprincipal);
				j.setBounds(0, 0, (int) ra.getLungimeComponenta(90), (int) ra.getHeight());
				JPanelEchipeSubFormaTabelara.this.jpanelmeniu.jframeprincipal.jpanelhome.add(j);
				JPanelEchipeSubFormaTabelara.this.jpanelmeniu.jframeprincipal.jpanelhome.repaint();
				JPanelEchipeSubFormaTabelara.this.jpanelmeniu.jframeprincipal.jpanelhome.revalidate();

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

		JButton butonAfisareEchipa = new JButton();
		butonAfisareEchipa.setBounds(ra.getPozitieX(45, width), ra.getPozitieY(80, height),
				ra.getInaltimeComponenta(10), ra.getInaltimeComponenta(10));
		ActionListener actiuneButonAfisareEchipa = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (JPanelEchipeSubFormaTabelara.this.jt.getSelectedRow() == -1) {
					JFrameAlertaEchipaNeselectata jfaen = new JFrameAlertaEchipaNeselectata();
					jfaen.setVisible(true);
				}
				try {
					// creez echipa si o trimit ca parametru clasei JPanelEchipaManager...
					int id = Integer.parseInt((String) JPanelEchipeSubFormaTabelara.this.jt
							.getValueAt(JPanelEchipeSubFormaTabelara.this.jt.getSelectedRow(), 0));
					String numeEchipa = (String) JPanelEchipeSubFormaTabelara.this.jt
							.getValueAt(JPanelEchipeSubFormaTabelara.this.jt.getSelectedRow(), 1);
					String departament = (String) JPanelEchipeSubFormaTabelara.this.jt
							.getValueAt(JPanelEchipeSubFormaTabelara.this.jt.getSelectedRow(), 2);
					Echipa echipa = new Echipa(id, numeEchipa, departament);

					// acum sterg ce am pe jpanelhome si pun panelul cu gestiunea echipei
					JPanelEchipaManager jpanelechipamanager = new JPanelEchipaManager(JPanelEchipeSubFormaTabelara.this,
							echipa);
					jpanelechipamanager.setBounds(0, 0, (int) ra.getLungimeComponenta(90), (int) ra.getHeight());
					JPanelEchipeSubFormaTabelara.this.jpanelmeniu.jframeprincipal.jpanelhome.removeAll();
					JPanelEchipeSubFormaTabelara.this.jpanelmeniu.jframeprincipal.jpanelhome.add(jpanelechipamanager);
					JPanelEchipeSubFormaTabelara.this.jpanelmeniu.jframeprincipal.jpanelhome.repaint();
					JPanelEchipeSubFormaTabelara.this.jpanelmeniu.jframeprincipal.jpanelhome.revalidate();
				} catch (ArrayIndexOutOfBoundsException easd) {

				}
			}
		};
		butonAfisareEchipa.addActionListener(actiuneButonAfisareEchipa);
		try {
			BufferedImage image = ImageIO.read(JPanelHome.class.getResource("/img/echipaTabel.png"));
			ResizeImagine.creezaImagineRedimensionataDeTipPNG(image, (int) ra.getInaltimeComponenta(10),
					(int) ra.getInaltimeComponenta(10), "echipaTabel2", "png");
		} catch (IOException ioe) {
			System.out.println(ioe.getMessage());
		}
		butonAfisareEchipa.setIcon(new ImageIcon(JPanelHome.class.getResource("/img/echipaTabel2.png")));
		butonAfisareEchipa.setOpaque(false);
		butonAfisareEchipa.setContentAreaFilled(false);
		butonAfisareEchipa.setBorderPainted(false);
		add(butonAfisareEchipa);

		JLabel jlt = new JLabel("Echipe sub forma tabelara");
		jlt.setBounds(ra.getPozitieX(35), ra.getPozitieY(2), ra.getLungimeComponenta(35, width),
				ra.getInaltimeComponenta(10, height));
		jlt.setFont(new Font("Tahoma", Font.PLAIN, 25));
		add(jlt);

		labelBackground = new JLabel();
		labelBackground.setBounds(0, 0, (int) width, (int) height);
		labelBackground.setIcon(new ImageIcon(JPanelHome.class.getResource("/img/homeBack2.jpg")));
		add(labelBackground);
	}
}
