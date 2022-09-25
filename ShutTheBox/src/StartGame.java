import java.util.Scanner;

public class StartGame {

	public static void main(String[] args) {
		// Rolls the dice
		System.out.println("Press 1 to roll dice.");
		Scanner userInputScanner = new Scanner(System.in);
		char userInputStart = userInputScanner.next().charAt(0);

		while (userInputStart != '1') {
			System.out.println("Press 1 to roll dice.");
			userInputStart = userInputScanner.next().charAt(0);
		}

		if (userInputStart == '1') {
			System.out.println("FIRST DIE: " + (int) RollDice.die1);
			System.out.println("SECOND DIE: " + (int) RollDice.die2);
		}
		while (!GameOver) {
		CloseNums.remainingNumbers();
		RollDice.die2;
		}
		
	}

}
