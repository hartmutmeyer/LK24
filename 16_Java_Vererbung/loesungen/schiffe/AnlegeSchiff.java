import java.awt.Color;
import java.awt.Graphics;

public class AnlegeSchiff extends FensterSchiff {
	private int stegPos;
	private int anlegeZaehler = 0;
	private final static int NICHT_ANGELEGT = 0;
	private final static int ANGELEGT = 1;
	private int zustand = NICHT_ANGELEGT;

	public AnlegeSchiff(Color f, int y, int speed, int stegPos) {
		super(f, y, speed);
		this.stegPos = stegPos;
	}

	@Override
	public void bewegen() {
		switch (zustand) {
		case NICHT_ANGELEGT:
			super.bewegen();
			if (xPos >= stegPos && xPos < stegPos + speed) {
				zustand = ANGELEGT;
			}
			break;
		case ANGELEGT:
			anlegeZaehler++;
			if (anlegeZaehler == 30) {
				zustand = NICHT_ANGELEGT;
				anlegeZaehler = 0;
			}
		}
	}
}
