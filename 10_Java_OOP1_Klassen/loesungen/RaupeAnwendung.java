import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;

import javax.swing.Timer;

import hilfe.*;

public class RaupeAnwendung extends HJFrame {
	// globale Variablen
	static final int WIDTH = 500;
	static final int HEIGHT = 500;
	private static final Color BACKGROUND = Color.WHITE;
	private static final Color FOREGROUND = Color.BLACK;
	private Raupe nimmersatt, immersatt, satt, gestopft;
	
	public RaupeAnwendung(final String title) {
		super(WIDTH, HEIGHT, BACKGROUND, FOREGROUND, title);
		// eigene Initialisierung
		nimmersatt = new Raupe(150, 100, Color.GREEN, 10);
		immersatt = new Raupe(100, 200, Color.BLUE, 7);
		satt = new Raupe(300, 300, Color.RED, 2);
		gestopft = new Raupe(200, 400, Color.GRAY, 1);
		Timer timer = new Timer(50, this);
		timer.start();
	}

	@Override
	public void myPaint(Graphics g) {
		// wird aufgerufen, wenn das Fenster neu gezeichnet wird
		nimmersatt.zeichnen(g);
		nimmersatt.bewegen();
		immersatt.zeichnen(g);
		immersatt.bewegen();
		satt.zeichnen(g);
		satt.bewegen();
		gestopft.zeichnen(g);
		gestopft.bewegen();
	}

	public static void main(final String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					RaupeAnwendung anwendung = new RaupeAnwendung("Raupe Anwendung");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}