package vue;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import controleur.Classe;
import controleur.Controleur;
import controleur.Tableau;

public class PanelClasses extends PanelPrincipal implements ActionListener {
	private JPanel panelForm = new JPanel();
	private JTextField txtNom = new JTextField();
	private JTextField txtSalle = new JTextField();
	private JTextField txtDiplome = new JTextField();
	private JButton btAnnuler = new JButton("Annuler");
	private JButton btEnregistrer = new JButton("Enregistrer");
	
	private JTable tableClasses; //table des classes
	private JScrollPane uneScroll;
	
	//Panel de filtrage 
	private JPanel panelFiltre = new JPanel (); 
	private JTextField txtFiltre = new JTextField();
	private JButton btFiltrer = new JButton ("Filtrer");
	
	private Tableau unTableau;
	
	private int nbClasses;
	private JLabel lbTitre = new JLabel("Nombre de classes ;");
	
	public PanelClasses (){
		super ();
		//construction du formulaire Classe
		this.panelForm.setLayout(new GridLayout(4,2));
		this.panelForm.setBackground(new Color(225, 198, 22));
		this.panelForm.setBounds(10, 10, 300, 300);
		this.panelForm.add(new JLabel("Nom classe : "));
		this.panelForm.add(this.txtNom);
		this.panelForm.add(new JLabel("Salle de cours: "));
		this.panelForm.add(this.txtSalle);
		this.panelForm.add(new JLabel("Diplome  : "));
		this.panelForm.add(this.txtDiplome);
		this.panelForm.add(this.btAnnuler);
		this.panelForm.add(this.btEnregistrer);
		this.panelForm.setVisible(true);
		this.add(this.panelForm);
		
		//construction de la table des classes 
		String entetes [] = {"ID Classe", "Nom classe", "Salle cours ","Diplome"};
		this.unTableau = new Tableau(entetes, this.remplirDonnees(""));
		this.tableClasses = new JTable(this.unTableau);
		this.tableClasses.getTableHeader().setReorderingAllowed(false);
		this.uneScroll = new JScrollPane (this.tableClasses);
		this.uneScroll.setBounds(400, 65, 300, 250);
		this.add(this.uneScroll);
		
		//construction du panel filtre 
		this.panelFiltre.setBounds(400, 30, 400, 30 );
		this.panelFiltre.setBackground(new Color(225, 198, 22));
		this.panelFiltre.setLayout(new GridLayout(1, 3));
		this.panelFiltre.add(new JLabel("Filtrer les classes par :"));
		this.panelFiltre.add(this.txtFiltre);
		this.panelFiltre.add(this.btFiltrer);
		this.add(this.panelFiltre);
       
		//rendre les boutons ecoutables
		this.btAnnuler.addActionListener(this);
		this.btEnregistrer.addActionListener(this);
		this.btFiltrer.addActionListener(this);	
		
		//placement du titre
		this.lbTitre.setBounds(200, 330, 300, 20);
		this.nbClasses = this.unTableau.getRowCount();
		this.lbTitre.setText("Nombre de classes :" + this.nbClasses);
		this.add(this.lbTitre);
		
		//supression d'une classe 
		this.tableClasses.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				int numLigne;
				int idClasse;
				if (e.getClickCount()>= 2 ) {
					numLigne = tableClasses.getSelectedRow();
					idClasse = Integer.parseInt(tableClasses.getValueAt(numLigne,0).toString());
					int reponse = JOptionPane.showConfirmDialog(null,
							"Voulez-vous confirmer la suppresion ?", "Suppression de la classe",
							JOptionPane.YES_NO_OPTION);
					if (reponse == 0) {
						//confirmation de la suppression dans la BDD
						Controleur.deleteClasse(idClasse);
						//confirmation de la suppression dans l'affichage des classes
						unTableau.supprimerLigne(numLigne);
						nbClasses = unTableau.getRowCount();
						lbTitre.setText("Nombre de classes :" + nbClasses);
						PanelEtudiants.remplirCBXClasses();
						
					}
				}
				else if (e.getClickCount() == 1 ) {
					numLigne = tableClasses.getSelectedRow();
					txtNom.setText(tableClasses.getValueAt(numLigne,1).toString());
					txtSalle.setText(tableClasses.getValueAt(numLigne,2).toString());
					txtDiplome.setText(tableClasses.getValueAt(numLigne,3).toString());
					btEnregistrer.setText("Modifier");
				}
				
			}
		});
	}

	public Object [][] remplirDonnees (String filtre) {
		//cette methode permet de convertir l'ArrayList en une matrice de données.
		ArrayList<Classe> lesClasses = Controleur.selectAllClasses(filtre);
		Object [][] matrice = new Object [lesClasses.size()][4];
		int i =0;
		for (Classe uneClasse : lesClasses) {
			matrice [i][0] = uneClasse.getIdclasse();
			matrice [i][1] = uneClasse.getNom();
			matrice [i][2] = uneClasse.getSalle();
			matrice [i][3] = uneClasse.getDiplome();
			i++;
		}
		return matrice;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource()== this.btAnnuler) {
			this.txtDiplome.setText("");
			this.txtSalle.setText("");
			this.txtNom.setText("");
			this.btEnregistrer.setText("Enregistrer");
		}
		else if (e.getSource()== this.btEnregistrer && this.btEnregistrer.getText().equals("Enregistrer")) {
			String nom = this.txtNom.getText();
			String salle = this.txtSalle.getText();
			String diplome = this.txtDiplome.getText();
			
			//instanciation d'une classe
			Classe uneClasse = new Classe (nom, salle, diplome);
			
			//insertion dans la base de données
			Controleur.insertClasse(uneClasse);
			JOptionPane.showMessageDialog(this, "classe ajoutéé avec sucées");
			
			//recuperation de l'id de la classe de la BDD
			uneClasse = Controleur.selectWhereClasse(nom, salle);
			
			//mettre à jour l'affichage
			Object ligne[] = {uneClasse.getIdclasse(), nom, salle, diplome};
			this.unTableau.ajouterLigne(ligne);
			
			this.nbClasses = unTableau.getRowCount();
			this.lbTitre.setText("Nombre de classes :" + this.nbClasses);
			
			
			this.txtDiplome.setText("");
			this.txtSalle.setText("");
			this.txtNom.setText("");
			//actualiser la liste des classes
			PanelEtudiants.remplirCBXClasses();
			
		}
		else if (e.getSource()== this.btFiltrer) {
			String filtre = this.txtFiltre.getText();
			Object matrice [][] = this.remplirDonnees(filtre);
			this.unTableau.setDonnees(matrice);
		}
		else if (e.getSource()== this.btEnregistrer && this.btEnregistrer.getText().equals("Modifier")) {
			String nom = this.txtNom.getText();
			String salle = this.txtSalle.getText();
			String diplome = this.txtDiplome.getText();
			int numLigne = this.tableClasses.getSelectedRow();
			int idLigne = Integer.parseInt(this.tableClasses.getValueAt(numLigne, 0).toString());
			//instanciation de la classe
			Classe uneClasse = new Classe(idLigne, nom, salle, diplome);
			//modification dans la base de données
			Controleur.updateClasse(uneClasse);
			//modification dans le tableau d'affichage
			Object ligne[] = { idLigne, nom, salle, diplome};
			this.unTableau.modifierLigne(numLigne, ligne);
			//on vide le formulaire
			this.txtNom.setText("");
			this.txtDiplome.setText("");
			this.txtSalle.setText("");
			this.txtNom.setText("");
			this.btEnregistrer.setText("Enregistrer");
			//actualiser la liste des classes
			PanelEtudiants.remplirCBXClasses();
		}
		
	}

}
