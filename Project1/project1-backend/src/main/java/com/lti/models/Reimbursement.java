package com.lti.models;

import java.security.Timestamp;
import java.util.Arrays;
import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name = "ERS_REIMBURSEMENT")
public class Reimbursement {
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "REIMB_ID",nullable = false)
	private int reimbId;
	
	@Column(name = "REIMB_AMOUNT",nullable = false)
	private double reimbAmount;
	
	@Temporal(value=TemporalType.TIMESTAMP)
	@Column(name = "REIMB_SUBMITTED")
	private Date reimbSubmit;
	
	@Temporal(value=TemporalType.TIMESTAMP)
	@Column(name = "REIMB_RESOLVED")
	private Date reimbResolve;
	
	@Column(name = "REIMB_DESCRIPTION")
	private String reimbDescript;
	
	@Column(name = "REIMB_RECEIPT")
	private byte[] reimbReceipt;
	
	@ManyToOne(targetEntity = User.class) 
	@JoinColumn(name = "REIMB_AUTHOR", nullable = false)
	private User reimbAuthor;
	
	
	@ManyToOne(targetEntity = User.class) 
	@JoinColumn(name = "REIMB_RESOLVER")
	private User reimbResolver;
	

	@ManyToOne(targetEntity = ReimburseStatus.class)
	@JoinColumn(name = "REIMB_STATUS_ID", nullable = false)
	private ReimburseStatus reimbStatusId;
	
	@ManyToOne(targetEntity = ReimburseType.class)
	@JoinColumn(name = "REIMB_TYPE_ID", nullable = false)
	private ReimburseType reimbTypeId;

	
	
	
	public Reimbursement(double reimbAmount, Date reimbSubmit, User reimbAuthor, ReimburseType reimbTypeId,
			ReimburseStatus reimbStatusId) {
		super();
		this.reimbAmount = reimbAmount;
		this.reimbSubmit = reimbSubmit;
		this.reimbAuthor = reimbAuthor;
		this.reimbStatusId = reimbStatusId;
		this.reimbTypeId = reimbTypeId;
	}

	public Reimbursement() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Reimbursement(int reimbId, double reimbAmount, Date reimbSubmit, User reimbAuthor, ReimburseStatus reimbStatusId,
			ReimburseType reimbTypeId) {
		super();
		this.reimbId = reimbId;
		this.reimbAmount = reimbAmount;
		this.reimbSubmit = reimbSubmit;
		this.reimbAuthor = reimbAuthor;
		this.reimbStatusId = reimbStatusId;
		this.reimbTypeId = reimbTypeId;
	}

	public int getReimbId() {
		return reimbId;
	}

	public void setReimbId(int reimbId) {
		this.reimbId = reimbId;
	}

	public double getReimbAmount() {
		return reimbAmount;
	}

	public void setReimbAmount(double reimbAmount) {
		this.reimbAmount = reimbAmount;
	}

	public Date getReimbSubmit() {
		return reimbSubmit;
	}

	public void setReimbSubmit(Date reimbSubmit) {
		this.reimbSubmit = reimbSubmit;
	}

	public Date getReimbResolve() {
		return reimbResolve;
	}

	public void setReimbResolve(Date reimbResolve) {
		this.reimbResolve = reimbResolve;
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

	public User getReimbAuthor() {
		return reimbAuthor;
	}

	public void setReimbAuthor(User reimbAuthor) {
		this.reimbAuthor = reimbAuthor;
	}

	public User getReimbResolver() {
		return reimbResolver;
	}

	public void setReimbResolver(User reimbResolver) {
		this.reimbResolver = reimbResolver;
	}

	public ReimburseStatus getReimbStatusId() {
		return reimbStatusId;
	}

	public void setReimbStatusId(ReimburseStatus reimbStatusId) {
		this.reimbStatusId = reimbStatusId;
	}

	public ReimburseType getReimbTypeId() {
		return reimbTypeId;
	}

	public void setReimbTypeId(ReimburseType reimbTypeId) {
		this.reimbTypeId = reimbTypeId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(reimbAmount);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((reimbAuthor == null) ? 0 : reimbAuthor.hashCode());
		result = prime * result + ((reimbDescript == null) ? 0 : reimbDescript.hashCode());
		result = prime * result + reimbId;
		result = prime * result + Arrays.hashCode(reimbReceipt);
		result = prime * result + ((reimbResolve == null) ? 0 : reimbResolve.hashCode());
		result = prime * result + ((reimbResolver == null) ? 0 : reimbResolver.hashCode());
		result = prime * result + ((reimbStatusId == null) ? 0 : reimbStatusId.hashCode());
		result = prime * result + ((reimbSubmit == null) ? 0 : reimbSubmit.hashCode());
		result = prime * result + ((reimbTypeId == null) ? 0 : reimbTypeId.hashCode());
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
		if (Double.doubleToLongBits(reimbAmount) != Double.doubleToLongBits(other.reimbAmount))
			return false;
		if (reimbAuthor == null) {
			if (other.reimbAuthor != null)
				return false;
		} else if (!reimbAuthor.equals(other.reimbAuthor))
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
		if (reimbResolve == null) {
			if (other.reimbResolve != null)
				return false;
		} else if (!reimbResolve.equals(other.reimbResolve))
			return false;
		if (reimbResolver == null) {
			if (other.reimbResolver != null)
				return false;
		} else if (!reimbResolver.equals(other.reimbResolver))
			return false;
		if (reimbStatusId == null) {
			if (other.reimbStatusId != null)
				return false;
		} else if (!reimbStatusId.equals(other.reimbStatusId))
			return false;
		if (reimbSubmit == null) {
			if (other.reimbSubmit != null)
				return false;
		} else if (!reimbSubmit.equals(other.reimbSubmit))
			return false;
		if (reimbTypeId == null) {
			if (other.reimbTypeId != null)
				return false;
		} else if (!reimbTypeId.equals(other.reimbTypeId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Reimbursement [reimbId=" + reimbId + ", reimbAmount=" + reimbAmount + ", reimbSubmit=" + reimbSubmit
				+ ", reimbResolve=" + reimbResolve + ", reimbDescript=" + reimbDescript + ", reimbReceipt="
				+ Arrays.toString(reimbReceipt) + ", reimbAuthor=" + reimbAuthor + ", reimbResolver=" + reimbResolver
				+ ", reimbStatusId=" + reimbStatusId + ", reimbTypeId=" + reimbTypeId + "]";
	}
	

	
	
}
