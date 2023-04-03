package model;

import java.sql.Date;
import java.sql.Timestamp;

public class Etudiant {
	private int id;
	private String nom;
	private String prenom;
	private String dernier_diplome;
	private String email;
	private int telephone;
	private String adresse;
	private Timestamp date_verif;
	private int isDossierInscription;
	private int id_secretaire;
	


	public Etudiant(int idEtudiant, String nom, String prenom, String dernier_diplome, String email, int telephone, String adresse) {
		super();
		this.id = idEtudiant;
		this.nom = nom;
		this.prenom = prenom;
		this.dernier_diplome = dernier_diplome;
		this.email = email;
		this.telephone  = telephone;
		this.adresse = adresse;

	}
	
	public Etudiant() {}
	

	public int getIdEtudiant() {
		return id;
	}
	public void setIdEtudiant(int idEtudiant) {
		this.id = idEtudiant;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getDernier_diplome() {
		return dernier_diplome;
	}

	public void setDernier_diplome(String dernier_diplome) {
		this.dernier_diplome = dernier_diplome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getTelephone() {
		return telephone;
	}

	public void setTelephone(int telephone) {
		this.telephone = telephone;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
	
	public Timestamp getDate_verif() {
		return date_verif;
		}

	public void setDate_verif(Timestamp date_verif) {
		   this.date_verif = date_verif; 
		}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}
	
	@Override
	public String toString() {
	    return nom + " " + prenom;
	}

	public int getIsDossierInscription() {
		return isDossierInscription;
	}

	public void setIsDossierInscription(int isDossierInscription) {
		this.isDossierInscription = isDossierInscription;
	}

	public int getId_secretaire() {
		return id_secretaire;
	}

	public void setId_secretaire(int id_secretaire) {
		this.id_secretaire = id_secretaire;
	}


	

	
	
}
