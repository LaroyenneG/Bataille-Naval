package gui;

import engine.Case;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JButton;

public class CaseJButton extends JButton {
	private static final long serialVersionUID = 6683172863248370961L;
	public int x;
	public int y;
	public Case case_model;

	public CaseJButton(Case case_model) {
		this.case_model = case_model;
		this.setPreferredSize(new Dimension(35, 35));
		this.setBackground(Color.BLUE);
	}
}