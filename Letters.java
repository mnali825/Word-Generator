package version_3;

import java.util.ArrayList;
import version_3.FindWords;

/**
 * Letters class that creates all permutations of a given String by user
 * 
 * @author mirali
 * @version 4.0 December 6th, 2014
 */
public class Letters {
	
	public static ArrayList<String> words = new ArrayList<String>();
	
	public static String wordCreator(String prefix, String letters) {
	    int length = letters.length();
	    
	    	//if each character has been used, return the current permutation of the letters
	        if (length == 0) {
	        	return prefix;
	        }
	        
	        //else recursively call on itself to create possible combinations by incrementing the letters
	        else {
	            for (int i = 0; i < length; i++) {
	                words.add(wordCreator(prefix + letters.charAt(i), letters.substring(0, i) + letters.substring(i+1, length)));
	            }
	        }
	        return prefix;
	}
}
