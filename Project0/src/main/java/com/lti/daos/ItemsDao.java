package com.lti.daos;

import java.util.List;

import com.lti.models.Shoes;

public interface ItemsDao {
	public abstract Shoes getItemById(int id);
	public abstract List<Shoes> getItems();
	public abstract boolean addItem(Shoes shoe); //returns if successfull
	public abstract boolean updateItem(Shoes shoe);
	public abstract int removeItem(Shoes shoe);
	public abstract List<Shoes> getShoebyCustomerId(int id);
	

}
