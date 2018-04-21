package io.github.oliviercailloux.y2018.j_voting;

import java.util.*;
import java.io.*;
import org.slf4j.*;


public class ReadProfile {
	
	static Logger log = LoggerFactory.getLogger(ReadProfile.class.getName());	
	private static int id =1;// id is the id of the next voter that will be created in the profile
	/**
	 * @param path a string : the path of the file to read 
	 * @return fileRead, a list of String where each element is a line of the SOC or SOI file read
	 *
	 */
	public static List<String> fromSOCorSOI(String path) throws IOException {
		log.debug("fromSOCorSOI : \n") ;
		Objects.requireNonNull(path);
		log.debug("parameter : path = {}\n",path);
		InputStream f= ReadProfile.class.getClassLoader().getResourceAsStream(path);
		try(BufferedReader in = new BufferedReader(new InputStreamReader(f))){
			String line;
			List<String> fileRead = new ArrayList<String>();
			while ((line= in.readLine()) != null) {
				log.debug("next line : {}\n",line);
				fileRead.add(line);
			}
		    return fileRead;
	    }
	}
	
	/**
	 * @param fileRead a list of strings : data which was read in a SOC/SOI file
	 * This function prints strings from the list passed as an argument
	 * 
	 */
	public static void displayProfileFromReadFile(List<String> fileRead){
		log.debug("displayProfileFromReadFile : \n") ;
		Objects.requireNonNull(fileRead);
		log.debug("parameter : fileRead = {}\n",fileRead);
		Iterator<String> it = fileRead.iterator();
		while(it.hasNext()){
			System.out.println(it.next());
		}
	}
	
	/**
	 * 
	 * @param nbAlternatives the number of alternatives in the profile
	 * @param file a list of strings each containing an alternative
	 * @return the alternatives in the profile given in a list of string.
	 */
	public static StrictPreference getAlternatives(int nbAlternatives,List<String> file){
		log.debug("GetAlternatives :") ;
		Objects.requireNonNull(nbAlternatives);
		Objects.requireNonNull(file);
		log.debug("parameters : nbAlternatives = {}, file = {}\n",nbAlternatives,file); 
		Iterator<String> it = file.iterator();
		String s1; 
		LinkedHashSet<Alternative> alternatives= new LinkedHashSet<Alternative>();
		for(int k=1;k<=nbAlternatives;k++){//we add each alternative to a list
			s1=it.next();
			log.debug("next Alternative : {}\n",s1);
			if (s1.contains(",")){//line with alternative doesn't contain ,
				throw new Error("Error: nbAlternative is not correct");
			}
			alternatives.add(new Alternative(Integer.parseInt(s1)));
		}
		StrictPreference listAlternatives = new StrictPreference(alternatives);
		log.debug("returns listAlternatives : {}\n",listAlternatives);
		return listAlternatives;
	}
	
	
	/**
	 * 
	 * @param s the line with the voters statistics (number, sum of count, number of unique alternatives)
	 * @return a list with the three computed statistics
	 */
	public static List<Integer> getStatsVoters(String s){
		log.debug("GetNbVoters :") ;
		Objects.requireNonNull(s);
		log.debug("parameter : s={}\n",s);
		List<Integer> list=new ArrayList<Integer>();
		String[] line=s.split(",");
		list.add(Integer.parseInt(line[0].trim()));
		list.add(Integer.parseInt(line[1].trim()));
		list.add(Integer.parseInt(line[2].trim()));
		log.debug("returns list : {}\n",list);
		return list;
	}
	
	/**
	 * 
	 * @param listeAlternatives the alternatives of the profile
	 * @param s1 a line of the profile containing the number of voters for a preference followed by the preference (list of alternatives)
	 * @return the StrictPreference given in the line s1
	 */
	public static StrictPreference getPreferences(StrictPreference listeAlternatives, String s1){
		log.debug("getPreferences\n");
		Objects.requireNonNull(listeAlternatives);
		Objects.requireNonNull(s1);
		log.debug("parameters : listeAlternatives {} , s1 {}\n",listeAlternatives,s1);
		String [] s2=s1.split(",");
		LinkedHashSet<Alternative> pref= new LinkedHashSet<Alternative>();
		for(String strAlter : s2){//we collect all the alternatives
			Alternative alter = new Alternative(Integer.parseInt(strAlter.trim()));
			log.debug("next alternative {}\n",alter);
			if(listeAlternatives.contains(alter)) {
				log.debug("correct alternative");
				pref.add(alter);
			}
			else {
				log.debug("alternative not in the profile");
				throw new IllegalArgumentException("The line s1 contains an alternative that is not in the profile's alternatives");
			}
		}
		return new StrictPreference(pref);
	}
	
	/**
	 * 
	 * @param pref a StrictPreference
	 * @param nbVoters the number of voters that voted for the preference as parameter
	 * @param profile the StrictProfile to which the votes will be added
	 */
	public static void addVotes(StrictPreference pref, int nbVoters, StrictProfile profile){
		log.debug("addVotes\n");
		Objects.requireNonNull(pref);
		Objects.requireNonNull(nbVoters);
		Objects.requireNonNull(profile);
		log.debug("parameters : nbVoters {} for the preference {}\n",nbVoters,pref); 
		for(int m=0 ; m<nbVoters ; m++){//we create as many profiles as voters 
			Voter v =new Voter(id);
			log.debug("adds th voter {} and the pref as parameter to the profile\n",id);
			id++;
			profile.addProfile(v,pref);
		}
	}

	/**
	 * 
	 * @param file the lines with the number of votes for each preference
	 * @param listAlternatives the alternatives of the profile
	 * @param nbVoters the number of voters
	 * @return the created StrictProfile
	 */
	public static StrictProfile buildProfile(List<String> file, StrictPreference listAlternatives, int nbVoters){
		log.debug("GetProfiles :") ;
		Objects.requireNonNull(file);
		Objects.requireNonNull(listAlternatives);
		Objects.requireNonNull(nbVoters);
		Iterator<String> it = file.iterator();
		StrictProfile profile = new StrictProfile();
		String s1; //where we store the current line
		while(it.hasNext()){
			s1=it.next();
			if (!s1.contains(",")){// if the line doesn't contain , it's the line of an alternative
				throw new IllegalArgumentException("the first string of file is an alternative line.");
			}
			else{
				StrictPreference pref=getPreferences(listAlternatives,s1);
				addVotes(pref, nbVoters,profile);
			}

		}
		return profile;
	}
	

	/**
	 * Creates a StrictProfile with the information of the fileRead List<String> extracted from a file.
	 * @param fileRead
	 * @return 
	 */

	public static StrictProfile createProfileFromReadFile(List<String> fileRead){
		log.debug("createProfileFromReadFile : \n") ;
		Objects.requireNonNull(fileRead);
		Iterator<String> it = fileRead.iterator();
		StrictProfile SProfile= new StrictProfile();
		String line_nb_voters;
		int nbAlternatives=Integer.parseInt(it.next());	//first number of the file is the number of alternative
		List<String> Alternatives=new ArrayList<String>();
		List<String> Profiles=new ArrayList<String>();
		for(int i=1;i<=nbAlternatives;i++){//get the lines with the alternatives
			Alternatives.add(it.next());
		}
		line_nb_voters=it.next();//get the line with the nb of voters
		while(it.hasNext()){//get the rest of the file
			Profiles.add(it.next());
		}
		StrictPreference listeAlternatives =getAlternatives(nbAlternatives,Alternatives);
		List<Integer> list_int= getStatsVoters(line_nb_voters);
		SProfile=buildProfile(Profiles,listeAlternatives, list_int.get(0));
		return SProfile;
	}
	
	
	
	
	/**
	 * This function calls fromSOCorSOI function if there is a SOC or a SOI file in the FILES directory
	 * 
	 **/
	public static void main(String[] args) throws IOException {
		List<String> socToRead = fromSOCorSOI("io/github/oliviercailloux/y2018/j_voting/profil.soc"); 
		@SuppressWarnings("unused")
		StrictProfile SProfile = createProfileFromReadFile(socToRead);
		
	
		// read SOC file
		/*List<String> socToRead = fromSOCorSOI("io/github/oliviercailloux/y2018/j_voting/profil.soc"); 
		System.out.println("SOC file :");
		displayProfileFromReadFile(socToRead);
		
		// read SOI file
		List<String> soiToRead = fromSOCorSOI("io/github/oliviercailloux/y2018/j_voting/profil.soi"); 
		System.out.println("SOI file :");
		displayProfileFromReadFile(soiToRead);*/

	}
}