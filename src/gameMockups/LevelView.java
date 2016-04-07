package gameMockups;

import javax.swing.GroupLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;

import builderMockups.BullpenView;
import builderMockups.SelectedPieceView;

public class LevelView extends JFrame {

	private static final long serialVersionUID = 1L;
	
	
	// TODO AbstractLevelModel m;
	SelectedPieceView selectedPiece;
	BullpenView availablePieces;
	// TODO BoardView boardView;
	LevelInfoView levelInfo;
	NumberMovesLeftView movesLeftView;
	NumbersReleasedView numbersReleasedView[];
	TimeRemainingView timeLeftView;
	BoardView boardView;
	JPanel content;
	
	// remove this later
	int sets = 3;
	
	public LevelView(String gameType) {

		availablePieces = new BullpenView("playing");
		levelInfo = new LevelInfoView();
		timeLeftView = new TimeRemainingView();
		boardView = new BoardView(gameType); 
		selectedPiece = new SelectedPieceView();
		movesLeftView = new NumberMovesLeftView();
		numbersReleasedView = new NumbersReleasedView[sets];
		for(int i = 0; i < sets; i++) {
			numbersReleasedView[i] = new NumbersReleasedView();
		}
		
		setupLayout(gameType);
	}
	
	private void setupLayout(String type) {
		setTitle(type);
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // TODO this will change with the intercept
		setBounds(100, 100, 620, 651);
		content = new JPanel();
		content.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(content);
		GroupLayout gl_LevelView = new GroupLayout(this.getContentPane());
		content.setLayout(gl_LevelView);

		gl_LevelView.setAutoCreateGaps(true);
		gl_LevelView.setAutoCreateContainerGaps(true);
		
	
		if(type.equals("Puzzle")) {
			gl_LevelView.setHorizontalGroup(
					gl_LevelView.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_LevelView.createSequentialGroup()
							.addGroup(gl_LevelView.createParallelGroup(Alignment.LEADING)
								.addComponent(boardView, GroupLayout.PREFERRED_SIZE, 388, GroupLayout.PREFERRED_SIZE)
								.addComponent(selectedPiece, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGroup(gl_LevelView.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_LevelView.createSequentialGroup()
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(gl_LevelView.createParallelGroup(Alignment.LEADING)
										.addComponent(levelInfo, GroupLayout.DEFAULT_SIZE, 217, Short.MAX_VALUE)
										.addGroup(gl_LevelView.createSequentialGroup()
											.addGap(62)
											.addComponent(availablePieces, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
											.addGap(29)))
									.addGap(18))
								.addGroup(gl_LevelView.createSequentialGroup()
									.addGap(58)
									.addGroup(gl_LevelView.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_LevelView.createSequentialGroup()
											.addGap(10))
										.addComponent(movesLeftView, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
									.addGap(63))))
				);
				gl_LevelView.setVerticalGroup(
					gl_LevelView.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_LevelView.createSequentialGroup()
							.addComponent(selectedPiece, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(boardView, GroupLayout.PREFERRED_SIZE, 387, GroupLayout.PREFERRED_SIZE))
						.addGroup(Alignment.LEADING, gl_LevelView.createSequentialGroup()
							.addContainerGap()
							.addComponent(levelInfo, GroupLayout.PREFERRED_SIZE, 112, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(availablePieces, GroupLayout.PREFERRED_SIZE, 349, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(movesLeftView, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addContainerGap(21, Short.MAX_VALUE))
				);
				setLayout(gl_LevelView);
		} else if(type.equals("Lightning")) {
			gl_LevelView.setHorizontalGroup(
				gl_LevelView.createParallelGroup(Alignment.TRAILING)
					.addGroup(gl_LevelView.createSequentialGroup()
						.addGroup(gl_LevelView.createParallelGroup(Alignment.LEADING)
							.addComponent(boardView, GroupLayout.PREFERRED_SIZE, 384, GroupLayout.PREFERRED_SIZE)
							.addComponent(selectedPiece, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_LevelView.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_LevelView.createSequentialGroup()
								.addPreferredGap(ComponentPlacement.RELATED)
								.addGroup(gl_LevelView.createParallelGroup(Alignment.LEADING)
									.addGroup(gl_LevelView.createSequentialGroup()
										.addGap(70)
										.addComponent(availablePieces, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED))
									.addGroup(gl_LevelView.createSequentialGroup()
										.addGroup(gl_LevelView.createParallelGroup(Alignment.TRAILING)
											.addComponent(timeLeftView, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
											.addComponent(levelInfo, GroupLayout.PREFERRED_SIZE, 189, GroupLayout.PREFERRED_SIZE))
										.addGap(39))))
							.addGroup(gl_LevelView.createSequentialGroup()
								.addGap(77)))
						.addGap(39))
			);
			gl_LevelView.setVerticalGroup(
				gl_LevelView.createParallelGroup(Alignment.TRAILING)
					.addGroup(Alignment.LEADING, gl_LevelView.createSequentialGroup()
						.addGap(12)
						.addComponent(levelInfo, GroupLayout.PREFERRED_SIZE, 121, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(availablePieces, GroupLayout.PREFERRED_SIZE, 312, GroupLayout.PREFERRED_SIZE)
						.addGap(18)
						.addComponent(timeLeftView, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addContainerGap(31, Short.MAX_VALUE))
					.addGroup(gl_LevelView.createSequentialGroup()
						.addComponent(selectedPiece, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(boardView, 0, 0, Short.MAX_VALUE))
			);
			setLayout(gl_LevelView);
		}  else if(type.equals("Release")) {
			gl_LevelView.setHorizontalGroup(
					gl_LevelView.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_LevelView.createSequentialGroup()
							.addGroup(gl_LevelView.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_LevelView.createSequentialGroup()
									.addGap(2)
									.addComponent(boardView, GroupLayout.PREFERRED_SIZE, 386, GroupLayout.PREFERRED_SIZE))
								.addComponent(selectedPiece, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGroup(gl_LevelView.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_LevelView.createSequentialGroup()
									.addGap(28)
									.addComponent(levelInfo, GroupLayout.PREFERRED_SIZE, 190, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_LevelView.createSequentialGroup()
									.addGap(69)
									.addComponent(availablePieces, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_LevelView.createSequentialGroup()
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(gl_LevelView.createParallelGroup(Alignment.LEADING)
										.addComponent(numbersReleasedView[0], GroupLayout.PREFERRED_SIZE, 212, GroupLayout.PREFERRED_SIZE)
										.addComponent(numbersReleasedView[1], GroupLayout.PREFERRED_SIZE, 212, GroupLayout.PREFERRED_SIZE)
										.addComponent(numbersReleasedView[2], GroupLayout.PREFERRED_SIZE, 212, GroupLayout.PREFERRED_SIZE))))
							.addContainerGap())
				);
				gl_LevelView.setVerticalGroup(
					gl_LevelView.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_LevelView.createSequentialGroup()
							.addComponent(selectedPiece, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
							.addComponent(boardView, GroupLayout.PREFERRED_SIZE, 384, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_LevelView.createSequentialGroup()
							.addContainerGap()
							.addComponent(levelInfo, GroupLayout.PREFERRED_SIZE, 115, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(availablePieces, GroupLayout.PREFERRED_SIZE, 274, GroupLayout.PREFERRED_SIZE)
							.addGap(35)
							.addComponent(numbersReleasedView[0], GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
							.addGap(6)
							.addComponent(numbersReleasedView[1], GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
							.addGap(6)
							.addComponent(numbersReleasedView[2], GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
							.addContainerGap(109, Short.MAX_VALUE))
				);
				setLayout(gl_LevelView);
		}
	}
	

}
