package bank;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

public class User {

	// first name
	private String firstName;

	// last name
	private String lastName;

	// ID number
	private String uuid;

	// hash of the user's pin number
	private byte pinHash[];

	// list of accounts for this user.

	private ArrayList<Account> accounts;

	public User(String firstName, String lastName, String pin, Bank theBank) {

		// set user's name
		this.firstName = firstName;
		this.lastName = lastName;

		// store the pin's MD5 hash for security reasons
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			this.pinHash = md.digest(pin.getBytes());
		} catch (Exception e) {
			System.err.println("error, caught exeption : " + e.getMessage());
			System.exit(1);
		}

		// get a new, unique UUID (universal unique ID) for the user
		this.uuid = theBank.getNewUserUUID();

		// create empty list of accounts
		this.accounts = new ArrayList<Account>();

		// print log message
		System.out.printf("New user %s, %s with ID %s created.\n", lastName, firstName, this.uuid);

	}

	// get the user ID number

	public String getUUID() {
		return this.uuid;
	}

	// add an account

	public void addAccount(Account anAcct) {
		this.accounts.add(anAcct);
	}

	// get the number of accounts

	public int numAccounts() {
		return this.accounts.size();
	}

	// get the balance of an account in particular

	public double getAcctBalance(int acctIdx) {
		return this.accounts.get(acctIdx).getBalance();
	}

	// get the UUID of the account we want

	public String getAcctUUID(int acctIdx) {
		return this.accounts.get(acctIdx).getUUID();
	}

	// print the transaction history for an account

	public void printAcctTransHistory(int acctIdx) {
		this.accounts.get(acctIdx).printTransHistory();
	}

	// add a transaction to an account

	public void addAcctTransaction(int acctIdx, double amount, String memo) {
		this.accounts.get(acctIdx).addTransaction(amount, memo);
	}

	// check if a certain pin matches the user's pin

	public boolean validatePin(String aPin) {

		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			return MessageDigest.isEqual(md.digest(aPin.getBytes()), this.pinHash);
		} catch (Exception e) {
			System.err.println("error, caught exeption : " + e.getMessage());
			System.exit(1);
		}

		return false;
	}

	// print the user's accounts summary
	public void printAccountsSummary() {

		System.out.printf("\n\n%s's accounts summary\n", this.firstName);
		for (int a = 0; a < this.accounts.size(); a++) {
			System.out.printf("%d) %s\n", a + 1, this.accounts.get(a).getSummaryLine());
		}
		System.out.println();

	}

}
