package com.luxoft.bankapp.tests;

import com.luxoft.bankapp.utils.AccountType;
import org.junit.Test;

import com.luxoft.bankapp.domain.Account;
import com.luxoft.bankapp.domain.AccountFactory;
import com.luxoft.bankapp.domain.CheckingAccount;
import com.luxoft.bankapp.domain.Currency;
import com.luxoft.bankapp.domain.SavingAccount;

import static org.junit.Assert.*;

public class TestAccountFactory {
	
	@Test
	public void testCreateSavingAccount() {
		Account account = AccountFactory.createSavingAccount(1, 1000.0);
		
		assertNotNull(account);
		assertTrue(account instanceof SavingAccount);
		assertEquals(1, account.getId());
		assertEquals(1000.0, account.getBalance(), 0.0);
	}
	
	@Test
	public void testCreateSavingAccountWithCurrency() {
		Currency currency = new Currency("USD");

		Account account = AccountFactory.createSavingAccount(2, 2000.0, currency);
		assertNotNull(account);
		assertTrue(account instanceof SavingAccount);

		SavingAccount savingAccount = (SavingAccount) account;

		assertEquals(2, savingAccount.getId());
		assertEquals(2000.0, savingAccount.getBalance(), 0.0);
		assertEquals(currency, savingAccount.getCurrency());
	}
	
	@Test
	public void testCreateCheckingAccount() {
		Account account = AccountFactory.createCheckingAccount(3, 1500.0, 100.0);
		
		assertNotNull(account);
		assertTrue(account instanceof CheckingAccount);
		CheckingAccount checkingAccount = (CheckingAccount) account;
		assertEquals(3, checkingAccount.getId());
		assertEquals(1500.0, checkingAccount.getBalance(), 0.0);
		assertEquals(100.0, checkingAccount.getOverdraft(), 0.0);
	}
	
	@Test
	public void testCreateCheckingAccountWithCurrency() {
		Currency currency = new Currency("EUR");
		Account account = AccountFactory.createCheckingAccount(4, 2500.0, 200.0, currency);
		
		assertNotNull(account);
		assertTrue(account instanceof CheckingAccount);

		CheckingAccount checkingAccount = (CheckingAccount) account;

		assertEquals(4, checkingAccount.getId());
		assertEquals(2500.0, checkingAccount.getBalance(), 0.0);
		assertEquals(200.0, checkingAccount.getOverdraft(), 0.0);
		assertEquals(currency, checkingAccount.getCurrency());
	}
	
	@Test
	public void testCreateAccountByTypeSaving() {
		Account account = AccountFactory.createAccount(AccountType.SAVING, 5, 3000.0, 0.0);
		
		assertNotNull(account);
		assertTrue(account instanceof SavingAccount);

		assertEquals(5, account.getId());
		assertEquals(3000.0, account.getBalance(), 0.0);
	}
	
	@Test
	public void testCreateAccountByTypeChecking() {
		Account account = AccountFactory.createAccount(AccountType.CHECKING, 6, 4000.0, 150.0);
		
		assertNotNull(account);
		assertTrue(account instanceof CheckingAccount);

		CheckingAccount checkingAccount = (CheckingAccount) account;

		assertEquals(6, checkingAccount.getId());
		assertEquals(4000.0, checkingAccount.getBalance(), 0.0);
		assertEquals(150.0, checkingAccount.getOverdraft(), 0.0);
	}
	
	@Test
	public void testCreateAccountByTypeWithCurrency() {
		Currency currency = new Currency("GBP");
		
		Account savingAccount = AccountFactory.createAccount(
				AccountType.SAVING, 7, 5000.0, 0.0, currency);

		assertTrue(savingAccount instanceof SavingAccount);
		assertEquals(currency, ((SavingAccount) savingAccount).getCurrency());
		
		Account checkingAccount = AccountFactory.createAccount(
				AccountType.CHECKING, 8, 6000.0, 250.0, currency);
		
		assertTrue(checkingAccount instanceof CheckingAccount);
		assertEquals(currency, ((CheckingAccount) checkingAccount).getCurrency());
		assertEquals(250.0, ((CheckingAccount) checkingAccount).getOverdraft(), 0.0);
	}
	
	@Test
	public void testFactoryMethodsReturnDifferentInstances() {
		Account account1 = AccountFactory.createSavingAccount(11, 1000.0);
		Account account2 = AccountFactory.createSavingAccount(12, 1000.0);
		
		assertNotNull("Account1 should not be null", account1);
		assertNotNull("Account2 should not be null", account2);

        assertNotSame("Accounts should be different instances", account1, account2);

		assertEquals("Account1 id should be 11", 11, account1.getId());
		assertEquals("Account2 id should be 12", 12, account2.getId());
	}
}
