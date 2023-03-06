package view.Profil_admin;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;

import manager.UserManager;
import model.User;
import view.Connexion;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import javax.swing.JComboBox;
import java.awt.Color;

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
	private JTextField textRole;
	private JTextField textField;
	private JTextField textField_1;
	
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
		frame.getContentPane().setBackground(new Color(128, 128, 192));
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
		btnNewButton.setForeground(Color.BLACK);
		btnNewButton.setBackground(Color.WHITE);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
			}
		});
		btnNewButton.setBounds(321, 134, 89, 23);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Retour");
		btnNewButton_1.setForeground(Color.BLACK);
		btnNewButton_1.setBackground(Color.WHITE);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Choix_creation_profil Choix_creation_profil = new Choix_creation_profil();
				Choix_creation_profil.run();
			}
		});
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
		
		textField = new JTextField();
		textField.setBounds(169, 174, 96, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(169, 206, 96, 20);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
	}
}
