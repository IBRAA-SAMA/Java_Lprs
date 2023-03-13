package view.Profil_admin;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

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
		frame.getContentPane().setBackground(new Color(128, 255, 128));
		frame.getContentPane().setForeground(Color.WHITE);
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Creation d'un nouveaux profil.");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel.setBounds(109, 10, 231, 34);
		frame.getContentPane().add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Admin");
		btnNewButton.setForeground(Color.LIGHT_GRAY);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Formulaire_inscription_admin Formulaire_inscription_admin = new Formulaire_inscription_admin();
				Formulaire_inscription_admin.run();
			}
		});
		btnNewButton.setBounds(172, 54, 85, 21);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnGestionnaireDeStock = new JButton("Gestionnaire de stock");
		btnGestionnaireDeStock.setForeground(Color.LIGHT_GRAY);
		btnGestionnaireDeStock.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Formulaire_gestionnairestock Formulaire_gestionnairestock = new Formulaire_gestionnairestock();
				Formulaire_gestionnairestock.run();
				
			}
		});
		btnGestionnaireDeStock.setBounds(145, 95, 143, 21);
		frame.getContentPane().add(btnGestionnaireDeStock);
		
		JButton btnProfesseur = new JButton("Professeur");
		btnProfesseur.setForeground(Color.LIGHT_GRAY);
		btnProfesseur.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Formulaire_professeur Formulaire_professeur = new Formulaire_professeur();
				Formulaire_professeur.run();
			}
			
		});
		btnProfesseur.setBounds(172, 135, 85, 21);
		frame.getContentPane().add(btnProfesseur);
		
		JButton btnNewButton_1_1 = new JButton("S\u00E9cretaire");
		btnNewButton_1_1.setForeground(Color.LIGHT_GRAY);
		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Formulaire_inscription_secretaire Formulaire_inscription_secretaire = new Formulaire_inscription_secretaire();
				Formulaire_inscription_secretaire.run();
			}
		});
		btnNewButton_1_1.setBounds(172, 178, 85, 21);
		frame.getContentPane().add(btnNewButton_1_1);
		
		JButton btnNewButton_1_1_1 = new JButton("Retour");
		btnNewButton_1_1_1.setForeground(Color.LIGHT_GRAY);
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
