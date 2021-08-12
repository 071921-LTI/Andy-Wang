package com.lti.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ERS_REIMBURSEMENT_STATUS")
public class ReimburseStatus {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(nullable = false, name = "REIMB_STATUS_ID")
	private int statusId;
	
	@Column(name = "REIMB_STATUS", nullable = false)
	private String status;

	public ReimburseStatus(int statusId) {
		super();
		this.statusId = statusId;
	}
	public ReimburseStatus(int statusId, String status) {
		super();
		this.statusId = statusId;
		this.status = status;
	}
	public ReimburseStatus() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getStatusId() {
		return statusId;
	}
	public void setStatusId(int statusId) {
		this.statusId = statusId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + statusId;
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
		ReimburseStatus other = (ReimburseStatus) obj;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		if (statusId != other.statusId)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "ReimburseStatus [statusId=" + statusId + ", status=" + status + "]";
	}
	
}
