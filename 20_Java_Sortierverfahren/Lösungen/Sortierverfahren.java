import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.util.Random;
import hilfe.*;

public class Sortierverfahren extends HJFrame {

    private static final long serialVersionUID = 1L;
    private static final int WIDTH = 600;
    private static final int HEIGHT = 300;
    private static final Color BACKGROUND = Color.WHITE;
    private static final Color FOREGROUND = Color.BLACK;

    private int laenge;
    private int[] unsortiert;
    private int[] zahlen;
    private int max;

    public Sortierverfahren(final String title) {
        super(WIDTH, HEIGHT, BACKGROUND, FOREGROUND, title);
        Random zufall = new Random();

        // a) Zufällige Länge zwischen 1 und 10
        laenge = zufall.nextInt(10) + 1;

        // b) Array erzeugen und mit Zufallszahlen zwischen 10 und 99 füllen
        zahlen = new int[laenge];
        unsortiert = new int[laenge];
        for (int i = 0; i < zahlen.length; i++) {
            zahlen[i] = zufall.nextInt(90) + 10;
            unsortiert[i] = zahlen[i]; // Kopie für dauerhafte "Unsortiert"-Anzeige
        }

        // d) Größte Zahl ermitteln
        max = findeMaximum(zahlen);

        // e) Array sortieren
        bubbleSort(zahlen); // Alternativ: selectionSort(zahlen); oder insertionSort(zahlen);
    }

    // c) Array in einer Zeile ausgeben
    public void gibAus(Graphics g, int[] feld, int y) {
        for (int i = 0; i < feld.length; i++) {
            g.drawString("" + feld[i], 40 + i * 40, y);
        }
    }

    // d) Maximum bestimmen
    public int findeMaximum(int[] feld) {
        int maximum = feld[0];
        for (int i = 1; i < feld.length; i++) {
            if (feld[i] > maximum) {
                maximum = feld[i];
            }
        }
        return maximum;
    }

    // e) Bubble Sort
    public void bubbleSort(int[] feld) {
        boolean getauscht = true;
        while (getauscht) {
            getauscht = false;
            for (int i = 0; i < feld.length - 1; i++) {
                if (feld[i] > feld[i + 1]) {
                    int temp = feld[i];
                    feld[i] = feld[i + 1];
                    feld[i + 1] = temp;
                    getauscht = true;
                }
            }
        }
    }

    // f) Selection Sort
    public void selectionSort(int[] feld) {
        for (int i = 0; i < feld.length - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < feld.length; j++) {
                if (feld[j] < feld[minIndex]) {
                    minIndex = j;
                }
            }
            int temp = feld[i];
            feld[i] = feld[minIndex];
            feld[minIndex] = temp;
        }
    }

    // g) Insertion Sort
    public void insertionSort(int[] feld) {
        for (int i = 1; i < feld.length; i++) {
            int einzufuegen = feld[i];
            int j = i;
            while (j > 0 && feld[j - 1] > einzufuegen) {
                feld[j] = feld[j - 1];
                j--;
            }
            feld[j] = einzufuegen;
        }
    }

    @Override
    public void myPaint(Graphics g) {
        if (zahlen == null) {
            return;
        }
        g.drawString("Länge des Arrays: " + laenge, 40, 50);
        g.drawString("Größte Zahl: " + max, 40, 75);

        g.drawString("Unsortiert:", 40, 120);
        gibAus(g, unsortiert, 145);

        g.drawString("Sortiert:", 40, 190);
        gibAus(g, zahlen, 215);
    }

    public static void main(final String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                new Sortierverfahren("Sortierverfahren");
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
