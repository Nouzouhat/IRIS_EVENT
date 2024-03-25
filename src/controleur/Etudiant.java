package controleur;

public class Etudiant {
	private int idetudiant;
	private String nom, prenom, email, datenais, statut;
	private int idclasse;
	
	public Etudiant(int idetudiant, String nom, String prenom, String email, String datenais, String statut,
			int idclasse) {
		this.idetudiant = idetudiant;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.datenais = datenais;
		this.statut = statut;
		this.idclasse = idclasse;
	}
	
	public Etudiant(String nom, String prenom, String email, String datenais, String statut,
			int idclasse) {
		this.idetudiant = 0;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.datenais = datenais;
		this.statut = statut;
		this.idclasse = idclasse;
	}

	public int getIdetudiant() {
		return idetudiant;
	}

	public void setIdetudiant(int idetudiant) {
		this.idetudiant = idetudiant;
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

	public String getDatenais() {
		return datenais;
	}

	public void setDatenais(String datenais) {
		this.datenais = datenais;
	}

	public String getStatut() {
		return statut;
	}

	public void setStatut(String statut) {
		this.statut = statut;
	}

	public int getIdclasse() {
		return idclasse;
	}

	public void setIdclasse(int idclasse) {
		this.idclasse = idclasse;
	}
	
	
}
