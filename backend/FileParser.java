/*
 * @(#)FileParser.java 1.1 2010/03/25
 */
package backend;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * This class is designed to parse a Java file or a directory of Java files
 * depending on the input path given to the parser. The returned result is an
 * <code>ArrayList</code> of type lava.lang.String. This class is abstract and
 * should not be instantiated. All methods are static.
 *
 * @author William Croft
 * @version 1.1 2010/03/25
 */
public abstract class FileParser {

	/* do not instantiate this class */
	private FileParser() {
	}

	/**
	 * This is the main function of this class. The parser itself checks if the
	 * file path is a directory or a single file. The method will parse a file
	 * or directory of files into an ArrayList of Strings. The Strings in the
	 * list represent the individual files that were read in.
	 *
	 *
	 * @param path
	 *            The file path to be parsed. Will only parse Java files.
	 * @return an <code>ArrayList</code> of type lava.lang.String that contains
	 *         the files that have been parsed.
	 * @throws IOException
	 *             if there is a problem reading the files.
	 */
	public static ArrayList<String> parse(String path) throws IOException {
		// Check if path is a file or directory
		// If the path is a file
		if (new File(path).isFile()) {
			// Create a new ArrayList of type String
			ArrayList<String> result = new ArrayList<String>();
			// Call the single file parser and append it to the ArrayList
			result.add(parseFile(path));
			return result;
			// if the file is a directory
		} else if (new File(path).isDirectory()) {
			return parseFiles(path);
			// if the file is neither?
		} else {
			// Error. Alert user via console.
			System.err.println("Error: unable to parse given file path.");
			throw new FileNotFoundException();
		}
	}

	/**
	 * The method will take in a directory path and parse all Java files
	 * contained within the directory.
	 *
	 * @param path
	 *            The file path of the directory to be parsed
	 * @return an ArrayList of the parsed files.
	 * @throws IOException
	 *             if the there is a problem reading the files.
	 */
	private static ArrayList<String> parseFiles(String path) throws IOException {
		// Create the array list to be returned
		ArrayList<String> szFileArray = new ArrayList<String>();
		// Create a file to store the path in question as a workable file
		File directory = new File(path);
		// Get the list of files within the directory.
		String[] files = directory.list();
		// For each file in the directory
		for (int i = 0; i < files.length; i++) {
			// create a file to hold the sub-directory or File to be checked.
			File currentFile = new File(path.concat("\\" + files[i]));
			// If the file is a directory, then recurse.
			if (currentFile.isDirectory()) {
				ArrayList<String> result = parseFiles(currentFile.getPath());
				for(Iterator<String> iterator = result.iterator();
						iterator.hasNext();){
					szFileArray.add(iterator.next());
				}
			// If the file is indeed a Java file, then parse it and add it to
			// the array.
			}else if (currentFile.isFile()
					&&  currentFile.getName().contains(".")
					&& currentFile.getName().substring(
							currentFile.getName().lastIndexOf('.')).equals(
							".java")) {
				szFileArray.add(parseFile(currentFile.getPath()));
			}
		}
		return szFileArray;
	}

	/**
	 * The method will parse a single file into a string.
	 *
	 * @param path
	 *            The path of the file to be parsed.
	 * @return the String of the parsed file.
	 * @throws IOException
	 *             if the there is a problem reading the file.
	 */
	private static String parseFile(String path) throws IOException {
		// Create a reader to read the input of the file. (Buffered FileReader)
		BufferedReader reader = new BufferedReader(new FileReader(path));
		// Create a StringBugger to store concatenatable String data.
		StringBuffer szFile = new StringBuffer();
		// create a loop ton continue until EoF
		while (true) {
			// create a value to hold the next line to be read.
			String line = reader.readLine();
			// If the line is null, then the EoF has been reached.
			if (line == null)
				break;
			szFile.append(line);
			szFile.append(System.getProperty("line.separator"));
		}
		// turn the buffer into a true String and return.
		return szFile.toString();
	}
}