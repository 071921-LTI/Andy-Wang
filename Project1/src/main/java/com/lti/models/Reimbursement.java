package com.lti.models;

import java.util.Arrays;

public class Reimbursement {
	private int reimbId;
	private double reimbAmout;
	private String reimbDescript;
	private byte[] reimbReceipt;
	private int reimbAuthor;
	private int reimbResolver;
	private int reimbStatusId;
	private int reimbTypeId;
	
	public Reimbursement() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Reimbursement(int reimbId, double reimbAmout, String reimbDescript, byte[] reimbReceipt, int reimbAuthor,
			int reimbResolver, int reimbStatusId, int reimbTypeId) {
		super();
		this.reimbId = reimbId;
		this.reimbAmout = reimbAmout;
		this.reimbDescript = reimbDescript;
		this.reimbReceipt = reimbReceipt;
		this.reimbAuthor = reimbAuthor;
		this.reimbResolver = reimbResolver;
		this.reimbStatusId = reimbStatusId;
		this.reimbTypeId = reimbTypeId;
	}
	public int getReimbId() {
		return reimbId;
	}
	public void setReimbId(int reimbId) {
		this.reimbId = reimbId;
	}
	public double getReimbAmout() {
		return reimbAmout;
	}
	public void setReimbAmout(double reimbAmout) {
		this.reimbAmout = reimbAmout;
	}
	public String getReimbDescript() {
		return reimbDescript;
	}
	public void setReimbDescript(String reimbDescript) {
		this.reimbDescript = reimbDescript;
	}
	public byte[] getReimbReceipt() {
		return reimbReceipt;
	}
	public void setReimbReceipt(byte[] reimbReceipt) {
		this.reimbReceipt = reimbReceipt;
	}
	public int getReimbAuthor() {
		return reimbAuthor;
	}
	public void setReimbAuthor(int reimbAuthor) {
		this.reimbAuthor = reimbAuthor;
	}
	public int getReimbResolver() {
		return reimbResolver;
	}
	public void setReimbResolver(int reimbResolver) {
		this.reimbResolver = reimbResolver;
	}
	public int getReimbStatusId() {
		return reimbStatusId;
	}
	public void setReimbStatusId(int reimbStatusId) {
		this.reimbStatusId = reimbStatusId;
	}
	public int getReimbTypeId() {
		return reimbTypeId;
	}
	public void setReimbTypeId(int reimbTypeId) {
		this.reimbTypeId = reimbTypeId;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(reimbAmout);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + reimbAuthor;
		result = prime * result + ((reimbDescript == null) ? 0 : reimbDescript.hashCode());
		result = prime * result + reimbId;
		result = prime * result + Arrays.hashCode(reimbReceipt);
		result = prime * result + reimbResolver;
		result = prime * result + reimbStatusId;
		result = prime * result + reimbTypeId;
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
		Reimbursement other = (Reimbursement) obj;
		if (Double.doubleToLongBits(reimbAmout) != Double.doubleToLongBits(other.reimbAmout))
			return false;
		if (reimbAuthor != other.reimbAuthor)
			return false;
		if (reimbDescript == null) {
			if (other.reimbDescript != null)
				return false;
		} else if (!reimbDescript.equals(other.reimbDescript))
			return false;
		if (reimbId != other.reimbId)
			return false;
		if (!Arrays.equals(reimbReceipt, other.reimbReceipt))
			return false;
		if (reimbResolver != other.reimbResolver)
			return false;
		if (reimbStatusId != other.reimbStatusId)
			return false;
		if (reimbTypeId != other.reimbTypeId)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Reimbursement [reimbId=" + reimbId + ", reimbAmout=" + reimbAmout + ", reimbDescript=" + reimbDescript
				+ ", reimbReceipt=" + Arrays.toString(reimbReceipt) + ", reimbAuthor=" + reimbAuthor
				+ ", reimbResolver=" + reimbResolver + ", reimbStatusId=" + reimbStatusId + ", reimbTypeId="
				+ reimbTypeId + "]";
	}
	
	
}
