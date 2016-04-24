package controllers.builder;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import controllers.common.Move;
import controllers.common.MovePieceOffBoardMove;
import controllers.common.MovePieceOnBoardMove;
import controllers.common.PlacePieceOnBoardFromBullpenMove;
import view.BoardView;
import view.BuilderView;
import view.BullpenView;
import view.ReleaseNumberCreationView;
import view.SelectedPieceView;
import model.AbstractLevelModel;
import model.AbstractTile;
import model.Board;
import model.Bullpen;
import model.Piece;
import model.PieceTile;

/**
 * Controls all actions having to do with the board in the Builder application
 * Handles showing piece placement previews, placing pieces, dragging pieces around, and removing them from the board
 * Also handles swapping types of tiles, empty <-> board <-> release tiles (including changing number/color)
 * 
 * @author awharrison
 * @author Dylan
 * @author hejohnson
 */

public class BuilderBoardController implements MouseListener, MouseMotionListener {
	BuilderView bView;
	AbstractLevelModel m;
	Board board;
	Bullpen bp;
	BullpenView bpv;
	Piece draggedPiece;
	BoardView boardView;
	SelectedPieceView spv;
	ReleaseNumberCreationView rncv;
	boolean mouseOn;
	int rOffset;
	int cOffset;

	/** 
	 * 
	 * @param builderView The BuilderView that this controller updates the state of
	 * @param levelModel The level entity that is being built
	 */
	public BuilderBoardController(BuilderView bView, AbstractLevelModel lm) {
		this.bView = bView;
		this.m = lm;
		this.board = lm.getBoard();
		this.bp = lm.getBullpen();
		this.bpv = bView.getBullpenView();
		this.boardView = bView.getBoardView();
		this.rncv = bView.getReleaseNumberView();
		this.spv = bView.getSelectedPieceView();
	}

	/**
	 * Select a piece that is currently on the board, if the tile that was clicked is part of a piece. 
	 * Removes piece from the board, and sets it as the dragged piece in the board entity
	 * @param me MouseEvent
	 */
	@Override
	public void mousePressed(MouseEvent me) {
		if(bView.getStateOfPlacement()){
			AbstractTile source  = board.getTileAt(me.getX(), me.getY());
			if (source instanceof PieceTile) {
				board.setDraggedPiece(((PieceTile)source).getPiece());
				rOffset = -((PieceTile)source).getRowInPiece();
				cOffset = -((PieceTile)source).getColInPiece();
				board.removePiece(board.getDraggedPiece());	
				boardView.redraw();
				boardView.repaint();
			}	
		}

	}

	/**
	 * Convert tile on board into another form: Release <-> Release <-> Board <-> Empty.
	 * Using a released action allows the user to click as quick as they want, preventing accidental behavior not related to a click
	 * (like a press, move, release instead of just a click).
	 * @param me MouseEvent
	 */
	@Override
	public void mouseReleased(MouseEvent me) {
		if(bView.getStateOfPlacement()){
			if(bView.getStateOfBoardConvert()){
				Move move = null;
				AbstractTile source = board.getTileAt(me.getX(), me.getY());
				if (mouseOn) {
					move = new PieceToNewBoardTilesMove(m, source, bpv);
					move.doMove();
					spv.getPiecePanel().redraw();
					spv.getPiecePanel().repaint();
				}
			}else if(bView.getStateOfHintConvert()){

			}else{
				Move move = null;
				AbstractTile source = board.getTileAt(me.getX(), me.getY());
				if (mouseOn) {
					move = new MovePieceOnBoardMove(m, source, board.getDraggedPiece(), rOffset, cOffset);
					if(!move.doMove()){
						move = new PlacePieceOnBoardFromBullpenMove(m, source, bpv);
						move.doMove();
					}
					spv.getPiecePanel().redraw();
					spv.getPiecePanel().repaint();
				}
			}
		}else if(rncv.getNumberSelected() < 0){
			AbstractTile source = board.getTileAt(me.getX(), me.getY());
			Move move;
			move = new SwapTileBoardToEmptyMove(source, board);
			if(!move.doMove()){
				move = new SwapTileEmptyToBoardMove(source, board);
				if(!move.doMove()){
					move = new SwapTileReleaseToBoardMove(source, board);	
					move.doMove();
				}
			}
		}else{
			AbstractTile source = board.getTileAt(me.getX(), me.getY());
			Move move;
			move = new SwapTileBoardToReleaseMove(rncv, source, board);
			if(!move.doMove()){
				move = new SwapTileReleaseToReleaseMove(rncv, source, board);
				move.doMove();
			}
		}
		boardView.redraw();
		boardView.repaint();
	}

	/**
	 * Tracks if mouse is over the board
	 * @param me MouseEvent
	 */
	@Override
	public void mouseEntered(MouseEvent me) {
		mouseOn = true;
	}

	/**
	 * If currently dragging a piece, remove the piece from the board and return it to the bullpen
	 * 
	 * @param me MouseEvent
	 */
	@Override
	public void mouseExited(MouseEvent me) {
		if(bView.getStateOfPlacement()){
			mouseOn = false;
			Move move = new MovePieceOffBoardMove(m, bpv);
			move.doMove();
			board.clearPiecePreview();
			boardView.redraw();
			boardView.repaint();
		}
	}


	/**
	 * Shows a preview of the piece being dragged (if there is one)
	 * 
	 * @param me MouseEvent
	 */
	@Override
	public void mouseDragged(MouseEvent me) {
		if(bView.getStateOfPlacement()){
			AbstractTile source  = board.getTileAt(me.getX(), me.getY());
			Piece p;
			if (board.getDraggedPiece() == null) {
				return;
			} else {
				p = board.getDraggedPiece();
			}
			board.clearPiecePreview();
			board.showPiecePreview(p, source.getRow()+rOffset, source.getCol()+cOffset);
			boardView.redraw();
			boardView.repaint();
		}
	}


	/**
	 * Show a preview of the piece that is selected to be placed (if it exists)
	 * 
	 * @param me MouseEvent
	 */
	@Override
	public void mouseMoved(MouseEvent me) {
		if(bView.getStateOfPlacement()){
			if(bView.getStateOfBoardConvert()){
				Piece p;
				AbstractTile source  = board.getTileAt(me.getX(), me.getY());
				p = bp.getSelectedPiece();

				if(p != null){
					board.clearPiecePreview();
					board.showConversionPreview(p, source.getRow(), source.getCol());
					boardView.redraw();
					boardView.repaint();
				}
			}else{
				Piece p;
				AbstractTile source  = board.getTileAt(me.getX(), me.getY());
				p = bp.getSelectedPiece();

				if(p != null){
					board.clearPiecePreview();
					board.showPiecePreview(p, source.getRow(), source.getCol());
					boardView.redraw();
					boardView.repaint();
				}
			}
		}
	}

	//====================== UNUSED BUT REQUIRED ======================//
	@Override
	public void mouseClicked(MouseEvent me) {}
}
