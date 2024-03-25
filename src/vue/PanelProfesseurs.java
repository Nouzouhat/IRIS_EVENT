package vue;

import java.awt.Color;


import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import com.mysql.jdbc.RowData;

import controleur.Classe;
import controleur.Controleur;
import controleur.Professeur;
import controleur.Tableau;

public class PanelProfesseurs extends PanelPrincipal implements ActionListener
{

	private JPanel panelForm = new JPanel ();
	private JTextField txtNom = new JTextField();
	private JTextField txtPrenom = new JTextField();
	private JTextField txtEmail = new JTextField();
	private JTextField txtDiplome = new JTextField();
	private JButton btAnnuler = new JButton("Annuler");
	private JButton btEnregistrer = new JButton("Enregistrer");
	
	//table des classes
	private JTable tableProfesseurs ; 
	private int nbProfesseurs;
	private JLabel lbTitre = new JLabel("Nombre de professeur : ");
	
	private JScrollPane uneScroll ;
	
	//panel de filtrage
	
	private JPanel panelFiltre = new JPanel();
	private JTextField txtFiltre = new JTextField();
	private JButton btFiltrer = new JButton("Filtrer");
	
	private Tableau unTableau ;
	
	public PanelProfesseurs() {
	super ();
	//Constructeur du formulaire Classe.
	this.panelForm.setLayout(new GridLayout(5,2));
	this.panelForm.setBackground(new Color (225, 198, 22));
	this.panelForm.setBounds(10, 10, 300, 300);
	this.panelForm.add(new JLabel("Nom :"));
	this.panelForm.add(this.txtNom);
	this.panelForm.add(new JLabel("Prenom :"));
	this.panelForm.add(this.txtPrenom);
	this.panelForm.add(new JLabel("Email :"));
	this.panelForm.add(this.txtEmail);
	this.panelForm.add(new JLabel("Diplome :"));
	this.panelForm.add(this.txtDiplome);
	this.panelForm.add(this.btAnnuler);
	this.panelForm.add(this.btEnregistrer);
	this.panelForm.setVisible(true);
	this.add(this.panelForm);
	
	
	//construction de la table des classes
	String entetes [] = {"ID Professeur", "Nom ", "Prenom ","Email" ,"Diplome"};
	this.unTableau = new Tableau(entetes, this.remplirDonnees(""));
	
	this.tableProfesseurs = new JTable(this.unTableau);
	this.tableProfesseurs.getTableHeader().setReorderingAllowed(false);
	this.uneScroll = new JScrollPane(this.tableProfesseurs);
	this.uneScroll.setBounds(350, 80, 460, 230);
	this.add(this.uneScroll);
	
	//construction du panel filtre
	this.panelFiltre.setBounds(400, 30, 400, 30);
	this.panelFiltre.setBackground(new Color (225, 198, 22));
	this.panelFiltre.setLayout(new GridLayout(1, 3));
	this.panelFiltre.add(new JLabel("Filtrer les professeurs par :"));
	this.panelFiltre.add(this.txtFiltre);
	this.panelFiltre.add(btFiltrer);
	this.add(this.panelFiltre);
	
	
	//rendre les boutons ecoutables
    this.btAnnuler.addActionListener(this);
    this.btEnregistrer.addActionListener(this);
    this.btFiltrer.addActionListener(this);
    
    //placement du titre 
    this.lbTitre.setBounds(200 , 330, 300, 20);
    this.nbProfesseurs = this.unTableau.getRowCount();
    this.lbTitre.setText("Nombre de professeur : " + this.nbProfesseurs);
    this.add(this.lbTitre);
    
    //suppresion d'une classe
    this.tableProfesseurs.addMouseListener(new MouseListener() {
		
		@Override
		public void mouseReleased(MouseEvent e) {
			
			
		}
		
		@Override
		public void mousePressed(MouseEvent e) {
			
			
		}
		
		@Override
		public void mouseExited(MouseEvent e) {


			
		}
		
		@Override
		public void mouseEntered(MouseEvent e) {
			
			
		}
		
		public void mouseClicked(MouseEvent e) {
        int numLigne ;
        int idProfesseur;
        if (e.getClickCount()>= 2) {
        	numLigne = tableProfesseurs.getSelectedRow();
        	idProfesseur = Integer.parseInt(tableProfesseurs.getValueAt(numLigne, 0).toString());
        	int reponse = JOptionPane.showConfirmDialog(null, "Voulez vous confirmer la suppression?", "Suppression de professeur", JOptionPane.YES_NO_OPTION);
        	if (reponse == 0) {
        		// confirmation de la suppresion dans la BDD
        		Controleur.deleteProfesseur(idProfesseur);
        		//confirmation de la suppression dans l'affichage des classes
        		unTableau.supprimerLigne(numLigne);
        		nbProfesseurs = unTableau.getRowCount();
        		lbTitre.setText("Nombre de professeur : " + nbProfesseurs);
        	}
        }
        else if (e.getClickCount()==1) {
        	numLigne = tableProfesseurs.getSelectedRow();
        	txtNom.setText(tableProfesseurs.getValueAt(numLigne,1).toString());
        	txtPrenom.setText(tableProfesseurs.getValueAt(numLigne,2).toString());
        	txtEmail.setText(tableProfesseurs.getValueAt(numLigne,3).toString());
        	txtDiplome.setText(tableProfesseurs.getValueAt(numLigne,4).toString());
        	btEnregistrer.setText("Modifier");
        }
			
		}
	});

  }
	public Object [][] remplirDonnees (String filtre){
		//cette méthode permet de convertire l'ArrayListe en une matrice de donnés.
		ArrayList<Professeur> lesProfesseurs = Controleur.selectAllProfesseurs(filtre);
		Object [][] matrice = new Object [lesProfesseurs.size()][5];
		int i =0;
		for (Professeur unProfesseur: lesProfesseurs) {
			matrice [i][0] = unProfesseur.getIdprofesseur();
			matrice [i][1] = unProfesseur.getNom();
			matrice [i][2] = unProfesseur.getPrenom();
			matrice [i][3] = unProfesseur.getEmail();
			matrice [i][4] = unProfesseur.getDiplome();
			i++;
		}
		return matrice;
	}
@Override
public void actionPerformed(ActionEvent e) {
	if (e.getSource() == this.btAnnuler) {
		this.txtNom.setText("");
		this.txtPrenom.setText("");
		this.txtEmail.setText("");
		this.txtDiplome.setText("");
		this.btEnregistrer.setText("Enregistrer");
		}
	else if (e.getSource() == this.btEnregistrer && this.btEnregistrer.getText().equals("Enregistrer")) {
		String nom = this.txtNom.getText();
		String prenom = this.txtPrenom.getText();
		String email = this.txtEmail.getText();
		String diplome = this.txtDiplome.getText();
		//instanciation d'une classe
		
		Professeur unProfesseur = new Professeur (nom,prenom,email,diplome);
		
		
		//insertion dans la base de données
		Controleur.insertProfesseur(unProfesseur);
		JOptionPane.showMessageDialog(this, "Professeur ajouter avec succès");
		
		//recuperetion de l'id de la classe de la BDD
		unProfesseur = Controleur.selectWhereProfesseur(nom, prenom);
		 
		
		//mettre à jour l'affichage
		Object ligne[]= {unProfesseur.getIdprofesseur(), nom, prenom,email, diplome};
		this.unTableau.ajouterLigne(ligne);
		this.nbProfesseurs = unTableau.getRowCount();
		this.lbTitre.setText("Nombre de Professeur : " + this.nbProfesseurs);
		this.txtNom.setText("");
		this.txtPrenom.setText("");
		this.txtEmail.setText("");
		this.txtDiplome.setText("");
	}
	else if (e.getSource()== this.btFiltrer) {
		String filtre = this.txtFiltre.getText();
		Object matrice[][] = this.remplirDonnees(filtre);
		this.unTableau.setDonnees(matrice);
	}
	else if (e.getSource()== this.btEnregistrer && this.btEnregistrer.getText().equals("Modifier")) {
		String nom = this.txtNom.getText();
		String prenom = this.txtPrenom.getText();
		String email = this.txtEmail.getText();
		String diplome = this.txtDiplome.getText();
		
		int numLigne = this.tableProfesseurs.getSelectedRow();
		int idLigne = Integer.parseInt(this.tableProfesseurs.getValueAt(numLigne, 0).toString());
		//instaciation de la classe 
		Professeur unProfesseur = new Professeur(idLigne, nom, prenom,email,diplome);
		//modification de la base de données :
		Controleur.updateProfesseur(unProfesseur);
		//modification de la table d'affichage
		Object ligne[] = {idLigne, nom, prenom,email, diplome};
		this.unTableau.modifierLigne(numLigne, ligne);
		//on vide le formulaire
		this.txtNom.setText("");
		this.txtPrenom.setText("");
		this.txtEmail.setText("");
		this.txtDiplome.setText("");
		this.btEnregistrer.setText("Enregistrer");
	}
 }
}






