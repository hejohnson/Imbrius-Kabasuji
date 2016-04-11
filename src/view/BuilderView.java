package view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.AbstractLevelModel;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

public class BuilderView extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	BoardView boardView;
	ButtonGroupView buttonGroupView;
	ReleaseNumberCreationView releaseNumberView;
	LevelPropertiesView levelPropertyView;
	BullpenView bullpenView;
	SelectedPieceView selectedPieceView;
	AbstractLevelModel modelLevel; 
	
	public BuilderView() {
		setResizable(false);
		setVisible(false);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 520, 650);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		boardView = new BoardView();
		buttonGroupView = new ButtonGroupView();
		releaseNumberView = new ReleaseNumberCreationView();
		levelPropertyView = new LevelPropertiesView();
		bullpenView = new BullpenView("builder");		
		selectedPieceView = new SelectedPieceView();
		
		setupLayout();		
	}
	
	
	/**
	 * Sets the level model for this builder. This is critical to setup. After the
	 * blank builderView has been passed around the type of level model it will be handling
	 * is determind at runtime. This method is used to set the model level and prepare the 
	 * view.
	 * @param m
	 */
	/* TODO Implement additional instructions as needed to paint the board, prepare the bullpen,
	 * etc.
	 */
	public void setModelLevel(AbstractLevelModel m){
		modelLevel = m;
	}
	
	
	/**
	 * Prepares the view of a puzzle level by disabling release
	 * tile creation and showing only relevant information in
	 * property view for a puzzle level.
	 */
	public void prepPuzzle(){
		releaseNumberView.setVisible(false);
		levelPropertyView.puzzle();	
	}
	
	/**
	 * Prepares the view of a lightning level by disabling release
	 * tile creation and showing only relevant information in
	 * property view for a lightning level.
	 */
	public void prepLightning(){
		releaseNumberView.setVisible(false);
		levelPropertyView.lightning();	
	}
	
	/**
	 * Prepares the view of a release level by showing only 
	 * relevant information in the property view for a release
	 * level (I.E. nothing)
	 */
	public void prepRelease(){
		releaseNumberView.setVisible(true);
		levelPropertyView.release();
	}
	
	
	void setupLayout(){
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(selectedPieceView, GroupLayout.PREFERRED_SIZE, 384, GroupLayout.PREFERRED_SIZE)
						.addComponent(boardView, GroupLayout.PREFERRED_SIZE, 384, GroupLayout.PREFERRED_SIZE))
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
							.addComponent(levelPropertyView, GroupLayout.PREFERRED_SIZE, 111, Short.MAX_VALUE)
							.addComponent(bullpenView, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(buttonGroupView, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addComponent(releaseNumberView, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(bullpenView, GroupLayout.PREFERRED_SIZE, 223, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(levelPropertyView, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(releaseNumberView, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(buttonGroupView, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(selectedPieceView, GroupLayout.PREFERRED_SIZE, 224, GroupLayout.PREFERRED_SIZE)
							.addComponent(boardView, GroupLayout.PREFERRED_SIZE, 384, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
	}
}