package io.github.oliviercailloux.y2018.j_voting.profiles.gui;

public class ProfileGUI {
	/* 1) 
	 * get a StrictProfile (which one ? how ?) --> check that it is SOC :
	*	if(!(strictProfile.isStrict() && strictProfile.isComplete()){
	*		throw new IllegalArgumentException("Profile is not in SOC format");
	*	}
	* 2)
	* StrictProfile --> Map ? : 
	* Map<Voter, Preference> profile = strictProfile.getProfile()
	* 
	* 3)
	* create table with size :
	* 		width : strictProfile.getNbVoters();
	*  		length : strictProfile.getProfile().get(1).size();
	*
	* 4)
	* populate table with map :
	* 		NavigableSet<Voter>  voters = strictProfile.getAllVoters(); --> first row of data
	* 		for(Voter voter : voters){
	* 			createColumn(voter) --> populate it with getPreference(voter);
	* 		}
	*
	* 5)
	* create "window" : 
	*
	* public static void main(String[] args) {
	*	Display display = new Display();
	*	Shell shell = new Shell(display);
	*	shell.setLayout(new FillLayout());
	*	new ProfileTableViewer(shell);
	*	shell.open();
	*
	*	while (!shell.isDisposed()) {
	*		if (!display.readAndDispatch())
	*			display.sleep();
	*	}
	*
	*	display.dispose();
	*
	* }
	* 
	*/
}
