package com.backend;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import com.jpanel.JPanelEchipaManager;

public class ManipulareAngajati {
	public ManipulareAngajatiDisponibili manipulareAngajatiDisponibili;
	public ManipulareAngajatiIndisponibili manipulareAngajatiIndisponibili;
	public JPanelEchipaManager jpanelechipamanager;
	public ArrayList<Angajat> listaAngajatiIndisponibili;
	public ArrayList<Angajat> listaAngajatiDisponibili;

	public ManipulareAngajati(JPanelEchipaManager jpanelechipamanager) {
		this.jpanelechipamanager = jpanelechipamanager;
		this.manipulareAngajatiDisponibili = new ManipulareAngajatiDisponibili(); // o referinta pentru a putea folosii
																					// metoda de actualizare a listei
																					// din BD
		this.manipulareAngajatiIndisponibili = new ManipulareAngajatiIndisponibili(
				jpanelechipamanager.echipa.getNume());
		this.listaAngajatiIndisponibili = manipulareAngajatiIndisponibili.getListaAngajatiIndisponibili();
		this.listaAngajatiDisponibili = manipulareAngajatiDisponibili.getListaAngajatiDisponibili();
	}

	public void actualizareContinua() {

		try {
			String url = "jdbc:mysql://localhost:3306/sys?&serverTimezone=UTC";
			String user = "root";
			String password = "password";

			Connection conn = DriverManager.getConnection(url, user, password);

			while (true) {
				try {
					Thread.sleep(5000);
				} catch (InterruptedException ie) {
					System.out.println(ie.getMessage());
				}

				java.util.Date utilDate = new java.util.Date();
				java.sql.Timestamp t = new java.sql.Timestamp(utilDate.getTime()); // obtin timpul actual in formatul
																					// necesar

				for (int i = 0; i < listaAngajatiIndisponibili.size(); i++) {
					if (listaAngajatiIndisponibili.get(i).getTimpSfarsit().compareTo(t) < 0) {
						System.out.println("Am actualizat:");
						String sqlCommandUpdate = "UPDATE angajati SET timp=null, numeEchipa=null, disponibil=true WHERE idangajati = ?";
						PreparedStatement ps = conn.prepareStatement(sqlCommandUpdate);
						ps.setInt(1, listaAngajatiIndisponibili.get(i).getId());
						ps.executeUpdate();
						this.listaAngajatiIndisponibili = manipulareAngajatiIndisponibili
								.actualizareListaAngajatiIndisponibili(jpanelechipamanager.echipa.getNume());
						this.listaAngajatiDisponibili = manipulareAngajatiDisponibili
								.actualizareListaAngajatiDisponibili();
						System.out.println("Disponibili sunt");
						System.out.println(listaAngajatiDisponibili);
						System.out.println();
						System.out.println();
						System.out.println("Indisponibili sunt");
						System.out.println(listaAngajatiIndisponibili);

					}
				}
			}
		} catch (SQLException sqle) {
			System.out.println(sqle.getMessage());
		}

	}

}
