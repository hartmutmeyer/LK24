import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import hilfe.*;

public class Zeichenbrett extends HJFrame implements MouseListener, MouseMotionListener, KeyListener {
	// globale Variablen
	private static final int WIDTH = 500;
	private static final int HEIGHT = 500;
	private static final Color BACKGROUND = Color.WHITE;
	private static final Color FOREGROUND = Color.BLACK;
	private Color pixel[][] = new Color[500][500];
	private Color farbe = Color.BLACK;
	private int breite = 5;

	public Zeichenbrett(final String title) {
		super(WIDTH, HEIGHT, BACKGROUND, FOREGROUND, title);
		// eigene Initialisierung
		zeichenflaeche.addMouseListener(this);
		zeichenflaeche.addMouseMotionListener(this);
		addKeyListener(this);
	}

	@Override
	public void myPaint(Graphics g) {
		// wird aufgerufen, wenn das Fenster neu gezeichnet wird
		for (int x = 0; x < 500; x++) {
			for (int y = 0; y < 500; y++) {
				if (pixel[x][y] != null) {
					g.setColor(pixel[x][y]);
					g.drawLine(x, y, x, y);
				}
			}
		}
	}

	// Anfang Ereignisprozeduren
	@Override
	public void mouseClicked(MouseEvent e) {
		for (int x = e.getX(); x < e.getX() + breite; x++) {
			for (int y = e.getY(); y < e.getY() + breite; y++) {
				pixel[x][y] = farbe;
			}
		}
		repaint();
	}

	@Override
	public void mousePressed(MouseEvent e) {}

	@Override
	public void mouseReleased(MouseEvent e) {}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}

	@Override
	public void mouseMoved(MouseEvent e) {}

	@Override
	public void mouseDragged(MouseEvent e) {
		for (int x = e.getX(); x < e.getX() + breite; x++) {
			for (int y = e.getY(); y < e.getY() + breite; y++) {
				pixel[x][y] = farbe;
			}
		}
		repaint();
	}

	@Override
	public void keyTyped(KeyEvent e) {
		switch (e.getKeyChar()) {
			case 'l', 'L' -> {
				for (int x = 0; x < 500; x++) {
					for (int y = 0; y < 500; y++) {
						pixel[x][y] = null;
					}
				}
				repaint();
			}
			case 's', 'S' -> farbe = Color.BLACK;
			case 'r', 'R' -> farbe = Color.RED;
			case 'b', 'B' -> farbe = Color.BLUE;
			case 'g', 'G' -> farbe = Color.GREEN;
			case '1', '2', '3', '4', '5', '6', '7', '8', '9' -> breite = e.getKeyChar() - '0';
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {}

	@Override
	public void keyReleased(KeyEvent e) {}

	public static void main(final String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					Zeichenbrett anwendung = new Zeichenbrett("Zeichenbrett");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}