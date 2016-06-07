package listener;

import gui.AccueilJPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by victor on 26/05/16.
 */
public class FprincListener implements ActionListener
{

	private AccueilJPanel accueilJPanel;

	public FprincListener(AccueilJPanel accueilJPane)
	{
		this.accueilJPanel = accueilJPane;
	}

	@Override
	public void actionPerformed(ActionEvent actionEvent)
	{
		if (actionEvent.getSource().equals(AccueilJPanel.getbPLay()))
		{
			accueilJPanel.close();
			boolean b= true;
			while (b){
				try {
					initGame();
					b=false;
				}catch (Exception e){
					if (e.getMessage()==null) {
						accueilJPanel.accueilJFrame.groupListener.equipe1.setNomEquipe("<équipe n°1>");
						accueilJPanel.accueilJFrame.groupListener.equipe2.setNomEquipe("<équipe n°2>");
						b=false;
					}else{
						JOptionPane jop = new JOptionPane();
						jop.showMessageDialog(null, e.getMessage(), "error", JOptionPane.INFORMATION_MESSAGE);
					}
				}
			}

			accueilJPanel.accueilJFrame.groupListener.createGrilleDeJeuJPanel(1);
		}

		if (actionEvent.getSource().equals(AccueilJPanel.getbQuit()))
			System.exit(0);
	}

	public void initGame() throws Exception
	{

		if(accueilJPanel.accueilJFrame.groupListener.equipe1.getNomEquipe().length()==0){
			String equipe1 = JOptionPane.showInputDialog(null, "nom du Joueur 1 ?", "", JOptionPane.QUESTION_MESSAGE);
			if (equipe1.length() == 0) {
				throw new Exception("Votre nom est vide");
			}
			accueilJPanel.accueilJFrame.groupListener.equipe1.setNomEquipe(equipe1);
		}

		if(accueilJPanel.accueilJFrame.groupListener.equipe2.getNomEquipe().length()==0){
			String equipe2 = JOptionPane.showInputDialog(null, "nom du Joueur 2 ?", "", JOptionPane.QUESTION_MESSAGE);
			if (equipe2.length() == 0) {
				throw new Exception("Votre nom est vide");
			}
			if (accueilJPanel.accueilJFrame.groupListener.equipe1.getNomEquipe().equals(equipe2)) {
				throw new Exception("Votre nom est le même que la première équipe");
			}
			accueilJPanel.accueilJFrame.groupListener.equipe2.setNomEquipe(equipe2);
		}

	}
}