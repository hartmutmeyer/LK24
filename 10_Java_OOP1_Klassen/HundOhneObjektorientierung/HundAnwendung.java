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
	private int bobbyX;
	private int bobbyY;
	private Color bobbyFarbe;
	private int bobbyGeschwindigkeit;
	private int charlieX;
	private int charlieY;
	private Color charlieFarbe;
	private int charlieGeschwindigkeit;
	private int anzahlHunde = 0;
	private int zaehler = 0;

	public HundAnwendung(final String title) {
		super(WIDTH, HEIGHT, BACKGROUND, FOREGROUND, title);
		// eigene Initialisierung

		// Erster Hund: Bobby
		bobbyX = 0;
		bobbyY = 0;
		bobbyFarbe = Color.RED;
		bobbyGeschwindigkeit = 2;
		anzahlHunde++;

		// Zweiter Hund: Charlie
		charlieX = 200;
		charlieY = 100;
		charlieFarbe = Color.GREEN;
		charlieGeschwindigkeit = 5;
		anzahlHunde++;

		// Timer für die Animation starten
		Timer timer = new Timer(100, this);
		timer.start();
	}

	public void hundZeichnen(Graphics g, int x, int y, Color farbe, boolean springend, boolean freutSich) {
		Color c = g.getColor();
		if (freutSich) {
			if (zaehler % 2 == 0) {
				// Schwanz nach links
				g.drawLine(x + 0, y + 0, x + 10, y + 20);
			} else {
				// Schwanz nach rechts
				g.drawLine(x + 20, y + 0, x + 10, y + 20);
			}
		} else {
			// Der Hund freut sich nicht --> Schwanz zeigt immer nach unten (wedelt nicht)
			g.drawLine(x + 0, y + 40, x + 10, y + 20);
		}
		if (springend) {
			// malt den Hund springend
			g.drawLine(x + 18, y + 40, x + 0, y + 52);
			g.drawLine(x + 20, y + 40, x + 2, y + 52);
			g.drawLine(x + 44, y + 40, x + 56, y + 52);
			g.drawLine(x + 46, y + 40, x + 58, y + 52);
		} else {
			// malt den Hund stehend
			g.drawLine(x + 18, y + 40, x + 18, y + 60);
			g.drawLine(x + 20, y + 40, x + 20, y + 60);
			g.drawLine(x + 44, y + 40, x + 44, y + 60);
			g.drawLine(x + 46, y + 40, x + 46, y + 60);
		}
		g.setColor(farbe);
		g.fillRoundRect(x + 10, y + 15, 45, 25, 12, 12);
		g.fillOval(x + 49, y + 0, 21, 21);
		g.setColor(c);
		g.drawRoundRect(x + 10, y + 15, 45, 25, 12, 12);
		g.drawOval(x + 49, y + 0, 21, 21);
	}

	@Override
	public void myPaint(Graphics g) {
		// wird aufgerufen, wenn das Fenster neu gezeichnet wird
		zaehler++;
		
		if (zaehler % 2 == 0) {
			hundZeichnen(g, bobbyX, bobbyY, bobbyFarbe, true, false);
		} else {
			hundZeichnen(g, bobbyX, bobbyY, bobbyFarbe, false, false);
		}
		bobbyX += bobbyGeschwindigkeit;
		if (bobbyX > 500) {
			bobbyX = -60;
		}

		if (zaehler % 2 == 0) {
			hundZeichnen(g, charlieX, charlieY, charlieFarbe, true, true);
		} else {
			hundZeichnen(g, charlieX, charlieY, charlieFarbe, false, true);
		}
		charlieX += charlieGeschwindigkeit;
		if (charlieX > 500) {
			charlieX = -60;
		}

		g.drawString("Es gibt " + anzahlHunde + " Hunde.", 350, 280);
	}

	public static void main(final String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HundAnwendung anwendung = new HundAnwendung("HundAnwendung (ohne Objektorientierung)");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}