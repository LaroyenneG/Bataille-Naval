package gui;

import java.awt.Color;
import javax.swing.JButton;

public class BateauJButton extends JButton {
	public String typeBateau;
	public int nbSelection;
	public int taille;
	private static final long serialVersionUID = 6321877984607297289L;

	public BateauJButton() {
		this.setText(this.typeBateau);
		this.setBackground(Color.BLACK);
	}

	public BateauJButton(String strType) {
		super(strType);
	}

	public void estPorteAvion() {
		this.nbSelection = 1;
		this.typeBateau = "Porte-avion";
	}

	public void estCroiseur() {
		this.nbSelection = 1;
		this.typeBateau = "Croiseur";
	}

	public void estContretTorpilleur() {
		this.nbSelection = 1;
		this.typeBateau = "Contre-Torpilleur";
	}

	public void estTorpilleur() {
		this.nbSelection = 2;
		this.typeBateau = "Torpilleur";
	}

	public void estSousMarin() {
		this.nbSelection = 1;
		this.typeBateau = "Sous-marin";
	}
}
