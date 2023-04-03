package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import manager.UserManager;
import model.Session;
import model.User;
import view.Profil_admin.Accueil_admin;
import view.Profil_professeur.Acceuil_professeur;
import view.Profil_secretaire.Accueil_secretaire;
import view.Profil_stock.Accueil_stock;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.JPasswordField;

public class Connexion extends JFrame {

	private static JFrame frame;
	private JTextField textLogin;
	private JPasswordField passwordField;
	private UserManager userManager = new UserManager();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Connexion window = new Connexion();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	public Connexion() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel lblLogin = new JLabel("Login");
		lblLogin.setBounds(160, 42, 114, 15);
		frame.getContentPane().add(lblLogin);                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                   

		textLogin = new JTextField();
		textLogin.setBounds(160, 69, 114, 19);
		frame.getContentPane().add(textLogin);
		textLogin.setColumns(10);

		JLabel lblPassword = new JLabel("Mot de passe");
		lblPassword.setBounds(160, 100, 114, 15);
		frame.getContentPane().add(lblPassword);

		passwordField = new JPasswordField();
		passwordField.setBounds(160, 127, 114, 19);
		frame.getContentPane().add(passwordField);

		JLabel lblErreur = new JLabel("<html>Le login/mdp saisi ne correspon pas � un utilisateur</html>");
		lblErreur.setVisible(false);
		lblErreur.setForeground(Color.RED);
		lblErreur.setBounds(74, 195, 291, 33);
		frame.getContentPane().add(lblErreur);

		JButton btnEnregistrer = new JButton("Connexion");
		btnEnregistrer.setBounds(160, 158, 117, 25);
		frame.getContentPane().add(btnEnregistrer);

		btnEnregistrer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				String login, password;
				login = textLogin.getText();
				password = String.valueOf(passwordField.getPassword());

				User user = userManager.Connexion(login, password);
				if(user == null) {
				    lblErreur.setVisible(true);
				} else {
					Session session = Session.createSession(user);

				    frame.setVisible(false);
				    int role = user.getRole();
				    if(role == 0) {
				        Accueil_admin accueil_admin = new Accueil_admin(user);
				        accueil_admin.run();
				    } else if(role == 1) {
				        Acceuil_professeur accueil_professeur = new Acceuil_professeur(user);
				        accueil_professeur.run();
				    } else if(role == 2) {
				        Accueil_stock accueil_stock = new Accueil_stock(user);
				        accueil_stock.run();
				    } else if(role == 3) {
				        Accueil_secretaire accueil_secretaire = new Accueil_secretaire(user);
				        accueil_secretaire.run();
				    }
				
				}




			};
		});
	}


	public static void run() {
		// TODO Auto-generated method stub
		frame.setVisible(true);

	}




}
