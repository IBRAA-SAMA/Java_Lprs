package view.Profil_secretaire;


import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;

import database.Database;
import manager.UserManager;
import model.Etudiant;
import model.Session;
import model.User;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JFormattedTextField;

public class fiche_etudiant_form extends JFrame {

	private static JFrame frame;
	private JTextField textFieldNom;
	private JTextField textFieldPrenom;
	private JTextField textFieldDernierDiplome;
	private JTextField textFieldEmail;
	private JTextField textFieldTelephone;
	private JTextField textFieldAdresse;
	private static Session session;
	private User utilisateurConnecte;




	/**
	 * Create the application.
	 */
	public fiche_etudiant_form(User user) {
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

		JLabel lblNewLabel = new JLabel("Enregistrement Fiche étudiant");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel.setBounds(166, 29, 214, 22);
		frame.getContentPane().add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Nom");
		lblNewLabel_1.setBounds(28, 93, 45, 13);
		frame.getContentPane().add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("prenom");
		lblNewLabel_2.setBounds(28, 124, 45, 13);
		frame.getContentPane().add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("Dernier diplôme");
		lblNewLabel_3.setBounds(28, 155, 95, 13);
		frame.getContentPane().add(lblNewLabel_3);

		JLabel lblNewLabel_4 = new JLabel("Email");
		lblNewLabel_4.setBounds(28, 186, 95, 13);
		frame.getContentPane().add(lblNewLabel_4);

		JLabel lblNewLabel_5 = new JLabel("Téléphone");
		lblNewLabel_5.setBounds(28, 217, 95, 13);
		frame.getContentPane().add(lblNewLabel_5);

		JLabel lblNewLabel_6 = new JLabel("Adresse");
		lblNewLabel_6.setBounds(28, 248, 95, 13);
		frame.getContentPane().add(lblNewLabel_6);

		textFieldNom = new JTextField();
		textFieldNom.setBounds(127, 90, 196, 19);
		frame.getContentPane().add(textFieldNom);
		textFieldNom.setColumns(10);

		textFieldPrenom = new JTextField();
		textFieldPrenom.setBounds(127, 121, 196, 19);
		frame.getContentPane().add(textFieldPrenom);
		textFieldPrenom.setColumns(10);

		textFieldDernierDiplome = new JTextField();
		textFieldDernierDiplome.setBounds(127, 152, 196, 19);
		frame.getContentPane().add(textFieldDernierDiplome);
		textFieldDernierDiplome.setColumns(10);

		textFieldEmail = new JTextField();
		textFieldEmail.setBounds(127, 183, 196, 19);
		frame.getContentPane().add(textFieldEmail);
		textFieldEmail.setColumns(10);

		textFieldTelephone = new JTextField();
		textFieldTelephone.setBounds(127, 214, 96, 19);
		frame.getContentPane().add(textFieldTelephone);
		textFieldTelephone.setColumns(10);

		textFieldAdresse = new JTextField();
		textFieldAdresse.setBounds(127, 245, 196, 19);
		frame.getContentPane().add(textFieldAdresse);
		textFieldAdresse.setColumns(10);

		JButton btnNewButton = new JButton("Retour");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		btnNewButton.setBounds(127, 313, 85, 21);
		frame.getContentPane().add(btnNewButton);

		JButton btnNewButton_1 = new JButton("Valider");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				btnNewButton_1.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {

						String nom = textFieldNom.getText().trim();
						String prenom = textFieldPrenom.getText().trim();
						String dernier_diplome = textFieldDernierDiplome.getText().trim();
						String email = textFieldEmail.getText().trim();
						String telephone = textFieldTelephone.getText().trim();
						String adresse = textFieldAdresse.getText().trim();

						if (nom.isEmpty() || prenom.isEmpty() || dernier_diplome.isEmpty() || email.isEmpty() || telephone.isEmpty() || adresse.isEmpty()) {
							JOptionPane.showMessageDialog(frame, "Veuillez remplir tous les champs !");
						} else {
							try {
								int tel = Integer.parseInt(telephone);
								Etudiant etudiant = new Etudiant();
								UserManager userManager = new UserManager();

								if (Session.sessionId != 0) {
									session = Session.getSessionExistante(utilisateurConnecte);
								} else {
									System.out.println("Pas de session détectée");
								}				                

								if (session != null) {
									int idSession = session.getSessionId();
									userManager.fiche_etudiant(etudiant, nom, prenom, dernier_diplome, email, telephone, adresse, idSession);
									textFieldNom.setText("");
									textFieldPrenom.setText("");
									textFieldDernierDiplome.setText("");
									textFieldEmail.setText("");
									textFieldTelephone.setText("");
									textFieldAdresse.setText("");

								} else {
									System.out.println("La session est nulle !");
								}

							} catch (NumberFormatException ex) {
								JOptionPane.showMessageDialog(frame, "Veuillez saisir un numéro de téléphone valide !");
							} catch (SQLException ex) {
								JOptionPane.showMessageDialog(frame, "Erreur lors de l'enregistrement des données !");
							}
						}
					}
					});


				}
			});
		btnNewButton_1.setBounds(238, 313, 85, 21);
		frame.getContentPane().add(btnNewButton_1);


		}
	}
