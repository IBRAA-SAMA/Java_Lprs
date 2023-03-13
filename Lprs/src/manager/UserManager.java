package manager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;

import database.Database;
import model.User;

public class UserManager {
	private Database coBdd;
	private String table = "utilisateur";


	public UserManager(Connection connection) {
		coBdd = new Database();
	}

	public User Inscription_admin(User user, String nom, String prenom, String email, String mdp) throws SQLException {
	    String sql = "INSERT INTO `"+table+"`( `nom`, `prenom`, `email`, `mdp`,`data_verif`,`role`,`reset_mdp`) VALUES (?,?,?,md5(?),?,0,1)";
	    Timestamp date_verif = new Timestamp(System.currentTimeMillis());
	    try (PreparedStatement pstm = coBdd.getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
	        pstm.setString(1, nom);
	        pstm.setString(2, prenom);
	        pstm.setString(3, email);
	        pstm.setString(4, mdp);
	        pstm.setTimestamp(5, date_verif);
	        pstm.executeUpdate();
	        try (ResultSet rs = pstm.getGeneratedKeys()) {
	            if (rs.next()) {
	                int lastInsertedId = rs.getInt(1);
	                user.setIdUser(lastInsertedId);
	                user.setData_verif(date_verif);
	            }
	        }
	    }
	    return user;
	}





	public ArrayList<User> getUsers() {
		ArrayList<User> users = new ArrayList<User>();
		User user;
		String sql = "SELECT * FROM "+table;
		PreparedStatement pstm;
		try {
			pstm = coBdd.getConnection().prepareStatement(sql);
			ResultSet rs = pstm.executeQuery();
			while (rs.next()) {
				user = new User(rs.getInt("id_user"), rs.getString("nom"), rs.getString("prenom"), rs.getString("mail"), rs.getString("login"), rs.getString("mdp"));
				users.add(user);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return users;
	}
	public User getUser(int idUser) {
		User user = null;
		String sql = "SELECT * FROM "+table+ " WHERE id_user=?";
		PreparedStatement pstm;
		try {
			pstm = coBdd.getConnection().prepareStatement(sql);
			pstm.setInt(1, idUser);
			ResultSet rs = pstm.executeQuery();
			if (rs.next()) {

				user = new User(rs.getInt("id_user"), rs.getString("nom"), rs.getString("prenom"), rs.getString("mail"), rs.getString("login"), rs.getString("mdp"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return user;
	}

	public User connexion(String login, String motDePasse) {
		User user = null;
		String sql = "SELECT * FROM "+table+ " WHERE email=? and mdp=?";
		PreparedStatement pstm;
		try {
			pstm = coBdd.getConnection().prepareStatement(sql);
			pstm.setString(1, login);
			pstm.setString(2, motDePasse);
			ResultSet rs = pstm.executeQuery();
			if (rs.next()) {	
				user = new User(rs.getInt("id"), rs.getString("nom"), rs.getString("prenom"), rs.getString("email"), rs.getString("mdp"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return user;

	}
	public User Inscription_Admin (String nom, String prenom, String email, String Motdepasse) {
		return null;
	}{

		String sql = "INSER INTO `"+table+"`( `nom`, `prenom`, `mail`, `login`, `mdp`) VALUES (?,?,?,?,md5(?))";

	}	
}
