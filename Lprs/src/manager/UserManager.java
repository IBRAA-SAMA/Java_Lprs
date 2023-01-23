package manager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import database.Database;
import model.User;

public class UserManager {
	private Database coBdd;
	private String table = "utilisateur";
	
	public UserManager() {
		coBdd = new Database();
	}
	
	public User sauvegarder(User user) throws SQLException {
		String sql;
		PreparedStatement pstm;
		//Update
		if(user.getIdUser()>0) {
			sql = "UPDATE `"+table+"` SET `nom`=?,`prenom`=?,`mail`=?,`login`=? WHERE id_user=?";
			pstm = coBdd.getConnection().prepareStatement(sql);
			pstm.setString(1, user.getNom());
			pstm.setString(2, user.getPrenom());
			pstm.setString(3, user.getMail());
			pstm.setString(4, user.getLogin());
			pstm.setInt(5, user.getIdUser());
			pstm.executeUpdate();
			
		}
		//insert
		else {
			sql = "INSERT INTO `"+table+"`( `nom`, `prenom`, `mail`, `login`, `mdp`) VALUES (?,?,?,?,md5(?))";
			
			pstm = coBdd.getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			pstm.setString(1, user.getNom());
			pstm.setString(2, user.getPrenom());
			pstm.setString(3, user.getMail());
			pstm.setString(4, user.getLogin());
			pstm.setString(5, user.getMdp());
			pstm.executeUpdate();
		    ResultSet rs = pstm.getGeneratedKeys();
            if(rs.next())
            {
                int last_inserted_id = rs.getInt(1);
                user.setIdUser(last_inserted_id);
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
				user = new User(rs.getInt("id"), rs.getString("nom"), rs.getString("prenom"), rs.getString("email"), rs.getString("mdp"), rs.getString("mdp"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return user;
		
	}
}
