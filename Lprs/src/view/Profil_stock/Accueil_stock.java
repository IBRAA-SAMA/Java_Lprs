package view.Profil_stock;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;

import model.User;
import view.Connexion;
import view.Profil_secretaire.fiche_etudiant_form;

import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Accueil_stock extends JFrame {

	private static JFrame frame;
	private User utilisateurConnecte;


public static void run() {
		
		frame.setVisible(true);

	}
	public Accueil_stock(User user) {
		this.utilisateurConnecte = user;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Profil Gestionnaire de stock");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel.setBounds(84, 20, 200, 29);
		frame.getContentPane().add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Demande de fourniture");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Afficher_demande Afficher_demande = new Afficher_demande(utilisateurConnecte);
				Afficher_demande.run();
			}
		});
		btnNewButton.setBounds(121, 59, 149, 21);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnDemandeDeFourniture = new JButton("Fiche fournitures");
		btnDemandeDeFourniture.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fiche_fourniture fiche_fourniture = new fiche_fourniture(utilisateurConnecte);
				fiche_fourniture.run();
			}
		});
		btnDemandeDeFourniture.setBounds(121, 90, 149, 21);
		frame.getContentPane().add(btnDemandeDeFourniture);
		
		JButton btnAfficherLeStock = new JButton("Afficher le stock");
		btnAfficherLeStock.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Afficher_stock Afficher_stock = new Afficher_stock(utilisateurConnecte);
				Afficher_stock.run();
			}
		});
		btnAfficherLeStock.setBounds(121, 127, 149, 21);
		frame.getContentPane().add(btnAfficherLeStock);
		
		JButton btnSeDeconecter = new JButton("Se deconnecter");
		btnSeDeconecter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose(); // ferme la fenêtre
				Connexion Connexion = new Connexion();
				Connexion.run();
			}
		});
		btnSeDeconecter.setBounds(121, 208, 149, 21);
		frame.getContentPane().add(btnSeDeconecter);
	}

}
