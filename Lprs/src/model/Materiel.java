package model;

import java.sql.Timestamp;

public class Materiel {

	 private int id;
	 private String nom_materiel;
	 private int nombre;
	 private String entreprise;
	 private String prix;
	private Timestamp date_verif;
	
	
	public Materiel(int id, String nom_materiel, int nombre, String entreprise, String prix, Timestamp date_verif) {
        this.id = id;
        this.nom_materiel = nom_materiel;
        this.nombre = nombre;
        this.entreprise = entreprise;
        this.prix = prix;
        this.date_verif = date_verif;
    }
	
	 
	 
	 
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNom_materiel() {
		return nom_materiel;
	}
	public void setNom_materiel(String nom_materiel) {
		this.nom_materiel = nom_materiel;
	}
	
	public int getNombre() {
		return nombre;
	}




	public void setNombre(int nombre) {
		this.nombre = nombre;
	}




	public String getPrix() {
		return prix;
	}




	public void setPrix(String prix) {
		this.prix = prix;
	}




	public Timestamp getDate_verif() {
		return date_verif;
	}




	public void setDate_verif(Timestamp date_verif) {
		this.date_verif = date_verif;
	}




	public String getEntreprise() {
		return entreprise;
	}
	public void setEntreprise(String entreprise) {
		this.entreprise = entreprise;
	}
	
	 
	 
	 
	 
	 
	 
	 
	
}
