package com.lti.services;

import com.lti.models.Shoes;

public interface EmployeeService {
	public abstract boolean addShoes(Shoes item);
	public abstract boolean acceptOffer(Shoes item);
	public abstract boolean rejectOffer(Shoes item);
	public abstract Shoes removeShoes(Shoes item);
}