package view.Profil_admin;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Choix_creation_profil {

	private JFrame frame;


	/**
	 * Create the application.
	 */
	public Choix_creation_profil() {
		initialize();
	}
	
	public void run() {
		frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Creation d'un nouveaux profil.");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel.setBounds(109, 10, 231, 34);
		frame.getContentPane().add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Admin");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Formulaire_inscription_admin Formulaire_inscription_admin = new Formulaire_inscription_admin();
				Formulaire_inscription_admin.run();
			}
		});
		btnNewButton.setBounds(172, 54, 85, 21);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnGestionnaireDeStock = new JButton("Gestionnaire de stock");
		btnGestionnaireDeStock.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		btnGestionnaireDeStock.setBounds(145, 95, 143, 21);
		frame.getContentPane().add(btnGestionnaireDeStock);
		
		JButton btnProfesseur = new JButton("Professeur");
		btnProfesseur.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Formulaire_professeur Formulaire_professeur = new Formulaire_professeur();
				Formulaire_professeur.run();
			}
			
		});
		btnProfesseur.setBounds(172, 135, 85, 21);
		frame.getContentPane().add(btnProfesseur);
		
		JButton btnNewButton_1_1 = new JButton("S\u00E9cretaire");
		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Formulaire_inscription_secretaire Formulaire_inscription_secretaire = new Formulaire_inscription_secretaire();
				Formulaire_inscription_secretaire.run();
			}
		});
		btnNewButton_1_1.setBounds(172, 178, 85, 21);
		frame.getContentPane().add(btnNewButton_1_1);
		
		JButton btnNewButton_1_1_1 = new JButton("Retour");
		btnNewButton_1_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Accueil_admin Accueil_admin = new Accueil_admin(null);
				Accueil_admin.run();
			}
		});
		btnNewButton_1_1_1.setBounds(30, 232, 85, 21);
		frame.getContentPane().add(btnNewButton_1_1_1);
	}

	
}
