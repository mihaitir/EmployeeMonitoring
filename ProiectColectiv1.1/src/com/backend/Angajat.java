package com.backend;

import java.sql.Timestamp;

public class Angajat {
	private int id;
	private String nume;
	private java.sql.Timestamp timpInceput;
	private java.sql.Timestamp timpSfarsit;
	private String numeEchipa;
	private boolean disponibil;

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Angajat other = (Angajat) obj;
		if (disponibil != other.disponibil)
			return false;
		if (id != other.id)
			return false;
		if (nume == null) {
			if (other.nume != null)
				return false;
		} else if (!nume.equals(other.nume))
			return false;
		if (numeEchipa == null) {
			if (other.numeEchipa != null)
				return false;
		} else if (!numeEchipa.equals(other.numeEchipa))
			return false;
		if (timpInceput == null) {
			if (other.timpInceput != null)
				return false;
		} else if (!timpInceput.equals(other.timpInceput))
			return false;
		if (timpSfarsit == null) {
			if (other.timpSfarsit != null)
				return false;
		} else if (!timpSfarsit.equals(other.timpSfarsit))
			return false;
		return true;
	}

	public Angajat(int id, String nume, String numeEchipa, boolean disponibil, Timestamp timpInceput,
			Timestamp timpSfarsit) {
		super();
		this.id = id;
		this.nume = nume;
		this.timpInceput = timpInceput;
		this.timpSfarsit = timpSfarsit;
		this.numeEchipa = numeEchipa;
		this.disponibil = disponibil;
	}
	
	public Angajat () {
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNume() {
		return nume;
	}

	public java.sql.Timestamp getTimpInceput() {
		return timpInceput;
	}

	public void setTimpInceput(java.sql.Timestamp timpInceput) {
		this.timpInceput = timpInceput;
	}

	public java.sql.Timestamp getTimpSfarsit() {
		return timpSfarsit;
	}

	public void setTimpSfarsit(java.sql.Timestamp timpSfarsit) {
		this.timpSfarsit = timpSfarsit;
	}

	public void setNume(String nume) {
		this.nume = nume;
	}

	public String getNumeEchipa() {
		return numeEchipa;
	}

	public void setNumeEchipa(String numeEchipa) {
		this.numeEchipa = numeEchipa;
	}

	public boolean isDisponibil() {
		return disponibil;
	}

	public void setDisponibil(boolean disponibil) {
		this.disponibil = disponibil;
	}

	@Override
	public String toString() {
		return "Angajat [id=" + id + ", nume=" + nume + ", timpInceput=" + timpInceput + ", timpSfarsit=" + timpSfarsit
				+ ", numeEchipa=" + numeEchipa + ", disponibil=" + disponibil + "]";
	}

}
