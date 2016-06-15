package gui;

import engine.Equipe;
import gui.GrilleDeJeuJPanel;
import gui.MenuSuperieurJMenuBar;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class FenetreJeux extends JFrame {
	private static final long serialVersionUID = 7002519433302593785L;
	private Equipe[] equipes;
	MenuSuperieurJMenuBar menuBar;
	public GrilleDeJeuJPanel deJeuJPanel1;
	public GrilleDeJeuJPanel deJeuJPanel2;

	public FenetreJeux(Equipe[] e, GrilleDeJeuJPanel deJeuJPanel1, GrilleDeJeuJPanel deJeuJPanel2) {
		this.deJeuJPanel1 = deJeuJPanel1;
		this.deJeuJPanel2 = deJeuJPanel2;

		for(int i = 0; i < 10; ++i) {
			for(int j = 0; j < 10; ++j) {
				deJeuJPanel1.grille[i][j].setBackground(Color.BLUE);
				deJeuJPanel2.grille[i][j].setBackground(Color.BLUE);
			}
		}

		this.equipes = e;
		this.creerWidget(1);
		this.setTitle("Jeux");
		this.setSize(400, 400);
		this.setLocationRelativeTo((Component)null);
		this.setVisible(true);
		this.setResizable(false);
		this.setDefaultCloseOperation(3);
	}

	public void creerWidget(int numero_grille) {
		this.menuBar = new MenuSuperieurJMenuBar(this);
		this.setJMenuBar(this.menuBar);
		JPanel pPrinc = new JPanel();
		JPanel pGrille = new JPanel();
		JPanel pLabel = new JPanel();
		JLabel afficheNomEquipeCourant = new JLabel();
		if(numero_grille == 1) {
			afficheNomEquipeCourant = new JLabel("C\'est au tour de l\'équipe " + this.equipes[1].getNomEquipe() + " de jouer.");
			afficheNomEquipeCourant.setFont(new Font("Bitstream Charter", 1, 20));
			pGrille.add(this.deJeuJPanel1);
		} else if(numero_grille == 2) {
			afficheNomEquipeCourant = new JLabel("C\'est au tour de l\'équipe " + this.equipes[0].getNomEquipe() + " de jouer.");
			afficheNomEquipeCourant.setFont(new Font("Bitstream Charter", 1, 20));
			pGrille.add(this.deJeuJPanel2);
		}

		pLabel.add(afficheNomEquipeCourant);
		pPrinc.add(pLabel, "North");
		pPrinc.add(pGrille, "South");
		this.setContentPane(pPrinc);
	}

	public void setListener(ActionListener actionListener) {
		int i;
		int j;
		for(i = 0; i < 10; ++i) {
			for(j = 0; j < 10; ++j) {
				this.deJeuJPanel1.grille[i][j].addActionListener(actionListener);
			}
		}

		for(i = 0; i < 10; ++i) {
			for(j = 0; j < 10; ++j) {
				this.deJeuJPanel2.grille[i][j].addActionListener(actionListener);
			}
		}

	}

	public Equipe[] getEquipes() {
		return this.equipes;
	}
}