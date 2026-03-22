import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import hilfe.*;

public class HundVorversionAnwendung extends HJFrame {
	// globale Variablen
	private static final int WIDTH = 500;
	private static final int HEIGHT = 500;
	private static final Color BACKGROUND = Color.WHITE;
	private static final Color FOREGROUND = Color.BLACK;
	HundVorversion bello, waldi;

	public HundVorversionAnwendung(final String title) {
		super(WIDTH, HEIGHT, BACKGROUND, FOREGROUND, title);
		// eigene Initialisierung
		bello = new HundVorversion();
		waldi = new HundVorversion();
	}

	@Override
	public void myPaint(Graphics g) {
		// wird aufgerufen, wenn das Fenster neu gezeichnet wird
		bello.x = 100;
		bello.y = 100;
		bello.zeichnen(g);
		
		waldi.farbe = Color.YELLOW;
		waldi.zeichnen(g);
	}

	public static void main(final String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					HundVorversionAnwendung anwendung = new HundVorversionAnwendung("HundVorversionAnwendung");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}