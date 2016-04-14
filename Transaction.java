package bank;

import java.util.Date;

public class Transaction {

	// amount of transaction
	private double amount;

	// time and date of transaction

	private Date timestamp;

	// memo for transaction
	private String memo;

	// account in wich the transaction was made
	private Account inAccount;

	public Transaction(double amount, Account inAccount) {

		this.amount = amount;
		this.inAccount = inAccount;
		this.timestamp = new Date();
		this.memo = "";

	}

	// new transaction with memo
	public Transaction(double amount, String memo, Account inAccount) {

		// call the other constructor first
		this(amount, inAccount);

		this.memo = memo;

	}

	// get the transaction amount.

	public double getAmount() {
		return this.amount;
	}

	// get a summary string
	public String getSummaryLine() {

		if (this.amount >= 0) {
			return String.format("%s, $%.02f : %s", this.timestamp.toString(), this.amount, this.memo);
		} else {
			return String.format("%s, $(%.02f) : %s", this.timestamp.toString(), -this.amount, this.memo);
		}
	}

}
