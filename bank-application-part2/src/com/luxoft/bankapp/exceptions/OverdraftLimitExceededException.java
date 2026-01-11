package com.luxoft.bankapp.exceptions;

public class OverdraftLimitExceededException extends NotEnoughFundsException {
	
	private static final long serialVersionUID = -3737648528527468343L;

	private final double overdraft;

	public OverdraftLimitExceededException(NotEnoughFundsException e, double overdraft) {
		super(e.getMessage());

		this.setId(e.getId());
	    this.setBalance(e.getBalance());
	    this.setAmount(Math.round(getAmount() * 100) / 100d);
        this.overdraft = overdraft;
    }
	
	public double getOverdraft() {
		return overdraft;
	}

}
