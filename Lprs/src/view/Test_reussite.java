package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;

import model.User;

import java.awt.BorderLayout;

public class Test_reussite {

	private JFrame frame;
	private User utilisateurConnecte;


	/**
	 * Launch the application.
	 */
	public void run() {
		frame.setVisible(true);
		
	}
	/**
	 * Create the application.
	 */
	public Test_reussite(User user) {
		this.utilisateurConnecte = user;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("CEST REUSSIIIIIIIIIIIIIT");
		lblNewLabel.setBounds(173, 109, 109, 73);
		frame.getContentPane().add(lblNewLabel);
	}


	
}
