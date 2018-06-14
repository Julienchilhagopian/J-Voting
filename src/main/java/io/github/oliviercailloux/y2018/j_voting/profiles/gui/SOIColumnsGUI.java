package io.github.oliviercailloux.y2018.j_voting.profiles.gui;

import java.io.*;
import java.util.*;
import java.util.List;

import org.eclipse.swt.*;
import org.eclipse.swt.events.*;
import org.eclipse.swt.layout.*;
import org.eclipse.swt.widgets.*;
import org.slf4j.*;

import com.google.common.base.Preconditions;

import io.github.oliviercailloux.y2018.j_voting.*;
import io.github.oliviercailloux.y2018.j_voting.profiles.*;
import io.github.oliviercailloux.y2018.j_voting.profiles.management.*;

public class SOIColumnsGUI extends ProfileDefaultGUI {

	private static final Logger LOGGER = LoggerFactory.getLogger(SOIColumnsGUI.class.getName());
	
	/**
	 * @return a list of Strings : the text to put in the columns titles (here the Voters)
	 */
	public static List<String> createColumns() {
		LOGGER.debug("createColumns :");
		StrictProfileI strictProfile = profileBuilder.createStrictProfileI();//if profile get from file is SOI, create a StrictProfile from it

		Iterable<Voter> allVoters = strictProfile.getAllVoters(); //get voters from profile
		
		int i = 0; 
		
		//COLUMNS
		List<String> titles = new ArrayList<>();
		for(Voter v : allVoters){
			titles.add("Voter " + v.getId());
			i++;
		}
		for (i = 0 ; i < titles.size() ; i++) {
			TableColumn column = new TableColumn (table, SWT.NONE);
			column.setText(titles.get(i));
		}
		
		return titles;
	}

	/**
	 * Populate rows with the right data (here each row i is each ith alternative with blank cell i,j if ith alternative doesn't exist in preference j)
	 */
	public static void populateRowsSOI() {
		LOGGER.debug("populateRowsSOI :");
		//ROWS
		StrictProfileI strictProfile = profileBuilder.createStrictProfileI();
		List<String> alternatives = new ArrayList<>();
		
		int nbAlternatives = strictProfile.getMaxSizeOfPreference();

		for(int i = 0 ; i < nbAlternatives ; i++){
			TableItem item = new TableItem (table, SWT.NONE);
			item.setText(strictProfile.getIthAlternativesAsStrings(i).toArray(new String[nbAlternatives]));	// create a row with ith alternatives
			alternatives.clear(); // empty the list
		}
	}

	public static void main (String [] args) throws IOException {
		LOGGER.debug("Main");
		profileBuilder = tableDisplay(args);
	}

}

