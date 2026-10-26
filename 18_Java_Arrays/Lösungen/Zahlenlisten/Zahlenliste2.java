import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.util.Random;

import hilfe.*;

public class Zahlenliste2 extends HJFrame {
	// globale Variablen
	private static final int WIDTH = 450;
	private static final int HEIGHT = 140;
	private static final Color BACKGROUND = Color.WHITE;
	private static final Color FOREGROUND = Color.BLACK;
	private int[] z = new int[10];
	private Random zufall = new Random();
	
	public Zahlenliste2(final String title) {
		super(WIDTH, HEIGHT, BACKGROUND, FOREGROUND, title);
		// eigene Initialisierung
		for (int i = 0; i < z.length; i++) {
			z[i] = zufall.nextInt(100) + 1;
		}
	}

	private int summeAllerZahlenDesArrays(int[] z) {
		int sum = 0;
		for (int i = 0; i < z.length; i++) {
			sum = sum + z[i];
		}
		return sum;
	}

	private int kleinsteZahlDesArrays(int[] array) {
		int min = 100;
		for (int i = 0; i < array.length; i++) {
			if (array[i] < min) {
				min = array[i];
			}
		}
		return min;
	}

	private int größteZahlDesArrays(int[] array) {
		int max = 0;
		for (int i = 0; i < array.length; i++) {
			if (array[i] > max) {
				max = array[i];
			}
		}
		return max;
	}

	@Override
	public void myPaint(Graphics g) {
		// wird aufgerufen, wenn das Fenster neu gezeichnet wird
		for (int x = 0; x < z.length; x++) {
			g.drawString("" + z[x], x * 40 + 30, 30);
		}

		// Summe
		g.drawString("Summe:  " + summeAllerZahlenDesArrays(z), 30, 60);

		// kleinste Zahl
		g.drawString("Kleinste Zahl: " + kleinsteZahlDesArrays(z), 30, 90);

		// größte Zahl
		g.drawString("Größte Zahl: " + größteZahlDesArrays(z), 30, 120);
	}

	public static void main(final String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					Zahlenliste2 anwendung = new Zahlenliste2("Zahlenliste2");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}