package listener;

import engine.Case;
import engine.Equipe;
import engine.GrilleJeux;
import gui.AccueilJFrame;
import gui.BateauJButton;
import gui.CaseJButton;
import gui.FenetreJeux;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import listener.GroupListener;

public class JeuListener implements ActionListener {
	public FenetreJeux fenetreJeux;
	public JButton[][] grille1;
	public JButton[][] grille2;
	public Case[][] grille_model1;
	public Case[][] grille_model2;
	private Case[][] grille_model_courante;
	private JButton[][] grille_courante;
	private int numero_grille;
	public Equipe equipe1;
	public Equipe equipe2;
	public GroupListener groupListener;

	public JeuListener(FenetreJeux fenetre, GrilleJeux grilleJeux1, GrilleJeux grilleJeux2, Equipe equipe1, Equipe equipe2, GroupListener groupListener) {
		this.fenetreJeux = fenetre;
		this.grille1 = fenetre.deJeuJPanel1.grille;
		this.grille2 = fenetre.deJeuJPanel2.grille;
		this.grille_model1 = grilleJeux1.getCases();
		this.grille_model2 = grilleJeux2.getCases();
		this.grille_courante = this.grille1;
		this.grille_model_courante = this.grille_model1;
		this.numero_grille = 1;
		this.equipe1 = equipe1;
		this.equipe2 = equipe2;
		this.groupListener = groupListener;
	}

	public void actionPerformed(ActionEvent e) {
		int[] position = this.case_appelee(e, this.grille_courante);
		if(!this.grille_model_courante[position[0]][position[1]].getTouche()) {
			this.grille_model_courante[position[0]][position[1]].touche();
			if(this.grille_courante[position[0]][position[1]] instanceof CaseJButton) {
				this.grille_courante[position[0]][position[1]].setBackground(Color.GRAY);
			} else if(this.grille_courante[position[0]][position[1]] instanceof BateauJButton) {
				if(!this.grille_model_courante[position[0]][position[1]].getBateau().getEstCoule()) {
					if(this.grille_model_courante[position[0]][position[1]].getTouche()) {
						this.grille_courante[position[0]][position[1]].setBackground(Color.ORANGE);
					}
				} else {
					Case[] message = this.grille_model_courante[position[0]][position[1]].getBateau().getCases();

					for(int i = 0; i < this.grille_courante.length; ++i) {
						for(int j = 0; j < this.grille_courante.length; ++j) {
							for(int k = 0; k < message.length; ++k) {
								if(this.grille_model_courante[i][j].equals(message[k])) {
									this.grille_courante[i][j].setBackground(Color.RED);
								}
							}
						}
					}
				}
			}

			if(this.verifGagnant()) {
				if(this.numero_grille == 1) {
					this.fenetreJeux.deJeuJPanel1.grille = this.grille_courante;
					this.fenetreJeux.creerWidget(1);
					this.fenetreJeux.validate();
					this.fenetreJeux.repaint();
					if(this.grille_model_courante[position[0]][position[1]].getTouche()) {
						if(this.grille_courante[position[0]][position[1]] instanceof CaseJButton) {
							if(this.numero_grille == 1) {
								this.equipe1.getJukebox().playSon("EAU");
							} else {
								this.equipe2.getJukebox().playSon("EAU");
							}
						} else if(this.grille_courante[position[0]][position[1]] instanceof BateauJButton) {
							if(this.grille_model_courante[position[0]][position[1]].getBateau().getEstCoule()) {
								if(this.numero_grille == 1) {
									this.equipe1.getJukebox().playSon("COULER");
								} else {
									this.equipe2.getJukebox().playSon("COULER");
								}
							} else if(this.grille_model_courante[position[0]][position[1]].getTouche()) {
								if(this.numero_grille == 1) {
									this.equipe1.getJukebox().playSon("TOUCHER");
								} else {
									this.equipe2.getJukebox().playSon("TOUCHER");
								}
							}
						}
					}
				} else if(this.numero_grille == 2) {
					this.fenetreJeux.deJeuJPanel2.grille = this.grille_courante;
					this.fenetreJeux.creerWidget(2);
					this.fenetreJeux.validate();
					this.fenetreJeux.repaint();
					if(this.grille_model_courante[position[0]][position[1]].getTouche()) {
						if(this.grille_courante[position[0]][position[1]] instanceof CaseJButton) {
							if(this.numero_grille == 1) {
								this.equipe1.getJukebox().playSon("EAU");
							} else {
								this.equipe2.getJukebox().playSon("EAU");
							}
						} else if(this.grille_courante[position[0]][position[1]] instanceof BateauJButton) {
							if(this.grille_model_courante[position[0]][position[1]].getBateau().getEstCoule()) {
								if(this.numero_grille == 1) {
									this.equipe1.getJukebox().playSon("COULER");
								} else {
									this.equipe2.getJukebox().playSon("COULER");
								}
							} else if(this.grille_model_courante[position[0]][position[1]].getTouche()) {
								if(this.numero_grille == 1) {
									this.equipe1.getJukebox().playSon("TOUCHER");
								} else {
									this.equipe2.getJukebox().playSon("TOUCHER");
								}
							}
						}
					}
				}

				String var7 = "";
				if(this.numero_grille == 1) {
					var7 = this.equipe2.getNomEquipe() + " a gagné !";
				} else if(this.numero_grille == 2) {
					var7 = this.equipe1.getNomEquipe() + " a gagné !";
				}

				JOptionPane.showMessageDialog(this.fenetreJeux, var7, "Victoire", 1);
				this.fenetreJeux.setVisible(false);
				this.groupListener.resetGame(this.equipe1.getNomEquipe(), this.equipe2.getNomEquipe());
				this.groupListener.fenetre = new AccueilJFrame(this.groupListener);
				this.groupListener.fenetre.setVisible(true);
			} else {
				if(this.numero_grille == 1) {
					this.fenetreJeux.deJeuJPanel1.grille = this.grille_courante;
					this.fenetreJeux.creerWidget(1);
					this.fenetreJeux.validate();
					this.fenetreJeux.repaint();
					if(this.grille_model_courante[position[0]][position[1]].getTouche()) {
						if(this.grille_courante[position[0]][position[1]] instanceof CaseJButton) {
							if(this.numero_grille == 1) {
								this.equipe1.getJukebox().playSon("EAU");
							} else {
								this.equipe2.getJukebox().playSon("EAU");
							}
						} else if(this.grille_courante[position[0]][position[1]] instanceof BateauJButton) {
							if(this.grille_model_courante[position[0]][position[1]].getBateau().getEstCoule()) {
								if(this.numero_grille == 1) {
									this.equipe1.getJukebox().playSon("COULER");
								} else {
									this.equipe2.getJukebox().playSon("COULER");
								}
							} else if(this.grille_model_courante[position[0]][position[1]].getTouche()) {
								if(this.numero_grille == 1) {
									this.equipe1.getJukebox().playSon("TOUCHER");
								} else {
									this.equipe2.getJukebox().playSon("TOUCHER");
								}
							}
						}
					}

					this.set_grille_courante();
					this.set_grille_model_courante();
					++this.numero_grille;
					this.fenetreJeux.creerWidget(2);
				} else if(this.numero_grille == 2) {
					this.fenetreJeux.deJeuJPanel2.grille = this.grille_courante;
					this.fenetreJeux.creerWidget(2);
					this.fenetreJeux.validate();
					this.fenetreJeux.repaint();
					if(this.grille_model_courante[position[0]][position[1]].getTouche()) {
						if(this.grille_courante[position[0]][position[1]] instanceof CaseJButton) {
							if(this.numero_grille == 1) {
								this.equipe1.getJukebox().playSon("EAU");
							} else {
								this.equipe2.getJukebox().playSon("EAU");
							}
						} else if(this.grille_courante[position[0]][position[1]] instanceof BateauJButton) {
							if(this.grille_model_courante[position[0]][position[1]].getBateau().getEstCoule()) {
								if(this.numero_grille == 1) {
									this.equipe1.getJukebox().playSon("COULER");
								} else {
									this.equipe2.getJukebox().playSon("COULER");
								}
							} else if(this.grille_model_courante[position[0]][position[1]].getTouche()) {
								if(this.numero_grille == 1) {
									this.equipe1.getJukebox().playSon("TOUCHER");
								} else {
									this.equipe2.getJukebox().playSon("TOUCHER");
								}
							}
						}
					}

					this.set_grille_courante();
					this.set_grille_model_courante();
					--this.numero_grille;
					this.fenetreJeux.creerWidget(1);
				}

				this.fenetreJeux.validate();
				this.fenetreJeux.repaint();
			}
		}

	}

	private int[] case_appelee(ActionEvent e, JButton[][] grille_courante) {
		int[] position = new int[2];

		for(int i = 0; i < 10; ++i) {
			for(int j = 0; j < 10; ++j) {
				if(e.getSource().equals(grille_courante[i][j])) {
					position[0] = i;
					position[1] = j;
				}
			}
		}

		return position;
	}

	public void set_grille_model_courante() {
		if(this.grille_model_courante.equals(this.grille_model1)) {
			this.grille_model_courante = this.grille_model2;
		} else if(this.grille_model_courante.equals(this.grille_model2)) {
			this.grille_model_courante = this.grille_model1;
		}

	}

	public void set_grille_courante() {
		if(this.grille_courante.equals(this.grille1)) {
			this.grille_courante = this.grille2;
		} else if(this.grille_courante.equals(this.grille2)) {
			this.grille_courante = this.grille1;
		}

	}

	public boolean verifGagnant() {
		boolean retour = true;

		for(int i = 0; i < 10; ++i) {
			for(int j = 0; j < 10; ++j) {
				if(this.grille_model_courante[i][j].getBateau() != null && !this.grille_model_courante[i][j].getBateau().getEstCoule()) {
					retour = false;
				}
			}
		}

		return retour;
	}
}
