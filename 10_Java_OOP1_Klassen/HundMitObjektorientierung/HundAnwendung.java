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
	private Hund bobby;
	private Hund charlie;
	private Hund maja;

	public HundAnwendung(final String title) {
		super(WIDTH, HEIGHT, BACKGROUND, FOREGROUND, title);
		// eigene Initialisierung
		bobby = new Hund(Color.RED, false);
		charlie = new Hund(200, 100, Color.GREEN);
		maja = new Hund(this);
		Timer timer = new Timer(100, this);
		timer.start();
	}

	@Override
	public void myPaint(Graphics g) {
		// wird aufgerufen, wenn das Fenster neu gezeichnet wird
		
		maja.zeichnen(g);
		
		bobby.zeichnen(g);
		bobby.bewegen(this, 2);
		
		charlie.zeichnen(g);
		charlie.bewegen(this, 5);
		
		g.drawString("Es gibt " + Hund.getAnzahlHunde() + " Hunde.", 350, 280);
	}

	public static void main(final String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HundAnwendung anwendung = new HundAnwendung("HundAnwendung (ojektorientiert)");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}