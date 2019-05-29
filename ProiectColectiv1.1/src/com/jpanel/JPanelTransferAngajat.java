package com.jpanel;

import java.awt.Dimension;
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
import java.util.HashMap;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import com.backend.Angajat;
import com.backend.ManipulareAngajatiIndisponibili;
import com.jframe.JFrameMesajTransferCuSucces;
import com.jframe.JFrameMesajTransferInsucces;
import com.tools.ResizeImagine;
import com.tools.RezolutieAplicatie;

public class JPanelTransferAngajat extends JPanel {

	private static final long serialVersionUID = 1L;
	public double width;
	public double height;
	public JPanelEchipaManager jpanelechipamanager;
	Angajat angajat;
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
	JLabel labelPersoanaSelectata;
	@SuppressWarnings("rawtypes")
	public JComboBox echipaBox;
	HashMap<String,Integer> hm = new HashMap<String,Integer>();

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public JPanelTransferAngajat(JPanelEchipaManager jpanelechipamanager, Angajat angajat) {
		this.jpanelechipamanager = jpanelechipamanager;
		this.angajat = angajat;
		this.jpanelechipamanager = jpanelechipamanager;
		this.setLayout(null);
		RezolutieAplicatie ra = new RezolutieAplicatie();
		this.setPreferredSize(new Dimension(ra.getLungimeComponenta(24), ra.getInaltimeComponenta(79)));
		this.width = ra.getLungimeComponenta(24);
		this.height = ra.getInaltimeComponenta(79);

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
		
		
		echipaBox = new JComboBox();
		try {
			String url = "jdbc:mysql://localhost:3306/sys?&serverTimezone=UTC";
			String user = "root";
			String password = "password";

			Connection conn = DriverManager.getConnection(url, user, password);
			
			Statement selectStatement = conn.createStatement();
			selectStatement.execute("SELECT numeEchipa, id from echipe");
			ResultSet rs = selectStatement.getResultSet();
			while(rs.next())
			{
				String numeE = rs.getString("numeEchipa");
				int ide = rs.getInt("id");
				hm.put(numeE, ide);
				echipaBox.addItem(numeE);
			}
		echipaBox.setBounds(ra.getPozitieX(40,width), ra.getPozitieY(60, height), ra.getLungimeComponenta(8), ra.getInaltimeComponenta(2));	
		add(echipaBox);	

			}
		catch(SQLException sqsle)
		{
			
		}
		JButton b = new JButton();
		b.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try {
					String url = "jdbc:mysql://localhost:3306/sys?&serverTimezone=UTC";
					String user = "root";
					String password = "password";

					Connection conn = DriverManager.getConnection(url, user, password);
					
					//iainte sa inseram preluam data din formula si data curenta
					
					String data = AnComboBox.getSelectedItem() + "-" + LunaComboBox.getSelectedItem() + "-"
							+ ZiComboBox.getSelectedItem() + " " + OraComboBox.getSelectedItem() + ":"
							+ MinutComboBox.getSelectedItem() + ":" + SecundaComboBox.getSelectedItem();
					java.sql.Timestamp timpFormular = java.sql.Timestamp.valueOf(data);
					java.util.Date utilDate = new java.util.Date();
				    java.sql.Timestamp timpCurent = new java.sql.Timestamp(utilDate.getTime());
				    
					Statement selectStatement = conn.createStatement();
					selectStatement.execute("SELECT * FROM angajati where idAngajat="+ angajat.getId());
					ResultSet rs = selectStatement.getResultSet();
					rs.next();
					java.sql.Timestamp timpSfarsitAngajat = rs.getTimestamp("timpSfarsit");
					if (timpFormular.compareTo(timpSfarsitAngajat)>0)
					{
						System.out.println("Nu poti pune o data mai mare decat contractul angajatului");
						JFrameMesajTransferInsucces jfmti = new JFrameMesajTransferInsucces();
						jfmti.setVisible(true);
						
					}
					else
					{
						//prima data consemnam transferul in tabelul istoricangajat
						String updateSQL = "INSERT INTO istoricmunca (idAngajat, idEchipa, timpInceput, timpSfarsit, terminat) values (?, ?, ?, ?,0)";
						PreparedStatement preparedStatement = conn.prepareStatement(updateSQL);
						preparedStatement.setInt(1, angajat.getId());
						preparedStatement.setInt(2, hm.get(echipaBox.getSelectedItem()));
						
						preparedStatement.setTimestamp(3, timpCurent);
						preparedStatement.setTimestamp(4, timpFormular);
						preparedStatement.executeUpdate();
						
						//apoi in tabela angajati
						
						updateSQL = "UPDATE angajati set timpInceput=?, timpSfarsit=?, numeEchipa=?  where idAngajat=?";
						preparedStatement = conn.prepareStatement(updateSQL);
						preparedStatement.setTimestamp(1, timpCurent);
						preparedStatement.setTimestamp(2, timpFormular);
						preparedStatement.setString(3,String.valueOf(echipaBox.getSelectedItem()));
						preparedStatement.setInt(4, angajat.getId());
						preparedStatement.executeUpdate();
						
						//dam refresh la paneul echipa manager si punem un frame care spune ca s-a realizat cu succes transferul
						
						JFrameMesajTransferCuSucces jfmtcs = new JFrameMesajTransferCuSucces();
						jfmtcs.setVisible(true);
						
						JPanelEchipaManager jpem = JPanelTransferAngajat.this.jpanelechipamanager;
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
						jpem.jscrollpane.setBounds(ra.getPozitieX(10, width2), ra.getPozitieY(10, height2), ra.getLungimeComponenta(25),
								ra.getInaltimeComponenta(80, height2));
						jpem.add(jpem.jscrollpane);
						jpem.add(jpem.labelBackground);
						jpem.jscrollpane.revalidate();
						jpem.jscrollpane.repaint();
						
						
						
					}		
			}
				catch(SQLException sqsle)
				{
					
				}
			}
		});
		b.setOpaque(false);
		b.setContentAreaFilled(false);
		b.setBorderPainted(false);
		try {
			BufferedImage image = ImageIO.read(JPanelHome.class.getResource("/img/schimba.png"));
			// sa fie imaginea patrata astfel luam 10% din inaltime

			ResizeImagine.creezaImagineRedimensionataDeTipPNG(image, (int) ra.getInaltimeComponenta(10),
					(int) ra.getInaltimeComponenta(10), "schimba2", "png");
		} catch (IOException ioe) {
			System.out.println(ioe.getMessage());
		}
		b.setIcon(new ImageIcon(JPanelHome.class.getResource("/img/schimba2.png")));
		b.setBounds(ra.getLungimeComponenta(45, width), ra.getPozitieY(70, height), (int) ra.getInaltimeComponenta(10),
				(int) ra.getInaltimeComponenta(10));
		add(b);

	}
}
