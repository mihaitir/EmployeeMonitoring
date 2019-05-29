package com.backend;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ManipulareAngajatiDisponibili {

	private ArrayList<Angajat> listaAngajatiDisponibili = new ArrayList<Angajat>();

	public ArrayList<Angajat> getListaAngajatiDisponibili() {
		return listaAngajatiDisponibili;
	}

	public void setListaAngajatiDisponibili(ArrayList<Angajat> listaAngajatiDisponibili) {
		this.listaAngajatiDisponibili = listaAngajatiDisponibili;
	}

	public ManipulareAngajatiDisponibili() {
		this.listaAngajatiDisponibili = actualizareListaAngajatiDisponibili();
	}

	public ArrayList<Angajat> actualizareListaAngajatiDisponibili() {
		ArrayList<Angajat> listaAngajatiDisponibili = new ArrayList<Angajat>();
		try {

			String url = "jdbc:mysql://localhost:3306/sys?&serverTimezone=UTC";
			String user = "root";
			String password = "password";

			Connection conn = DriverManager.getConnection(url, user, password);
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery("select * from angajati");

			while (rs.next()) {
				if (rs.getBoolean("disponibil") == true) {
					int idangajati = rs.getInt(1);
					String nume = rs.getString(2);
					String numeEchipa = rs.getString(3);
					boolean disponibil = rs.getBoolean(4);
					java.sql.Timestamp timpInceput = rs.getTimestamp(5);
					java.sql.Timestamp timpSfarsit = rs.getTimestamp(6);
					
					
					Angajat tmp = new Angajat(idangajati, nume, numeEchipa, disponibil, timpInceput, timpSfarsit);
//	        		listaAngajatiIndisponibili.remove(tmp);
					// oarecum acum sa tai nu pare important
//					System.out.print("Am adaugat in disponibili pe "+ tmp);
					listaAngajatiDisponibili.add(tmp);
				}
			}
			conn.close(); // sa nu uitam sa inchdem conexiunea la baza de date

		}

		catch (SQLException sqle) {
			System.out.println(sqle.getMessage());
		}
	return listaAngajatiDisponibili;
	}
}
