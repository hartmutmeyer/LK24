import java.awt.*;

public class Hund {
	// Der Hund hat die Breite 70 Pixel und die Höhe 60 Pixel (unveränderbar)
	public Color farbe = Color.GRAY;
	public boolean wedeln = false;
	private boolean springend = false;
	// Linke obere Ecke:
	public int x = 0;
	public int y = 0;
	// Zähler für die Animation
	private int zaehler = 0;
	// Klassenvariable: zählt die Anzahl aller Hunde
	private static int anzahl = 0;

	public static int getAnzahlHunde() {
		return anzahl;
	}

	public Hund() {
		anzahl++;
	}

	public Hund(int xPos, int yPos, Color farbe) {
		x = xPos;
		y = yPos;
		this.farbe = farbe;
		wedeln = true;
		anzahl++;
	}

	public Hund(int x, int y, boolean wedeln) {
		this.x = x;
		this.y = y;
		farbe = Color.YELLOW;
		this.wedeln = wedeln;
		anzahl++;
	}

	public Hund(Color f, boolean w) {
		farbe = f;
		wedeln = w;
		anzahl++;
	}

	public void zeichnen(Graphics g) {
		Color c = g.getColor();
		if (wedeln == true) {
			// Schwanz nach rechts
			g.drawLine(x + 20, y + 0, x + 10, y + 20);
		} else {
			// Schwanz nach links
			g.drawLine(x + 0, y + 0, x + 10, y + 20);
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
