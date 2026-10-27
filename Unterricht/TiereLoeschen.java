import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;

public class TiereLoeschen extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField tfTierID;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TiereLoeschen frame = new TiereLoeschen();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TiereLoeschen() {
		createGUI();

	}

	private void createGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 411, 95);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTierID = new JLabel("Tiernummer:");
		lblTierID.setBounds(10, 11, 104, 14);
		contentPane.add(lblTierID);
		
		tfTierID = new JTextField();
		tfTierID.setBounds(124, 8, 86, 20);
		contentPane.add(tfTierID);
		tfTierID.setColumns(10);
		
		JButton btnTierLöschen = new JButton("Tier Löschen");
		btnTierLöschen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tierLöschen();
			}
		});
		btnTierLöschen.setBounds(220, 7, 146, 23);
		contentPane.add(btnTierLöschen);
	}
	
	private void tierLöschen() {
		String id = tfTierID.getText();
		if (JOptionPane.showConfirmDialog(this, "Sind Sie sicher?") == JOptionPane.YES_OPTION) {
			try {
				Connection conn = DriverManager.getConnection(
						"jdbc:mysql://localhost/haustier"
						+ "?serverTimezone=UTC&useSSL=false&allowPublicKeyRetrieval=true",
						"root","root");
				Statement stmt = conn.createStatement();
				
				// Im ersten Schritt die Einträge in der Beziehungstabelle löschen
				String sql = "DELETE FROM beziehung WHERE beziehung_tier_id = " + id;
				System.out.println("tierLöschen(): " + sql);
				int ergebnis = stmt.executeUpdate(sql);
				JOptionPane.showMessageDialog(this, "Für das Tier mit der ID " + id + " wurden " + ergebnis + " Einträge in der Beziehungstabelle entfernt.");
				
				// Anschließend kann auch das Tier gelsöcht werden
				sql = "DELETE FROM tier WHERE tier_id = " + id;
				System.out.println("tierLöschen(): " + sql);
				ergebnis = stmt.executeUpdate(sql);
				if (ergebnis == 1) {
					JOptionPane.showMessageDialog(this, "Das Tier mit der ID " + id + " wurde erfolgreich gelöscht.");
				} else {
					JOptionPane.showMessageDialog(this, "Das Tier mit der ID " + id + " konnte NICHT gelöscht werden.");
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
