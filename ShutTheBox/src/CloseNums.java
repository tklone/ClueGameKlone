import java.util.ArrayList;
import java.util.Scanner;

public class CloseNums {
	public static ArrayList<Integer> listNums = new ArrayList<>();
	public static int totalUserInput = 0;
	
	public static ArrayList <Integer> remainingNumbers() {
		Scanner userChoice = new Scanner(System.in);
		Integer userInputNums;

		for (Integer i = 1; i < 10; i++) {
			listNums.add(i);
		}

		System.out.println(listNums);

		while (totalUserInput != RollDice.dieTotal) {
			userInputNums = userChoice.nextInt();
			totalUserInput = totalUserInput + userInputNums;

			// Remove that number from the arraylist of numbers
			for (int i = 0; i < listNums.size(); i++) {
				if (listNums.get(i) == userInputNums) {
					listNums.remove(i);
				}
			}
			System.out.println(listNums);
			System.out.println(totalUserInput);
		}
		return listNums;
	}

//	public static void main(String[] args) {
//		Scanner userChoice = new Scanner(System.in);
//		Integer userInputNums;
//
//		for (Integer i = 1; i < 10; i++) {
//			listNums.add(i);
//		}
//
//		System.out.println(listNums);
//
//		while (totalUserInput != RollDice.dieTotal) {
//			userInputNums = userChoice.nextInt();
//			totalUserInput = totalUserInput + userInputNums;
//
//			// Remove that number from the arraylist of numbers
//			for (int i = 0; i < listNums.size(); i++) {
//				if (listNums.get(i) == userInputNums) {
//					listNums.remove(i);
//				}
//			}
//			System.out.println(listNums);
//			System.out.println(totalUserInput);
//		}
//
////		if (totalUserInput == RollDice.dieTotal) {
////			RollDice.die1 = RollDice.reRollDice().get(0);
////			RollDice.die2 = RollDice.reRollDice().get(1);
////		}
//	}
}
