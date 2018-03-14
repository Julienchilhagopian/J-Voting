package contract1;

import java.util.ArrayList;

public class StrictPreference {
	private ArrayList<Alternative> preferences;
	
	/**
	 * Creates a list of strict preferences with the list given as a parameter
	 * @param preferences
	 */
	public StrictPreference(ArrayList<Alternative> preferences) {
		for(int i=0;i<preferences.size();i++) {
			if(preferences.lastIndexOf(preferences.get(i))!=i) {
				throw new IllegalArgumentException("Une liste de pr�f�rences ne peut pas contenir plusieurs fois la m�me alternative");
			}
		}
		this.preferences=preferences;
	}
	
	/**
	 * 
	 * @return the list of strict preferences
	 */
	public ArrayList<Alternative> getPreferences(){
		return preferences;
	}
}
