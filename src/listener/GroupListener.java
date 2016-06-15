package listener;

import engine.Equipe;
import engine.GrilleJeux;
import gui.AccueilJFrame;
import gui.ArrierePlanJPanel;
import gui.FenetreJeux;
import gui.MenuSuperieurJMenuBar;
import java.awt.Component;
import javax.swing.JOptionPane;
import listener.CaseListener_Initialisation;
import listener.JeuListener;

public class GroupListener {
	public GrilleJeux grille_jeux1;
	public GrilleJeux grille_jeux2;
	public ArrierePlanJPanel arrierePlanJPanel1;
	public ArrierePlanJPanel arrierePlanJPanel2;
	public Equipe equipe1;
	public Equipe equipe2;
	public AccueilJFrame fenetre;
	public CaseListener_Initialisation caseListener1;
	public CaseListener_Initialisation caseListener2;
	public FenetreJeux fenetreJeux;
	public JeuListener jeuListener;

	public GroupListener(GrilleJeux grille_jeux1, GrilleJeux grille_jeux2, Equipe equipe1, Equipe equipe2) {
		this.grille_jeux1 = grille_jeux1;
		this.grille_jeux2 = grille_jeux2;
		this.equipe1 = equipe1;
		this.equipe2 = equipe2;
		this.fenetre = new AccueilJFrame(this);
		this.fenetre.setVisible(true);
	}

	public void createGrilleDeJeuJPanel(int i) {
		this.fenetre.setVisible(false);
		this.fenetre.getContentPane().removeAll();
		if(i == 1) {
			JOptionPane.showMessageDialog((Component)null, this.equipe1.getNomEquipe() + ", veuillez placer vos bateaux", "Initialisation", 1);
			this.arrierePlanJPanel1 = new ArrierePlanJPanel(this.grille_jeux1, 1);
			this.fenetre.setjPanel(this.arrierePlanJPanel1);
			this.caseListener1 = new CaseListener_Initialisation(this.grille_jeux1, this.fenetre, this.equipe1, 1, this);
			this.arrierePlanJPanel1.getGrilleDeJeuJPanel().setCaseListener(this.caseListener1);
			this.arrierePlanJPanel1.getSelectionBateauJPanel().setCaseListener(this.caseListener1);
		} else {
			JOptionPane.showMessageDialog((Component)null, this.equipe2.getNomEquipe() + ", veuillez placer vos bateaux", "Initialisation", 1);
			this.arrierePlanJPanel2 = new ArrierePlanJPanel(this.grille_jeux2, 2);
			this.fenetre.setjPanel(this.arrierePlanJPanel2);
			this.caseListener2 = new CaseListener_Initialisation(this.grille_jeux2, this.fenetre, this.equipe2, 2, this);
			this.arrierePlanJPanel2.getGrilleDeJeuJPanel().setCaseListener(this.caseListener2);
			this.arrierePlanJPanel2.getSelectionBateauJPanel().setCaseListener(this.caseListener2);
		}

		this.fenetre.setSize(700, 550);
		this.fenetre.setLocationRelativeTo((Component)null);
		MenuSuperieurJMenuBar menuBar = new MenuSuperieurJMenuBar(this.fenetre);
		this.fenetre.setJMenuBar(menuBar);
		this.fenetre.getContentPane().repaint();
		this.fenetre.setVisible(true);
	}

	public void createFenetreJeu() {
		this.fenetre.setVisible(false);
		Equipe[] e = new Equipe[]{this.equipe1, this.equipe2};
		this.fenetreJeux = new FenetreJeux(e, this.arrierePlanJPanel1.getGrilleDeJeuJPanel(), this.arrierePlanJPanel2.getGrilleDeJeuJPanel());
		this.jeuListener = new JeuListener(this.fenetreJeux, this.grille_jeux1, this.grille_jeux2, this.equipe1, this.equipe2, this);
		this.fenetreJeux.setListener(this.jeuListener);
		this.fenetreJeux.setSize(500, 550);
	}

	public void resetGame(String nom1, String nom2) {
		this.grille_jeux1 = new GrilleJeux();
		this.grille_jeux2 = new GrilleJeux();
		this.equipe1 = new Equipe(this.grille_jeux1, nom1);
		this.equipe2 = new Equipe(this.grille_jeux2, nom2);
		Equipe[] equipes = new Equipe[]{this.equipe1, this.equipe2};
		this.grille_jeux1.setEquipes(equipes);
		this.grille_jeux2.setEquipes(equipes);
	}
}