import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

import javax.swing.Timer;

import hilfe.*;

public class GameOfLife extends HJFrame implements KeyListener {
	// globale Variablen
	private static final int WIDTH = 800;
	private static final int HEIGHT = 800;
	private static final Color BACKGROUND = Color.LIGHT_GRAY;
	private static final Color FOREGROUND = Color.BLUE;
	private static final int CELLWIDTH = 8;
	private static final int GROESSE = WIDTH / CELLWIDTH;
	private static final boolean LEBENDIG = true;
	private static final boolean TOT = false;
	private int runde;
	private int geschwindigkeit = 100;
	private boolean[][] spielfeldA = new boolean[GROESSE + 2][GROESSE + 2];
	private boolean[][] spielfeldB = new boolean[GROESSE + 2][GROESSE + 2];
	private Random zufall = new Random();
	private Timer timer;

	public GameOfLife(final String title) {
		super(WIDTH, HEIGHT, BACKGROUND, FOREGROUND, title);
		// eigene Initialisierung
		timer = new Timer(geschwindigkeit, this);
		timer.start();
		spielfeldInitialisieren();
		addKeyListener(this);
	}

	@Override
	public void myPaint(Graphics g) {
		// wird aufgerufen, wenn das Fenster neu gezeichnet wird
		if ((runde++ % 2) == 0) {
			for (int x = 1; x <= GROESSE; x++) {
				for (int y = 1; y <= GROESSE; y++) {
					if (spielfeldA[x][y] == LEBENDIG) {
						zeichneLebendig(g, x, y);
					} else {
						zeichneTot(g, x, y);
					}
					spielfeldB[x][y] = totOderLebendig(x, y, spielfeldA);
				}
			}
		} else {
			for (int x = 1; x <= GROESSE; x++) {
				for (int y = 1; y <= GROESSE; y++) {
					if (spielfeldB[x][y] == LEBENDIG) {
						zeichneLebendig(g, x, y);
					} else {
						zeichneTot(g, x, y);
					}
					spielfeldA[x][y] = totOderLebendig(x, y, spielfeldB);
				}
			}
		}
	}

	public void zeichneLebendig(Graphics g, int x, int y) {
		g.fillRect((x - 1) * CELLWIDTH, (y - 1) * CELLWIDTH, CELLWIDTH, CELLWIDTH);
	}

	public void zeichneTot(Graphics g, int x, int y) {
		g.drawRect((x - 1) * CELLWIDTH, (y - 1) * CELLWIDTH, CELLWIDTH, CELLWIDTH);
	}

	public void spielfeldInitialisieren() {
		for (int x = 1; x <= GROESSE; x++) {
			for (int y = 1; y <= GROESSE; y++) {
				spielfeldA[x][y] = TOT;
			}
		}
		for (int x = GROESSE / 4; x <= GROESSE - GROESSE / 4; x++) {
			for (int y = GROESSE / 4; y <= GROESSE - GROESSE / 4; y++) {

				spielfeldA[x][y] = zufall.nextBoolean();
			}
		}
		normal();
		runde = 0;
	}

	public int anzahlNachbarn(int x, int y, boolean[][] spielfeld) {
		int anzahl = 0;
		if (spielfeld[x - 1][y]) {
			anzahl++;
		}
		if (spielfeld[x + 1][y]) {
			anzahl++;
		}
		if (spielfeld[x - 1][y - 1]) {
			anzahl++;
		}
		if (spielfeld[x][y - 1]) {
			anzahl++;
		}
		if (spielfeld[x + 1][y - 1]) {
			anzahl++;
		}
		if (spielfeld[x - 1][y + 1]) {
			anzahl++;
		}
		if (spielfeld[x][y + 1]) {
			anzahl++;
		}
		if (spielfeld[x + 1][y + 1]) {
			anzahl++;
		}
		return anzahl;
	}

	public boolean totOderLebendig(int x, int y, boolean[][] spielfeld) {
		int nachbarn = anzahlNachbarn(x, y, spielfeld);
		if (spielfeld[x][y] == LEBENDIG) {
			if (nachbarn == 2 || nachbarn == 3) {
				return LEBENDIG;
			} else {
				return TOT;
			}
		} else {
			if (nachbarn == 3) {
				return LEBENDIG;
			} else {
				return TOT;
			}
		}
	}

	public void normal() {
		geschwindigkeit = 100;
		timer.setDelay(geschwindigkeit);
	}

	public void langsamer() {
		geschwindigkeit += geschwindigkeit / 33;
		timer.setDelay(geschwindigkeit);
	}

	public void schneller() {
		geschwindigkeit -= geschwindigkeit / 33;
		timer.setDelay(geschwindigkeit);
	}

	public void anhalten() {
		timer.stop();
	}

	public void weitermachen() {
		timer.start();
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int c = e.getKeyCode();
		switch (c) {
			case KeyEvent.VK_R -> spielfeldInitialisieren();
			case KeyEvent.VK_S -> schneller();
			case KeyEvent.VK_L -> langsamer();
			case KeyEvent.VK_N -> normal();
			case KeyEvent.VK_A -> anhalten();
			case KeyEvent.VK_W -> weitermachen();
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {}

	@Override
	public void keyTyped(KeyEvent e) {}

	public static void main(final String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					GameOfLife anwendung = new GameOfLife("Game of Life");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}