package view.detail;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import manager.UserManager;
import model.User;

import view.Profil_admin.UserForm;

@SuppressWarnings("serial")
public class PanelUser extends JPanel  {
    private static String[] colMedHdr = { "id", "nom", "prenom", "email","mdp" };
    private static DefaultTableModel tblModel = new DefaultTableModel(colMedHdr, 0);
    private JTable table;
    private UserManager userManager = new UserManager();
    private static JFrame frame; 
    
	/**
	 * Create the panel.
	 */
	public PanelUser(User user) {

		setLayout(null);
		
		JLabel lblUser = new JLabel("User");
		lblUser.setHorizontalAlignment(SwingConstants.CENTER);
		lblUser.setBounds(0, 5, 700, 15);
		add(lblUser);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 36, 482, 318);
		add(scrollPane);
		
		table = new JTable(tblModel){
	         public boolean editCellAt(int row, int column, java.util.EventObject e) {
	             return false;
	         }
	       };
		table.setFocusable(false);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent me) {
	            if (me.getClickCount() == 2) {    
	                JTable target = (JTable)me.getSource();
	                int row = target.getSelectedRow(); // select a row
	                int idUser = (int) target.getValueAt(row, 0); // select a column
	                User userSel = userManager.getUser(idUser);
	                UserForm userForm = new UserForm(userSel);
	                userForm.run();
	             }
			}
		});
		table.setBounds(0, 0, 286, 219);
		scrollPane.setViewportView(table);
		
		JButton btnNewButton = new JButton("Annuler");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.dispose();
			}
		});
		btnNewButton.setBounds(529, 81, 117, 25);
		add(btnNewButton);
		populateTable();
	}
	
	private void populateTable() {
		
		ArrayList<User> users = userManager.getUsers();
		for (User user : users) {
			Object[] data = {user.getIdUser(),user.getNom(), user.getPrenom(), user.getEmail(),user.getMdp()};
			tblModel.addRow(data);
		}
		
	}
	public static void actualiseTableau() {
		tblModel.getDataVector().removeAllElements();
		
		UserManager userManager = new UserManager();
		ArrayList<User> users = userManager.getUsers();
		
		for (User user : users) {
			Object[] data = {user.getIdUser(),user.getNom(), user.getPrenom(), user.getEmail(),user.getMdp()};
			tblModel.addRow(data);
		}
		tblModel.fireTableDataChanged();
	}

	

}
