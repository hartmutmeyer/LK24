package hilfe;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 * Hilfsklasse zum einfachen Abspielen von WAV-Dateien im Unterricht.
 * Unterstützt einmaliges Abspielen (Soundeffekte) sowie Endlosschleifen (Hintergrundmusik).
 */
public class HSound {

	private static final Map<URL, Clip> clipCache = new HashMap<>();

	/**
	 * Spielt eine WAV-Datei einmalig ab, die im selben Package wie das aufrufende
	 * Objekt (typischerweise <code>this</code>) liegt.
	 *
	 * @param kontext Das aufrufende Objekt (meist <code>this</code>)
	 * @param dateiname Name der WAV-Datei (z. B. "ok.wav")
	 */
	public static void play(Object kontext, String dateiname) {
		URL url = kontext.getClass().getResource(dateiname);
		playFromUrl(url, dateiname, false);
	}

	/**
	 * Spielt eine WAV-Datei einmalig ab. Der Pfad wird relativ zum <code>src</code>-Ordner
	 * angegeben (z. B. "meinPackage/ok.wav" oder "ok.wav", falls direkt in src).
	 *
	 * @param pfad Relativer Pfad zur WAV-Datei
	 */
	public static void play(String pfad) {
		URL url = Thread.currentThread().getContextClassLoader().getResource(pfad);
		playFromUrl(url, pfad, false);
	}

	/**
	 * Spielt eine WAV-Datei in einer Endlosschleife ab (z. B. für Hintergrundmusik).
	 *
	 * @param kontext Das aufrufende Objekt (meist <code>this</code>)
	 * @param dateiname Name der WAV-Datei
	 */
	public static void loop(Object kontext, String dateiname) {
		URL url = kontext.getClass().getResource(dateiname);
		playFromUrl(url, dateiname, true);
	}

	/**
	 * Stoppt die Wiedergabe einer zuvor gestarteten WAV-Datei.
	 *
	 * @param kontext Das aufrufende Objekt (meist <code>this</code>)
	 * @param dateiname Name der WAV-Datei
	 */
	public static void stop(Object kontext, String dateiname) {
		URL url = kontext.getClass().getResource(dateiname);
		if (url != null && clipCache.containsKey(url)) {
			clipCache.get(url).stop();
		}
	}

	private static void playFromUrl(URL url, String name, boolean loop) {
		if (url == null) {
			System.err.println("HSound-Fehler: Die Datei '" + name + "' wurde nicht gefunden!");
			return;
		}
		try {
			Clip clip = clipCache.get(url);
			if (clip == null) {
				AudioInputStream audioStream = AudioSystem.getAudioInputStream(url);
				clip = AudioSystem.getClip();
				clip.open(audioStream);
				clipCache.put(url, clip);
			}
			clip.stop();
			clip.setFramePosition(0);
			if (loop) {
				clip.loop(Clip.LOOP_CONTINUOUSLY);
			} else {
				clip.start();
			}
		} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
			System.err.println("HSound-Fehler beim Abspielen von '" + name + "': " + e.getMessage());
		}
	}
}
