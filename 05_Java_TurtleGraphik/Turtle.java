import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class Turtle extends JPanel {

	private static final long serialVersionUID = 1L;
	private double x, y;
	private double winkel = 180; // Blick nach oben
	private double bogenmass;
	private boolean turtleSichtbar = false;

	public Turtle(int xStart, int yStart) {
		setBackground(Color.WHITE);
		x = xStart;
		y = yStart;
		bogenmass = Math.PI * winkel / 180.0;
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		turtleSichtbar = false;
		zeichneTurtle(g);
	}

	/**
	 * Zeichnet (oder löscht im XOR-Modus) ein kleines rotes Dreieck 
	 * an der aktuellen Position und Blickrichtung der Turtle.
	 */
	private void zeichneTurtle(Graphics g) {
		if (g == null) {
			return;
		}
		int groesse = 10;
		double spitzeX = x + groesse * Math.sin(bogenmass);
		double spitzeY = y + groesse * Math.cos(bogenmass);
		double linksX = x + (groesse * 0.6) * Math.sin(bogenmass + 2.4);
		double linksY = y + (groesse * 0.6) * Math.cos(bogenmass + 2.4);
		double rechtsX = x + (groesse * 0.6) * Math.sin(bogenmass - 2.4);
		double rechtsY = y + (groesse * 0.6) * Math.cos(bogenmass - 2.4);

		int[] xPunkte = { (int) Math.round(spitzeX), (int) Math.round(linksX), (int) Math.round(rechtsX) };
		int[] yPunkte = { (int) Math.round(spitzeY), (int) Math.round(linksY), (int) Math.round(rechtsY) };

		g.setXORMode(Color.WHITE);
		g.setColor(Color.RED);
		g.fillPolygon(xPunkte, yPunkte, 3);
		g.setPaintMode();
		turtleSichtbar = !turtleSichtbar;
	}

	public void vor(double strecke) {
		Graphics g = getGraphics();
		if (g == null) {
			return;
		}
		if (turtleSichtbar) {
			zeichneTurtle(g); // Alte Turtle-Anzeige löschen
		}

		double dx = strecke * Math.sin(bogenmass);
		double dy = strecke * Math.cos(bogenmass);
		double xEnde = x + dx;
		double yEnde = y + dy;

		g.setColor(Color.BLACK);
		g.drawLine((int) Math.round(x), (int) Math.round(y),
				(int) Math.round(xEnde), (int) Math.round(yEnde));
		x = xEnde;
		y = yEnde;

		zeichneTurtle(g); // Turtle an neuer Position zeichnen
	}

	public void drehen(double plusWinkel) {
		Graphics g = getGraphics();
		if (turtleSichtbar && g != null) {
			zeichneTurtle(g); // Alte Turtle-Anzeige löschen
		}

		winkel += plusWinkel;
		while (winkel >= 360) {
			winkel -= 360;
		}
		while (winkel < 0) {
			winkel += 360;
		}
		bogenmass = Math.PI * winkel / 180.0;

		if (g != null) {
			zeichneTurtle(g); // Turtle mit neuem Winkel zeichnen
		}
	}

	public void neuStart(int neuX, int neuY, int neuWinkel) {
		Graphics g = getGraphics();
		if (turtleSichtbar && g != null) {
			zeichneTurtle(g);
		}
		x = neuX;
		y = neuY;
		winkel = neuWinkel;
		bogenmass = Math.PI * winkel / 180.0;
		if (g != null) {
			zeichneTurtle(g);
		}
	}

	public void neuStart(int neuX, int neuY) {
		neuStart(neuX, neuY, 180); // Blick nach oben
	}

	public void loeschen() {
		turtleSichtbar = false;
		repaint();
	}

	public int getXpos() {
		return (int) Math.round(x);
	}

	public int getYpos() {
		return (int) Math.round(y);
	}

	public int getWinkel() {
		/*
		 * Umrechnung: Blick nach oben ist 0 Grad (statt 180 wie für die
		 * interne Rechnung)
		 */
		if (winkel >= 180) {
			return (int) Math.round(winkel - 180);
		} else {
			return (int) Math.round(winkel + 180);
		}
	}
}
