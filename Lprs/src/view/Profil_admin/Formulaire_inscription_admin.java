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
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.awt.event.ActionEvent;

public class Formulaire_inscription_admin {

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
	

	/**
	 * Create the application.
	 */
	public Formulaire_inscription_admin() {
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
		frame.getContentPane().setForeground(Color.DARK_GRAY);
		frame.getContentPane().setBackground(Color.PINK);
		frame.getContentPane().setFont(new Font("Tahoma", Font.PLAIN, 15));
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Inscription Admin");
		lblNewLabel.setBackground(new Color(255, 128, 128));
		lblNewLabel.setForeground(new Color(64, 64, 64));
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel.setBounds(162, 22, 129, 19);
		frame.getContentPane().add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(172, 52, 96, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(172, 83, 96, 20);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(172, 118, 96, 20);
		frame.getContentPane().add(textField_2);
		textField_2.setColumns(10);
		
		JButton btnNewButton = new JButton("Retour");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Choix_creation_profil Choix_creation_profil = new Choix_creation_profil();
				Choix_creation_profil.run();
			}
		});
		btnNewButton.setBackground(new Color(255, 128, 128));
		btnNewButton.setForeground(Color.DARK_GRAY);
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton.setBounds(179, 229, 89, 23);
		frame.getContentPane().add(btnNewButton);
		
		JLabel lblNewLabel_1 = new JLabel("Nom");
		lblNewLabel_1.setForeground(Color.DARK_GRAY);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(97, 53, 49, 14);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Prenom");
		lblNewLabel_2.setForeground(Color.DARK_GRAY);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_2.setBounds(97, 86, 65, 14);
		frame.getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Adresse mail");
		lblNewLabel_3.setForeground(Color.DARK_GRAY);
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_3.setBounds(73, 119, 80, 14);
		frame.getContentPane().add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Mot de passe");
		lblNewLabel_4.setForeground(Color.DARK_GRAY);
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_4.setBounds(73, 152, 89, 14);
		frame.getContentPane().add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Confirmer mdp");
		lblNewLabel_5.setForeground(Color.DARK_GRAY);
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_5.setBounds(73, 182, 96, 14);
		frame.getContentPane().add(lblNewLabel_5);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(172, 149, 96, 20);
		frame.getContentPane().add(passwordField);
		
		passwordField_1 = new JPasswordField();
		passwordField_1.setBounds(172, 180, 96, 20);
		frame.getContentPane().add(passwordField_1);
		
		JButton btnNewButton_1 = new JButton("Valider");
		btnNewButton_1.addActionListener(new ActionListener() {
		    @SuppressWarnings("deprecation")
		    public void actionPerformed(ActionEvent e) {
		        String nom = textField.getText().trim();
		        String prenom = textField_1.getText().trim();
		        String email = textField_2.getText().trim();
		        String mdp = passwordField.getText().trim();
		        String mdpConfirmation = passwordField_1.getText().trim();
		        if (nom.isEmpty() || prenom.isEmpty() || email.isEmpty() || mdp.isEmpty() || mdpConfirmation.isEmpty()) {
		            JOptionPane.showMessageDialog(null, "Veuillez remplir tous les champs !");
		        } else if (!mdp.equals(mdpConfirmation)) {
		            JOptionPane.showMessageDialog(null, "Les mots de passe ne correspondent pas !");
		        } else {
		            Database bdd = new Database();
		            Connection co_bdd = bdd.getConnection();
		            try {
		            	PreparedStatement stm = co_bdd.prepareStatement(
		            			"INSERT INTO utilisateur (nom, prenom, email, mdp, date_verif, role, reset_mdp) VALUES (?, ?, ?, md5(?), ?, ?, ?);");
		            			stm.setString(1, nom);
		            			stm.setString(2, prenom);
		            			stm.setString(3, email);
		            			stm.setString(4, mdp);
		            			stm.setTimestamp(5, new Timestamp(System.currentTimeMillis()));
		            			stm.setInt(6, 1);
		            			stm.setInt(7, 1);
		                int res = stm.executeUpdate();
		                if (res == 1) {
		                    JOptionPane.showMessageDialog(null, "Insertion r�ussie !");
		                }
		            } catch (SQLException ex) {
		                ex.printStackTrace();
		            }
		        }
		    }
		});

	
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton_1.setBounds(302, 117, 85, 21);
		frame.getContentPane().add(btnNewButton_1);
	}

	
}
