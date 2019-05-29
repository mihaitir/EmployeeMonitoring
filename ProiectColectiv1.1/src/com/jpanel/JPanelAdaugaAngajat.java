package com.jpanel;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import com.jframe.JFrameMesajInserareInsucces;
import com.jframe.JFrameMesajInserareSucces;
import com.tools.ResizeImagine;
import com.tools.RezolutieAplicatie;

public class JPanelAdaugaAngajat extends JPanel {

	private static final long serialVersionUID = 1L;
	public double width;
	public double height;
	private JTextField idTextField;
	private JTextField numeTextField;
	public JPanelMeniu jpanelmeniu;
	//public ArrayList<Echipa> listaEchipe = new ArrayList<Echipa>();
	public boolean inserareCuSucces;

	public JPanelAdaugaAngajat(JPanelMeniu jpanelmeniu) {
		this.jpanelmeniu = jpanelmeniu;
		RezolutieAplicatie ra = new RezolutieAplicatie();
		width = ra.getLungimeComponenta(90); // jpanel are lungime 75% din componenta parinte
		height = ra.getHeight();
		setLayout(null);
		
		JButton butonAdauga = new JButton();
		butonAdauga.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					String url = "jdbc:mysql://localhost:3306/sys?&serverTimezone=UTC";
					String user = "root";
					String password = "password";
					JPanelAdaugaAngajat.this.inserareCuSucces = true; //initial presupunem ca inserarea se realizeaza cu succes
					Connection conn = DriverManager.getConnection(url, user, password);

					String insertSQL = "INSERT INTO angajati(idAngajat, nume, numeEchipa, disponibil, timpInceput, timpSfarsit) values (?, ?, ?, ?, ?, ?)";
					PreparedStatement preparedStatement = conn.prepareStatement(insertSQL);
					
					preparedStatement.setInt(1, Integer.parseInt(idTextField.getText()));
					preparedStatement.setString(2, numeTextField.getText());
					preparedStatement.setString(3, null);
					preparedStatement.setBoolean(4, true);
					preparedStatement.setTimestamp(5, null);
					preparedStatement.setTimestamp(6, null);
					preparedStatement.executeUpdate();
				
//				
				}
				catch (SQLIntegrityConstraintViolationException e) {
			        //exceptie ce intervine la o cheie duplicata, adica atunci cand un angajat este deja introdus in sistem, duca cheia sa publica
					System.out.println(e.getMessage());
					JPanelAdaugaAngajat.this.inserareCuSucces = false;
					JFrameMesajInserareInsucces jfmii = new JFrameMesajInserareInsucces();
					jfmii.setVisible(true);
			    }
				catch (SQLException sqle) {
					System.out.println(sqle.getMessage());
					JPanelAdaugaAngajat.this.inserareCuSucces = false;
					JFrameMesajInserareInsucces jfmii = new JFrameMesajInserareInsucces();
					jfmii.setVisible(true);
				}
			if (JPanelAdaugaAngajat.this.inserareCuSucces)
			{
				JFrameMesajInserareSucces jfmis = new JFrameMesajInserareSucces();
				jfmis.setVisible(true);
	
			}
			}
			
		});
		
		butonAdauga.setOpaque(false);
		butonAdauga.setContentAreaFilled(false);
		butonAdauga.setBorderPainted(false);
		try {
			BufferedImage image = ImageIO.read(JPanelHome.class.getResource("/img/addPerson.png"));
			// sa fie imaginea patrata astfel luam 10% din inaltime

			ResizeImagine.creezaImagineRedimensionataDeTipPNG(image, (int) ra.getInaltimeComponenta(10),
					(int) ra.getInaltimeComponenta(10), "addPerson2", "png");
		} catch (IOException ioe) {
			System.out.println(ioe.getMessage());
		}
		butonAdauga.setIcon(new ImageIcon(JPanelHome.class.getResource("/img/addPerson2.png")));
		butonAdauga.setBounds(ra.getPozitieX(42, width), ra.getPozitieY(60, height), ra.getInaltimeComponenta(10),
				ra.getInaltimeComponenta(10));
		this.add(butonAdauga);
		
		idTextField = new JTextField();
		idTextField.setBounds(ra.getPozitieX(35,width), ra.getPozitieY(30, height), ra.getInaltimeComponenta(30), 
				ra.getLungimeComponenta(2));
		this.add(idTextField);
		
		JLabel jLabelID = new JLabel("ID Angajat");
		jLabelID.setFont(new Font("Tahoma", Font.PLAIN, 25));
//		jLabelID.setFont(new Font(20));
		jLabelID.setBounds(ra.getPozitieX(40,width), ra.getPozitieY(20, height), ra.getInaltimeComponenta(30), 
				ra.getLungimeComponenta(2));
		this.add(jLabelID);
		
		numeTextField = new JTextField();
		numeTextField.setBounds(ra.getPozitieX(35,width), ra.getPozitieY(49, height), ra.getInaltimeComponenta(30), 
				ra.getLungimeComponenta(2));
		this.add(numeTextField);
		
		JLabel jLabelNume = new JLabel("Nume Angajat");
		jLabelNume.setFont(new Font("Tahoma", Font.PLAIN, 25));
		jLabelNume.setBounds(ra.getPozitieX(40,width), ra.getPozitieY(39, height), ra.getInaltimeComponenta(30), 
				ra.getLungimeComponenta(2));
		this.add(jLabelNume);
		
		JLabel jlt = new JLabel("Formular introducere angajat nou in sistem");
		jlt.setBounds(ra.getPozitieX(27), ra.getPozitieY(2), ra.getLungimeComponenta(35, width), ra.getInaltimeComponenta(10, height));
		jlt.setFont(new Font("Tahoma", Font.PLAIN, 25));
		add(jlt);
		
		JButton butonHome = new JButton();
		butonHome.setBounds(ra.getLungimeComponenta(93, width), 0, ra.getInaltimeComponenta(10),
				ra.getInaltimeComponenta(10));
		ActionListener actiuneButon = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				JPanelAdaugaAngajat.this.jpanelmeniu.jframeprincipal.jpanelhome.removeAll();
				JPanelHome j = new JPanelHome(JPanelAdaugaAngajat.this.jpanelmeniu.jframeprincipal);
				j.setBounds(0, 0, (int) ra.getLungimeComponenta(90), (int) ra.getHeight());
				JPanelAdaugaAngajat.this.jpanelmeniu.jframeprincipal.jpanelhome.add(j);
				JPanelAdaugaAngajat.this.jpanelmeniu.jframeprincipal.jpanelhome.repaint();
				JPanelAdaugaAngajat.this.jpanelmeniu.jframeprincipal.jpanelhome.revalidate();

			}
		};
		butonHome.addActionListener(actiuneButon);
		butonHome.setIcon(new ImageIcon(JPanelHome.class.getResource("/img/home2.png")));
		butonHome.setOpaque(false);
		butonHome.setContentAreaFilled(false);
		butonHome.setBorderPainted(false);
		add(butonHome);
		
		
		JLabel lblNewLabel = new JLabel();
		lblNewLabel.setBounds(0, 0, (int)width,(int) height);
		lblNewLabel.setIcon(new ImageIcon(JPanelAdaugaAngajat.class.getResource("/img/homeBack2.jpg")));
		this.add(lblNewLabel);
	}

}
