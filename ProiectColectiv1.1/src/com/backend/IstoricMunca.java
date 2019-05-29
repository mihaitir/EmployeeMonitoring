package com.backend;

import java.sql.Timestamp;

public class IstoricMunca {
	private int idIstoric;
	private int idAngajat;
	private int idEchipa;
	private Timestamp timpInceput;
	private Timestamp timpSfarsit;
	private int terminat;
	public int getIdIstoric() {
		return idIstoric;
	}
	public void setIdIstoric(int idIstoric) {
		this.idIstoric = idIstoric;
	}
	public int getIdAngajat() {
		return idAngajat;
	}
	public void setIdAngajat(int idAngajat) {
		this.idAngajat = idAngajat;
	}
	public int getIdEchipa() {
		return idEchipa;
	}
	public void setIdEchipa(int idEchipa) {
		this.idEchipa = idEchipa;
	}
	public Timestamp getTimpInceput() {
		return timpInceput;
	}
	public void setTimpInceput(Timestamp timpInceput) {
		this.timpInceput = timpInceput;
	}
	public Timestamp getTimpSfarsit() {
		return timpSfarsit;
	}
	public void setTimpSfarsit(Timestamp timpSfarsit) {
		this.timpSfarsit = timpSfarsit;
	}
	public int getTerminat() {
		return terminat;
	}
	public void setTerminat(int terminat) {
		this.terminat = terminat;
	}
	public IstoricMunca(int idIstoric, int idAngajat, int idEchipa, Timestamp timpInceput, Timestamp timpSfarsit,
			int terminat) {
		super();
		this.idIstoric = idIstoric;
		this.idAngajat = idAngajat;
		this.idEchipa = idEchipa;
		this.timpInceput = timpInceput;
		this.timpSfarsit = timpSfarsit;
		this.terminat = terminat;
	}
	@Override
	public String toString() {
		return "IstoricMunca [idIstoric=" + idIstoric + ", idAngajat=" + idAngajat + ", idEchipa=" + idEchipa
				+ ", timpInceput=" + timpInceput + ", timpSfarsit=" + timpSfarsit + ", terminat=" + terminat + "]";
	}
	
	

}
