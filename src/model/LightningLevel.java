package model;

import java.io.IOException;
import java.io.Serializable;

import app.Game;
import controllers.player.ExitLevelButtonController;
import controllers.player.LightningBoardGameController;
import view.LevelView;
import view.TimeRemainingView;

/** 
 * A LightningLevel handles the back end for a Lightning game mode. Tracking the end conditions and progress of 
 * the game.
 * @author dfontana
 */
public class LightningLevel extends AbstractLevelModel implements Serializable{
	/**Serialized ID used for writing to disk**/
	private static final long serialVersionUID = 407028463108073009L;
	
	/**Total time the level has to be played, in seconds**/
	int totalTime;

	/**
	 * Makes a lightninglevel
	 * @param levelID id of this level being made
	 */
	public LightningLevel(int levelID) {
		super(levelID, "Lightning", false);
		totalTime = 0;
	}
	
	/**
	 * CheckStatus occurs after every move is made. This updates the stars earned for the current level if
	 * the number of marked tiles is equal to the thresholds. The starsEarned is set rather than incremented
	 * to prevent duplicate triggers of the same threshold. (Ie place a piece that marks all but 12 tiles and
	 * the place another piece that marks all but 8 tiles. That would increment twice - not wanted).
	 * 
	 * CheckStatus then returns true if the level's termination conditions are met or not (all tiles marked).
	 * @return boolean - true if level is done
	 */
	@Override
	public boolean checkStatus() {
		int unmarkedTiles = board.getNumBoardTiles();
		
		if(unmarkedTiles <= 12 && unmarkedTiles > 6){	starsEarned = 1;}
		if(unmarkedTiles <= 6 && unmarkedTiles > 0){ 	starsEarned = 2;}
		if(unmarkedTiles == 0){ 						starsEarned = 3;}
		
		return (unmarkedTiles == 0);
	}
	
	/**
	 * Sets the totalTime for the lightning level
	 * @param totalTime
	 */
	public void setTotalTime(int totalTime){
		this.totalTime = totalTime;
	}
	
	/**
	 * Returns the totalTime this lightning level allows
	 * @return int - totalTime in seconds
	 */
	public int getTotalTime(){
		return this.totalTime;
	}
	
	/**
	 * Initializes the view to display correctly for a lightninglevel. 
	 * @param g - Game where the levelView is located
	 * @return LevelView - view of the initialized LevelView
	 */
	@Override
	public LevelView initializeGame(Game g) {
		LevelView view = new LevelView("Lightning", new TimeRemainingView(), this);
		view.addWindowListener(new ExitLevelButtonController(view, g));
		LightningBoardGameController lbgc = new LightningBoardGameController(g, view);
		view.getBoardView().addMouseListener(lbgc);
		view.getBoardView().addMouseMotionListener(lbgc);
		return view;
	}
	
	/**
	 * Creates a toString of this level
	 * @return string representation of this level
	 */
	@Override
	public String toString(){
		return levelType+levelID+totalTime+board.toString()+bullpen.toString();
	}
	
	/**
	 * When deserializing this, the transient fields needs to be initialized.
	 * This method does just that, by calling the initialize method.
	 */
	private void readObject(java.io.ObjectInputStream in) throws IOException, ClassNotFoundException{
		in.defaultReadObject();
	}

}
