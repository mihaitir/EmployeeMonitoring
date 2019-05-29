package com.backend;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;

public class IstoricMuncaHandler {
	
	public ArrayList<IstoricMunca> getToateIstoricele()
	{
		ArrayList<IstoricMunca> l = new ArrayList<IstoricMunca>();
		
		try {
			String url = "jdbc:mysql://localhost:3306/sys?&serverTimezone=UTC";
			String user = "root";
			String password = "password";
			
			Connection conn = DriverManager.getConnection(url, user, password);
			Statement statement = conn.createStatement();
			ResultSet rs = statement.executeQuery("Select * from istoricmunca where terminat =0");
			while(rs.next())
			{
				int idIstoric = rs.getInt("idIstoric");
				int idAngajat = rs.getInt("idAngajat");
				int idEchipa = rs.getInt("idEchipa");
				Timestamp timpInceput = rs.getTimestamp("timpInceput");
				Timestamp timpSfarsit = rs.getTimestamp("timpSfarsit");
				int terminat = rs.getInt("terminat");
				IstoricMunca im = new IstoricMunca(idIstoric, idAngajat, idEchipa, timpInceput, timpSfarsit, terminat);
				l.add(im);				
			}	
			conn.close();	
		} catch (SQLException sqle) {
			System.out.println(sqle.getMessage());
		}
		return l;
	}
	
	
	public ArrayList<IstoricMunca> getIsoricePentruAnumitAngajat(int idAngajat)
	{
		
ArrayList<IstoricMunca> l = new ArrayList<IstoricMunca>();
		
		try {
			String url = "jdbc:mysql://localhost:3306/sys?&serverTimezone=UTC";
			String user = "root";
			String password = "password";
			
			Connection conn = DriverManager.getConnection(url, user, password);
			Statement statement = conn.createStatement();
			ResultSet rs = statement.executeQuery("Select * from istoricmunca where terminat = 0 and idAngajat="+idAngajat);
			while(rs.next())
			{
				int idIstoric = rs.getInt("idIstoric");
				int idEchipa = rs.getInt("idEchipa");
				Timestamp timpInceput = rs.getTimestamp("timpInceput");
				Timestamp timpSfarsit = rs.getTimestamp("timpSfarsit");
				int terminat = rs.getInt("terminat");
				IstoricMunca im = new IstoricMunca(idIstoric, idAngajat, idEchipa, timpInceput, timpSfarsit, terminat);
				l.add(im);
				
			}	
			conn.close();
		} catch (SQLException sqle) {
			System.out.println(sqle.getMessage());
		}
		return l;
	}
	
	public ArrayList<IstoricMunca> getIsoriceRealizatePentruAnumitAngajat(int idAngajat)
	{
		
ArrayList<IstoricMunca> l = new ArrayList<IstoricMunca>();
		
		try {
			String url = "jdbc:mysql://localhost:3306/sys?&serverTimezone=UTC";
			String user = "root";
			String password = "password";
			
			Connection conn = DriverManager.getConnection(url, user, password);
			Statement statement = conn.createStatement();
			ResultSet rs = statement.executeQuery("Select * from istoricmunca where terminat = 1 and idAngajat="+idAngajat);
			while(rs.next())
			{
				int idIstoric = rs.getInt("idIstoric");
				int idEchipa = rs.getInt("idEchipa");
				Timestamp timpInceput = rs.getTimestamp("timpInceput");
				Timestamp timpSfarsit = rs.getTimestamp("timpSfarsit");
				int terminat = rs.getInt("terminat");
				IstoricMunca im = new IstoricMunca(idIstoric, idAngajat, idEchipa, timpInceput, timpSfarsit, terminat);
				l.add(im);	
			}	
			conn.close();
		} catch (SQLException sqle) {
			System.out.println(sqle.getMessage());
		}
		return l;
	}
	
	public String getNumeEchipaDupaId(int idEchipa)
	{
		String nume= null;
		try {
			String url = "jdbc:mysql://localhost:3306/sys?&serverTimezone=UTC";
			String user = "root";
			String password = "password";
			
			Connection conn = DriverManager.getConnection(url, user, password);
			Statement statement = conn.createStatement();
			ResultSet rs = statement.executeQuery("Select numeEchipa from echipe where id="+idEchipa);
			rs.next();
			
			nume = rs.getString("numeEchipa");	
				
			conn.close();	
		} catch (SQLException sqle) {
			System.out.println(sqle.getMessage());
		}
		return nume;
	}
	
	public String getNumeAngajatDupaId(int idAngajat)
	{
		String nume= null;
		try {
			String url = "jdbc:mysql://localhost:3306/sys?&serverTimezone=UTC";
			String user = "root";
			String password = "password";
			
			Connection conn = DriverManager.getConnection(url, user, password);
			Statement statement = conn.createStatement();
			ResultSet rs = statement.executeQuery("Select nume from angajati where idAngajat="+idAngajat);
			rs.next();
			nume = rs.getString("nume");				
			conn.close();	
		} catch (SQLException sqle) {
			System.out.println(sqle.getMessage());
		}
		return nume;
	}
	
	public String getDepartamentDupaId(int idEchipa)
	{
		String nume= null;
		try {
			String url = "jdbc:mysql://localhost:3306/sys?&serverTimezone=UTC";
			String user = "root";
			String password = "password";
			
			Connection conn = DriverManager.getConnection(url, user, password);
			Statement statement = conn.createStatement();
			ResultSet rs = statement.executeQuery("Select departament from echipe where id="+idEchipa);
			rs.next();
			nume = rs.getString("departament");	
				
			conn.close();	
		} catch (SQLException sqle) {
			System.out.println(sqle.getMessage());
		}
		return nume;
	}
	
}
