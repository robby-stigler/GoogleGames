import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class BoomerangCode {

	public static void main(String[] args){
		File block = new File("boomerang.txt");
		String boom = "";
		try {
			Scanner getVals = new Scanner(block);
			while (getVals.hasNextLine()){
				String nextLine = getVals.nextLine();
				boom += nextLine;
			}
			String columns = getColumns(boom, 70);
			
			getLargestBoomerang(boom);
			getLargestBoomerang(columns);
		} catch (FileNotFoundException e){
			e.printStackTrace();
		}
	}
	
	public static String getColumns(String block, int lineSize){
		String newBlock = "";
		for (int i = 0; i < lineSize; i++){
			for (int j = 0; j < lineSize; j++){
				newBlock += block.charAt((j * 70) + i);
			}
		}
		return newBlock;
	}
	
	public static void getLargestBoomerang(String encrypted){
		ArrayList<String> dirs = new ArrayList<String>();
		int startIndex = 0;
		int currIndex = 1;
		boolean goUp = encrypted.charAt(1) > encrypted.charAt(0);
		currIndex++;
		int numSwitches = 0;
		while (currIndex < encrypted.length()){
			if (goUp){
				while (currIndex < encrypted.length() && 
						(encrypted.charAt(currIndex) >= encrypted.charAt(currIndex - 1))){
					currIndex++;
				}
				
			} else {
				while (currIndex < encrypted.length() && 
						(encrypted.charAt(currIndex) <= encrypted.charAt(currIndex - 1))){
					currIndex++;
				}
			}
			String dir = encrypted.substring(startIndex, currIndex);
			dirs.add(dir);
			startIndex = currIndex - 2;
			goUp = !goUp;
		}
		printLargestPair(dirs);
	}
	
	public static void printLargestPair(ArrayList<String> dirs){
		int currIndex = 1;
		int pairSize = dirs.get(0).length() + dirs.get(1).length();
		int largestIndex = 0;
		while (currIndex < dirs.size() - 1){
			int newSize = dirs.get(currIndex).length() + dirs.get(currIndex + 1).length();
			if (newSize > pairSize){
				largestIndex = currIndex;
				pairSize = newSize;
			}
			currIndex++;
		}
		System.out.println(dirs.get(largestIndex) + dirs.get(largestIndex + 1).substring(1));
		System.out.println(new StringBuilder(dirs.get(largestIndex + 1)).reverse().toString()
						+ new StringBuilder(dirs.get(largestIndex)).reverse().toString().substring(1));
	}
}