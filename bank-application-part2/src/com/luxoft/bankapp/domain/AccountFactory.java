package com.luxoft.bankapp.domain;

import com.luxoft.bankapp.utils.AccountType;

public class AccountFactory {

	private AccountFactory() {

	}

	public static Account createSavingAccount(int id, double amount) {
		return new SavingAccount(id, amount);
	}

	public static Account createSavingAccount(int id, double amount, Currency currency) {
		return new SavingAccount(id, amount, currency);
	}

	public static Account createCheckingAccount(int id, double amount, double overdraft) {
		return new CheckingAccount(id, amount, overdraft);
	}

	public static Account createCheckingAccount(int id, double amount, double overdraft, Currency currency) {
		return new CheckingAccount(id, amount, overdraft, currency);
	}

	public static Account createAccount(AccountType accountType, int id, double amount, double overdraft) {
        return switch (accountType) {
            case SAVING -> createSavingAccount(id, amount);
			case CHECKING -> createCheckingAccount(id, amount, overdraft);
        };
	}

	public static Account createAccount(AccountType accountType, int id, double amount, double overdraft, Currency currency) {
        return switch (accountType) {
            case SAVING -> createSavingAccount(id, amount, currency);
            case CHECKING -> createCheckingAccount(id, amount, overdraft, currency);
        };
	}
}
