
public class ATM {

	BankAccount bankAccount;
	
	ATM() {
		bankAccount = new BankAccount(500);
	}
	
	public  void handleTransactions() throws NegativeBalanceException {
		try {
			bankAccount.withdraw(600);
		} catch (NegativeBalanceException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) throws NegativeBalanceException {
		ATM atm = new ATM();
		atm.handleTransactions();
	}
	
}
