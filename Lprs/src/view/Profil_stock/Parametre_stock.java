package view.Profil_stock;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;

public class Parametre_stock {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Parametre_stock window = new Parametre_stock();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Parametre_stock() {
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
		
		JLabel lblNewLabel = new JLabel("Parametre Administrateur");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel.setBounds(116, 20, 192, 27);
		frame.getContentPane().add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Modifier les donn\u00E9es");
		btnNewButton.setBounds(141, 57, 140, 21);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnRinitialiserMotDe = new JButton("R\u00E9initialiser mot de passe");
		btnRinitialiserMotDe.setBounds(128, 102, 167, 21);
		frame.getContentPane().add(btnRinitialiserMotDe);
		
		JButton btnRetour = new JButton("Retour");
		btnRetour.setBounds(161, 153, 109, 21);
		frame.getContentPane().add(btnRetour);
	}

}
