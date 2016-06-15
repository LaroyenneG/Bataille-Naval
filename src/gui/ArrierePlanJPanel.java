package gui;

import engine.GrilleJeux;
import gui.GrilleDeJeuJPanel;
import gui.SelectionBateauJPanel;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ArrierePlanJPanel extends JPanel {
	private static final long serialVersionUID = -553855920680431324L;
	private GrilleJeux grille_jeux;
	private GrilleDeJeuJPanel grilleDeJeuJPanel;
	private SelectionBateauJPanel selectionBateauJPanel;
	private int numGrille;

	public ArrierePlanJPanel(GrilleJeux grille_jeux, int numGrille) {
		super(new GridLayout(1, 1));
		this.grille_jeux = grille_jeux;
		this.grilleDeJeuJPanel = new GrilleDeJeuJPanel(grille_jeux);
		this.selectionBateauJPanel = new SelectionBateauJPanel(grille_jeux);
		this.numGrille = numGrille;
		this.creerWidget();
	}

	public ArrierePlanJPanel(GrilleJeux grilleJeux, JButton[][] grille, SelectionBateauJPanel bateauJPanel, int numGrille) {
		super(new GridLayout(1, 1));
		this.grille_jeux = grilleJeux;
		this.grilleDeJeuJPanel = new GrilleDeJeuJPanel(grilleJeux, grille);
		this.selectionBateauJPanel = bateauJPanel;
		this.numGrille = numGrille;
		this.creerWidget();
	}

	private void creerWidget() {
		Container contp = new Container();
		contp.setLayout(new GridBagLayout());
		GridBagConstraints gbd = new GridBagConstraints();
		gbd.ipadx = 20;
		gbd.ipady = 20;
		contp.add(this.grilleDeJeuJPanel, gbd);
		contp.add(this.selectionBateauJPanel, gbd);
		JLabel jLabel = null;
		if(this.numGrille == 1) {
			jLabel = new JLabel("C\'est au tour de l\'équipe " + this.grille_jeux.getEquipes()[this.numGrille - 1].getNomEquipe() + " de placer ses bateaux.");
			jLabel.setFont(new Font("Bitstream Charter", 1, 20));
		} else if(this.numGrille == 2) {
			jLabel = new JLabel("C\'est au tour de l\'équipe " + this.grille_jeux.getEquipes()[this.numGrille - 1].getNomEquipe() + " de placer ses bateaux.");
			jLabel.setFont(new Font("Bitstream Charter", 1, 20));
		}

		JPanel pPrinc = new JPanel();
		JPanel pLabel = new JPanel();
		JPanel pInitial = new JPanel();
		pLabel.add(jLabel);
		pInitial.add(contp);
		pPrinc.add(pLabel, "North");
		pPrinc.add(pInitial, "South");
		this.add(pPrinc);
	}

	public SelectionBateauJPanel getSelectionBateauJPanel() {
		return this.selectionBateauJPanel;
	}

	public GrilleDeJeuJPanel getGrilleDeJeuJPanel() {
		return this.grilleDeJeuJPanel;
	}
}
