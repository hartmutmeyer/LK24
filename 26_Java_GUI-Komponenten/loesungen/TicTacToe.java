import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class TicTacToe extends JFrame implements ActionListener {
	// globale Variablen
	private static final int WIDTH = 300;
	private static final int HEIGHT = 300;
	private JButton[][] btnFeld = new JButton[3][3];
	private JButton btnReset = new JButton("Zurücksetzen");
	private boolean spieler1 = true;
	private boolean gewonnen;

	public TicTacToe(final String title) {
		super(title);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 15));
		setContentPane(contentPane);
		JPanel pnlSpielfeld = new JPanel();
		pnlSpielfeld.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		pnlSpielfeld.setLayout(new GridLayout(3, 3, 4, 4));
		for (int x = 0; x < 3; x++) {
			for (int y = 0; y < 3; y++) {
				btnFeld[x][y] = new JButton();
				btnFeld[x][y].setFont(new Font("Arial", Font.BOLD, 72));
				pnlSpielfeld.add(btnFeld[x][y]);
				btnFeld[x][y].addActionListener(this);
			}
		}
		contentPane.add(pnlSpielfeld, BorderLayout.CENTER);
		contentPane.add(btnReset, BorderLayout.PAGE_END);
		btnReset.addActionListener(this);
		pack();
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(btnReset)) {
			reset();
		} else {
			JButton button = (JButton) e.getSource();
			if (spieler1) {
				button.setText("X");
			} else {
				button.setText("O");
			}
			button.setEnabled(false);
			spieler1 = !spieler1;
			gewinner();
		}
	}

	private void reset() {
		for (int x = 0; x < 3; x++) {
			for (int y = 0; y < 3; y++) {
				btnFeld[x][y].setText("");
				btnFeld[x][y].setEnabled(true);
			}
		}
		spieler1 = true;
		gewonnen = false;
	}

// Die Aufgabenstelllung hatte nicht gefordert, dass der Gewinner ermittelt wird.
// Lösung kommt von Malte Voigt (LK15)
	public void gewinner() {
		// Hat Spieler 1 gewonnen?
		if (btnFeld[0][0].getText().equals("X") && btnFeld[0][1].getText().equals("X") && btnFeld[0][2].getText().equals("X")) {
			JOptionPane.showMessageDialog(this, "Spieler 1 hat gewonnen");
			gewonnen = true;
		}
		if (btnFeld[1][0].getText().equals("X") && btnFeld[1][1].getText().equals("X") && btnFeld[1][2].getText().equals("X")) {
			JOptionPane.showMessageDialog(this, "Spieler 1 hat gewonnen");
			gewonnen = true;
		}
		if (btnFeld[2][0].getText().equals("X") && btnFeld[2][1].getText().equals("X") && btnFeld[2][2].getText().equals("X")) {
			JOptionPane.showMessageDialog(this, "Spieler 1 hat gewonnen");
			gewonnen = true;
		}
		if (btnFeld[0][0].getText().equals("X") && btnFeld[1][0].getText().equals("X") && btnFeld[2][0].getText().equals("X")) {
			JOptionPane.showMessageDialog(this, "Spieler 1 hat gewonnen");
			gewonnen = true;
		}
		if (btnFeld[0][1].getText().equals("X") && btnFeld[1][1].getText().equals("X") && btnFeld[2][1].getText().equals("X")) {
			JOptionPane.showMessageDialog(this, "Spieler 1 hat gewonnen");
			gewonnen = true;
		}
		if (btnFeld[0][2].getText().equals("X") && btnFeld[1][2].getText().equals("X") && btnFeld[2][2].getText().equals("X")) {
			JOptionPane.showMessageDialog(this, "Spieler 1 hat gewonnen");
			gewonnen = true;
		}
		if (btnFeld[0][0].getText().equals("X") && btnFeld[1][1].getText().equals("X") && btnFeld[2][2].getText().equals("X")) {
			JOptionPane.showMessageDialog(this, "Spieler 1 hat gewonnen");
			gewonnen = true;
		}
		if (btnFeld[0][2].getText().equals("X") && btnFeld[1][1].getText().equals("X") && btnFeld[2][0].getText().equals("X")) {
			JOptionPane.showMessageDialog(this, "Spieler 1 hat gewonnen");
			gewonnen = true;
		}
		// Hat Spieler 2 gewonnen?
		if (btnFeld[0][0].getText().equals("O") && btnFeld[0][1].getText().equals("O") && btnFeld[0][2].getText().equals("O")) {
			JOptionPane.showMessageDialog(this, "Spieler 2 hat gewonnen");
			gewonnen = true;
		}
		if (btnFeld[1][0].getText().equals("O") && btnFeld[1][1].getText().equals("O") && btnFeld[1][2].getText().equals("O")) {
			JOptionPane.showMessageDialog(this, "Spieler 2 hat gewonnen");
			gewonnen = true;
		}
		if (btnFeld[2][0].getText().equals("O") && btnFeld[2][1].getText().equals("O") && btnFeld[2][2].getText().equals("O")) {
			JOptionPane.showMessageDialog(this, "Spieler 2 hat gewonnen");
			gewonnen = true;
		}
		if (btnFeld[0][0].getText().equals("O") && btnFeld[1][0].getText().equals("O") && btnFeld[2][0].getText().equals("O")) {
			JOptionPane.showMessageDialog(this, "Spieler 2 hat gewonnen");
			gewonnen = true;
		}
		if (btnFeld[0][1].getText().equals("O") && btnFeld[1][1].getText().equals("O") && btnFeld[2][1].getText().equals("O")) {
			JOptionPane.showMessageDialog(this, "Spieler 2 hat gewonnen");
			gewonnen = true;
		}
		if (btnFeld[0][2].getText().equals("O") && btnFeld[1][2].getText().equals("O") && btnFeld[2][2].getText().equals("O")) {
			JOptionPane.showMessageDialog(this, "Spieler 2 hat gewonnen");
			gewonnen = true;
		}
		if (btnFeld[0][0].getText().equals("O") && btnFeld[1][1].getText().equals("O") && btnFeld[2][2].getText().equals("O")) {
			JOptionPane.showMessageDialog(this, "Spieler 2 hat gewonnen");
			gewonnen = true;
		}
		if (btnFeld[0][2].getText().equals("O") && btnFeld[1][1].getText().equals("O") && btnFeld[2][0].getText().equals("O")) {
			JOptionPane.showMessageDialog(this, "Spieler 2 hat gewonnen");
			gewonnen = true;
		}

		if (gewonnen) {
			for (int x = 0; x < 3; x++) {
				for (int y = 0; y < 3; y++) {
					btnFeld[x][y].setEnabled(false);
				}
			}
		}
		if (!btnFeld[0][0].getText().isEmpty() && !btnFeld[0][1].getText().isEmpty() && !btnFeld[0][2].getText().isEmpty()
				&& !btnFeld[1][0].getText().isEmpty() && !btnFeld[1][1].getText().isEmpty() && !btnFeld[1][2].getText().isEmpty()
				&& !btnFeld[2][0].getText().isEmpty() && !btnFeld[2][1].getText().isEmpty() && !btnFeld[2][2].getText().isEmpty()
				&& gewonnen != true) {
			for (int x = 0; x < 3; x++) {
				for (int y = 0; y < 3; y++) {
					btnFeld[x][y].setEnabled(false);
				}
			}
			JOptionPane.showMessageDialog(this, "Unentschieden");
		}
	}

	public static void main(final String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					new TicTacToe("Tic Tac Toe");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}