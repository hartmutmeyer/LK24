import java.awt.*;

public class Blume {
	private int x = 0;
	private int y = 0;
	private static int anzahlBlumen;
	private Color farbeBluetenblatt = Color.RED;
	private Color farbeStempel = Color.YELLOW;
	private Color farbeStaengel = Color.GREEN;
	private int zeitZumVerbluehen = 200;
	private boolean verblueht = false;

	public Blume(int xpos, int ypos) {
		x = xpos;
		y = ypos;
		farbeBluetenblatt = switch (anzahlBlumen++ % 3) {
			case 0 -> Color.RED;
			case 1 -> Color.BLUE;
			case 2 -> Color.CYAN;
			default -> throw new IllegalArgumentException("Unexpected value: " + anzahlBlumen++ % 3);
		};
	}

	public void zeichnen(Graphics g) {
		if (y >= 100) {
			// Die Blume wächst noch
			y--;
		} else {
			if (zeitZumVerbluehen > 0) {
				zeitZumVerbluehen--;
			} else {
				verblueht = true;
			}
		}
		// Die Blume zeichen
		//  ° Blumenstängel und Blütenstempel in jedem Fall:
		g.setColor(farbeStaengel);
		g.fillRect(x + 29, y + 40, 3, 1000);
		g.setColor(farbeStempel);
		g.fillOval(x + 20, y + 20, 20, 20);
		//  ° Die Blütenblätter nur, wenn die Blume noch nicht verblüht ist:
		if (!verblueht) {
			g.setColor(farbeBluetenblatt);
			g.fillOval(x + 20, y, 20, 20);
			g.fillOval(x, y + 20, 20, 20);
			g.fillOval(x + 40, y + 20, 20, 20);
			g.fillOval(x + 20, y + 40, 20, 20);
		}
	}
}
