import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Toolkit;

import javax.swing.Timer;

import hilfe.*;

public class HundAnwendung extends HJFrame {
	// globale Variablen
	private static final int WIDTH = 800;
	private static final int HEIGHT = 300;
	private static final Color BACKGROUND = Color.LIGHT_GRAY;
	private static final Color FOREGROUND = Color.BLACK;
	private Hund h1, h2, h3, h4;

	public HundAnwendung(final String title) {
		super(WIDTH, HEIGHT, BACKGROUND, FOREGROUND, title);
		// eigene Initialisierung
		h1 = new Hund(50, 100, Color.GRAY);
		h2 = new Hund(300, 200, Color.BLACK);
		h3 = new Hund(150, 50, true);
		h4 = new Hund(250, 150, Color.RED);
		h2.freutSich(false);
		h3.freutSich(true);
		h4.freutSich(false);

		Timer timer = new Timer(100, this);
		timer.start();
	}

	@Override
	public void myPaint(Graphics g) {
		// wird aufgerufen, wenn das Fenster neu gezeichnet wird
		
		h1.zeichnen(g);

		h2.zeichnen(g);
		h2.bewegen(this, 10);

		h3.zeichnen(g);
		h3.bewegen(this, 0);
		
		h4.zeichnen(g);
		h4.bewegen(this,  5);
	
		g.drawString("Es gibt " + Hund.getAnzahlHunde() + " Hunde.", 350, 280);
	}

	public static void main(final String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HundAnwendung anwendung = new HundAnwendung("HundAnwendung (Aufgabe 1)");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}