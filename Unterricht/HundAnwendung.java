import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;

import javax.swing.Timer;

import hilfe.*;

public class HundAnwendung extends HJFrame {
	// globale Variablen
	private static final int WIDTH = 800;
	private static final int HEIGHT = 500;
	private static final Color BACKGROUND = Color.WHITE;
	private static final Color FOREGROUND = Color.BLACK;
	private Hund rose;
	private Hund hartmut;
	private Hund dasMoechteIchNicht;
	private Hund witzbold;
	private boolean stehendOderSpringend = false;

	public HundAnwendung(final String title) {
		super(WIDTH, HEIGHT, BACKGROUND, FOREGROUND, title);
		// eigene Initialisierung
		hartmut = new Hund();
		dasMoechteIchNicht = new Hund(100, 100, Color.GREEN);
		witzbold = new Hund(300, 250, false);
		rose = new Hund(400, 400, Color.PINK);
		Timer timer = new Timer(200, this);
		timer.start();
	}

	@Override
	public void myPaint(Graphics g) {
		// wird aufgerufen, wenn das Fenster neu gezeichnet wird
		rose.springen(g);
		if (stehendOderSpringend == true) {
			hartmut.stehen(g);
		} else {
			hartmut.springen(g);
		}
		stehendOderSpringend = !stehendOderSpringend;
		dasMoechteIchNicht.springen(g);
		witzbold.stehen(g);
		g.drawString("Es gibt " + Hund.getAnzahlHunde() + " Hunde", 20, 450);

		// Aufgabenteil b
		if (rose.wedeln == true) {
			rose.wedeln = false;
		} else {
			rose.wedeln = true;
		}
		// Alternative Lösung für Aufgabenteil b
		witzbold.wedeln = !witzbold.wedeln;

		// Aufgabenteil c
		hartmut.x += 10;   // Kurzform von hartmut.x = hartmut.x + 10;
		if (hartmut.x > WIDTH) {
			hartmut.x = -100;
		}

	}

	public static void main(final String[] args) {
		new HundAnwendung("HundAnwendung");
		System.out.println("Hallo");
	}
}