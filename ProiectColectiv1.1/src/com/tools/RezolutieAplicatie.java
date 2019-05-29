package com.tools;

import java.awt.Dimension;
import java.awt.Toolkit;

public class RezolutieAplicatie {
	private double width;
	private double height;

	public RezolutieAplicatie() {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		this.width = screenSize.getWidth();
		this.height = screenSize.getHeight();
	}
	
	public double getWidth() {
		return width;
	}

	public void setWidth(double width) {
		this.width = width;
	}

	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
	}

	/**
	 * 
	 * @param procent
	 * @return functia calculeaza lungimea unei componente in functie de cat % dorim
	 *         noi sa ocupe pe lungime (de la stanga la dreapta)
	 */

	public int getLungimeComponenta(int procent) {
		return (int) width * procent / 100;
	}

	/**
	 * 
	 * @param procent
	 * @return functia calculeaza inaltimea unei componente in functie de cat% dorim
	 *         noi sa ocupe pe inaltime (sus in jos )
	 */

	public int getInaltimeComponenta(int procent) {
		return (int) height * procent / 100;
	}

	public int getLungimeComponenta(int procent, double width) {
		return (int) width * procent / 100;
	}

	public int getInaltimeComponenta(int procent, double height) {
		return (int) height * procent / 100;
	}

	public int getPozitieX(int procent) {
		return (int) width * procent / 100;
	}

	public int getPozitieY(int procent) {
		return (int) height * procent / 100;

	}
	
	public int getPozitieX(int procent, double width) {
		return (int) width * procent / 100;
	}

	public int getPozitieY(int procent, double height) {
		return (int) height * procent / 100;

	}

	
}
