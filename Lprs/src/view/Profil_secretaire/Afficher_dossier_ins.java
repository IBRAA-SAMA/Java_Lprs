package view.Profil_secretaire;

import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Afficher_dossier_ins {
	private static JFrame frame;
	private JTable table;
	private DefaultTableModel model;


	public Afficher_dossier_ins() {
	    initialize();
	    afficherDonnees();
	}

	private void initialize() {
	    frame = new JFrame();
	    frame.setBounds(100, 100, 800, 500);
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    
	    JPanel panel = new JPanel();
	    frame.getContentPane().add(panel);
	    
	    model = new DefaultTableModel();
	    panel.setLayout(null);
	    table = new JTable(model);
	    
	    JScrollPane scrollPane = new JScrollPane(table);
	    scrollPane.setBounds(167, 5, 452, 402);
	    panel.add(scrollPane);
	    
	    JButton btnNewButton = new JButton("Retour");
	    btnNewButton.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		frame.dispose();
	    	}
	    });
	    btnNewButton.setBounds(342, 417, 85, 21);
	    panel.add(btnNewButton);
	}

	private void afficherDonnees() {
	    Connection conn = null;
	    PreparedStatement stmt = null;
	    ResultSet rs = null;
	    
	    try {
	        // Connexion à la base de données
	        conn = DriverManager.getConnection("jdbc:mysql://localhost/java_lprs?serverTimezone=UTC", "root", "");
	        
	        // Requête pour récupérer toutes les données de la table dossier_ins
	        String query = "SELECT * FROM dossier_ins";
	        stmt = conn.prepareStatement(query);
	        rs = stmt.executeQuery();
	        
	        // Ajout des données dans le modèle de la table
	        int numCols = rs.getMetaData().getColumnCount();
	        for (int i = 1; i <= numCols; i++) {
	            model.addColumn(rs.getMetaData().getColumnName(i));
	        }
	        
	        while (rs.next()) {
	            Object[] rowData = new Object[numCols];
	            for (int i = 1; i <= numCols; i++) {
	                rowData[i-1] = rs.getObject(i);
	            }
	            model.addRow(rowData);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        // Fermeture des ressources
	        if (rs != null) {
	            try {
	                rs.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	        if (stmt != null) {
	            try {
	                stmt.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	        if (conn != null) {
	            try {
	                conn.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	    }
	}

	public static void run() {
	        frame.setVisible(true);
	    
	}
}