package modele;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import controleur.Classe;
import controleur.Enseignement;
import controleur.Etudiant;
import controleur.Professeur;
import controleur.User;

public class Modele {
	private static Bdd uneBdd = new Bdd("localhost:3307", "scolarite_iris_2024", "root", "");
	public static User selectWhereUser (String email, String mdp) {
		String requete = "select * from user where email ='"+email+"' and "
				+ "mdp = '"+mdp+"'; ";
		User unUser = null;
		try {
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaconnexion().createStatement();
			ResultSet unRes = unStat.executeQuery(requete);
			if (unRes.next()) {
				unUser = new User (
						unRes.getInt("iduser"), unRes.getString("nom"), 
						unRes.getString("prenom"), unRes.getString("email"),
						unRes.getString("mdp"), unRes.getString("role")
						);
			}
			unStat.close();
			uneBdd.seDeConnecter();
		}
		catch (SQLException exp) {
			System.out.println("Erreur de requete : "+ requete);
			
		}
		return unUser;
	}
/***********************GESTION DES CLASSES*******************/
public static void insertClasse (Classe uneClasse) {
	String requete ="insert into classe values (null, '"+uneClasse.getNom()+"','"
			+ uneClasse.getSalle()+ "','"
			+uneClasse.getDiplome()+ "');";
	System.out.println(requete);
	try {
		uneBdd.seConnecter();
		Statement unStat = uneBdd.getMaconnexion().createStatement();
		unStat.execute(requete);
		unStat.close();
		uneBdd.seDeConnecter();
}
	catch(SQLException exp) {
	System.out.println("Erreur de requete : "+ requete);
}
}

public static void deleteClasse (int idClasse) {
	String requete ="delete from classe where idclasse ="+idClasse+";";
	try {
		uneBdd.seConnecter();
		Statement unStat = uneBdd.getMaconnexion().createStatement();
		unStat.execute(requete);
		unStat.close();
		uneBdd.seDeConnecter();
	}
	catch(SQLException exp) {
	System.out.println("Erreur de requete : "+ requete);
	}
}

public static void updateClasse (Classe uneClasse) {
	String requete ="update Classe set nom = '"
			+uneClasse.getNom()+"', salle='"
			+uneClasse.getSalle()+"', diplome='"
			+uneClasse.getDiplome()+"' where idClasse = "
			+uneClasse.getIdclasse()+";";
	try {
		uneBdd.seConnecter();
		Statement unStat = uneBdd.getMaconnexion().createStatement();
		unStat.execute(requete);
		unStat.close();
		uneBdd.seDeConnecter();

		}
	catch(SQLException exp) {
	System.out.println("Erreur de requete : "+ requete);
	}
	 }

public static ArrayList<Classe> selectAllClasses (String filtre){
	ArrayList<Classe> lesClasses = new ArrayList<Classe>();
	String requete = "";
	if (filtre.contentEquals("")) {
		requete = "select * from classe; ";
	} else {
		requete = "select * from classe where nom like '%" + filtre
		+ "%' or salle like '%" +filtre
		+ "%' or diplome like '%" +filtre + "%' ;";
	}
	try {
		uneBdd.seConnecter();
		Statement unStat = uneBdd.getMaconnexion().createStatement();
		ResultSet desRes = unStat.executeQuery(requete);
		while (desRes.next()) {
			Classe uneClasse = new Classe (desRes.getInt("idclasse"),
					desRes.getString("nom"), desRes.getString("salle"), 
					desRes.getString("diplome"));
			lesClasses.add(uneClasse);
		}
		unStat.close();
		uneBdd.seDeConnecter();
	}
	catch (SQLException exp) {
		System.out.println("Erreur de requete : "+ requete);
		
	}
	
	return lesClasses;
}

public static Classe selectWhereClasse (int idClasse) {
	Classe uneClasse = null;
	String requete = "select * from classe where idclasse = "+idClasse+";";
	try {
		uneBdd.seConnecter();
		Statement unStat = uneBdd.getMaconnexion().createStatement();
		ResultSet desRes = unStat.executeQuery(requete);
		if (desRes.next()) {
			uneClasse = new Classe(desRes.getInt("idClasse"),
					desRes.getString("nom"),
					desRes.getString("salle"), 
					desRes.getString("diplome"));
		}
		unStat.close();
		uneBdd.seDeConnecter();
	}
	catch (SQLException exp) {
		System.out.println("Erreur de requete : "+ requete);
		
	}
	
	return uneClasse;
}

public static Classe selectWhereClasse (String nom, String salle) {
	Classe uneClasse = null;
	String requete = "select * from classe where nom = '" +nom+ "' and salle = '" +salle+"';";
	try {
		uneBdd.seConnecter();
		Statement unStat = uneBdd.getMaconnexion().createStatement();
		ResultSet desRes = unStat.executeQuery(requete);
		if (desRes.next()) {
			uneClasse = new Classe(desRes.getInt("idClasse"),
					desRes.getString("nom"),
					desRes.getString("salle"), 
					desRes.getString("diplome"));
		}
		unStat.close();
		uneBdd.seDeConnecter();
	}
	catch (SQLException exp) {
		System.out.println("Erreur de requete : "+ requete);
		
	}
	
	return uneClasse;
}
public static void updateuUser(User unUser) {
	String requete = "update user set nom '"
			+unUser.getNom()+"' , prenom = "
			+unUser.getPrenom()+ "' , email = '"
			+unUser.getEmail()+ "' , role='"
			+unUser.getRole()+ "' , mdp='"
			+unUser.getMdp()+ "' where iduser ="
			+unUser.getIduser()+";";
	try {
		uneBdd.seConnecter();
		Statement unStat = uneBdd.getMaconnexion().createStatement();
		unStat.execute(requete);
		unStat.close();
		uneBdd.seDeConnecter();
	}
	catch (SQLException exp) {
		System.out.println("Erreur de requete : "+ requete);
		
	}
	
	
}
/*************** Gestion des Etudiants ***************************/

public static void insertEtudiant (Etudiant unEtudiant) {
    String requete ="insert into etudiant values (null, '"
            +unEtudiant.getNom()+"','"
            +unEtudiant.getPrenom()+"','"
            +unEtudiant.getEmail()+"','"
            +unEtudiant.getDatenais()+"','"
            +unEtudiant.getStatut()+"','"
            +unEtudiant.getIdclasse()+"');";
   // System.out.println(requete);
    try {
        uneBdd.seConnecter();
        Statement unStat = uneBdd.getMaconnexion().createStatement(); 
        unStat.execute(requete);
        unStat.close(); 
        uneBdd.seDeConnecter();
    }
    catch (SQLException exp) {
        System.out.println("Erreur de requete : " + requete);
    }
}

public static void deleteEtudiant (int idEtudiant) {
    String requete ="delete from etudiant where idetudiant = "+idEtudiant+";";
    try {
        uneBdd.seConnecter();
        Statement unStat = uneBdd.getMaconnexion().createStatement(); 
        unStat.execute(requete);
        unStat.close(); 
        uneBdd.seDeConnecter();
    }
    catch (SQLException exp) {
        System.out.println("Erreur de requete : " + requete);
    }
}
public static void updateEtudiant(Etudiant unEtudiant) {
    String requete ="update etudiant set nom='" 
            +unEtudiant.getNom()+"', prenom ='"
            +unEtudiant.getPrenom()+"', email ='"
            +unEtudiant.getEmail()+"', dateNais ='"
            +unEtudiant.getDatenais()+"', status ='"
            +unEtudiant.getStatut()+"', idclasse ='"
            +unEtudiant.getIdclasse()+"' where idetudiant = "
            +unEtudiant.getIdetudiant()+";";
    try {
        uneBdd.seConnecter();
        Statement unStat = uneBdd.getMaconnexion().createStatement(); 
        unStat.execute(requete);
        unStat.close(); 
        uneBdd.seDeConnecter();
    }
    catch (SQLException exp) {
        System.out.println("Erreur de requete : " + requete);
    }
}
public static ArrayList<Etudiant> selectAllEtudiants (String filtre){
    ArrayList<Etudiant> lesEtudiants = new  ArrayList<Etudiant>(); 
    String requete ="" ; 
    if (filtre.contentEquals("")) {
        requete = "select * from  etudiant ; ";
    }else {
        requete = "select * from  etudiant where nom like '%" + filtre 
                + "%'  or  prenom like '%"+filtre
                + "%'  or  email like '%"+filtre
                + "%'  or  datenais like '%"+filtre
                + "%'  or  status like '%"+filtre+ "%'";
                
    }
    try {
        uneBdd.seConnecter();
        Statement unStat = uneBdd.getMaconnexion().createStatement(); 
        ResultSet desRes = unStat.executeQuery(requete);
        while (desRes.next()) {
            Etudiant unEtudiant = new Etudiant(desRes.getInt("idetudiant"),
                    desRes.getString("nom"), 
                    desRes.getString("prenom"),desRes.getString("email"),
                    desRes.getString("dateNais"),desRes.getString("status"),
                    desRes.getInt("idclasse"));
                    
            lesEtudiants.add(unEtudiant);
        }
        unStat.close(); 
        uneBdd.seDeConnecter();
    }
    catch (SQLException exp) {
        System.out.println("Erreur de requete : " + requete);
    }
    return lesEtudiants; 
}
public static Etudiant selectWhereEtudiant (int idEtudiant) {
    Etudiant unEtudiant = null; 
    String requete = "select * from etudiant where idetudiant = " +idEtudiant+";";
    try {
        uneBdd.seConnecter();
        Statement unStat = uneBdd.getMaconnexion().createStatement(); 
        ResultSet desRes = unStat.executeQuery(requete);
        if (desRes.next()) {
            unEtudiant = new Etudiant(desRes.getInt("idetudiant"),
                    desRes.getString("nom"), 
                    desRes.getString("prenom"),desRes.getString("email"),
                    desRes.getString("dateNais"),desRes.getString("status"),
                    desRes.getInt("idclasse"));
        }
        unStat.close(); 
        uneBdd.seDeConnecter();
    }
    catch (SQLException exp) {
        System.out.println("Erreur de requete : " + requete);
    }
    return unEtudiant; 
}
public static Etudiant selectWhereEtudiant (String nom,String prenom, String email) {
    Etudiant unEtudiant = null; 
    String requete = "select * from etudiant where nom='"+nom+"' and prenom='"+prenom+"' and email='"+email+ "';";
    try {
        uneBdd.seConnecter();
        Statement unStat = uneBdd.getMaconnexion().createStatement(); 
        ResultSet desRes = unStat.executeQuery(requete);
        if (desRes.next()) {
            unEtudiant = new Etudiant(desRes.getInt("idetudiant"),
                    desRes.getString("nom"), 
                    desRes.getString("prenom"),desRes.getString("email"),
                    desRes.getString("dateNais"),desRes.getString("status"),
                    desRes.getInt("idclasse"));
        }
        unStat.close(); 
        uneBdd.seDeConnecter();
    }
    catch (SQLException exp) {
        System.out.println("Erreur de requete : " + requete);
    }
    return unEtudiant; 
}

/*           gestion des Professeurs               */


public static void insertProfesseur (Professeur unProfesseur) {
	String requete ="insert into professeur values (null, '"
			+unProfesseur.getNom()+"','"
	        +unProfesseur.getPrenom()+"','"
	        +unProfesseur.getEmail()+"','"
	        +unProfesseur.getDiplome()+"');";
	System.out.println(requete);
	try {
		uneBdd.seConnecter();
		Statement unStat = uneBdd.getMaconnexion().createStatement();
		unStat.execute(requete);
       unStat.close();
		
		uneBdd.seDeConnecter();
	}
	catch (SQLException exp) {
		System.out.println("Erreur de requete : " + requete);
	}
	
}

public static void deleteProfesseur(int idProfesseur) {
	String requete ="delete from professeur where idprofesseur ="+idProfesseur+";";
	try {
		uneBdd.seConnecter();
		Statement unStat = uneBdd.getMaconnexion().createStatement();
		unStat.execute(requete);
       unStat.close();
		
		uneBdd.seDeConnecter();
	}
	catch (SQLException exp) {
		System.out.println("Erreur de requete : " + requete);
	}
}

public static void updateProfesseur(Professeur unProfesseur) {
	String requete = "update professeur set nom='"
			+unProfesseur.getNom()+"', prenom ='"
			+unProfesseur.getPrenom()+"', email ='"
			+unProfesseur.getEmail()+"', diplome ='"
			+unProfesseur.getDiplome()+"' where idprofesseur ="
			+unProfesseur.getIdprofesseur()+";";
	try {
		uneBdd.seConnecter();
		Statement unStat = uneBdd.getMaconnexion().createStatement();
		unStat.execute(requete);
        unStat.close();
		
		uneBdd.seDeConnecter();
	}
	catch (SQLException exp) {
		System.out.println("Erreur de requete : " + requete);
	}
  } 
public static ArrayList<Professeur> selectAllProfesseurs (String filtre){
	ArrayList<Professeur> lesProfesseurs = new ArrayList<Professeur>();
	String requete ="";
	if (filtre.contentEquals("")) {
		requete = "select * from professeur ; ";	
	}else {
		requete = "select * from professeur where nom like '%" +filtre
				+"%' or prenom like '%"+filtre
				+"%' or email like '%"+filtre
				+"%' or diplome like '%"+filtre +"%' ;";
	}
	try {
		uneBdd.seConnecter();
		Statement unStat = uneBdd.getMaconnexion().createStatement();
		ResultSet desRes = unStat.executeQuery(requete);
		while (desRes.next()) {
			Professeur unProfesseur = new Professeur(desRes.getInt("idprofesseur"),
					desRes.getString("nom"),
					desRes.getString("prenom"),
					desRes.getString("email"),desRes.getString("diplome"));
			lesProfesseurs.add(unProfesseur);
		}
        unStat.close();
		
		uneBdd.seDeConnecter();
	}
	catch (SQLException exp) {
		System.out.println("Erreur de requete : " + requete);
	}
	return lesProfesseurs;
}
public static Professeur selectWhereProfesseur (int idProfesseur) {
	Professeur unProfesseur= null;
	String requete = "select * from professeur where idprofesseur ="+idProfesseur+";";
	try {
		uneBdd.seConnecter();
		Statement unStat = uneBdd.getMaconnexion().createStatement();
		ResultSet desRes = unStat.executeQuery(requete);
		if (desRes.next()) {
			 unProfesseur = new Professeur(desRes.getInt("idprofesseur"),
					desRes.getString("nom"),
					desRes.getString("prenom"),
					desRes.getString("email"),desRes.getString("diplome"));
			
		}
        unStat.close();
		uneBdd.seDeConnecter();
	}
	catch (SQLException exp) {
		System.out.println("Erreur de requete : " + requete);
	}
	return unProfesseur;
}
public static Professeur selectWhereProfesseur (String nom, String prenom) {
	Professeur unProfesseur = null;
	String requete = "select * from professeur where nom ='"+nom+"'and prenom ='"+prenom+"';";
	try {
		uneBdd.seConnecter();
		Statement unStat = uneBdd.getMaconnexion().createStatement();
		ResultSet desRes = unStat.executeQuery(requete);
		if (desRes.next()) {
			 unProfesseur = new Professeur(desRes.getInt("idprofesseur"),
					desRes.getString("nom"),
					desRes.getString("prenom"),
					desRes.getString("email"),desRes.getString("diplome"));
			
		}
        unStat.close();
		uneBdd.seDeConnecter();
	}
	catch (SQLException exp) {
		System.out.println("Erreur de requete : " + requete);
	}
	return unProfesseur;
}



/******************************Gestion Enseignements***************************/

public static void insertEnseignement(Enseignement unEnseignement) {
	String requete ="insert into enseignement values (null, '"+unEnseignement.getmatiere()+"','"
													+unEnseignement.getnbheure()+"','"
													+unEnseignement.getcoeff()+"','"
													+unEnseignement.getidclasse()+"','"
													+unEnseignement.getidprofesseur()+"');";
												
	try {
		uneBdd.seConnecter();
		Statement unStat = (Statement) uneBdd.getMaconnexion().createStatement();
		unStat.execute(requete);
		unStat.close();
		uneBdd.seDeConnecter();
		}
	catch (SQLException exp) {
		System.out.println("Erreur de requete : " + requete);
	}
}
public static void deleteEnseignement (int idenseignement) {
	String requete = "delete from enseignement where idenseignement ="+idenseignement+";";
			 try{
					uneBdd.seConnecter();
					Statement unStat = (Statement) uneBdd.getMaconnexion().createStatement();
					unStat.execute(requete);
					unStat.close();
					uneBdd.seDeConnecter();
					}
			 catch (SQLException exp) {
					System.out.println("Erreur de requete : " + requete);
				}
}
public static void updateEnseignement (Enseignement unEnseignement) {
	String requete ="update enseignement set matiere='"
			+unEnseignement.getmatiere()+"',nbheure='"
			+unEnseignement.getnbheure()+"',coeff='"
			+unEnseignement.getcoeff()+"',Idclasse='"
			+unEnseignement.getidclasse()+"',Idprofesseur='"
			+unEnseignement.getidprofesseur()+"'where idenseignement="
			+unEnseignement.getidenseignement()+";";
	try {
		uneBdd.seConnecter();
		Statement unStat = (Statement) uneBdd.getMaconnexion().createStatement();
		unStat.execute(requete);
		unStat.close();
		uneBdd.seDeConnecter();
		}
catch (SQLException exp) {
		System.out.println("Erreur de requete : " + requete);
	}
}
public static ArrayList<Enseignement> selectAllEnseignement (String filtre){
	ArrayList<Enseignement> lesEnseignements = new ArrayList<Enseignement>();
	String requete ="";
	if (filtre.contentEquals("")) {
		requete = "select * from enseignement ; ";
	}else {
		requete = "select * from enseignement where matiere like '%" + filtre
				+ "%' or nbheure like '%"+filtre
				+ "%' or coeff like '%"+filtre
				+ "%' or idclasse like '%"+filtre
				+ "%' or idprofesseur like '%"+filtre+"%' ;";
	}
	 try{
			uneBdd.seConnecter();
			Statement unStat = (Statement) uneBdd.getMaconnexion().createStatement();
			ResultSet desRes = unStat.executeQuery(requete);
			while (desRes.next()) {
				Enseignement unEnseignement = new Enseignement(desRes.getInt("idenseignement"),
						desRes.getString("matiere"),
						desRes.getString("nbheure"),
						desRes.getString("coeff"),
						desRes.getString("idclasse"),
						desRes.getString("idprofesseur"));
				lesEnseignements.add(unEnseignement);
			}
			unStat.close();
			uneBdd.seDeConnecter();
			}
	 catch (SQLException exp) {
			System.out.println("Erreur de requete : " + requete);
		}
	return lesEnseignements;
}
public static Enseignement selectwhereEnseignement (int idenseignement) {
	Enseignement unEnseignement = null;
	String requete = "select * from enseignement where idenseignement =" +idenseignement+";";
	 try{
			uneBdd.seConnecter();
			Statement unStat = (Statement) uneBdd.getMaconnexion().createStatement();
			ResultSet desRes = unStat.executeQuery(requete);
			if (desRes.next()) {
				 unEnseignement = new Enseignement(desRes.getInt("idenseignement"),
						desRes.getString("matiere"),
						desRes.getString("nbheure"),
						desRes.getString("coeff"),
						desRes.getString("idclasse"),
						desRes.getString("idprofesseur"));	
			}
			unStat.close();
			uneBdd.seDeConnecter();
			}
	 catch (SQLException exp) {
			System.out.println("Erreur de requete : " + requete);
		}
		
		return unEnseignement;
}
public static Enseignement selectwhereEnseignement (String matiere, String nbheure, String coeff, String idclasse, String idprofesseur) {
	Enseignement unEnseignement = null;
	String requete = "select * from enseignement where matiere='"+matiere+"' and nbheure='"+nbheure+"'and coeff='"+coeff+"'and idclasse='"+idclasse+"' and idprofesseur='"+idprofesseur+"';";
	 try{
			uneBdd.seConnecter();
			Statement unStat = (Statement) uneBdd.getMaconnexion().createStatement();
			ResultSet desRes = unStat.executeQuery(requete);
			if (desRes.next()) {
				 unEnseignement = new Enseignement(desRes.getInt("idenseignement"),
						desRes.getString("matiere"),
						desRes.getString("nbheure"),
						desRes.getString("coeff"),
						desRes.getString("idclasse"),
						desRes.getString("idprofesseur"));	
			}
			unStat.close();
			uneBdd.seDeConnecter();
			}
	 catch (SQLException exp) {
			System.out.println("Erreur de requete : " + requete);
		}
		return unEnseignement;
}
}
