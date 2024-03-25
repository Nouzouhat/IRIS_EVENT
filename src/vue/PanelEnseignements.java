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
 
import controleur.Enseignement;
import controleur.Tableau;
import controleur.Controleur;
 
public class PanelEnseignements extends PanelPrincipal implements ActionListener{
	private JPanel panelForm = new JPanel ();
	private JTextField txtmatiere = new JTextField ();
	private JTextField txtnbheure = new JTextField ();
	private JTextField txtcoeff = new JTextField ();
	private JTextField txtidclasse = new JTextField ();
	private JTextField txtidprofesseur = new JTextField ();
	private JButton btAnnuler = new JButton("Annuler");
	private JButton btEnregistrer = new JButton("Enregistrer");

	private JScrollPane uneScroll ;
	//panel de filtrage
	private JPanel panelFiltre = new JPanel();
	private JTextField txtFiltre = new JTextField();
	private JButton btFiltrer = new JButton("filtrer");
	private Tableau unTableau;
	private int nbEnseignements ;
	private JLabel lbTitre = new JLabel("Nombre des enseignements");
	private JTable tableEnseignements;
	public PanelEnseignements() {
		super();
		//construction du formulaire Enseignement
		this.panelForm.setLayout(new GridLayout(7,2));
		this.panelForm.setBackground(new Color(225, 198, 22));
		this.panelForm.setBounds(10, 10, 300, 300);
		this.panelForm.add(new JLabel("matiere"));
		this.panelForm.add(this.txtmatiere);
		this.panelForm.add(new JLabel("nbheure"));
		this.panelForm.add(this.txtnbheure);
		this.panelForm.add(new JLabel("coeff"));
		this.panelForm.add(this.txtcoeff);
		this.panelForm.add(new JLabel("idclasse"));
		this.panelForm.add(this.txtidclasse);
		this.panelForm.add(new JLabel("idprofesseur"));
		this.panelForm.add(this.txtidprofesseur);
		this.panelForm.add(this.btAnnuler);
		this.panelForm.add(this.btEnregistrer);
		this.panelForm.setVisible(true);
		this.add(this.panelForm);
		//construction de la table des enseignements
		String entetes [] = {"ID Enseignement", "matiere", "nbheure","coeff","idclasse","idprofesseur"};
		this.unTableau = new Tableau (entetes, this.remplirDonnees(""));
		this.tableEnseignements = new JTable(this.unTableau);
		this.tableEnseignements.getTableHeader().setReorderingAllowed(false);
		this.uneScroll = new JScrollPane(this.tableEnseignements);
		this.uneScroll.setBounds(350, 80, 460, 230);
		this.add(this.uneScroll);
		//construction du panel filtre
		this.panelFiltre.setBounds(400, 30, 400, 30);
		this.panelFiltre.setBackground(new Color (225, 198, 22));
		this.panelFiltre.setLayout(new GridLayout(1, 3));
		this.panelFiltre.add(new JLabel("Filtrer les enseignement par :"));
		this.panelFiltre.add(this.txtFiltre);
		this.panelFiltre.add(btFiltrer);
		this.add(this.panelFiltre);
		//rentre les boutons ecoutables
		this.btAnnuler.addActionListener(this);
		this.btEnregistrer.addActionListener(this);
		this.btFiltrer.addActionListener(this);
		//placement du titre
		this.lbTitre.setBounds(200, 330, 300, 20);
		this.nbEnseignements = this.unTableau.getRowCount();
		this.lbTitre.setText("Nombre de Enseignements :" + this.nbEnseignements);
		this.add(this.lbTitre);
		//suppression d'un enseignement
		this.tableEnseignements.addMouseListener(new MouseListener() {
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
				int numLigne ;
				int idEnseignement;
				if(e.getClickCount() >=2) {
					numLigne = tableEnseignements.getSelectedRow();
					idEnseignement = Integer.parseInt(tableEnseignements.getValueAt(numLigne,0).toString());
					int reponse = JOptionPane.showConfirmDialog(null,
							"Voulez vous confirmez la supression?", "Supression d'un enseignement",JOptionPane.YES_NO_OPTION);
					if(reponse == 0) {
						//confirmation de la supression de la bdd
						Controleur.deleteEnseignement(idEnseignement);
						//confirmation de la supression dans l'affichage des enseignements
						unTableau.supprimerLigne(numLigne);
						nbEnseignements = unTableau.getRowCount();
						lbTitre.setText("Nombre de Enseignements :" + nbEnseignements);
					}
				}
				else if (e.getClickCount()== 1) {
					numLigne = tableEnseignements.getSelectedRow();
					txtmatiere.setText(tableEnseignements.getValueAt(numLigne,1).toString());
					txtnbheure.setText(tableEnseignements.getValueAt(numLigne,2).toString());
					txtcoeff.setText(tableEnseignements.getValueAt(numLigne,3).toString());
					txtidclasse.setText(tableEnseignements.getValueAt(numLigne,4).toString());
					txtidprofesseur.setText(tableEnseignements.getValueAt(numLigne,5).toString());
					btEnregistrer.setText("Modifier");
				}
			}
		}
		);
	}
	public Object [][] remplirDonnees (String filtre){
		//cette méthode permet de convertir l'ArrayList en une matrice de données.
		ArrayList<Enseignement> lesEnseignements = Controleur.selectAllEnseignement(filtre) ;
		Object [][] matrice = new Object [lesEnseignements.size()][6];
		int i =0;
		for (Enseignement unEnseignement : lesEnseignements) {
			matrice [i][0] = unEnseignement.getidenseignement();
			matrice [i][1] = unEnseignement.getmatiere();
			matrice [i][2] = unEnseignement.getnbheure();
			matrice [i][3] = unEnseignement.getcoeff();
			matrice [i][4] = unEnseignement.getidclasse();
			matrice [i][5] = unEnseignement.getidprofesseur();
			i++;
		}
		return matrice;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		 if (e.getSource() == this.btAnnuler) {
			 this.txtmatiere.setText("");
			 this.txtnbheure.setText("");
			 this.txtcoeff.setText("");
			 this.txtidclasse.setText("");
			 this.txtidprofesseur.setText("");
			 this.btEnregistrer.setText("Enregistrer");
		 }
		 else if (e.getSource() == this.btEnregistrer && this.btEnregistrer.getText().equals("Enregistrer")) {
			 String matiere = this.txtmatiere.getText();
			 String nbheure = this.txtnbheure.getText();
			 String coeff = this.txtcoeff.getText();
			 String idclasse = this.txtidclasse.getText();
			 String idprofesseur = this.txtidprofesseur.getText();
			 //instanciation d'un enseignement
			 Enseignement unEnseignement = new Enseignement (matiere,nbheure,coeff, idclasse,idprofesseur);
			 //insertion dans la base de données
			 Controleur.insertEnseignement(unEnseignement);
			 JOptionPane.showMessageDialog(this, "Enseignement ajouté avec succès");
			 //recuperation de l'id d'un Enseignement de la BDD
			 unEnseignement = Controleur.selectwhereEnseignement (matiere, nbheure, coeff, idclasse,idprofesseur);
			 //mettre à jour l'afffichage
			 Object ligne[]= {unEnseignement.getidenseignement(),matiere, nbheure, coeff, idclasse,idprofesseur};
			 this.unTableau.ajouterLigne(ligne);
			 this.nbEnseignements = unTableau.getRowCount();
			 this.lbTitre.setText("Nombre des Enseignements :" + this.nbEnseignements);
			 this.txtmatiere.setText("");
			 this.txtnbheure.setText("");
			 this.txtcoeff.setText("");
			 this.txtidclasse.setText("");
			 this.txtidprofesseur.setText("");
		 }
		 else if (e.getSource() == this.btFiltrer) {
			 String filtre = this.txtFiltre.getText();
			 Object matrice [][] = this.remplirDonnees(filtre);
			 this.unTableau.setDonnees(matrice);
		 }
		 else if (e.getSource() == this.btEnregistrer && this.btEnregistrer.getText().equals("Modifier")) {
			 String matiere = this.txtmatiere.getText();
			 String nbheure = this.txtnbheure.getText();
			 String coeff = this.txtcoeff.getText();
			 String idclasse = this.txtidclasse.getText();
			 String idprofesseur = this.txtidprofesseur.getText();
			 int numLigne = this.tableEnseignements.getSelectedRow();
			 int idLigne = Integer.parseInt(this.tableEnseignements.getValueAt( numLigne,0).toString());
			 //instanciation d'un Enseignement
			 Enseignement unEnseignement = new Enseignement(idLigne, matiere, nbheure,coeff, idclasse,idprofesseur);
			 //modification dans la base de données
			 Controleur.updateEnseignement(unEnseignement);
			 //modification dans la table d'affichage
			 Object ligne [] = {idLigne, matiere , nbheure,coeff, idclasse, idprofesseur};
			 this.unTableau.modifierLigne(numLigne, ligne);
			 //on vide le formulaire
			 this.txtmatiere.setText("");
			 this.txtnbheure.setText("");
			 this.txtcoeff.setText("");
			 this.txtidclasse.setText("");
			 this.txtidprofesseur.setText("");
 
			 this.btEnregistrer.setText("Enregistrer");
		 }
	}
}
