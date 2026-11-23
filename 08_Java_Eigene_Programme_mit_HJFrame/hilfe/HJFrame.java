package hilfe;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

/**
 * Von <code>HJFrame</code> abgeleitete Klassen erzeugen ein Fenster unveränderlicher Größe.
 * Das Fenster wird von einer Zeichenfläche gefüllt, in die mithilfe der Methode
 * <code>myPaint()</code> gezeichnet werden kann.
 *
 * @author hartmut meyer
 */
public abstract class HJFrame extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;

	/**
	 * Die Zeichenfläche, auf der mit <code>myPaint()</code> gezeichnet werden kann.
	 */
	protected JPanel zeichenflaeche;

	/**
	 * Einziger Konstruktor der Klasse <code>HJFrame</code>.
	 *
	 * @param width Die Breite (in Pixeln) der Zeichenfläche
	 * @param height Die Höhe (in Pixeln) der Zeichenfläche
	 * @param background Die Hintergrundfarbe
	 * @param foreground Die Vordergrundfarbe
	 * @param title Der Titel des Programmfensters
	 */
	public HJFrame(int width, int height, Color background, Color foreground, final String title) {
		super(title);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		zeichenflaeche = new JPanel() {
			private static final long serialVersionUID = 1L;

			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				if (g instanceof Graphics2D g2d) {
					g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
					g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
				}
				myPaint(g);
			}
		};

		zeichenflaeche.setPreferredSize(new Dimension(width, height));
		zeichenflaeche.setMaximumSize(new Dimension(width, height));
		zeichenflaeche.setMinimumSize(new Dimension(width, height));
		zeichenflaeche.setOpaque(true);
		zeichenflaeche.setDoubleBuffered(true);
		zeichenflaeche.setBackground(background);
		zeichenflaeche.setForeground(foreground);
		zeichenflaeche.setFont(new Font("Arial", Font.PLAIN, 12));
		contentPane.add(zeichenflaeche);

		setResizable(false);
		pack();
		setLocationRelativeTo(null);
		EventQueue.invokeLater(() -> setVisible(true));
	}

	/**
	 * Zeichnet die Zeichenfläche neu und synchronisiert die Grafikausgabe (z. B. für Animationen).
	 */
	public void neuZeichnen() {
		repaint();
		Toolkit.getDefaultToolkit().sync();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		neuZeichnen();
	}

	/**
	 * <p>Die Methode <code>myPaint()</code> muss in Klassen, die von <code>HJFrame</code> abgeleitet werden,
	 * implementiert werden. Mit dem als Parameter übergebenen <code>Graphics</code>-Objekt kann dann auf der
	 * Zeichenfläche gezeichnet werden.</p>
	 *
	 * <p><code>myPaint()</code> wird immer dann aufgerufen, wenn das Fenster neu gezeichnet werden muss.
	 * Dies kann vom Betriebssystem verursacht werden (etwa weil ein zuvor minimiertes Fenster wieder
	 * aus der Taskleiste geholt wird) oder weil es durch einen Timer regelmäßig dazu aufgefordert wird.</p>
	 *
	 * @param g Das <code>Graphics</code>-Objekt der Zeichenfläche.
	 */
	public abstract void myPaint(Graphics g);
}
