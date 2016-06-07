/*******************************************************************************
 * 2016, All rights reserved.
 *******************************************************************************/
package gui;

import engine.GrilleJeux;

import javax.swing.*;
import java.awt.*;

// Start of user code (user defined imports)

// End of user code

/**
 * Description of ArrierePlanJPanel.
 * 
 * @author nicolas
 */

public class ArrierePlanJPanel extends JPanel
{

	private static final long		serialVersionUID	= -553855920680431324L;

	private GrilleJeux				grille_jeux;

	private GrilleDeJeuJPanel		grilleDeJeuJPanel;
	private SelectionBateauJPanel	selectionBateauJPanel;

	private int numGrille;

	public ArrierePlanJPanel(GrilleJeux grille_jeux, int numGrille)
	{
		// Start of user code constructor for ArrierePlanJPanel)
		// super();
		super(new GridLayout(1, 1));

		this.grille_jeux = grille_jeux;

		grilleDeJeuJPanel = new GrilleDeJeuJPanel(grille_jeux);
		selectionBateauJPanel = new SelectionBateauJPanel(grille_jeux);
		this.numGrille = numGrille;

		creerWidget(); // Initialisation des attributs .

		// End of user code
	}

	public ArrierePlanJPanel(GrilleJeux grilleJeux, JButton[][] grille, SelectionBateauJPanel bateauJPanel, int numGrille)
	{
		super(new GridLayout(1, 1));

		this.grille_jeux = grilleJeux;
		this.grilleDeJeuJPanel = new GrilleDeJeuJPanel(grilleJeux, grille);
		this.selectionBateauJPanel = bateauJPanel;
		this.numGrille = numGrille;

		creerWidget();
	}

	private void creerWidget()
	{
		// disposition des panels
		Container contp = new Container();
		contp.setLayout(new GridBagLayout());
		GridBagConstraints gbd = new GridBagConstraints();
		gbd.ipadx = 20;
		gbd.ipady = 20;
		contp.add(grilleDeJeuJPanel, gbd);
		contp.add(selectionBateauJPanel, gbd);

		JLabel jLabel = null;
		if (numGrille==1){
			jLabel = new JLabel(
					"C'est au tour de l'équipe " + grille_jeux.getEquipes()[numGrille-1].getNomEquipe()
							+ " de placer ses bateaux.");
			jLabel.setFont(new Font("Bitstream Charter", Font.BOLD, 20));
		}else if (numGrille==2){
			jLabel = new JLabel(
					"C'est au tour de l'équipe " + grille_jeux.getEquipes()[numGrille-1].getNomEquipe()
							+ " de placer ses bateaux.");
			jLabel.setFont(new Font("Bitstream Charter", Font.BOLD, 20));
		}

		JPanel pPrinc = new JPanel();


		JPanel pLabel = new JPanel();
		JPanel pInitial = new JPanel();

		pLabel.add(jLabel);
		pInitial.add(contp);

		pPrinc.add(pLabel, BorderLayout.NORTH);
		pPrinc.add(pInitial, BorderLayout.SOUTH);

		add(pPrinc);

	}

	public SelectionBateauJPanel getSelectionBateauJPanel()
	{
		return selectionBateauJPanel;
	}

	public GrilleDeJeuJPanel getGrilleDeJeuJPanel()
	{
		return grilleDeJeuJPanel;
	}
}
