package bank;

import java.util.ArrayList;
import java.util.Random;

public class Bank {
	// name of the bank.

	private String name;

	// list of the account holders of the bank

	private ArrayList<User> users;

	// list of the accounts of the bank.

	private ArrayList<Account> accounts;

	// new Bank object with empty lists of users and accounts.

	public Bank(String name) {

		this.name = name;

		// init users and accounts
		users = new ArrayList<User>();
		accounts = new ArrayList<Account>();

	}

	// generate a new UUID for a user

	public String getNewUserUUID() {

		String uuid;

		Random rng = new Random();
		int len = 6;
		boolean nonUnique;

		// loop till we get an unique ID
		do {

			// generate random number
			uuid = "";
			for (int c = 0; c < len; c++) {
				uuid += ((Integer) rng.nextInt(10)).toString();
			}

			// check to make sure it's unique
			nonUnique = false;
			for (User u : this.users) {
				if (uuid.compareTo(u.getUUID()) == 0) {
					nonUnique = true;
					break;
				}
			}

		} while (nonUnique);

		return uuid;
	}

	// generate a new UUID for an account

	public String getNewAccountUUID() {

		String uuid;
		Random rng = new Random();
		int len = 10;
		boolean nonUnique = false;

		// loop till we get an unique ID
		do {

			// generate random number
			uuid = "";
			for (int c = 0; c < len; c++) {
				uuid += ((Integer) rng.nextInt(10)).toString();
			}

			// check to make sure it's unique
			for (Account a : this.accounts) {
				if (uuid.compareTo(a.getUUID()) == 0) {
					nonUnique = true;
					break;
				}
			}

		} while (nonUnique);

		return uuid;

	}

	public User addUser(String firstName, String lastName, String pin) {

		// creates a new User object and add it to the list
		User newUser = new User(firstName, lastName, pin, this);
		this.users.add(newUser);

		// creates a savings account for the user and add it to the list
		Account newAccount = new Account("Savings", newUser, this);
		newUser.addAccount(newAccount);
		this.accounts.add(newAccount);

		return newUser;

	}

	// adds an existing account for a particular user object

	public void addAccount(Account newAccount) {
		this.accounts.add(newAccount);
	}

	// gets the user object associated with a particular userID and pin, if they
	// are valid

	public User userLogin(String userID, String pin) {

		// search through the list of users
		for (User u : this.users) {

			// if the user is found, and the pin is correct, returns the user
			// object
			if (u.getUUID().compareTo(userID) == 0 && u.validatePin(pin)) {
				return u;
			}
		}

		// if the user is not found or the pin is incorrect, returns null
		return null;

	}

	// get the name of the bank.
	public String getName() {
		return this.name;
	}

}
