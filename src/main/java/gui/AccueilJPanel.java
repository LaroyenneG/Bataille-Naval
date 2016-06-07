/*******************************************************************************
 * 2016, All rights reserved.
 *******************************************************************************/
package gui;

import listener.FprincListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;

// Start of user code (user defined imports)

// End of user code

/**
 * Description of AcueilleJPanel.
 * 
 * @author nicolas
 */
public class AccueilJPanel extends JPanel
{
	// Start of user code (user defined attributes for AcueilleJPanel)
	private static JButton		bPLay;
	private static JButton		bQuit;

	public AccueilJFrame		accueilJFrame;
	// End of user code

	private static final long	serialVersionUID	= -992696352145551420L;

	public AccueilJPanel(AccueilJFrame accueilJFrame)
	{
		// Start of user code constructor for AcueilleJPanel)
		super();
		this.accueilJFrame = accueilJFrame;
		creatWidget();

		// End of user code
	}

	// Start of user code (user defined methods for AcueilleJPanel)

	public void creatWidget()
	{
		JPanel pPrinc = new JPanel();

		JPanel pCentrageMenu = new JPanel(new BorderLayout());
		pCentrageMenu.setLayout(new GridLayout(3, 1));

		JPanel pTitel = new JPanel();

		JPanel pButton = new JPanel();
		pButton.setLayout(new GridLayout(2, 1));
		JPanel pPLay = new JPanel();
		JPanel pQuit = new JPanel();

		JLabel lTitel = new JLabel("Battle-Nav");
		lTitel.setFont(new Font("Bitstream Charter", Font.BOLD, 40));

		bPLay = new JButton("Play");

		// JButton bCredit = new JButton("Source et credit");

		bQuit = new JButton("Quitter");

		// construction de Frame
		pTitel.add(lTitel);
		pPLay.add(bPLay);
		pQuit.add(bQuit);

		pButton.add(pPLay);
		pButton.add(pQuit);

		pCentrageMenu.add(pTitel, BorderLayout.CENTER);
		pCentrageMenu.add(pPLay, BorderLayout.CENTER);
		pCentrageMenu.add(pQuit, BorderLayout.CENTER);

		pPrinc.add(pCentrageMenu);

		bPLay.addActionListener(new FprincListener(this));
		bQuit.addActionListener(new FprincListener(this));

		add(pPrinc);
	}

	public static JButton getbQuit()
	{
		return bQuit;
	}

	public static JButton getbPLay()
	{
		return bPLay;
	}

	public void close()
	{
		JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
		topFrame.setVisible(false);
		topFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		topFrame.dispatchEvent(new WindowEvent(topFrame, WindowEvent.WINDOW_CLOSING));
	}
	// End of user code

}
