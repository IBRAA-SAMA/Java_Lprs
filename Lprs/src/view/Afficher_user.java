package view;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import manager.UserManager;
import model.User;

public class Afficher_user {

	private JFrame frame;
	private JTable table;
	private DefaultTableModel model;
	private User utilisateurConnecte;

	public void run() {
		frame.setVisible(true);
	}

	public Afficher_user(User user) {
		this.utilisateurConnecte = user;
		initialize();
	}

	/**
	 * Initialise le contenu de la fenêtre.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 700, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		model = new DefaultTableModel(new String[] { "ID", "Nom", "Prénom", "Email", "Mot de passe", "Date de vérification", "Rôle", "Reset MDP" }, 0) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		// Récupération des données de la table utilisateur
		UserManager userManager = new UserManager();
    	ResultSet rs = null;
    	try {
    	    rs = userManager.getUtilisateur();
    	} catch (SQLException e1) {
    	    e1.printStackTrace();
    	}

    	try {
    	    while (rs.next()) {
    	        int id = rs.getInt("id");
    	        String nom = rs.getString("nom");
    	        String prenom = rs.getString("prenom");
    	        String email = rs.getString("email");
    	        String mdp = rs.getString("mdp");
    	        String date = rs.getString("date_verif");
    	        int role = rs.getInt("role");
    	        int reset_mdp = rs.getInt("reset_mdp");

    	        

    	        model.addRow(new Object[] { id, nom, prenom, email, mdp, date, role, reset_mdp });
    	    }
    	} catch (SQLException e) {
    	    e.printStackTrace();
    	}

		// Créer une JTable pour afficher les données de la table utilisateur
		table = new JTable();
		table.setModel(model);

		// Créer un JScrollPane pour permettre le défilement de la JTable
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportView(table);
		scrollPane.setBounds(0, 0, 684, 428);
		frame.getContentPane().add(scrollPane);

		// Ajouter un bouton "Annuler"
		JButton btnAnnuler = new JButton("Annuler");
		btnAnnuler.setBounds(584, 439, 100, 23);
		btnAnnuler.addActionListener(e -> {
			frame.dispose(); // fermer la fenêtre
		});
		frame.getContentPane().add(btnAnnuler);
	}
}
