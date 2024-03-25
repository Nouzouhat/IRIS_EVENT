package vue;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import controleur.IRISEVENT;
import controleur.User;

public class VueGenerale extends JFrame implements ActionListener{
	private JPanel panelMenu = new JPanel();
	private JButton btClasses = new JButton("Classes");
	private JButton btEtudiants = new JButton("Etudiants");
	private JButton btProfesseurs = new JButton("Professeurs");
	private JButton btEnseignements = new JButton("Enseignements");
	private JButton btStats = new JButton("Stats");
	private JButton btQuitter = new JButton("Quitter");
	private JButton btProfil = new JButton ("Profil");

	//instanciation des panels : 
	private PanelProfil unPanelProfil;
	private PanelClasses unPanelClasse = new PanelClasses();
	private PanelEtudiants unPanelEtudiant = new PanelEtudiants();
	private PanelProfesseurs unPanelProfesseur = new PanelProfesseurs();
	private PanelEnseignements unPanelEnseignement = new PanelEnseignements();
	private PanelStats unPanelStats= new PanelStats();

	public VueGenerale(User unUser) {
		this.setTitle("Scolarite IRIS");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().setBackground(new Color(225, 198,22));
		this.setLayout(null);
		this.setBounds(200, 200, 900, 500);
		this.setResizable(false);
		
		//instanciation du panel profil
		this.unPanelProfil = new PanelProfil(unUser);
		
		//placement du menu
		this.panelMenu.setBounds(30, 10, 800, 30);
		this.panelMenu.setBackground(new Color(225, 198,22));
		this.panelMenu.setLayout(new GridLayout(1, 7));
		this.panelMenu.add(this.btProfil);
		this.panelMenu.add(this.btClasses);
		this.panelMenu.add(this.btEtudiants);
		this.panelMenu.add(this.btProfesseurs);
		this.panelMenu.add(this.btEnseignements);
		this.panelMenu.add(this.btStats);
		this.panelMenu.add(this.btQuitter);
		this.panelMenu.setVisible(true);
		this.add(this.panelMenu);
		
		//rendre les boutons ecoutables
		this.btQuitter.addActionListener(this);
		this.btClasses.addActionListener(this);
		this.btEnseignements.addActionListener(this);
		this.btEtudiants.addActionListener(this);
		this.btProfesseurs.addActionListener(this);
		this.btStats.addActionListener(this);
		this.btProfil.addActionListener(this);
		
		//ajout des panels dans la fenetre 
		this.add(this.unPanelProfil);
		this.add(this.unPanelClasse);
		this.add(this.unPanelEnseignement);
		this.add(this.unPanelEtudiant);
		this.add(this.unPanelProfesseur);
		this.add(this.unPanelStats);
		
		this.setVisible(true);
	}
	
	public void afficherPanel (int choix ) {
		this.unPanelClasse.setVisible(false);
		this.unPanelEnseignement.setVisible(false);
		this.unPanelEtudiant.setVisible(false);
		this.unPanelProfesseur.setVisible(false);
		this.unPanelStats.setVisible(false);
		this.unPanelProfil.setVisible(false);
		switch (choix) {
		case 1 : unPanelClasse.setVisible(true); break;
		case 2 : unPanelEtudiant.setVisible(true); break;
		case 3 : unPanelProfesseur.setVisible(true); break;
		case 4 : unPanelEnseignement.setVisible(true);break;
		case 5 : unPanelStats.setVisible(true); break;
		case 6 : unPanelProfil.setVisible(true); break;
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource()== this.btQuitter) {
			IRISEVENT.rendreVisibleGenerale(false, null);
			IRISEVENT.rendreVisibleConnexion(true);
		}
		else if (e.getSource()== this.btClasses) {
			this.afficherPanel(1);
		}
		
		else if (e.getSource()== this.btEtudiants) {
			this.afficherPanel(2);
		}
		
		else if (e.getSource()== this.btProfesseurs) {
			this.afficherPanel(3);
		}
		
		else if (e.getSource()== this.btEnseignements) {
			this.afficherPanel(4);
		}
		
		else if (e.getSource()== this.btStats) {
			this.afficherPanel(5);
		}
		
		else if (e.getSource()== this.btProfil) {
			this.afficherPanel(6);
		}
		
	}

}
