package view.Profil_admin;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;

import model.User;
import view.Afficher_user;
import view.Connexion;

import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class Accueil_admin extends JFrame {

	private static JFrame frame;
	private User utilisateurConnecte;


	/**
	 * Create the application.
	 */
	public Accueil_admin(User user) {
		this.utilisateurConnecte = user;
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
		frame.getContentPane().setBackground(new Color(255, 128, 64));
		frame.getContentPane().setFont(new Font("Tahoma", Font.PLAIN, 16));
		frame.setBounds(100, 100, 503, 372);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Profil admininstrateur:");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setBackground(Color.WHITE);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel.setBounds(152, 36, 159, 25);
		frame.getContentPane().add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Afficher les utilisateur");
		btnNewButton.setForeground(Color.DARK_GRAY);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Afficher_user Afficher_user = new Afficher_user(utilisateurConnecte);
				Afficher_user.run();
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton.setBounds(137, 96, 193, 21);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Cr\u00E9er un nouvel utilisateur");
		btnNewButton_1.setForeground(Color.DARK_GRAY);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Choix_creation_profil Choix_creation_profil = new Choix_creation_profil();
				Choix_creation_profil.run();
			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnNewButton_1.setBounds(137, 133, 193, 25);
		frame.getContentPane().add(btnNewButton_1);
		
		JButton btnRetour = new JButton("Retour");
		btnRetour.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			frame.dispose(); // ferme la fenêtre
			Connexion Connexion = new Connexion();
			Connexion.run();
		}
		});
		btnRetour.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnRetour.setBounds(188, 270, 89, 23);
		frame.getContentPane().add(btnRetour);
	}


	

}
