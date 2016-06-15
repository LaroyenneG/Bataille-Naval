package listener;

import gui.AccueilJPanel;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

public class FprincListener implements ActionListener {
	private AccueilJPanel accueilJPanel;

	public FprincListener(AccueilJPanel accueilJPane) {
		this.accueilJPanel = accueilJPane;
	}

	public void actionPerformed(ActionEvent actionEvent) {
		if(actionEvent.getSource().equals(AccueilJPanel.getbPLay())) {
			this.accueilJPanel.close();
			boolean b = true;

			while(b) {
				try {
					this.initGame();
					b = false;
				} catch (Exception var5) {
					if(var5.getMessage() == null) {
						this.accueilJPanel.accueilJFrame.groupListener.equipe1.setNomEquipe("<équipe n°1>");
						this.accueilJPanel.accueilJFrame.groupListener.equipe2.setNomEquipe("<équipe n°2>");
						b = false;
					} else {
						JOptionPane jop = new JOptionPane();
						JOptionPane.showMessageDialog((Component)null, var5.getMessage(), "error", 1);
					}
				}
			}

			this.accueilJPanel.accueilJFrame.groupListener.createGrilleDeJeuJPanel(1);
		}

		if(actionEvent.getSource().equals(AccueilJPanel.getbQuit())) {
			System.exit(0);
		}

	}

	public void initGame() throws Exception {
		String equipe2;
		if(this.accueilJPanel.accueilJFrame.groupListener.equipe1.getNomEquipe().length() == 0) {
			equipe2 = JOptionPane.showInputDialog((Component)null, "nom du Joueur 1 ?", "", 3);
			if(equipe2.length() == 0) {
				throw new Exception("Votre nom est vide");
			}

			this.accueilJPanel.accueilJFrame.groupListener.equipe1.setNomEquipe(equipe2);
		}

		if(this.accueilJPanel.accueilJFrame.groupListener.equipe2.getNomEquipe().length() == 0) {
			equipe2 = JOptionPane.showInputDialog((Component)null, "nom du Joueur 2 ?", "", 3);
			if(equipe2.length() == 0) {
				throw new Exception("Votre nom est vide");
			}

			if(this.accueilJPanel.accueilJFrame.groupListener.equipe1.getNomEquipe().equals(equipe2)) {
				throw new Exception("Votre nom est le même que la première équipe");
			}

			this.accueilJPanel.accueilJFrame.groupListener.equipe2.setNomEquipe(equipe2);
		}

	}
}