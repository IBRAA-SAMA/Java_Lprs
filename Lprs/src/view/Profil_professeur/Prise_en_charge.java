package view.Profil_professeur;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.EventQueue;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import database.Database;
import manager.UserManager;
import model.Dossier_ins;
import model.Etudiant;
import model.Session;
import model.User;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Prise_en_charge {
	
	private static JFrame frame;
	private User utilisateurConnecte;
	private JComboBox<Dossier_ins> listeDeroulante;
	private JButton prendreEnCharge;
	private Session session;
	private JButton btnNewButton;


	/**
	 * Create the application.
	 */
	public Prise_en_charge(User user) {
		initialize();
		this.utilisateurConnecte = user;
	    initialize();
	    session = Session.getSessionExistante(utilisateurConnecte); // appel de la méthode getSessionExistante()

	}

	public void run() {
		frame.setVisible(true);

	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("Gestion des dossiers");
		frame.setSize(400, 200);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Récupération des dossiers d'inscription des étudiants dans la base de données
		List<Dossier_ins> dossiersEtudiants = new UserManager().getNomsPrenomsDossierEtudiant();

		listeDeroulante = new JComboBox<Dossier_ins>();
		listeDeroulante.setBounds(32, 5, 200, 20);
		listeDeroulante.setPreferredSize(new Dimension(200, 20));
		for (Dossier_ins etudiant : dossiersEtudiants) {
			listeDeroulante.addItem(etudiant);
		}


		JPanel panelPrincipal = new JPanel();
		panelPrincipal.setLayout(null);

		panelPrincipal.add(listeDeroulante);

		prendreEnCharge = new JButton("Prendre en charge");
		prendreEnCharge.setBounds(237, 5, 117, 21);
		prendreEnCharge.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Récupération du dossier d'inscription de l'étudiant sélectionné
				Dossier_ins dossierEtudiantSelectionne = (Dossier_ins) listeDeroulante.getSelectedItem();
				int idDossierEtudiantSelectionne = dossierEtudiantSelectionne.getId();
				String nomPrenom = dossierEtudiantSelectionne.getNom_prenom();

				// Appel de la méthode priseEnCharge avec l'ID du dossier d'inscription sélectionné, l'ID de la session sélectionnée et l'utilisateur connecté
				boolean succes = new UserManager().priseEnCharge(idDossierEtudiantSelectionne, session.getSessionId());
	                
				if (succes) {
				    // Afficher un message de succès
				    JOptionPane.showMessageDialog(null, "Le dossier d'inscription a été pris en charge avec succès.");
				} else {
				    // Afficher un message d'erreur
				    JOptionPane.showMessageDialog(null, "Une erreur est survenue lors de la prise en charge du dossier d'inscription.");
				}
			}	
		});

		panelPrincipal.add(prendreEnCharge);

		frame.getContentPane().add(panelPrincipal);
		
		btnNewButton = new JButton("Annuler");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		btnNewButton.setBounds(247, 38, 85, 21);
		panelPrincipal.add(btnNewButton);
		frame.setVisible(true);
	}
}