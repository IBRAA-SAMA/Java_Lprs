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
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import javax.swing.JComboBox;

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
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel.setBounds(126, 11, 233, 41);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Nom");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(10, 64, 49, 14);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Prenom");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_2.setBounds(10, 95, 49, 14);
		frame.getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Email");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_3.setBounds(10, 136, 49, 14);
		frame.getContentPane().add(lblNewLabel_3);
		
		JButton btnNewButton = new JButton("Valider");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		    	String nom = txtNom.getText();
		        String prenom = txtPrenom.getText();
		        String email = txtEmail.getText();
		        String mdp = new String(passwordField_2.getPassword());
		        String confirmationMdp = new String(passwordField_3.getPassword());

		        
		        User user = new User();
		        UserManager userManager = new UserManager();
		        User Inscription_admin = null;
				try {
					Inscription_admin = userManager.Inscription_admin(user);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		        if (Inscription_admin != null) {
		            JOptionPane.showMessageDialog(frame, "Inscription r�ussie !");
		        } else {
		            JOptionPane.showMessageDialog(frame, "L'inscription a �chou�. Veuillez r�essayer.");
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
		            			stm.setInt(6, 2);
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
		btnNewButton.setBounds(321, 134, 89, 23);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Retour");
		btnNewButton_1.setBounds(321, 184, 89, 23);
		frame.getContentPane().add(btnNewButton_1);
		
		JLabel lblPassword = new JLabel("Mot de passe");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPassword.setBounds(10, 175, 96, 14);
		frame.getContentPane().add(lblPassword);
		
		JLabel lblNewLabel_5 = new JLabel("Confirmer mot de passe");
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
