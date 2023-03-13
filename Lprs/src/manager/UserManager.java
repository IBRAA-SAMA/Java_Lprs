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
		// Création de la variable date_verif qui contient la date du jour
		Timestamp date_verif = new Timestamp(System.currentTimeMillis());
		// Requête SQL avec la variable date_verif pour enregistrer la date du jour lors de l'inscription
		String sql = "INSERT INTO "+table+"( nom, prenom, email, mdp,date_verif,role,reset_mdp) VALUES (?,?,?,md5(?),?,0,1)";
		try (PreparedStatement pstm = coBdd.getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
			pstm.setString(1, nom);
			pstm.setString(2, prenom);
			pstm.setString(3, email);
			pstm.setString(4, mdp);
			// Définition de la variable date_verif comme cinquième paramètre de la méthode setString()
			pstm.setTimestamp(5, date_verif);
			pstm.executeUpdate();
			try (ResultSet rs = pstm.getGeneratedKeys()) {
				if (rs.next()) {
					int lastInsertedId = rs.getInt(1);
					user.setIdUser(lastInsertedId);
					// Définition de la valeur de la variable date_verif pour l'objet User
					user.setDate_verif(date_verif);
				}
			}
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
