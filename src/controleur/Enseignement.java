package controleur;
 
public class Enseignement {
	private int idenseignement;
	 private String matiere, nbheure, coeff, idclasse, idprofesseur;
	public Enseignement(int idenseignement, String matiere, String nbheure, String coeff, String idclasse, String idprofesseur) {
		super();
		this.idenseignement = idenseignement;
		this.matiere = matiere;
		this.nbheure = nbheure;
		this.coeff = coeff;
		this.idclasse = idclasse;
		this.idprofesseur = idprofesseur;
	}
	public Enseignement(String matiere, String nbheure, String coeff, String idclasse, String idprofesseur) {
		super();
		this.idenseignement = 0;
		this.matiere = matiere;
		this.nbheure = nbheure;
		this.coeff = coeff;
		this.idclasse = idclasse;
		this.idprofesseur = idprofesseur;
	
}
	public int getidenseignement() {
		return idenseignement;
	}
	public void setIdenseignement(int idenseignement) {
		this.idenseignement = idenseignement;
	}
	public String getmatiere() {
		return matiere;
	}
	public void setmatiere(String matiere) {
		this.matiere = matiere;
	}
	public String getnbheure() {
		return nbheure;
	}
	public void setnbheure(String nbheure) {
		this.nbheure = nbheure;
	}
	public String getcoeff() {
		return coeff;
	}
	public void setcoeff(String coeff) {
		this.coeff = coeff;
	}
	public String getidclasse() {
		return idclasse;
	}
	public void setidclasse(String idclasse) {
		this.idclasse = idclasse;
	}
	public String getidprofesseur() {
		return idprofesseur;
	}
	public void setidprofesseur(String idprofesseur) {
		this.idprofesseur = idprofesseur;
	}
	
	
}