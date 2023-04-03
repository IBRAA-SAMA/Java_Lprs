package view.Profil_stock;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import manager.UserManager;
import model.Session;
import model.User;

public class Afficher_stock {

	private static JFrame frame;
	private JTable table;
	private DefaultTableModel model;
	private static Session session;

	private User utilisateurConnecte;

    /**
     * Create the application.
     */
    public Afficher_stock() {
        initialize();
    }
    
    public Afficher_stock(User user) {
		initialize();
		this.utilisateurConnecte = user;
		initialize();
		session = Session.getSessionExistante(utilisateurConnecte); // appel de la méthode getSessionExistante()
	}

	public static void run() {
		frame.setVisible(true);

	}

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
    	frame = new JFrame();
    	frame.setBounds(100, 100, 800, 600);
    	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    	model = new DefaultTableModel(new String[] { "id", "Materiel", "Nombre", "Entreprise", "Adresse", "Contact", "date" }, 0) {
    	    @Override
    	    public boolean isCellEditable(int row, int column) {
    	        return false;
    	    }
    	};

    	// Récupération des données de la table demande_fourniture
    	UserManager userManager = new UserManager();
    	ResultSet rs = null;
    	try {
    	    rs = userManager.getFicheFourniture();
    	} catch (SQLException e1) {
    	    e1.printStackTrace();
    	}

    	try {
    	    while (rs.next()) {
    	        int id = rs.getInt("id");
    	        String materiel = rs.getString("materiel");
    	        int nombre = rs.getInt("nombre");
    	        String entreprise = rs.getString("entreprise");
    	        String adresse = rs.getString("adresse");
    	        int contact = rs.getInt("contact");
    	        String date = rs.getString("date");

    	        model.addRow(new Object[] { id, materiel, nombre, entreprise, adresse, contact, date });
    	    }
    	} catch (SQLException e) {
    	    e.printStackTrace();
    	}

    	// Créer une JTable pour afficher les données de la table fourniture
    	table = new JTable();
    	table.setModel(model);

    	// Créer un JScrollPane pour permettre le défilement de la JTable
    	JScrollPane scrollPane = new JScrollPane();
    	scrollPane.setViewportView(table);
    	frame.getContentPane().add(scrollPane, BorderLayout.CENTER);

    	// Ajouter un bouton "Annuler"
    	JButton btnAnnuler = new JButton("Annuler");
    	btnAnnuler.addActionListener(new ActionListener() {
    	    public void actionPerformed(ActionEvent e) {
    	        frame.dispose(); // ferme la fenêtre
    	    }
    	});
    	frame.getContentPane().add(btnAnnuler, BorderLayout.SOUTH);
    }}
        
     