package com.backend;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import com.jframe.JFrameNotoficareTransferAngajatAltaEchipa;
import com.jframe.JFrameNotoficareTransferAngajatPeListaDisponibil;

public class FirExecutieHandleIstoric extends Thread{

	ArrayList<IstoricMunca> listaToata;
	ArrayList<IstoricMunca> listaIstoricAsupraUnuiAngajat;
	IstoricMuncaHandler imh;

	public void run()
    {
	
	imh = new IstoricMuncaHandler();	
	try {
		String url = "jdbc:mysql://localhost:3306/sys?&serverTimezone=UTC";
		String user = "root";
		String password = "password";
		Connection conn = DriverManager.getConnection(url, user, password);
	    
	    while(true)
	    { 	
	    	Thread.sleep(1000);
	    	//obtin timpul de acum
	    	java.util.Date utilDate = new java.util.Date();
		    java.sql.Timestamp timpCurent = new java.sql.Timestamp(utilDate.getTime());
		 
	    	listaToata = imh.getToateIstoricele();
	    	for(int i =0;i<listaToata.size();i++)
	    	{
	    		if (listaToata.get(i).getTimpSfarsit().compareTo(timpCurent)<0)
	    		{	
	    	     	listaIstoricAsupraUnuiAngajat = imh.getIsoricePentruAnumitAngajat(listaToata.get(i).getIdAngajat());
	    	     	if(listaIstoricAsupraUnuiAngajat.size()==1)
	    	     	{
	    	     		System.out.println(listaIstoricAsupraUnuiAngajat);
	    	     	//cazul in size==0 trec termina = 1 in baza de date si la studentul cu acel id trec nu;; ;a numeEchipa.disponibil.timp*2	 	     		
	    	    			
	    	    			String updateSQL = "UPDATE angajati set timpInceput=?, timpSfarsit=?, numeEchipa=?, disponibil = 1 where idAngajat=?";
	    					PreparedStatement preparedStatement = conn.prepareStatement(updateSQL);
	    					preparedStatement.setTimestamp(1, null);
	    					preparedStatement.setTimestamp(2, null);
	    					preparedStatement.setString(3,null);
	    					preparedStatement.setInt(4, listaIstoricAsupraUnuiAngajat.get(0).getIdAngajat());
	    					preparedStatement.executeUpdate();
	    					
	    					updateSQL = "UPDATE istoricmunca set terminat = 1 where idIstoric=?";
	    					preparedStatement = conn.prepareStatement(updateSQL);
	    					preparedStatement.setInt(1,listaIstoricAsupraUnuiAngajat.get(0).getIdIstoric());
	    					preparedStatement.executeUpdate();
	    					JFrameNotoficareTransferAngajatPeListaDisponibil f = new JFrameNotoficareTransferAngajatPeListaDisponibil(
	    							imh.getNumeAngajatDupaId(listaIstoricAsupraUnuiAngajat.get(0).getIdAngajat()));
	    					f.setVisible(true);
	    						
	    					
	    					break;
	    	    	} 
	    	     	else
	    	     	{
	    	     	//caut data urmatoare
	    	     		System.out.println(listaIstoricAsupraUnuiAngajat);
	    	     		String updateSQL = "UPDATE istoricmunca set terminat = 1 where idIstoric=?";
	    	     		PreparedStatement preparedStatement = conn.prepareStatement(updateSQL);
    					preparedStatement = conn.prepareStatement(updateSQL);
    					int n = listaIstoricAsupraUnuiAngajat.size();
    					preparedStatement.setInt(1,listaIstoricAsupraUnuiAngajat.get(n-1).getIdIstoric());
    					preparedStatement.executeUpdate();
    					
    					updateSQL = "UPDATE angajati set timpInceput=?, timpSfarsit=?, numeEchipa=?, disponibil = 0 where idAngajat=?";
    					preparedStatement = conn.prepareStatement(updateSQL);
    					preparedStatement.setTimestamp(1,listaIstoricAsupraUnuiAngajat.get(n-2).getTimpInceput());
    					preparedStatement.setTimestamp(2, listaIstoricAsupraUnuiAngajat.get(n-2).getTimpSfarsit());
    					preparedStatement.setString(3,imh.getNumeEchipaDupaId(listaIstoricAsupraUnuiAngajat.get(n-2).getIdEchipa()));
    					preparedStatement.setInt(4, listaIstoricAsupraUnuiAngajat.get(n-2).getIdAngajat());
    					preparedStatement.executeUpdate();
    					JFrameNotoficareTransferAngajatAltaEchipa f = new JFrameNotoficareTransferAngajatAltaEchipa(
    							imh.getNumeEchipaDupaId(listaIstoricAsupraUnuiAngajat.get(n-1).getIdEchipa()),
    							imh.getNumeEchipaDupaId(listaIstoricAsupraUnuiAngajat.get(n-2).getIdEchipa()),
    							imh.getNumeAngajatDupaId(listaIstoricAsupraUnuiAngajat.get(n-2).getIdAngajat()));
    					f.setVisible(true);
    					break;
	    	     	}
	    		}
	    	}
	    	
	    }
	    
	} catch (InterruptedException e) {
		e.printStackTrace();
	}
	catch (SQLException e) {
		System.out.println(e.getMessage());
	}
    }

}
