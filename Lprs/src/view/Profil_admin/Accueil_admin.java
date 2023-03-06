package view.Profil_admin;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;

import model.User;
import view.Connexion;

import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class Accueil_admin {

	private static JFrame frame;
	private User utilisateurConnecte;


	/**
	 * Create the application.
	 */
	public Accueil_admin(User user) {
		this.utilisateurConnecte = user;
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
		frame.getContentPane().setFont(new Font("Tahoma", Font.PLAIN, 16));
		frame.setBounds(100, 100, 503, 372);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Profil admininstrateur");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel.setBounds(137, 25, 159, 25);
		frame.getContentPane().add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Afficher les utilisateur");
		btnNewButton.setForeground(Color.DARK_GRAY);
		btnNewButton.setBackground(Color.WHITE);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnNewButton.setBounds(137, 60, 152, 21);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Cr\u00E9er un nouvel utilisateur");
		btnNewButton_1.setForeground(Color.DARK_GRAY);
		btnNewButton_1.setBackground(Color.WHITE);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Choix_creation_profil Choix_creation_profil = new Choix_creation_profil();
				Choix_creation_profil.run();
			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnNewButton_1.setBounds(118, 103, 193, 25);
		frame.getContentPane().add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Se deconnecter");
		btnNewButton_2.setForeground(Color.DARK_GRAY);
		btnNewButton_2.setBackground(Color.WHITE);
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Connexion Connexion = new Connexion();
				Connexion.run();
			}
		});
		btnNewButton_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnNewButton_2.setBounds(137, 201, 152, 25);
		frame.getContentPane().add(btnNewButton_2);
		
		JButton btnNewButton_1_1 = new JButton("Parametre du compte");
		btnNewButton_1_1.setForeground(Color.DARK_GRAY);
		btnNewButton_1_1.setBackground(Color.WHITE);
		btnNewButton_1_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnNewButton_1_1.setBounds(118, 152, 193, 25);
		frame.getContentPane().add(btnNewButton_1_1);
	}

	

}
