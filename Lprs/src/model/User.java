package model;

import java.sql.Timestamp;

public class User {
	private static int id;
	private String nom;
	private String prenom;
	private String email;
	private String mdp;
	private String mdp2;
	private int role;
	private Timestamp date_verif;
	

	public User(int idUser, String nom, String prenom, String email, String mdp, int role) {
		super();
		this.id = idUser;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.mdp = mdp;
		this.role = role;

	}
	
	public User(int idUser) {
		super();
		this.id = idUser;
	}

	
	public User() {}
	
	public static int getIdUser() {
		return id;
	}
	public void setIdUser(int idUser) {
		this.id = idUser;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getMdp() {
		return mdp;
	}
	public void setMdp(String mdp) {
		this.mdp = mdp;
	}
	
	public int getRole() {
		return role;
	}

	public void setRole(int role) {
		this.role = role;
	}
	
	public String getMdp2() {
		return mdp2;
	}
	
	public Timestamp getDate_verif() {
		return date_verif;
		}


	public void setMdp2(String mdp2) {
		this.mdp2 = mdp2;
	}

	public void setDate_verif(Timestamp date_verif) {
		   this.date_verif = date_verif; 
		}

	public int getSessionId() {
		return this.id;
	}

	

	
	
}
