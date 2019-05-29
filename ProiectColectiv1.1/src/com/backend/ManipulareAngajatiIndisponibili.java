package com.backend;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ManipulareAngajatiIndisponibili {
	
	private ArrayList<Angajat> listaAngajatiIndisponibili = new ArrayList<Angajat>();
	@SuppressWarnings("unused")
	private String numeEchipa;
	
	public ArrayList<Angajat> getListaAngajatiIndisponibili() {
		return listaAngajatiIndisponibili;
	}

	public void setListaAngajatiIndisponibili(ArrayList<Angajat> listaAngajatiIndisponibili) {
		this.listaAngajatiIndisponibili = listaAngajatiIndisponibili;
	}

	public ManipulareAngajatiIndisponibili(String numeEchipa)
	{
		this.numeEchipa = numeEchipa;
		this.listaAngajatiIndisponibili = actualizareListaAngajatiIndisponibili(numeEchipa);
	}
	
	public ArrayList<Angajat> actualizareListaAngajatiIndisponibili(String numeEchipa)
	{
		ArrayList<Angajat> listaAngajatiIndisponibili = new ArrayList<Angajat>();
		try {
			String url = "jdbc:mysql://localhost:3306/sys?&serverTimezone=UTC";
			String user = "root";
			String password = "password";

			Connection conn = DriverManager.getConnection(url, user, password);
			String s = "select * from angajati where numeEchipa = ?";
			PreparedStatement ps = conn.prepareStatement(s);
			ps.setString(1, numeEchipa);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				int idangajati = rs.getInt(1);
				String nume = rs.getString(2);
				boolean disponibil = rs.getBoolean(4);
				java.sql.Timestamp timpInceput = rs.getTimestamp(5);
				java.sql.Timestamp timpSfarsit = rs.getTimestamp(6);
				
				Angajat tmp = new Angajat(idangajati, nume, numeEchipa, disponibil, timpInceput, timpSfarsit);
				listaAngajatiIndisponibili.add(tmp);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return listaAngajatiIndisponibili;
	}

}
