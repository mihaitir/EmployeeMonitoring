package com.jpanel;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import com.jframe.JFramePrincipal;
import com.tools.ResizeImagine;
import com.tools.RezolutieAplicatie;
import java.awt.Font;

public class JPanelHome extends JPanel {

	private static final long serialVersionUID = 1L;
	public JFramePrincipal jframeprincipal; // componenta parinte
	public double width;
	public double height;
	public DefaultPieDataset defaultPieDatasetPentruDepartament;
	public PieDataset pieDatasetPentruDepartament;
	public JFreeChart chartDepartamente;
	public ChartPanel chartPanelPentruDepartament;
	public DefaultPieDataset defaultPieDatasetPentruAngajati;
	public PieDataset pieDatasetPentruAngajati;
	public JFreeChart chartAngajati;
	public ChartPanel chartPanelPentruAngajati;
	public JLabel labelBackground;

	public JPanelHome(JFramePrincipal jframeprincipal) {
		this.jframeprincipal = jframeprincipal;
		setLayout(null);
		RezolutieAplicatie ra = new RezolutieAplicatie();
		width = ra.getLungimeComponenta(90); // jpanel are lungime 75% din componenta parinte
		height = ra.getHeight();
		setBounds(ra.getPozitieX(10), 0, (int) ra.getLungimeComponenta(90), (int) ra.getHeight());
		JLabel labelWelcome = new JLabel("Welcome");
		labelWelcome.setFont(new Font("Serif", Font.BOLD, 40));
		labelWelcome.setBounds(ra.getPozitieX(38), ra.getPozitieY(1), ra.getLungimeComponenta(15),
				ra.getInaltimeComponenta(10));
		add(labelWelcome);

		Map<String, Integer> map = new HashMap<String, Integer>();
		int numarAngajatiPreluati = 0;
		int numarAngajatiDisponibili = 0;
		// acum sa preluam echipele din baza de date
		try {
			String url = "jdbc:mysql://localhost:3306/sys?&serverTimezone=UTC";
			String user = "root";
			String password = "password";

			Connection conn = DriverManager.getConnection(url, user, password);
			Statement st = conn.createStatement();

			ResultSet rs = st.executeQuery("select departament from echipe");

			while (rs.next()) {
				String departament = rs.getString("departament");
				if (!map.containsKey(departament)) {
					map.put(departament, 1);
				} else {
					int aparitii = map.get(departament);
					map.put(departament, aparitii + 1);
				}
			}
			
			rs = st.executeQuery("select disponibil from angajati");
			
			while (rs.next()) {
				int stare = Integer.parseInt(rs.getString("disponibil"));
				if (stare == 0)  numarAngajatiPreluati++; else numarAngajatiDisponibili++;
			}
			
		} catch (SQLException sqle) {
			System.out.println(sqle.getMessage());
		}
		// avem nevoie de un default dataset in care sa punem valorile
		this.defaultPieDatasetPentruDepartament = new DefaultPieDataset();
		for (Map.Entry<String, Integer> entry : map.entrySet()) {

			defaultPieDatasetPentruDepartament.setValue(entry.getKey(), entry.getValue());

		}
		this.pieDatasetPentruDepartament = defaultPieDatasetPentruDepartament;
		this.chartDepartamente = ChartFactory.createPieChart3D("Statistica Departamente", pieDatasetPentruDepartament, true, true, false);
		this.chartPanelPentruDepartament = new ChartPanel(chartDepartamente);
		chartPanelPentruDepartament.setBounds(ra.getPozitieX(10, width), ra.getPozitieY(30, height), ra.getLungimeComponenta(30, width),
				ra.getInaltimeComponenta(30, height));

		add(chartPanelPentruDepartament);
		
		
		this.defaultPieDatasetPentruAngajati = new DefaultPieDataset();
		defaultPieDatasetPentruAngajati.setValue("Angajati Preluati", numarAngajatiPreluati);
		defaultPieDatasetPentruAngajati.setValue("Angajati Disponibili", numarAngajatiDisponibili);
		this.pieDatasetPentruAngajati = defaultPieDatasetPentruAngajati;
		this.chartAngajati = ChartFactory.createPieChart3D("Statistica Angajati", pieDatasetPentruAngajati, true, true, false);
		this.chartPanelPentruAngajati = new ChartPanel(chartAngajati);
		chartPanelPentruAngajati.setBounds(ra.getPozitieX(55, width), ra.getPozitieY(30, height), ra.getLungimeComponenta(30, width),
				ra.getInaltimeComponenta(30, height));
		add(chartPanelPentruAngajati);
		// la sfarsit pun jlabelul peste toate componentele ca si background

		this.labelBackground = new JLabel();
		labelBackground.setBounds(0, 0, (int) width, (int) height);
		try {
			BufferedImage image = ImageIO.read(JPanelHome.class.getResource("/img/homeBack.jpg"));
			ResizeImagine.creezaImagineRedimensionataDeTipJPG(image, (int) width, (int) height, "homeBack2", "jpg");
		} catch (IOException ioe) {
			System.out.println(ioe.getMessage());
		}
		labelBackground.setIcon(new ImageIcon(JPanelHome.class.getResource("/img/homeBack2.jpg")));
		add(labelBackground);
	}	
}
