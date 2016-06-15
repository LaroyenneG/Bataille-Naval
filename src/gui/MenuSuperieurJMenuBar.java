package gui;

import gui.AccueilJFrame;
import gui.AccueilJPanel;
import gui.FenetreJeux;
import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import listener.MenuListener;

public class MenuSuperieurJMenuBar extends JMenuBar {
	static final String rep = "src/gui/icone/";
	private static final JOptionPane message = new JOptionPane();
	final String signature = new String("Projet informatique semestre 2" + System.getProperty("line.separator") + "(session 2015-2016)" + System.getProperty("line.separator") + "Programmé par :" + System.getProperty("line.separator") + "BORDY Tanguy" + System.getProperty("line.separator") + "COUTURIEUX Victor" + System.getProperty("line.separator") + "LAROYENNE Guillaume" + System.getProperty("line.separator") + "MARTIN Florent" + System.getProperty("line.separator") + "SALVI Tom" + System.getProperty("line.separator") + "IUNG Nicolas" + System.getProperty("line.separator") + "VALENZA Pierre" + System.getProperty("line.separator") + "VAISSIERE Floriant");
	private final ImageIcon iconeInfo = new ImageIcon((new ImageIcon("src/gui/icone/info.jpg")).getImage().getScaledInstance(30, 30, 0));
	private final ImageIcon iconeRestart = new ImageIcon((new ImageIcon("src/gui/icone/restart.png")).getImage().getScaledInstance(30, 30, 0));
	private final ImageIcon iconeSonOn = new ImageIcon((new ImageIcon("src/gui/icone/sonOn.jpg")).getImage().getScaledInstance(30, 30, 0));
	private final ImageIcon iconeSonOff = new ImageIcon((new ImageIcon("src/gui/icone/sonOff.jpg")).getImage().getScaledInstance(30, 30, 0));
	private final ImageIcon iconeStop = new ImageIcon((new ImageIcon("src/gui/icone/stop.png")).getImage().getScaledInstance(30, 30, 0));
	private final ImageIcon iconeQuiter = new ImageIcon((new ImageIcon("src/gui/icone/quiter.jpg")).getImage().getScaledInstance(30, 30, 0));
	private JMenu optionPlay;
	private JMenu audio;
	private JMenu info;
	private JMenuItem itemOptionPlay1;
	private JMenuItem itemOptionPlay2;
	private JMenuItem itemOptionPlay3;
	private JMenuItem itemOptionPlay4;
	private JMenuItem itemOptionPlay5;
	private JMenuItem itemAudio1;
	private JMenuItem itemAudio2;
	private JMenuItem itemInfo1;
	private MenuListener menuListener;
	private AccueilJFrame accueilJFrame;
	private AccueilJPanel accueilJPanel;
	private FenetreJeux fenetreJeux;
	private JPanel jPanel;

	public MenuSuperieurJMenuBar(AccueilJFrame accueilJFrame, AccueilJPanel accueilJPanel) {
		this.accueilJFrame = accueilJFrame;
		this.accueilJPanel = accueilJPanel;
		this.jPanel = accueilJFrame.getjPanel();
		this.initAttribut2();
		this.creerJMenuBar2();
	}

	public MenuSuperieurJMenuBar(FenetreJeux fenetreJeux) {
		this.fenetreJeux = fenetreJeux;
		this.initAttribut1();
		this.creerJMenuBar1();
	}

	public MenuSuperieurJMenuBar(AccueilJFrame accueilJFrame) {
		this.accueilJFrame = accueilJFrame;
		this.jPanel = accueilJFrame.getjPanel();
		if(this.jPanel == this.accueilJPanel) {
			this.initAttribut2();
			this.creerJMenuBar2();
		} else {
			this.initAttribuAccueil();
			this.creerJMenuBarAccueil();
		}

	}

	private void initAttribut2() {
		this.info = new JMenu("A propos");
		this.itemInfo1 = new JMenuItem("Createurs");
		this.menuListener = new MenuListener(this, this.accueilJFrame);
		this.itemInfo1.addActionListener(this.menuListener);
	}

	private void initAttribut1() {
		this.optionPlay = new JMenu("Game");
		this.audio = new JMenu("Audio");
		this.info = new JMenu("À propos");
		this.itemOptionPlay1 = new JMenuItem("Abandonner la partie");
		this.itemOptionPlay2 = new JMenuItem("Recommencer la partie");
		this.itemOptionPlay3 = new JMenuItem("Quitter");
		this.itemAudio1 = new JMenuItem("ON");
		this.itemAudio2 = new JMenuItem("OFF");
		this.itemInfo1 = new JMenuItem("Créateurs");
		this.menuListener = new MenuListener(this, this.fenetreJeux);
		this.itemOptionPlay1.addActionListener(this.menuListener);
		this.itemOptionPlay2.addActionListener(this.menuListener);
		this.itemOptionPlay3.addActionListener(this.menuListener);
		this.itemAudio1.addActionListener(this.menuListener);
		this.itemAudio2.addActionListener(this.menuListener);
		this.itemInfo1.addActionListener(this.menuListener);
	}

	private void initAttribuAccueil() {
		this.optionPlay = new JMenu("Game");
		this.info = new JMenu("À propos");
		this.itemOptionPlay4 = new JMenuItem("Abandonner la partie");
		this.itemOptionPlay5 = new JMenuItem("Recommencer la partie");
		this.itemOptionPlay3 = new JMenuItem("Quitter");
		this.itemInfo1 = new JMenuItem("Créateurs");
		this.menuListener = new MenuListener(this, this.accueilJFrame);
		this.itemOptionPlay4.addActionListener(this.menuListener);
		this.itemOptionPlay5.addActionListener(this.menuListener);
		this.itemOptionPlay3.addActionListener(this.menuListener);
		this.itemInfo1.addActionListener(this.menuListener);
	}

	private void creerJMenuBarAccueil() {
		this.itemOptionPlay4.setIcon(this.iconeStop);
		this.itemOptionPlay5.setIcon(this.iconeRestart);
		this.itemOptionPlay3.setIcon(this.iconeQuiter);
		this.itemInfo1.setIcon(this.iconeInfo);
		this.itemInfo1.setBackground(Color.white);
		this.itemOptionPlay4.setBackground(Color.white);
		this.itemOptionPlay5.setBackground(Color.white);
		this.itemOptionPlay3.setBackground(Color.white);
		this.optionPlay.add(this.itemOptionPlay4);
		this.optionPlay.add(this.itemOptionPlay5);
		this.optionPlay.add(this.itemOptionPlay3);
		this.info.add(this.itemInfo1);
		this.add(this.optionPlay);
		this.add(this.info);
	}

	private void creerJMenuBar1() {
		this.itemOptionPlay1.setIcon(this.iconeStop);
		this.itemOptionPlay2.setIcon(this.iconeRestart);
		this.itemOptionPlay3.setIcon(this.iconeQuiter);
		this.itemAudio1.setIcon(this.iconeSonOn);
		this.itemAudio2.setIcon(this.iconeSonOff);
		this.itemInfo1.setIcon(this.iconeInfo);
		this.itemAudio1.setBackground(Color.white);
		this.itemAudio2.setBackground(Color.white);
		this.itemInfo1.setBackground(Color.white);
		this.itemOptionPlay1.setBackground(Color.white);
		this.itemOptionPlay2.setBackground(Color.white);
		this.itemOptionPlay3.setBackground(Color.white);
		this.optionPlay.add(this.itemOptionPlay1);
		this.optionPlay.add(this.itemOptionPlay2);
		this.optionPlay.add(this.itemOptionPlay3);
		this.audio.add(this.itemAudio1);
		this.audio.add(this.itemAudio2);
		this.info.add(this.itemInfo1);
		this.add(this.optionPlay);
		this.add(this.audio);
		this.add(this.info);
	}

	private void creerJMenuBar2() {
		this.itemInfo1.setIcon(this.iconeInfo);
		this.itemInfo1.setBackground(Color.white);
		this.info.add(this.itemInfo1);
		this.add(this.info);
	}

	public JMenuItem getItemInfo1() {
		return this.itemInfo1;
	}

	public JMenuItem getItemAudio1() {
		return this.itemAudio1;
	}

	public JMenuItem getItemAudio2() {
		return this.itemAudio2;
	}

	public JMenuItem getItemOptionPlay1() {
		return this.itemOptionPlay1;
	}

	public JMenuItem getItemOptionPlay2() {
		return this.itemOptionPlay2;
	}

	public JMenuItem getItemOptionPlay3() {
		return this.itemOptionPlay3;
	}

	public static JOptionPane getMessage() {
		return message;
	}

	public String getSignature() {
		return this.signature;
	}

	public AccueilJPanel getAccueilJPanel() {
		return this.accueilJPanel;
	}

	public void setAccueilJPanel(AccueilJPanel accueilJPanel) {
		this.accueilJPanel = accueilJPanel;
	}

	public JMenuItem getItemOptionPlay4() {
		return this.itemOptionPlay4;
	}

	public JMenuItem getItemOptionPlay5() {
		return this.itemOptionPlay5;
	}
}
