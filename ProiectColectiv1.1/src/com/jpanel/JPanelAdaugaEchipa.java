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

public class JPanelAdaugaEchipa extends JPanel {

	private static final long serialVersionUID = 1L;
	public double width;
	public double height;
	JTextField idTextFieldEchipa;
	JTextField numeTextFieldEchipa;
	JTextField departament;
	public JPanelMeniu jpanelmeniu;
	public boolean inserareCuSucces;


	
	public JPanelAdaugaEchipa(JPanelMeniu jpanelmeniu) {
		
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
					 JPanelAdaugaEchipa.this.inserareCuSucces = true; //initial presupunem ca inserarea se realizeaza cu succes
					Connection conn = DriverManager.getConnection(url, user, password);

					String insertSQL = "INSERT INTO echipe(id,numeEchipa,departament) values (?, ?, ?)";
					PreparedStatement preparedStatement = conn.prepareStatement(insertSQL);

					preparedStatement.setInt(1, Integer.parseInt(idTextFieldEchipa.getText()));
					preparedStatement.setString(2, numeTextFieldEchipa.getText());
					preparedStatement.setString(3, departament.getText());
					preparedStatement.executeUpdate();
				}
				catch (SQLIntegrityConstraintViolationException e) {
			        //exceptie ce intervine la o cheie duplicata, adica atunci cand un angajat este deja introdus in sistem, duca cheia sa public
					System.out.println(e.getMessage());
					 JPanelAdaugaEchipa.this.inserareCuSucces = false;
					JFrameMesajInserareInsucces jfmii = new JFrameMesajInserareInsucces();
					jfmii.setVisible(true);
			    }
				catch (SQLException sqle) {
					System.out.println(sqle.getMessage());
					 JPanelAdaugaEchipa.this.inserareCuSucces = false;
					JFrameMesajInserareInsucces jfmii = new JFrameMesajInserareInsucces();
					jfmii.setVisible(true);
				}
				
				if ( JPanelAdaugaEchipa.this.inserareCuSucces)
				{
					JFrameMesajInserareSucces jfmis = new JFrameMesajInserareSucces();
					jfmis.setVisible(true);
					//daca s-a inserat cu succes atunci trebuie sa redesenez si diagrama cu statistica departamentelor
					//o mica observatie momentan redesenez tot jpanelul, dar pe viitor cand voi avea timp void redesena doar bucatia cu panelul pentru
	
					
					
				}
			}
		});
		butonAdauga.setOpaque(false);
		butonAdauga.setContentAreaFilled(false);
		butonAdauga.setBorderPainted(false);
		try {
			BufferedImage image = ImageIO.read(JPanelHome.class.getResource("/img/addTeam.png"));
			// sa fie imaginea patrata astfel luam 10% din inaltime

			ResizeImagine.creezaImagineRedimensionataDeTipPNG(image, (int) ra.getInaltimeComponenta(10),
					(int) ra.getInaltimeComponenta(10), "addTeam2", "png");
		} catch (IOException ioe) {
			System.out.println(ioe.getMessage());
		}
		butonAdauga.setIcon(new ImageIcon(JPanelHome.class.getResource("/img/addTeam2.png")));
		butonAdauga.setBounds(ra.getPozitieX(42, width), ra.getPozitieY(75, height), ra.getInaltimeComponenta(12),
				ra.getInaltimeComponenta(12));
		this.add(butonAdauga);

		JLabel jLabelID = new JLabel("ID Echipa");
		jLabelID.setFont(new Font("Tahoma", Font.PLAIN, 25));
		jLabelID.setBounds(ra.getPozitieX(40, width), ra.getPozitieY(15, height), ra.getInaltimeComponenta(30),
				ra.getLungimeComponenta(2));
		this.add(jLabelID);

		idTextFieldEchipa = new JTextField();
		idTextFieldEchipa.setBounds(ra.getPozitieX(35, width), ra.getPozitieY(22, height), ra.getInaltimeComponenta(30),
				ra.getLungimeComponenta(2));
		this.add(idTextFieldEchipa);

		JLabel jLabelDepartament = new JLabel("Departament:");
		jLabelDepartament.setFont(new Font("Tahoma", Font.PLAIN, 25));
		jLabelDepartament.setBounds(ra.getPozitieX(40, width), ra.getPozitieY(35, height), ra.getInaltimeComponenta(30),
				ra.getLungimeComponenta(2));
		this.add(jLabelDepartament);

		departament = new JTextField();
		departament.setBounds(ra.getPozitieX(35, width), ra.getPozitieY(42, height), ra.getInaltimeComponenta(30),
				ra.getLungimeComponenta(2));
		this.add(departament);

		JLabel jLabelNume = new JLabel("Nume Echipa");
		jLabelNume.setFont(new Font("Tahoma", Font.PLAIN, 25));
		jLabelNume.setBounds(ra.getPozitieX(40, width), ra.getPozitieY(55, height), ra.getInaltimeComponenta(30),
				ra.getLungimeComponenta(2));
		this.add(jLabelNume);

		numeTextFieldEchipa = new JTextField();
		numeTextFieldEchipa.setBounds(ra.getPozitieX(35, width), ra.getPozitieY(62, height),
				ra.getInaltimeComponenta(30), ra.getLungimeComponenta(2));
		this.add(numeTextFieldEchipa);

		JButton butonHome = new JButton();
		butonHome.setBounds(ra.getLungimeComponenta(93, width), 0, ra.getInaltimeComponenta(10),
				ra.getInaltimeComponenta(10));
		ActionListener actiuneButon = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				JPanelAdaugaEchipa.this.jpanelmeniu.jframeprincipal.jpanelhome.removeAll();
				JPanelHome j = new JPanelHome(JPanelAdaugaEchipa.this.jpanelmeniu.jframeprincipal);
				j.setBounds(0, 0, (int) ra.getLungimeComponenta(90), (int) ra.getHeight());
				JPanelAdaugaEchipa.this.jpanelmeniu.jframeprincipal.jpanelhome.add(j);
				JPanelAdaugaEchipa.this.jpanelmeniu.jframeprincipal.jpanelhome.repaint();
				JPanelAdaugaEchipa.this.jpanelmeniu.jframeprincipal.jpanelhome.revalidate();

			}
		};
		butonHome.addActionListener(actiuneButon);
		butonHome.setIcon(new ImageIcon(JPanelHome.class.getResource("/img/home2.png")));
		butonHome.setOpaque(false);
		butonHome.setContentAreaFilled(false);
		butonHome.setBorderPainted(false);
		add(butonHome);
		
		
		JLabel jlt = new JLabel("Formular pentru introducerea unei noi echipe in sistem");
		jlt.setBounds(ra.getPozitieX(24), ra.getPozitieY(2), ra.getLungimeComponenta(40, width), ra.getInaltimeComponenta(10, height));
		jlt.setFont(new Font("Tahoma", Font.PLAIN, 25));
		add(jlt);


		JLabel lblNewLabel = new JLabel();
		lblNewLabel.setBounds(0, 0, (int) width, (int) height);
		lblNewLabel.setIcon(new ImageIcon(JPanelAdaugaEchipa.class.getResource("/img/homeBack2.jpg")));
		this.add(lblNewLabel);	
	}

}
