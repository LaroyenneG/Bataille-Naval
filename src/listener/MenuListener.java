package listener;

import engine.Equipe;
import engine.GrilleJeux;
import gui.AccueilJFrame;
import gui.AccueilJPanel;
import gui.FenetreJeux;
import gui.MenuSuperieurJMenuBar;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import listener.GroupListener;

public class MenuListener implements ActionListener {
	MenuSuperieurJMenuBar menuSuperieurJMenuBar;
	AccueilJFrame accueilJFrame;
	AccueilJPanel accueilJPanel;
	FenetreJeux fenetreJeux;

	public MenuListener(MenuSuperieurJMenuBar menuSuperieurJMenuBar, AccueilJFrame accueilJFrame) {
		this.menuSuperieurJMenuBar = menuSuperieurJMenuBar;
		this.accueilJFrame = accueilJFrame;
	}

	public MenuListener(MenuSuperieurJMenuBar menuSuperieurJMenuBar, FenetreJeux fenetreJeux) {
		this.fenetreJeux = fenetreJeux;
		this.menuSuperieurJMenuBar = menuSuperieurJMenuBar;
	}

	public void newGame() {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				GrilleJeux grilleJeux2 = new GrilleJeux();
				GrilleJeux grilleJeux1 = new GrilleJeux();
				Equipe equipes1 = new Equipe(grilleJeux1, "");
				Equipe equipes2 = new Equipe(grilleJeux2, "");
				Equipe[] equipes = new Equipe[]{equipes1, equipes2};
				grilleJeux1.setEquipes(equipes);
				grilleJeux2.setEquipes(equipes);
				new GroupListener(grilleJeux1, grilleJeux2, equipes1, equipes2);
			}
		});
	}

	public void newGame(final String nom1, final String nom2) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				GrilleJeux grilleJeux2 = new GrilleJeux();
				GrilleJeux grilleJeux1 = new GrilleJeux();
				Equipe equipes1 = new Equipe(grilleJeux1, nom1);
				Equipe equipes2 = new Equipe(grilleJeux2, nom2);
				Equipe[] equipes = new Equipe[]{equipes1, equipes2};
				grilleJeux1.setEquipes(equipes);
				grilleJeux2.setEquipes(equipes);
				new GroupListener(grilleJeux1, grilleJeux2, equipes1, equipes2);
			}
		});
	}

	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == this.menuSuperieurJMenuBar.getItemInfo1()) {
			MenuSuperieurJMenuBar var10000 = this.menuSuperieurJMenuBar;
			MenuSuperieurJMenuBar.getMessage();
			var10000 = this.menuSuperieurJMenuBar;
			JOptionPane.showMessageDialog(MenuSuperieurJMenuBar.getMessage(), this.menuSuperieurJMenuBar.getSignature(), "Information", 1);
		}

		if(e.getSource() == this.menuSuperieurJMenuBar.getItemOptionPlay3()) {
			System.exit(0);
		}

		if(e.getSource() == this.menuSuperieurJMenuBar.getItemOptionPlay1()) {
			this.fenetreJeux.setVisible(false);
			this.newGame();
		}

		if(e.getSource() == this.menuSuperieurJMenuBar.getItemOptionPlay2()) {
			String menuBar = this.fenetreJeux.getEquipes()[0].getNomEquipe();
			String nom2 = this.fenetreJeux.getEquipes()[1].getNomEquipe();
			this.fenetreJeux.setVisible(false);
			this.newGame(menuBar, nom2);
		}

		if(e.getSource() == this.menuSuperieurJMenuBar.getItemAudio1()) {
			this.fenetreJeux.getEquipes()[0].getJukebox().activeSon(true);
			this.fenetreJeux.getEquipes()[1].getJukebox().activeSon(true);
		}

		if(e.getSource() == this.menuSuperieurJMenuBar.getItemAudio2()) {
			this.fenetreJeux.getEquipes()[0].getJukebox().activeSon(false);
			this.fenetreJeux.getEquipes()[1].getJukebox().activeSon(false);
		}

		if(e.getSource() == this.menuSuperieurJMenuBar.getItemOptionPlay5()) {
			this.accueilJFrame.groupListener.resetGame(this.accueilJFrame.groupListener.equipe1.getNomEquipe(), this.accueilJFrame.groupListener.equipe2.getNomEquipe());
			this.accueilJPanel = new AccueilJPanel(this.accueilJFrame);
			this.accueilJFrame.setSize(400, 250);
			this.accueilJFrame.setjPanel(this.accueilJPanel);
			this.menuSuperieurJMenuBar.setAccueilJPanel(this.accueilJPanel);
			this.accueilJFrame.setLocationRelativeTo((Component)null);
			this.accueilJFrame.getContentPane().repaint();
			MenuSuperieurJMenuBar menuBar1 = new MenuSuperieurJMenuBar(this.accueilJFrame, this.accueilJPanel);
			this.accueilJFrame.setJMenuBar(menuBar1);
			this.accueilJFrame.setVisible(true);
		}

		if(e.getSource() == this.menuSuperieurJMenuBar.getItemOptionPlay4()) {
			this.accueilJFrame.setVisible(false);
			this.newGame();
		}

	}
}
