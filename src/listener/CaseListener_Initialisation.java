package listener;

import engine.ContretTorpilleur;
import engine.Croiseur;
import engine.Equipe;
import engine.GrilleJeux;
import engine.PorteAvion;
import engine.SousMarin;
import engine.Torpilleur;
import gui.AccueilJFrame;
import gui.ArrierePlanJPanel;
import gui.BateauJButton;
import gui.CaseJButton;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import listener.GroupListener;

public class CaseListener_Initialisation implements ActionListener {
	public AccueilJFrame fenetre;
	public GrilleJeux grille_jeu;
	public Equipe equipe;
	public JButton[][] grille;
	public BateauJButton[] bateauJButton;
	public ArrayList<BateauJButton[]> bateauJButtons;
	public int[][] position_save;
	public int numero_grille;
	public GroupListener groupListener;

	public CaseListener_Initialisation(GrilleJeux grille_jeu, AccueilJFrame fenetre, Equipe equipe, int numero_grille, GroupListener groupListener) {
		this.grille_jeu = grille_jeu;
		this.fenetre = fenetre;
		this.equipe = equipe;
		this.numero_grille = numero_grille;
		this.groupListener = groupListener;
		this.grille = ((ArrierePlanJPanel)fenetre.getjPanel()).getGrilleDeJeuJPanel().grille;
		this.bateauJButton = null;
		this.position_save = (int[][])null;
	}

	public void actionPerformed(ActionEvent e) {
		if(e.getSource() instanceof CaseJButton && this.bateauJButton != null) {
			if(this.position_save == null) {
				this.position_save = new int[2][2];
				this.case_appelee(e, 0);
				this.cases_possibles();
			} else if(((CaseJButton)e.getSource()).getBackground().equals(Color.RED)) {
				this.case_appelee(e, 1);
				this.place_bateau();
				this.position_save = (int[][])null;
				this.bateauJButton = null;
			} else {
				this.reset_color(false);
				this.position_save = (int[][])null;
				this.bateauJButton = null;
			}
		} else if(e.getSource() instanceof BateauJButton && !((JButton)e.getSource()).getBackground().equals(Color.RED) && this.bateauJButton == null) {
			this.setBateau(e);
		} else if(e.getSource().equals(((ArrierePlanJPanel)this.fenetre.getjPanel()).getSelectionBateauJPanel().bValider)) {
			this.valider();
		}

	}

	private void case_appelee(ActionEvent e, int numero_case) {
		for(int i = 0; i < 10; ++i) {
			for(int j = 0; j < 10; ++j) {
				if(e.getSource().equals(this.grille[i][j])) {
					this.position_save[numero_case][0] = i;
					this.position_save[numero_case][1] = j;
				}
			}
		}

	}

	private void cases_possibles() {
		int i;
		if(this.position_save[0][0] - this.bateauJButton.length + 1 >= 0) {
			for(i = this.position_save[0][0] - this.bateauJButton.length + 1; i <= this.position_save[0][0]; ++i) {
				this.grille[i][this.position_save[0][1]].setBackground(Color.ORANGE);
			}

			this.grille[this.position_save[0][0] - this.bateauJButton.length + 1][this.position_save[0][1]].setBackground(Color.RED);
		}

		if(this.position_save[0][0] + this.bateauJButton.length - 1 < 10) {
			for(i = this.position_save[0][0]; i <= this.position_save[0][0] + this.bateauJButton.length - 1; ++i) {
				this.grille[i][this.position_save[0][1]].setBackground(Color.ORANGE);
			}

			this.grille[this.position_save[0][0] + this.bateauJButton.length - 1][this.position_save[0][1]].setBackground(Color.RED);
		}

		if(this.position_save[0][1] - this.bateauJButton.length + 1 >= 0) {
			for(i = this.position_save[0][1] - this.bateauJButton.length + 1; i <= this.position_save[0][1]; ++i) {
				this.grille[this.position_save[0][0]][i].setBackground(Color.ORANGE);
			}

			this.grille[this.position_save[0][0]][this.position_save[0][1] - this.bateauJButton.length + 1].setBackground(Color.RED);
		}

		if(this.position_save[0][1] + this.bateauJButton.length - 1 < 10) {
			for(i = this.position_save[0][1]; i <= this.position_save[0][1] + this.bateauJButton.length - 1; ++i) {
				this.grille[this.position_save[0][0]][i].setBackground(Color.ORANGE);
			}

			this.grille[this.position_save[0][0]][this.position_save[0][1] + this.bateauJButton.length - 1].setBackground(Color.RED);
		}

		this.grille[this.position_save[0][0]][this.position_save[0][1]].setBackground(Color.PINK);
	}

	private void place_bateau() {
		try {
			String e;
			byte j;
			if(this.position_save[0][0] == this.position_save[1][0]) {
				e = this.bateauJButton[0].typeBateau;
				j = -1;
				switch(e.hashCode()) {
					case -2116255080:
						if(e.equals("Croiseur")) {
							j = 1;
						}
						break;
					case -1844015918:
						if(e.equals("Torpilleur")) {
							j = 3;
						}
						break;
					case -1124121462:
						if(e.equals("Porte-avion")) {
							j = 0;
						}
						break;
					case -583766374:
						if(e.equals("Contre-Torpilleur")) {
							j = 2;
						}
						break;
					case 231331216:
						if(e.equals("Sous-marin")) {
							j = 4;
						}
				}

				switch(j) {
					case 0:
						new PorteAvion(this.position_save[0][0], Math.min(this.position_save[0][1], this.position_save[1][1]), this.position_save[1][0], Math.max(this.position_save[0][1], this.position_save[1][1]), this.equipe);
						break;
					case 1:
						new Croiseur(this.position_save[0][0], Math.min(this.position_save[0][1], this.position_save[1][1]), this.position_save[1][0], Math.max(this.position_save[0][1], this.position_save[1][1]), this.equipe);
						break;
					case 2:
						new ContretTorpilleur(this.position_save[0][0], Math.min(this.position_save[0][1], this.position_save[1][1]), this.position_save[1][0], Math.max(this.position_save[0][1], this.position_save[1][1]), this.equipe);
						break;
					case 3:
						new Torpilleur(this.position_save[0][0], Math.min(this.position_save[0][1], this.position_save[1][1]), this.position_save[1][0], Math.max(this.position_save[0][1], this.position_save[1][1]), this.equipe);
						break;
					case 4:
						new SousMarin(this.position_save[0][0], Math.min(this.position_save[0][1], this.position_save[1][1]), this.position_save[1][0], Math.max(this.position_save[0][1], this.position_save[1][1]), this.equipe);
				}
			} else if(this.position_save[0][1] == this.position_save[1][1]) {
				e = this.bateauJButton[0].typeBateau;
				j = -1;
				switch(e.hashCode()) {
					case -2116255080:
						if(e.equals("Croiseur")) {
							j = 1;
						}
						break;
					case -1844015918:
						if(e.equals("Torpilleur")) {
							j = 3;
						}
						break;
					case -1124121462:
						if(e.equals("Porte-avion")) {
							j = 0;
						}
						break;
					case -583766374:
						if(e.equals("Contre-Torpilleur")) {
							j = 2;
						}
						break;
					case 231331216:
						if(e.equals("Sous-marin")) {
							j = 4;
						}
				}

				switch(j) {
					case 0:
						new PorteAvion(Math.min(this.position_save[0][0], this.position_save[1][0]), this.position_save[0][1], Math.max(this.position_save[0][0], this.position_save[1][0]), this.position_save[1][1], this.equipe);
						break;
					case 1:
						new Croiseur(Math.min(this.position_save[0][0], this.position_save[1][0]), this.position_save[0][1], Math.max(this.position_save[0][0], this.position_save[1][0]), this.position_save[1][1], this.equipe);
						break;
					case 2:
						new ContretTorpilleur(Math.min(this.position_save[0][0], this.position_save[1][0]), this.position_save[0][1], Math.max(this.position_save[0][0], this.position_save[1][0]), this.position_save[1][1], this.equipe);
						break;
					case 3:
						new Torpilleur(Math.min(this.position_save[0][0], this.position_save[1][0]), this.position_save[0][1], Math.max(this.position_save[0][0], this.position_save[1][0]), this.position_save[1][1], this.equipe);
						break;
					case 4:
						new SousMarin(Math.min(this.position_save[0][0], this.position_save[1][0]), this.position_save[0][1], Math.max(this.position_save[0][0], this.position_save[1][0]), this.position_save[1][1], this.equipe);
				}
			}

			String var3;
			byte var4;
			int var6;
			int var7;
			if(this.position_save[1][0] - this.bateauJButton.length + 1 == this.position_save[0][0]) {
				var6 = this.position_save[0][0];

				for(var7 = 0; var6 <= this.position_save[1][0]; ++var7) {
					this.grille[var6][this.position_save[1][1]] = new BateauJButton();
					var3 = this.bateauJButton[var7].typeBateau;
					var4 = -1;
					switch(var3.hashCode()) {
						case -2116255080:
							if(var3.equals("Croiseur")) {
								var4 = 1;
							}
							break;
						case -1844015918:
							if(var3.equals("Torpilleur")) {
								var4 = 3;
							}
							break;
						case -1124121462:
							if(var3.equals("Porte-avion")) {
								var4 = 0;
							}
							break;
						case -583766374:
							if(var3.equals("Contre-Torpilleur")) {
								var4 = 2;
							}
							break;
						case 231331216:
							if(var3.equals("Sous-marin")) {
								var4 = 4;
							}
					}

					switch(var4) {
						case 0:
							((BateauJButton)this.grille[var6][this.position_save[1][1]]).estPorteAvion();
							break;
						case 1:
							((BateauJButton)this.grille[var6][this.position_save[1][1]]).estCroiseur();
							break;
						case 2:
							((BateauJButton)this.grille[var6][this.position_save[1][1]]).estContretTorpilleur();
							break;
						case 3:
							((BateauJButton)this.grille[var6][this.position_save[1][1]]).estTorpilleur();
							break;
						case 4:
							((BateauJButton)this.grille[var6][this.position_save[1][1]]).estSousMarin();
					}

					++var6;
				}
			} else if(this.position_save[1][0] + this.bateauJButton.length - 1 == this.position_save[0][0]) {
				var6 = this.position_save[1][0];

				for(var7 = 0; var6 <= this.position_save[0][0]; ++var7) {
					this.grille[var6][this.position_save[1][1]] = new BateauJButton();
					var3 = this.bateauJButton[var7].typeBateau;
					var4 = -1;
					switch(var3.hashCode()) {
						case -2116255080:
							if(var3.equals("Croiseur")) {
								var4 = 1;
							}
							break;
						case -1844015918:
							if(var3.equals("Torpilleur")) {
								var4 = 3;
							}
							break;
						case -1124121462:
							if(var3.equals("Porte-avion")) {
								var4 = 0;
							}
							break;
						case -583766374:
							if(var3.equals("Contre-Torpilleur")) {
								var4 = 2;
							}
							break;
						case 231331216:
							if(var3.equals("Sous-marin")) {
								var4 = 4;
							}
					}

					switch(var4) {
						case 0:
							((BateauJButton)this.grille[var6][this.position_save[1][1]]).estPorteAvion();
							break;
						case 1:
							((BateauJButton)this.grille[var6][this.position_save[1][1]]).estCroiseur();
							break;
						case 2:
							((BateauJButton)this.grille[var6][this.position_save[1][1]]).estContretTorpilleur();
							break;
						case 3:
							((BateauJButton)this.grille[var6][this.position_save[1][1]]).estTorpilleur();
							break;
						case 4:
							((BateauJButton)this.grille[var6][this.position_save[1][1]]).estSousMarin();
					}

					++var6;
				}
			} else if(this.position_save[1][1] - this.bateauJButton.length + 1 == this.position_save[0][1]) {
				var6 = this.position_save[0][1];

				for(var7 = 0; var6 <= this.position_save[1][1]; ++var7) {
					this.grille[this.position_save[1][0]][var6] = new BateauJButton();
					var3 = this.bateauJButton[var7].typeBateau;
					var4 = -1;
					switch(var3.hashCode()) {
						case -2116255080:
							if(var3.equals("Croiseur")) {
								var4 = 1;
							}
							break;
						case -1844015918:
							if(var3.equals("Torpilleur")) {
								var4 = 3;
							}
							break;
						case -1124121462:
							if(var3.equals("Porte-avion")) {
								var4 = 0;
							}
							break;
						case -583766374:
							if(var3.equals("Contre-Torpilleur")) {
								var4 = 2;
							}
							break;
						case 231331216:
							if(var3.equals("Sous-marin")) {
								var4 = 4;
							}
					}

					switch(var4) {
						case 0:
							((BateauJButton)this.grille[this.position_save[1][0]][var6]).estPorteAvion();
							break;
						case 1:
							((BateauJButton)this.grille[this.position_save[1][0]][var6]).estCroiseur();
							break;
						case 2:
							((BateauJButton)this.grille[this.position_save[1][0]][var6]).estContretTorpilleur();
							break;
						case 3:
							((BateauJButton)this.grille[this.position_save[1][0]][var6]).estTorpilleur();
							break;
						case 4:
							((BateauJButton)this.grille[this.position_save[1][0]][var6]).estSousMarin();
					}

					++var6;
				}
			} else if(this.position_save[1][1] + this.bateauJButton.length - 1 == this.position_save[0][1]) {
				var6 = this.position_save[1][1];

				for(var7 = 0; var6 <= this.position_save[0][1]; ++var7) {
					this.grille[this.position_save[1][0]][var6] = new BateauJButton();
					var3 = this.bateauJButton[var7].typeBateau;
					var4 = -1;
					switch(var3.hashCode()) {
						case -2116255080:
							if(var3.equals("Croiseur")) {
								var4 = 1;
							}
							break;
						case -1844015918:
							if(var3.equals("Torpilleur")) {
								var4 = 3;
							}
							break;
						case -1124121462:
							if(var3.equals("Porte-avion")) {
								var4 = 0;
							}
							break;
						case -583766374:
							if(var3.equals("Contre-Torpilleur")) {
								var4 = 2;
							}
							break;
						case 231331216:
							if(var3.equals("Sous-marin")) {
								var4 = 4;
							}
					}

					switch(var4) {
						case 0:
							((BateauJButton)this.grille[this.position_save[1][0]][var6]).estPorteAvion();
							break;
						case 1:
							((BateauJButton)this.grille[this.position_save[1][0]][var6]).estCroiseur();
							break;
						case 2:
							((BateauJButton)this.grille[this.position_save[1][0]][var6]).estContretTorpilleur();
							break;
						case 3:
							((BateauJButton)this.grille[this.position_save[1][0]][var6]).estTorpilleur();
							break;
						case 4:
							((BateauJButton)this.grille[this.position_save[1][0]][var6]).estSousMarin();
					}

					++var6;
				}
			}

			this.reset_color(true);
		} catch (Exception var5) {
			this.reset_color(false);
			this.fenetre.creerDialogError(var5.getMessage());
		}

	}

	private void reset_color(boolean bateau_place) {
		int i;
		for(i = 0; i < this.bateauJButton.length; ++i) {
			if(bateau_place) {
				this.bateauJButton[i].setBackground(Color.RED);
			} else {
				this.bateauJButton[i].setBackground(Color.BLACK);
			}
		}

		for(i = 0; i < this.grille.length; ++i) {
			for(int j = 0; j < this.grille.length; ++j) {
				if(this.grille[i][j] instanceof CaseJButton) {
					this.grille[i][j].setBackground(Color.BLUE);
				} else if(this.grille[i][j] instanceof BateauJButton) {
					this.grille[i][j].setBackground(Color.BLACK);
				}
			}
		}

		if(this.numero_grille == 1) {
			this.groupListener.arrierePlanJPanel1 = new ArrierePlanJPanel(this.grille_jeu, this.grille, this.groupListener.arrierePlanJPanel1.getSelectionBateauJPanel(), this.numero_grille);
			this.fenetre.setjPanel(this.groupListener.arrierePlanJPanel1);
		} else {
			this.groupListener.arrierePlanJPanel2 = new ArrierePlanJPanel(this.grille_jeu, this.grille, this.groupListener.arrierePlanJPanel2.getSelectionBateauJPanel(), this.numero_grille);
			this.fenetre.setjPanel(this.groupListener.arrierePlanJPanel2);
		}

		this.fenetre.validate();
		this.fenetre.repaint();
	}

	private void setBateau(ActionEvent e) {
		boolean stop = false;

		for(int i = 0; i < this.bateauJButtons.size() && !stop; ++i) {
			for(int j = 0; j < ((BateauJButton[])this.bateauJButtons.get(i)).length && !stop; ++j) {
				if(e.getSource().equals(((BateauJButton[])this.bateauJButtons.get(i))[j])) {
					this.bateauJButton = new BateauJButton[((BateauJButton[])this.bateauJButtons.get(i)).length];

					for(int k = 0; k < ((BateauJButton[])this.bateauJButtons.get(i)).length; ++k) {
						this.bateauJButton[k] = ((BateauJButton[])this.bateauJButtons.get(i))[k];
						this.bateauJButton[k].setBackground(Color.ORANGE);
					}

					stop = true;
				}
			}
		}

	}

	private void valider() {
		boolean valider = true;

		for(int i = 0; i < this.bateauJButtons.size(); ++i) {
			for(int j = 0; j < ((BateauJButton[])this.bateauJButtons.get(i)).length; ++j) {
				if(!((BateauJButton[])this.bateauJButtons.get(i))[j].getBackground().equals(Color.RED)) {
					valider = false;
				}
			}
		}

		if(valider && this.numero_grille == 1) {
			this.groupListener.createGrilleDeJeuJPanel(2);
		} else if(valider && this.numero_grille == 2) {
			this.groupListener.createFenetreJeu();
		} else {
			this.fenetre.creerDialogError("Veuillez placer tous vos bateaux.");
		}

	}
}
