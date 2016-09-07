package version_3;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

import version_3.Dictionary;

/**
 * This program creates possible words of a given word, cross checks it with a given
 * dictionary file, then prints out all valid words. The FindWords class is the main 
 * class that makes all the calls to methods within other classes that create the
 * possible words (Letters class) and does the searches (Dictionary class) 
 * 
 * Dictionary file is given in the command line
 *  
 * @author mirali
 * @version 4.0 December 6th, 2014
 * 
 */
public class FindWords {
	
	protected static String Letter = "";
	protected static ArrayList<String> possibleWords = new ArrayList<>();
	protected static ArrayList<String> finalWords = new ArrayList<>();
	protected static ArrayList<Integer> binaryCheck = new ArrayList<>();
	private static String[] letters;
	private static String l;
	private static int errorCounter = 0;
	public static Dictionary dictionary;

	
	public static void main(String[] args) {
		//check to make sure dictionary is loaded
		if (args.length < 1) {
			errorCounter++;
			System.out.println("       " + errorCounter + " Error(s) Reported:");
			System.out.println("-----------------------------------");
			System.err.printf("(1) Invalid number of arguments.\n", args);
			}
		//read dictionary file and save values in ArrayList
		File file = new File(args[0]);
		dictionary = new Dictionary(file);
		//print out instructions for user
		System.out.println("Welcome to the Word Generator");
		System.out.println("Please enter a list of letters ranging from 2-10 characters");
		System.out.println("Hit ENTER to finish");
		//allow user to input characters
		Scanner input = new Scanner(System.in);
		//read characters, convert to lower case & save them as String
		l = input.nextLine().toLowerCase();
		errorCounter = 0;
		//if list length is too short, print error
		long start = System.nanoTime();
		if (!l.matches("^[a-zA-Z]+$")) {
			errorCounter++;
			System.out.println("       " + errorCounter + " Error(s) Reported:");
			System.out.println("-----------------------------------");
			System.out.println("(1) Only accepts alphabetical characters");
			System.exit(0);
		}
		if (l.length() < 2) {
			errorCounter++;
			System.out.println("       " + errorCounter + " Error(s) Reported:");
			System.out.println("-----------------------------------");
			System.out.println("(" + errorCounter + ") Out of Bounds Error\nInput must be 2 characters or more");				
			System.exit(0);
		}
		//if list length is too long, print error
		else if (l.length() > 10) {
			errorCounter++;
			System.out.println("       " + errorCounter + " Error(s) Reported:");
			System.out.println("-----------------------------------");
			System.out.println("(" + errorCounter + ") Out of Bound Error\nInput must be 10 characters or less");
			System.exit(0);
		}
		//if input is good...
		else if (l.length() >= 2 && l.length() <= 10) {
			//split characters and enter into array
			letters = l.split("");
		//fill ArrayList with elements of user input
			for (int i = 0; i < letters.length; i++) {
				Letter = Letter + letters[i];
				}
			//close input
			input.close();
			//invoke wordCreator which creates all possible permutations from user input
			Letters.wordCreator("", Letter);
		}
		
		//add all elements from the binary search into an arrayList 
		for (int i = 0; i < Letters.words.size(); i++) {
			if (dictionary.contains(Letters.words.get(i)) == true) {
				finalWords.add(Letters.words.get(i));
			}
		}
		
		//alphabetize final list
		Collections.sort(finalWords);
		//remove duplicates
		removeDuplicates(finalWords);
		System.out.println("\nNumber of Possible Combinations: " + Letters.words.size());
		System.out.println("\nNumber of Valid Words: " + finalWords.size());
		System.out.println("\nList of Possible Words:");
		//print out all valid words
		for (int i = 0; i < finalWords.size(); i++) {
			System.out.println(finalWords.get(i));
		}				
		//if no words found print out message
		if (finalWords.size() == 0) {
			System.out.println("\nNo words found!");
		}
		long stop = System.nanoTime();
		double result = (stop - start) / 1000000000.0; 
		System.out.println("\nTime Elapsed: " + result + " seconds");
}
	/**
	 * removes duplicates from an arrayList list
	 * @param list
	 * @return true or false if action complete
	 */
protected static boolean removeDuplicates(ArrayList<String> list) {
	boolean modified = false;		
	try {
		for (int i = 0; i < list.size() - 1; i++) {
			int current_i = i;
			if (list.get(i).equals(list.get(i+1))) {
				list.remove(i);
				i = current_i - 1;
				modified = true;
			}
		}
	}
	catch(IllegalArgumentException e) {
		System.err.println("Exception: " + e.getMessage());
		modified = false;
	}
		return modified;
}
}
