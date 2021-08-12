package com.lti.models;

import javax.persistence.*;

@Entity
@Table(name = "ERS_REIMBURSEMENT_TYPE")
public class ReimburseType {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(nullable = false, name = "REIMB_TYPE_ID")
	private int typeId;
	
	@Column(name = "REIMB_TYPE", nullable = false)
	private String type;
	
	public ReimburseType() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ReimburseType(int typeId, String type) {
		super();
		this.typeId = typeId;
		this.type = type;
	}
	public ReimburseType(int typeId) {
		super();
		this.typeId = typeId;
	}
	public int getTypeId() {
		return typeId;
	}
	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		result = prime * result + typeId;
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
		ReimburseType other = (ReimburseType) obj;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		if (typeId != other.typeId)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "ReimburseType [typeId=" + typeId + ", type=" + type + "]";
	}
	
	
}
