package view.Profil_secretaire;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Accueil_secretaire {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Accueil_secretaire window = new Accueil_secretaire();
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
	public Accueil_secretaire() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 539, 399);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnNewButton_1 = new JButton("Dossier inscription etudiant");
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton_1.setBounds(129, 71, 216, 23);
		frame.getContentPane().add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Creer Fiche étudiant");
		btnNewButton_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton_2.setBounds(139, 112, 200, 23);
		frame.getContentPane().add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("Se deconnecter");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton_3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton_3.setBounds(188, 328, 137, 23);
		frame.getContentPane().add(btnNewButton_3);
		
		JLabel lblNewLabel = new JLabel("Profil Secretaire");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel.setBounds(179, 24, 146, 23);
		frame.getContentPane().add(lblNewLabel);
		
		JButton btnNewButton_4 = new JButton("Afficher fiche etudiant");
		btnNewButton_4.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton_4.setBounds(139, 159, 200, 23);
		frame.getContentPane().add(btnNewButton_4);
		
		JButton btnNewButton = new JButton("Afficher dossier etudiant");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton.setBounds(139, 206, 200, 23);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_5 = new JButton("Parametre du compte");
		btnNewButton_5.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton_5.setBounds(165, 283, 174, 23);
		frame.getContentPane().add(btnNewButton_5);
	}

}
