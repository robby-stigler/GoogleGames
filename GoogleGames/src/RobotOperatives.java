import java.util.ArrayList;
import java.util.Scanner;

public class RobotOperatives {
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		System.out.print("How many hours would you like to make robots? ");
		int numHours = sc.nextInt();
		System.out.print("Would you like to view the robots resulting from each hour's decision (y for Yes, "
				+ "other for No)? ");
		boolean viewHour = sc.next().equals("y");
		int bots = 0;
		/*Runs each round, starting at 1 hour left, then incrementing by one to 10.
		  At each round finds the builder level that will create the most level 0 
		  robots if run for the rest of the time, then adds the values from each hour*/
		for (int hour = numHours; hour > 0; hour--){
			
			//The max number of bots created as a result of this round
			int maxRoundBots = 0;
			
			/*Tests the possible build-levels for this round. Assumes that the possible
			  levels range from 0 to round - 1, as if the robot made were level round, 
			  it would never make a 0 level robot, thus never actually deploying spies*/
			for (int testVal = 0; testVal < hour; testVal++){
				
				//The list of robots created as a result of the testVal for this round
				ArrayList<Integer> robots = new ArrayList<Integer>();
				
				int roundsLeft = hour - 1;
				
				robots.add(testVal);
				
				while (roundsLeft > 0){
					ArrayList<Integer> newBots = new ArrayList<Integer>();
					newBots.addAll(robots);
					for (Integer i : robots){
						if (!i.equals(0)){
							newBots.add(i - 1);
						}
					}
					robots = newBots;
					roundsLeft--;
				}
				int numRobots = 0;
				for (Integer i : robots){
					if (i.equals(0)){
						numRobots++;
					}
				}
				if (numRobots > maxRoundBots){
					maxRoundBots = numRobots;
				}
			}
			if (viewHour){
				System.out.println("Robots made as a result of hour " + (numHours - hour + 1) + ": " + maxRoundBots);
			}
			bots += maxRoundBots;
		}
		System.out.println("Maximum possible spy robots = " + bots);
	}
}
