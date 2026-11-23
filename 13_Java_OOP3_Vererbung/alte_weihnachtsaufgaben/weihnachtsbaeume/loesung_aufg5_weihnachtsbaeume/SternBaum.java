import java.awt.*;
import hilfe.*;

class SternBaum extends KugelBaum {
	protected int zustand = 0;

	public SternBaum(int x, int y) {
		super(x, y);
	}

	@Override
	public void zeichnen(Graphics g) {
		super.zeichnen(g);
		g.setColor(Color.YELLOW);
		switch (zustand++ % 4) {
			case 0 -> {
				HZeichnen.fillDreieck(g, x + 80, y - 30, x + 120, y - 30, x + 100, y);
				HZeichnen.fillDreieck(g, x + 80, y - 10, x + 120, y - 10, x + 100, y - 40);
			}
			case 1, 3 -> {
				HZeichnen.fillDreieck(g, x + 90, y - 30, x + 110, y - 30, x + 100, y);
				HZeichnen.fillDreieck(g, x + 90, y - 10, x + 110, y - 10, x + 100, y - 40);
			}
			case 2 -> g.drawLine(x + 100, y, x + 100, y - 40);
		}
	}
}
