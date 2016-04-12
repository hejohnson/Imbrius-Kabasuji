package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/** An AbstractLevelModel class determines what kind of information all three types of 
 * levels should store inside of them and the kinds of functionality they should have.
 * @author Dylan
 */
public class LightningLevel extends AbstractLevelModel{

	LightningLevel(File sourceFile, int levelID) {
		super(sourceFile, levelID, "Lightning", false);
		// TODO Auto-generated constructor stub
	}

	/**LoadLevel is a helper method to the constructor. On instantiation, it will attempt to
	 * read any data about the level it can in. If nothing is found inside the file, then no 
	 * fields are set and it's apparent the level is being CREATED in the BUILDER. Setters will 
	 * handle the rest from here out in that case.
	 * 
	 * If the file can't be opened, the error is caught here. 
	 * 
	 * Method for reading: line by line through a buffer. File is closed at end of reading.
	 */
	void loadLevel(){
		try{
			FileReader fileReader = new FileReader(sourceFile);
			BufferedReader br = new BufferedReader(fileReader);
			String strLine;
			while ((strLine = br.readLine()) != null)   {
				//Read the file LINE by LINE and do something with each line
				strLine.length(); //Do something with strLine...
			}
			//End Of File Reached, Close the file
			fileReader.close();
		}catch (IOException e){//Catch exception if any
			System.err.println("Error: " + e.getMessage());
		}
	}

	@Override
	boolean saveProgressInFile() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	boolean saveLevelToFile() {
		// TODO Auto-generated method stub
		return false;
	}
	

	@Override
	boolean isComplete() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	boolean hasWon() {
		// TODO Auto-generated method stub
		return false;
	}
}
