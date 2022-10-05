
public class BankAccount {

	public double balance;
	
	BankAccount(double Balance) {
		balance = Balance;
	}
	
	public void withdraw(double amountToWithdraw) throws NegativeBalanceException {
		
		if (amountToWithdraw > balance) {
			Double negativeBalance = balance - amountToWithdraw;
			throw new NegativeBalanceException(negativeBalance);
		}
		else if (amountToWithdraw <= balance) {
			balance = balance - amountToWithdraw;
		}
	}
	
	public void quickWithdraw(double amountToWithdraw) throws NegativeBalanceException {
		if (amountToWithdraw > balance) {
			throw new NegativeBalanceException();
		}
		else if (amountToWithdraw <= balance) {
			balance = balance - amountToWithdraw;
		}
	}
	
}
