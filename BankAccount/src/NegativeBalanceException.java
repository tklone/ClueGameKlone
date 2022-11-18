import java.io.PrintWriter;

public class NegativeBalanceException extends Exception {

	public Double negativeBalance;

	public NegativeBalanceException() {
		super("Error: negaative balance");
	}

	public NegativeBalanceException(Double negativeBalance) {
		super("Amount exceeds balance by " + negativeBalance.toString());

		try {
			PrintWriter printWriter = new PrintWriter("logfile.txt");
			printWriter.println("Amount exceeds balance by " + negativeBalance);
			printWriter.close();
		} catch (Exception e) {
			System.out.println("Error writing to file");
			e.getMessage();
		}

		//This is not the toString() method but I need to make one still
		System.out.println("Balance of " + negativeBalance.toString() + " not allowed.");
		
	}
}
