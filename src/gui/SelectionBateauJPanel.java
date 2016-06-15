package gui;

import engine.GrilleJeux;
import gui.BateauJButton;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import listener.CaseListener_Initialisation;

public class SelectionBateauJPanel extends JPanel {
	public ArrayList<BateauJButton[]> bateauJButtons;
	private GrilleJeux grilleJeux;
	public JButton bValider;
	private static final long serialVersionUID = 3937304672276095355L;

	public SelectionBateauJPanel(GrilleJeux grilleJeux) {
		this.grilleJeux = grilleJeux;
		this.initPanel();
		this.createPanel();
	}

	private void initPanel() {
		this.bateauJButtons = new ArrayList();
		BateauJButton[] bateau = new BateauJButton[5];

		int j;
		for(j = 0; j < 5; ++j) {
			bateau[j] = new BateauJButton();
			bateau[j].estPorteAvion();
			bateau[j].setBackground(Color.BLACK);
		}

		this.bateauJButtons.add(bateau);
		bateau = new BateauJButton[4];

		for(j = 0; j < 4; ++j) {
			bateau[j] = new BateauJButton();
			bateau[j].estCroiseur();
			bateau[j].setBackground(Color.BLACK);
		}

		this.bateauJButtons.add(bateau);
		bateau = new BateauJButton[3];

		for(j = 0; j < 3; ++j) {
			bateau[j] = new BateauJButton();
			bateau[j].estContretTorpilleur();
			bateau[j].setBackground(Color.BLACK);
		}

		this.bateauJButtons.add(bateau);
		bateau = new BateauJButton[3];

		for(j = 0; j < 3; ++j) {
			bateau[j] = new BateauJButton();
			bateau[j].estSousMarin();
			bateau[j].setBackground(Color.BLACK);
		}

		this.bateauJButtons.add(bateau);
		bateau = new BateauJButton[2];

		for(j = 0; j < 2; ++j) {
			bateau[j] = new BateauJButton();
			bateau[j].estTorpilleur();
			bateau[j].setBackground(Color.BLACK);
		}

		this.bateauJButtons.add(bateau);
		this.bValider = new JButton("Valider les bateaux");
	}

	private void createPanel() {
		this.setLayout(new GridLayout(6, 1));
		JPanel pPorteAvion = new JPanel(new GridLayout(2, 1));
		JPanel pCroiseur = new JPanel(new GridLayout(2, 1));
		JPanel pContretTorpilleur = new JPanel(new GridLayout(2, 1));
		JPanel pSousMarin = new JPanel(new GridLayout(2, 1));
		JPanel pTorpilleur = new JPanel(new GridLayout(2, 1));
		JLabel lPorteAvion = new JLabel("Porte-Avion");
		JLabel lCroiseur = new JLabel("Croiseur");
		JLabel lContretTorpilleur = new JLabel("Contre-Torpilleur");
		JLabel lSousMarin = new JLabel("Sous-Marin");
		JLabel lTorpilleur = new JLabel("Torpilleur");
		JPanel pInPorteAvion = new JPanel(new GridLayout(1, 5));
		JPanel pInCroiseur = new JPanel(new GridLayout(1, 5));
		JPanel pInContretTorpilleur = new JPanel(new GridLayout(1, 5));
		JPanel pInSousMarin = new JPanel(new GridLayout(1, 5));
		JPanel pInTorpilleur = new JPanel(new GridLayout(1, 5));
		JPanel pValider = new JPanel();

		int i;
		for(i = 0; i < 5; ++i) {
			pInPorteAvion.add(((BateauJButton[])this.bateauJButtons.get(0))[i]);
		}

		for(i = 0; i < 4; ++i) {
			pInCroiseur.add(((BateauJButton[])this.bateauJButtons.get(1))[i]);
		}

		for(i = 0; i < 3; ++i) {
			pInContretTorpilleur.add(((BateauJButton[])this.bateauJButtons.get(2))[i]);
		}

		for(i = 0; i < 3; ++i) {
			pInSousMarin.add(((BateauJButton[])this.bateauJButtons.get(3))[i]);
		}

		for(i = 0; i < 2; ++i) {
			pInTorpilleur.add(((BateauJButton[])this.bateauJButtons.get(4))[i]);
		}

		pPorteAvion.add(lPorteAvion);
		pPorteAvion.add(pInPorteAvion);
		pCroiseur.add(lCroiseur);
		pCroiseur.add(pInCroiseur);
		pContretTorpilleur.add(lContretTorpilleur);
		pContretTorpilleur.add(pInContretTorpilleur);
		pSousMarin.add(lSousMarin);
		pSousMarin.add(pInSousMarin);
		pTorpilleur.add(lTorpilleur);
		pTorpilleur.add(pInTorpilleur);
		pValider.add(this.bValider);
		this.add(pPorteAvion);
		this.add(pCroiseur);
		this.add(pContretTorpilleur);
		this.add(pSousMarin);
		this.add(pTorpilleur);
		this.add(pValider);
	}

	public void setCaseListener(ActionListener actionListener) {
		for(int i = 0; i < this.bateauJButtons.size(); ++i) {
			for(int j = 0; j < ((BateauJButton[])this.bateauJButtons.get(i)).length; ++j) {
				((BateauJButton[])this.bateauJButtons.get(i))[j].addActionListener(actionListener);
			}
		}

		((CaseListener_Initialisation)actionListener).bateauJButtons = this.bateauJButtons;
		this.bValider.addActionListener(actionListener);
	}
}

