import java.util.random.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class RollDice {
	public static double die1 = (Math.random() * (6 - 1) + 1);
	public static double die2 = (Math.random() * (6 - 1) + 1);
	public static int dieTotal = (int) die1 + (int) die2;

//	public static ArrayList<Integer> reRollDice() {
//		ArrayList <Integer> newDie = new ArrayList<>();
//		newDie.add((int) (Math.random() * (6-1) +1));
//		newDie.add((int)Math.random() * (6-1)+1);
//		return newDie;
//	}
	
}
