import java.util.Random;

public class Philosoph extends Thread {
	static final int OHNE_STAEBCHEN = 0;
	static final int MIT_EINEM_STAEBCHEN = 1;
	static final int MIT_ZWEI_STAEBCHEN = 2;
	// Klassenvariablen
	private static Random zufall = new Random();

	// Objektvariablen
	private int zustand = OHNE_STAEBCHEN;
	private int staebchenRechts;
	private int staebchenLinks;
	private int anzahlStaebchen;
	private Tisch tisch;
	private boolean staebchen[];

	public Philosoph(Tisch tisch, boolean staebchen[], int platz) {
		this.tisch = tisch;
		this.staebchen = staebchen;
		this.staebchenRechts = platz;
		staebchenLinks = platz - 1;
		if (staebchenLinks < 0) {
			staebchenLinks = 4;
		}
	}
	
	public int getZustand() {
		return zustand;
	}

	boolean nimmStaebchen(int pos) {
		boolean erfolg = false;
		if (!staebchen[pos]) {
			staebchen[pos] = true;
			erfolg = true;
		}
		return erfolg;
	}

	void staebchenFreigeben() {
		staebchen[staebchenRechts] = false;
		staebchen[staebchenLinks] = false;
	}

	@Override
	public void run() {
		while (true) {
			// Ausruhen simulieren
			try {
				Thread.sleep((zufall.nextInt(5) + 1) * 150);
			} catch (Exception e) {
			}
			if (nimmStaebchen(staebchenRechts)) {
				anzahlStaebchen++;
			}
			if (nimmStaebchen(staebchenLinks)) {
				anzahlStaebchen++;
			}
			switch (anzahlStaebchen) {
				case 0 -> {
					zustand = OHNE_STAEBCHEN;
					tisch.repaint();
				}
				case 1 -> {
					zustand = MIT_EINEM_STAEBCHEN;
					tisch.repaint();
				}
				case 2 -> {
					zustand = MIT_ZWEI_STAEBCHEN;
					tisch.repaint();
					// Essen simulieren
					try {
						Thread.sleep((zufall.nextInt(10) + 1) * 150);
					} catch (Exception e) {
					}
					staebchenFreigeben();
					anzahlStaebchen = 0;
					zustand = OHNE_STAEBCHEN;
					tisch.repaint();
				}
			}
		}
	}
}
