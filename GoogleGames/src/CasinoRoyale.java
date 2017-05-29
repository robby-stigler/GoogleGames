import java.util.ArrayList;
import java.util.Arrays;

public class CasinoRoyale {
	
	static int[][] hands;
	
	public static void main(String[] args){
		createHands();
		hand2();
		hand4();
	}

	/*2112*/
	public static void hand2(){
		int numHands = 0;
		for (int[] currHand : hands){
			int[] suits = new int[5];
			int[] vals = new int[5];
			for (int i = 0; i < 5; i++){
				suits[i] = (currHand[i] - 1) / 13;
				vals[i] = (currHand[i] - 1) % 13 + 1;
			}
			int sum = vals[0] + vals[1] + vals[2] + vals[3] + vals[4];
			if (suits[0] < 2 && suits[1] < 2 && suits[2] < 2 && suits[3] < 2 && suits[4] < 2 &&
				sum == 32 && distinctVals(vals)){
				numHands++;
			}
		}
		System.out.println("hand2: " + numHands);
	}
	
	public static boolean distinctVals(int[] vals){
		for (int i = 0; i < 4; i++){
			for (int j = i + 1; j < 5; j++){
				if (vals[i] == vals[j]){
					return false;
				}
			}
		}
		return true;
	}
	/* 520 */
//	public static void hand3(){
//		int numHands = 0;
//		for (int[] currHand : hands){
//			
//			for (int i = 0; i < 5; i++){
//			}
//			
//		}
//		System.out.println("Num possible hand4: " + numHands);
//	}
	
	public static void hand4(){
		int numHands = 0;
		for (int[] currHand : hands){
			int[] suits = new int[5];
			int[] vals = new int[5];
			for (int i = 0; i < 5; i++){
				suits[i] = (currHand[i] - 1) / 13;
				vals[i] = (currHand[i] - 1) % 13 + 1;
			}
			int sum = vals[0] + vals[1] + vals[2] + vals[3] + vals[4];
			if (suits[0] + suits[1] + suits[2] + suits[3] + suits[4] == 1 &&
				((sum > 11 && sum < 27) || (sum > 45 && sum < 60))){
				numHands++;
			}
		}
		System.out.println("Num possible hand4: " + numHands);
	}
	/* 2005
	 * Creates all possible hands as combinations of numbers 0 - 51
	  Suit breakdown:
		Spades: 0-12
		Clubs: 13-25
		Diamonds: 26-38
		Hearts: 39-51
	  Values are the number % 13 (0 = ace, 10 = jack, 11 = queen, 12 = king)
	*/
	public static void createHands(){
		hands = new int[2598960][5];
		int currHand = 0;
		for (int card1 = 1; card1 < 53; card1++){
			for (int card2 = 1; card2 < card1; card2++){
				for (int card3 = 1; card3 < card2; card3++){
					for (int card4 = 1; card4 < card3; card4++){
						for (int card5 = 1; card5 < card4; card5++){
							hands[currHand] = new int[] {card1, card2, card3, card4, card5};
							currHand++;
						}
					}
				}
			}
		}
	}
}
