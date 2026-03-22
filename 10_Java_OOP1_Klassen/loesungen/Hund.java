import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

import javax.swing.JFrame;

public class Hund {
	// KLASSENVARIABLEN (static): 
	// eine Variable, die zwischen allen Objekten der Klasse geteilt wird (also keine individuelle Eigenschaft
	// einzelner Objekte beschreibt)

	// zählt die Anzahl aller Hunde
	private static int anzahlHunde = 0;

	// ATTRIBUTE DER KLASSE:
	// Mit Hilfe der globalen Variablen werden die Eigenschaften von Objekten der Klasse beschrieben.
	
	private Color farbe = Color.GRAY;
	private boolean freutSich = false;
	private boolean springend = false;
	// Linke obere Ecke des Hundes:
	private int x = 0;
	private int y = 0;
	// Zähler für die Animation
	private int zaehler = 0;

	// STATISCHE METHODEN (static): 
	// Brauchen kein Objekt der Klasse um sie aufrufen zu können.
	
	public static int getAnzahlHunde() {
		return anzahlHunde;
	}
	
	// KONSTRUKTOR(EN):
	// Werden benutzt, wenn ein Objekt der Klasse zu erzeugen und sorgen dafür, dass die
	// Startbedingungen für das neue Objekt sinnvoll gesetzt werden.
	
	public Hund(JFrame frame) {
		Random zufall = new Random();
		// Das neue Hund-Objekt wird an einer zufälligen Stelle des Fensters erzeugt
		x = zufall.nextInt(frame.getContentPane().getWidth() - 70);
		y = zufall.nextInt(frame.getContentPane().getHeight() - 60);
		anzahlHunde++;
	}

	public Hund(int xPos, int yPos, Color farbe) {
		x = xPos;
		y = yPos;
		this.farbe = farbe;
		freutSich = true;
		anzahlHunde++;
	}

	public Hund(int x, int y, boolean freutSich) {
		this.x = x;
		this.y = y;
		farbe = Color.YELLOW;
		this.freutSich = freutSich;
		anzahlHunde++;
	}

	public Hund(Color farbe, boolean f) {
		this.farbe = farbe;
		freutSich = f;
		anzahlHunde++;
	}

	// METHODEN DER KLASSE:
	// Definieren, was mit den Objekten der Klasse gemacht werden kann.
	
	public void bewegen(JFrame frame, int vNachRechts) {
		x += vNachRechts;
		if (x > frame.getContentPane().getWidth()) {
			x = -60;
		}
		if (zaehler++ % 2 == 0 && vNachRechts != 0) {
			springend = true;
		} else {
			springend = false;
		}
	}

	public void freutSich(boolean gutGelaunt) {
		freutSich = gutGelaunt;
	}

	public void zeichnen(Graphics g) {
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
}
