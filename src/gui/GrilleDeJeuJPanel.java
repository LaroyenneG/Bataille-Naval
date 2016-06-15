package gui;

import engine.GrilleJeux;
import gui.CaseJButton;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GrilleDeJeuJPanel extends JPanel {
	private static final long serialVersionUID = -416871502500554822L;
	public static final int TAILLE_GRILLE = 10;
	public static final char[] INTITULE_LIGNE = new char[]{'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J'};
	public JButton[][] grille;
	public JLabel[] intitule_ligne = new JLabel[10];
	public JLabel[] intitule_colonne = new JLabel[10];
	private GrilleJeux grille_jeu;

	public GrilleDeJeuJPanel(GrilleJeux gj) {
		this.grille_jeu = gj;
		this.initAttribut();
		this.creerWidget();
	}

	public GrilleDeJeuJPanel(GrilleJeux grilleJeux, JButton[][] grille) {
		this.grille_jeu = grilleJeux;
		this.grille = grille;
		this.intitule_ligne = new JLabel[11];
		int i = 0;

		for(int j = -1; i < 11; ++j) {
			if(i == 0) {
				this.intitule_ligne[i] = new JLabel();
			} else {
				this.intitule_ligne[i] = new JLabel(Character.toString(INTITULE_LIGNE[j]));
			}

			++i;
		}

		this.intitule_colonne = new JLabel[10];

		for(i = 0; i < 10; ++i) {
			this.intitule_colonne[i] = new JLabel(Integer.toString(i + 1));
		}

		this.creerWidget();
	}

	public void initAttribut() {
		this.grille = new JButton[10][10];

		int i;
		int j;
		for(i = 0; i < 10; ++i) {
			for(j = 0; j < 10; ++j) {
				this.grille[i][j] = new CaseJButton(this.grille_jeu.getCases()[i][j]);
			}
		}

		this.intitule_ligne = new JLabel[11];
		i = 0;

		for(j = -1; i < 11; ++j) {
			if(i == 0) {
				this.intitule_ligne[i] = new JLabel();
			} else {
				this.intitule_ligne[i] = new JLabel(Character.toString(INTITULE_LIGNE[j]));
			}

			++i;
		}

		this.intitule_colonne = new JLabel[10];

		for(i = 0; i < 10; ++i) {
			this.intitule_colonne[i] = new JLabel(Integer.toString(i + 1));
		}

	}

	public void creerWidget() {
		JPanel pGrille = new JPanel(new GridLayout(11, 11));

		for(int i = 0; i < 11; ++i) {
			for(int j = 0; j < 11; ++j) {
				if(i == 0 && j == 0) {
					pGrille.add(this.intitule_ligne[i]);
				} else if(i == 0 && j != 0) {
					pGrille.add(this.intitule_colonne[j - 1]);
				} else if(i != 0 && j == 0) {
					pGrille.add(this.intitule_ligne[i]);
				} else if(i != 0 && j != 0) {
					pGrille.add(this.grille[i - 1][j - 1]);
				}
			}
		}

		this.add(pGrille);
	}

	public void setCaseListener(ActionListener actionListener) {
		for(int i = 0; i < 10; ++i) {
			for(int j = 0; j < 10; ++j) {
				this.grille[i][j].addActionListener(actionListener);
			}
		}

	}

	public void setJeuListener(ActionListener actionListener) {
		for(int i = 0; i < 10; ++i) {
			for(int j = 0; j < 10; ++j) {
				this.grille[i][j].addActionListener(actionListener);
			}
		}

	}

	public GrilleJeux getGrille_jeu() {
		return this.grille_jeu;
	}
}