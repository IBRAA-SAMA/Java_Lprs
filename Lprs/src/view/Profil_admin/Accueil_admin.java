package view.Profil_admin;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;

public class Accueil_admin {

	private JFrame frame;

	/**
	 * Create the application.
	 */
	public Accueil_admin() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setFont(new Font("Tahoma", Font.PLAIN, 16));
		frame.setBounds(100, 100, 503, 372);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Profil admininstrateur");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel.setBounds(137, 25, 159, 25);
		frame.getContentPane().add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Afficher les utilisateur");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnNewButton.setBounds(137, 60, 152, 21);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Cr\u00E9er un nouvel utilisateur");
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnNewButton_1.setBounds(118, 103, 193, 25);
		frame.getContentPane().add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Se deconnecter");
		btnNewButton_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnNewButton_2.setBounds(137, 201, 152, 25);
		frame.getContentPane().add(btnNewButton_2);
		
		JButton btnNewButton_1_1 = new JButton("Parametre du compte");
		btnNewButton_1_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnNewButton_1_1.setBounds(118, 152, 193, 25);
		frame.getContentPane().add(btnNewButton_1_1);
	}

}
