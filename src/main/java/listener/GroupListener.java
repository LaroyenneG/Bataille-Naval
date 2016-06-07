/*******************************************************************************
 * 2016, All rights reserved.
 *******************************************************************************/
package listener;

import engine.Equipe;
import engine.GrilleJeux;
import gui.AccueilJFrame;
import gui.ArrierePlanJPanel;
import gui.FenetreJeux;
import gui.MenuSuperieurJMenuBar;

import static javax.swing.JOptionPane.INFORMATION_MESSAGE;
import static javax.swing.JOptionPane.showMessageDialog;

public class GroupListener
{
	// Start of user code (user defined attributes for GrilleDeJeuJPanel)
	public GrilleJeux					grille_jeux1, grille_jeux2;
	public ArrierePlanJPanel			arrierePlanJPanel1, arrierePlanJPanel2;
	public Equipe						equipe1, equipe2;
	public AccueilJFrame				fenetre;
	public CaseListener_Initialisation	caseListener1, caseListener2;
	public FenetreJeux					fenetreJeux;
	public JeuListener					jeuListener;
	// End of user code

	/**
	 * The constructor.
	 */
	public GroupListener(GrilleJeux grille_jeux1, GrilleJeux grille_jeux2, Equipe equipe1, Equipe equipe2)
	{
		this.grille_jeux1 = grille_jeux1;
		this.grille_jeux2 = grille_jeux2;
		this.equipe1 = equipe1;
		this.equipe2 = equipe2;

		fenetre = new AccueilJFrame(this);
		fenetre.setVisible(true);
	}

	public void createGrilleDeJeuJPanel(int i)
	{
		fenetre.setVisible(false);
		fenetre.getContentPane().removeAll();
		if (i == 1)
		{
			showMessageDialog(null, equipe1.getNomEquipe() + ", veuillez placer vos bateaux", "Initialisation",
					INFORMATION_MESSAGE);
			arrierePlanJPanel1 = new ArrierePlanJPanel(grille_jeux1, 1);
			fenetre.setjPanel(arrierePlanJPanel1);
			caseListener1 = new CaseListener_Initialisation(grille_jeux1, fenetre, equipe1, 1, this);
			arrierePlanJPanel1.getGrilleDeJeuJPanel().setCaseListener(caseListener1);
			arrierePlanJPanel1.getSelectionBateauJPanel().setCaseListener(caseListener1);
		}
		else
		{
			showMessageDialog(null, equipe2.getNomEquipe() + ", veuillez placer vos bateaux", "Initialisation",
					INFORMATION_MESSAGE);
			arrierePlanJPanel2 = new ArrierePlanJPanel(grille_jeux2,2);
			fenetre.setjPanel(arrierePlanJPanel2);
			caseListener2 = new CaseListener_Initialisation(grille_jeux2, fenetre, equipe2, 2, this);
			arrierePlanJPanel2.getGrilleDeJeuJPanel().setCaseListener(caseListener2);
			arrierePlanJPanel2.getSelectionBateauJPanel().setCaseListener(caseListener2);
		}
		fenetre.setSize(700, 550);
		fenetre.setLocationRelativeTo(null);
		MenuSuperieurJMenuBar menuBar = new MenuSuperieurJMenuBar(fenetre);
		fenetre.setJMenuBar(menuBar);
		fenetre.getContentPane().repaint();
		fenetre.setVisible(true);
	}

	public void createFenetreJeu()
	{
		fenetre.setVisible(false);
		Equipe[] e = new Equipe[2];
		e[0] = equipe1;
		e[1] = equipe2;
		fenetreJeux = new FenetreJeux(e, arrierePlanJPanel1.getGrilleDeJeuJPanel(),
				arrierePlanJPanel2.getGrilleDeJeuJPanel());
		jeuListener = new JeuListener(fenetreJeux, grille_jeux1, grille_jeux2, equipe1, equipe2, this);
		fenetreJeux.setListener(jeuListener);
		fenetreJeux.setSize(500, 550);
	}

	public void resetGame(String nom1, String nom2)
	{
		grille_jeux1 = new GrilleJeux();

		grille_jeux2 = new GrilleJeux();

		equipe1 = new Equipe(grille_jeux1, nom1);
		equipe2 = new Equipe(grille_jeux2, nom2);

		Equipe[] equipes = new Equipe[] { equipe1, equipe2 };
		grille_jeux1.setEquipes(equipes);
		grille_jeux2.setEquipes(equipes);
	}
}