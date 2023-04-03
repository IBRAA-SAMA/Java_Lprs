package view.Profil_stock;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import manager.UserManager;
import model.Session;
import model.User;

public class Afficher_demande {
	private static JFrame frame;
	private JTable table;
	private DefaultTableModel model;
	private static Session session;

	private User utilisateurConnecte;

	/**
	 * Create the application.
	 */
	public Afficher_demande(User user) {
		this.utilisateurConnecte = user;
		initialize();
		session = Session.getSessionExistante(utilisateurConnecte); // appel de la méthode getSessionExistante(
		initialize();
	}

	public static void run() {

		frame.setVisible(true);

	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 800, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout());

		// Création du modèle de la table
		model = new DefaultTableModel(new String[] { "ID demande", "ID professeur", "Demande", "Valider", "Date" }, 0) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		// Récupération des données de la table demande_fourniture
		UserManager userManager = new UserManager();
		ResultSet rs = null;
		try {
			rs = userManager.getDemandeFourniture();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}

		// Ajout des données dans le modèle de la table
		try {
			while (rs.next()) {
				int id = rs.getInt("id");
				int idProfesseur = rs.getInt("id_professeur");
				String demande = rs.getString("demande");
				boolean valider = rs.getBoolean("valider");
				String date = rs.getString("date");

				String validerStr = "Non";
				if (valider) {
					validerStr = "Oui";
				}

				model.addRow(new Object[] { id, idProfesseur, demande, validerStr, date });
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		// Création de la table avec le modèle
		table = new JTable(model);

		// Ajout de la table dans un JScrollPane pour ajouter les barres de défilement si nécessaire
		JScrollPane scrollPane = new JScrollPane(table);
		frame.getContentPane().add(scrollPane, BorderLayout.CENTER);

		JButton btnValider = new JButton("Valider");
		btnValider.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedRow = table.getSelectedRow();
				if (selectedRow != -1 && selectedRow < model.getRowCount()) {
					int idDemande = (int) model.getValueAt(selectedRow, 0);
					UserManager userManager = new UserManager();
					userManager.validerDemandeFourniture(idDemande);
					model.setValueAt("Oui", selectedRow, 3);
				}
			}
		});


		JButton btnSupprimer = new JButton("Supprimer");
		btnSupprimer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Code à exécuter lorsque le bouton est cliqué
				// Vous pouvez appeler ici la méthode qui supprime la demande de fourniture
				int selectedRow = table.getSelectedRow();
				if (selectedRow != -1) {
					int idDemande = (int) model.getValueAt(selectedRow, 0);
					UserManager userManager = new UserManager();
					userManager.supprimerDemandeFourniture(idDemande);
					model.removeRow(selectedRow);
				}
			}
		});


		JButton btnAnnuler = new JButton("Annuler");
		btnAnnuler.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose(); // ferme la fenêtre
			}
		});
		frame.getContentPane().add(btnAnnuler, BorderLayout.SOUTH);



		// Ajout des boutons dans un JPanel
		JPanel panel = new JPanel();
		panel.add(btnValider);
		panel.add(btnSupprimer);
		panel.add(btnAnnuler);

		// Ajout du JPanel en bas du JScrollPane pour que les boutons restent en bas de la fenêtre
		frame.getContentPane().add(panel, BorderLayout.SOUTH);
	}

	/**

	Appel de cette méthode pour lancer la fenêtre d'affichage des demandes de fourniture
	 */
	public static void afficher(User user) {
		Afficher_demande window = new Afficher_demande(user);
		run();
	}
}