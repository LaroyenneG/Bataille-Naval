package gui;

import gui.AccueilJPanel;
import gui.MenuSuperieurJMenuBar;
import java.awt.Component;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import listener.GroupListener;

public class AccueilJFrame extends JFrame {
	private JPanel jPanel;
	public GroupListener groupListener;
	private static final long serialVersionUID = 6984202546554955770L;

	public AccueilJFrame(GroupListener groupListener) {
		MenuSuperieurJMenuBar menuBar = new MenuSuperieurJMenuBar(this);
		this.setJMenuBar(menuBar);
		this.groupListener = groupListener;
		this.setjPanel(new AccueilJPanel(this));
		this.setSize(400, 250);
		this.setLocationRelativeTo((Component)null);
		this.setVisible(true);
		this.setTitle("Battle-Nav Menu");
		this.setResizable(false);
		this.setDefaultCloseOperation(3);
	}

	public JPanel getjPanel() {
		return this.jPanel;
	}

	public void setjPanel(JPanel jPanel) {
		this.jPanel = jPanel;
		this.setContentPane(jPanel);
	}

	public void creerDialogError(String message) {
		JOptionPane.showMessageDialog(this, message, "Erreur", 0);
	}
}