package com.luxoft.bankapp.domain;

import java.util.HashMap;
import java.util.Map;

public class AccountCache {

	public static final String SAVING = "saving";
	public static final String CHECKING = "checking";

	private static final Map<String, AbstractAccount> cache = new HashMap<>();

	private AccountCache() {}

	public static void loadCache() {
		SavingAccount savingAccount = new SavingAccount(0, 0.0);
		cache.put(SAVING, savingAccount);

		CheckingAccount checkingAccount = new CheckingAccount(0, 0.0, 0.0);
		cache.put(CHECKING, checkingAccount);
	}

	public static AbstractAccount cloneAccount(String accountType) {
		AbstractAccount cachedAccount = cache.get(accountType);
		if (cachedAccount == null) {
			throw new IllegalArgumentException("Unknown account type: " + accountType);
		}
		return cachedAccount.clone();
	}

	public static AbstractAccount getAccount(String accountType) {
		return cache.get(accountType);
	}

	public static void clearCache() {
		cache.clear();
	}
}
