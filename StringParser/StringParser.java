package StringParser;

import java.util.regex.*;

//Test
public class StringParser {
	private static int count;
	private static String tempString = "";

	private StringParser() {

	}

	public static void countAndRemoveComments(String string) {
		removeStrings(string);
		//System.out.println(string); 
		//System.out.println("\n");
		string = getString();
		Pattern commentPattern = Pattern.compile("\\//.*\\n*");
		Matcher matcher = commentPattern.matcher(string);
		int count = 0;
		String replace = "";
		while (matcher.find())
			count++;
		string = matcher.replaceAll(replace);
		Pattern multilineCommentPattern = Pattern
				.compile("(?:/\\*(?:[^*]|(?:\\*+[^*/]))*\\*+/)|(?://.*)");
		Matcher multilineMatcher = multilineCommentPattern.matcher(string);
		while(multilineMatcher.find())
			count++;
		string = multilineMatcher.replaceAll(replace);
		setCount(count);
		System.out.println(string);
		setString(string);
	}
	
	// TEXT 
	public static void removeStrings(String string) {
		Pattern stringPattern = Pattern.compile("(\").*([\"][\\s])*(\")");
		Matcher matcher = stringPattern.matcher(string);
		String replace = "\"\"";
		string = matcher.replaceAll(replace);
		setString(string);
	}

	public static void setString(String string) {
		tempString = string;
	}

	public static String getString() {
		return tempString;
	}

	// System.out.println();
	public static void setCount(int newCount) {
		count = newCount;
	}

	public static int getCount() {
		return count;
	}
}
