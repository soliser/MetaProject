/**
 * @author Kandra Wilson
 * 3/1/10
 * This Class is design to Count number of Lines and also number of static Attributes.
 * 
 * 
 */
package McCabe;

public class Counter {
	private static int count;
	private static int staticCount;

	private Counter() {
	}

	// Count the number of lines. Start from beginning location and count each
	// individual line.
	public static void countLines(String string, int startingLocation,
			int endLocation, int count) {
		if (startingLocation == endLocation) {
			setCount(count);
			return;
		} else {
			int tempIndex = string.indexOf("\n", startingLocation);
			if (tempIndex != endLocation)
				tempIndex++;
			if (string.indexOf("\n", tempIndex) != -1) {
				count++;
				countLines(string, tempIndex, endLocation, count);
			}
		}

	}

	public static void setCount(int newCount) {
		count = newCount;
	}

	public static int getCount() {
		return count;
	}

	// Count the number of Static Attributes. Ignores "static" in comments.
	public static void countStaticAttributes(String string,
			int startingLocation, int endLocation, int count) {
		
		}
	

	public static void setStaticCount(int newCount) {
		staticCount = newCount;
	}

	public static int getStaticCount() {
		return staticCount;
	}

}
