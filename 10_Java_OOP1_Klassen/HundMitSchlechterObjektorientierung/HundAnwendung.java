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
	Hund bobby;
	Hund charlie;
	int zaehler;

	public HundAnwendung(final String title) {
		super(WIDTH, HEIGHT, BACKGROUND, FOREGROUND, title);
		// eigene Initialisierung
		Timer timer = new Timer(100, this);
		timer.start();
		bobby = new Hund(Color.RED, false);
		charlie = new Hund(200, 100, Color.GREEN);
	}

	@Override
	public void myPaint(Graphics g) {
		// wird aufgerufen, wenn das Fenster neu gezeichnet wird
		zaehler++;
		
		bobby.zeichnen(g);
		bobby.x += 2;
		if (bobby.x > WIDTH) {
			bobby.x = -100;
		}
		
		if (zaehler % 2 == 0) {
			charlie.wedeln = true;
		} else {
			charlie.wedeln = false;
		}
		charlie.zeichnen(g);
		charlie.x += 5;
		if (charlie.x > WIDTH) {
			charlie.x = -100;
			charlie.farbe = Color.BLACK;
		}
		g.drawString("Es gibt " + Hund.getAnzahlHunde() + " Hunde.", 350, 280);
	}

	public static void main(final String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					HundAnwendung anwendung = new HundAnwendung("HundAnwendung (schlecht ojektorientiert)");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}