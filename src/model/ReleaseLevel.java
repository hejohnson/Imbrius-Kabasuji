package model;

import java.io.IOException;
import java.io.Serializable;

import app.Game;
import controllers.player.ExitLevelButtonController;
import controllers.player.PuzzleBoardGameController;
import controllers.player.ReleaseBoardGameController;
import view.LevelView;
import view.NumbersReleasedView;

/** 
 * A ReleaseLevel handles the back end for a Release game mode. Tracking the end conditions and progress of 
 * the game.
 * @author dfontana
 * @author hejohnson
 */
public class ReleaseLevel extends AbstractLevelModel implements Serializable{
	/**Serialized ID used for writing to disk**/
	private static final long serialVersionUID = -1980934631273821149L;
	
	/**Each of these arrays can hold up to 1 set of numbers, of the corresponding color.
	 * Only numbers 1-6 exist, and only 1 set of each color exists. So when a number is released
	 * it is populated into the index = value-1**/
	transient int reds[];
	transient int greens[];
	transient int blues[];

	/**
	 * Generates a ReleaseLevel
	 * @param levelID - Id of this level being made
	 */
	public ReleaseLevel(int levelID) {
		super(levelID, "Release", false);
		initializeVars();
	}
	
	/**
	 * InitializeVars is here to redirect the contructor. When deserializing an object, it's 
	 * constructor is ignored. Because of this, any transient fields won't get initialized.
	 * To prevent this, a readObject() method is implemented and inside logic to initialize
	 * these fields is put there. To prevent duplicate code from the constructor, however,
	 * all this logic is done here.
	 * 
	 * Exploit this fact to initialize non-transient files in the constructor!
	 */
	void initializeVars() {
		reds = new int[6];
		greens = new int[6];
		blues = new int[6];
		
		for(int i=0; i<6; i++){
			reds[i] = -1;
			greens[i] = -1;
			blues[i] = -1;
		}
	}

	/**
	 * CheckStatus occurs after every move is made. This updates the stars earned for the current level if 
	 * a set has been released. Each set is checked in a separate statement as a way to ensure that if more
	 * that one set was released at a time, the number of stars earned is updated correctly. 
	 * 
	 * After the StarsEarned is modified, checkStatus then returns the boolean as to whether or not the level is 
	 * completed. It returns true if all sets are released OR the bullpen no longer has any pieces to be played.
	 * @return	boolean - true if level is complete
	 */
	@Override
	public boolean checkStatus() {
		boolean redSum = sumIsSix(reds);
		boolean blueSum = sumIsSix(blues);
		boolean greenSum = sumIsSix(greens);
		starsEarned = 0;
		if(redSum){  	starsEarned++;}
		if(blueSum){ 	starsEarned++;}
		if(greenSum){	starsEarned++;}
		
		return (redSum&&blueSum&&greenSum) || bullpen.isEmpty();
	}
	
	/**
	 * Helper method to update progress. Allows to check the array passed in sums to 6, indicating all
	 * numbers in a set is released.
	 * @param array being summed
	 * @return true if the array sums to 6
	 */
	boolean sumIsSix(int array[]){
		int total = 0;
		for(int i: array){ total += i; }
		if(total == 6){ return true;}
		return false;
	}
	
	/**
	 * Fills the index of the reds array with a marker, indicating the corresponding number was released.
	 * Only fills if the number has not already released (Aka: Handles duplicate numbers on board).
	 * @param releasedNum Is the number that was released
	 */
	public void addToRedReleased(int releasedNum){
		if(reds[releasedNum-1] != 1) { reds[releasedNum-1] = 1; }
	}
	
	/**
	 * Fills the index of the blues array with a marker, indicating the corresponding number was released.
	 * Only fills if the number has not already released (Aka: Handles duplicate numbers on board).
	 * @param releasedNum Is the number that was released
	 */
	public void addToBlueReleased(int releasedNum){
		if(blues[releasedNum-1] != 1) { blues[releasedNum-1] = 1; }
	}
	
	/**
	 * Fills the index of the greens array with a marker, indicating the corresponding number was released.
	 * Only fills if the number has not already released (Aka: Handles duplicate numbers on board).
	 * @param releasedNum Is the number that was released
	 */
	public void addToGreenReleased(int releasedNum){
		if(greens[releasedNum-1] != 1) { greens[releasedNum-1] = 1; }
	}

	/**
	 * Initializes the view to display correctly for a lightninglevel. 
	 * @param g - Game where levelView is located
	 * @return LevelView - view of the initialized LevelView
	 */
	@Override
	public LevelView initializeGame(Game g) {
		LevelView view = new LevelView("Release", new NumbersReleasedView(), this);
		view.addWindowListener(new ExitLevelButtonController(view, g));
		ReleaseBoardGameController rbgc = new ReleaseBoardGameController(g, view);
		view.getBoardView().addMouseListener(rbgc);
		view.getBoardView().addMouseMotionListener(rbgc);
		return view;
	}
	
	/**
	 * Returns a string representation of this level
	 * @return string of this level
	 */
	@Override
	public String toString(){
		return levelType+levelID+sumIsSix(reds)+sumIsSix(blues)+sumIsSix(greens)+board.toString()+bullpen.toString();
	}
	
	/**
	 * When deserializing this, the transient fields needs to be initialized.
	 * This method does just that, by calling the initialize method.
	 */
	private void readObject(java.io.ObjectInputStream in) throws IOException, ClassNotFoundException{
		in.defaultReadObject();
		initializeVars();
	}
}
