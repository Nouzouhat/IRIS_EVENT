package vue;

import java.awt.Color;


import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
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
import controleur.Etudiant;
import controleur.Tableau;

public class PanelEtudiants extends PanelPrincipal implements ActionListener
{

	private JPanel panelForm = new JPanel ();
	private JTextField txtNom = new JTextField();
	private JTextField txtPrenom = new JTextField();
	private JTextField txtEmail = new JTextField();
	private JTextField txtDateNais = new JTextField();
	private JComboBox<String> txtStatut = new JComboBox<String>();
	
	private static JComboBox<String> txtIdclasse = new  JComboBox<String>();
	
	private JButton btAnnuler = new JButton("Annuler");
	private JButton btEnregistrer = new JButton("Enregistrer");
	
	//table des classes
	private JTable tableEtudiants ; 
	private int nbEtudiants;
	private JLabel lbTitre = new JLabel("Nombre d'etudiant : ");
	
	private JScrollPane uneScroll ;
	
	//panel de filtrage
	
	private JPanel panelFiltre = new JPanel();
	private JTextField txtFiltre = new JTextField();
	private JButton btFiltrer = new JButton("Filtrer");
	
	private Tableau unTableau ;
	
	public PanelEtudiants () {
	super ();
	//Constructeur du formulaire Classe.
	this.panelForm.setLayout(new GridLayout(7,2));
	this.panelForm.setBackground(new Color (225, 198, 22));
	this.panelForm.setBounds(10, 10, 300, 300);
	this.panelForm.add(new JLabel("Nom :"));
	this.panelForm.add(this.txtNom);
	this.panelForm.add(new JLabel("Prenom :"));
	this.panelForm.add(this.txtPrenom);
	this.panelForm.add(new JLabel("Email  :"));
	this.panelForm.add(this.txtEmail);
	this.panelForm.add(new JLabel("Date de Naissance :"));
	this.panelForm.add(this.txtDateNais);
	this.panelForm.add(new JLabel("Statut :"));
	this.panelForm.add(this.txtStatut);
	this.panelForm.add(new JLabel("Idclasse :"));
	this.panelForm.add(this.txtIdclasse);
	this.panelForm.add(this.btAnnuler);
	this.panelForm.add(this.btEnregistrer);
	this.panelForm.setVisible(true);
	this.add(this.panelForm);
	
	
	//construction de la table des classes
	String entetes [] = {"ID", "Nom ", " Prenom "," Email "," DT Naissance "," Statut" ," Classe"};
	this.unTableau = new Tableau(entetes, this.remplirDonnees(""));
	
	this.tableEtudiants = new JTable(this.unTableau);
	this.tableEtudiants.getTableHeader().setReorderingAllowed(false);
	this.uneScroll = new JScrollPane(this.tableEtudiants);
	this.uneScroll.setBounds(350, 80, 460, 230);
	this.add(this.uneScroll);
	
	//construction du panel filtre
	this.panelFiltre.setBounds(400, 30, 400, 30);
	this.panelFiltre.setBackground(new Color (225, 198, 22));
	this.panelFiltre.setLayout(new GridLayout(1, 3));
	this.panelFiltre.add(new JLabel("Filtrer les etudiants par :"));
	this.panelFiltre.add(this.txtFiltre);
	this.panelFiltre.add(btFiltrer);
	this.add(this.panelFiltre);
	
	
	//rendre les boutons ecoutables
    this.btAnnuler.addActionListener(this);
    this.btEnregistrer.addActionListener(this);
    this.btFiltrer.addActionListener(this);
    
    //placement du titre 
    this.lbTitre.setBounds(200 , 330, 300, 20);
    this.nbEtudiants = this.unTableau.getRowCount();
    this.lbTitre.setText("Nombre d'etudiant : " + this.nbEtudiants);
    this.add(this.lbTitre);
    
    //remplir le statut
    this.txtStatut.addItem("alternant");
    this.txtStatut.addItem("initial");
    
    //remplir les classes dans le comboBox
    this.remplirCBXClasses ();
    
    //suppresion d'un etudiant
    this.tableEtudiants.addMouseListener(new MouseListener() {
		
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
        int idEtudiant;
        if (e.getClickCount()>= 2) {
        	numLigne = tableEtudiants.getSelectedRow();
        	idEtudiant = Integer.parseInt(tableEtudiants.getValueAt(numLigne, 0).toString());
        	int reponse = JOptionPane.showConfirmDialog(null, "Voulez vous confirmer la suppression?", "Suppression de l'etudiant", JOptionPane.YES_NO_OPTION);
        	if (reponse == 0) {
        		// confirmation de la suppresion dans la BDD
        		Controleur.deleteEtudiant(idEtudiant);
        		//confirmation de la suppression dans l'affichage des classes
        		unTableau.supprimerLigne(numLigne);
        		nbEtudiants = unTableau.getRowCount();
        		lbTitre.setText("Nombre d'etudiant : " + nbEtudiants);
        	}
        }
        else if (e.getClickCount()==1) {
        	numLigne = tableEtudiants.getSelectedRow();
        	txtNom.setText(tableEtudiants.getValueAt(numLigne,1).toString());
        	txtPrenom.setText(tableEtudiants.getValueAt(numLigne,2).toString());
        	txtEmail.setText(tableEtudiants.getValueAt(numLigne,3).toString());
        	txtDateNais.setText(tableEtudiants.getValueAt(numLigne,4).toString());
        	//txtStatut.setText(tableEtudiants.getValueAt(numLigne,5).toString());
        	//txtIdclasse.setText(tableEtudiants.getValueAt(numLigne,6).toString());
        	btEnregistrer.setText("Modifier");
        }
			
		}
	});

  }
	public static void remplirCBXClasses() {
		txtIdclasse.removeAllItems();
		ArrayList<Classe> lesClasses = Controleur.selectAllClasses("");
		for (Classe uneClasse : lesClasses) {
			txtIdclasse.addItem(uneClasse.getIdclasse()+ "-"
					+ uneClasse.getNom());
		}
		
	}
	public Object [][] remplirDonnees (String filtre){
		//cette méthode permet de convertire l'ArrayListe en une matrice de donnés.
		ArrayList<Etudiant> lesEtudiants = Controleur.selectAllEtudiants(filtre);
		Object [][] matrice = new Object [lesEtudiants.size()][7];
		int i =0;
		for (Etudiant unEtudiant: lesEtudiants) {
			matrice [i][0] = unEtudiant.getIdetudiant();
			matrice [i][1] = unEtudiant.getNom();
			matrice [i][2] = unEtudiant.getPrenom();
			matrice [i][3] = unEtudiant.getEmail();
			matrice [i][4] = unEtudiant.getDatenais();
			matrice [i][5] = unEtudiant.getStatut();
			matrice [i][6] = unEtudiant.getIdclasse();
			
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
		this.txtDateNais.setText("");
		//this.txtStatut.setText("");
		//this.txtIdclasse.setText("");
		this.btEnregistrer.setText("Enregistrer");
		}
	else if (e.getSource() == this.btEnregistrer && this.btEnregistrer.getText().equals("Enregistrer")) {
		String nom = this.txtNom.getText();
		String prenom = this.txtPrenom.getText();
		String email = this.txtEmail.getText();
		String datenais = this.txtDateNais.getText();
		String statut = this.txtStatut.getSelectedItem().toString();
		String chaine = this.txtIdclasse.getSelectedItem().toString();
		String tab[] = chaine.split("-");
		int idclasse = Integer.parseInt(tab[0]);
		
		//instanciation d'une classe
		
		Etudiant unEtudiant = new Etudiant( nom, prenom, email, datenais , statut, idclasse);
		
		
		//insertion dans la base de données
		Controleur.insertEtudiant(unEtudiant);
		JOptionPane.showMessageDialog(this, "Etudiant ajouter avec succès");
		
		//recuperation de l'id de l'etudiant  de la BDD
		unEtudiant = Controleur.selectWhereEtudiant (nom, prenom ,email);
		 
		
		//mettre à jour l'affichage
		Object ligne[]= {unEtudiant.getIdetudiant(), nom, prenom, email, datenais, statut, idclasse};
		this.unTableau.ajouterLigne(ligne);
		this.nbEtudiants = unTableau.getRowCount();
		
		this.lbTitre.setText("Nombre d'etudiant : " + this.nbEtudiants);
		this.txtNom.setText("");
		this.txtPrenom.setText("");
		this.txtEmail.setText("");
		this.txtDateNais.setText("");
		//this.txtStatut.setText("");
		//this.txtIdclasse.setText("");
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
		String datenais = this.txtDateNais.getText();
		String statut = this.txtStatut.getSelectedItem().toString();
		String chaine = this.txtIdclasse.getSelectedItem().toString();
		String tab[] = chaine.split("-");
		int idclasse = Integer.parseInt(tab[0]);
		
		int numLigne = this.tableEtudiants.getSelectedRow();
		int idLigne = Integer.parseInt(this.tableEtudiants.getValueAt(numLigne, 0).toString());
		//instaciation de la classe 
		Etudiant unEtudiant = new Etudiant(idLigne, nom, prenom, email, datenais, statut ,idclasse);
		//modification de la base de données :
		Controleur.updateEtudiant(unEtudiant);
		//modification de la table d'affichage
		Object ligne[] = {idLigne, nom, prenom, email, datenais, statut ,idclasse};
		this.unTableau.modifierLigne(numLigne, ligne);
		//on vide le formulaire
		this.txtNom.setText("");
		this.txtPrenom.setText("");
		this.txtEmail.setText("");
		this.txtDateNais.setText("");
		//this.txtStatut.setText("");
		//this.txtIdclasse.setText("");
		this.btEnregistrer.setText("Enregistrer");
	}
 }
}






