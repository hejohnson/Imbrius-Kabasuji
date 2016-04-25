package controllers.common;

import model.Bullpen;
import model.Piece;

/**
 * Move for flipping a piece horizontally (over the vertical axis)
 * @author hejohnson
 */
public class FlipHorizontalMove implements IMove {
	/** The piece to be flipped **/
	Piece p;
	/** The bullpen that contains the piece **/
	Bullpen bp;
	
	/**
	 * Rotates a piece and provides undo/redo functionality for the builder
	 * @param p The selected piece to be flipped
	 * @param bp The bullpen that contains the selected piece
	 */
	FlipHorizontalMove (Piece p, Bullpen bp) {
		this.p = p;
		this.bp = bp;
	}

	/**
	 * Executes the move by flipping the piece horizontally (around a vertical axis)
	 * @return True if the move is valid and was executed
	 */
	@Override
	public boolean doMove() {
		if (isValid()) {
			p.flipH();
			return true;
		}
		return false;
	}

	/**
	 * The move can be done if the selected piece of the bullpen is equal to the 
	 * piece being manipulated
	 * @return True if the piece is the selected piece
	 */
	@Override
	public boolean isValid() {
		return bp.getSelectedPiece().equals(p);
	}

	/**
	 * Executes the move again, since flipping is it's own inverse
	 * @return True if the undo was valid and executed properly
	 */
	@Override
	public boolean undo() {
		return doMove();
	}

	/**
	 * Executes the move again
	 * @return True if the undo is valid and executed properly
	 */
	@Override
	public boolean redo() {
		return doMove();
	}

}
