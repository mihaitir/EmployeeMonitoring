package com.tools;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.awt.Graphics2D;
import java.awt.Image;

public class ResizeImagine {

	public static void creezaImagineRedimensionataDeTipJPG(BufferedImage image, int width, int height, String nume,
			String extensie) {
		BufferedImage resized = resizeJPG(image, width, height);
		// obtin calea pana la folderul in care se gaseste aplicatia in format STRING
		// proiectul nostru are si un folder src si apoi img in care punem imaginile
		// aplicatiei care ulterior vor fi redimensionate
		Path currentRelativePath = Paths.get("");
		String s = currentRelativePath.toAbsolutePath().toString();
		File output = new File(s + "\\src\\img\\" + nume + "." + extensie);
		try {
			ImageIO.write(resized, extensie, output);
		} catch (IOException ioex) {
			System.out.println(ioex.getMessage());
		}

	}

	public static void creezaImagineRedimensionataDeTipPNG(BufferedImage image, int width, int height, String nume,
			String extensie) {
		BufferedImage resized = resizePNG(image, width,height);
		Path currentRelativePath = Paths.get("");
		String s = currentRelativePath.toAbsolutePath().toString();
		File output = new File(s + "\\src\\img\\" + nume + "." + extensie);
		try {
			ImageIO.write(resized, extensie, output);
		} catch (IOException ioex) {
			System.out.println(ioex.getMessage());
		}

	}

	public static BufferedImage resizeJPG(BufferedImage img, int width, int height) {
		Image tmp = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
		BufferedImage resized = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		Graphics2D g2d = resized.createGraphics();
		g2d.drawImage(tmp, 0, 0, null);
		g2d.dispose();
		return resized;
	}
	
	public static BufferedImage resizePNG(BufferedImage img, int width, int height) {
		Image tmp = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
		BufferedImage resized = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2d = resized.createGraphics();
		g2d.drawImage(tmp, 0, 0, null);
		g2d.dispose();
		return resized;
	}

	
}