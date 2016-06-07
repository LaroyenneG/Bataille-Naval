/*******************************************************************************
 * 2016, All rights reserved.
 *******************************************************************************/
package gui;

import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import engine.GrilleJeux;

// Start of user code (user defined imports)

/**
 * Description of GrilleDeJeuJPanel.
 * 
 * @author nicolas
 */
public class GrilleDeJeuJPanel extends JPanel
{
	/**
	 * 
	 */
	private static final long	serialVersionUID	= -416871502500554822L;
	public static final int		TAILLE_GRILLE		= 10;													// Taille
																											// des
																											// lignes
																											// et
																											// colonnes
																											// de
																											// la
																											// grille.
	public static final char[]	INTITULE_LIGNE		= { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J' };	// Intitulé
																											// des
																											// lignes.

	// Start of user code (user defined attributes for GrilleDeJeuJPanel)
	public JButton[][]			grille;																		// Grille
																											// de
																											// JButton.
	public JLabel[]				intitule_ligne		= new JLabel[TAILLE_GRILLE];							// Intitulé
																											// des
																											// lignes.
	public JLabel[]				intitule_colonne	= new JLabel[TAILLE_GRILLE];; // Intitulé
																					// des
																					// colonnes.
	private GrilleJeux			grille_jeu;

	// public ArrayList<BateauJButton[]> bateauJButtons;

	// End of user code

	/**
	 * The constructor.
	 */

	public GrilleDeJeuJPanel(GrilleJeux gj)
	{
		// Start of user code constructor for GrilleDeJeuJPanel)
		super(); // new GridLayout(1, 2)
		this.grille_jeu = gj;
		initAttribut(); // Initialisation des attributs.
		creerWidget(); // Initialisation de la fenêtre.

		// End of user code
	}

	public GrilleDeJeuJPanel(GrilleJeux grilleJeux, JButton[][] grille)
	{
		super();

		this.grille_jeu = grilleJeux;
		this.grille = grille;

		intitule_ligne = new JLabel[TAILLE_GRILLE + 1];
		for (int i = 0 , j = -1 ; i < TAILLE_GRILLE + 1 ; i++ , j++)
		{
			if (i == 0)
				intitule_ligne[i] = new JLabel();
			else
				intitule_ligne[i] = new JLabel(Character.toString(INTITULE_LIGNE[j]));
		}

		intitule_colonne = new JLabel[TAILLE_GRILLE];
		for (int i = 0 ; i < TAILLE_GRILLE ; i++)
			intitule_colonne[i] = new JLabel(Integer.toString(i + 1));

		creerWidget();
	}

	// Start of user code (user defined methods for GrilleDeJeuJPanel)

	/**
	 * Initialisation de la grille.
	 */
	public void initAttribut()
	{
		grille = new JButton[TAILLE_GRILLE][TAILLE_GRILLE]; // Initialisation
															// du tableau de
															// CaseJButton.

		// Parcours de la grille pour initialiser chaque case.
		for (int i = 0 ; i < TAILLE_GRILLE ; i++)
			for (int j = 0 ; j < TAILLE_GRILLE ; j++)
			{
				grille[i][j] = new CaseJButton(grille_jeu.getCases()[i][j]);
			}

		// Initialisation de l'intitulé des lignes.
		intitule_ligne = new JLabel[TAILLE_GRILLE + 1];
		for (int i = 0 , j = -1 ; i < TAILLE_GRILLE + 1 ; i++ , j++)
		{
			if (i == 0)
				intitule_ligne[i] = new JLabel();
			else
				intitule_ligne[i] = new JLabel(Character.toString(INTITULE_LIGNE[j]));
		}

		// Initialisation de l'intitulé des colonnes.
		intitule_colonne = new JLabel[TAILLE_GRILLE];
		for (int i = 0 ; i < TAILLE_GRILLE ; i++)
			// Erreur, c'est quoi le but de ce code ?
			// C'est marqué plus haut : initialisation de l'intitulé des
			// colonnes ! Il faut apprendre à lire !
			intitule_colonne[i] = new JLabel(Integer.toString(i + 1));
	}

	/**
	 * Création de la fenêtre.
	 */
	public void creerWidget()
	{

		JPanel pGrille = new JPanel(new GridLayout(TAILLE_GRILLE + 1, TAILLE_GRILLE + 1));
		for (int i = 0 ; i < TAILLE_GRILLE + 1 ; i++)
			for (int j = 0 ; j < TAILLE_GRILLE + 1 ; j++)
			{
				if (i == 0 && j == 0)
					pGrille.add(intitule_ligne[i]);
				else if (i == 0 && j != 0)
					pGrille.add(intitule_colonne[j - 1]);
				else if (i != 0 && j == 0)
					pGrille.add(intitule_ligne[i]);
				else if (i != 0 && j != 0)
					pGrille.add(grille[i - 1][j - 1]);
			}

		add(pGrille);
	}

	/**
	 * Initialisation du listener de la grille.
	 */
	public void setCaseListener(ActionListener actionListener)
	{
		for (int i = 0 ; i < TAILLE_GRILLE ; i++)
			for (int j = 0 ; j < TAILLE_GRILLE ; j++)
				grille[i][j].addActionListener(actionListener);
	}

	public void setJeuListener(ActionListener actionListener)
	{
		for (int i = 0 ; i < TAILLE_GRILLE ; i++)
			for (int j = 0 ; j < TAILLE_GRILLE ; j++)
				grille[i][j].addActionListener(actionListener);
	}

	public GrilleJeux getGrille_jeu()
	{
		return grille_jeu;
	}

	// End of user code
}