package view.Profil_secretaire;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;

import model.Session;
import model.User;
import view.Afficher_user;
import view.Connexion;

import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Accueil_secretaire extends JFrame {

	private static JFrame frame;
	private User utilisateurConnecte;
	private static Session session;

	
	public static void run() {
	        frame.setVisible(true);
	}

	
	public Accueil_secretaire(User user) {
	    this.utilisateurConnecte = user;
	    initialize();
	    session = Session.getSessionExistante(utilisateurConnecte); // appel de la méthode getSessionExistante()
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 539, 399);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
	    System.out.println("La variable de session vaut : " + Session.sessionId);
		
		JButton btnNewButton_1 = new JButton("Dossier inscription etudiant");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DossierInscription DossierInscription = new DossierInscription(utilisateurConnecte);
				DossierInscription.run();
			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton_1.setBounds(129, 71, 216, 23);
		frame.getContentPane().add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Creer Fiche étudiant");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				fiche_etudiant_form fiche_etudiant_form = new fiche_etudiant_form(utilisateurConnecte);
				fiche_etudiant_form.run();
				
			}
		});
		btnNewButton_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton_2.setBounds(139, 112, 200, 23);
		frame.getContentPane().add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("Se deconnecter");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose(); // ferme la fenêtre
				Connexion Connexion = new Connexion();
				Connexion.run();
			}
		});
		btnNewButton_3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton_3.setBounds(167, 265, 137, 23);
		frame.getContentPane().add(btnNewButton_3);
		
		JLabel lblNewLabel = new JLabel("Profil Secretaire");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel.setBounds(179, 24, 146, 23);
		frame.getContentPane().add(lblNewLabel);
		
		JButton btnNewButton_4 = new JButton("Afficher fiche etudiant");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Afficher_fiche_etudiant Afficher_fiche_etudiant = new Afficher_fiche_etudiant();
				Afficher_fiche_etudiant.run();
			}
		});
		btnNewButton_4.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton_4.setBounds(139, 159, 200, 23);
		frame.getContentPane().add(btnNewButton_4);
		
		JButton btnNewButton = new JButton("Afficher dossier etudiant");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Afficher_dossier_ins Afficher_dossier_ins = new Afficher_dossier_ins();
				Afficher_dossier_ins.run();
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton.setBounds(139, 206, 200, 23);
		frame.getContentPane().add(btnNewButton);
	}

}
