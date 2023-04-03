package view.Profil_stock;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;

import database.Database;
import manager.UserManager;
import model.Session;
import model.User;
import model.Materiel;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JFormattedTextField;

public class fiche_fourniture extends JFrame {

	private static JFrame frame;
	private JTextField textFieldNom;
	private JTextField textFieldEntreprise;
	private JTextField textFieldPrix;
	private JTextField textFieldNombre;
	private static Session session;
	private User utilisateurConnecte;
	private JTextField textFieldAdresse;
	private JTextField textFieldContact;




	/**
	 * Create the application.
	 */
	public fiche_fourniture(User user) {
		initialize();
		this.utilisateurConnecte = user;
		initialize();
		session = Session.getSessionExistante(utilisateurConnecte); // appel de la méthode getSessionExistante()
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
		frame.setBounds(100, 100, 563, 426);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("Enregistrement Fiche Matériel");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel.setBounds(166, 29, 214, 22);
		frame.getContentPane().add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel(" Nom du materieaux :");
		lblNewLabel_1.setBounds(10, 93, 111, 13);
		frame.getContentPane().add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Entreprise :");
		lblNewLabel_2.setBounds(28, 153, 93, 13);
		frame.getContentPane().add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("Prix :");
		lblNewLabel_3.setBounds(28, 246, 77, 13);
		frame.getContentPane().add(lblNewLabel_3);

		JLabel lblNewLabel_4 = new JLabel("Nombre :");
		lblNewLabel_4.setBounds(28, 124, 60, 13);
		frame.getContentPane().add(lblNewLabel_4);

		textFieldNom = new JTextField();
		textFieldNom.setBounds(127, 90, 196, 19);
		frame.getContentPane().add(textFieldNom);
		textFieldNom.setColumns(10);

		textFieldEntreprise = new JTextField();
		textFieldEntreprise.setBounds(127, 150, 96, 19);
		frame.getContentPane().add(textFieldEntreprise);
		textFieldEntreprise.setColumns(10);

		textFieldPrix = new JTextField();
		textFieldPrix.setBounds(127, 243, 96, 19);
		frame.getContentPane().add(textFieldPrix);
		textFieldPrix.setColumns(10);

		textFieldNombre = new JTextField();
		textFieldNombre.setBounds(127, 121, 96, 19);
		frame.getContentPane().add(textFieldNombre);
		textFieldNombre.setColumns(10);

		JButton btnNewButton = new JButton("Retour");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose(); // ferme la fenêtre
			}
		});
		btnNewButton.setBounds(127, 285, 85, 21);
		frame.getContentPane().add(btnNewButton);

		JButton btnNewButton_1 = new JButton("Valider");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Récupération des données saisies dans les champs de texte
				String nom = textFieldNom.getText();
				int nombre = 0;
				try {
					nombre = Integer.parseInt(textFieldNombre.getText());
				} catch (NumberFormatException e1) {
					JOptionPane.showMessageDialog(null, "Le champ nombre doit être un entier", "Erreur", JOptionPane.ERROR_MESSAGE);
					return;
				}
				String entreprise = textFieldEntreprise.getText();
				String adresse = textFieldAdresse.getText();
				int contact = 0;
				try {
					contact = Integer.parseInt(textFieldContact.getText());
				} catch (NumberFormatException e1) {
					JOptionPane.showMessageDialog(null, "Le champ contact doit être un entier", "Erreur", JOptionPane.ERROR_MESSAGE);
					return;
				}
				int prix = 0;
				try {
					prix = Integer.parseInt(textFieldPrix.getText());
				} catch (NumberFormatException e1) {
					JOptionPane.showMessageDialog(null, "Le champ prix doit être un entier", "Erreur", JOptionPane.ERROR_MESSAGE);
					return;
				}

				// Vérification que les champs obligatoires ont bien été remplis
				if (textFieldContact.getText().isEmpty() || adresse.isEmpty() || nom.isEmpty() || entreprise.isEmpty() || textFieldPrix.getText().isEmpty() || textFieldNombre.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Tous les champs sont obligatoires", "Erreur", JOptionPane.ERROR_MESSAGE);
					return;
				}

				// Vérification que le champ contact ne contient pas de caractères non numériques
				if (!textFieldContact.getText().matches("[0-9]+")) {
					JOptionPane.showMessageDialog(null, "Le champ contact ne doit contenir que des chiffres", "Erreur", JOptionPane.ERROR_MESSAGE);
					return;
				}

				User user = new User();
				if (Session.sessionId != 0) {
					session = Session.getSessionExistante(utilisateurConnecte);
				} else {
					System.out.println("pas de session détécté");
				}
				if (session != null) {
					int idSession = session.getSessionId();

					// Appel de la méthode ajouter_fiche_fourniture de la classe UserManager
					try {
						UserManager.ajouter_fiche_fourniture(nom, nombre, entreprise, adresse, contact, prix, idSession);
						JOptionPane.showMessageDialog(null, "La fiche de fourniture a été ajoutée avec succès", "Succès", JOptionPane.INFORMATION_MESSAGE);
						frame.dispose();
					} catch (SQLException ex) {
						JOptionPane.showMessageDialog(null, "Une erreur est survenue lors de l'ajout de la fiche de fourniture : " + ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
					}
				}

			}
		});
		btnNewButton_1.setBounds(238, 285, 85, 21);
		frame.getContentPane().add(btnNewButton_1);

		JLabel lblNewLabel_5 = new JLabel("Adresse :");
		lblNewLabel_5.setBounds(31, 188, 90, 13);
		frame.getContentPane().add(lblNewLabel_5);

		JLabel lblNewLabel_6 = new JLabel("Contact :");
		lblNewLabel_6.setBounds(28, 211, 93, 13);
		frame.getContentPane().add(lblNewLabel_6);

		textFieldAdresse = new JTextField();
		textFieldAdresse.setBounds(127, 185, 96, 19);
		frame.getContentPane().add(textFieldAdresse);
		textFieldAdresse.setColumns(10);

		textFieldContact = new JTextField();
		textFieldContact.setBounds(127, 214, 96, 19);
		frame.getContentPane().add(textFieldContact);
		textFieldContact.setColumns(10);
	}

}
