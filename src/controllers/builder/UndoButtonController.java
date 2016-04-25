package controllers.builder;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import app.Builder;
import app.UndoManager;
import model.Board;
import view.BuilderView;
import view.SelectedPieceView;

/**
 * Controller to link to the undo button in the level builder
 * @author awharrison
 * @author dfontana
 */
public class UndoButtonController implements ActionListener{
	/**Link to the UndoManager singleton for the button to perform actions on**/
	UndoManager manager;
	/**The board having undos done to it**/
	Board board;
	/**View of the board for redrawing/painting after undo**/
	BuilderView builderView;
	/**Piece preview that would need to be redrawn after undo**/
	SelectedPieceView spv;
	
	public UndoButtonController(Builder builder){
		this.builderView = builder.getBuilderView();
		this.board = builder.getCurrentLevel().getBoard();
		this.spv = builderView.getSelectedPieceView();
		
		manager = UndoManager.getInstance();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(manager.undo()){
			board.clearPiecePreview();
			spv.getPiecePanel().redraw();
			spv.getPiecePanel().repaint();
			builderView.getBoardView().redraw();
			builderView.getBoardView().repaint();
		}else{
			//No more moves to undo
			//TODO Gray out button. How do you get the button to be reenabled, then?
		}
	}
}
