package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;

import model.AbstractTile;
import model.Bullpen;
import model.PieceTile;

public class SelectedPieceView extends JPanel{
	private static final long serialVersionUID = 1L;
	GroupLayout groupLayout;
	PiecePanel piecePanel;
	JButton btnRotateLeft;
	JButton btnRotateRight;
	JButton btnFlipX;
	JButton btnFlipY;
	Bullpen bp;
	
	
	
	public SelectedPieceView(Bullpen bp){
		setPreferredSize(new Dimension(384, 224));
		this.bp = bp;
		btnRotateLeft = new JButton("");
		btnRotateLeft.setToolTipText("Rotate Left");
		btnRotateRight = new JButton("");
		btnRotateRight.setToolTipText("Rotate Right");
		btnFlipX = new JButton("");
		btnFlipX.setToolTipText("Flip X");
		btnFlipY = new JButton("");
		btnFlipY.setToolTipText("Flip Y");
		
		piecePanel = new PiecePanel(bp);
		setupLayout();
	}
	
	public PiecePanel getPiecePanel(){
		return this.piecePanel;
	}

	
	
	private void setupLayout(){
		
		btnRotateLeft.setIcon(new ImageIcon(SelectedPieceView.class.getResource("/icons/RotateLeft.png")));
		btnRotateRight.setIcon(new ImageIcon(SelectedPieceView.class.getResource("/icons/RotateRight.png")));
		btnFlipX.setIcon(new ImageIcon(SelectedPieceView.class.getResource("/icons/FlipX.png")));
		btnFlipY.setIcon(new ImageIcon(SelectedPieceView.class.getResource("/icons/FlipY.png")));
		
		groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(96, Short.MAX_VALUE)
					.addComponent(piecePanel, GroupLayout.PREFERRED_SIZE, 192, GroupLayout.PREFERRED_SIZE)
					.addGap(14)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(btnFlipX, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(btnFlipY, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(btnRotateLeft, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnRotateRight, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addComponent(btnRotateLeft)
								.addComponent(btnRotateRight))
							.addPreferredGap(ComponentPlacement.RELATED, 156, Short.MAX_VALUE)
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addComponent(btnFlipX)
								.addComponent(btnFlipY)))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(16)
							.addComponent(piecePanel, GroupLayout.PREFERRED_SIZE, 192, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);

		setLayout(groupLayout);
	}
}
