package view;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import controllers.ExistingLevelEditController;

import java.awt.Color;
import javax.swing.BorderFactory;
import java.awt.Dimension;

class ExistingLevelView extends JButton {	
	ImageIcon icon;
	ExistingLevelView(String levelType, Integer levelNumber) {
		super(levelType+" "+levelNumber.toString());
		Dimension levelPreviewSize = new Dimension(126, 126);
		if (levelType.equals("Release")) {
			icon = new ImageIcon(LevelTypeSelectView.class.getResource("/icons/ReleaseSm.png"));
		} else if (levelType.equals("Lightning")) {
			icon = new ImageIcon(LevelTypeSelectView.class.getResource("/icons/LightningSm.png"));
		} else if (levelType.equals("Puzzle")) {
			icon = new ImageIcon(LevelTypeSelectView.class.getResource("/icons/PuzzleSm.png"));
		} else {
			//#TODO: some kind of behavior here
		}
		this.setIcon(icon);
		this.setHorizontalAlignment(SwingConstants.CENTER);
		this.setHorizontalTextPosition(SwingConstants.CENTER);
		this.setVerticalTextPosition(SwingConstants.BOTTOM);
		this.setPreferredSize(levelPreviewSize);
		this.setMaximumSize(levelPreviewSize);
		this.setMinimumSize(levelPreviewSize);
		//this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		this.addActionListener(new ExistingLevelEditController(levelNumber));
	}
}
