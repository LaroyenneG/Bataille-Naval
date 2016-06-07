/*******************************************************************************
 * 2016, All rights reserved.
 *******************************************************************************/
package gui;

import static java.awt.Color.BLACK;

import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import engine.GrilleJeux;
import listener.CaseListener_Initialisation;

/**
 * Description of SelectionBateauJPanel.
 * 
 * @author nicolas
 */
public class SelectionBateauJPanel extends JPanel
{
	// Start of user code (user defined attributes for SelectionBateauJPanel)
	public ArrayList<BateauJButton[]>	bateauJButtons;
	private GrilleJeux					grilleJeux;
	public JButton						bValider;
	// End of user code

	private static final long			serialVersionUID	= 3937304672276095355L;

	public SelectionBateauJPanel(GrilleJeux grilleJeux)
	{
		// Start of user code constructor for SelectionBateauJPanel)
		super();
		this.grilleJeux = grilleJeux;

		initPanel();
		createPanel();
		// End of user code
	}

	// Start of user code (user defined methods for SelectionBateauJPanel)
	private void initPanel()
	{
		bateauJButtons = new ArrayList<BateauJButton[]>();

		BateauJButton[] bateau = new BateauJButton[5];
		for (int j = 0 ; j < 5 ; j++)
		{
			bateau[j] = new BateauJButton();
			bateau[j].estPorteAvion();
			bateau[j].setBackground(BLACK);
		}
		bateauJButtons.add(bateau);

		bateau = new BateauJButton[4];
		for (int j = 0 ; j < 4 ; j++)
		{
			bateau[j] = new BateauJButton();
			bateau[j].estCroiseur();
			bateau[j].setBackground(BLACK);
		}
		bateauJButtons.add(bateau);

		bateau = new BateauJButton[3];
		for (int j = 0 ; j < 3 ; j++)
		{
			bateau[j] = new BateauJButton();
			bateau[j].estContretTorpilleur();
			bateau[j].setBackground(BLACK);
		}
		bateauJButtons.add(bateau);

		bateau = new BateauJButton[3];
		for (int j = 0 ; j < 3 ; j++)
		{
			bateau[j] = new BateauJButton();
			bateau[j].estSousMarin();
			bateau[j].setBackground(BLACK);
		}
		bateauJButtons.add(bateau);

		bateau = new BateauJButton[2];
		for (int j = 0 ; j < 2 ; j++)
		{
			bateau[j] = new BateauJButton();
			bateau[j].estTorpilleur();
			bateau[j].setBackground(BLACK);
		}
		bateauJButtons.add(bateau);

		bValider = new JButton("Valider les bateaux");
	}

	private void createPanel()
	{
		setLayout(new GridLayout(6, 1));

		// creation des panel de bouton de selection
		JPanel pPorteAvion = new JPanel(new GridLayout(2, 1));
		JPanel pCroiseur = new JPanel(new GridLayout(2, 1));
		JPanel pContretTorpilleur = new JPanel(new GridLayout(2, 1));
		JPanel pSousMarin = new JPanel(new GridLayout(2, 1));
		JPanel pTorpilleur = new JPanel(new GridLayout(2, 1));

		// creation des label pour indiquer le type de bateaux A ajouter aux
		// panel de bouton de selection
		JLabel lPorteAvion = new JLabel("Porte-Avion");
		JLabel lCroiseur = new JLabel("Croiseur");
		JLabel lContretTorpilleur = new JLabel("Contre-Torpilleur");
		JLabel lSousMarin = new JLabel("Sous-Marin");
		JLabel lTorpilleur = new JLabel("Torpilleur");

		// creation des Panel de bouton de selection de bateau A ajouter aux
		// panel de bouton de selection
		JPanel pInPorteAvion = new JPanel(new GridLayout(1, 5));
		JPanel pInCroiseur = new JPanel(new GridLayout(1, 5));
		JPanel pInContretTorpilleur = new JPanel(new GridLayout(1, 5));
		JPanel pInSousMarin = new JPanel(new GridLayout(1, 5));
		JPanel pInTorpilleur = new JPanel(new GridLayout(1, 5));

		// creation du panel et du bouton pour valider l'initialisation des
		// bateaux

		JPanel pValider = new JPanel();

		///////
		// assamblage des attribut de selection
		//////

		for (int i = 0 ; i < 5 ; i++)
			pInPorteAvion.add(bateauJButtons.get(0)[i]);

		for (int i = 0 ; i < 4 ; i++)
			pInCroiseur.add(bateauJButtons.get(1)[i]);

		for (int i = 0 ; i < 3 ; i++)
			pInContretTorpilleur.add(bateauJButtons.get(2)[i]);

		for (int i = 0 ; i < 3 ; i++)
			pInSousMarin.add(bateauJButtons.get(3)[i]);

		for (int i = 0 ; i < 2 ; i++)
			pInTorpilleur.add(bateauJButtons.get(4)[i]);

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

		pValider.add(bValider);

		add(pPorteAvion);
		add(pCroiseur);
		add(pContretTorpilleur);
		add(pSousMarin);
		add(pTorpilleur);
		add(pValider);
	}

	public void setCaseListener(ActionListener actionListener)
	{
		for (int i = 0 ; i < bateauJButtons.size() ; i++)
			for (int j = 0 ; j < bateauJButtons.get(i).length ; j++)
				bateauJButtons.get(i)[j].addActionListener(actionListener);

		((CaseListener_Initialisation) actionListener).bateauJButtons = bateauJButtons;

		bValider.addActionListener(actionListener);
	}
	// End of user code

}
