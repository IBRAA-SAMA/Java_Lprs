package view.Profil_admin;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

import manager.UserManager;
import model.User;
import view.detail.PanelUser;
import javax.swing.JPasswordField;

public class UserForm {

	private JFrame frame;
	private JTextField nomField;
	private JTextField prenomField;
	private User user;
	private JTextField mailField;
	private JPasswordField passwordField;
	private JPasswordField ConfPasswordField;
	private JLabel lblPasswordConf;
	private JLabel lblPassword;

	public void run() {
		frame.setVisible(true);
	}
	/**
	 * Create the application.
	 */
	public UserForm(User user) {
		initialize();
		this.user = user;
		this.nomField.setText(user.getNom());
		this.prenomField.setText(user.getPrenom());
		this.mailField.setText(user.getEmail());
		if(user.getIdUser()>0) {
			this.ConfPasswordField.setVisible(false);
			this.lblPasswordConf.setVisible(false);
			this.passwordField.setVisible(false);
			this.lblPassword.setVisible(false);
		}else {
			this.ConfPasswordField.setVisible(true);
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
		frame.setBounds(100, 100, 218, 444);
		frame.getContentPane().setLayout(null);
		
		JLabel lblTtire = new JLabel("<html>Ajouter/modifier un user</html>");
		lblTtire.setHorizontalAlignment(SwingConstants.CENTER);
		lblTtire.setHorizontalTextPosition(SwingConstants.LEADING);
		lblTtire.setBounds(12, 12, 186, 25);
		frame.getContentPane().add(lblTtire);
		
		JLabel lblNom = new JLabel("Nom");
		lblNom.setBounds(22, 42, 176, 15);
		frame.getContentPane().add(lblNom);
		
		nomField = new JTextField();
		nomField.setBounds(22, 64, 176, 19);
		frame.getContentPane().add(nomField);
		nomField.setColumns(10);
		
		JLabel lblPrenom = new JLabel("Prenom");
		lblPrenom.setBounds(22, 90, 176, 15);
		frame.getContentPane().add(lblPrenom);
		
		prenomField = new JTextField();
		prenomField.setBounds(22, 112, 176, 19);
		frame.getContentPane().add(prenomField);
		prenomField.setColumns(10);
		JLabel lblMail = new JLabel("Mail");
		lblMail.setBounds(22, 143, 70, 15);
		frame.getContentPane().add(lblMail);
		
		mailField = new JTextField();
		mailField.setBounds(22, 170, 176, 19);
		frame.getContentPane().add(mailField);
		mailField.setColumns(10);
		
		lblPassword = new JLabel("Mot de passe");
		lblPassword.setBounds(22, 252, 176, 15);
		frame.getContentPane().add(lblPassword);
		
		lblPasswordConf = new JLabel("Confirmation");
		lblPasswordConf.setBounds(22, 310, 176, 15);
		frame.getContentPane().add(lblPasswordConf);
		
		ConfPasswordField = new JPasswordField();
		ConfPasswordField.setBounds(22, 337, 176, 19);
		frame.getContentPane().add(ConfPasswordField);
		passwordField = new JPasswordField();
		passwordField.setBounds(22, 279, 176, 19);
		frame.getContentPane().add(passwordField);
		
		
		JButton btnNewButton = new JButton("Sauvegarder");
		btnNewButton.setBounds(22, 363, 176, 25);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				UserManager userManager= new UserManager();
				try {
					user.setNom(nomField.getText());
					user.setPrenom(prenomField.getText());
					user.setEmail(mailField.getText());
					if(passwordField.isVisible()) {
						String password = String.valueOf(passwordField.getPassword());
						String confPassword = String.valueOf(ConfPasswordField.getPassword());
						if(password.equals(confPassword)) {
							user.setMdp(password);
							userManager.sauvegarder(user);
							PanelUser.actualiseTableau();
							frame.setVisible(false);
						}else {
							System.out.println("erreur mdp ");
						}
					}else {
						userManager.sauvegarder(user);
						PanelUser.actualiseTableau();
						frame.setVisible(false);
					}

				} catch (SQLException e) {
					System.out.println("Erreur");
					//e.printStackTrace();
				} catch (NumberFormatException e) {
					//e.printStackTrace();
					System.out.println("Erreur format");
				}

			}
		}
		);
		frame.getContentPane().add(btnNewButton);
	}

}
