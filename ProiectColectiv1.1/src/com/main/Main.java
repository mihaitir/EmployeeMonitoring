package com.main;

import com.backend.FirExecutieHandleIstoric;
import com.jframe.*;
public class Main {
	public static void main(String[] args)
	{
		
		
		Login login = new Login();
		login.frmLogin.setVisible(true);
		FirExecutieHandleIstoric fir = new FirExecutieHandleIstoric();
		fir.start();
}
}
