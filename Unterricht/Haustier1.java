import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;

public class Haustier1 extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private DefaultListModel<String> lebendigeTiereUndBesitzer = new DefaultListModel<String>();
	private JList<String> list = new JList<String>(lebendigeTiereUndBesitzer);

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Haustier1 frame = new Haustier1();
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
	public Haustier1() {
		createGUI();
		datenbankAbfragen();
	}

	private void datenbankAbfragen() {
		Connection conn;
		try {
			conn = DriverManager.getConnection(
					"jdbc:mysql://localhost/haustier" + "?serverTimezone=UTC&useSSL=false&allowPublicKeyRetrieval=true",
					"root", "root");
			Statement stmt = conn.createStatement();
			String sql = "SELECT * FROM besitzer, tier, beziehung " + "WHERE besitzer_id = beziehung_besitzer_id "
					+ "AND tier_id = beziehung_tier_id " + "AND lebendig = 'ja'";
			System.out.println("datenbankAbfragern(): " + sql);
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				lebendigeTiereUndBesitzer.addElement(rs.getString("nachname") + ", " 
			               + rs.getString("vorname") + ": " + rs.getString("name")
			               + ", " + rs.getString("tierart") + ", " + rs.getString("geschlecht"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void createGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Liste aller lebendigen Tiere und ihrer Besitzer:");
		lblNewLabel.setBounds(10, 11, 414, 14);
		contentPane.add(lblNewLabel);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 36, 414, 214);
		contentPane.add(scrollPane);

		scrollPane.setViewportView(list);
	}
}
