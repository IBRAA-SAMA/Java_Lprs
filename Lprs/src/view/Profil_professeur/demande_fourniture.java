package view.Profil_professeur;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;

import database.Database;
import manager.UserManager;
import model.Etudiant;
import model.Session;
import model.User;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.awt.event.ActionEvent;

public class demande_fourniture extends JFrame {

	private static JFrame frame;
	private JTextField textField;

	private static Session session;
	private User utilisateurConnecte;

	/**
	 * Create the application.
	 */
	public demande_fourniture(User user) {
		initialize();
		this.utilisateurConnecte = user;
	    initialize();
	    session = Session.getSessionExistante(utilisateurConnecte);
	}

	public static void run() {
	    frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		System.out.println("La variable de session vaut : " + Session.sessionId);

		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Demande de fourniture");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setBounds(117, 10, 228, 32);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Votre demande :");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(10, 86, 122, 32);
		frame.getContentPane().add(lblNewLabel_1);
		
		textField = new JTextField();
		textField.setBounds(142, 95, 228, 72);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("Envoyer");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String commentaire = textField.getText();

				if (commentaire.equals("")) {
				    JOptionPane.showMessageDialog(frame, "Veuillez remplir tous les champs !");
				} else {
				    // Récupérer l'id de la session
				    Database database = new Database();
				    Connection connection = database.getConnection();

				    User user = new User();
				    if (Session.sessionId != 0) {
				        session = Session.getSessionExistante(utilisateurConnecte);
				    } else {
				        System.out.println("pas de session détécté");
				    }
				                
				    if (session != null) {
				        int idSession = session.getSessionId();
				        UserManager userManager = new UserManager();
				        // Récupérer le contenu du textfield
				        String demandeFourniture = textField.getText();
				        // Appeler la méthode pour créer la demande de fourniture
				        userManager.demandeFourniture(idSession, demandeFourniture);
				        
				        // Afficher un message de confirmation
				        JOptionPane.showMessageDialog(frame, "La demande de fourniture a été créée avec succès !");
				    }
				}

	         
			}
		});
		btnNewButton.setBounds(285, 208, 85, 21);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Annuler");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
             frame.dispose();
			}
		});
		btnNewButton_1.setBounds(142, 208, 85, 21);
		frame.getContentPane().add(btnNewButton_1);
	}

}
