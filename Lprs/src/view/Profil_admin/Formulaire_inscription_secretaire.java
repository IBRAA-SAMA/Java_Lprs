package view.Profil_admin;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;

import database.Database;
import manager.UserManager;
import model.User;

import javax.swing.JButton;
import java.awt.Color;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.awt.event.ActionEvent;

public class Formulaire_inscription_secretaire {

	private User user;
	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JPasswordField passwordField;
	private JPasswordField passwordField_1;

	/**
	 * Launch the application.
	 */
	public void run() {
		frame.setVisible(true);
	}


	/**
	 * Create the application.
	 */
	public Formulaire_inscription_secretaire() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(64, 128, 128));
		frame.getContentPane().setForeground(new Color(128, 128, 64));
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Formulaire Secretaire");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel.setBounds(162, 11, 174, 20);
		frame.getContentPane().add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(193, 42, 96, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(193, 81, 96, 20);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(193, 117, 96, 20);
		frame.getContentPane().add(textField_2);
		textField_2.setColumns(10);
		
		JButton btnNewButton = new JButton("Retour");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();

			}
		});
		btnNewButton.setBackground(Color.WHITE);
		btnNewButton.setForeground(Color.DARK_GRAY);
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnNewButton.setBounds(200, 229, 89, 23);
		frame.getContentPane().add(btnNewButton);
		
		JLabel lblNewLabel_1 = new JLabel("Nom");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setBounds(96, 43, 49, 14);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Prenom");
		lblNewLabel_2.setForeground(Color.WHITE);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_2.setBounds(96, 82, 49, 14);
		frame.getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Adresse mail");
		lblNewLabel_3.setForeground(Color.WHITE);
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_3.setBounds(96, 118, 96, 14);
		frame.getContentPane().add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Mot de passe");
		lblNewLabel_4.setForeground(Color.WHITE);
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_4.setBounds(96, 154, 96, 14);
		frame.getContentPane().add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Confirmation mdp");
		lblNewLabel_5.setForeground(Color.WHITE);
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_5.setBounds(78, 193, 113, 14);
		frame.getContentPane().add(lblNewLabel_5);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(193, 153, 96, 20);
		frame.getContentPane().add(passwordField);
		
		passwordField_1 = new JPasswordField();
		passwordField_1.setBounds(193, 192, 96, 20);
		frame.getContentPane().add(passwordField_1);
		
		JButton btnNewButton_1 = new JButton("Valider");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				 String nom = textField.getText();
			        String prenom = textField_1.getText();
			        String email = textField_2.getText();
			        String password = new String(passwordField.getPassword());
			        String confirmPassword = new String(passwordField_1.getPassword());

			        if (nom.equals("") || prenom.equals("") || email.equals("") || password.equals("") || confirmPassword.equals("")) {
			            JOptionPane.showMessageDialog(null, "Veuillez remplir tous les champs.");
			        } else if (!isValidEmail(email)) {
			            JOptionPane.showMessageDialog(null, "Adresse email invalide.");
			        } else if (!password.equals(confirmPassword)) {
			            JOptionPane.showMessageDialog(null, "Les mots de passe ne correspondent pas.");
			        } else {
			            try {
			                // Connection à la base de données
			            	Database database = new Database();
			            	Connection connection = database.getConnection();

			                // Création de l'utilisateur
			                user = new User();
			                user.setNom(nom);
			                user.setPrenom(prenom);
			                user.setEmail(email);
			                user.setMdp(password);

			                // Ajout de l'utilisateur dans la base de données
			                UserManager userManager = new UserManager();
			                userManager.Inscription_secretaire(user, nom, prenom, email, password);

			                JOptionPane.showMessageDialog(null, "Inscription réussie.");

			                // Retour au choix de création de profil
			                Choix_creation_profil Choix_creation_profil = new Choix_creation_profil();
			                Choix_creation_profil.run();
			                frame.dispose();

			            } catch (SQLException ex) {
			                ex.printStackTrace();
			                JOptionPane.showMessageDialog(null, "Erreur lors de l'inscription.");
			            }
			        }
			    }

			private boolean isValidEmail(String email) {
				// Utilisation d'une expression régulière pour vérifier si l'adresse email est valide
				String regex = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
				Pattern pattern = Pattern.compile(regex);
				Matcher matcher = pattern.matcher(email);
				return matcher.matches();
			}
		});
		btnNewButton_1.setForeground(Color.DARK_GRAY);
		btnNewButton_1.setBackground(Color.WHITE);
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton_1.setBounds(324, 116, 89, 23);
		frame.getContentPane().add(btnNewButton_1);
	}
}
