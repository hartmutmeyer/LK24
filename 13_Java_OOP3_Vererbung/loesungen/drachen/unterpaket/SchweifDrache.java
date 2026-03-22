import java.awt.Color;
import java.awt.Graphics;

public class SchweifDrache extends GesichtDrache {
	
	private Color schweifFarbe;

	public SchweifDrache(int x, int y, Color farbe, Color schweifFarbe) {
		super(x, y, farbe);
		this.schweifFarbe = schweifFarbe;
	}
	
	@Override
	public void zeichnen(Graphics g) {
		super.zeichnen(g);
		// Den Schwanz des Drachens zeichnen
		g.setColor(schweifFarbe);
		g.fillOval(x + 30, y + 120, 20, 20);
		g.fillOval(x + 30, y + 140, 20, 20);
		g.fillOval(x + 30, y + 160, 20, 20);
	}
		
}
