package gui;

import gui.AccueilJFrame;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.WindowEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import listener.FprincListener;

public class AccueilJPanel extends JPanel {
	private static JButton bPLay;
	private static JButton bQuit;
	public AccueilJFrame accueilJFrame;
	private static final long serialVersionUID = -992696352145551420L;

	public AccueilJPanel(AccueilJFrame accueilJFrame) {
		this.accueilJFrame = accueilJFrame;
		this.creatWidget();
	}

	public void creatWidget() {
		JPanel pPrinc = new JPanel();
		JPanel pCentrageMenu = new JPanel(new BorderLayout());
		pCentrageMenu.setLayout(new GridLayout(3, 1));
		JPanel pTitel = new JPanel();
		JPanel pButton = new JPanel();
		pButton.setLayout(new GridLayout(2, 1));
		JPanel pPLay = new JPanel();
		JPanel pQuit = new JPanel();
		JLabel lTitel = new JLabel("Battle-Nav");
		lTitel.setFont(new Font("Bitstream Charter", 1, 40));
		bPLay = new JButton("Play");
		bQuit = new JButton("Quitter");
		pTitel.add(lTitel);
		pPLay.add(bPLay);
		pQuit.add(bQuit);
		pButton.add(pPLay);
		pButton.add(pQuit);
		pCentrageMenu.add(pTitel, "Center");
		pCentrageMenu.add(pPLay, "Center");
		pCentrageMenu.add(pQuit, "Center");
		pPrinc.add(pCentrageMenu);
		bPLay.addActionListener(new FprincListener(this));
		bQuit.addActionListener(new FprincListener(this));
		this.add(pPrinc);
	}

	public static JButton getbQuit() {
		return bQuit;
	}

	public static JButton getbPLay() {
		return bPLay;
	}

	public void close() {
		JFrame topFrame = (JFrame)SwingUtilities.getWindowAncestor(this);
		topFrame.setVisible(false);
		topFrame.setDefaultCloseOperation(2);
		topFrame.dispatchEvent(new WindowEvent(topFrame, 201));
	}
}
