import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class RockAndRoll {

	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		//System.out.print("What are the dimensions of the map (h w)? ");
		//int numMapCols = sc.nextInt();
		//int numMapRows = sc.nextInt();
		
		File movementsFile = new File("movements.txt");
		File mapFile = new File("seaMap.txt");
		int[][] map = new int[20][20];
		int[] movements = new int[0];
		try {
			map = getMapFromFile(mapFile, 20, 20);
			movements = getMovementsFromFile(movementsFile);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		testStartingPosition(map, movements);
	}
	
	public static void testStartingPosition(int[][] map, int[] moves){
		for (int startRow = 0; startRow < map.length; startRow++){
			for (int startCol = 0; startCol < map[0].length; startCol++){
				if (map[startRow][startCol] == 1){
					
				} else {
					int currRow = startRow;
					int currCol = startCol;
					for (int moveIndex = 0; moveIndex < moves.length; moveIndex++){
						int move = moves[moveIndex];
						switch (move){
							case 0: currRow--;
									break;
							case 1: currCol--;
									break;
							case 2: currRow++;
									break;
							case 3: currCol++;
									break;
						}
						if (currRow < 0 || currRow >= map.length || currCol < 0 || currCol >= map[0].length || map[currRow][currCol] == 1){
							System.out.println("Moving to " + currRow + ", " + currCol);
							System.out.println();
							break;
						} else if (moveIndex == moves.length - 1){
						}
					}
				}
			}
		}
	}
	
	public static int[] getMovementsFromFile(File movementsFile) throws FileNotFoundException{
		Scanner num = new Scanner(movementsFile);
		int numMoves = 0;
		while (num.hasNext()){
			num.next();
			numMoves++;
		}
		int[] moves = new int[numMoves];
		Scanner getMoves = new Scanner(movementsFile);
		int index = 0;
		while (getMoves.hasNext()){
			char move = getMoves.next().charAt(0);
			if (move == 'N'){
				moves[index] = 0;
			} else if (move == 'W'){
				moves[index] = 1;
			} else if (move == 'S'){
				moves[index] = 2;
			} else if (move == 'E'){
				moves[index] = 3;
			}
			index++;
		}
		return moves;
	}
	
	public static int[][] getMapFromFile(File mapFile, int numRows, int numCols) throws FileNotFoundException{
		int[][] map = new int[numRows][numCols];
		Scanner mapScanner = new Scanner(mapFile);
		for (int row = 0; row < numRows; row++){
			for (int col = 0; col < numCols; col++){
				if (mapScanner.hasNextInt()){
					map[row][col] = mapScanner.nextInt();
				} else {
					System.out.println("You done fucked up!");
				}
			}
		}
		return map;
	}
}
