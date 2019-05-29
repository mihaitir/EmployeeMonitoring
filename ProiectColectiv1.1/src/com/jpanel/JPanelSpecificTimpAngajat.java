package com.jpanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import com.JButton.ButonAngajatListaDisponibil;
import com.backend.Angajat;
import com.backend.ManipulareAngajatiIndisponibili;
import com.tools.ResizeImagine;
import com.tools.RezolutieAplicatie;

public class JPanelSpecificTimpAngajat extends JPanel {

	private static final long serialVersionUID = 1L;
	public double width;
	public double height;
	public ButonAngajatListaDisponibil butonAngajatListaDisponibil;
	public Angajat angajat;
	@SuppressWarnings("rawtypes")
	public JComboBox AnComboBox;
	@SuppressWarnings("rawtypes")
	public JComboBox LunaComboBox;
	@SuppressWarnings("rawtypes")
	public JComboBox ZiComboBox;
	@SuppressWarnings("rawtypes")
	public JComboBox OraComboBox;
	@SuppressWarnings("rawtypes")
	public JComboBox MinutComboBox;
	@SuppressWarnings("rawtypes")
	public JComboBox SecundaComboBox;
	public JButton butonPreia;
	public JLabel labelPersoanaSelectata;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public JPanelSpecificTimpAngajat(ButonAngajatListaDisponibil butonAngajatListaDisponibil, Angajat angajat) {
		this.angajat = angajat;
		this.butonAngajatListaDisponibil = butonAngajatListaDisponibil;
		RezolutieAplicatie ra = new RezolutieAplicatie();
		this.width = ra.getLungimeComponenta(64,
				butonAngajatListaDisponibil.jpanelListaAngajatiDisponibiliPeIntregSistemul.jpanelAdaugaAngajatInEchipa.width);
		this.height = ra.getInaltimeComponenta(50,
				butonAngajatListaDisponibil.jpanelListaAngajatiDisponibiliPeIntregSistemul.jpanelAdaugaAngajatInEchipa.height);
		this.setLayout(null);

		labelPersoanaSelectata = new JLabel("Ati selectat pe " + angajat.getNume());
		labelPersoanaSelectata.setBounds(ra.getPozitieX(20, width), ra.getPozitieY(5, height),
				ra.getLungimeComponenta(10), ra.getInaltimeComponenta(5));
		add(labelPersoanaSelectata);
		JLabel lblAn = new JLabel("an");
		lblAn.setBounds(ra.getPozitieX(20, width), ra.getPozitieY(20, height), ra.getLungimeComponenta(5),
				ra.getInaltimeComponenta(5));
		add(lblAn);

		
		AnComboBox = new JComboBox<String>();
		AnComboBox.setBounds(ra.getPozitieX(20, width), ra.getPozitieY(30, height), ra.getLungimeComponenta(3),
				ra.getInaltimeComponenta(2));
		for (int i = 0; i < 20; i++) {
			AnComboBox.addItem(String.valueOf(2019 + i));
		}

		add(AnComboBox);

		JLabel lbluna = new JLabel("luna");
		lbluna.setBounds(ra.getPozitieX(40, width), ra.getPozitieY(20, height), ra.getLungimeComponenta(5),
				ra.getInaltimeComponenta(5));
		add(lbluna);

		LunaComboBox = new JComboBox();
		LunaComboBox.setBounds(ra.getPozitieX(40, width), ra.getPozitieY(30, height), ra.getLungimeComponenta(3),
				ra.getInaltimeComponenta(2));
		for (int i = 1; i < 10; i++) {
			LunaComboBox.addItem("0" + String.valueOf(i));
		}
		for (int i = 10; i <= 12; i++) {
			LunaComboBox.addItem(String.valueOf(i));
		}
		add(LunaComboBox);

		JLabel zi = new JLabel("zi");
		zi.setBounds(ra.getPozitieX(60, width), ra.getPozitieY(20, height), ra.getLungimeComponenta(5),
				ra.getInaltimeComponenta(5));
		add(zi);

		ZiComboBox = new JComboBox();
		for (int i = 1; i <= 9; i++) {
			ZiComboBox.addItem("0" + String.valueOf(i));
		}
		for (int i = 10; i <= 31; i++) {
			ZiComboBox.addItem(String.valueOf(i));
		}

		ZiComboBox.setBounds(ra.getPozitieX(60, width), ra.getPozitieY(30, height), ra.getLungimeComponenta(3),
				ra.getInaltimeComponenta(2));
		add(ZiComboBox);

		JLabel lblOra = new JLabel("ora");
		lblOra.setBounds(ra.getPozitieX(20, width), ra.getPozitieY(40, height), ra.getLungimeComponenta(5),
				ra.getInaltimeComponenta(5));
		add(lblOra);
		OraComboBox = new JComboBox();
		for (int i = 0; i <= 9; i++) {
			OraComboBox.addItem("0" + String.valueOf(i));
		}
		for (int i = 10; i <= 23; i++) {
			OraComboBox.addItem(String.valueOf(i));
		}
		OraComboBox.setBounds(ra.getPozitieX(20, width), ra.getPozitieY(50, height), ra.getLungimeComponenta(3),
				ra.getInaltimeComponenta(2));
		add(OraComboBox);

		JLabel lblMinut = new JLabel("minut");
		lblMinut.setBounds(ra.getPozitieX(40, width), ra.getPozitieY(40, height), ra.getLungimeComponenta(5),
				ra.getInaltimeComponenta(5));
		add(lblMinut);
		MinutComboBox = new JComboBox();
		for (int i = 0; i <= 9; i++) {
			MinutComboBox.addItem("0" + String.valueOf(i));
		}
		for (int i = 10; i <= 59; i++) {
			MinutComboBox.addItem(String.valueOf(i));
		}
		MinutComboBox.setBounds(ra.getPozitieX(40, width), ra.getPozitieY(50, height), ra.getLungimeComponenta(3),
				ra.getInaltimeComponenta(2));
		add(MinutComboBox);

		JLabel lblSec = new JLabel("sec");
		lblSec.setBounds(ra.getPozitieX(60, width), ra.getPozitieY(40, height), ra.getLungimeComponenta(5),
				ra.getInaltimeComponenta(5));
		add(lblSec);

		SecundaComboBox = new JComboBox();
		for (int i = 0; i <= 9; i++) {
			SecundaComboBox.addItem("0" + String.valueOf(i));
		}
		for (int i = 10; i <= 59; i++) {
			SecundaComboBox.addItem(String.valueOf(i));
		}
		SecundaComboBox.setBounds(ra.getPozitieX(60, width), ra.getPozitieY(50, height), ra.getLungimeComponenta(3),
				ra.getInaltimeComponenta(2));
		add(SecundaComboBox);

		butonPreia = new JButton();
		butonPreia.setBounds(ra.getPozitieX(30, width), ra.getPozitieY(70, height), ra.getInaltimeComponenta(10),
				ra.getInaltimeComponenta(10));
		butonPreia.setOpaque(false);
		butonPreia.setContentAreaFilled(false);
		butonPreia.setBorderPainted(false);
		try {
			BufferedImage image = ImageIO.read(JPanelHome.class.getResource("/img/preiaAngajat.png"));
			// sa fie imaginea patrata astfel luam 10% din inaltime

			ResizeImagine.creezaImagineRedimensionataDeTipPNG(image, (int) ra.getInaltimeComponenta(8),
					(int) ra.getInaltimeComponenta(8), "preiaAngajat2", "png");
		} catch (IOException ioe) {
			System.out.println(ioe.getMessage());
		}
		butonPreia.setIcon(new ImageIcon(JPanelHome.class.getResource("/img/preiaAngajat2.png")));
		butonPreia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				// cand dam click pe preia modificam
//				1:in baza de date initial
//				2:reinitializam componeneta JPanelEchipaManager ca sa para ca aplciatia e dinamica
//				3:reinititry {

				try {
					String url = "jdbc:mysql://localhost:3306/sys?&serverTimezone=UTC";
					String user = "root";
					String password = "password";

					Connection conn = DriverManager.getConnection(url, user, password);
					String updateSQL = "UPDATE angajati set timpInceput=?, timpSfarsit=?, numeEchipa=?, disponibil = 0 where idAngajat=?";
					PreparedStatement preparedStatement = conn.prepareStatement(updateSQL);

					// obtin data curenta
					java.util.Date utilDate = new java.util.Date();
//					    java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
					java.sql.Timestamp timpCurent = new java.sql.Timestamp(utilDate.getTime());

					// obtin data din formular
					String data = AnComboBox.getSelectedItem() + "-" + LunaComboBox.getSelectedItem() + "-"
							+ ZiComboBox.getSelectedItem() + " " + OraComboBox.getSelectedItem() + ":"
							+ MinutComboBox.getSelectedItem() + ":" + SecundaComboBox.getSelectedItem();
					java.sql.Timestamp time = java.sql.Timestamp.valueOf(data);

					preparedStatement.setTimestamp(1, timpCurent);
					preparedStatement.setTimestamp(2, time);
					preparedStatement.setString(3,
							JPanelSpecificTimpAngajat.this.butonAngajatListaDisponibil.jpanelListaAngajatiDisponibiliPeIntregSistemul.jpanelAdaugaAngajatInEchipa.jpanelechipamanager.echipa
									.getNume());
					preparedStatement.setInt(4, angajat.getId());
					preparedStatement.executeUpdate();

					// si acum trebuie sa consemnam faptul si in tabela IstoricMunca

					updateSQL = "INSERT into istoricmunca (idAngajat, idEchipa, timpInceput, timpSfarsit, terminat) values (?,?,?,?,0)";
					preparedStatement = conn.prepareStatement(updateSQL);
					preparedStatement.setInt(1, angajat.getId());
					preparedStatement.setInt(2,
							JPanelSpecificTimpAngajat.this.butonAngajatListaDisponibil.jpanelListaAngajatiDisponibiliPeIntregSistemul.jpanelAdaugaAngajatInEchipa.jpanelechipamanager.echipa
									.getId());
					preparedStatement.setTimestamp(3, timpCurent);
					preparedStatement.setTimestamp(4, time);
					preparedStatement.executeUpdate();

				}

				catch (SQLException sqle) {
					System.out.println(sqle.getMessage());
				}
				JPanelEchipaManager jpem = JPanelSpecificTimpAngajat.this.butonAngajatListaDisponibil.jpanelListaAngajatiDisponibiliPeIntregSistemul.jpanelAdaugaAngajatInEchipa.jpanelechipamanager;
				ManipulareAngajatiIndisponibili mai = new ManipulareAngajatiIndisponibili(jpem.echipa.getNume());
				jpem.echipa.setListaAngajati(mai.actualizareListaAngajatiIndisponibili(jpem.echipa.getNume()));
				jpem.jpanellistaangajatiperechipa = new JPanelListaAngajatiPerEchipa(jpem);
				jpem.remove(jpem.jscrollpane);
				jpem.remove(jpem.labelBackground);
				jpem.repaint();
				jpem.revalidate();
				jpem.jscrollpane = new JScrollPane(jpem.jpanellistaangajatiperechipa);
				int width2 = jpem.getWidth();
				int height2 = jpem.getHeight();
				jpem.jscrollpane.setBounds(ra.getPozitieX(10, width2), ra.getPozitieY(10, height2),
						ra.getLungimeComponenta(25), ra.getInaltimeComponenta(80, height2));
				jpem.add(jpem.jscrollpane);
				jpem.add(jpem.labelBackground);
				jpem.jscrollpane.revalidate();
				jpem.jscrollpane.repaint();

				jpem.panelDreapta.removeAll();
				JPanelAdaugaAngajatInEchipa jaaie = new JPanelAdaugaAngajatInEchipa(jpem);
				jaaie.setBounds(0, 0, ra.getLungimeComponenta(25), ra.getInaltimeComponenta(80, ra.getHeight()));
				jpem.panelDreapta.add(jaaie);
				jpem.panelDreapta.repaint();
				jpem.panelDreapta.revalidate();
			}
		});
		add(butonPreia);

	}

}
