package view.Profil_stock;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;

public class Accueil_stock {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Accueil_stock window = new Accueil_stock();
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
	public Accueil_stock() {
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
		
		JLabel lblNewLabel = new JLabel("Profil Gestionnaire de stock");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel.setBounds(84, 20, 200, 29);
		frame.getContentPane().add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Demande de fourniture");
		btnNewButton.setBounds(121, 59, 149, 21);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnDemandeDeFourniture = new JButton("Fiche fournitures");
		btnDemandeDeFourniture.setBounds(121, 90, 149, 21);
		frame.getContentPane().add(btnDemandeDeFourniture);
		
		JButton btnAfficherLeStock = new JButton("Afficher le stock");
		btnAfficherLeStock.setBounds(121, 127, 149, 21);
		frame.getContentPane().add(btnAfficherLeStock);
		
		JButton btnParametreDuCompte = new JButton("Parametre du compte");
		btnParametreDuCompte.setBounds(121, 170, 149, 21);
		frame.getContentPane().add(btnParametreDuCompte);
		
		JButton btnSeDeconecter = new JButton("Se deconnecter");
		btnSeDeconecter.setBounds(121, 209, 149, 21);
		frame.getContentPane().add(btnSeDeconecter);
	}

}
