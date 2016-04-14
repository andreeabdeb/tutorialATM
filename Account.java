package bank;

import java.util.ArrayList;

public class Account {

	// name of the account.

	private String name;

	// account ID number.

	private String uuid;

	// user object owner of the account

	private User holder;

	// list of transactions for this account.

	private ArrayList<Transaction> transactions;

	// creates new Account instance

	public Account(String name, User holder, Bank theBank) {

		// set the account name and holder
		this.name = name;
		this.holder = holder;

		// get next account UUID
		this.uuid = theBank.getNewAccountUUID();

		// init transactions
		this.transactions = new ArrayList<Transaction>();

	}

	// gets the account number and return the UUID
	public String getUUID() {
		return this.uuid;
	}

	// adds a new transaction in this account.
	public void addTransaction(double amount) {

		// create new transaction and add it to our list
		Transaction newTrans = new Transaction(amount, this);
		this.transactions.add(newTrans);

	}

	public void addTransaction(double amount, String memo) {

		// creates new transaction and adds it to the list
		Transaction newTrans = new Transaction(amount, memo, this);
		this.transactions.add(newTrans);

	}

	// gets the balance of this account by adding the amounts of the
	// transactions.

	public double getBalance() {

		double balance = 0;
		for (Transaction t : this.transactions) {
			balance += t.getAmount();
		}
		return balance;

	}

	public String getSummaryLine() {

		// get the account's balance
		double balance = this.getBalance();

		// format summary line depending on the balance
		if (balance >= 0) {
			return String.format("%s : $%.02f : %s", this.uuid, balance, this.name);
		} else {
			return String.format("%s : $(%.02f) : %s", this.uuid, balance, this.name);
		}

	}

	// prints transaction history for account

	public void printTransHistory() {

		System.out.printf("\nTransaction history for account %s\n", this.uuid);
		for (int t = this.transactions.size() - 1; t >= 0; t--) {
			System.out.println(this.transactions.get(t).getSummaryLine());
		}
		System.out.println();

	}

}
