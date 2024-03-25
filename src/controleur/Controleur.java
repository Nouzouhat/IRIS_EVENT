package controleur;

import java.util.ArrayList;

import modele.Modele;

public class Controleur {
	public static User selectWhereUSer (String email, String mdp) {
		//tester email et la complexité mdp.
		return Modele.selectWhereUser(email, mdp);
	}
	
	/*********************gestion des classes ***************/
	public static void insertClasse (Classe uneClasse) {
		Modele.insertClasse(uneClasse);
	}
	
	public static void deleteClasse (int idClasse) {
		Modele.deleteClasse(idClasse);
	}
	
	public static void updateClasse (Classe uneClasse) {
		Modele.updateClasse(uneClasse);
	}
	
	public static ArrayList<Classe> selectAllClasses (String filtre) {
		return Modele.selectAllClasses(filtre);
	}
	
	public static Classe selectWhereClasse (int idClasse) {
		return Modele.selectWhereClasse(idClasse);
	}

	public static void updateuUser(User unUser) {
		Modele.updateuUser(unUser);
		
	}
	
	public static Classe selectWhereClasse (String nom, String salle) {
		return Modele.selectWhereClasse(nom, salle);
	}
	/************gestion des etudiant***********/

	public static void insertEtudiant( Etudiant unEtudiant) {
		Modele.insertEtudiant(unEtudiant);
	}
	public static void deleteEtudiant(int idEtudiant) {
		Modele.deleteEtudiant(idEtudiant);
	}
	public static void updateEtudiant(Etudiant unEtudiant) {
		Modele.updateEtudiant(unEtudiant);
	}

	public static ArrayList<Etudiant> selectAllEtudiants( String filtre) {
		return Modele.selectAllEtudiants(filtre);
	}
	public static Etudiant selectWhereEtudiant(int idEtudiant) {
		return Modele.selectWhereEtudiant(idEtudiant);
	}


	public static Etudiant selectWhereEtudiant (String nom, String salle, String email) {
		return Modele.selectWhereEtudiant(nom, salle, email);
	 }

	/*******************gestion des Professeurs*****************/

	public static void insertProfesseur( Professeur unProfesseur) {
		Modele.insertProfesseur(unProfesseur);
	}
	public static void deleteProfesseur(int idProfesseur) {
		Modele.deleteProfesseur(idProfesseur);
	}
	public static void updateProfesseur(Professeur unProfesseur) {
		Modele.updateProfesseur(unProfesseur);
	}

	public static ArrayList<Professeur> selectAllProfesseurs( String filtre) {
		return Modele.selectAllProfesseurs(filtre);
	}
	public static Professeur selectWhereProfesseur(int idProfesseur) {
		return Modele.selectWhereProfesseur(idProfesseur);
	}


	public static Professeur selectWhereProfesseur (String nom, String prenom) {
		return Modele.selectWhereProfesseur(nom, prenom);
	 }
	
	/************************Gestion enseignements******************/
	public static void insertEnseignement( Enseignement unEnseignement) {
		Modele.insertEnseignement(unEnseignement);
	}
	public static void deleteEnseignement( int idenseignement) {
		Modele.deleteEnseignement(idenseignement);
	}
	public static void updateEnseignement( Enseignement unEnseignement) {
		Modele.updateEnseignement(unEnseignement);
	}
	public static ArrayList<Enseignement> selectAllEnseignement( String filtre){
		return Modele.selectAllEnseignement(filtre);
	}
	public static Enseignement selectwhereEnseignement(int idEnseignement) {
		return Modele.selectwhereEnseignement(idEnseignement);
	}
	public static Enseignement selectwhereEnseignement (String matiere, String nbheure,String coeff,String idclasse,String idProfesseur) {
		return Modele.selectwhereEnseignement(matiere, nbheure, coeff, idclasse,idProfesseur);
		}
	}
	



