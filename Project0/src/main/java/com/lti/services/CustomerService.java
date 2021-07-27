package com.lti.services;

public interface CustomerService {
	public abstract boolean makeOffer(String item);
	public abstract double remainingBalance(String item);
}
