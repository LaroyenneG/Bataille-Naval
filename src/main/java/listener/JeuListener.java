package listener;

import static gui.GrilleDeJeuJPanel.TAILLE_GRILLE;
import static java.awt.Color.GRAY;
import static java.awt.Color.ORANGE;
import static java.awt.Color.RED;
import static javax.swing.JOptionPane.INFORMATION_MESSAGE;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import engine.Case;
import engine.Equipe;
import engine.GrilleJeux;
import gui.AccueilJFrame;
import gui.BateauJButton;
import gui.CaseJButton;
import gui.FenetreJeux;

public class JeuListener implements ActionListener
{
	public FenetreJeux		fenetreJeux;
	public JButton[][]		grille1, grille2;
	public Case[][]			grille_model1, grille_model2;
	private Case[][]		grille_model_courante;
	private JButton[][]		grille_courante;
	private int				numero_grille;
	public Equipe			equipe1, equipe2;
	public GroupListener	groupListener;

	public JeuListener(FenetreJeux fenetre, GrilleJeux grilleJeux1, GrilleJeux grilleJeux2, Equipe equipe1,
			Equipe equipe2, GroupListener groupListener)
	{
		this.fenetreJeux = fenetre;
		this.grille1 = fenetre.deJeuJPanel1.grille;
		this.grille2 = fenetre.deJeuJPanel2.grille;
		grille_model1 = grilleJeux1.getCases();
		grille_model2 = grilleJeux2.getCases();
		grille_courante = grille1;
		grille_model_courante = grille_model1;
		numero_grille = 1;
		this.equipe1 = equipe1;
		this.equipe2 = equipe2;
		this.groupListener = groupListener;
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		int[] position = case_appelee(e, grille_courante);

		if (!grille_model_courante[position[0]][position[1]].getTouche())
		{
			grille_model_courante[position[0]][position[1]].touche();

			if (grille_courante[position[0]][position[1]] instanceof CaseJButton)
				// Image d'une case vide touchée.
				grille_courante[position[0]][position[1]].setBackground(GRAY);
			else if (grille_courante[position[0]][position[1]] instanceof BateauJButton)
			{
				if (grille_model_courante[position[0]][position[1]].getBateau().getEstCoule())
				{
					Case[] cases = grille_model_courante[position[0]][position[1]].getBateau().getCases();
					for (int i = 0 ; i < grille_courante.length ; i++)
						for (int j = 0 ; j < grille_courante.length ; j++)
							for (int k = 0 ; k < cases.length ; k++)
								if (grille_model_courante[i][j].equals(cases[k]))
									grille_courante[i][j].setBackground(RED);
				}
				else if (grille_model_courante[position[0]][position[1]].getTouche())
					// Si le bateau est touché, une image de feu ou autre.
					grille_courante[position[0]][position[1]].setBackground(ORANGE);
			}

			if (verifGagnant())
			{
				String message = "";
				if (numero_grille == 1)
					message = equipe1.getNomEquipe() + " a gagné !";
				else if (numero_grille == 2)
					message = equipe2.getNomEquipe() + " a gagné !";
				JOptionPane.showMessageDialog(fenetreJeux, message, "Victoire", INFORMATION_MESSAGE);
				fenetreJeux.setVisible(false);
				groupListener.resetGame(equipe1.getNomEquipe(), equipe2.getNomEquipe());

				groupListener.fenetre = new AccueilJFrame(groupListener);
				groupListener.fenetre.setVisible(true);
			}
			else
			{
				if (numero_grille == 1)
				{
					fenetreJeux.deJeuJPanel1.grille = grille_courante;
					fenetreJeux.creerWidget(1);
					fenetreJeux.validate();
					fenetreJeux.repaint();

					if (grille_model_courante[position[0]][position[1]].getTouche())
					{
						if (grille_courante[position[0]][position[1]] instanceof CaseJButton)
						{
							if (numero_grille == 1)
								equipe1.getJukebox().playSon("EAU");
							else
								equipe2.getJukebox().playSon("EAU");
						}
						else if (grille_courante[position[0]][position[1]] instanceof BateauJButton)
						{
							if (grille_model_courante[position[0]][position[1]].getBateau().getEstCoule())
							{
								if (numero_grille == 1)
									equipe1.getJukebox().playSon("COULER");
								else
									equipe2.getJukebox().playSon("COULER");
							}
							else if (grille_model_courante[position[0]][position[1]].getTouche())
							{
								if (numero_grille == 1)
									equipe1.getJukebox().playSon("TOUCHER");
								else
									equipe2.getJukebox().playSon("TOUCHER");
							}
						}
					}

					set_grille_courante();
					set_grille_model_courante();
					numero_grille++;
					fenetreJeux.creerWidget(2);
				}
				else if (numero_grille == 2)
				{
					fenetreJeux.deJeuJPanel2.grille = grille_courante;
					fenetreJeux.creerWidget(2);
					fenetreJeux.validate();
					fenetreJeux.repaint();

					if (grille_model_courante[position[0]][position[1]].getTouche())
					{
						if (grille_courante[position[0]][position[1]] instanceof CaseJButton)
						{
							if (numero_grille == 1)
								equipe1.getJukebox().playSon("EAU");
							else
								equipe2.getJukebox().playSon("EAU");
						}
						else if (grille_courante[position[0]][position[1]] instanceof BateauJButton)
						{
							if (grille_model_courante[position[0]][position[1]].getBateau().getEstCoule())
							{
								if (numero_grille == 1)
									equipe1.getJukebox().playSon("COULER");
								else
									equipe2.getJukebox().playSon("COULER");
							}
							else if (grille_model_courante[position[0]][position[1]].getTouche())
							{
								if (numero_grille == 1)
									equipe1.getJukebox().playSon("TOUCHER");
								else
									equipe2.getJukebox().playSon("TOUCHER");
							}
						}
					}

					set_grille_courante();
					set_grille_model_courante();
					numero_grille--;
					fenetreJeux.creerWidget(1);
				}
				fenetreJeux.validate();
				fenetreJeux.repaint();
			}
		}
	}

	private int[] case_appelee(ActionEvent e, JButton[][] grille_courante)
	{
		int[] position = new int[2];

		for (int i = 0 ; i < TAILLE_GRILLE ; i++)
			for (int j = 0 ; j < TAILLE_GRILLE ; j++)
				if (e.getSource().equals(grille_courante[i][j]))
				{
					position[0] = i;
					position[1] = j;
				}

		return position;
	}

	public void set_grille_model_courante()
	{
		if (grille_model_courante.equals(grille_model1))
			grille_model_courante = grille_model2;
		else if (grille_model_courante.equals(grille_model2))
			grille_model_courante = grille_model1;
	}

	public void set_grille_courante()
	{
		if (grille_courante.equals(grille1))
			grille_courante = grille2;
		else if (grille_courante.equals(grille2))
			grille_courante = grille1;
	}

	public boolean verifGagnant()
	{
		boolean retour = true;

		for (int i = 0 ; i < TAILLE_GRILLE ; i++)
			for (int j = 0 ; j < TAILLE_GRILLE ; j++)
				if (grille_model_courante[i][j].getBateau() != null)
					if (!grille_model_courante[i][j].getBateau().getEstCoule())
						retour = false;

		return retour;
	}
}