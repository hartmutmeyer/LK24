import java.awt.*;

public class Strichmensch {
	private int y = 0;
	private int kmh = 0;
	private Color farbek = Color.RED;
	private Color farbeh = Color.BLACK;
	private static int z1 = 0;
	private int x = 10;
	private int a = 0;
	private boolean hut = false;

	public Strichmensch(int xPos, int yPos, Color farbek, Color farbeh, int kmh) {
		y = yPos;
		x = xPos;
		this.farbek = farbek;
		this.farbeh = farbeh;
		this.kmh = kmh;
		z1++;
		if (z1 % 3 == 0) {
			hut = true;
		} else {
			hut = false;
		}
	}

	public void zeichnen(Graphics g) {

		switch (a % 4) {
			case 0 -> {
				g.setColor(Color.BLACK);
				g.drawOval(x, y, 20, 20);
				g.setColor(farbeh);
				g.fillOval(x, y, 20, 20);
				g.setColor(farbek);
				g.fillOval(x, y + 20, 20, 50);
				g.setColor(Color.BLACK);
				// Beine
				// g.drawLine(x+10,y+70,x+10,y+110);
				g.drawLine(x + 10, y + 70, x - 10, y + 110);
				g.drawLine(x + 10, y + 70, x + 30, y + 110);

				// Arme
				g.drawLine(x + 10, y + 30, x + 10, y + 50);
				g.drawLine(x + 10, y + 50, x + 20, y + 50);
			}
			case 1 -> {
				g.setColor(Color.BLACK);
				g.drawOval(x, y, 20, 20);
				g.setColor(farbeh);
				g.fillOval(x, y, 20, 20);
				g.setColor(farbek);
				g.fillOval(x, y + 20, 20, 50);
				g.setColor(Color.BLACK);
				// Beine
				g.drawLine(x + 10, y + 70, x + 10, y + 110);
				g.drawLine(x + 10, y + 70, x + 20, y + 90);
				g.drawLine(x + 20, y + 90, x, y + 100);

				// Arme
				g.drawLine(x + 10, y + 30, x + 20, y + 50);
				g.drawLine(x + 20, y + 50, x + 30, y + 50);
			}
			case 2 -> {
				g.setColor(Color.BLACK);
				g.drawOval(x, y, 20, 20);
				g.setColor(farbeh);
				g.fillOval(x, y, 20, 20);
				g.setColor(farbek);
				g.fillOval(x, y + 20, 20, 50);
				g.setColor(Color.BLACK);
				// Beine
				g.drawLine(x + 10, y + 70, x - 10, y + 110);
				g.drawLine(x + 10, y + 70, x + 30, y + 110);
				// Arme
				g.drawLine(x + 10, y + 30, x + 10, y + 50);
				g.drawLine(x + 10, y + 50, x + 20, y + 50);
			}
			case 3 -> {
				g.setColor(Color.BLACK);
				g.drawOval(x, y, 20, 20);
				g.setColor(farbeh);
				g.fillOval(x, y, 20, 20);
				g.setColor(farbek);
				g.fillOval(x, y + 20, 20, 50);
				g.setColor(Color.BLACK);
				// Beine
				g.drawLine(x + 10, y + 70, x - 10, y + 90);
				g.drawLine(x - 10, y + 90, x - 30, y + 80);

				g.drawLine(x + 10, y + 70, x + 30, y + 110);
				// Arme
				g.drawLine(x + 10, y + 30, x - 10, y + 40);
				g.drawLine(x - 10, y + 40, x - 10, y + 50);
			}
		}

		if (hut == true) {
			g.drawLine(x, y, x + 20, y);
			g.setColor(Color.BLACK);
			g.fillRect(x + 5, y - 10, 10, 10);
		}
		a++;
	}

	public void laufen() {
		x = x + kmh;
		if (x >= 1000) {
			x = -50;
		}
	}

}