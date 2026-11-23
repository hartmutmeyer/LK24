import java.awt.Color;
import java.awt.Graphics;

public class AnlegeSchiff extends FensterSchiff {
	private int stegPos;
	private int anlegeZaehler = 0;
	private final static int NOCH_NICHT_ANGELEGT = 0;
	private final static int ANGELEGT = 1;
	private final static int BEREITS_ANGELEGT = 2;
	private int zustand = NOCH_NICHT_ANGELEGT;

	public AnlegeSchiff(Color f, int y, int speed, int stegPos) {
		super(f, y, speed);
		this.stegPos = stegPos;
	}

	@Override
	public void bewegen() {	
		switch (zustand) {
			case NOCH_NICHT_ANGELEGT -> {
				super.bewegen();
				if (xPos >= stegPos) {
					zustand = ANGELEGT;
					System.out.println("ANGELEGT");
				}
			}
			case ANGELEGT -> {
				anlegeZaehler++;
				if (anlegeZaehler == 30) {
					zustand = BEREITS_ANGELEGT;
					System.out.println("BEREITS_ANGELEGT");
					anlegeZaehler = 0;
				}
			}
			case BEREITS_ANGELEGT -> {
				super.bewegen();
				if (xPos >= 500) {
					zustand = NOCH_NICHT_ANGELEGT;
					System.out.println("NOCH_NICHT_ANGELEGT");
				}
			}
		}
		
	}
}
