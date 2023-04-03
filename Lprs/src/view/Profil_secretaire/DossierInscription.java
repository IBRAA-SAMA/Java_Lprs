package view.Profil_secretaire;

import java.awt.Container;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import java.awt.Font;
import java.util.List;

import javax.swing.JTextPane;

import manager.UserManager;
import model.Etudiant;
import model.Session;
import model.User;

import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DossierInscription {

    private static JFrame frame;
    private static Session session;
    private User utilisateurConnecte;


    /**
     * Create the application.
     */
    public DossierInscription(User user) {
        this.utilisateurConnecte = user;
        initialize();
        session = Session.getSessionExistante(utilisateurConnecte); // appel de la méthode getSessionExistante()

        JComboBox<Etudiant> comboBox = new JComboBox<>();
        List<Etudiant> etudiants = new UserManager().getNomsPrenomsFichesEtudiant();
        for (Etudiant etudiant : etudiants) {
            comboBox.addItem(etudiant);
        }


        
        comboBox.setBounds(103, 91, 227, 21);
        frame.getContentPane().add(comboBox);

        JButton btnNewButton = new JButton("Valider");
        
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	
        		System.out.println("La variable de session vaut : " + Session.sessionId);


                // Récupérer les informations de la fiche étudiante sélectionnée
                Etudiant ficheEtudiantSelectionnee = (Etudiant) comboBox.getSelectedItem();
                int id_fiche_etudiant_selectionnee = ficheEtudiantSelectionnee.getIdEtudiant();
                String nom_prenom = ficheEtudiantSelectionnee.getNom() + " " + ficheEtudiantSelectionnee.getPrenom();

                // Créer la nouvelle liste déroulante avec les options "SLAM" et "SISR"
                String[] optionsFiliere = {"SLAM", "SISR"};
                JComboBox<String> filiereComboBox = new JComboBox<String>(optionsFiliere);

                // Créer le champ de texte pour la motivation
                JTextField textField = new JTextField(10);

                // Afficher la fenêtre de dialogue pour saisir la motivation et la filière
                Object[] message = {
                "Motivation : ", textField,
                "Filière : ", filiereComboBox
                };
                int option = JOptionPane.showConfirmDialog(null, message, "Dossier d'inscription", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

                if (option == JOptionPane.OK_OPTION) {
                // Récupérer les valeurs saisies
                    
                String filiere = (String) filiereComboBox.getSelectedItem();
                String motivation = textField.getText();
                
                int idSession = session.getSessionId();

                
                // Ajouter le dossier d'inscription dans la base de données

				boolean succes = new UserManager().ajouterDossierInscription(id_fiche_etudiant_selectionnee, nom_prenom, filiere, motivation, idSession);

				if (succes) {
				    // Afficher un message de succès
				    JOptionPane.showMessageDialog(null, "Le dossier d'inscription a été ajouté avec succès.");
				} else {
				    // Afficher un message d'erreur
				    JOptionPane.showMessageDialog(null, "Une erreur est survenue lors de l'ajout du dossier d'inscription.");
				}

				}
			}
		});
		btnNewButton.setBounds(247, 146, 85, 21);
		frame.getContentPane().add(btnNewButton);

		JButton btnNewButton_1 = new JButton("Annuler");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		btnNewButton_1.setBounds(103, 146, 85, 21);
		frame.getContentPane().add(btnNewButton_1);

	}

	public static void run() {
		frame.setVisible(true);

	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		System.out.println("La variable de session vaut : " + Session.sessionId);

		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("Dossier d'inscription");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setBounds(145, 23, 187, 25);
		frame.getContentPane().add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Eleve :");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(30, 87, 51, 25);
		frame.getContentPane().add(lblNewLabel_1);


	}
}
