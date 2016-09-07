package version_3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Dictionary class that creates a String AVL tree, extending from the generic AVLTree class. 
 * 
 * @author mirali
 * @version 4.0 December 6th, 2014
 */
public class Dictionary extends AVLTree<String> {

	//general constructor
	public Dictionary() {
		root = null;
	}
	
	/**
	 * Creates a dictionary object that is an AVL tree
	 * reads the given file and stores it in an AVL tree
	 * @param file
	 */
	public Dictionary(File file) {
		this.root = null;
		Scanner dict = null;
		try {
			dict = new Scanner(file);
		}
		//if file not found...
		catch(FileNotFoundException e) {
			System.out.println("File was not found!");
		}
		//while dictionary file has words
		while(dict.hasNext()) {
			//insert into avl tree
			this.insert(dict.nextLine());
		}
		//close input
		dict.close();
	}
}
