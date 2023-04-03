package view.Profil_professeur;

import java.awt.EventQueue;

import javax.swing.JFrame;

import model.Session;
import model.User;
import view.Connexion;
import view.Profil_secretaire.fiche_etudiant_form;

import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Acceuil_professeur extends JFrame {

	private static JFrame frame;
	private User utilisateurConnecte;
	private static Session session;



public static void run() {
		
		frame.setVisible(true);

	}

	/**
	 * Create the application.
	 */
	public Acceuil_professeur(User user) {
		this.utilisateurConnecte = user;
		initialize();
	    session = Session.getSessionExistante(utilisateurConnecte); // appel de la méthode getSessionExistante()
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnNewButton_1 = new JButton("Prendre en charge un etudiant");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Prise_en_charge Prise_en_charge = new Prise_en_charge(utilisateurConnecte);
				Prise_en_charge.run();
			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton_1.setBounds(109, 69, 265, 23);
		frame.getContentPane().add(btnNewButton_1);
		
		JButton btnNewButton_3 = new JButton("Demande de fourniture");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				demande_fourniture demande_fourniture = new demande_fourniture(utilisateurConnecte);
				demande_fourniture.run();
			}
		});
		btnNewButton_3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton_3.setBounds(141, 115, 205, 23);
		frame.getContentPane().add(btnNewButton_3);
		
		JButton btnNewButton_4 = new JButton("Se déconnecter");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				frame.dispose(); // ferme la fenêtre
				Connexion Connexion = new Connexion();
				Connexion.run();
			}
		});
		btnNewButton_4.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton_4.setBounds(173, 229, 131, 23);
		frame.getContentPane().add(btnNewButton_4);
		
		JLabel lblNewLabel = new JLabel("Profil Professeur");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel.setBounds(173, 20, 149, 23);
		frame.getContentPane().add(lblNewLabel);
	}
}
