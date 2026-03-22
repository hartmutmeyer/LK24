import java.util.Random;

public class Philosoph extends Thread {
	static final int RUHT_SICH_AUS = 0;
	static final int ISST = 1;
	// Klassenvariablen
	private static Random zufall = new Random();
	private static Object monitor = new Object();

	// Objektvariablen
	private int zustand = RUHT_SICH_AUS;;
	private int staebchenRechts;
	private int staebchenLinks;
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

	boolean nimmStaebchen() {
		boolean erfolg = false;
		synchronized (monitor) {
			if (!staebchen[staebchenRechts] && !staebchen[staebchenLinks]) {
				staebchen[staebchenRechts] = true;
				staebchen[staebchenLinks] = true;
				erfolg = true;
			}
		}
		return erfolg;
	}

	void staebchenFreigeben() {
		synchronized (monitor) {
			staebchen[staebchenRechts] = false;
			staebchen[staebchenLinks] = false;
		}
	}

	@Override
	public void run() {
		while (true) {
			// Ausruhen simulieren
			try {
				Thread.sleep((zufall.nextInt(5) + 1) * 150);
			} catch (Exception e) { 
				/* nothing */
			}
			if (nimmStaebchen()) {
				zustand = ISST;
				tisch.repaint();
				// Essen simulieren
				try {
					Thread.sleep((zufall.nextInt(10) + 1) * 150);
				} catch (Exception e) { 
					/* nothing */
				}
				zustand = RUHT_SICH_AUS;
				tisch.repaint();
				staebchenFreigeben();
			}
		}
	}
}
