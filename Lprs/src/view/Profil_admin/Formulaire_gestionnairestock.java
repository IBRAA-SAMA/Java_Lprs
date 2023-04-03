package view.Profil_admin;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;

import database.Database;
import manager.UserManager;
import model.User;
import view.Connexion;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import javax.swing.JComboBox;
import java.awt.Color;
import javax.swing.JDesktopPane;

public class Formulaire_gestionnairestock {

	private JFrame frame;
	private JTextField txtPrenom;
	private JTextField txtNom;
	private JTextField txtEmail;
	private User user;
	private JPasswordField passwordField;
	private JPasswordField passwordField_1;
	private JLabel lblPasswordConf;
	private JLabel lblPassword;
	
	private JPasswordField passwordField_2;
	private JPasswordField passwordField_3;
	
	public void run() {
		frame.setVisible(true);
	}

	/**
	 * Create the application.
	 */
	public Formulaire_gestionnairestock() {
		initialize();

	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(192, 192, 192));
		frame.getContentPane().setFont(new Font("Tahoma", Font.PLAIN, 12));
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		txtPrenom = new JTextField();
		txtPrenom.setBounds(169, 94, 96, 20);
		frame.getContentPane().add(txtPrenom);
		txtPrenom.setColumns(10);
		
		txtNom = new JTextField();
		txtNom.setBounds(169, 63, 96, 20);
		frame.getContentPane().add(txtNom);
		txtNom.setColumns(10);
		
		txtEmail = new JTextField();
		txtEmail.setBounds(169, 135, 96, 20);
		frame.getContentPane().add(txtEmail);
		txtEmail.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Formulaire Gestionnaire stock");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel.setBounds(126, 11, 233, 41);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Nom");
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(10, 64, 49, 14);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Prenom");
		lblNewLabel_2.setForeground(Color.WHITE);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_2.setBounds(10, 95, 49, 14);
		frame.getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Email");
		lblNewLabel_3.setForeground(Color.WHITE);
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_3.setBounds(10, 136, 49, 14);
		frame.getContentPane().add(lblNewLabel_3);
		
		JButton btnNewButton = new JButton("Valider");
		btnNewButton.setBackground(Color.LIGHT_GRAY);
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String nom = txtNom.getText();
		        String prenom = txtPrenom.getText();
		        String email = txtEmail.getText();
		        String password = new String(passwordField_2.getPassword());
		        String confirmPassword = new String(passwordField_3.getPassword());

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
		                userManager.Inscription_stock(user, nom, prenom, email, password);

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
		btnNewButton.setBounds(321, 134, 89, 23);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Retour");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		btnNewButton_1.setForeground(Color.WHITE);
		btnNewButton_1.setBounds(321, 184, 89, 23);
		frame.getContentPane().add(btnNewButton_1);
		
		JLabel lblPassword = new JLabel("Mot de passe");
		lblPassword.setForeground(Color.WHITE);
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPassword.setBounds(10, 175, 96, 14);
		frame.getContentPane().add(lblPassword);
		
		JLabel lblNewLabel_5 = new JLabel("Confirmer mot de passe");
		lblNewLabel_5.setForeground(Color.WHITE);
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_5.setBounds(10, 207, 149, 14);
		frame.getContentPane().add(lblNewLabel_5);
		
		passwordField_2 = new JPasswordField();
		passwordField_2.setBounds(169, 169, 96, 20);
		frame.getContentPane().add(passwordField_2);
		
		passwordField_3 = new JPasswordField();
		passwordField_3.setBounds(169, 206, 96, 20);
		frame.getContentPane().add(passwordField_3);
	}
}
