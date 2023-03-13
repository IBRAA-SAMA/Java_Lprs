package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
	private static String url = "jdbc:mysql://localhost/java_lprs?serverTimezone=UTC";
	private static String user = "root";
	private static String password = "";
	
	public Connection getConnection() {
		
		try {
			Connection cnx = DriverManager.getConnection(this.url,this.user,this.password);
			//System.out.print("Etat de la connexion :");
			//System.out.print(cnx.isClosed()?"fermée":"ouverte \r\n");
			return cnx;
			
		} catch (SQLException e) {
			System.out.print("erreur");
			e.printStackTrace();
			return null;
		}
	}
}
