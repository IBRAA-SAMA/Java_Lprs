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
	

	/**
	 * Create the application.
	 */
	public Formulaire_gestionnairestock(User user) {
		initialize();
		this.user = user;
		this.txtNom.setText(user.getNom());
		this.txtPrenom.setText(user.getPrenom());
		this.txtEmail.setText(user.getMail());
		
		passwordField = new JPasswordField();
		passwordField.setBounds(169, 170, 96, 19);
		frame.getContentPane().add(passwordField);
		
		passwordField_1 = new JPasswordField();
		passwordField_1.setBounds(169, 203, 96, 19);
		frame.getContentPane().add(passwordField_1);
		
		textRole = new JTextField();
		textRole.setBounds(169, 233, 96, 19);
		frame.getContentPane().add(textRole);
		textRole.setColumns(10);
		if(user.getIdUser()>0) {
			this.passwordField_1.setVisible(false);
			this.lblPasswordConf.setVisible(false);
			this.passwordField.setVisible(false);
			this.lblPassword.setVisible(false);
		}else {
			this.passwordField_1.setVisible(true);
			this.lblPasswordConf.setVisible(true);
			this.passwordField.setVisible(true);
			this.lblPassword.setVisible(true);
		}

		

		

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
				
				UserManager userManager= new UserManager();
				try {
					user.setNom(txtNom.getText());
					user.setPrenom(txtPrenom.getText());
					user.setMail(txtEmail.getText());
					if(passwordField.isVisible()) {
						String password = String.valueOf(passwordField.getPassword());
						String confPassword = String.valueOf(passwordField_1.getPassword());
						if(password.equals(confPassword)) {
							user.setMdp(password);
							userManager.Inscription_stock(user);
							
							Connexion connexion = new  Connexion();
							Connexion.run();
							
							
							frame.setVisible(false);
						}else {
							System.out.println("erreur mdp ");
						}
					}else {
						userManager.Inscription_stock(user);
						Connexion connexion = new  Connexion();
						Connexion.run();
						frame.setVisible(false);
					}

				} catch (SQLException e1) {
					System.out.println("Erreur");
					//e.printStackTrace();
				} catch (NumberFormatException e1) {
					//e.printStackTrace();
					System.out.println("Erreur format");
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
		
		JLabel lblNewLabel_6 = new JLabel("Role");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_6.setBounds(10, 233, 49, 14);
		frame.getContentPane().add(lblNewLabel_6);
	}
}
