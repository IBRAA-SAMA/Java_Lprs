package manager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import org.mindrot.jbcrypt.BCrypt;


import database.Database;
import model.User;
import view.Profil_secretaire.DossierInscription;
import model.Dossier_ins;
import model.Etudiant;
import model.Session;

public class UserManager{
	private Database coBdd;
	private String table = "utilisateur";
	private static JFrame frame;


	public UserManager() {
		coBdd = new Database();
	}

	public User sauvegarder(User user) throws SQLException {
		String sql;
		PreparedStatement pstm;
		//Update
		if(user.getIdUser()>0) {
			sql = "UPDATE `"+table+"` SET `nom`=?,`prenom`=?,`email`=? WHERE id = ?";
			pstm = coBdd.getConnection().prepareStatement(sql);
			pstm.setString(1, user.getNom());
			pstm.setString(2, user.getPrenom());
			pstm.setString(3, user.getEmail());
			pstm.setInt(4, user.getIdUser());
			pstm.executeUpdate();

		}
		//insert
		else {
			sql = "INSERT INTO `"+table+"`( `nom`, `prenom`, `email`, `mdp`) VALUES (?,?,?,md5(?))";

			pstm = coBdd.getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			pstm.setString(1, user.getNom());
			pstm.setString(2, user.getPrenom());
			pstm.setString(3, user.getEmail());
			pstm.setString(4, user.getMdp());

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

	public User Inscription_admin(User user, String nom, String prenom, String email, String mdp) throws SQLException {

		if (mdp.length() < 8) {
			String message = "Le mot de passe doit contenir au minimum 8 caractères";
			JOptionPane.showMessageDialog(null, message, "Erreur", JOptionPane.ERROR_MESSAGE);
			throw new IllegalArgumentException(message);
		}else {


			Timestamp date_verif = new Timestamp(System.currentTimeMillis());
			// Requête SQL avec la variable date_verif pour enregistrer la date du jour lors de l'inscription
			String sql = "INSERT INTO "+table+"( nom, prenom, email, mdp,date_verif,role,reset_mdp) VALUES (?,?,?,?,?,0,1)";
			try (PreparedStatement pstm = coBdd.getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
				// Vérifie que l'adresse email est valide avant de l'enregistrer
				if (!isValidEmail(email)) {
					System.out.println("Adresse email invalide");
					return null;
				}
				pstm.setString(1, nom);
				pstm.setString(2, prenom);
				pstm.setString(3, email);
				// Utilisation de la méthode hashPassword() pour hacher le mot de passe avant de l'enregistrer
				pstm.setString(4, hashPassword(mdp));
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
			} catch (SQLException e) {
				System.out.println("Erreur lors de l'inscription : " + e.getMessage());
				return null;
			}
		}
		return user;
	}



	public User Inscription_stock(User user, String nom, String prenom, String email, String mdp) throws SQLException {
		if (mdp.length() < 8) {
			String message = "Le mot de passe doit contenir au minimum 8 caractères";
			JOptionPane.showMessageDialog(null, message, "Erreur", JOptionPane.ERROR_MESSAGE);
			throw new IllegalArgumentException(message);
		}else {
			Timestamp date_verif = new Timestamp(System.currentTimeMillis());
			// Requête SQL avec la variable date_verif pour enregistrer la date du jour lors de l'inscription
			String sql = "INSERT INTO "+table+"( nom, prenom, email, mdp,date_verif,role,reset_mdp) VALUES (?,?,?,?,?,2,1)";
			try (PreparedStatement pstm = coBdd.getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
				// Vérifie que l'adresse email est valide avant de l'enregistrer
				if (!isValidEmail(email)) {
					System.out.println("Adresse email invalide");
					return null;
				}
				pstm.setString(1, nom);
				pstm.setString(2, prenom);
				pstm.setString(3, email);
				// Utilisation de la méthode hashPassword() pour hacher le mot de passe avant de l'enregistrer
				pstm.setString(4, hashPassword(mdp));
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
			} catch (SQLException e) {
				System.out.println("Erreur lors de l'inscription : " + e.getMessage());
				return null;
			}
		}
		return user;
	}

	public Etudiant fiche_etudiant(Etudiant etudiant, String nom, String prenom, String dernier_diplome, String email, String telephone, String adresse, int idSession) throws SQLException {
		// Création de la variable date_verif qui contient la date du jour
		Timestamp date_verif = new Timestamp(System.currentTimeMillis());

		// Récupération de l'utilisateur secrétaire correspondant à l'ID de session
		User user = getUser(idSession);
		if(user == null) {
			throw new IllegalArgumentException("ID de session invalide");
		}else {

			// Requête SQL avec la variable date_verif pour enregistrer la date du jour lors de l'inscription
			String sql = "INSERT INTO fiche_etudiant (nom, prenom, dernier_diplome, email, telephone, adresse, date_verif, id_Secretaire, dossier_inscription) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

			try (PreparedStatement pstm = coBdd.getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
				// Vérification que l'adresse email est valide avant de l'enregistrer
				if (!isValidEmail(email)) {
					JOptionPane.showMessageDialog(null, "Adresse email invalide");
					return null;

				}else {

					pstm.setString(1, nom);
					pstm.setString(2, prenom);
					pstm.setString(3, dernier_diplome);
					pstm.setString(4, email);
					pstm.setString(5, telephone);
					pstm.setString(6, adresse);
					pstm.setTimestamp(7, date_verif);
					pstm.setInt(8, user.getIdUser());
					pstm.setInt(9, 0);
					pstm.executeUpdate();

					JOptionPane.showMessageDialog(frame, "Les données de l'étudiant ont été enregistrées avec succès !");

					try (ResultSet rs = pstm.getGeneratedKeys()) {
						if (rs.next()) {
							int lastInsertedId = rs.getInt(1);
							etudiant.setIdEtudiant(lastInsertedId);
							// Définition de la valeur de la variable date_verif pour l'objet Etudiant
							etudiant.setDate_verif(date_verif);
						}
					}
				}
			}
		}
		return etudiant;
	}








	public User Inscription_prof(User user, String nom, String prenom, String email, String mdp) throws SQLException {

		if (mdp.length() < 8) {
			String message = "Le mot de passe doit contenir au minimum 8 caractères";
			JOptionPane.showMessageDialog(null, message, "Erreur", JOptionPane.ERROR_MESSAGE);
			throw new IllegalArgumentException(message);
		}else {

			// Création de la variable date_verif qui contient la date du jour
			Timestamp date_verif = new Timestamp(System.currentTimeMillis());
			// Requête SQL avec la variable date_verif pour enregistrer la date du jour lors de l'inscription
			String sql = "INSERT INTO "+table+"( nom, prenom, email, mdp,date_verif,role,reset_mdp) VALUES (?,?,?,?,?,1,1)";
			try (PreparedStatement pstm = coBdd.getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
				// Vérifie que l'adresse email est valide avant de l'enregistrer
				if (!isValidEmail(email)) {
					throw new IllegalArgumentException("Adresse email invalide");
				}
				pstm.setString(1, nom);
				pstm.setString(2, prenom);
				pstm.setString(3, email);
				// Utilisation de la méthode hashPassword() pour hacher le mot de passe avant de l'enregistrer
				pstm.setString(4, hashPassword(mdp));
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
		}
		return user;
	}

	public User Inscription_secretaire(User user, String nom, String prenom, String email, String mdp) throws SQLException {

		if (mdp.length() < 8) {
			String message = "Le mot de passe doit contenir au minimum 8 caractères";
			JOptionPane.showMessageDialog(null, message, "Erreur", JOptionPane.ERROR_MESSAGE);
			throw new IllegalArgumentException(message);
		}else {

			// Création de la variable date_verif qui contient la date du jour
			Timestamp date_verif = new Timestamp(System.currentTimeMillis());
			// Requête SQL avec la variable date_verif pour enregistrer la date du jour lors de l'inscription
			String sql = "INSERT INTO "+table+"( nom, prenom, email, mdp,date_verif,role,reset_mdp) VALUES (?,?,?,?,?,3,1)";
			try (PreparedStatement pstm = coBdd.getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
				// Vérifie que l'adresse email est valide avant de l'enregistrer
				if (!isValidEmail(email)) {
					System.out.println("Adresse email invalide");
				}
				pstm.setString(1, nom);
				pstm.setString(2, prenom);
				pstm.setString(3, email);
				// Utilisation de la méthode hashPassword() pour hacher le mot de passe avant de l'enregistrer
				pstm.setString(4, hashPassword(mdp));
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
		}
		return user;
	}


	public User Connexion(String login, String motDePasse) {
		User user = null;
		String sql = "SELECT * FROM "+table+ " WHERE email=?";
		PreparedStatement pstm;
		try {
			pstm = coBdd.getConnection().prepareStatement(sql);
			pstm.setString(1, login);
			ResultSet rs = pstm.executeQuery();
			if (rs.next()) {
				String hashedPassword = rs.getString("mdp");
				if (checkPassword(motDePasse, hashedPassword)) {
					int role = rs.getInt("role");
					user = new User(rs.getInt("id"), rs.getString("nom"), rs.getString("prenom"), rs.getString("email"), hashedPassword, role);
				} else {
					JOptionPane.showMessageDialog(null, "Erreur dans le mail ou le mot de passe incorrect", "Erreur", JOptionPane.ERROR_MESSAGE);
				}
			} else {
				JOptionPane.showMessageDialog(null, "Erreur dans le mail ou le mot de passe incorrect", "Erreur", JOptionPane.ERROR_MESSAGE);
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Une erreur est survenue lors de la connexion : " + e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
		return user;
	}




	private boolean checkPassword(String motDePasse, String hashedPassword) {
		// Utilisation de la fonction BCrypt.checkpw() pour vérifier si le mot de passe fourni correspond au mot de passe haché stocké dans la base de données
		return BCrypt.checkpw(motDePasse, hashedPassword);
	}

	public User Inscription_Admin (String nom, String prenom, String email, String Motdepasse) {
		return null;
	}{

		String sql = "INSER INTO `"+table+"`( `nom`, `prenom`, `email`, `mdp`) VALUES (?,?,?,md5(?))";

	}	

	public User getUser(int idUser) {
		User user = null;
		String sql = "SELECT * FROM "+table+ " WHERE id=?";
		PreparedStatement pstm;
		try {
			pstm = coBdd.getConnection().prepareStatement(sql);
			pstm.setInt(1, idUser);
			ResultSet rs = pstm.executeQuery();
			if (rs.next()) {

				user = new User(rs.getInt("id"), rs.getString("nom"), rs.getString("prenom"), rs.getString("email"), rs.getString("mdp"), idUser);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return user;
	}

	public Dossier_ins getDossier(int idSecretaire) {
		Dossier_ins dossier_ins = null;
		String sql = "SELECT * FROM dossier_ins WHERE id=?";
		PreparedStatement pstm;
		try {
			pstm = coBdd.getConnection().prepareStatement(sql);
			pstm.setInt(1, idSecretaire);
			ResultSet rs = pstm.executeQuery();
			if (rs.next()) {

				dossier_ins = new Dossier_ins(rs.getInt("id"), rs.getInt("id_fiche_etudiant"), rs.getString("nom_prenom"), rs.getDate("date"), rs.getString("filiere"),  rs.getString("motivation"), idSecretaire);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return dossier_ins;
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
				user = new User(rs.getInt("id"), rs.getString("nom"), rs.getString("prenom"), rs.getString("email"), rs.getString("mdp"), 0);
				users.add(user);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return users;
	}


	private boolean isValidEmail(String email) {
		// Utilisation d'une expression régulière pour vérifier si l'adresse email est valide
		String regex = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(email);
		return matcher.matches();
	}


	// Hache un mot de passe en utilisant l'algorithme de hachage bcrypt
	private String hashPassword(String password) {
		// Génère un sel aléatoire pour le hachage bcrypt
		String salt = BCrypt.gensalt();
		// Hache le mot de passe en utilisant l'algorithme bcrypt avec le sel généré
		String hashedPassword = BCrypt.hashpw(password, salt);
		return hashedPassword; // Retourne le mot de passe haché
	}

	private User currentUser; // l'utilisateur actuellement connecté

	public int getIdUtilisateurConnecte() {
		if (currentUser == null) {
			return 0; // ou une autre valeur par défaut
		}
		return currentUser.getIdUser();
	}

	public User getCurrentUser() throws SQLException {
		int idUtilisateur = getIdUtilisateurConnecte();
		Database database = new Database();
		Connection connection = database.getConnection();
		PreparedStatement statement = connection.prepareStatement("SELECT * FROM "+table+" WHERE id = ?");
		statement.setInt(1, idUtilisateur);
		ResultSet result = statement.executeQuery();
		if (result.next()) {
			int id = result.getInt("id");
			String nom = result.getString("nom");
			String prenom = result.getString("prenom");
			String email = result.getString("email");
			String mdp = result.getString("mdp");
			int role = result.getInt("role");
			User user = new User(id, nom, prenom, email, mdp, role);
			return user;
		}
		return null;
	}

	public static User getConnectedUser() {
		// récupérer l'identifiant de l'utilisateur connecté depuis la session
		int userId = (int) Session.getAttribute("userId");

		// exécuter la requête SQL pour récupérer les informations de l'utilisateur depuis la base de données
		String query = "SELECT * FROM utilisateur WHERE id = ?";
		PreparedStatement stmt = null;
		ResultSet rs = null;
		User user = null;
		Database database = new Database();


		try (		Connection connection = database.getConnection()) {
			stmt = connection.prepareStatement(query);
			stmt.setInt(1, userId);
			rs = stmt.executeQuery();

			// créer un objet User avec les informations récupérées depuis la base de données
			if (rs.next()) {
				user = new User();
				user.setIdUser(rs.getInt("id"));
				user.setNom(rs.getString("nom"));
				user.setPrenom(rs.getString("prenom"));
				user.setEmail(rs.getString("email"));

			}
		} catch (SQLException e) {
			// gérer les exceptions
		} finally {
			// fermer les ressources
			try {
				if (rs != null) rs.close();
				if (stmt != null) stmt.close();
			} catch (SQLException e) {
				// gérer les exceptions
			}
		}

		return user;
	}

	public List<Etudiant> getNomsPrenomsFichesEtudiant() {
		List<Etudiant> fichesEtudiant = new ArrayList<>();
		Database database = new Database();
		Connection connection = database.getConnection();
		try {
			PreparedStatement statement = connection.prepareStatement("SELECT id, nom, prenom FROM fiche_etudiant");
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				String nom = resultSet.getString("nom");
				String prenom = resultSet.getString("prenom");
				Etudiant etudiant = new Etudiant();
				etudiant.setIdEtudiant(id);
				etudiant.setNom(nom);
				etudiant.setPrenom(prenom);
				fichesEtudiant.add(etudiant);
			}
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return fichesEtudiant;
	}

	public List<Dossier_ins> getNomsPrenomsDossierEtudiant() {
		List<Dossier_ins> listeDossier_ins = new ArrayList<>();
		Database database = new Database();
		Connection connection = database.getConnection();
		try {
			PreparedStatement statement = connection.prepareStatement("SELECT id, nom_prenom FROM dossier_ins");
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				String nom_prenom = resultSet.getString("nom_prenom");
				Dossier_ins dossier_ins = new Dossier_ins();
				dossier_ins.setId(id);
				dossier_ins.setNom_prenom(nom_prenom);
				listeDossier_ins.add(dossier_ins);

			}
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listeDossier_ins;
	}




	public boolean ajouterDossierInscription(int id_fiche_etudiant, String nom_prenom, String filiere, String motivation, int idSession) {
		Timestamp date = new Timestamp(System.currentTimeMillis());

		User user = getUser(idSession);
		if(user == null) {
			throw new IllegalArgumentException("ID de session invalide");
		}else {
			boolean succes = false;
			try {
				Database database = new Database();
				Connection connection = database.getConnection();
				PreparedStatement statement = connection.prepareStatement("INSERT INTO dossier_ins (id_fiche_etudiant, nom_prenom, date, filiere, motivation, id_secretaire) VALUES (?, ?, ?, ?, ?, ?)");

				statement.setInt(1, id_fiche_etudiant);
				statement.setString(2, nom_prenom);
				statement.setTimestamp(3, date);
				statement.setString(4, filiere);
				statement.setString(5, motivation);
				statement.setInt(6, user.getIdUser());
				int rowsInserted = statement.executeUpdate();
				if (rowsInserted > 0) {
					succes = true;
				}
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
			return succes;
		}
	}

	public static List<Dossier_ins> listerEtudiants() {
		List<Dossier_ins> etudiants = new ArrayList<Dossier_ins>();

		try {
			Database database = new Database();
			Connection connection = database.getConnection();
			PreparedStatement statement = connection.prepareStatement("SELECT * FROM Dossier_ins");

			ResultSet rs = statement.executeQuery();

			while (rs.next()) {
				Dossier_ins etudiant = new Dossier_ins(
						rs.getInt("id"),
						rs.getInt("id_fiche_etudiant"),
						rs.getString("nom_prenom"),
						rs.getDate("date"),
						rs.getString("filiere"),
						rs.getString("motivation"),
						rs.getString("id_secretaire")
						);
				etudiants.add(etudiant);
			}

			rs.close();
			statement.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return etudiants;
	}

	public boolean priseEnCharge(int idDossierIns, int idSession) {
		User user = getUser(idSession);
		Dossier_ins dossier_ins = getDossier(idDossierIns);
		if(user == null) {
			throw new IllegalArgumentException("ID de session invalide");
		}else {

			boolean isSuccess = false;
			try {
				// Établir une connexion à la base de données
				Database database = new Database();
				Connection connection = database.getConnection();			// Préparer la requête SQL pour insérer les données dans la table "prise_en_charge"
				String query = "INSERT INTO prise_en_charge (id_dossier_ins, id_utilisateur) VALUES (?, ?)";
				PreparedStatement preparedStatement = connection.prepareStatement(query);

				// Assigner les valeurs des paramètres
				preparedStatement.setInt(1, dossier_ins.getId());
				preparedStatement.setInt(2, user.getIdUser());

				// Exécuter la requête et récupérer le nombre de lignes affectées
				int rowsAffected = preparedStatement.executeUpdate();

				// Vérifier si l'insertion a réussi
				if (rowsAffected > 0) {
					isSuccess = true;
				}

				// Fermer les ressources utilisées
				preparedStatement.close();
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return isSuccess;
		}
	}

	public void demandeFourniture(int idSession, String demandeFourniture) {
		// Récupération de l'utilisateur correspondant à l'ID de session
		User user = getUser(idSession);
		if(user == null) {
			throw new IllegalArgumentException("ID de session invalide");
		}
		// Enregistrement de la demande de fourniture dans la base de données
		String sql = "INSERT INTO demande_fourniture (id_professeur, demande, valider, date) VALUES (?, ?, ?, NOW())";
		try (PreparedStatement pstm = coBdd.getConnection().prepareStatement(sql)) {
			pstm.setInt(1, user.getIdUser());
			pstm.setString(2, demandeFourniture);
			pstm.setBoolean(3, false);
			pstm.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public ResultSet getDemandeFourniture() throws SQLException {

		Database database = new Database();
		Connection connection = database.getConnection();	
		// Requête SQL pour récupérer toutes les colonnes de la table demande_fourniture
		String sql = "SELECT * FROM demande_fourniture";

		// Création de l'objet Statement pour exécuter la requête
		Statement stmt = connection.createStatement();

		// Exécution de la requête et récupération du ResultSet contenant les résultats
		ResultSet rs = stmt.executeQuery(sql);

		// Retourne le ResultSet contenant toutes les données de la table demande_fourniture
		return rs;
	}

	public ResultSet getFicheFourniture() throws SQLException {

		Database database = new Database();
		Connection connection = database.getConnection();	
		// Requête SQL pour récupérer toutes les colonnes de la table demande_fourniture
		String sql = "SELECT * FROM fiche_fourniture";

		// Création de l'objet Statement pour exécuter la requête
		Statement stmt = connection.createStatement();

		// Exécution de la requête et récupération du ResultSet contenant les résultats
		ResultSet rs = stmt.executeQuery(sql);

		// Retourne le ResultSet contenant toutes les données de la table demande_fourniture
		return rs;
	}

	public ResultSet getUtilisateur() throws SQLException {

		Database database = new Database();
		Connection connection = database.getConnection();	
		// Requête SQL pour récupérer toutes les colonnes de la table demande_fourniture
		String sql = "SELECT * FROM utilisateur";

		// Création de l'objet Statement pour exécuter la requête
		Statement stmt = connection.createStatement();

		// Exécution de la requête et récupération du ResultSet contenant les résultats
		ResultSet rs = stmt.executeQuery(sql);

		// Retourne le ResultSet contenant toutes les données de la table demande_fourniture
		return rs;
	}

	public void supprimerDemandeFourniture(int idDemande) {
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			Database database = new Database();
			conn = database.getConnection();
			// Préparation de la requête de suppression
			String sql = "DELETE FROM demande_fourniture WHERE id = ?";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, idDemande);

			// Exécution de la requête de suppression
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// Fermeture des ressources
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

	public void validerDemandeFourniture(int idDemande) {
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			// Récupération de la connexion à la base de données
			Database database = new Database();
			conn = database.getConnection();	

			// Préparation de la requête de mise à jour
			String sql = "UPDATE demande_fourniture SET valider = 1 WHERE id = ?";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, idDemande);

			// Exécution de la requête de mise à jour
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// Fermeture des ressources
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


	public static void ajouter_fiche_fourniture(String materiel, int nombre, String entreprise, String adresse, int contact, int prix, int id_gestionnaire) throws SQLException {

		Timestamp date = new Timestamp(System.currentTimeMillis());

		UserManager userManager = new UserManager();
		User user = userManager.getUser(id_gestionnaire);

		if(user == null) {
			throw new IllegalArgumentException("ID de session invalide");
		}

		Connection connexion = null;
		PreparedStatement statement = null;
		try {
			connexion = new Database().getConnection();
			statement = connexion.prepareStatement("INSERT INTO fiche_fourniture (materiel, nombre, entreprise, adresse, contact, prix, id_gestionnaire, date) VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
			statement.setString(1, materiel);
			statement.setInt(2, nombre);
			statement.setString(3, entreprise);
			statement.setString(4, adresse);
			statement.setInt(5, contact);
			statement.setInt(6, prix);
			statement.setInt(7, user.getIdUser());
			statement.setTimestamp(8, date);
			statement.executeUpdate();
		} finally {
			if (statement != null) {
				statement.close();
			}
			if (connexion != null) {
				connexion.close();
			}
		}

	}


}
