	package com.backend;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Echipa {

	private int id;
	private String nume;
	private String departament;
	private ArrayList<Angajat> listaAngajati = new ArrayList<Angajat>();

	public String getDepartament() {
		return departament;
	}

	public void setDepartament(String departament) {
		this.departament = departament;
	}

	public ArrayList<Angajat> getListaAngajati() {
		return listaAngajati;
	}

	public void setListaAngajati(ArrayList<Angajat> listaAngajati) {
		this.listaAngajati = listaAngajati;
	}

	public String getNume() {
		return nume;
	}

	public void setNume(String nume) {
		this.nume = nume;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Echipa(int id, String nume, String departament) {
		super();
		this.nume = nume;
		this.id = id;
		this.departament = departament;
		// cand creez echipa accesez baza de date pentru a citii toti angajatii din
		// spate

		try {
			String url = "jdbc:mysql://localhost:3306/sys?&serverTimezone=UTC";
			String user = "root";
			String password = "password";

			Connection conn = DriverManager.getConnection(url, user, password);
			String s = "select * from angajati where numeEchipa = ?";
			PreparedStatement ps = conn.prepareStatement(s);
			ps.setString(1, nume);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				int idangajati = rs.getInt(1);
				String numeAngajat = rs.getString(2);
				boolean disponibil = rs.getBoolean(4);
				java.sql.Timestamp timpInceput = rs.getTimestamp(5);
				java.sql.Timestamp timpSfarsit = rs.getTimestamp(6);
				Angajat tmp = new Angajat(idangajati, numeAngajat, nume, disponibil,timpInceput, timpSfarsit);
				listaAngajati.add(tmp);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Echipa other = (Echipa) obj;
		if (departament == null) {
			if (other.departament != null)
				return false;
		} else if (!departament.equals(other.departament))
			return false;
		if (id != other.id)
			return false;
		if (listaAngajati == null) {
			if (other.listaAngajati != null)
				return false;
		} else if (!listaAngajati.equals(other.listaAngajati))
			return false;
		if (nume == null) {
			if (other.nume != null)
				return false;
		} else if (!nume.equals(other.nume))
			return false;
		return true;
	}

	
	@Override
	public String toString() {
		return "Echipa " + nume;
	}

}
