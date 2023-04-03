package model;

import java.sql.Date;
import java.sql.Timestamp;

public class Dossier_ins {

	private int id;
	private int id_fiche_etudiant;
	private String nom_prenom;
	private Timestamp date;
	private String filiere;
	private String motivation;
	private int id_secretaire;
	
	public Dossier_ins(int id, int id_fiche_etudiant, String nom_prenom, Timestamp date, String filiere, String motivation, int id_secretaire) {
		this.id = id;
		this.id_fiche_etudiant = id_fiche_etudiant;
		this.nom_prenom = nom_prenom;
		this.date = date;
		this.filiere = filiere;
		this.motivation = motivation;
		this.id_secretaire = id_secretaire;
		}
	public Dossier_ins() {
	}

	
	
	public Dossier_ins(int i, int j, String string, Date date2, String string2, String string3, String string4) {}
	
	public Dossier_ins(int int1, int int2, String string, Date date2, String string2, String string3,
			int idSecretaire) {
		// TODO Auto-generated constructor stub
	}
	public String getNom_prenom() {
		return nom_prenom;
	}

	public void setNom_prenom(String nom_prenom) {
		this.nom_prenom = nom_prenom;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId_fiche_etudiant() {
		return id_fiche_etudiant;
	}

	public void setId_fiche_etudiant(int id_fiche_etudiant) {
		this.id_fiche_etudiant = id_fiche_etudiant;
	}

	public Timestamp getDate() {
		return date;
	}

	public void setDate(Timestamp date) {
		this.date = date;
	}

	public String getFiliere() {
		return filiere;
	}

	public void setFiliere(String filiere) {
		this.filiere = filiere;
	}

	public String getMotivation() {
		return motivation;
	}

	public void setMotivation(String motivation) {
		this.motivation = motivation;
	}

	public int getId_secretaire() {
		return id_secretaire;
	}

	public void setId_secretaire(int id_secretaire) {
		this.id_secretaire = id_secretaire;
	}
	
	@Override
	public String toString() {
	return this.getNom_prenom();
	}

	
	
	
	
}
