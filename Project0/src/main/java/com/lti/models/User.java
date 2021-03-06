package com.lti.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class User implements Serializable{

	private String username;
	private String password;
	private int id;
	private List<BidList> bids = new ArrayList<>();
	public int getId() {
		return id;
	}
	
	
	public List<BidList> getBids() {
		return bids;
	}


	public void addToBids(BidList bid) {
		this.bids.add(bid);
	}
	
	public void editBidStatus(int item_id,String status) {
		for (BidList bid:bids) {
			if (bid.getItemId() == item_id) {
				bid.setItemStatus(status);
			}
		}
	}

	public void setId(int id) {
		this.id = id;
	}
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	public User(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}
	public String getUsername() {
		return username;
	}
	public User(String username, int id) {
		super();
		this.username = username;
		this.id = id;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (id != other.id)
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "User [username=" + username + ", password=" + password + ", id=" + id + "]";
	}
	
	public String toFileString() {
		return username + ":" + password;
	}
}
